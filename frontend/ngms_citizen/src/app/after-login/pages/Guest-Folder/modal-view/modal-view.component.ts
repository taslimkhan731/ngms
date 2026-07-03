import { DatePipe } from '@angular/common';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';
import { NavParams, ModalController, AlertController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import * as moment from 'moment';
import * as numberToWords from 'number-to-words';
import { differenceInDays, format, parse, parseISO } from 'date-fns';
import { AppConstants } from 'src/app/app.constants';
import { Browser } from '@capacitor/browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modal-view',
  templateUrl: './modal-view.component.html',
  styleUrls: ['./modal-view.component.scss'],
})
export class ModalViewComponent implements OnInit {
  modalData: any;
  @ViewChild('dateInput', { static: false }) dateInput: any;
  @ViewChild('extendDt', { static: false }) extendDt: any;
  date;
  ToDate;
  printArray = [
    { key: 'Booking Id', value: 'G18' },
    { key: 'Booking Date', value: '2023-02-21 11:50:42' },
    { key: 'City Name', value: 'Delhi' },
    { key: 'From Date', value: '2023-01-18 09:00:00' },
    { key: 'To Date', value: '2023-01-19 08:00:00' },
    { key: 'No of Rooms', value: '1' },
  ]
  questionArray = [
    { key: 'Ease of Check In and Check out', value: 'checkin_checkout' },
    { key: 'Up Keep of the Room', value: 'keepup_room' },
    { key: 'Cleanliness and Hygiene', value: 'cleanliness_hygiene' },
    { key: 'Quality Of Food', value: 'food_quality' },
    { key: 'Conduct of staff', value: 'staff_conduct' },
    { key: 'Overall Feedback', value: 'overall_feedback' }
  ]
  img = 'assets/afterlogin/sad.png'
  feedbackForm: FormGroup;
  fields: string[] = [];
  model = {
    checkin_checkout: '',
    keepup_room: '',
    cleanliness_hygiene: '',
    food_quality: '',
    staff_conduct: '',
    overall_feedback: '',
    comment: ''
  };
  formGroupFields: {};
  bookingDetails: any;
  fromDate: Date;
  toDate = '';
  dateDiff: number = 0;
  reqData: any;
  billData = [];
  charges: any;
  total
  ghName: any;
  ghCity: any;
  bookingId: any;
  bookingDate: any;
  cityName: any;
  fromdt: any;
  toDt: any;
  noRooms: any;
  noBeds: any;
  mobileNO: any;
  purpose: any;
  caegory: any;
  designation: any;
  office: any;
  conveyance: any;
  pdf: any;
  fileName = 'testdownload.pdf';
  link: HTMLAnchorElement;
  fileUrl: any;
  content: string;
  paymentData: any;
  ifNullAdd = true;
  FromDt: string;
  res: any;
  refund: number;
  result: any;
  billID: any;
  guestName: any;
  amount: any;
  modeOfPayment: any;
  billDate: any;
  receiptDate: any;
  showInFinal = false;
  amountToPay;
  amountToPayText;
  headerForBill = "Bill Details For Full Payment"
  recieptData;
  confReceiptData = [];
  finalRecieptData;
  isFinalReciept = false;
  nocDetails;
  public show = false;
  accessCode: any;
  encRequest: any;
  // billDate;
  public totalGuest = 0;
  public partialCancelFrmDt;
  public partialCancelToDt;
  public noOfDays;
  public partialFromMax;
  public partialFromMin;
  public totalAmount;
  public totalAmountText;
  public confBillId;
  public paymentDetails;
  public currDate = new Date();
  public fontBase64;
  public clicked = false;

  public cashInAdvance = "0";
  public posInAdvance = "0";
  public onlineInAdvance = "0";
  public fcashInAdvance = "0";
  public fposInAdvance = "0";
  public fonlineInAdvance = "0";

