import { DatePipe, Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';

@Component({
  selector: 'app-make-receipt',
  templateUrl: './make-receipt.component.html',
  styleUrls: ['./make-receipt.component.scss'],
})
export class MakeReceiptComponent implements OnInit {

  public content: string;
  public receiptData;
  public guestNames;
  public currDate = new Date();
  public dateTime;
  public disableCash = true;
  public disablePos = true;
  public disableOnline = true;
  public cashAmount;
  public posAmount;
  public onlineAmount;
  public showPosTransation = false;
  public transactionNo;
  public type;
  public totalAmount;
  public receiptPDFData;

  constructor(private commonservice: CommonService,
    private alertController: AlertController,
    private loadingController: LoadingController,
    private location: Location,
    private navParams: NavParams,
    private modalController: ModalController,
    private commonService: CommonService,
    private pdfGenerator: PDFGenerator,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe,
    private mdmService: MdmService) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.receiptData = this.navParams.data.receiptData;
    this.type = this.navParams.data.type;
    console.log(this.receiptData)
    this.setReceiptData()
    this.dateTime = this.datePipe.transform(this.currDate, 'dd-MM-yyyy HH:mm');
    if (this.type == 'viewReceipt') {
      this.getReceiptDataForPDF()
    }
  }

  public setReceiptData() {
    this.guestNames = this.receiptData.guest.map((guest: string) => {
      if (guest.indexOf(',') !== -1) {
        return guest.slice(0, guest.indexOf(','));
      } else {
        return guest;
      }
    });
    console.log(this.guestNames)
  }

  getCheckboxId(checkbox: HTMLInputElement, event) {
    let id = checkbox.id;
    let isChecked = event.target.checked;
    let total = this.receiptData.total;
    if (id === 'cash') {
      // this.posAmount = 0;
      // this.onlineAmount = 0;
      if (isChecked) {
        this.disableCash = false;
        let posAmount = this.posAmount || 0;
        // let onlineAmount = this.onlineAmount || 0;
        console.log(total, posAmount,)
        let cashAmount = total - posAmount;
        this.cashAmount = cashAmount;
        console.log(this.cashAmount)
      } else {
        console.log("Inside Else(cash)")
        this.disableCash = true;
        this.cashAmount = 0;
      }
    }
    if (id === 'pos') {
      // this.cashAmount = 0;
      // this.onlineAmount = 0
      if (isChecked) {
        this.disablePos = false
        let cashAmount = this.cashAmount || 0
        // let onlineAmount = this.onlineAmount || 0
        console.log(total, cashAmount)
        let posAmount = total - cashAmount;
        this.posAmount = posAmount
        this.showPosTransation = true;
        console.log(this.posAmount)
        // $('#postx').prop('hidden', false);
      } else {
        console.log("Inside Else(pos)")
        this.showPosTransation = false;
        this.disablePos = true
        this.posAmount = 0
      }
    }
    // if (id === 'online') {
    //   // this.cashAmount = 0;
    //   // this.posAmount = 0
    //   if (isChecked) {
    //     this.disableOnline = false;
    //     let cashAmount = this.cashAmount || 0
    //     let posAmount = this.posAmount || 0;
    //     let onlineAmount = total - cashAmount - posAmount;
    //     console.log(total, cashAmount, posAmount)
    //     this.onlineAmount = onlineAmount;
    //     console.log(this.onlineAmount)
    //   } else {
    //     console.log("Inside Else(online)")
    //     this.disableOnline = true;
    //     this.onlineAmount = 0
    //   }
    // }
  }

  public check(event, type) {
    let total = this.receiptData.total;
    let cashAmount = parseFloat(this.cashAmount || 0);
    let posAmount = parseFloat(this.posAmount || 0);
    // let onlineAmount = parseFloat(this.onlineAmount || 0);
    let totalAmount = cashAmount + posAmount;
    if (totalAmount > total) {
      this.commonservice.showErrorToast("Amount should not be greater than total.")
      if (type == 'cash') {
        this.cashAmount = 0
      }
      if (type == 'pos') {
        this.posAmount = 0
      }
      if (type == 'online') {
        this.onlineAmount = 0
      }
    }
  }

  public async submitReceipt(cash, pos) {
    let mode = {}
    if (!cash && !pos) {
      this.commonservice.showErrorToast("Please select payment mode")
      return;
    }
    if (cash) {
      if (this.cashAmount == '' || this.cashAmount === '0') {
        this.commonservice.showErrorToast('Cash amount should not be empty or zero if cash is selected');
        return false;
      }
      mode['cash'] = parseFloat(this.cashAmount);
    }
    if (pos) {
      if (this.posAmount == '' || this.posAmount === '0') {
        this.commonservice.showErrorToast('POS amount should not be empty or zero if cash is selected');
        return false;
      }
      if (this.transactionNo == '') {
        this.commonservice.showErrorToast('Please provide transaction number');
        return false;
      }
      mode['pos'] = parseFloat(this.posAmount);
    }
    // if (online) {
    //   if (this.onlineAmount == '' || this.onlineAmount === '0') {
    //     this.commonservice.showErrorToast('Online amount should not be empty or zero if cash is selected');
    //     return false;
    //   }
    //   mode['online'] = parseFloat(this.onlineAmount);
    // }

    let data = {
      billId: this.receiptData.billId,
      total: this.receiptData.total,
      mobileNo: this.receiptData.mobileNo,
      cityId: this.receiptData.cityId,
      ghId: this.receiptData.ghId,
      bookingId: this.receiptData.bookingId,
      mode: mode,
      transactionNo: this.transactionNo
    };
    console.log(data)
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });

    loader.present();
    this.mdmService.saveReceiptForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          // this.router.navigate(['after-login/pages/orgadmin/GH-menu/receipt'])
          this.modalController.dismiss(1);
          loader.dismiss();
        } else if (res.status == 0) {
          loader.dismiss();
          this.commonservice.showErrorToast(res.statusDesc);
        }
      },
      err => {
        loader.dismiss()
        // this.commonservice.showErrorToast('Something went wrong');
      },
      () => {
        loader.dismiss()
      }
    );
  }

  public goBack() {
    this.modalController.dismiss(0);
  }

  extractName(inputString: string): string {
    return inputString.split(',')[0].trim();
  }

  public getReceiptDataForPDF() {
    console.log("Inside Payment Reciept Download")
    this.mdmService.getReceiptPdfForMobile(this.receiptData?.recId).subscribe(
      res => {
        if (res.status == 1) {
          console.log(res)
          this.receiptPDFData = res.data;
          this.totalAmount = parseInt(this.receiptPDFData.advancedAmount) + parseInt(this.receiptPDFData.amountPaid)

          // this.router.navigate(['after-login/pages/orgadmin/GH-menu/receipt'])
        } else if (res.status == 0) {
          this.commonservice.showErrorToast(res.statusDesc);
        }
      },
      err => {
        // loader.dismiss()
        this.commonservice.showErrorToast('Something went wrong');
      });

  }
  public printReceipt() {
    this.content = document.getElementById('printReceipt').innerHTML;
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


}
