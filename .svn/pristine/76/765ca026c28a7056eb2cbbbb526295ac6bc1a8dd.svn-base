import { DatePipe, Time, formatDate } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { async } from '@angular/core/testing';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertController, InfiniteScrollCustomEvent, IonContent, LoadingController, Platform } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { format, parse } from 'date-fns';

@Component({
  selector: 'app-receptionist-shift',
  templateUrl: './receptionist-shift.component.html',
  styleUrls: ['./receptionist-shift.component.scss'],
})
export class ReceptionistShiftComponent implements OnInit {

  @ViewChild(IonContent, { static: true }) content: IonContent;

  selectedValue = '';
  selctedGH = ''
  selctCity = ''
  public isSubmitBtnClicked = false;
  public splitBtnClass = [
    { btnName: 'Add Shift', btnIcon: 'eye-outline', active: false },
    { btnName: 'Change Shift', btnIcon: 'eye-outline', active: false },
    { btnName: 'Close Shift', btnIcon: 'eye-outline', active: false },
    { btnName: 'Report', btnIcon: 'eye-outline', active: false },
  ]
  public addShiftForm: FormGroup
  public changeShiftForm: FormGroup
  public closeShiftForm: FormGroup
  getAllCityList = [];
  guestHouseList: any;
  headingName = 'Add Shift';
  currentDateTime: string;
  tomorrow: string | number | Date;
  tomorrowDateTime: string;
  currenTime: string;
  currentShift: string;
  buttonName: string = 'Add Shift';
  approveStatus: any;
  reportData: any = [];
  selectAdd: boolean = false;
  getCurrentShiftData: any;
  closeShiftSum = 0;
  addShiftSum = 0;
  number1: any;
  number2: any;
  shiftId: number;
  backToTop: boolean = false;
  pageSize = 5;
  pageNum = 1;

  obj = {
    cId: this.selctCity,
    gId: this.selctedGH,
    shiftId: '',
    createdBy: '',
    createdDate: '',
    pageSize: this.pageSize,
    pageNum: this.pageNum
  }

  constructor(private mdmService: MdmService,
    private alertController: AlertController,
    private loadingCtrl: LoadingController,
    public datepipe: DatePipe,
    private formFB: FormBuilder,
    private commonService: CommonService,
    private platform: Platform,
  ) {
    this.createAddShiftForm();
    this.createChangeShift();
    this.createCloseShift();
  }

  createAddShiftForm() {
    this.addShiftForm = this.formFB.group({
      openTime: ['', Validators.compose([Validators.required])],
      closeTime: [''],
      refundBal: [0, Validators.compose([Validators.required])],
      regularBal: [0, Validators.compose([Validators.required])],
      totalBal: [''],
      // checkShift: ['', Validators.compose([Validators.required])],
    });
  }

  createChangeShift() {
    this.changeShiftForm = this.formFB.group({
      openTime: ['', Validators.compose([Validators.required])],
      closeTime: [''],
      refundBal: [0, Validators.compose([Validators.required])],
      regularBal: [0, Validators.compose([Validators.required])],
      totalBal: [''],
      checkShift: ['', Validators.compose([Validators.required])],
    });
  }

  createCloseShift() {
    this.closeShiftForm = this.formFB.group({
      openTime: ['', Validators.compose([Validators.required])],
      closeTime: [''],
      refundBal: ['', Validators.compose([Validators.required])],
      regularBal: ['', Validators.compose([Validators.required])],
      totalBal: [''],
    });
  }

