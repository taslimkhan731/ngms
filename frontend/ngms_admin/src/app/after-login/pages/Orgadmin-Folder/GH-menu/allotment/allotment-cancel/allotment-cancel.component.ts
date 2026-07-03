import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-allotment-cancel',
  templateUrl: './allotment-cancel.component.html',
  styleUrls: ['./allotment-cancel.component.scss'],
})
export class AllotmentCancelComponent implements OnInit {
  cancelData
  getCanRoomDetails = [];
  canType;
  canMsg;
  selectedGuest = []
  occupied = []
  showCanBtn = false;
  constructor(private commonservice: CommonService,
    private alertController: AlertController,
    private loadingController: LoadingController,
    private location: Location,
    private navParam: NavParams,
    private modalController: ModalController,
    private mdmService: MdmService) { }

  ngOnInit() {

  }
  async getCancelRoomDetails(id, type) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCanRoomDetailsList(id, type).subscribe(
      res => {
        if (res.status == 1) {
          this.getCanRoomDetails = res.data;
          this.getCanRoomDetails.forEach(el => {
            if (el.occupied) {
              this.occupied.push(el.allotid)
            }
          })
          loading.dismiss()

        } else if (res.status == 0) {
          this.canMsg = "No Data Available";
          loading.dismiss();
        }
      },
      err=> {
        loading.dismiss();
      },
      ()=> {
        loading.dismiss();
      })
  }

  public select(event, allotid) {
    if (event.target.checked) {
      this.selectedGuest.push(allotid);
    }

    if (!event.target.checked) {
      this.selectedGuest.forEach((ele, ind) => {
        if (ele == allotid) {
          this.selectedGuest.splice(ind, 1);
        }
      })
    }

    if (this.occupied.length == this.selectedGuest.length) {
      this.showCanBtn = true
    } else {
      this.showCanBtn = false;
    }


  }

  public async cancel() {
    if (this.canType == 'C') {
      const alert = await this.alertController.create({
        cssClass: 'alertCancel',
        header: 'Are you sure?',
        message: "Are you sure to cancel?",
        buttons: [
          {
            text: 'Yes,cancel it',
            cssClass: 'alertButton',
            handler: () => {
              this.cancelReservation();
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
    } else if (this.canType == 'P') {
      const alert = await this.alertController.create({
        cssClass: 'alertCancel',
        header: 'Are you sure?',
        message: "Are you sure to partial cancel?",
        buttons: [
          {
            text: 'Yes,cancel it',
            cssClass: 'alertButton',
            handler: () => {
              this.partialCancel();
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

  public async cancelReservation() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    loader.present()
    this.mdmService.cancelReservationForAllot(this.cancelData.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.commonservice.showSuccessToast(res.statusDesc)
          this.modalController.dismiss();
        } else {
          loader.dismiss();
          this.commonservice.showErrorToast(res.statusDesc);
          this.modalController.dismiss();
        }
      },
      err => {
        this.commonservice.showErrorToast("Something Went Wrong");
        loader.dismiss();
      })
  }
  public async partialCancel() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    let selectedGuest = this.selectedGuest.join(",")
    console.log(selectedGuest)
    let postData = {
      allotId: selectedGuest,
      bookingId: this.cancelData.bookingId
    }
    loader.present()
    this.mdmService.partialCancelForAllot(postData).subscribe(res => {
      if (res.status == 1) {
        loader.dismiss();
        this.commonservice.showSuccessToast(res.statusDesc)
        this.modalController.dismiss();
      } else {
        loader.dismiss();
        this.commonservice.showErrorToast(res.statusDesc);
        this.modalController.dismiss();
      }
    }, err => {
      this.commonservice.showErrorToast("Something Went Wrong");
      loader.dismiss();
    })
  }

  public goBack() {
    this.modalController.dismiss()
  }

  async ionViewWillEnter() {
    this.cancelData = this.navParam.data.data;
    console.log(this.cancelData)
    this.canType = this.navParam.data.type;
    this.getCancelRoomDetails(this.cancelData.bookingId, this.canType);

  }

}
