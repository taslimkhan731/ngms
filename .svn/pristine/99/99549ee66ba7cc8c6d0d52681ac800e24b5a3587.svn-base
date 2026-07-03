import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { InAppBrowser } from '@awesome-cordova-plugins/in-app-browser/ngx';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-payment-modal',
  templateUrl: './payment-modal.component.html',
  styleUrls: ['./payment-modal.component.scss'],
})
export class PaymentModalComponent implements OnInit {

  constructor(
    private router: Router,
    private modalCtrl: ModalController,
    // private iab: InAppBrowser
  ) { }

  ngOnInit() {}
  

  public goBack() {
    // this.location.back()
    this.router.navigate(["/after-login/pages/guest/create-booking",]);

  }
  openInappBrowser(){
    // const browser = this.iab.create('https://ionicframework.com/');

// browser.executeScript(...);

// browser.insertCSS(...);
// browser.on('loadstop').subscribe(event => {
//    browser.insertCSS({ code: "body{color: red;" });
// });
  }

  
}
