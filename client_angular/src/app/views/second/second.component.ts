import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute }               from '@angular/router';
import { BackendService }               from "../../services/backend.service";

@Component({
    selector: 'app-second',
    templateUrl: './second.component.html',
    styleUrls: []
})
export class SecondComponent implements OnInit, OnDestroy {
    public registration: Atmosphere.Request
    public login: Atmosphere.Request

    public param: string;

    constructor(
        private route: ActivatedRoute,
        private backend: BackendService
    ) {
        this.registration = this.backend.service("/auth/registration", this.registrationHandler)
        this.login = this.backend.service("/auth/login", this.loginHandler)
    }

    async ngOnDestroy() {
        this.registration.close()
    }

    async ngOnInit() {
        this.param = this.route.snapshot.paramMap.get('param');
    }


    async startRegistration() {
        this.backend.push(this.registration, {
            username: "Admin",
            password: "123456."
        })
    }

    async startLogin() {
        this.backend.push(this.login, {
            username: "Admin",
            password: "123456."
        })
    }

    registrationHandler(response: Atmosphere.Response) {
        const body = JSON.parse(response.responseBody)
        const success = body.success || false

        console.log(body)

        alert("registration success: " + success)
    }

    loginHandler(response: Atmosphere.Response) {
        const body = JSON.parse(response.responseBody)
        const success = body.success || false

        console.log(body)

        alert("login success: " + success)
    }
}
