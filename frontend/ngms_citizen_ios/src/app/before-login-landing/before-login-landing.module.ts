import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BeforeLoginLandingPageRoutingModule } from './before-login-landing-routing.module';

import { BeforeLoginLandingPage } from './before-login-landing.page';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreatePasswordComponent } from './components/create-password/create-password.component';
import { FirstLoginComponent } from './components/first-login/first-login.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    BeforeLoginLandingPageRoutingModule,
    ReactiveFormsModule 
  ],
  declarations: [
    BeforeLoginLandingPage,
    BeforeLoginLandingPage,
    RegisterComponent,
    LoginComponent,
    ForgotPasswordComponent,
    CreatePasswordComponent,
    FirstLoginComponent,
  ]
})

export class BeforeLoginLandingPageModule { }
