import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BeforeLoginLandingPage } from './before-login-landing.page';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreatePasswordComponent } from './components/create-password/create-password.component';
import { FirstLoginComponent } from './components/first-login/first-login.component';

const routes: Routes = [
  {
    path: '', component: BeforeLoginLandingPage,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'forgot-password', component: ForgotPasswordComponent },
      { path: 'create-password', component: CreatePasswordComponent },
      { path: 'first-login', component: FirstLoginComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BeforeLoginLandingPageRoutingModule { }
