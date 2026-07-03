import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { MakeReceiptComponent } from '../make-receipt/make-receipt.component';

@Component({
  selector: 'app-view-receipt',
  templateUrl: './view-receipt.component.html',
  styleUrls: ['./view-receipt.component.scss'],
})
export class ViewReceiptComponent implements OnInit {
  public bookingId;
  public billId;
  public recData;
  public guestNames;
  public currDate = new Date();

  constructor(private commonservice: CommonService,
    private navParams:NavParams,
    private alertController: AlertController,
    private loadingController: LoadingController,
    private location: Location,
    private modalController: ModalController,
    private route: ActivatedRoute,
    private mdmService: MdmService) { }
  ngOnInit() { }

  ionViewWillEnter() {
    // this.route.queryParams.subscribe((params) => {
    //   this.bookingId = params['bookingId']
    //   this.billId = params['billId']
    //   // console.log(this.bookingId)
    // })
    this.bookingId = this.navParams.data.bookingId;
    this.billId = this.navParams.data.billId;
    this.getReceiptDetailsForMobile()

  }

  public async getReceiptDetailsForMobile() {
    const loader = await this.loadingController.create({
      message: "Please wait..."
    });
    loader.present();

    this.mdmService.getReceiptDetailsForMobile(this.bookingId, this.billId).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.recData = res.data;
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
      },
      ()=>{
        loader.dismiss()
      }
    )
  }

  public async makeReceipt() {
    const modal = await this.modalController.create({
      component: MakeReceiptComponent,
      componentProps: { receiptData: this.recData, type: 'makeReceipt' }
    })
    modal.onDidDismiss().then((data) => {
      if(data.data == 1) {
        this.modalController.dismiss()
      }
     })

    return await modal.present();
  }

  public goBack() {
    this.modalController.dismiss();
  }

}
