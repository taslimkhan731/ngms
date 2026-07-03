import { Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AlertController, InfiniteScrollCustomEvent, IonContent, LoadingController, ModalController, Platform } from '@ionic/angular';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { ModalViewComponent } from '../../modal-view/modal-view.component';

@Component({
  selector: 'app-view-conference',
  templateUrl: './view-conference.component.html',
  styleUrls: ['./view-conference.component.scss'],
})
export class ViewConferenceComponent implements OnInit {

  @ViewChild(IonContent, { static: true }) content: IonContent;
  backToTop: boolean = false;
  public confList = [];
  public pageSize=10;
  public pageNum=1;

  constructor(private modalcontroller: ModalController,
    private router: Router,
    private alertController: AlertController,
    private commonService: CommonService,
    private mdmService: MdmService,
    public loadingController: LoadingController,
    private platform: Platform,) { }

  ngOnInit() {
    // this.router.events.subscribe((e)=>{
    //   if (e instanceof NavigationEnd){
    //     this.viewBookingHistory();
    //   }
    // });
  }

  ionViewWillEnter(){
    this.viewBookingHistory(this.pageSize,this.pageNum);
  }

  onIonInfinite(ev) {
    // this.generateItems();
    this.viewBookingHistory(this.pageSize,++this.pageNum);
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 500);
  }

  async viewBookingHistory(pageSize,pageNum) {
    console.log("Inside viewBookingHistory")
    const obj = {
      "cId":"",
      "reqType": "GH",
      "pageSize":pageSize,
      "pageNum":pageNum
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getConfrenceRequestDetailsForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          this.confList = this.confList.concat(res.data.data);
          console.log(this.confList)
        } else if(res.status == 0) {
          this.commonService.showErrorToast(res.statusDesc)
          loading.dismiss();
        }
        else {
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast(err.statusDesc)
      })
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  async getbutton(button, bookingDetails) {
    console.log('button', bookingDetails);
    // localStorage.setItem("gfdhgf","dgfdhf");
    // this.commonService.storeDetails(bookingDetails);
    localStorage.setItem("bookingDetails",JSON.stringify(bookingDetails))
    if (button == 'Edit') {
      this.router.navigate(['after-login/pages/guest/create-booking/editConference'], { queryParams: { bhawan_Name: bookingDetails.title } });
    } else if (button == 'View') {
      this.router.navigate(['after-login/pages/guest/create-booking/viewConference'], { queryParams: { bhawan_Name: bookingDetails.title } });
    } else if (button == 'Delete') {
      this.deleteFunction(bookingDetails);
    } else if(button == 'Bill View' || button == 'Receipt View') {
      const modal = await this.modalcontroller.create({
        component: ModalViewComponent,
        componentProps: { button, bookingDetails }
      });
      return await modal.present();
    }

  }

  public async deleteFunction(data) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to cancel the conference request?',
      buttons: [
        {
          text: 'Yes,Cancel it',
          cssClass: 'alertButton',
          handler: () => {
            this.deleteBooking(data);
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

  public deleteBooking(bookingDetails) {
    console.log("book", bookingDetails);
    this.mdmService.cancelConfrenceDetailsForMobile(bookingDetails.bookingId).subscribe(res => {
      if (res.status == 1) {
        this.commonService.showSuccessToast(res.statusDesc);
        this.pageSize = 10;
        this.pageNum = 1;
        this.confList = []
        this.viewBookingHistory(this.pageSize,this.pageNum)
      }
      else {
        this.commonService.showErrorToast(res.statusDesc);
      }

    })
  }

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  handleRefresh(event) {
    setTimeout(() => {
      this.confList = []
      this.pageSize = 10;
      this.pageNum = 1;
      this.viewBookingHistory(this.pageSize,this.pageNum);
      event.target.complete();
    }, 2000);
  }
  ionViewDidLeave(){
    this.pageSize = 10;
    this.pageNum = 1;
    this.confList = []
  }
}
