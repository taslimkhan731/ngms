import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/Services/authentication/auth.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { emailValidator, mobileNumberValidator, nameValidator } from 'src/app/validators/custom-validators';
// import { userIdValidator, passwordValidator, emailValidator, numberValidator, nameValidator, mobileNumberValidator } from './../../validators/custom.validators';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {

  public captchaKey;
  public captchaURL;

  public registrationForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private mdmService: MdmService,
    private commonService: CommonService
  ) {
    this.createRegistrationFormInstance();
  }

  public createRegistrationFormInstance() {
    this.registrationForm = this.fb.group({
      name: ['', Validators.compose([Validators.required,nameValidator])],
      email: ['', Validators.compose([Validators.required,emailValidator])],
      mobile: ['', Validators.compose([Validators.required,mobileNumberValidator])],
      dob: ['', Validators.compose([Validators.required])],
      guestGender: ['', Validators.compose([Validators.required])],
      resAddr: ['', Validators.compose([Validators.required])],
      captcha: ['', Validators.compose([Validators.required])]
    });
  }

  ngOnInit() {
    this.getCaptcha();
  }

  public getCaptcha() {
    this.authService.getCaptchaData().subscribe(
      res => {
        if (res) {
          this.captchaKey = res.headers.get('captchaKey');
          this.createImageFromBlob(res.body);
        }
      },
      error => {
      })
  }

  public createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.captchaURL = reader.result;
    }, false);
    if (image) {
      reader.readAsDataURL(image);
    }
  }

  public getRegistrationData(data) {
    if (data.captcha) {
      this.mdmService.registration(data, this.captchaKey).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.storeDetails(data);
            this.commonService.showSuccessToast(res.statusDesc);
            this.router.navigate(['../' + 'create-password'], { relativeTo: this.route });
          } else if (res.status == 0) {
            this.commonService.showErrorToast(res.statusDesc);
            this.registrationForm.get('captcha').reset();
            this.getCaptcha();
          }
        },
        err => {
          this.commonService.showErrorToast('Something went wrong');
        });
    }
  }

  public goToLogin(path: String) {
    this.router.navigate(['../' + path], { relativeTo: this.route });
  }

}











