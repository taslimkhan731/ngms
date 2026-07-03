import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ModalController, NavParams, LoadingController } from '@ionic/angular';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { ModeratorCancelComponent } from '../moderator-cancel/moderator-cancel.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-conference-mod',
  templateUrl: './conference-mod.component.html',
  styleUrls: ['./conference-mod.component.scss'],
})
export class ConferenceModComponent implements OnInit {

  public allBookingData;
  public bookingId;
  public type;
  public headerName;
  public totalAmount;
  public currDate = new Date();
  public receiptDate;
  public billDate;
  public data;
  public paymentData;

  constructor(
    private fb: FormBuilder,
    private modalController: ModalController,
    private navParams: NavParams,
    private commonService: CommonService,
    private mdmService: MdmService,
    private datePipe: DatePipe,
    private loadingController: LoadingController
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.type = this.navParams.data.btnName;
    this.bookingId = this.navParams.data.data.bookingId;
    this.data = this.navParams.data.data;
    console.log(this.data)
    if (this.type == 'confSave') {
      this.headerName = 'Save Moderator Details'
      this.getConferenceBookingDetailsByBookingId()
    }
    if (this.type == 'confSave') {
      this.headerName = 'Save Moderator Details'
      this.getConferenceBookingDetailsByBookingId()
    }
    if (this.type == 'confBill') {
      this.headerName = 'Billing Details'
      this.allBookingData = this.navParams.data.data;
      this.totalAmount = parseInt(this.allBookingData.additionalCharge) + parseInt(this.allBookingData.totalAmt)
    }
    if (this.type == 'confReceipt') {
      this.headerName = 'Receipt'
      this.getRecieptDetailsForMobile(this.navParams.data.data.confBillId)
    }
  }

  public async getRecieptDetailsForMobile(billId) {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })

    loader.present();
    this.mdmService.getRecieptDetailsForMobile(billId).subscribe(
      res => {
        if (res.status == 1) {
          this.allBookingData = res.data.data[0];
          console.log(this.allBookingData)
          this.paymentData = res.data.data;
          console.log(this.paymentData)
          this.setReceiptData(res.data.data);
          loader.dismiss();
        } else if (res.status == 0) {
          loader.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
        }
      },
      err => {
        loader.dismiss()
        this.commonService.showErrorToast('Something went wrong');
      },
      ()=> {
        loader.dismiss()
      });
  }

  public setReceiptData(data) {
    this.receiptDate = this.datePipe.transform(this.currDate, 'dd-MM-yyyy')
    this.billDate = this.datePipe.transform(this.currDate, 'dd-MM-yyyy')
    console.log(data)
    for (var i = 0; i < data.length; i++) {
      console.log(data[i])
      if (data[i].recStatus == "A") {
        this.receiptDate = data[i].receiptDate;
        this.billDate = data[i].billDate;
      }
      console.log(this.billDate)

      // if (result.data[i].recStatus == "A") {
      //   $(".finalPaymentReceipt").hide();
                  // if (result.data[i].modeOfpayment === 'cash') {
                  //   $('#cashShow').show();
                  //   $('#pCashAmount').val(result.data[i].amount);
                  // }
                  // if (result.data[i].modeOfpayment === 'pos') {
                  //   $('#posShow').show();
                  //   $('#pPostx').show();
                  //   $('#pPosAmount').val(result.data[i].amount);
                  //   $('#pPosId').val(result.data[i].pos_tr_num);
                  // }
                  // if (result.data[i].modeOfpayment === 'online') {
                  //   $('#onlineShow').show();
                  //   $('#pOnlineAmount').val(result.data[i].amount);
                  // }
      // }
    }
  }


  public async getConferenceBookingDetailsByBookingId() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    const params = new HttpParams()
      .set('bookingId', this.bookingId)

    loader.present();
    this.mdmService.getConferenceBookingDetailsByBookingId(params).subscribe(
      res => {
        if (res.status == 1) {
          this.allBookingData = res.data;
          console.log(this.allBookingData)
          // this.setBookingData();
          loader.dismiss();
        } else if (res.status == 0) {
          loader.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
        }
      },
      err => {
        loader.dismiss()
        this.commonService.showErrorToast('Something went wrong');
      },
      ()=> {
        loader.dismiss()
      });
  }

  public async cancel(modData) {
    const modal = await this.modalController.create({
      component: ModeratorCancelComponent,
      componentProps: { modData, dataComingFrom: 'Conference' }
    });
    modal.onDidDismiss().then((data) => {
      this.modalController.dismiss();
    })
    return await modal.present();
  }

  public async saveDetails() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    let postData = {
      bookingId: this.allBookingData.bookingId,
      catg: this.data.catgId,
    }
    console.log(postData)
    loader.present();
    this.mdmService.confModeration(postData).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.commonService.showSuccessToast('Moderator Details Saved Successfully')
          this.modalController.dismiss()
        } else {
          loader.dismiss();
          this.commonService.showErrorToast(res.statusDesc)
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      ()=> {
        loader.dismiss()
      }
    )
  }

  public goBack() {
    this.modalController.dismiss();
  }

}
