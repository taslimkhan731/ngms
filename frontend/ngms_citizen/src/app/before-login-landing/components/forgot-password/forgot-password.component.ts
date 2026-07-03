import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoadingController, Platform } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { AuthService } from 'src/app/Services/authentication/auth.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { mobileNumberValidator } from 'src/app/validators/custom-validators';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  public captchaURL: any = '';
  public captchaKey: any = '';
  public forgotPasswordForm: FormGroup;
  public isSubmitBtnClicked = false;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private formFB: FormBuilder,
    private mdmService: MdmService,
    private loadingController:LoadingController,
    private ngZone: NgZone,
    private platform: Platform,
    private commonService: CommonService) {
      this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
        document.addEventListener("backbutton", function(event) {
          event.preventDefault(); event.stopPropagation();
        }, false);
        this.getCaptcha();
        processNextHandler();
  
      });
    this.createForgotPasswordInstance();
  }

  public createForgotPasswordInstance() {
    this.forgotPasswordForm = this.formFB.group({
      userType: ['GUEST'],
      mobile: ['', Validators.compose([Validators.required,mobileNumberValidator])],
      captcha: ['', Validators.compose([Validators.required])]
    });
  }

  ngOnInit(): void {
    this.getCaptcha();
  }

  ionViewWillEnter() {
    this.getCaptcha();
  }

  public async getCaptcha() {
    this.forgotPasswordForm.get('captcha').reset();
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    loader.present()
    this.authService.getCaptchaData().subscribe(res => {
      if (res) {
        this.captchaKey = res.headers.get('captchaKey');
        this.createImageFromBlob(res.body);
      }
      loader.dismiss()
    },
      error => {
        loader.dismiss()
      },
      ()=> {
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
      reader.readAsDataURL(image);
    }
  }

  public submitOtpForgotPassword(formValue) {
    this.isSubmitBtnClicked = true;
    if (this.forgotPasswordForm.invalid) {
      return;
    }
    this.commonService.storeDetails(formValue,'forgotPassword');
    if (formValue) {
      this.mdmService.forgotPasswordOtp(formValue, this.captchaKey).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.showSuccessToast('An OTP has been sent to your Mobile number.');
            this.router.navigate(['before-login-landing/create-password'], { queryParams: { type: 'ForgotPassword' } });
          } else if (res.status == 0) {
            this.commonService.showErrorToast(res.statusDesc);
            this.forgotPasswordForm.get('captcha').reset();
            this.getCaptcha();
          }
        },
        err => {
          this.commonService.showErrorToast('Something went wrong');
          this.forgotPasswordForm.get('captcha').reset();
          this.getCaptcha();
        });
    }
  }

  public goToLogin(path: String) {
    // this.router.navigate(['../' + path], { relativeTo: this.route });
    this.ngZone.run(() => {
      this.router.navigateByUrl('/before-login-landing/login');
    })
  }
}
