import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AlertController, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';

@Component({
  selector: 'app-guest-feedback-report',
  templateUrl: './guest-feedback-report.component.html',
  styleUrls: ['./guest-feedback-report.component.scss'],
})
export class GuestFeedbackReportComponent implements OnInit {
  city_Id: any;
  gh_Id: any;
  fromDate: any;
  toDate
  category
  feedbackType
  guestfeedbackList: any;
  public isSubmitBtnClicked = false;
  public guestFeedbackForm: FormGroup;
  constructor(private route: ActivatedRoute,
    private loadingController: LoadingController,
    private mdmService: MdmService,
    private fb: FormBuilder,
    public datepipe: DatePipe,
    private alertController: AlertController,) {
    this.createGuestFeedback()
  }


  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.city_Id = params['cityId'];
      this.gh_Id = params['ghid'];
      console.log("citygh", this.city_Id, this.gh_Id);
    });
  }
  createGuestFeedback() {
    this.guestFeedbackForm = this.fb.group({
      fromDate: ['', Validators.required],
      toDate: ['', Validators.required],
      selectCategory: ['', Validators.required],
      feedback: ['', Validators.required],
    })
  }
  public async checkCityAndGh() {
    if (this.city_Id == '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    if (this.gh_Id == '') {
      const alert = await this.alertController.create({
        message: 'Please Select Guest house.',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    if (this.city_Id != '' && this.gh_Id != '') {
      return Promise.resolve(true)
    }
  }

  async getGuestFeedback(formValue) {
    this.isSubmitBtnClicked = true;
    console.log("form is", formValue)
    const obj = {
      cId: this.city_Id,
      gId: this.gh_Id,
      formDate: this.datepipe.transform(formValue.fromDate, 'dd-MM-yyyy'),
      todate: this.datepipe.transform(formValue.toDate, 'dd-MM-yyyy'),
      category: formValue.selectCategory,
      feedback: formValue.feedback
    }
    console.log(obj)
    if (this.guestFeedbackForm.valid) {
      const loading = await this.loadingController.create({
        message: 'Loading...'
      });
      loading.present();
      this.mdmService.guestFeedbackDetails(obj).subscribe(res => {
        loading.dismiss();
        this.guestfeedbackList = res.data;
      })
    }

  }

}
