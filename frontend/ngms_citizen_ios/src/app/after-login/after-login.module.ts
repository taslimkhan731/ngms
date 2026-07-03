import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AfterLoginPageRoutingModule } from './after-login-routing.module';

import { AfterLoginPage } from './after-login.page';
import { DirectivesModule } from '../directives/directives.module';
import { HeadingToolbarComponent } from './heading-toolbar/heading-toolbar.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AfterLoginPageRoutingModule,
    DirectivesModule,
  ],
 
  declarations: [
    AfterLoginPage,HeadingToolbarComponent
  ],
    
})
export class AfterLoginPageModule { }
