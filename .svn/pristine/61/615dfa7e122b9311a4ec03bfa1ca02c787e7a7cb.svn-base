import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router, NavigationEnd  } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { AuthService } from 'src/app/Services/authentication/auth.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { EncryptionService } from 'src/app/Services/encryption/encryption.service';
import { StorageService } from 'src/app/Services/storage/storage.service';
import { AppConstants } from 'src/app/app.constants';
import jwt_decode from 'jwt-decode';
import { NavController,Platform } from '@ionic/angular';
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

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private loadingCtrl: LoadingController,
    private navCtrl:NavController,
    private fb: FormBuilder,
    private commonService: CommonService,
    private authService: AuthService,
    private platform: Platform,
    private ngZone: NgZone,
    public sanitizer: DomSanitizer,
    public encryptService: EncryptionService,
    public storageService: StorageService
  ) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function(event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.getCaptcha();
      processNextHandler();

    });
    this.createLoginFormInstance();
  }

  public createLoginFormInstance() {
    this.loginForm = this.fb.group({
      userType: ['', Validators.compose([Validators.required])],
      userId: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])],
      captcha: ['', Validators.compose([Validators.required])]
    });
  }
  ngOnInit() {
      this.getCaptcha();
  }

  public async getCaptcha() {
    console.log("Inside getCaptcha")
    this.loginForm.get('captcha').reset();
    const loader = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loader.present();
    this.authService.getCaptchaData().subscribe(
      res => {
        if (res) {
          this.captchaKey = res.headers.get('captchaKey');
          this.createImageFromBlob(res.body);
          loader.dismiss();
        } else {
          loader.dismiss()
        }
      },
      error => {
        loader.dismiss();
      },
      ()=>{
        loader.dismiss()
      })
  }

  public createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.captchaURL = reader.result;
    },
      false);
    if (image) {
    console.log("$$$$$$$$")
      reader.readAsDataURL(image);
    }
    console.log("$$$$$$$$",this.captchaURL)

  }

  public async getLoginData(data) {
    console.log("Inside getLoginData")
    const loader = await this.loadingCtrl.create({
      message:'Please wait..'
    })
    this.isSubmitBtnClicked = true;
    if(this.loginForm.invalid){
      return;
    }
    console.log("login data",data)
    if (data.captcha) {
      let encPassword = this.encryptService.encryptPasswordWithSalt(data.password, data.captcha);
      const loginData: object = {
        'userType': data.userType,
        'userId': data.userId,
        'password': encPassword,
        'captcha': data.captcha
      };
      localStorage.setItem('loginType', data.userType);
      loader.present();
      this.authService.login(loginData, this.captchaKey).subscribe(
        res => {
          if (res.status == 1) {
            loader.dismiss();
            console.log(res)
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
            loader.dismiss()
            this.commonService.showErrorToast(res.statusDesc);
            this.loginForm.get('captcha').reset();
            this.getCaptcha();
          }
        },
        err => {
          loader.dismiss();
          console.log("jhjdfjsdfh")
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

  // public goForRegistration(path: String) {
  //   this.router.navigate(['../' + path], { relativeTo: this.route });
  // }

  public togglePassword(){
    this.showPassword = !this.showPassword;
  }

  navigateBack() {
    this.navCtrl.pop(); // Use the NavController to navigate back
  }


  public forgetPassword(path: String) {
    this.ngZone.run(()=>{
      this.router.navigateByUrl('/before-login-landing/forgot-password')
    })
    // this.router.navigate(['../' + path], { relativeTo: this.route });
  }
}