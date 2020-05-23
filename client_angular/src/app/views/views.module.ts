import { NgModule }     from '@angular/core';
import { CommonModule } from '@angular/common';

import { SocketIoModule, SocketIoConfig } from 'ngx-socket-io';

const config: SocketIoConfig = {url: environment.backend, options: {}};

import { WebsocketService } from 'app/services/websocket.service';

import { SecondComponent } from 'app/views/second/second.component';
import { FirstComponent }  from 'app/views/first/first.component';
import { environment }     from 'environments/environment';

@NgModule({
  declarations: [
    SecondComponent,
    FirstComponent
  ],
  providers: [
    WebsocketService
  ],
  imports: [
    CommonModule,
    SocketIoModule.forRoot(config)
  ]
})

export class ViewsModule {
}
