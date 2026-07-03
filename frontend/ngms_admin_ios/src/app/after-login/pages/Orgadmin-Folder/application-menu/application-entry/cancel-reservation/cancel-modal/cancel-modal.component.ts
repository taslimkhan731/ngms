import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { differenceInDays, format, parse, parseISO } from 'date-fns';

@Component({
  selector: 'app-cancel-modal',
  templateUrl: './cancel-modal.component.html',
  styleUrls: ['./cancel-modal.component.scss'],
})
export class CancelModalComponent implements OnInit {

  @ViewChild('dateInput', { static: false }) dateInput: any;
  @ViewChild('dateInput2', { static: false }) dateInput2: any;
  public bookingId;
  public name;
  public mobileNo;
  public cancelDate;
  public modalData;
  public cancelType;
  public cancelRequestData;
  public canFromMax;
  public canFromMin;
  public CancelFrmDt;
  public CancelToDt;
  public noOfDay;
  public currDate;
  public partianCanMax;
  public partianCanMin;

  constructor(
    private mdCtrl: ModalController,
    private navParams: NavParams,
    private alertController: AlertController,
    private commonService: CommonService,
    private mdmService: MdmService,
    private datePipe: DatePipe,
    private loadingController: LoadingController

  ) { }

  ngOnInit() {

  }

  ionViewWillEnter() {
    this.modalData = this.navParams.data;
    this.cancelType = this.modalData.type;
    console.log(this.modalData)

    if (this.cancelType == 'cancel') {
      this.getCancelReqInfo()
    }
    if (this.cancelType == 'partialCan') {
      this.getPartialCanReqInfo()
    }
  }
  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  public calculateDateDiffForPartialCan() {
    console.log(this.currDate)
    if (this.currDate !== null && this.currDate) {
      // let frmDt = new Date(this.currDate);
      // let toDt = new Date(this.CancelToDt);
      // let days = Math.floor((Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
      // this.noOfDay = days;

      const parsedCancelFrmDt = parse(this.currDate, 'dd-MM-yyyy', new Date());
      const parsedCancelToDt = parse(this.CancelToDt, 'dd-MM-yyyy', new Date());
      const daysDifference = differenceInDays(parsedCancelToDt, parsedCancelFrmDt);

      this.noOfDay = daysDifference;
    }

  }
  public calculateDateDiffForCan() {
    if (this.CancelFrmDt !== null && this.CancelFrmDt) {
      // let frmDt = new Date(this.currDate);
      // let toDt = new Date(this.CancelToDt);
      // let days = Math.floor((Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
      // this.noOfDay = days;
      
      const parsedCancelFrmDt = parse(this.datePipe.transform(this.CancelFrmDt, 'dd-MM-yyyy'), 'dd-MM-yyyy', new Date());
      const parsedCancelToDt = parse(this.CancelToDt, 'dd-MM-yyyy', new Date());
    console.log(this.CancelFrmDt,this.CancelToDt)
    console.log(parsedCancelFrmDt,parsedCancelToDt)

      const daysDifference = differenceInDays(parsedCancelToDt, parsedCancelFrmDt);

      this.noOfDay = daysDifference;
    }

  }

  // public changeDateFormat(date) {
  //   let objectDate = new Date(date);
  //   let day = String(objectDate.getDate()).padStart(2, '0');
  //   let month = String(objectDate.getMonth() + 1).padStart(2, '0');
  //   let year = objectDate.getFullYear();
  //   let formatDate = day + "-" + month + "-" + year;
  //   return formatDate;
  // }

  public openDatepicker(name) {
    this[name].nativeElement.click();
  }

  public changeDateFormat(date, name) {
    this[name] = this.formatTime(date)
    // console.log("this.partialCancelFrmDt", this.partialCancelFrmDt)
  }

  public formatTime(dateValue: string): string {
    const parsedDate = parseISO(dateValue);
    return format(parsedDate, 'dd-MM-yyyy');
  }

  public getCancelReqInfo() {
    this.cancelRequestData = this.modalData.data;
    this.bookingId = this.cancelRequestData.bookingId;
    this.name = this.cancelRequestData.name;
    this.mobileNo = this.cancelRequestData.mobile;

    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    date.setDate(date.getDate())
    console.log(date)
    this.currDate = date.toISOString().slice(0, 10);
    // this.currDate = this.changeDateFormat(this.currDate)
    console.log(this.currDate)
    this.CancelFrmDt = this.changeDateFormatyymmdd(this.cancelRequestData.fromDate.split(' ')[0])
    this.CancelToDt = this.changeDateFormatyymmdd(this.cancelRequestData.toDate.split(' ')[0])
    let CanFrom = new Date(this.CancelToDt);
    CanFrom.setDate(CanFrom.getDate() - 1)
    this.canFromMax = CanFrom.toISOString().slice(0, 10)
    console.log(this.canFromMax, this.CancelFrmDt)
    this.canFromMin = this.CancelFrmDt;
    this.currDate = this.formatTime(this.currDate);
    this.CancelToDt = this.formatTime(this.CancelToDt);
    this.calculateDateDiffForCan();

  }

