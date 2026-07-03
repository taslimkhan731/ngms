import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, LoadingController, ModalController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { ModalViewComponent } from '../modal-view/modal-view.component';

@Component({
  selector: 'app-conveyance',
  templateUrl: './conveyance.component.html',
  styleUrls: ['./conveyance.component.scss'],
})
export class ConveyanceComponent implements OnInit {
  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    { btnName: 'Delete', btnIcon: 'trash-outline', color: '#E91E34', background: '#FFE2E5' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
    { btnName: 'Download', btnIcon: 'download-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Extend', btnIcon: 'print-outline', color: '#FF7B01', background: '#FFF2CC' },
    { btnName: 'Feedback', btnIcon: 'mail-outline', color: '#0FA958', background: '#DAFEFE' },
    { btnName: 'Payment Receipt', btnIcon: 'mail-outline', color: '#BA18C5', background: '#F8E7FA' },
  ];
  guestHouseList: any;
  public twentyFourCancel: number;
  constructor(
    private mdmService: MdmService,
    private commonService: CommonService,
    public loadingController: LoadingController,
     private router: Router,
    private alertController: AlertController,
    private modalcontroller: ModalController,
  ) { }

  ngOnInit() {
    this.viewConveyanceHistory();
  }
  async viewConveyanceHistory() {
    const obj = {
      "id": "con"
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewBookingHistory(obj).subscribe(res => {
      if(res.status==1){
        loading.dismiss();
        this.guestHouseList=res.data;
      }
      else{
        this.commonService.showErrorToast(res.statusDesc)
      }
     
    })
  }
    async getbutton(button, bookingDetails) {
      console.log(bookingDetails);
      this.commonService.storeDetails(bookingDetails);
      if (button == 'Edit') {
        this.router.navigate(['after-login/pages/guest/create-booking/edit'], { queryParams: { bhawan_Name: bookingDetails.title } });
      } else if (button == 'View') {
        this.router.navigate(['after-login/pages/guest/create-booking/view'], { queryParams: { bhawan_Name: bookingDetails.title } });
      } else if (button == 'Delete') {
        this.deleteFunction(bookingDetails);
      } else if (button == 'Cancel') {
        this.cancelFunction(bookingDetails)
      } else if (button == 'Download' || button == 'Extend' || button == 'Feedback' || button == 'Payment Receipt'|| button == 'bill') {
        const modal = await this.modalcontroller.create({
          component: ModalViewComponent,
          componentProps: { button, bookingDetails }
        });
        return await modal.present();
      }
    }
  async deleteFunction(bookingDetails) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to delete the booking request !',
      buttons: [
        {
          text: 'Yes,Delete it',
          cssClass: 'alertButton',
          handler: () => {
            this.deleteBooking(bookingDetails);
            this.viewConveyanceHistory()
          },
        },
        {
          text: 'Cancel',
          handler: () => {
            console.log('Confirm No');
          },
        },

      ],
    });
    await alert.present();
  }

  async cancelFunction(bookingDetails) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to cancel the booking request !',
      buttons: [
        {
          text: 'Yes,cancel it',
          cssClass: 'alertButton',
          handler: () => {
            // localStorage.clear();
            this.cancelBooking(bookingDetails);
            this.viewConveyanceHistory()

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

  public cancelBooking(bookingDetails: any) {
    console.log("book", bookingDetails)
    const date = new Date();
    console.log("date", date.toLocaleString());
    if (bookingDetails.fromDate < date.toLocaleString()) {
      this.twentyFourCancel = 1;
    } else {
      this.twentyFourCancel = 0;
    }
    const obj = {
      bookingId: bookingDetails.bookingId,
      fromDate: bookingDetails.fromDate,
      toDate: bookingDetails.toDate,
      twentyFourCancel: this.twentyFourCancel
    }
    this.mdmService.getCancelBooking(obj).subscribe(res => {
      if (res.status == 1) {
        this.commonService.showSuccessToast(res.statusDesc);
      }
      else {
        this.commonService.showErrorToast(res.statusDesc);
      }
    })
  }

  public deleteBooking(bookingDetails) {
    console.log("book", bookingDetails);
    this.mdmService.getDeleteBooking(bookingDetails.bookingId).subscribe(res => {
      if (res.status == 1) {
        this.commonService.showSuccessToast(res.statusDesc);
      }
      else {
        this.commonService.showErrorToast(res.statusDesc);
      }

    })
  }

}