  public CancelFrmDt;
  public CancelToDt;
  public noOfDayC;
  public cancelModalName;
  public twentyFourCancel: number;
  public extendMinDate;
  public totalAdvance;
  public totalFinal;
  public style;
  constructor(private navParams: NavParams,
    private modalctrl: ModalController,
    private commonService: CommonService,
    private alertController: AlertController,
    private mdmService: MdmService,
    public sanitizer: DomSanitizer,
    private datePipe: DatePipe,
    private router: Router,
    public http: HttpClient,
    private pdfGenerator: PDFGenerator) {
    this.createFeedbackForm();
  }

  ngOnInit() {
    // this.convertFontToBase64();
    this.modalData = this.navParams.data;
    this.bookingDetails = this.modalData.bookingDetails;
    this.fromDate = this.bookingDetails.fromDate;
    console.log("from date", this.modalData);
    // this.FromDt = this.datePipe.transform(this.fromDate, 'dd-MM-yyyy');
    if (this.modalData.button === 'Download') {
      this.getRequest();
      // this.download();
    }
    if (this.modalData.button === 'bill') {
      this.downloadBill();
    }
    if (this.modalData.button === 'Payment Receipt') {
      this.paymentReceiptData();
    }
    if (this.modalData.button === 'Bill View') {
      this.billViewData(this.bookingDetails);
    }
    if (this.modalData.button === 'Receipt View') {
      this.RecieptViewData(this.bookingDetails);
      console.log(this.bookingDetails)
    }
    if (this.modalData.button === 'NOC') {
      this.getNoc(this.bookingDetails);
    }
    if (this.modalData.button === 'DUES') {
      this.getDuesPaymentReceiptForMobile(this.bookingDetails);
    }
    if (this.modalData.button === 'Cancel') {
      if (this.bookingDetails.partialCancel > 0) {
        this.cancelModalName = "Partial Cancel";
        console.log(this.cancelModalName)
        this.partialCancelInfo(this.bookingDetails);
      }
      if (this.bookingDetails.twentyFourCancel > 0) {
        this.cancelModalName = "Cancel Reservation";
        console.log(this.cancelModalName)
        this.cancelReservationInfo(this.bookingDetails);
      }
    }
    if (this.modalData.button === 'Extend') {
      this.getExtendInfo(this.bookingDetails);
    }
  }

  ionViewWillEnter() {
    this.show = false
  }

