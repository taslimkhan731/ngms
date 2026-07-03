import { Component, NgZone, OnInit, Optional } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { IonRouterOutlet, LoadingController, Platform } from '@ionic/angular';
import { AuthService } from 'src/app/Services/authentication/auth.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { EncryptionService } from 'src/app/Services/encryption/encryption.service';
import { StorageService } from 'src/app/Services/storage/storage.service';
import { AppConstants } from 'src/app/app.constants';
import { App } from '@capacitor/app';
import jwt_decode from 'jwt-decode';
import { mobileNumberValidator } from 'src/app/validators/custom-validators';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})

export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  public captchaURL: any = '';
  public captchaKey: any = '';
  public isSubmitBtnClicked = false;
  public showPassword = false;
  public dataFromToken;
  private lastBackButtonTap: number = 0;
  private doubleTapInterval: number = 300; // milliseconds
  showError: boolean;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private platform:Platform,
    private commonService: CommonService,
    private authService: AuthService,
    private loadingController:LoadingController,
    public sanitizer: DomSanitizer,
    public encryptService: EncryptionService,
    public storageService: StorageService,
    private ngZone: NgZone,
    @Optional() private routerOutlet?: IonRouterOutlet
  ) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function(event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.isSubmitBtnClicked = false;
      this.showPassword=false
      this.captchaKey = '';
      this.captchaURL = '';
      this.getCaptcha();
      processNextHandler();

    });
    this.createLoginFormInstance();
    // this.initializeApp();
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.registerBackButtonAction();
    });
  }

  public createLoginFormInstance() {
    this.loginForm = this.fb.group({
      userType: ['GUEST'],
      userId: ['', Validators.compose([Validators.required,mobileNumberValidator])],
      password: ['', Validators.compose([Validators.required])],
      captcha: ['', Validators.compose([Validators.required])]
    });
  }

  ngOnInit() {
    console.log("ngOnInit")
    this.route.data.subscribe((data: any) => { 
      console.log('Code executed when revisiting the page');
      this.getCaptcha();
    })
  }

  // ionViewWillEnter(){
  //   console.log("ionViewDidEnter")
  //   this.getCaptcha();
  // }


  public async getCaptcha() {
    this.loginForm.get('captcha').reset();
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    loader.present()
    this.authService.getCaptchaData().pipe(
      catchError((error) => {
        // Handle the error here
        console.error('Error fetching captcha data:', error);
  
        // Optionally, you can display a user-friendly error message
        // and provide fallback content.
        // For example, set a flag to display an error message in the template.
        this.showError = true;
  
        // Rethrow the error to propagate it to the component that subscribes
        return throwError(error);
      })
    ).subscribe(
      (res) => {
        if (res) {
          this.captchaKey = res.headers.get('captchaKey');
          this.createImageFromBlob(res.body);
          console.log(res);
          // Optionally, hide any error messages if the request succeeds.
          this.showError = false;
        }
        loader.dismiss()
      },
      (error) => {
        loader.dismiss()
        // Handle any additional error handling here if needed.
      },
      ()=> {
        loader.dismiss()
      }
    );
  }
  

  public createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.captchaURL = reader.result;
    },
      false);
    if (image) {
      reader.readAsDataURL(image);
    }
  }

  public async getLoginData(data) {
    this.isSubmitBtnClicked = true;
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    if(this.loginForm.invalid){
      return;
    }
    if (data.captcha) {
      let encPassword = this.encryptService.encryptPasswordWithSalt(data.password, data.captcha);
      const loginData= {
        'userType': 'GUEST',
        'userId': data.userId,
        'password': encPassword,
        'captcha': data.captcha
      };
      // this.commonService.showLoading();
      loader.present()
      this.authService.login(loginData, this.captchaKey).subscribe(
        res => {
          if (res.status == 1) {
            loader.dismiss();
            localStorage.setItem('accessTokenDetails', JSON.stringify(res.access_token));
            this.dataFromToken = jwt_decode(res.access_token)
            localStorage.setItem('dataFromToken', JSON.stringify(this.dataFromToken));
            console.log(this.dataFromToken)
            if (res.firstLogin == false) {
              this.router.navigate(['/before-login-landing/first-login']);
            }
            else if (res.firstLogin == true) {
              
              this.router.navigate(['/after-login']);
            }

          }
          else if (res.status == 0) {
            loader.dismiss();
            this.commonService.showErrorToast(res.statusDesc);
            this.loginForm.get('captcha').reset();
            this.getCaptcha();
          }
          // this.commonService.hideLoading();
        },
        err => {
          loader.dismiss();
          this.commonService.showErrorToast('Something went wrong');
          this.loginForm.get('captcha').reset();
          this.getCaptcha();
          // this.commonService.hideLoading();
        },
        ()=>{
          loader.dismiss()
        });
    }
  }

  public goForRegistration(path: String) {
    // this.router.navigate(['../' + path], { relativeTo: this.route });
    this.ngZone.run(()=> {
      this.router.navigateByUrl('/before-login-landing/register');
    })
  }

  public forgetPassword(path: String) {
    this.ngZone.run(()=> {
      this.router.navigateByUrl('/before-login-landing/forgot-password');
    })
    // this.router.navigate(['../' + path], { relativeTo: this.route });
  }

  public togglePassword(){
    this.showPassword = !this.showPassword;
  }

  private registerBackButtonAction() {
    this.platform.backButton.subscribeWithPriority(10, () => {
      // // if (!this.routerOutlet.canGoBack()) {
      // //   App.exitApp();
      // // }
      // console.log('Back button pressed in:', this.constructor.name);
      // const now = Date.now();
      // if (now - this.lastBackButtonTap < this.doubleTapInterval) {
      //   App.exitApp();
      // } else {
      //   this.lastBackButtonTap = now;
      // }
    });
  }
}