import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { ModeratorSaveComponent } from './moderator-save/moderator-save.component';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/Services/common/common.service';
import { ModifyModerationComponent } from './modify-moderation/modify-moderation.component';
import { ModeratorCancelComponent } from './moderator-cancel/moderator-cancel.component';
import { ConferenceModComponent } from './conference-mod/conference-mod.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss'],
})
export class ModeratorComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public categoryList = [];
  public infiniteScrollEnabled = true;


  public splitBtnClass = [
    { btnName: 'Moderator', btnIcon: 'eye-outline', active: false },
    { btnName: 'Edit', btnIcon: 'eye-outline', active: false },
    { btnName: 'Cancel', btnIcon: 'eye-outline', active: false },
    { btnName: 'Modify', btnIcon: 'eye-outline', active: false },
    { btnName: 'Conference', btnIcon: 'eye-outline', active: false },
  ]
  public pageSize = 5;
  public totalPages;
  public pageNum = 1;
  private isFirstApiHit = true;

  obj = {
    bookingDate: "",
    bookingId: "",
    cId: "",
    category: "",
    gId: "",
    mobNo: "",
    name: "",
    purpose: "",
    reqType: "MA",
    roomType: "",
    pageSize: 5,
    pageNum: 1
  }

  obj2 = {
    bookingDate: "",
    bookingId: "",
    cId: "",
    category: "",
    gId: "",
    mobNo: "",
    name: "",
    // pageNo: 1,
    pageSize: 5,
    pageNum: 1,
    purpose: "",
    roomType: "",
    type: "modifyListMOD"
  }
  obj3 = {
    bookingDate: "",
    bookingId: "",
    cId: "",
    category: "",
    gId: "",
    mobNo: "",
    name: "",
    pageSize: 5,
    pageNum: 1
  }
  selectGH = ''
  selectCity = ''
  status;
  backToTop: boolean = false;
  selectedValue = 'Moderator';
  headingName = 'Moderator';
  applicationEntry = [];
  guestHouseList = [];
  cancellationList = [];
  modifyList = [];
  confList = [];
  public show = false;
  public getAllCityList = [];

  constructor(public popoverController: PopoverController,
    private loadingController: LoadingController,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private router: Router,
    private platform: Platform,
    private alertController: AlertController,
    private fb: FormBuilder) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function (event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.filtermodal.isOpen = false;
      processNextHandler();
    });
  }


  ngOnInit() {
  }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.pageSize = 5;
    this.getModerationList(this.obj),
      this.getCategoryList();
  }

  onIonInfinite(ev, type) {
    this.obj.pageSize = 5;
    if (this.infiniteScrollEnabled) {
      if (this.totalPages !== this.obj.pageNum) {
        this.obj.pageNum = this.obj.pageNum + 1;
        this.obj2.pageNum = this.obj2.pageNum + 1;
        this.obj3.pageNum = this.obj3.pageNum + 1;
        this.pageNum = this.pageNum + 1;
        if (type == 'Moderator') {
          this.getModerationList(this.obj);
        }
        if (type == 'Modify') {
          this.modifyModeration();
        }
      }

      if (type == 'Conference') {
        // this.pageNum = this.pageNum + 1;
        this.getConfListForModeration()
      }
      setTimeout(() => {
        (ev as InfiniteScrollCustomEvent).target.complete();
      }, 1500);
    }
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  handleRefresh(event) {
    this.resetPagination()
    setTimeout(() => {
      this.getModerationList(this.obj)
      event.target.complete();
    }, 2000);
  }


  filterHandleRefresh(event) {
    this.resetPagination()
    setTimeout(() => {
      if (this.selectedValue == 'Moderator') {
        this.obj.bookingDate = "",
          this.obj.bookingId = "",
          this.obj.cId = this.selectCity,
          this.obj.category = "",
          this.obj.gId = this.selectGH,
          this.obj.mobNo = "",
          this.obj.purpose = "",
          this.obj.name = "",
          this.obj.reqType = "MA",
          this.obj.pageNum = 1,
          this.obj.pageSize = 5,
          this.obj.roomType = "",
          this.applicationEntry = []
        this.getModerationList(this.obj);
        this.isFirstApiHit = true
        event.target.complete();
      }

      else if (this.selectedValue == 'Edit') {
        this.obj.bookingDate = "",
          this.obj.bookingId = "",
          this.obj.cId = this.selectCity,
          this.obj.category = "",
          this.obj.gId = this.selectGH,
          this.obj.mobNo = "",
          this.obj.purpose = "",
          this.obj.name = "",
          this.obj.reqType = "MM",
          this.obj.roomType = "",
          this.obj.pageNum = 1;
        this.obj.pageSize = 5;
        this.applicationEntry = []
        this.getModerationList(this.obj)
        this.isFirstApiHit = true
        event.target.complete();

      }
      else if (this.selectedValue == 'Modify') {
        this.obj2.bookingDate = "",
          this.obj2.bookingId = "",
          this.obj2.cId = "",
          this.obj2.category = "",
          this.obj2.gId = "",
          this.obj2.mobNo = "",
          this.obj2.purpose = "",
          this.obj2.name = "",
          this.obj2.roomType = "",
          this.obj2.type = "modifyList",
          this.obj2.pageNum = 1;
        this.obj2.pageSize = 5;
        this.modifyList = []
        this.modifyModeration()
        this.isFirstApiHit = true
        event.target.complete();

      }
      else if (this.selectedValue == 'Conference') {
        this.obj3.bookingDate = "",
          this.obj3.bookingId = "",
          this.obj3.cId = "",
          this.obj3.category = "",
          this.obj3.gId = "",
          this.obj3.mobNo = "",
          this.obj3.name = "",
          this.obj3.pageSize = 5,
          this.obj3.pageNum = 1
        this.confList = [];
        this.getConfListForModeration();
        this.isFirstApiHit = true
        event.target.complete();

      }

    }, 500);
  }




  gotToTop() {
    this.content.scrollToTop(1000);
  }
  getCityList() {
    this.mdmService.getCityDataTableForMobile().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.getAllCityList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  async getGuestHouseList(event) {
    if (event.target.value) {
      const loading = await this.loadingController.create({
        message: 'Please wait...'
      });
      loading.present();
      this.selectGH = ''
      this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(
        res => {
          if (res.status == 1) {
            this.guestHouseList = res.data;
            this.obj.cId = event.target.value;
            this.resetPagination()
            if (this.guestHouseList.length == 1) {
              console.log("Inside IF Condition")
              console.log(res.data)
              this.selectGH = res.data[0].id;
              // this.getModerationList(this.obj)
            } else {
              console.log("On Click City465467856438", this.applicationEntry)
              this.getModerationList(this.obj)
            }
            loading.dismiss();
          } else {
            loading.dismiss();
          }
        }, err => {
          loading.dismiss();
        })
    }
    // this.obj.cId = event.target.value;
    // this.getModerationList(this.obj)
  }
  getBhawanName(event) {
    this.obj.gId = event.target.value;
    this.resetPagination()
    if (event.target.value) {
      this.getModerationList(this.obj)
    }
  }

  public async checkCityAndGh() {
    if (this.selectCity == '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    if (this.selectGH == '') {
      const alert = await this.alertController.create({
        message: 'Please Select Guest house.',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    if (this.selectCity != '' && this.selectGH != '') {
      return Promise.resolve(true)
    }
  }

  // get 4 button
  async getButtonName(btn) {
    this.resetPagination()
    if (btn.btnName === 'Moderator') {

      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.category = "",
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        // this.obj.reqType = "MM",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.applicationEntry = []
      this.selectedValue = btn.btnName;
      this.headingName = this.selectedValue;
      console.log('button is', btn.btnName)
      console.log('selected is', this.selectedValue)
      this.obj.reqType = "MA";
      this.getModerationList(this.obj)
    }
    if (btn.btnName === 'Edit') {
      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.category = "",
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        // this.obj.reqType = "MA",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.applicationEntry = []

      this.selectedValue = btn.btnName;
      this.headingName = this.selectedValue + " Moderator";

      this.obj.reqType = "MM"
      this.getModerationList(this.obj)
    }

    if (btn.btnName == "Cancel") {
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = this.selectedValue + " Moderator";
        console.log('button is', btn.btnName)
        console.log('selected is', this.selectedValue)
        this.cancelReservationOfModerator()
      }
    }
    if (btn.btnName == "Modify") {
      this.obj2.bookingDate = "",
        this.obj2.bookingId = "",
        this.obj2.category = "",
        this.obj2.mobNo = "",
        this.obj2.purpose = "",
        this.obj2.name = "",
        this.obj2.type = "modifyList",
        this.obj2.pageNum = 1;
      this.obj2.pageSize = 5;
      this.modifyList = []
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = this.selectedValue + " Moderator";
        console.log('button is', btn.btnName)
        console.log('selected is', this.selectedValue)
        this.modifyModeration()
      }
    }
    if (btn.btnName == "Conference") {
      this.obj3.bookingDate = "",
        this.obj3.bookingId = "",
        this.obj3.category = "",
        this.obj3.mobNo = "",
        this.obj3.name = "",
        this.obj3.pageSize = 5,
        this.obj3.pageNum = 1
      this.confList = [];
      // this.getConfListForModeration();
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = this.selectedValue + " List";
        console.log('button is', btn.btnName)
        console.log('selected is', this.selectedValue)
        this.getConfListForModeration()
      }
    }
  }

  public async getConfListForModeration() {

    // this.obj3.bookingDate = "",
    //   this.obj3.bookingId = "",
    //   this.obj3.category = "",
    //   this.obj3.mobNo = "",
    //   this.obj3.name = "",
    this.obj3.cId = this.selectCity,
      this.obj3.gId = this.selectGH,
      this.obj3.pageSize = this.pageSize,
      this.obj3.pageNum = this.pageNum

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getConfListForModerationForMobile(this.obj3).subscribe(
      res => {
        if (res.status == 1) {
          if (this.isFirstApiHit) {
            console.log("loader  cvxv ")
            this.isFirstApiHit = false;
          }
          this.confList = this.confList.concat(res.data);
          loading.dismiss();
        } else {
          if (this.isFirstApiHit) {
            loading.dismiss();
          }
        }
      }, err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
      },
      () => {
        loading.dismiss();
      }
    )

  }
  // get Moderation list & Edit moderator
  public async getModerationList(objData) {
    this.obj.cId = this.selectCity,
      this.obj.gId = this.selectGH,
      this.obj.pageSize = this.pageSize,
      this.obj.pageNum = this.pageNum

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    console.log(objData)
    this.mdmService.getAllotmentListDetails(this.obj).subscribe(res => {
      if (!res.data.data) {
        this.show = true;
      }
      this.applicationEntry = this.applicationEntry.concat(res.data.data);
      this.totalPages = res.data.totalPages;
      console.log("applicationEntry", this.applicationEntry)
      if (this.isFirstApiHit) {
        loading.dismiss();
        this.isFirstApiHit = false;
      }
    }, err => {
      if (this.isFirstApiHit) {
        loading.dismiss();
      }
    },
      () => {
        loading.dismiss()
      })
  }
  // Cancel Moderation list
  async cancelReservationOfModerator() {
    const obj = {
      cId: this.selectCity,
      gId: this.selectGH,
      type: "MOD"
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getModerationCancellationList(obj).subscribe(res => {
      this.cancellationList = res.data;
      loading.dismiss()
    }, err => {
      loading.dismiss()
    },
      () => {
        loading.dismiss()
      })
  }
  // modify moderation
  async modifyModeration() {

    // this.obj2.bookingDate = "",
    //   this.obj2.bookingId = "",
    //   this.obj2.category = "",
    //   this.obj2.mobNo = "",
    //   this.obj2.purpose = "",
    //   this.obj2.name = "",
    //   this.obj2.roomType = "",
    //   this.obj2.bookingDate = "",

    //   // this.obj.bookingId = "",     
    //   // this.obj.category = "",
    //   // this.obj.mobNo = "",
    //   // this.obj.purpose = "",
    //   // this.obj.name = "",
    //   // this.obj.roomType="",

    this.obj2.cId = this.selectCity,
      this.obj2.gId = this.selectGH,
      this.obj2.pageSize = this.pageSize,
      this.obj2.pageNum = this.pageNum,
      this.obj2.type = "modifyListMOD"

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getModerationModifyList(this.obj2).subscribe(res => {
      this.modifyList = this.modifyList.concat(res?.data);
      console.log(this.modifyList)
      loading.dismiss();
    }, err => {
      loading.dismiss();
    },
      () => {
        loading.dismiss();
      })
  }

  // Edit moderation sub button (Delete & Edit)
  async getEditModButton(name, data) {
    console.log("edit btn Name", name)
    if (name == 'Delete') {
      const alert = await this.alertController.create({
        cssClass: 'alertCancel',
        header: 'Are you sure?',
        message: 'Are you sure to delete the moderation request !',
        buttons: [
          {
            text: 'Yes,Delete it',
            cssClass: 'alertButton',
            handler: () => {
              this.deleteModerator(data.bookingId)
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
  // Edit moderation sub button ==Delete
  async deleteModerator(id) {
    // const param = new HttpParams().set('reqId',id)
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.editModeratorCancel(id).subscribe(res => {
      if (res.status == 1) {
        loading.dismiss()
        this.commonService.showSuccessToast(res.statusDesc)
        this.obj.reqType = "MM"
        this.resetPagination()
        this.getModerationList(this.obj)
      } else {
        loading.dismiss()
        this.commonService.showErrorToast(res.statusDesc)
      }
    }, err => {
      loading.dismiss();
      this.commonService.showErrorToast("Something Went Wrong!")
    },
      () => {
        loading.dismiss();
      })
  }
  // Cancel moderation sub button (Cancel)
  async getCancelResButton(data, canType) {
    let canMsg;
    if (canType == 'CAN') {
      canMsg = "Are you sure to cancel the request?"
    } else if (canType == 'PCAN') {
      canMsg = "Are you sure to partial cancel?"
    }
    console.log("getCancelResButton", data);
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: canMsg,
      buttons: [
        {
          text: 'Yes,Cancel it',
          cssClass: 'alertButton',
          handler: () => {
            this.cancelReservation(data.bookingId, canType)
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

  async cancelReservation(bookingId, canType) {
    const loading = await this.loadingController.create({
      message: 'Please Wait...'
    });
    if (canType == 'CAN') {
      loading.present();
      this.mdmService.cancelReservationOfModerator(bookingId).subscribe(res => {
        if (res.status == 1) {
          loading.dismiss()
          this.commonService.showSuccessToast(res.statusDesc);
          this.cancelReservationOfModerator();
        } else {
          loading.dismiss()
          this.commonService.showErrorToast(res.statusDesc)
        }
      }, err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!")
      },
        () => {
          loading.dismiss()
        })
    }
    if (canType == 'PCAN') {
      loading.present();
      this.mdmService.partialCancelById(bookingId).subscribe(res => {
        if (res.status == 1) {
          loading.dismiss()
          this.commonService.showSuccessToast(res.statusDesc);
          this.cancelReservationOfModerator();
        } else {
          loading.dismiss()
          this.commonService.showErrorToast(res.statusDesc)
        }
      }, err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!")
      },
        () => {
          loading.dismiss();
        })
    }

  }
  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
  }

  public async openModal(data, btnName) {
    let type = data.typeOfRoom;
    if (btnName == 'save') {
      console.log("From Date", data.fromDate)
      const date = new Date();
      const hours = date.getHours();
      const minutes = date.getMinutes();
      let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;
      date.setDate(date.getDate())
      let currDate = date.toISOString().slice(0, 10);
      console.log(currDate)
      let fromDate = this.changeDateFormatyymmdd(data.fromDate.split(' ')[0])
      let fromTime = data.fromDate.slice(11, 16)
      // fromDate = fromDate + " " + fromTime

      console.log(fromDate)
      if (fromDate < currDate) {
        const alert = await this.alertController.create({
          message: 'Please Change the From date.',
          buttons: ['OK'],
        });
        await alert.present();
        return;
      }
      const modal = await this.modalcontroller.create({
        component: ModeratorSaveComponent,
        componentProps: { data, btnName, dataComingFrom: 'MOD', type }
      });
      modal.onDidDismiss().then(async (data) => {
        this.resetPagination()
        this.infiniteScrollEnabled = false;
        await this.getModerationList(this.obj)
        this.infiniteScrollEnabled = true;
      })
      return await modal.present();
    } else if (btnName == 'edit') {
      console.log(data.cityId)
      localStorage.removeItem("bookingDetails");
      localStorage.setItem("bookingDetails", JSON.stringify(data))
      // localStorage.setItem("currentLocation", 'after-login/pages/orgadmin/moderator')
      this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/modEdit'], { queryParams: { bhawan_Name: data.title } });

      // let url = 'after-login/pages/orgadmin/application-menu/application-entry/edit?city_id='+data.cityId;
      // this.router.navigate([url])
    } else if (btnName == 'updateMod') {
      const modal = await this.modalcontroller.create({
        component: ModeratorSaveComponent,
        componentProps: { data, btnName, type }
      });
      modal.onDidDismiss().then(async (data) => {
        this.resetPagination()
        this.infiniteScrollEnabled = false;
        await this.getModerationList(this.obj);
        this.infiniteScrollEnabled = true;
      })
      return await modal.present();
    } else if (btnName == 'CAN') {
      const modal = await this.modalcontroller.create({
        component: ModeratorCancelComponent,
        componentProps: { modData: data, dataComingFrom: 'Moderator' }
      });
      modal.onDidDismiss().then(async () => {
        // this.modalcontroller.dismiss();
        this.resetPagination()
        this.infiniteScrollEnabled = false;
        await this.getModerationList(this.obj)
        this.infiniteScrollEnabled = true;
      })
      return await modal.present();
    } else if (['confSave', 'confBill', 'confReceipt'].includes(btnName)) {
      const modal = await this.modalcontroller.create({
        component: ConferenceModComponent,
        componentProps: { data, btnName }
      });
      modal.onDidDismiss().then(async (data) => {
        this.resetPagination()
        this.infiniteScrollEnabled = false;
        await this.getConfListForModeration();
        this.infiniteScrollEnabled = true;
      })
      return await modal.present();
    }
  }


  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  private formatTimeComponent(component: number): string {
    return component < 10 ? `0${component}` : component.toString();
  }

  public async modifyAllotment(data, type) {
    let cityAndGhIds = {
      cityId: this.selectCity,
      ghId: this.selectGH
    }
    const modal = await this.modalcontroller.create({
      component: ModifyModerationComponent,
      componentProps: { data, type: 'Modify', cityAndGhIds }
    });
    modal.onDidDismiss().then(async (data) => {
      this.resetPagination()
      this.infiniteScrollEnabled = false;
      await this.modifyModeration();
      this.infiniteScrollEnabled = true;
    })
    return await modal.present();

  }

  ionViewDidLeave(): void {
    // this.selectGH = ''
    // this.selectCity = ''
    this.selectGH = ''
    this.selectCity = ''
    this.guestHouseList = []
    this.selectedValue = 'Moderator';
    this.headingName = 'Moderator';
    this.applicationEntry = []
    this.obj = {
      bookingDate: "",
      bookingId: "",
      cId: "",
      category: "",
      gId: "",
      mobNo: "",
      name: "",
      purpose: "",
      reqType: "MA",
      roomType: "",
      pageSize: 5,
      pageNum: 1
    }
    this.resetPagination()
    // this.getModerationList(this.obj)
  }

  public resetPagination() {
    this.show = false;
    this.obj.pageSize = 5;
    this.obj.pageNum = 1;
    this.applicationEntry = []
    this.isFirstApiHit = true;
    this.pageSize = 5;
    this.pageNum = 1;
    this.confList = [];
    this.modifyList = []
    this.infiniteScrollEnabled = true;
  }
  public openFilterModal() {
    this.createFormInstance();
    this.filtermodal.isOpen = true;
  }

  public getCategoryList() {
    this.mdmService.getCategoryData().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.categoryList = res.data;

      }
    })
  }

  public createFormInstance() {
    this.filterForm = this.fb.group({
      bookingDate: [''],
      bookingId: [''],
      cId: [''],
      category: [''],
      gId: [''],
      mobNo: [''],
      name: [''],
      purpose: [''],
      roomType: [''],
      reqType: ['AL'],
      pageSize: this.pageSize,
      pageNum: this.pageNum
    })
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public changeDateFormat(date) {
    const dateComponents = date.split("-");
    const year = dateComponents[0];
    const month = dateComponents[1];
    const day = dateComponents[2];
    const formattedDate = `${day}-${month}-${year}`;
    return formattedDate;

  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }

  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingDate === '' && data.bookingId === '' && data.cId === '' && data.category === '' && data.gId === '' && data.category === '' && data.gId === '' && data.mobNo === '' && data.purpose === '' && data.name === '' && data.roomType === '') {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }
    if (data.bookingDate) {
      data.bookingDate = this.changeDateFormat(data.bookingDate);
    }
    this.obj = {
      bookingDate: data.bookingDate,
      bookingId: data.bookingId,
      cId: this.selectCity,
      category: data.category,
      gId: this.selectGH,
      mobNo: data.mobNo.toString(),
      name: data.name,
      purpose: data.purpose,
      reqType: "",
      pageSize: 5,
      pageNum: 1,
      roomType: data.roomType
    }

    this.obj2 = {
      bookingDate: data.bookingDate,
      bookingId: data.bookingId,
      cId: this.selectCity,
      category: data.category,
      gId: this.selectGH,
      mobNo: data.mobNo.toString(),
      name: data.name,
      purpose: data.purpose,
      pageSize: 5,
      pageNum: 1,
      roomType: data.roomType,
      type: "modifyListMOD"
    }
    this.obj3 = {
      bookingDate: data.bookingDate,
      bookingId: data.bookingId,
      cId: this.selectCity,
      category: data.category,
      gId: this.selectGH,
      mobNo: data.mobNo.toString(),
      name: data.name,
      pageSize: 5,
      pageNum: 1

    }


    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    if (this.selectedValue == 'Moderator') {
      this.obj.reqType = 'MA';
      this.mdmService.getAllotmentListDetails(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.applicationEntry = res.data.data;
          console.log(this.applicationEntry);
          loading.dismiss();
          this.filtermodal.isOpen = false;
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss();
        })
    }
    else if (this.selectedValue == 'Edit') {
      this.obj.reqType = 'MM';
      this.mdmService.getAllotmentListDetails(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.applicationEntry = res.data.data;
          console.log(this.applicationEntry);
          loading.dismiss();
          this.filtermodal.isOpen = false;
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss();
        })

    }
    else if (this.selectedValue == 'Modify') {
      this.mdmService.getModerationModifyList(this.obj2).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.modifyList = res.data.data;
          loading.dismiss();
          this.filtermodal.isOpen = false;
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss();
        })
    }
    else if (this.selectedValue == 'Conference') {
      this.mdmService.getConfListForModerationForMobile(this.obj3).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.confList = res.data.data;
          loading.dismiss();
          this.filtermodal.isOpen = false;
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss()
        })
    }

  }

}
