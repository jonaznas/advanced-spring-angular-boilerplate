import { NgModule }             from '@angular/core';
import { CommonModule }         from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SecondComponent }      from './views/second/second.component';
import { FirstComponent }       from './views/first/first.component';
import { UserAuthGuard }        from './guards/user-auth.guard';

const routes: Routes = [
  {path: '', component: FirstComponent},
  {path: 'second/:param', component: SecondComponent, canActivate: [UserAuthGuard]}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule {
}
