import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Subscription, timer } from 'rxjs';
import { take } from 'rxjs/operators';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { EncryptionService } from 'src/app/Services/encryption/encryption.service';
import { matchingPasswords, numberValidator, passwordValidator } from 'src/app/validators/custom-validators';

@Component({
  selector: 'app-create-password',
  templateUrl: './create-password.component.html',
  styleUrls: ['./create-password.component.scss'],
})
export class CreatePasswordComponent implements OnInit {

  public createPasswordForm: FormGroup;
  public registrationData
  countDown: Subscription;
  counter = 120;
  tick = 1000;
  buttonName: string;
  otpType: any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formFB: FormBuilder,
    private mdmService: MdmService,
    private commonService: CommonService,
    private datePipe: DatePipe,
    private loadingController:LoadingController,
    public encryptService: EncryptionService
  ) {
    this.createPassword()
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.otpType = params['type'];
      console.log("type is", this.otpType);
    });
    this.registrationData = this.commonService.getStoredDetails();
    console.log("register data", this.registrationData);
    this.countDown = timer(0, this.tick)
      .pipe(take(this.counter))
      .subscribe(() => {
        --this.counter;
        this.buttonName = 'OTP Resend in' + " " + this.counter + " " + "sec"
        if (this.counter == 0) {
          this.buttonName = 'OTP Resend'
          this.countDown.unsubscribe();
        }
      });
    if (!this.registrationData) {
      this.router.navigate(['../' + 'register'], { relativeTo: this.route });
    }
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
      password: ['', Validators.compose([Validators.required,passwordValidator])],
      cpassword: ['', Validators.compose([Validators.required])],
      otp: ['', Validators.compose([Validators.required,numberValidator])],
      dob: [''],
      officeAddr: [''],
      resAddr: [''],
      guestGender: ['']
    }, {
      validator: matchingPasswords('password', 'cpassword')
    })
  }

  public async submitCreatePasswordForm(data) {
    const loader = await  this.loadingController.create({
      message:'Please wait...'
    })
    this.registrationData = this.commonService.getStoredDetails();
    let encPassword = this.encryptService.encodePassword(data.password, 1);
    let encCPassword = this.encryptService.encodePassword(data.cpassword, 1);
    console.log(this.registrationData)
    if (this.commonService.otpPageType=='registration') {
      const parts = this.registrationData.dob.split('-');
      const formattedDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);
      const createPasswordData: object = {
        'userType': this.registrationData.userType,
        'userId': this.registrationData.userId,
        'name': this.registrationData.name,
        'mobile': this.registrationData.mobile,
        'email': this.registrationData.email,
        'password': encPassword,
        'cpassword': encCPassword,
        'otp': data.otp,
        'dob': this.datePipe.transform(formattedDate, 'dd-MM-yyyy'),
        'officeAddr': this.registrationData.officeAddr,
        'resAddr': this.registrationData.resAddr,
        'guestGender': this.registrationData.guestGender
      };
      loader.present()
      this.mdmService.createPassword(createPasswordData).subscribe(
        res => {
          if (res.status == 1) {
            loader.dismiss()
            this.commonService.showSuccessToast(res.statusDesc);
            this.router.navigate(['/before-login-landing/login']);
          } else if (res.status == 0) {
            loader.dismiss()
            this.commonService.showErrorToast(res.statusDesc);
          }
        },
        err => {
          loader.dismiss()
          this.commonService.showErrorToast('Something went wrong');
        },
        ()=>{
          loader.dismiss()
        });
    } else if (this.commonService.otpPageType=='forgotPassword') {
      const createPasswordData: object = {
        'userType': this.registrationData.userType,
        'userId': this.registrationData.mobile,
        'password': encPassword,
        'cpassword': encCPassword,
        'otp': data.otp,
      };
      console.log(createPasswordData)
      loader.present()
      this.mdmService.validateForgotPasswordOtp(createPasswordData).subscribe(
        res => {
          if (res.status == 1) {
            loader.dismiss()
            this.commonService.showSuccessToast(res.statusDesc);
            this.router.navigate(['/before-login-landing/login']);
          } else if (res.status == 0) {
            loader.dismiss()
            this.commonService.showErrorToast(res.statusDesc);
          }
        },
        err => {
          loader.dismiss()
          this.commonService.showErrorToast('Something went wrong');
        },
        ()=>{
          loader.dismiss()
        });
    }
  }
  public resendOTP() {
    if (this.otpType == 'Registration') {
      const obj = {
        email: this.registrationData.email,
        mobile: this.registrationData.mobile
      }
      this.mdmService.ResendOtpRegistration(obj).subscribe(res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast('OTP Resend Successfully');
          this.router.navigate(['../' + 'create-password'], { relativeTo: this.route },);
        } else if (res.status == 0) {
          this.commonService.showErrorToast(res.statusDesc);
          // this.forgotPasswordForm.get('captcha').reset();
        }
      })
    }
    else if (this.otpType == 'ForgotPassword') {
      const obj = {
        captcha: "",
        mobile: this.registrationData.mobile,
        userType: "GUEST"
      }
      this.mdmService.ResendOtp(obj).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.showSuccessToast('OTP Resend Successfully');
            this.router.navigate(['../' + 'create-password'], { relativeTo: this.route },);
          } else if (res.status == 0) {
            this.commonService.showErrorToast(res.statusDesc);
            // this.forgotPasswordForm.get('captcha').reset();
          }
        },
        err => {
          this.commonService.showErrorToast('Something went wrong');
          // this.forgotPasswordForm.get('captcha').reset();
        });
    }



  }

}
