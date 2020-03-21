import { Component, OnInit } from '@angular/core';
import { WebsocketService }  from 'app/services/websocket.service';
import { ActivatedRoute }    from '@angular/router';

@Component({
    selector: 'app-second',
    templateUrl: './second.component.html',
    styleUrls: []
})
export class SecondComponent implements OnInit {
    public param: string;
    text: any;

    constructor(
        private route: ActivatedRoute,
        private backend: WebsocketService
    ) {
    }

    ngOnInit() {
        this.param = this.route.snapshot.paramMap.get('param');
        this.text = this.param;
    }

    async testMessage(): Promise<void> {
        const res = await this.backend.request('aabc', {});
        console.log(res);
    }

}
