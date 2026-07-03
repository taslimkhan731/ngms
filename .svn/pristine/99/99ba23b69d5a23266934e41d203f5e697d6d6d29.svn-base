import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, NgZone, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/Services/authentication/auth.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { emailValidator, mobileNumberValidator, nameValidator } from 'src/app/validators/custom-validators';
import { DatePipe } from '@angular/common';
import { LoadingController, Platform } from '@ionic/angular';
import * as moment from 'moment';
import { format, parseISO } from 'date-fns';

// import { userIdValidator, passwordValidator, emailValidator, numberValidator, nameValidator, mobileNumberValidator } from './../../validators/custom.validators';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {

  public captchaKey;
  public captchaURL;
  public currentDate = new Date();
  public maxDate;
  public minDate=new Date(1950, 0, 1).toISOString().slice(0, 10);
  public registrationForm: FormGroup;
  public isSubmitBtnClicked = false;
  @ViewChild('dateInput', { static: false }) dateInput: any;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private mdmService: MdmService,
    private commonService: CommonService,
    private platform: Platform,
    private loadingController: LoadingController,
    private ngZone:NgZone
  ) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function(event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.getCaptcha();
      processNextHandler();

    });
    this.createRegistrationFormInstance();
  }

  public createRegistrationFormInstance() {
    this.registrationForm = this.fb.group({
      name: ['', Validators.compose([Validators.required, nameValidator])],
      email: ['', Validators.compose([emailValidator])],
      mobile: ['', Validators.compose([Validators.required, mobileNumberValidator])],
      dob: ['', Validators.compose([Validators.required])],
      guestGender: ['', Validators.compose([Validators.required])],
      resAddr: ['', Validators.compose([Validators.required])],
      captcha: ['', Validators.compose([Validators.required])]
    });
  }

  ngOnInit() {
    this.getCaptcha();
    this.currentDate.setDate(this.currentDate.getDate() - 1);
    this.maxDate = this.currentDate.toISOString().slice(0, 10);
    console.log("87reyeiyruw",this.maxDate)
  }
  ionViewWillEnter() {
    this.getCaptcha();
    this.currentDate.setDate(this.currentDate.getDate() - 1);
    this.maxDate = this.currentDate.toISOString().slice(0, 10);
    console.log("87reyeiyruw",this.maxDate)
  }

  public async getCaptcha() {
    this.registrationForm.get('captcha').reset();
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    loader.present()
    this.authService.getCaptchaData().subscribe(
      res => {
        if (res) {
          loader.dismiss()
          console.log(res.headers.get('captchaKey'))
          this.captchaKey = res.headers.get('captchaKey');
          this.createImageFromBlob(res.body);
        } else {
          loader.dismiss()
        }
      },
      error => {
        loader.dismiss()
        this.commonService.showErrorToast('Something went wrong');
      },
      ()=>{
        loader.dismiss()
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

  public async getRegistrationData(data) {
    this.isSubmitBtnClicked = true;
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    if (this.registrationForm.invalid) {
      return;
    }
    console.log(data.dob)
    // let objectDate = new Date(data.dob);
    // let day = String(objectDate.getDate()).padStart(2, '0');
    // let month = String(objectDate.getMonth() + 1).padStart(2, '0');
    // let year = objectDate.getFullYear();
    // data.dob = day + "-" + month + "-" + year;
    console.log(data.dob)
    console.log(data)

    if (data.captcha) {
      loader.present();
      this.mdmService.registration(data, this.captchaKey).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.storeDetails(data,'registration');
            loader.dismiss()
            this.commonService.showSuccessToast(res.statusDesc);
            this.router.navigate(['before-login-landing/create-password'], { queryParams: { type: 'Registration' } });
          } else if (res.status == 0) {
            loader.dismiss()
            this.commonService.showErrorToast(res.statusDesc);
            this.registrationForm.get('captcha').reset();
            this.getCaptcha();
          }
        },
        err => {
          loader.dismiss();
          this.commonService.showErrorToast('Something went wrong');
        },
        ()=>{
          loader.dismiss()
        });
    }
  }
  public getDate(event) {
    // return format(parsedDate, 'dd-MM-yyyy');
    const parsedDate = parseISO(event.target.value);
    this.registrationForm.get('dob').setValue(format(parsedDate, 'dd-MM-yyyy'))
    console.log(this.registrationForm.get('dob').value)

  }

  public openDatepicker() {
    this.dateInput.nativeElement.click();
  }

  public goToLogin(path: String) {
    // this.router.navigate(['../' + path], { relativeTo: this.route });
    this.ngZone.run(()=> {
      this.router.navigateByUrl('/before-login-landing/login');
    })
  }

  transformText(event) {
    const inputValue = event.target.value;
    const words = inputValue.split(' '); // Split the input by spaces
    const capitalizedWords = words.map(word => {
      return word.charAt(0).toUpperCase() + word.slice(1); // Capitalize each word
    });
    const capitalizedValue = capitalizedWords.join(' '); // Join the words back together with spaces
    this.registrationForm.get('name').setValue(capitalizedValue);
  }
}











