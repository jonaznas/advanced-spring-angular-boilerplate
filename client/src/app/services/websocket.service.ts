import { Injectable } from '@angular/core';
import { Socket }     from 'ngx-socket-io';

@Injectable({
    providedIn: 'root'
})
export class WebsocketService {

    constructor(
        private socket: Socket,
    ) {
        this.socket.on('broadcast', res => {
            console.log(res);
        });
    }

    request(event: string, data: object, session: string = localStorage.session || null): Promise<object> {
        return new Promise<object>(resolve => {
            this.socket.emit(event, {
                session: session,
                data: data
            }, res => {
                resolve(res);
            });
        });
    }

}
