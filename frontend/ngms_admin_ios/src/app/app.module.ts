import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule, HAMMER_GESTURE_CONFIG, } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
// import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';
import { DatePipe, registerLocaleData } from '@angular/common';
import { TooltipModule } from 'ng2-tooltip-directive';
import localeEnIN from '@angular/common/locales/en-IN';
// import { QRScanner } from '@ionic-native/qr-scanner/ngx';
// import { NativeGeocoder, NativeGeocoderResult, NativeGeocoderOptions } from '@awesome-cordova-plugins/native-geocoder/ngx';
registerLocaleData(localeEnIN)
@NgModule({
  declarations: [AppComponent,],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule, ReactiveFormsModule,TooltipModule,
    FormsModule, HttpClientModule],
  providers: [ {provide: LOCALE_ID,useValue:'en-IN'},{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }, DatePipe],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