  convertFontToBase64() {

    const fontPath = 'assets/font/Lato/Lato-Light.ttf'; // Update the path
    this.http.get(fontPath, { responseType: 'blob' }).subscribe(
      (blob: Blob) => {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.fontBase64 = e.target.result;
          this.style = `<style>
            @font-face {
            font-family: 'lato';
            src: url(data:font/opentype;base64,${this.fontBase64}) format('truetype');
            // font-weight: 600;
              }
              body {
                font-family: 'lato', sans-serif;
                // font-weight: bolder !important;
              }
            </style>`
        };
        reader.readAsDataURL(blob);
      },
      (error) => {
        console.error('Error loading font:', error);
      }
    );
  }


  public getExtendInfo(data) {
    this.FromDt = this.formatTime(this.changeDateFormatyymmdd(data.toDate.split(' ')[0]))
    // this.toDate = this.changeDateFormatyymmdd(data.toDate.split(' ')[0])

    console.log(this.FromDt)
    let extendDate = this.changeDateFormatyymmdd(data.toDate.split(' ')[0])
    let extendDtTypeDate = new Date(extendDate);
    extendDtTypeDate.setDate(extendDtTypeDate.getDate() + 1)
    this.extendMinDate = extendDtTypeDate.toISOString().slice(0, 10)
    console.log(this.extendMinDate)
  }

  public cancelReservationInfo(bookingDetails) {
    console.log("Inside Cancel Reservation", bookingDetails)
    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;
    date.setDate(date.getDate())
    let currDate = date.toISOString().slice(0, 10) + " " + currentTime
    console.log("currDate", currDate)
    let checkInDate = this.changeDateFormatyymmdd(bookingDetails.fromDate.split(' ')[0])
    let checkInTime = bookingDetails.fromDate.slice(11, 16)
    checkInDate = checkInDate + " " + checkInTime
    console.log("checkInDate", checkInDate)

    let cancelFromDate = date.toISOString().slice(0, 10) + ' ' + '08:00'
    console.log("cancelFromDate", cancelFromDate)

    // let cancelFromDate = currDate + ' ' +'08:00'

    if (currDate < checkInDate) {
      console.log("currDate < checkInDate", currDate < checkInDate)
      cancelFromDate = checkInDate;
    } else {

      if (currDate > cancelFromDate) {
        console.log("currDate > checkInDate", currDate > checkInDate)

        let tDate = new Date()
        tDate.setDate(tDate.getDate() + 1)
        let cDate = tDate.toISOString().slice(0, 10)
        cancelFromDate = cDate;
      }
    }
    this.CancelFrmDt = this.formatTime(cancelFromDate.slice(0, 10));
    // console.log(this.changeDateFormatyymmdd(this.CancelFrmDt.slice(0,10).split(' ')[0]))
    console.log(this.CancelFrmDt.slice(0, 10))

    this.CancelToDt = this.formatTime(this.changeDateFormatyymmdd(bookingDetails.toDate.split(' ')[0]))
    console.log(this.CancelToDt)
    // console.log(bookingDetails.toDate)

    this.calculateDateDiffForFullCan();

  }

  private formatTime(dateValue: string): string {
    const parsedDate = parseISO(dateValue);
    return format(parsedDate, 'dd-MM-yyyy');
  }

  public calculateDateDiffForFullCan() {
    if (this.CancelFrmDt !== null && this.CancelToDt) {
      const parsedCancelFrmDt = parse(this.CancelFrmDt, 'dd-MM-yyyy', new Date());
      const parsedCancelToDt = parse(this.CancelToDt, 'dd-MM-yyyy', new Date());
      const daysDifference = differenceInDays(parsedCancelToDt, parsedCancelFrmDt);

      console.log('Number of days difference:', daysDifference);
      this.noOfDayC = daysDifference;
    }
  }

  public changeDateFormat(date, name) {
    this[name] = this.formatTime(date)
    console.log("this.partialCancelFrmDt", this.partialCancelFrmDt)
  }

  public partialCancelInfo(bookingDetails) {
    console.info("Inside Partial Cancel", bookingDetails)
    this.partialCancelFrmDt = this.changeDateFormatyymmdd(bookingDetails.fromDate.split(' ')[0])
    this.partialCancelToDt = this.changeDateFormatyymmdd(bookingDetails.toDate.split(' ')[0])
    let partialFrom = new Date(this.partialCancelToDt);
    partialFrom.setDate(partialFrom.getDate() - 1)
    this.partialFromMax = partialFrom.toISOString().slice(0, 10)
    console.log(this.partialFromMax)

    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();

    let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;

    console.log(currentTime)

    if (currentTime <= '08:00') {
      date.setDate(date.getDate())
      let currDate = date.toISOString().slice(0, 10)
      this.partialFromMin = currDate;
      this.partialCancelFrmDt = currDate;
    } else {
      date.setDate(date.getDate() + 1)
      let currDate = date.toISOString().slice(0, 10)
      this.partialFromMin = currDate;
      this.partialCancelFrmDt = currDate;
    }

    this.partialCancelFrmDt = this.formatTime(this.partialCancelFrmDt);
    this.partialCancelToDt = this.formatTime(this.partialCancelToDt);

    this.calculateDateDiffForPartialCan();
  }

  public calculateDateDiffForPartialCan() {
    if (this.partialCancelFrmDt !== null && this.partialCancelToDt) {
      // let frmDt = new Date(this.partialCancelFrmDt);
      // let toDt = new Date(this.partialCancelToDt);
      // let days = Math.floor((Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
      const parsedCancelFrmDt = parse(this.partialCancelFrmDt, 'dd-MM-yyyy', new Date());
      const parsedCancelToDt = parse(this.partialCancelToDt, 'dd-MM-yyyy', new Date());
      const daysDifference = differenceInDays(parsedCancelToDt, parsedCancelFrmDt);

      this.noOfDays = daysDifference;
    }
  }

  private formatTimeComponent(component: number): string {
    return component < 10 ? `0${component}` : component.toString();
  }

  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }
  goBack() {
    this.modalctrl.dismiss();
  }
  detectChange(data, key) {
    this.feedbackForm.get(key).setValue(data.target.value);
  }
  createFeedbackForm() {
    const formGroupFields = this.getFormControlsFields();
    this.feedbackForm = new FormGroup(formGroupFields);
  }
  getFormControlsFields() {
    this.formGroupFields = {};
    for (const field of Object.keys(this.model)) {
      this.formGroupFields[field] = new FormControl("");
      this.fields.push(field);
    }
    return this.formGroupFields;
  }
  submitFeedback(formvalue) {
    console.log("feedback", formvalue)
    if ((formvalue.checkin_checkout == '' || formvalue.checkin_checkout == null) || (formvalue.cleanliness_hygiene == '' || formvalue.cleanliness_hygiene == null) || (formvalue.staff_conduct == '' || formvalue.staff_conduct == null) || (formvalue.food_quality == '' || formvalue.food_quality == null) || (formvalue.keepup_room == '' || formvalue.keepup_room == null) || (formvalue.overall_feedback == '' || formvalue.overall_feedback == null)) {
      this.commonService.showErrorToast(" Please give all the feedback.");
      return;
    }
    const obj = {
      checkincheckout: formvalue.checkin_checkout.toString(),
      clean: formvalue.cleanliness_hygiene.toString(),
      conduct: formvalue.staff_conduct.toString(),
      feedbacksms: formvalue.comment,
      food: formvalue.food_quality.toString(),
      housekeeping: formvalue.keepup_room.toString(),
      mobile: this.bookingDetails.mobile,
      name: this.bookingDetails.name.toString(),
      overall: formvalue.overall_feedback.toString(),
      requestId: this.bookingDetails.bookingId,
    }
    console.log(obj)
    this.mdmService.giveFeedback(obj).subscribe(res => {
      if (res.status == 1) {
        this.commonService.showSuccessToast(res.statusDesc);
        this.feedbackForm.reset();
        this.modalctrl.dismiss()
        // this.router.navigate(['/after-login/pages/guest/view-booking/guest-house']);
      }
      else {
        this.commonService.showErrorToast(res.statusDesc);
        this.feedbackForm.reset();
        this.modalctrl.dismiss()

        // this.router.navigate(['/after-login/pages/guest/view-booking/guest-house']);
      }
    })
  }
  paymentReceiptData() {
    this.mdmService.getPaymentReceiptData(this.bookingDetails.bookingId).subscribe(res => {
      this.paymentData = res.data;
      this.billID = this.paymentData.receiptId;
      this.billDate = this.paymentData.billDate;
      this.guestName = this.paymentData.guestName;
      this.amount = this.paymentData.amountPaid;
      this.paymentDetails = this.paymentData.receiptModel;
      this.totalAmount = parseInt(this.paymentData.advancedAmount) + parseInt(this.paymentData.amountPaid)
      console.log("Payment Details", this.paymentDetails)
      this.modeOfPayment = this.paymentData.receiptModel.modeOfPayment;
      console.log("payment data is", this.paymentData)
    })
  }
  paymentPDF() {
    console.log("Inside Payment Reciept Download")
    this.content = document.getElementById('PaymentReceipt').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }
  duesPDF() {
    console.log("Inside Payment Reciept Download")
    this.content = document.getElementById('duesReceipt').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }

  paymentReceiptNOC() {
    console.log("Inside Payment Reciept Download")
    this.content = document.getElementById('nocReceipt').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }
  downloadBill() {
    const params = new HttpParams()
      .set('bookingId', this.bookingDetails.bookingId)
      .set('billId', this.bookingDetails.billId);
    this.mdmService.getBillPDF(params).subscribe(res => {
      this.result = res.data;
      this.billData = res.data.guestDetails;
      this.totalGuest = this.billData.length + this.billData[0].count;
      this.ghName = res.data.guestDetails[0].ghName;
      this.ghCity = res.data.guestDetails[0].cityName;
      this.charges = res.data.charges;
      this.total = res.data.total;
      this.refund = this.total - this.result.advanceAmount;
      console.log("bill data", this.ghName, this.refund);
    })
  }
  downloadPrintBill() {
    // this.mdmService.getBillPDFPrint(this.bookingDetails.bookingId, this.bookingDetails.billId).subscribe(res => {
    //   const blob = new Blob([res], { type: 'application/pdf' });
    //   this.saveAndDownloadFile(blob, 'Bill Report');
    // })
    this.content = document.getElementById('PrintBill').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Bill.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }
  DateChange() {
    // let frmDt = new Date(this.FromDt);
    // let toDt = new Date(this.toDate);
    // let days = Math.floor((Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
    const frmDt = parse(this.FromDt, 'dd-MM-yyyy', new Date());
    const toDt = parse(this.toDate, 'dd-MM-yyyy', new Date());
    const daysDifference = differenceInDays(toDt, frmDt);

    console.log('Number of days difference:', daysDifference);

    this.dateDiff = daysDifference;

    // this.toDate = event.target.value;
    // var frmDt = new Date(this.FromDt);
    // this.toDate = new Date(this.toDate);
    // this.dateDiff = Math.floor((Date.UTC(this.toDate.getFullYear(), this.toDate.getMonth(), this.toDate.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
  }
  submitDateExchange() {
    if (this.toDate == '') {
      this.commonService.showErrorToast("To Date Can't be Blank!!")
      return
    }
    const parts = this.toDate.split('-');
    const formattedDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);
    let toDt = this.datePipe.transform(formattedDate, 'dd-MM-yyyy 08:00')
    const dateObject = moment(toDt, 'DD-MM-YYYY HH:mm').toDate();
    const obj = {
      reqId: this.modalData.bookingDetails.bookingId,
      toDate: dateObject,
      noOfDays: this.dateDiff
    }
    console.log(obj)
    this.mdmService.extendBooking(obj).subscribe(res => {
      if (res.status == 1) {
        this.commonService.showSuccessToast("Extension Request Submitted.");
        this.modalctrl.dismiss()
        // this.router.navigate(['/after-login/pages/guest/view-booking/guest-house']);
      }
      else {
        this.commonService.showErrorToast(res.statusDesc);
        this.modalctrl.dismiss();
      }

    })
  }
  getRequest() {
    this.mdmService.getRequestData(this.bookingDetails.bookingId).subscribe(res => {
      console.log("getRequest ", res);
      this.reqData = res.data;
      this.bookingId = this.reqData.bookingId;
      this.bookingDate = this.reqData.bookingDt;
      this.cityName = this.reqData.cityName;
      this.guestName = this.reqData.name;
      this.ghName = this.reqData.ghName;
      this.fromdt = this.reqData.fromDate;
      this.toDt = this.reqData.toDate;
      this.noRooms = this.reqData.noRooms;
      this.noBeds = this.reqData.noBeds ? this.reqData.noBeds : '-';
      this.mobileNO = this.reqData.mobile;
      this.purpose = this.reqData.purpose;
      this.caegory = this.reqData.category;
      this.designation = this.reqData.desg;
      this.office = this.reqData.office;
      this.conveyance = this.reqData.conveyance;


    })
  }
  download() {
    this.content = document.getElementById('PrintInvoice').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }

  saveAndDownloadFile(fileData: Blob) {
    console.log("blob is", fileData);
    const fileName = 'documet.pdf'
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(fileData);
    link.setAttribute('download', fileName);
    console.log("limkll", link.href);
    this.link = link
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);

  }

  public billViewData(data) {
    console.log(data)

    if (data.billStatus == 'P' || data.billStatus == 'B') {
      this.totalAmountText = "Total Amount"
      this.headerForBill = "Bill Details For Advance Payment"
      this.showInFinal = false;
      if (data.billStatus == 'B') {
        this.amountToPayText = "Amount Need To Pay"
      }
      if (data.billStatus == 'P') {
        this.amountToPayText = "Amount Paid";
      }
      this.amountToPay = data.advanceAmount

    }

    if (data.billStatus == 'F' || data.billStatus == "FB") {
      if(data.additonalCharge=="0"){
        this.ifNullAdd = false;
      }else{
        this.ifNullAdd = true;
      }
      this.totalAmountText = "Total Amount(Amount+Additional Charge)"
      this.showInFinal = false;
      this.headerForBill = "Bill Details For Total Payment"

      if (data.billStatus == 'FB') {
        this.amountToPayText = "Amount Need To Pay"
        this.amountToPay = data.pendingAmount;
      }
      if (data.billStatus == 'F') {
        this.showInFinal = true;
        this.amountToPayText = "Amount Paid";
        this.amountToPay = data.pendingAmount;
      } 
    }

    this.totalAmount = parseInt(data.pendingAmount) + parseInt(data.advanceAmount)
  }

  public convertNumberToWords(number: number): string {
    console.log(number)
    return numberToWords.toWords(number);
  }

  public RecieptViewData(data) {
    this.mdmService.getRecieptDetailsForMobile(data.billId).subscribe(
      res => {
        if (res.status == 1) {
          // this.recieptData = res.data;
          this.confBillId = data.billId
          // console.log(this.recieptData)
          this.billDate = this.datePipe.transform(this.currDate, 'dd-MM-yyyy')
          this.receiptDate = this.datePipe.transform(this.currDate, 'dd-MM-yyyy')
          console.log(this.datePipe.transform(this.currDate, 'dd-MM-yyyy'))
          let i;
          this.confReceiptData = res.data.data;
          this.guestName = this.confReceiptData[0]?.name;
          this.guestName = this.guestName.split(',')[0];
          for (i = 0; i < res.data.data.length; i++) {
            if (res.data.data[i].status == 'P' || res.data.data[i].status == 'FB') {
              console.log("fgj")
              if (res.data.data[i].modeOfpayment == 'cash') {
                this.cashInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'pos') {
                this.posInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'online') {
                this.onlineInAdvance = res.data.data[i].amount;
              }
            }
            if (res.data.data[i].status == 'F') {
              console.log("fgj")
              if (res.data.data[i].modeOfpayment == 'cash' && res.data.data[i].recStatus == "A") {
                this.cashInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'cash' && res.data.data[i].recStatus == "F") {
                this.fcashInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'pos' && res.data.data[i].recStatus == "A") {
                this.posInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'pos' && res.data.data[i].recStatus == "F") {
                this.fposInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'online' && res.data.data[i].recStatus == "A") {
                this.onlineInAdvance = res.data.data[i].amount;
              }
              if (res.data.data[i].modeOfpayment == 'online' && res.data.data[i].recStatus == "F") {
                this.fonlineInAdvance = res.data.data[i].amount;
              }

            }
            this.totalAdvance = parseInt(this.cashInAdvance) + parseInt(this.posInAdvance) + parseInt(this.onlineInAdvance)
            this.totalFinal = parseInt(this.fcashInAdvance) + parseInt(this.fposInAdvance) + parseInt(this.fonlineInAdvance)
            this.totalAmount = this.totalAdvance + this.totalFinal
            console.log()
            console.log(this.fonlineInAdvance)
            if (res.data.data[i].recStatus == "A") {
              this.isFinalReciept = false;
              this.recieptData = res.data.data[i]
              this.billDate = this.recieptData.billDate;
              this.receiptDate = this.recieptData.receiptDate;
              console.log(res.data.data[i])
            }

            if (res.data.data[i].recStatus == "F") {
              this.isFinalReciept = true;
              this.finalRecieptData = res.data.data[i]
            }
          }
        }


      })
  }
  public getNoc(data) {
    this.mdmService.getNoc(data.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          console.log(res.data)
          this.nocDetails = res.data.data;
          this.encRequest = res.data?.encRequest;
          this.accessCode = res.data?.accessCode;
        } else {
          this.commonService.showErrorToast(res.statusDesc)
        }

        // if (res.status==1) {
        //   // this.recieptData = res.data;
        //   this.confBillId = data.billId
        //   console.log(this.recieptData)

        //   let i;
        //   for(i=0;i< res.data.data.length;i++){
        //     if(res.data.data[i].recStatus == "A"){
        //       this.isFinalReciept = false;
        //       this.recieptData = res.data.data[i]
        //     console.log(res.data.data[i])
        //     }

        //     if(res.data.data[i].recStatus == "F"){
        //       this.isFinalReciept = true;
        //       this.finalRecieptData = res.data.data[i]
        //     }
        //   }
        // }
      })
  }
  public getDuesPaymentReceiptForMobile(data) {
    this.mdmService.getDuesPaymentReceiptForMobile(data.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          console.log(res.data)
          this.paymentData = res.data;
        } else {
          this.commonService.showErrorToast(res.statusDesc)
        }
      },
      err => {
        this.commonService.showErrorToast("Something Went Wrong.")
      })
  }

  async submitNOC() {
    this.clicked = true;
    console.log('url', AppConstants.BASE_API_URL);
    console.log("encRequest", this.encRequest);
    console.log("accessCode", this.accessCode);
    // const url = `http://192.168.29.78:8080/ngms/pgMobileRequest.do?encRequest=${this.encRequest}&accessCode=${this.accessCode}`;
    const url = AppConstants.BASE_API_URL+`pgMobileRequest.do?encRequest=${this.encRequest}&accessCode=${this.accessCode}`;
    await Browser.open({ url: url });
    Browser.addListener('browserFinished',() => {
      console.log("Browser finished")
      this.modalctrl.dismiss();
      // this.router.navigate(['after-login/pages/guest/view-booking/guest-house'])
    })
  }

  downloadBillForConference() {
    console.log("Inside downloadBillForConference")
    this.content = document.getElementById('billDetails').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }
  downloadRecieptForConference() {
    console.log("Inside downloadBillForConference")
    this.content = document.getElementById('RecieptDetails').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }

  public submitPartialCan() {
    if (this.partialCancelFrmDt == '' || this.partialCancelFrmDt == null) {
      this.commonService.showErrorToast("Please provide from date.")
      return;
    }
    let obj = {
      bookingId: this.modalData.bookingDetails.bookingId,
      fromDate: this.partialCancelFrmDt + ' ' + '09:00',
      toDate: this.partialCancelToDt + ' ' + '08:00'
    }

    console.log(obj)
    this.mdmService.partialCancelForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc)
          this.modalctrl.dismiss(res.status);
        } else {
          this.commonService.showErrorToast(res.statusDesc)
          this.modalctrl.dismiss()
        }
      }
    )
  }

  // async cancelFunction() {
  //   const alert = await this.alertController.create({
  //     cssClass: 'alertCancel',
  //     header: 'Are you sure?',
  //     message: 'Are you sure to cancel the booking request !',
  //     buttons: [
  //       {
  //         text: 'Yes,cancel it',
  //         cssClass: 'alertButton',
  //         handler: () => {
  //           // localStorage.clear();
  //           this.cancelBooking(this.bookingDetails);
  //         },
  //       },
  //       {
  //         text: 'Close',
  //         handler: () => {
  //           console.log('Confirm No');
  //         },
  //       },

  //     ],
  //   });
  //   await alert.present();
  // }

  public async cancelBooking() {
    console.log("book", this.bookingDetails)
    let cancelMessage = "Cancellation after from date will result in non occupancy charges, Are you sure to cancel?"
    const date = new Date();
    console.log("date", date.toLocaleString());
    let checkInDate = this.changeDateFormatyymmdd(this.bookingDetails.fromDate.split(' ')[0])
    console.log("CancelFrmDt", this.CancelFrmDt)
    console.log("checkInDate", checkInDate)
    if (this.CancelFrmDt <= checkInDate) {
      cancelMessage = "Are you sure to cancel?";
      this.twentyFourCancel = 1;
    } else {
      this.twentyFourCancel = 0;
    }
    const obj = {
      bookingId: this.bookingDetails.bookingId,
      fromDate: this.CancelFrmDt + ' ' + '08:00',
      toDate: this.CancelToDt + ' ' + '08:00',
      twentyFourCancel: this.twentyFourCancel
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

            this.mdmService.getCancelBooking(obj).subscribe(res => {
              if (res.status == 1) {
                this.commonService.showSuccessToast(res.statusDesc);
                this.modalctrl.dismiss(res.status);
              }
              else {
                this.commonService.showErrorToast(res.statusDesc);
                this.modalctrl.dismiss();
              }
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

  public openDatepicker(name) {
    this[name].nativeElement.click();
  }




}