  public getPartialCanReqInfo() {
    this.cancelRequestData = this.modalData.data;
    this.bookingId = this.cancelRequestData.bookingId;
    this.name = this.cancelRequestData.name;
    this.mobileNo = this.cancelRequestData.mobile;

    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    date.setDate(date.getDate())
    this.currDate = date.toISOString().slice(0, 10);
    let frDt = this.changeDateFormatyymmdd(this.cancelRequestData.fromDate.split(' ')[0])
    this.CancelToDt = this.changeDateFormatyymmdd(this.cancelRequestData.toDate.split(' ')[0])

    let partialFrom = new Date(this.CancelToDt);
    partialFrom.setDate(partialFrom.getDate() - 1)
    this.partianCanMax = partialFrom.toISOString().slice(0, 10)
    console.log(this.partianCanMax)

    let partialTo = new Date(frDt);
    partialTo.setDate(partialTo.getDate() + 1)
    this.partianCanMin = partialTo.toISOString().slice(0, 10)
    console.log(this.partianCanMin)
    this.currDate = this.formatTime(this.partianCanMin);
    this.CancelToDt = this.formatTime(this.CancelToDt);

    this.calculateDateDiffForPartialCan();

  }


  goBack() {
    this.mdCtrl.dismiss();
  }

  public async partialCanBooking() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    let obj = {
      bookingId: this.bookingId,
      fromDate: this.currDate + " " + "09:00",
      toDate: this.cancelRequestData.toDate,
    }
    console.log(obj)

    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: "Are you sure to cancel?",
      buttons: [
        {
          text: 'Yes,cancel it',
          cssClass: 'alertButton',
          handler: () => {
            // localStorage.clear();
            console.log(obj)
            loading.present();
            this.mdmService.partialCancelForMobile(obj).subscribe(
              res => {
                if (res.status == 1) {
                  loading.dismiss()
                  this.commonService.showSuccessToast(res.statusDesc);
                  this.mdCtrl.dismiss(res.status);
                }
                else {
                  loading.dismiss()
                  this.commonService.showErrorToast(res.statusDesc);
                  this.mdCtrl.dismiss();
                }
              },
              err=> {
                loading.dismiss();
                this.commonService.showErrorToast("Something Went Wrong.");
              },
              ()=> {
                loading.dismiss()
              })
          },
        },
        {
          text: 'Close',
          handler: () => {
            console.log('Confirm No');
          },
        },

      ],
    });
    await alert.present();
  }


  public async cancelBooking() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    let cancelMessage = "Cancellation after from date will result in non occupancy charges, Are you sure to cancel!";
    let twentyFourCancel;

    if (this.currDate <= this.CancelFrmDt) {
      twentyFourCancel = 1;
      cancelMessage = "Are you sure to cancel!";
    } else {
      twentyFourCancel = 0;
    }

    console.log(this.cancelRequestData)
    let obj = {
      bookingId: this.bookingId,
      fromDate: this.currDate + " " + "09:00",
      toDate: this.cancelRequestData.toDate,
      // toDate: this.datePipe.transform(this.cancelRequestData.toDate,'dd-MM-yyyy') + " " + "08:00",
      twentyFourCancel: twentyFourCancel
    }
    console.log(obj)
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: cancelMessage,
      buttons: [
        {
          text: 'Yes,cancel it',
          cssClass: 'alertButton',
          handler: () => {
            // localStorage.clear();
            console.log(obj)
            loader.present();
            this.mdmService.cancelRequest1ForMobile(obj).subscribe(
              res => {
                if (res.status == 1) {
                  loader.dismiss()
                  this.commonService.showSuccessToast(res.statusDesc);
                  this.mdCtrl.dismiss(res.status);
                }
                else {
                  loader.dismiss()
                  this.commonService.showErrorToast(res.statusDesc);
                  this.mdCtrl.dismiss();
                }
              }, err => {
                loader.dismiss();
                this.commonService.showErrorToast("Something Went Wrong.")
              },
              ()=> {
                loader.dismiss()
              })
          },
        },
        {
          text: 'Close',
          handler: () => {
            console.log('Confirm No');
          },
        },

      ],
    });
    await alert.present();

  }

}