  minDate;
  maxDate;
  ngOnInit() {
    console.log('inside ngoninit');
    this.currentDateTime = this.datepipe.transform((new Date), 'dd-MM-yyyy h:mm');
    this.currenTime = formatDate(new Date(), 'yyyy-MM-ddThh:mm', 'en-US', '+0530');
    console.log('current time', this.currenTime);
    this.closeShiftForm.get('closeTime').patchValue(this.currenTime);
    this.tomorrow = new Date();
    this.datepipe.transform(this.tomorrow.setDate(new Date().getDate() + 1), 'dd-MM-yyyy');
    this.tomorrowDateTime = this.datepipe.transform(this.tomorrow, 'dd-MM-yyyy');
    const currentHour = new Date().getHours();
    if (currentHour >= 6 && currentHour < 14) {
      this.currentShift = '1';
      // this.addShiftForm.get('checkShift').setValue(this.currentShift);
    } else if (currentHour >= 14 && currentHour < 22) {
      this.currentShift = '2';
      // this.addShiftForm.get('checkShift').setValue(this.currentShift);
    } else {
      this.currentShift = '3';
      // this.addShiftForm.get('checkShift').setValue(this.currentShift);
    }
    // this.addShiftForm.controls.checkShift.setValue(this.currentShift);
    const today = (new Date()).toLocaleString("EN-CA").slice(0, 10); // get local current date
    this.minDate = today + "T00:00";
    this.maxDate = today + "T23:59";
    // console.log('today', today);
    // document.querySelectorAll('ion-input[type="datetime-local"]').forEach(el => {
    //   console.log(el);
    //   // el.min = today + "T00:00"; el.max = today + "T23:59";
    // })
  }

  ionViewWillEnter() {
    this.selctCity = '';
    this.selctedGH = '';
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.createAddShiftForm();
  }

