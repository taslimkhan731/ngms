import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, Platform } from '@ionic/angular';
import { Network } from '@ionic-native/network/ngx';
import { CommonService } from './Services/common/common.service';
// import { capacitorSplashScreen } from '@capacitor/core/dist';
import { SplashScreen } from '@capacitor/splash-screen';
// const { capacitorSplashScreen } = Plugins;
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {

  public auth = false;
  currentRoute: string;
  constructor(
    private platform: Platform,
    private _location: Location,
    private router: Router,
    private alertCtrl: AlertController,
    private commonService:CommonService,
    private network: Network
  ) {
    if (window.location !== window.parent.location) {
      this.auth = false;
    } else {
      this.auth = true;
      var status = localStorage.getItem('Login')
      if (status === 'sucess') {
        this.router.navigate(['/after-login'])
      }
    }
    this.checkNetworkStatus();
    this.initializeApp()
  }

  async ngOnInit() {
    // document.documentElement.style.setProperty('--darkTheme-color', '#35488a');
    // document.documentElement.style.setProperty('--lightTheme-color', '#1642dbc2');
    // document.documentElement.style.setProperty('--lighterTheme-color', '#1642dbc2');
    // document.documentElement.style.setProperty('--header-color', '#35488a');
    // document.documentElement.style.setProperty('--menu-color', '#35488a');
    const dataFromToken = localStorage.getItem('dataFromToken');
    if (dataFromToken !== null) {
      // Data is found, navigate to another component
      this.router.navigate(['/after-login']); 
    } else {
      // Data is not found, navigate to the login component
      this.router.navigate(['/before-login-landing']);
    }
    await SplashScreen.show({
      showDuration: 1000,
      autoHide: true,
    });
  }

  // DON'T USE SPLASHSCREEN SHOW METHOD ANYWHERE
  // SplashScreen.show(); 

  initializeApp() {
    this.platform.ready().then(async () => {
      // this.router.navigateByUrl("splash");
      SplashScreen.hide();
      this.registerBackButtonAction()
    });
  }

  checkNetworkStatus() {
    const networkType = this.network.type;
    // if(networkType === 'none' || networkType === 'unknown') {
    //   this.commonService.showNoInternetToast("No internet connection 1");
    // } else {
    //   // this.commonService.showAvailInternetToast("Internet connection available");
    // }

    this.network.onConnect().subscribe(()=> {
      this.commonService.showAvailInternetToast("Connected to internet");
    });
    this.network.onDisconnect().subscribe(()=> {
      this.commonService.showNoInternetToast("No internet connection");
    });
  }

  private registerBackButtonAction() {
    this.platform.backButton.subscribeWithPriority(10, async (processNextHandler) => {
      console.log('Back press handler!', this.router.url);
      if (this._location.isCurrentPathEqualTo('/after-login/pages/guest/dashboard') || this._location.isCurrentPathEqualTo('/after-login/pages/guest') || this._location.isCurrentPathEqualTo('/before-login-landing/login')) {
        // await this.askForConfirmation(); // Wait for user confirmation
        navigator['app'].exitApp();
        // processNextHandler(); // Continue handling the back button event
      } else if(this._location.isCurrentPathEqualTo('/before-login-landing/create-password?type=Registration')){
        this.askForConfirmation()
      } else if (this._location.isCurrentPathEqualTo('/after-login/pages/guest/view-booking/guest-house') || this._location.isCurrentPathEqualTo('/after-login/pages/guest/view-booking/conference') || this._location.isCurrentPathEqualTo('/after-login/pages/guest/profile/view-profile') ) {
        this.router.navigate(['/after-login/pages/guest/dashboard']);
      } else {
        console.log('Navigate to back page');
        this._location.back();
        // processNextHandler();
      }
    });
  
    // this.platform.backButton.subscribeWithPriority(5, () => {
    //   console.log('Handler called to force close!');
    //   navigator['app'].exitApp();
    // });
  }
  

  public async askForConfirmation() {
    const alert = await this.alertCtrl.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'are you sure you don\'t want to proceed further',
      buttons: [
        {
          text: 'Yes',
          handler: () => {
            this._location.back();
          },
        },
        {
          text: 'No',
          // role: 'cancel',
          // cssClass: 'alertButton',
          handler: () => {
          },
        },
      ],
    });
    await alert.present();
  }

  // initializeApp() {
  //   this.platform.ready().then(() => {
  //     // this.statusBar.backgroundColorByHexString('#3880ff');
  //     capacitorSplashScreen.hide();
  //   });
  // }
}
