import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule, HAMMER_GESTURE_CONFIG, } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';
import { DatePipe, registerLocaleData } from '@angular/common';
import { File } from '@ionic-native/file';
import { Network } from '@ionic-native/network/ngx';
import localeEn from '@angular/common/locales/en';
import { SharedModule } from './shared/shared.module';
// import { InAppBrowser } from '@ionic-native/in-app-browser/ngx';
// import { PdfJsViewerModule } from 'ngx-extended-pdf-viewer';
// import { QRScanner } from '@ionic-native/qr-scanner/ngx';
// import { NativeGeocoder, NativeGeocoderResult, NativeGeocoderOptions } from '@awesome-cordova-plugins/native-geocoder/ngx';
registerLocaleData(localeEn)
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule, ReactiveFormsModule,
    FormsModule, HttpClientModule,SharedModule],
  providers: [ PDFGenerator,{provide: LOCALE_ID,useValue:'en'},{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }, DatePipe, Network],
  bootstrap: [AppComponent],
  
  
})
export class AppModule { }
