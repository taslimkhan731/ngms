import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, NavigationEnd, NavigationStart, Router } from '@angular/router';
import { LoadingController, ModalController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { mobileNumberValidator } from 'src/app/validators/custom-validators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-viewedit-conference',
  templateUrl: './viewedit-conference.component.html',
  styleUrls: ['./viewedit-conference.component.scss'],
})
export class VieweditConferenceComponent implements OnInit {

  public createConferenceForm: FormGroup;
  public pathName: any;
  public nextDayDt
  public nextDay = new Date();
  public isSubmitBtnClicked = false;
  public bhawanId: number;
  public bhawanName;
  public timePeriodData = []
  public conferenceAmount;
  public showStartEndTime = false;
  public bookingData;
  public timePeriod;
  public hallType;
  public confType;
  isEdit: any;
  dropdown: any;

  constructor(private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private commonService: CommonService,
    private mdmService: MdmService,
    private loadingController: LoadingController,
    private modalcontroller: ModalController,
    private location: Location) {
    this.createConferenceInstance();
  }

  public createConferenceInstance() {
    this.createConferenceForm = this.fb.group({
      blockId: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      mobileNo: ['', Validators.compose([Validators.required, mobileNumberValidator])],
      confCatId: ['', Validators.required],
      confUseId: ['', Validators.required],
      fromDate: ['', Validators.required],
      strtTime: [''],
      endTime: [''],
      amount: ['', Validators.required],
      capacity: [''],
      advanceAmount: [''],
      categoryType: ['']
    })
  }

  ngOnInit() {
    this.nextDay.setDate(this.nextDay.getDate() + 1)
    this.nextDayDt = this.nextDay.toISOString().slice(0, 10)

    this.bookingData = localStorage.getItem("bookingDetails")
    this.bookingData = JSON.parse(this.bookingData)

    this.router.events.subscribe((e) => {
      if (e instanceof NavigationStart) {
        console.log("Inside NavigationStart")
        this.bookingData = localStorage.getItem("bookingDetails")
        this.bookingData = JSON.parse(this.bookingData)
        this.isSubmitBtnClicked = false
      }
    });
    this.bhawanId = this.bookingData.blockId;
    console.log(this.bookingData)

    console.log(this.bhawanId)
    this.route?.url?.subscribe(params => {
      this.editViewBooking(params);
    });
  }

  public editViewBooking(param) {
    if (param.length != 0) {
      this.pathName = param[0].path;
      if (this.pathName == 'editConference') {
        console.log('inside edit');
        this.createConferenceForm.get('confCatId').disable();
        this.createConferenceForm.get('confUseId').disable()
        this.createConferenceForm.get('fromDate').disable()
        this.createConferenceForm.get('strtTime').disable();
        this.createConferenceForm.get('endTime').disable();
        this.createConferenceForm.get('amount').disable();
        this.createConferenceForm.get('advanceAmount').disable();
        this.createConferenceForm.get('capacity').disable();
        this.setConferenceData(this.bookingData);
        // this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
      }
      if (this.pathName == 'viewConference') {
        this.isEdit = true;
        this.dropdown = null;
        console.log('inside view');
        console.log(this.bookingData)
        this.setConferenceData(this.bookingData);
        // this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
        this.createConferenceForm.disable();
      }
    }
  }

