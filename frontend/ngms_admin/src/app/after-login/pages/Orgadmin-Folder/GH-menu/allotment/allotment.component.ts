import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { AddchangecanAllotmentComponent } from './addchangecan-allotment/addchangecan-allotment.component';
import { AllotmentCancelComponent } from './allotment-cancel/allotment-cancel.component';
import { PreviewAllComponent } from './preview-all/preview-all.component';
import { ViewDetailsComponent } from './view-details/view-details.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-allotment',
  templateUrl: './allotment.component.html',
  styleUrls: ['./allotment.component.scss'],
})
export class AllotmentComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public categoryList = [];
  applicationEntry = [];
  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    { btnName: 'Delete', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
    // { btnName: 'Extend', btnIcon: 'documents-outline', color: '#FF7B01', background: '#FFF2CC' },
    // { btnName: 'Feedback', btnIcon: 'mail-outline', color: '#0FA958', background: '#DAFEFE' },

  ];
  public splitBtnClass = [
    { btnName: 'Allotment', btnIcon: 'eye-outline' },
    { btnName: 'Preview', btnIcon: 'eye-outline' },
    { btnName: 'Change', btnIcon: 'eye-outline' },
    { btnName: 'Cancel', btnIcon: 'eye-outline' },
    { btnName: 'Modify', btnIcon: 'eye-outline' },
    // { btnName: 'Moderation List', btnIcon: 'eye-outline' },
  ]
  selectedValue = '';
  private isFirstApiHit = true;
  guestHouseList: any;
  selectGH = ''
  selectCity = ''
  headingName = 'Room Allotment List'
  approveStatus: any;
  backToTop: boolean = false;
  public pageSize = 5;
  public pageNum = 1;
  public totalPages;
  public city: string;
  public guestHouse: string;
  modList = []
  obj = {
    bookingDate: "",
    bookingId: "",
    cId: "01",
    category: "",
    gId: "01",
    mobNo: "",
    name: "",
    purpose: "",
    roomType: "",
    type: "",
    pageSize: this.pageSize,
    pageNum: this.pageNum,
    pageNo: ""
  }


  allotmentList = [];
  changeRoomList = [];
  modifyAllotmentList: any;
  cancelReservLis = [];
  previewProcessList = []
  previewCancelledList = []
  previewConferenceList = []
  previewModifiedList = []
  previewConveyanceList = []
  public getAllCityList = [];
  constructor(
    private popoverController: PopoverController,
    private loadingController: LoadingController,
    private mdmService: MdmService,
    private platform: Platform,
    private alertController: AlertController,
    private modalcontroller: ModalController,
    private commonservice: CommonService,
    private fb: FormBuilder) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function (event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.filtermodal.isOpen = false;
      processNextHandler();
    });
  }

  ngOnInit() { }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getCategoryList();
  }

  ionViewDidLeave(): void {
    this.resetPaginationData();
  }

  onIonInfinite(ev, selectedValue) {
    this.obj.pageSize = 5;
    // if (this.totalPages !== this.obj.pageNum) {
    this.obj.pageNum = this.obj.pageNum + 1;
    if (selectedValue == 'Allotment') {
      this.getAllotmentList();
    }
    if (selectedValue == 'Change') {
      this.getChangeRoomList();
    }
    if (selectedValue == 'Modify') {
      this.getModifyAllotmentList();
    }
    // this.getAllotmentList(this.obj);
    // }
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
  }

  // getCityList() {
  //   this.mdmService.getCityDataTableForMobile().subscribe(res => {
  //     console.log(res);
  //     if (res.status === 1) {
  //       this.getAllCityList = res.data;
  //     }
  //   }, error => {
  //     console.log(error);
  //   })
  // }

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  // handleRefresh(event) {
  //   this.resetPaginationData()
  //   setTimeout(() => {
  //     this.getAllotmentList()
  //     event.target.complete();
  //   }, 2000);
  // }

  handleRefresh(event) {
    setTimeout(() => {

      if (this.selectedValue == 'Allotment') {
        this.obj.bookingDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.mobNo = "",
          this.obj.purpose = "",
          this.obj.name = "",
          this.obj.roomType = "",
          this.obj.pageNum = 1;
        this.obj.pageSize = 5;
        this.obj.type = "allotmentList"
        this.allotmentList = []
        this.getAllotmentList();
        this.isFirstApiHit = true
        event.target.complete();
      }
      else if (this.selectedValue == 'Modify') {
        this.obj.bookingDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.mobNo = "",
          this.obj.purpose = "",
          this.obj.name = "",
          this.obj.roomType = "",
          this.obj.pageNum = 1;
        this.obj.pageSize = 5;
        this.obj.type = "modifyList"
        this.modifyAllotmentList = []
        this.getModifyAllotmentList()
        this.isFirstApiHit = true
        event.target.complete();
      }
      else if (this.selectedValue == 'Change') {
        this.obj.bookingDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.mobNo = "",
          this.obj.purpose = "",
          this.obj.name = "",
          this.obj.roomType = "",
          this.obj.pageNum = 1;
        this.obj.pageSize = 5;
        this.obj.type = "changeList"
        this.changeRoomList = []
        this.getChangeRoomList()
        this.isFirstApiHit = true
        event.target.complete();
      }

    }, 500);
  }


  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  async getGuestHouseList(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    const selectedCity = this.getAllCityList.find(city => city.cityId === event.target.value);
    if (selectedCity) {
      const cityName = selectedCity.cityName;
      this.city = cityName;
    }
    console.log("city name is", event.target.value);
    this.selectGH = ''
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      console.log("result is", res);
      this.guestHouseList = res.data;
      if (this.guestHouseList.length == 1) {
        console.log("Inside IF Condition")
        console.log(res.data)
        this.selectGH = res.data[0].id;
      }
      loading.dismiss()
    },
      err => {
        loading.dismiss()
      },
      () => {
        loading.dismiss()
      })
  }
  getBhawanName(event) {
    const selectedGhouse = this.guestHouseList.find(gHouse => gHouse.id === event.target.value);
    if (selectedGhouse) {
      const gHouseName = selectedGhouse.name;
      this.guestHouse = gHouseName;
    }
    console.log("gh name", event.target.value);
    console.log(this.guestHouse);
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

  public resetPaginationData() {
    console.log("resetPaginationData")
    this.isFirstApiHit = true;
    this.obj.pageSize = 5;
    this.obj.pageNum = 1;
    this.changeRoomList = [];
    this.allotmentList = [];
    this.cancelReservLis = [];
    this.modifyAllotmentList = [];
  }

  async getButtonName(btn) {
    console.log("getButtonName")
    this.resetPaginationData()
    if (btn.btnName == 'Allotment') {
      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.category = "",
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.obj.type = "allotmentList"
      this.allotmentList = []
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = 'Room Allotment List';
        console.log("button is", btn.btnName);
        this.mdmService.getCheckForShifta(this.selectCity, this.selectGH).subscribe(
          async res => {
            let msg = res.data.message;
            this.approveStatus = res.data.status;
            if (this.approveStatus == true) {
              this.getAllotmentList();
            } else if (this.approveStatus == false) {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: msg,
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            } else {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: 'Please open shift',
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            }
          })
      }
    }

    if (btn.btnName == 'Change') {
      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.category = "",
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.obj.type = "changeList"
      this.changeRoomList = []
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = 'Change Allotment List';
        console.log("button is", btn.btnName);
        this.mdmService.getCheckForShifta(this.selectCity, this.selectGH).subscribe(
          async res => {
            let msg = res.data.message;
            this.approveStatus = res.data.status;
            if (this.approveStatus == true) {
              this.getChangeRoomList();
            } else if (this.approveStatus == false) {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: msg,
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            } else {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: 'Please open shift',
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            }
          })
      }
    }
    if (btn.btnName == 'Cancel') {
      const status = await this.checkCityAndGh()
      console.log(status)

      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = 'Cancel List';
        console.log("button is", btn.btnName);
        this.mdmService.getCheckForShifta(this.selectCity, this.selectGH).subscribe(
          async res => {
            let msg = res.data.message;
            this.approveStatus = res.data.status;
            if (this.approveStatus == true) {
              this.getCancelReservationList();
            } else if (this.approveStatus == false) {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: msg,
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            } else {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: 'Please open shift',
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            }
          })
      }
    }
    if (btn.btnName == 'Modify') {
      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.category = "",
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.obj.type = "modifyList"
      this.modifyAllotmentList = []

      const status = await this.checkCityAndGh()
      console.log(status)

      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = 'Modify Allotment List';
        console.log("button is", btn.btnName);
        this.mdmService.getCheckForShifta(this.selectCity, this.selectGH).subscribe(
          async res => {
            let msg = res.data.message;
            this.approveStatus = res.data.status;
            if (this.approveStatus == true) {
              this.getModifyAllotmentList();
            } else if (this.approveStatus == false) {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: msg,
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            } else {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: 'Please open shift',
                buttons: [
                  {
                    text: 'OK',
                    handler: () => {
                    },
                  },
                ],
              });
              await alert.present();
              return;
            }
          })
      }
    }
    if (btn.btnName == 'Preview') {
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btn.btnName;
        this.headingName = 'Preview';
        console.log("button is", btn.btnName);
        this.allProcessApplicationListmoderatorView();
        this.getAllCancelDataForApproveAllotTime();
        this.getAllConferenceDataForApprove();
        this.getAllModifiedDataForApproveAllotTime();
        this.getAllotTimeAllConveyanceDataForApprove();
      }
    }
    if (btn.btnName == 'Moderation List') {
      this.selectedValue = btn.btnName;
      this.headingName = 'Moderation List';
      console.log("button is", btn.btnName);
      this.getModListForView();
    }
  }
  // async getAllotmentList() {
  //   this.obj.type = 'allotmentList'
  //   this.obj.cId = this.selectCity;
  //   this.obj.gId = this.selectGH;
  //   const loading = await this.loadingController.create({
  //     message: 'Loading...'
  //   });
  //   loading.present();
  //   this.mdmService.getAllotmentList(this.obj).subscribe(
  //     res => {
  //       loading.dismiss();
  //       this.allotmentList = this.allotmentList.concat(res.data)
  //       this.totalPages = res.data.totalPages;
  //       console.log(res)
  //     }, err => {
  //       loading.dismiss();
  //       this.commonservice.showErrorToast("Something Went Wrong!");
  //     })
  // }
  async getAllotmentList() {
    this.obj.type = 'allotmentList';
    this.obj.cId = this.selectCity;
    this.obj.gId = this.selectGH;
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }

    this.mdmService.getAllotmentList(this.obj).subscribe(
      res => {
        this.allotmentList = this.allotmentList.concat(res.data);
        this.totalPages = res.data.totalPages;
        console.log(res);
        if (this.isFirstApiHit) {
          loading.dismiss();
          this.isFirstApiHit = false;
        }
      },
      err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
        this.commonservice.showErrorToast("Something Went Wrong!");
      },
      () => {
        loading.dismiss();
      }
    );
  }
  async getChangeRoomList() {
    this.obj.type = 'changeList'
    this.obj.cId = this.selectCity;
    this.obj.gId = this.selectGH;
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAllotmentList(this.obj).subscribe(
      res => {
        this.changeRoomList = this.changeRoomList.concat(res.data);
        this.totalPages = res.data.totalPages;
        if (this.isFirstApiHit) {
          loading.dismiss();
          this.isFirstApiHit = false;
        }
        // console.log(res)
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!");
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
      },
      () => {
        loading.dismiss();
      })
  }
  async getCancelReservationList() {
    const obj = {
      cId: this.selectCity,
      gId: this.selectGH,
      type: "ALO"
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCancelReservationist(obj).subscribe(res => {
      this.cancelReservLis = res.data;
      loading.dismiss()
    }, err => {
      loading.dismiss();
      this.commonservice.showErrorToast("Something Went Wrong!")
    },
      () => {
        loading.dismiss();
      })
  }
  async getModifyAllotmentList() {
    this.obj.type = 'modifyList'
    this.obj.cId = this.selectCity;
    this.obj.gId = this.selectGH;
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAllotmentList(this.obj).subscribe(
      res => {
        this.modifyAllotmentList = this.modifyAllotmentList.concat(res.data);
        if (this.isFirstApiHit) {
          loading.dismiss();
          this.isFirstApiHit = false;
        }
      }, err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
        this.commonservice.showErrorToast("Something Went Wrong!")
      },
      () => {
        loading.dismiss();
      })
  }
  async getAllotmentCancel(data, type) {
    // this.router.navigate(['after-login/pages/orgadmin/GH-menu/allotment/allotmentCancel'], { queryParams: { cancelReservation: JSON.stringify(data) } });
    // this.router.navigate(['after-login/pages/orgadmin/GH-menu/allotment/allotmentCancel'],{queryParams:{type:type}});
    const modal = await this.modalcontroller.create({
      component: AllotmentCancelComponent,
      componentProps: { data, type }
    });
    modal.onDidDismiss().then((data) => {
      this.getCancelReservationList();
    })
    return await modal.present();

    // this.commonservice.setAllotmentCancelData(data);
  }
  async checkForShift() {
    console.log(" city gh", this.selectCity, this.selectGH);
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCheckForShifta(this.selectCity, this.selectGH).subscribe(res => {
      this.approveStatus = res.status
      loading.dismiss()
    }, err => {
      loading.dismiss();
      this.commonservice.showErrorToast("Something Went Wrong!")
    },
      () => {
        loading.dismiss();
      })

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


  public async getDormitoryDetails(data) {
    let status;

    return new Promise((resolve, reject) => {
      this.mdmService.getTotalBedsForMobile(data.bookingId, this.selectCity, this.selectGH).subscribe(
        res => {
          if (res.status == 1) {
            console.log(res);
            let bedDetails = res.data;
            if (bedDetails.roomNos.length == 0) {
              resolve(false);
            } else {
              resolve(true);
            }
          } else {
            this.commonservice.showErrorToast(res.statusDesc);
            resolve(false);
          }
        });
    });
  }

  async allot(data, type) {
    let cityAndGhIds = {
      cityId: this.selectCity,
      ghId: this.selectGH
    }

    console.log(data.type)
    if (type == 'allot') {
      const goToDormitory = await this.getDormitoryDetails(data);
      console.log("getDormitoryDetails")
      console.log(goToDormitory)

      if (data.type != 'Room' && !goToDormitory) {
        this.commonservice.showErrorToast("No beds available in this guest house.")
        return
      } else {
        const modal = await this.modalcontroller.create({
          component: AddchangecanAllotmentComponent,
          componentProps: { data, type: 'allot', cityAndGhIds }
        });
        modal.onDidDismiss().then((data) => {
          this.obj.pageSize = 5;
          this.obj.pageNum = 1;
          this.allotmentList = [];
          this.isFirstApiHit = true;
          this.getAllotmentList()
        })
        return await modal.present();
      }
    }

    if (type == 'changeAllot') {
      const modal = await this.modalcontroller.create({
        component: AddchangecanAllotmentComponent,
        componentProps: { data, type: 'change', cityAndGhIds }
      });
      modal.onDidDismiss().then((data) => {
        this.obj.pageSize = 5;
        this.obj.pageNum = 1;
        this.changeRoomList = [];
        this.isFirstApiHit = true;
        this.getChangeRoomList();
      })
      return await modal.present();
    }
    if (type == 'modify') {
      const modal = await this.modalcontroller.create({
        component: AddchangecanAllotmentComponent,
        componentProps: { data, type: 'modify', cityAndGhIds }
      });
      modal.onDidDismiss().then((data) => {
        this.obj.pageSize = 5;
        this.obj.pageNum = 1;
        this.pageSize = 5;
        this.pageNum = 1;
        this.isFirstApiHit = true;
        this.modifyAllotmentList = []
        this.getModifyAllotmentList();
      })
      return await modal.present();
    }

    if (type == 'cancel') {
      const alert = await this.alertController.create({
        cssClass: 'my-custom-class',
        header: 'Are you sure?',
        message: 'Are you sure to cancel the Allotment?',
        buttons: [
          {
            text: 'Yes',
            handler: () => {
              this.cancelAllotment(data);
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
  }

  public async cancelAllotment(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.cancelAllotmentForMobile(data.bookingId, data.modId, data.mrrId).subscribe(
      res => {
        if (res.status == 1) {
          this.commonservice.showSuccessToast(res.statusDesc)
          this.obj.pageSize = 5;
          this.obj.pageNum = 1;
          if (this.selectedValue == 'Allotment') {
            this.allotmentList = []
            this.getAllotmentList();
          } else {
            this.changeRoomList = []
            this.getChangeRoomList();
          }
          loading.dismiss();
        } else {
          this.commonservice.showErrorToast(res.statusDesc)
          loading.dismiss();
        }
      },
      err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss();
      },
      () => {
        loading.dismiss();
        // this.obj.pageSize = 5;
        // this.obj.pageNum = 1;
        // this.changeRoomList = [];
        // this.allotmentList = [];
        // this.getAllotmentList();
        // this.getChangeRoomList();
      }
    )
  }

  public async allProcessApplicationListmoderatorView() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.allProcessApplicationListmoderatorViewForMobile(this.selectCity, this.selectGH).subscribe(
      res => {
        if (res.status == 1) {
          this.previewProcessList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      }, err => {
        loading.dismiss()
        this.commonservice.showErrorToast("Something Went Wrong!")
      }, () => {
        loading.dismiss()
      }
    )
  }
  public async getAllCancelDataForApproveAllotTime() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAllCancelDataForApproveAllotTimeForMobile(this.selectCity, this.selectGH).subscribe(
      res => {
        if (res.status == 1) {
          this.previewCancelledList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      }, err => {
        loading.dismiss()
        this.commonservice.showErrorToast("Something Went Wrong!")
      }, () => {
        loading.dismiss();
      }
    )
  }
  public async getAllConferenceDataForApprove() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAllConferenceDataForApproveForMobile(this.selectCity, this.selectGH).subscribe(
      res => {
        if (res.status == 1) {
          this.previewConferenceList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss()
      },
      () => {
        loading.dismiss();
      }
    )
  }
  public async getAllModifiedDataForApproveAllotTime() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAllModifiedDataForApproveAllotTimeForMobile(this.selectCity, this.selectGH).subscribe(
      res => {
        if (res.status == 1) {
          this.previewModifiedList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss()
      },
      () => {
        loading.dismiss()
      }
    )
  }
  public async getAllotTimeAllConveyanceDataForApprove() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAllotTimeAllConveyanceDataForApproveForMobile(this.selectCity, this.selectGH).subscribe(
      res => {
        if (res.status == 1) {
          this.previewConveyanceList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss()
      },
      () => {
        loading.dismiss()
      }
    )
  }

  public async getModListForView() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getModListForViewForMobile(this.selectCity, this.selectGH).subscribe(
      res => {
        if (res.status == 1) {
          this.modList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss()
      },
      () => {
        loading.dismiss();
      }
    )
  }

  public async viewAll() {
    let allPreviewData = {
      "processList": this.previewProcessList,
      "conveyanceList": this.previewConveyanceList,
      "modifiedList": this.previewModifiedList,
      "cacelledList": this.previewCancelledList,
      "conferenceList": this.previewConferenceList,
      "city": this.city,
      "guestHouse": this.guestHouse
    }
    let obj = {

    }
    const modal = await this.modalcontroller.create({
      component: PreviewAllComponent,
      componentProps: { allPreviewData }
    });
    return await modal.present();
  }

  public async goToViewDetails(bookingId, type) {
    const modal = await this.modalcontroller.create({
      component: ViewDetailsComponent,
      componentProps: { bookingId, viewType: type }
    });
    return await modal.present();
  }

  ionViewWillLeave() {
    this.selectGH = '';
    this.selectCity = '';
    this.obj.type = '';
    this.guestHouseList = ''
    this.selectedValue = '';
    this.approveStatus = '';
    this.isFirstApiHit = true;
  }

  //for filter
  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingDate === '' && data.bookingId === '' && data.cId === '' && data.category === '' && data.gId === '' && data.category === '' && data.gId === '' && data.mobNo === '' && data.purpose === '' && data.name === '' && data.roomType === '') {
      this.commonservice.showErrorToast('Provide any field for filter');
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
      roomType: data.roomType,
      pageSize: 5,
      pageNum: 1,
      type: '',
      pageNo: '',

    }

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    if (this.selectedValue == 'Allotment') {
      this.obj.type = 'allotmentList';
      this.mdmService.getAllotmentList(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.allotmentList = res.data;
          console.log(this.allotmentList);
          this.filtermodal.isOpen = false;
          loading.dismiss();
        } else {
          loading.dismiss()
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss();
        })
    }
    else if (this.selectedValue == 'Change') {
      this.obj.type = 'changeList';
      this.mdmService.getAllotmentList(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.changeRoomList = res.data;
          this.filtermodal.isOpen = false;
          loading.dismiss();
        } else {
          loading.dismiss()
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss()
        })
    }
    else if (this.selectedValue == 'Modify') {
      this.obj.type = 'modifyList';
      this.mdmService.getAllotmentList(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.modifyAllotmentList = res.data;
          this.filtermodal.isOpen = false;
          loading.dismiss();
        } else {
          loading.dismiss()
        }
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss()
        })
    }

  }

  public changeDateFormat(date) {
    const dateComponents = date.split("-");
    const year = dateComponents[0];
    const month = dateComponents[1];
    const day = dateComponents[2];
    const formattedDate = `${day}-${month}-${year}`;
    return formattedDate;

  }

  public openFilterModal() {
    if (this.selectCity === '' && this.selectGH === '') {
      this.commonservice.showErrorToast('Provide City and Guest House');
    }
    else {
      console.log("in else block")
      this.createFormInstance();
      this.filtermodal.isOpen = true;
    }
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
      pageSize: this.pageSize,
      pageNum: this.pageNum
    })
  }

  public getCategoryList() {
    this.mdmService.getCategoryData().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.categoryList = res.data;
      }
    })
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }

}
