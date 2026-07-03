import { Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AlertController, InfiniteScrollCustomEvent, IonContent, LoadingController, ModalController, Platform } from '@ionic/angular';
import { ModalViewComponent } from '../modal-view/modal-view.component';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.scss'],
})
export class ViewBookingComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  headerToolbar = ' Booking List'
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
  backToTop: boolean = false;
  public pageSize=10;
  public pageNum=1;

  public actionButton: boolean = false;
  public guestHouseList = [];
  public twentyFourCancel: number;

  constructor(private modalcontroller: ModalController,
    private router: Router,
    private alertController: AlertController,
    private commonService: CommonService,
    private mdmService: MdmService,
    public loadingController: LoadingController,
    private platform: Platform,) { }

  ngOnInit() {
    // this.viewBookingHistory();
    // this.router.events.subscribe((e) => {
    //   if (e instanceof NavigationEnd) {
    //     this.viewBookingHistory();
    //   }
    // });
  }

  ionViewWillEnter(){
    this.viewBookingHistory(this.pageSize,this.pageNum);
  }

  ionViewDidLeave(){
    this.pageSize = 10;
    this.pageNum = 1;
    this.guestHouseList = []
  }

  onIonInfinite(ev) {
    // this.generateItems();
    this.viewBookingHistory(this.pageSize,++this.pageNum);

    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 500);

    // this.viewBookingHistory(this.pageSize, this.pageNum++).then((data) => {
    //   if (data.length === 0) {
    //     // No data available in the next page, inform the user
    //     this.showNoMoreDataMessage();
    //   } else {
    //     // Data was successfully loaded, continue processing
    //     // ...
    //   }
    // }).catch((error) => {
    //   // Handle API request error here
    //   this.showError(error);
    // }).finally(() => {
    //   setTimeout(() => {
    //     (ev as InfiniteScrollCustomEvent).target.complete();
    //   }, 500);
    // });
  }


  async viewBookingHistory(pageSize,pageNum) {
    const obj = {
      pageSize:pageSize,
      pageNum:pageNum
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewBookingHistory(obj).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          // this.guestHouseList = res.data;
          this.guestHouseList = this.guestHouseList.concat(res.data)
          console.log(this.guestHouseList)
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

  async getbutton(button, bookingDetails) {
    console.log('button', bookingDetails);
    // localStorage.setItem("gfdhgf","dgfdhf");
    // this.commonService.storeDetails(bookingDetails);
    localStorage.setItem("bookingDetails", JSON.stringify(bookingDetails))
    if (button == 'Edit') {
      this.router.navigate(['after-login/pages/guest/create-booking/edit'], { queryParams: { bhawan_Name: bookingDetails.title } });
    } else if (button == 'View') {
      this.router.navigate(['after-login/pages/guest/create-booking/view'], { queryParams: { bhawan_Name: bookingDetails.title } });
    } else if (button == 'Delete') {
      this.deleteFunction(bookingDetails);
      // } 
      // else if (button == 'Cancel') {
      //   this.cancelFunction(bookingDetails)
    } else if (button == 'Download' || button == 'Extend' || button == 'Feedback' || button == 'Payment Receipt' || button == 'bill' || button == 'NOC' || button == 'DUES' || button == 'Cancel') {
      const modal = await this.modalcontroller.create({
        component: ModalViewComponent,
        componentProps: { button, bookingDetails }
      });

      modal.onDidDismiss().then((data) => {
        this.pageSize = 10;
        this.pageNum = 1;
        this.guestHouseList = []
        this.viewBookingHistory(this.pageSize,this.pageNum)
      })
      return await modal.present();
    }
  }

  async deleteFunction(bookingDetails) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to delete the booking request?',
      buttons: [
        {
          text: 'Yes,Delete it',
          cssClass: 'alertButton',
          handler: () => {
            this.deleteBooking(bookingDetails);
            this.pageSize = 10;
            this.pageNum = 1;
            this.guestHouseList = []
            this.viewBookingHistory(this.pageSize,this.pageNum)
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
            this.viewBookingHistory(this.pageSize,this.pageNum)

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

  public async deleteBooking(bookingDetails) {
    console.log("book", bookingDetails);
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    this.mdmService.getDeleteBooking(bookingDetails.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc);
          this.pageSize = 10;
            this.pageNum = 1;
            this.guestHouseList = []
          this.viewBookingHistory(this.pageSize,this.pageNum);
          loading.dismiss()
        }
        else {
          this.commonService.showErrorToast(res.statusDesc);
          loading.dismiss()
        }
      },
      err => {
        this.commonService.showErrorToast("Something Went Wrong.")
        loading.dismiss()
      },
      ()=>{
        loading.dismiss();
      })
  }

  public clickAction() {
    this.actionButton = true;
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  handleRefresh(event) {
    setTimeout(() => {
      this.pageSize = 10;
      this.pageNum = 1;
      this.guestHouseList = []
      this.viewBookingHistory(this.pageSize,this.pageNum);
      event.target.complete();
    }, 2000);
  }

}
