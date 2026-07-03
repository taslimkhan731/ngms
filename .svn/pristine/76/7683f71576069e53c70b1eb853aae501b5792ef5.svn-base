import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, Platform } from '@ionic/angular';
import { CommonService } from './Services/common/common.service';
import { Location } from '@angular/common';
// import { capacitorSplashScreen } from '@capacitor/core/dist';
// import { SplashScreen } from '@capacitor/splash-screen';
// const { capacitorSplashScreen } = Plugins;
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {

  public auth = false;

  constructor(
    private platform: Platform,
    private router: Router,
    private _location: Location,
    private alertCtrl: AlertController,
    private commonService:CommonService,
    // private network: Network
  ) 
  {
    if (window.location !== window.parent.location) {
      this.auth = false;
    } else {
      this.auth = true;
      var status = localStorage.getItem('Login')
      if (status === 'sucess') {
        this.router.navigate(['/after-login'])
      }
    }
    // this.checkNetworkStatus();
    this.initializeApp();
  }

  ngOnInit() {
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
  }

  // DON'T USE SPLASHSCREEN SHOW METHOD ANYWHERE
  // SplashScreen.show(); 

  initializeApp() {
    this.platform.ready().then(async () => {
      // this.router.navigateByUrl("splash");
      // SplashScreen.hide();
      this.registerBackButtonAction()
    });
  }

  private registerBackButtonAction() {
    this.platform.backButton.subscribeWithPriority(10, async (processNextHandler) => {
      console.log('Back press handler!', this.router.url);
      if (this._location.isCurrentPathEqualTo('/after-login/pages/orgadmin') || this._location.isCurrentPathEqualTo('/before-login-landing/login') || this._location.isCurrentPathEqualTo('/after-login/pages/orgadmin/application-menu/application-entry')) {
        // await this.askForConfirmation(); // Wait for user confirmation
        navigator['app'].exitApp();
        // processNextHandler(); // Continue handling the back button event
      // } else if (this._location.isCurrentPathEqualTo('/after-login/pages/guest/view-booking/guest-house') || this._location.isCurrentPathEqualTo('/after-login/pages/guest/view-booking/conference') || this._location.isCurrentPathEqualTo('/after-login/pages/guest/profile/view-profile') ) {
      //   this.router.navigate(['/after-login/pages/guest/dashboard']);
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

  // initializeApp() {

  //   this.platform.ready().then(() => {

  //     // this.statusBar.backgroundColorByHexString('#3880ff');

  //     capacitorSplashScreen.hide();

  //   });

  // }
}