  public async getConfrenceTypeDetails() {
    console.log("Inside getConfrenceTypeDetails")
    let obj = {
      "gId": this.bhawanId
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getConfrenceTypeDetails(obj).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          // this.createConferenceForm.get('confCatId').setValue(this.hallType);
          this.confType = res.data.data;
          console.log(this.confType)
        }
        else {
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast(err.ststusDesc)
      })
  }

  public async getConfrenceTimePeriodDetailsForMobile(event) {
    this.createConferenceForm.get('confUseId').reset();
    console.log(event)
    const loader = await this.loadingController.create();
    let obj = {
      "gId": this.bhawanId,
      "conCatId": event
    }
    this.mdmService.getConfrenceTimePeriodDetailsForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.timePeriodData = res.data.data;
          // this.createConferenceForm.get('confUseId').setValue(this.timePeriod);

          loader.dismiss();
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      },
      ()=>{
        loader.dismiss()
      }
    );
  }
  public async getConfrenceAmountForMobile(event) {
    console.log(event.target.value)
    const loader = await this.loadingController.create();
    let obj = {
      "conCatId": this.createConferenceForm.get('confCatId').value,
      "conUseId": event.target.value
    }
    this.mdmService.getConfrenceAmountForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.conferenceAmount = res.data.data;
          this.createConferenceForm.get('amount').setValue(res.data.data)
          if (event.target.value == 4) {
            this.showStartEndTime = true
          } else {
            this.showStartEndTime = false;
            this.createConferenceForm.get('strtTime').reset();
            this.createConferenceForm.get('endTime').reset();
          }
          loader.dismiss();
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      },
      ()=>{
        loader.dismiss()
      }
    );
  }

  public addTwoHour2EndTime(event) {

    let selectedDate = new Date();
    console.log("selectedDate", selectedDate)
    let selectedTime = event.target.value
    console.log("selectedTime", selectedTime)

    // if(selectedDate == ''){
    //   this.commonService.showErrorToast("Please Select from date first !")
    //   this.createConferenceForm.get('strtTime').reset()
    //   return;
    // }

    let finalDateTime = selectedDate + ":" + selectedTime + ":00Z";
    console.log("finalDateTime", finalDateTime)

    const givenTime = new Date(finalDateTime);
    const forwardTime = new Date(givenTime.getTime() + (2 * 60 * 60 * 1000));
    const forwardTimeStr = forwardTime.toISOString().slice(11, 16);
    console.log("time", forwardTimeStr);

    this.createConferenceForm.get('endTime').setValue(forwardTimeStr);
  }

  public validateAdAmount() {
    let totalAmount = this.createConferenceForm.get('amount').value;
    let enteredAmount = this.createConferenceForm.get('advanceAmount').value
    // let enteredAmount = event.target.value
    console.log("Inside validateAdAmount")
    console.log(enteredAmount, totalAmount)
    console.log(enteredAmount > totalAmount)
    if (enteredAmount > totalAmount) {
      console.log("Inside validateAdAmount Comp")
      return true;
    }
  }

  public changeDateFormat(date) {
    let objectDate = new Date(date);
    let day = String(objectDate.getDate()).padStart(2, '0');
    let month = String(objectDate.getMonth() + 1).padStart(2, '0');
    let year = objectDate.getFullYear();
    let formatDate = day + "-" + month + "-" + year;
    return formatDate;
  }

  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  public setConferenceData(data) {
    if (data != undefined) {
      console.log(data?.charge)
      this.hallType = data?.confrenceId
      this.timePeriod = data?.confreqnceUseId
      console.log("timePeriod", this.timePeriod)

      if(this.timePeriod == '4') {
        this.showStartEndTime = true;
      } else {
        this.showStartEndTime = false;
      }

      this.createConferenceForm.get('name').setValue(data?.name);
      this.createConferenceForm.get('address').setValue(data?.address);
      this.createConferenceForm.get('mobileNo').setValue(data?.mobile);
      // this.getConfrenceTypeDetails()

      this.createConferenceForm.get('confCatId').setValue(data?.confrenceDesc);
      this.createConferenceForm.get('confUseId').setValue(data?.confreqnceUseDesc);
      // this.getConfrenceTimePeriodDetailsForMobile(this.hallType)

      this.createConferenceForm.get('fromDate').setValue(data?.fromDate ? data?.fromDate.split(' ')[0] : '');

      // this.createConferenceForm.get('fromDate').setValue(data?.fromDate);
      this.createConferenceForm.get('strtTime').setValue(data?.fromDate.slice(11, 16));
      this.createConferenceForm.get('endTime').setValue(data?.toDate.slice(11, 16));
      this.createConferenceForm.get('amount').setValue(data?.charge);
      this.createConferenceForm.get('capacity').setValue(data?.capacity);
      this.createConferenceForm.get('advanceAmount').setValue(data?.advanceAmount);
    }
  }

  public async submitConferenceData() {
    this.isSubmitBtnClicked = true;
    if (this.createConferenceForm.invalid) {
      this.commonService.showErrorToast("Please Fill up all the required details!!")
      return;
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });

    let obj = {
      "reqId": this.bookingData.bookingId,
      "mobileNo": (this.createConferenceForm.get('mobileNo').value).toString(),
      "address": this.createConferenceForm.get('address').value,
      "capacity": this.createConferenceForm.get('capacity').value ? (this.createConferenceForm.get('capacity').value).toString() : '',
      "name": this.createConferenceForm.get('name').value
    }
    loading.present()
    this.mdmService.editBillingDetailsForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          // this.commonService.storeDetails(res.data)
          this.createConferenceInstance();
          loading.dismiss();
          this.commonService.showSuccessToast("Conference Data Edited Successfully")
          this.router.navigate(['/after-login/pages/guest/view-booking/conference'])
        } else {
          loading.dismiss()
          this.commonService.showErrorToast(res.statusDesc)
        }
      }, err => {
        this.commonService.showErrorToast("Something Went Wrong.");
        loading.dismiss();
      },
      ()=>{
        loading.dismiss()
      }
    )

    // console.log(data)
  }

  ionViewDidLeave() {
    // this.allBookingData = []
    this.createConferenceInstance()
    // this.createBookingDetails()
    localStorage.removeItem("bookingDetails")
  }
}
