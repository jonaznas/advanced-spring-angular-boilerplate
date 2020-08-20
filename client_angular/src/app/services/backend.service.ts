import { Injectable }  from '@angular/core';
import * as atmosphere from 'atmosphere.js'
import { environment } from "../../environments/environment";
import { HttpClient }  from "@angular/common/http";
import Request = Atmosphere.Request;

@Injectable({
    providedIn: 'root'
})
export class BackendService {

    constructor(private httpClient: HttpClient) {

    }

    rest(path: string, data: object) {
        return new Promise(resolve => {
            const url = environment.backend.rest + path

            this.httpClient.post(url, data).subscribe(resolve)
        })
    }

    push(request: Atmosphere.Request, data: object) {
        const string = JSON.stringify({
            session: "xxx-xxx",
            body: data
        })
        request.push(string)
    }

    service(
        service: string,
        onMessage: (response: Atmosphere.Response) => void = null,
        onError: (response: Atmosphere.Response) => void   = null
    ) {
        const builder = this.build(service)

        if (onMessage != null) builder.onMessage = onMessage
        if (onError != null) builder.onError = onError

        return this.subscribe(builder)
    }

    build(service: string) {
        const builder = new atmosphere.AtmosphereRequest();

        builder.url = environment.backend.service + service
        builder.enableXDR = true;
        builder.contentType = "application/json";
        builder.transport = "websocket";
        builder.fallbackTransport = "long-polling";

        if (!environment.production) builder.logLevel = "debug"

        return builder
    }

    subscribe(builder: Request) {
        const socket = atmosphere
        return socket.subscribe(builder)
    }
}
