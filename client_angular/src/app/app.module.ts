import { BrowserModule } from '@angular/platform-browser';
import { NgModule }      from '@angular/core';

import { AppComponent }     from './app.component';
import { ViewsModule }      from './views/views.module';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule }     from '@angular/router';

import { WebsocketService } from './services/websocket.service';

import { HeaderComponent } from './shared/header.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    ViewsModule
  ],
  providers: [
    WebsocketService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
