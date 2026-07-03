import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IonContent, PopoverController, AlertController, LoadingController, ModalController, Platform, InfiniteScrollCustomEvent } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { Location } from '@angular/common';
import { ModeratorSaveComponent } from '../../../application-menu/moderator/moderator-save/moderator-save.component';

@Component({
  selector: 'app-current-booking-moderation',
  templateUrl: './current-booking-moderation.component.html',
  styleUrls: ['./current-booking-moderation.component.scss'],
})
export class CurrentBookingModerationComponent implements OnInit {

  selectGH = ''
  selectCity = ''
  backToTop: boolean = false;
  shiftStatus: any;
  shiftMsg: any;
  @ViewChild(IonContent, { static: true }) content: IonContent;
  public pageSize = 5;
  public totalPages;
  public pageNum = 1;
  private isFirstApiHit = true;
  public filterObj;
  public cityId;
  public ghId;
  obj = {
    "cId": "",
    "gId": "",
    "bookingId": "",
    "bookingDate": "",
    "category": "",
    "purpose": "",
    "mobNo": "",
    "name": "",
    "reqType": "GH",
    "status": "",
    pageSize: this.pageSize,
    pageNum: this.pageNum
  }
  applicationEntry = [];
  guestHouseList: any;

  constructor(
    public popoverController: PopoverController,
    public loadingController: LoadingController,
    private route: ActivatedRoute,
    public modalController: ModalController,
    private platform: Platform,
    private location: Location,
    private mdmService: MdmService,) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.route.queryParams.subscribe((params) => {
      this.cityId = params['city_id'];
      this.ghId = params['ghId'];
      this.filterObj = JSON.parse(params['obj'])
      // this.bookingForm.get('blockId').setValue(this.ghId);
      console.log("this.route.queryParams", this.filterObj)
    });
    this.obj.cId=this.cityId;
    this.obj.gId=this.ghId;
    this.getAllotmentList(this.obj)
  }

  onIonInfinite(ev) {
    this.obj.pageSize = 5;
    if (this.totalPages !== this.obj.pageNum) {
      this.obj.pageNum = this.obj.pageNum + 1;
      this.getAllotmentList(this.obj);
    }
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
  }

  async getbutton(button, data) {
    console.log("button type", button, data);
    if (button == 'MOD') {
      const modal = await this.modalController.create({
        component: ModeratorSaveComponent,
        componentProps: { data, btnName:'save', dataComingFrom: 'CBMOD', type:data.typeOfRoom }
      });
      modal.onDidDismiss().then(() => {
        this.obj.pageSize = 5;
        this.obj.pageNum = 1;
        this.isFirstApiHit = true;
        this.applicationEntry = []
        this.getAllotmentList(this.obj)
      })
      return await modal.present();
    }
  }


  public async getAllotmentList(objData) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAllotmentListDetails(objData).subscribe(res => {
      console.log("resis ", res.data)
      if (this.isFirstApiHit) {
        loading.dismiss();
        this.isFirstApiHit = false;
      }
      this.applicationEntry = this.applicationEntry.concat(res.data.data);
      this.totalPages = res.data.totalPages;
      console.log("resis ", this.applicationEntry)
    }, () => {
      if (this.isFirstApiHit) {
        loading.dismiss();
      }
    })
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

  public goBack(){
    this.location.back()
  }

  handleRefresh(event) {
    setTimeout(() => {
      this.obj.pageSize = 5;
      this.obj.pageNum = 1;
      this.applicationEntry = [];
      this.isFirstApiHit = true
      this.getAllotmentList(this.obj)
      event.target.complete();
    }, 2000);
  }

  ionViewDidLeave(): void {
    // this.selectGH = ''
    // this.selectCity = ''
    this.obj.pageSize = 5;
    this.obj.pageNum = 1;
    this.applicationEntry = []
    this.isFirstApiHit = true;
  }

}