  getCityList() {
    this.mdmService.getCityDataTableForMobile().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.getAllCityList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  async getGuestHouseList(event) {
    this.selctedGH = ''
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(
      res => {
        loading.dismiss();
        this.guestHouseList = res.data;
        if (this.guestHouseList.length == 1) {
          console.log("Inside IF Condition")
          console.log(res.data)
          this.selctedGH = res.data[0].id;
        }
      },
      err => {
        loading.dismiss()
      },
      () => {
        loading.dismiss()
      })
  }

  getGHId(event) {
    if (event.target.value !== this.commonService.getStoredDetails() && this.buttonName !== 'Report') {
      this.createAddShiftForm();
      this.createChangeShift();
      this.createCloseShift();
      this.selectAdd = false;
      this.buttonName = 'Add Shift';
      this.commonService.storeDetails(event.target.value);
    } else if (this.buttonName === 'Report') {
      this.reportData = [];
      this.obj.cId = this.selctCity;
      this.obj.gId = this.selctedGH;
      this.obj.pageNum = this.pageNum;
      this.obj.pageSize = this.pageSize;
      this.getReceptionistReport(this.obj);
    }
  }

  async getButtonName(btn) {
    if (btn.btnName == 'Add Shift' || btn.btnName == 'Change Shift' || btn.btnName == 'Close Shift') {
      if (this.selctCity === '') {
        const alert = await this.alertController.create({
          message: 'Please Select City.',
          buttons: ['OK'],
        });
        await alert.present();
      } else if (this.selctedGH === '') {
        const alert = await this.alertController.create({
          message: 'Please Select Guest house.',
          buttons: ['OK'],
        });
        await alert.present();
      } else {
        this.checkCurrentShift(btn.btnName);
      }
    } else if (btn.btnName == 'Report') {
      console.log("this is Report");
      this.buttonName = 'Report'
      // this.selctCity = '';
      // this.selctedGH = '';
      this.getReceptionistReport(this.obj);
      // const obj = {
      //   cId: this.selctCity,
      //   gId: this.selctedGH,
      //   shiftId: '',
      //   createdBy: '',
      //   createdDate: '',
      //   pageNum: 1,
      //   pageSize: 5
      // }

      // const loading = await this.loadingCtrl.create({
      //   message: 'Loading...'
      // });
      // loading.present();
      // this.mdmService.receptionistReport(this.obj).subscribe(res => {
      //   loading.dismiss();
      //   this.reportData = res.data;
      // })
    }
  }

  async getReceptionistReport(obj) {
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.receptionistReport(obj).subscribe
      (res => {
        loading.dismiss();
        this.reportData = this.reportData.concat(res.data);
        console.log('report data', this.reportData, 'report length', this.reportData.length);
      },
        err => {
          loading.dismiss()
        },
        () => {
          loading.dismiss()
        })
  }

  public async showAlert(msg) {
    const alert = await this.alertController.create({
      message: msg,
      buttons: ['OK'],
    });
    await alert.present();
  }

  checkCurrentShift(btnName) {
    const data = { 'cityId': this.selctCity, 'blockId': this.selctedGH };
    this.mdmService.checkCurrentShift(data).subscribe(res => {
      if (res) {
        console.log(res.data.message)
        if (res.data.message === 'Please Open a Shift') {
          this.buttonName = 'Add Shift'
          this.selectAdd = true;
          if (btnName !== 'Add Shift') {
            this.showAlert(res.data.message);
          }
        } else if (res.data.message === 'Close the previous shift') {
          this.buttonName = btnName;
          this.getCurrentShift();
          if (btnName === 'Add Shift') {
            this.buttonName = 'Close Shift';
            this.showAlert(res.data.message);
          }
        } else if (res.data.message === 'Redirect any operation') {
          this.buttonName = btnName;
          this.getCurrentShift();
          if (btnName === 'Add Shift') {
            this.showAlert('Shift already open');
            this.buttonName = 'Close Shift';
          }
        } else if (res.data.message === 'Shift already opened by another user') {
          this.showAlert(res.data.message);
          this.buttonName = 'Add Shift';
          this.selectAdd = false;
        }
      }
    })
  }

  getCurrentShift() {
    const obj = {
      cityId: this.selctCity,
      blockId: this.selctedGH
    }
    this.mdmService.getCurrentShift(obj).subscribe(res => {
      this.getCurrentShiftData = res.data;
      this.setUpdateShiftData(this.getCurrentShiftData);
      this.setCloseShiftData(this.getCurrentShiftData);
    })
  }

  async submitAddShift(formValue) {
    this.isSubmitBtnClicked = true;
    if (this.addShiftForm.get('openTime').value === '') {
      this.showAlert('Provide Opening Time');
    } else if (this.addShiftForm.get('refundBal').value === '') {
      this.showAlert('Provide Opening Balance Refund');
    } else if (this.addShiftForm.get('regularBal').value === '') {
      this.showAlert('Provide Opening Balance Regular');
      // } else if (this.addShiftForm.get('checkShift').value === '') {
    } else if (this.currentShift === '') {
      this.showAlert('Provide the Shift');
    }
    if (this.addShiftForm.valid) {
      const obj = {
        cityName: this.selctCity,
        guesthouseName: this.selctedGH,
        openBalRefund: this.addShiftForm.get('refundBal').value.toString(),
        openBalRegular: this.addShiftForm.get('regularBal').value.toString(),
        openBalTotal: JSON.stringify(this.addShiftForm.get('totalBal').value),
        openTime: this.datepipe.transform((this.addShiftForm.get('openTime').value), 'dd-MM-yyyy h:mm'),
        // shift: this.addShiftForm.get('checkShift').value
        shift: this.currentShift
      }
      const loading = await this.loadingCtrl.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.addNewShift(obj).subscribe(
        res => {
          this.showAlert(res.statusDesc);
          this.selectAdd = false;
          loading.dismiss();
        }, err => {
          this.commonService.showErrorToast('Something went wrong');
          loading.dismiss();
        },
        () => {
          loading.dismiss()
        })
    }
  }

  async submitUpdateShift(formValue) {
    this.isSubmitBtnClicked = true;
    if (this.changeShiftForm.get('openTime').value === '') {
      this.showAlert('Provide Opening Time');
    } else if (this.changeShiftForm.get('refundBal').value === '') {
      this.showAlert('Provide Opening Balance Refund');
    } else if (this.changeShiftForm.get('regularBal').value === '') {
      this.showAlert('Provide Opening Balance Regular');
    } else if (this.changeShiftForm.get('checkShift').value === '') {
      this.showAlert('Provide the Shift');
    }
    if (this.changeShiftForm.valid) {
      const obj = {
        shiftId: this.shiftId,
        createdTime: this.datepipe.transform((this.changeShiftForm.get('openTime').value), 'dd-MM-yyyy h:mm'),
        openBalRefund: this.changeShiftForm.get('refundBal').value,
        openBalRegular: this.changeShiftForm.get('regularBal').value,
        openBalTotal: this.changeShiftForm.get('totalBal').value
      }
      const loading = await this.loadingCtrl.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.updateShiftData(obj).subscribe(res => {
        this.showAlert(res.statusDesc);
        loading.dismiss();
      }, err => {
        loading.dismiss();
      },
        () => {
          loading.dismiss()
        })
    }
  }

  async submitCloseShift(formvalue) {
    const obj = {
      closeTime: this.datepipe.transform((this.closeShiftForm.get('closeTime').value), 'dd-MM-yyyy h:mm'),
      openBalRefund: this.closeShiftForm.get('refundBal').value,
      openBalRegular: this.closeShiftForm.get('regularBal').value,
      openBalTotal: this.closeShiftForm.get('totalBal').value,
      shiftId: this.getCurrentShiftData.shiftId
    }
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.closePreviousShift(obj).subscribe(res => {
      loading.dismiss();
      this.buttonName = 'Add Shift';
      this.showAlert(res.statusDesc);
      this.createAddShiftForm();
    },
      err => {
        loading.dismiss()
      },
      () => {
        loading.dismiss();
      })
  }

  setUpdateShiftData(data) {
    this.changeShiftForm.controls.openTime.setValue(this.setDateTime(data?.createdTime));
    console.log("Open Time", this.changeShiftForm.controls.openTime.value)
    this.changeShiftForm.controls.refundBal.setValue(data?.openBalRefund);
    this.changeShiftForm.controls.regularBal.setValue(data?.openBalRegular);
    this.changeShiftForm.controls.totalBal.setValue(data?.openBalTotal);
    this.changeShiftForm.controls.checkShift.setValue(data?.shiftNum);
    this.shiftId = data.shiftId;
  }

  setCloseShiftData(data) {
    this.closeShiftForm.controls.openTime.setValue(data?.createdTime);
    this.closeShiftForm.controls.refundBal.setValue(data?.openBalRefund);
    this.closeShiftForm.controls.regularBal.setValue(data?.openBalRegular);
    this.closeShiftForm.controls.totalBal.setValue(data?.openBalTotal);
    this.currentDateTime = this.datepipe.transform((new Date), 'dd-MM-yyyy h:mm');
    this.currenTime = formatDate(new Date(), 'yyyy-MM-ddThh:mm', 'en-US', '+0530');
    this.closeShiftForm.get('closeTime').patchValue(this.currenTime);
  }

  setDateTime(dateString: string): string {
    console.log(dateString);
  
    // Parse the date string into a Date object using date-fns
    const currentTime = parse(dateString, 'dd-MM-yyyy HH:mm', new Date());
  
    console.log(currentTime);
  
    if (!isNaN(currentTime.getTime())) {
      // The date is valid
      // Format the date as needed
      const formattedDate = format(currentTime, "yyyy-MM-dd'T'HH:mm");
      return formattedDate;
    } else {
      console.error('Invalid date string');
      return ''; // Handle the case of an invalid date string
    }
  }

  calculateClose1() {
    this.number1 = this.closeShiftForm.get('refundBal').value;
    this.number2 = this.closeShiftForm.get('regularBal').value;
    this.closeShiftSum = parseInt(this.number1) + parseInt(this.number2)
    this.closeShiftForm.controls.totalBal.setValue(this.closeShiftSum);
  }

  calculateTotalFund() {
    this.number1 = this.addShiftForm.get('refundBal').value;
    this.number2 = (this.addShiftForm.get('regularBal').value == '' ? '0' : this.addShiftForm.get('regularBal').value);
    this.addShiftSum = parseInt(this.number1) + parseInt(this.number2);
    this.addShiftForm.controls.totalBal.setValue(this.addShiftSum);
  }

  calculateTotalFundForUpdate() {
    this.number1 = this.changeShiftForm.get('refundBal').value;
    this.number2 = (this.changeShiftForm.get('regularBal').value == '' ? '0' : this.changeShiftForm.get('regularBal').value);
    this.addShiftSum = parseInt(this.number1) + parseInt(this.number2);
    this.changeShiftForm.controls.totalBal.setValue(this.addShiftSum);
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  handleRefresh(event) {
    setTimeout(async () => {
      this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      const loading = await this.loadingCtrl.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.receptionistReport(this.obj).subscribe(
        res => {
          loading.dismiss();
          this.reportData = res.data;
        },
        err => {
          loading.dismiss()
        },
        () => {
          loading.dismiss()
        })
      event.target.complete();
    }, 2000);
  }

  async onIonInfinite(ev) {
    setTimeout(() => {
      this.obj.pageSize = 5;
      console.log('inside onIonInfinite');
      this.obj.pageNum = this.obj.pageNum + 1;
      this.getReceptionistReport(this.obj);
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 2000);
  }

  gotToTop() {
    this.content.scrollToTop(1000);
  }
}
