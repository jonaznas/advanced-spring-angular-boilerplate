import { BrowserModule } from '@angular/platform-browser';
import { NgModule }      from '@angular/core';

import { AppComponent }     from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule }     from '@angular/router';

import { HeaderComponent }  from './shared/header.component';
import { SecondComponent }  from "./views/second/second.component";
import { FirstComponent }   from "./views/first/first.component";
import { HttpClientModule } from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FirstComponent,
    SecondComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
