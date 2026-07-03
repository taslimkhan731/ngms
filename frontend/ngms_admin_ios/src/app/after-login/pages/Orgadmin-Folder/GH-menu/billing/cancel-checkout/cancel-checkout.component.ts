import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ModalController, LoadingController, NavParams, AlertController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-cancel-checkout',
  templateUrl: './cancel-checkout.component.html',
  styleUrls: ['./cancel-checkout.component.scss'],
})
export class CancelCheckoutComponent implements OnInit {

  public bookingId;
  public billDetails;
  constructor(
    private modalCtrl: ModalController,
    private loadingCtrl: LoadingController,
    private alertController: AlertController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private navParams: NavParams,
  ) { }

  ngOnInit() {}

  public goBack(){
    this.modalCtrl.dismiss();
  }

  ionViewWillEnter() {
    this.bookingId = this.navParams.data.bookingId;
    this.getBillDetailsByBookingIdForMobile();
  }

  public async getBillDetailsByBookingIdForMobile() {
    const loader = await this.loadingCtrl.create({
      message: "Please wait..."
    });
    loader.present();
    this.mdmService.getBillDetailsByBookingIdForMobile(this.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.billDetails = res.data;
        } else {
          loader.dismiss();
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      },
      ()=> {
        loader.dismiss()
      }
    )
  }

  public async cancelCheckOut(){
    const alert = await this.alertController.create({
      cssClass: 'my-custom-class',
      header: 'Are you sure?',
      message: 'Are you sure to cancel check out?',
      buttons: [
        {
          text: 'Yes',
          handler: () => {
            this.cancel();
          },
        },

        {
          text: 'No',
          handler: () => {
            console.log('Confirm No');
          },
        },

      ],
    });
    await alert.present();
  }

  public async cancel(){
    let guestList = []
    for (let i = 0; i < this.billDetails?.guestDetails.length; i++) {
      guestList.push(this.billDetails.guestDetails[i].trId)
      console.log(guestList)
    }
    const loader = await this.loadingCtrl.create({
      message: "Please wait..."
    });
    loader.present();

    this.mdmService.cancelCheckoutDetailsForMobile(guestList).subscribe(
      res => {
        if(res.status == 1) {
          loader.dismiss()
          this.commonService.showSuccessToast("checkout cancel successfull")
          this.modalCtrl.dismiss()
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss()
        this.commonService.showErrorToast("Sometthing Went Wrong!!")
      },
      ()=> {
        loader.dismiss();
      }
    )
    
  }
}
