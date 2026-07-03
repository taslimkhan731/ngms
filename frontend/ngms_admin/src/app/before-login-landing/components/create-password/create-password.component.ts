import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription, timer } from 'rxjs';
import { take } from 'rxjs/operators';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { AuthService } from 'src/app/Services/authentication/auth.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { EncryptionService } from 'src/app/Services/encryption/encryption.service';

@Component({
  selector: 'app-create-password',
  templateUrl: './create-password.component.html',
  styleUrls: ['./create-password.component.scss'],
})
export class CreatePasswordComponent implements OnInit,OnDestroy {

  public createPasswordForm: FormGroup;
  public registrationData
  countDown: Subscription;
  counter = 60;
  tick = 1000;
  buttonName: string;
  otpData: any;
  captchaKey: any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formFB: FormBuilder,
    private mdmService: MdmService,
    private commonService: CommonService,
    public encryptService: EncryptionService,
    private authService: AuthService,
  ) {
    this.createPassword()
  }

  ngOnInit() {
    this.otpData=this.commonService.getStoredDetails();
    console.log("otp data", this.otpData);
    this.getCaptcha();
    this.countDown = timer(0, this.tick)
    .pipe(take(this.counter))
    .subscribe(() => {
      --this.counter;
      this.buttonName='OTP Resend in'+" " +this.counter +" "+"sec"
      if (this.counter == 0) {
        this.buttonName='OTP Resend'
        this.countDown.unsubscribe();
      }
    });
    this.registrationData = this.commonService.getStoredDetails();
    if (!this.registrationData) {
      this.router.navigate(['../' + 'register'], { relativeTo: this.route });
    }
  }
  public getCaptcha(){
    this.authService.getCaptchaData().subscribe(res => {
      if (res) {
        this.captchaKey = res.headers.get('captchaKey');
      }
    },
      error => {
      })
  }
  public goToLoginPage(path: String) {
    this.router.navigate(['../' + path], { relativeTo: this.route });
  }

  public createPassword() {
    this.createPasswordForm = this.formFB.group({
      userType: ['GUEST'],
      userId: [''],
      name: [''],
      mobile: [''],
      email: [''],
      password: ['', Validators.compose([Validators.required])],
      cpassword: ['', Validators.compose([Validators.required])],
      otp: ['', Validators.compose([Validators.required])],
      dob: [''],
      officeAddr: [''],
      resAddr: [''],
      guestGender: ['']
    })
  }
  ngOnDestroy() {
    this.countDown = null;
  }
  public submitCreatePasswordForm(data) {
    this.registrationData = this.commonService.getStoredDetails();
    let encPassword = this.encryptService.encodePassword(data.password, 1);
    let encCPassword = this.encryptService.encodePassword(data.cpassword, 1);
    if (this.registrationData.email) {
      const createPasswordData: object = {
        'userType': this.registrationData.userType,
        'userId': this.registrationData.userId,
        'name': this.registrationData.name,
        'mobile': this.registrationData.mobile,
        'email': this.registrationData.email,
        'password': encPassword,
        'cpassword': encCPassword,
        'otp': data.otp,
        'dob': this.registrationData.dob,
        'officeAddr': this.registrationData.officeAddr,
        'resAddr': this.registrationData.resAddr,
        'guestGender': this.registrationData.guestGender
      };
      this.mdmService.createPassword(createPasswordData).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.showSuccessToast(res.statusDesc);
            this.router.navigate(['/before-login-landing/login']);
          } else if (res.status == 0) {
            this.commonService.showErrorToast(res.statusDesc);
          }
        },
        err => {
          this.commonService.showErrorToast('Something went wrong');
        });
    } else if (!this.registrationData.email) {
      const createPasswordData: object = {
        'userType': this.registrationData.userType,
        'userId': this.registrationData.mobile,
        'password': encPassword,
        'cpassword': encCPassword,
        'otp': data.otp,
      };
      this.mdmService.validateForgotPasswordOtp(createPasswordData).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.showSuccessToast(res.statusDesc);
            this.router.navigate(['/before-login-landing/login']);
          } else if (res.status == 0) {
            this.commonService.showErrorToast(res.statusDesc);
          }
        },
        err => {
          this.commonService.showErrorToast('Something went wrong');
        });
    }
  }
  public resendOTP(){
    this.mdmService.forgotPasswordResendOtp(this.otpData).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast('An OTP has been sent to your Mobile number');
          this.router.navigate(['../' + 'create-password'], { relativeTo: this.route },);
        } else if (res.status == 0) {
          this.commonService.showErrorToast(res.statusDesc);
          // this.forgotPasswordForm.get('captcha').reset();
          this.getCaptcha();
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
        // this.forgotPasswordForm.get('captcha').reset();
        this.getCaptcha();
      });
  }

}
