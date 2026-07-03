import { Component, OnInit, ViewChild } from '@angular/core';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { ViewCheckinoutModalComponent } from './view-checkinout-modal/view-checkinout-modal.component';
import { ViewCheckinslipComponent } from './view-checkinslip/view-checkinslip.component';
import { EditCancelCheckinoutComponent } from './edit-cancel-checkinout/edit-cancel-checkinout.component';
import { CancelAllotmentComponent } from './cancel-allotment/cancel-allotment.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-checkin-checkout',
  templateUrl: './checkin-checkout.component.html',
  styleUrls: ['./checkin-checkout.component.scss'],
})
export class CheckinCheckoutComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;
  public categoryList = [];
  filterForm: FormGroup;
  backToTop: boolean = false;
  public infiniteScrollEnabled = true;
  selectGH = ''
  selectCity = ''
  selectedValue = '';
  getAllCityList = [];
  public headerName = 'Check-In Requests';



  public btnData = [
    { btnName: 'Checkout ', btnIcon: 'eye-outline', color: 'rgb(0, 120, 255)', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  ];
  public splitBtnClass = [
    { btnName: 'Check In', btnIcon: 'eye-outline', active: false },
    { btnName: 'Check Out', btnIcon: 'eye-outline', active: false },
    { btnName: 'Check In Slip', btnIcon: 'eye-outline', active: false },
  ]
  guestHouseList: any;
  checkInSlipdata = [];
  shiftStatus: any;
  shiftMsg: any;
  shiftId: any;
  checkOutdata = [];
  public pageNo = 1;
  public isFirstApiHit = true;
  public checkInData = [];
  public selectedGuest = []
  public finalGuestIds = []


  obj = {
    bookingId: "",
    cId: this.selectCity,
    category: "",
    fromDate: "",
    gId: this.selectGH,
    name: "",
    pageNo: 1
  }
  obj2 = {
    bookingId: "",
    cId: this.selectCity,
    gId: this.selectGH,
    pageNo: this.pageNo
  }

  constructor(public popoverController: PopoverController,
    private loadingController: LoadingController,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private commonService: CommonService,
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

  ngOnInit() { }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getCategoryList();
  }
  onIonInfinite(ev, selectedValue) {
    // if (this.totalPages !== this.obj.pageNum) {
    console.log("After Modal Close 2")
    console.log(this.infiniteScrollEnabled)
    if (this.infiniteScrollEnabled) {
      this.pageNo = this.pageNo + 1;
      if (selectedValue == 'Check In') {
        this.getCheckInData();
      }
      if (selectedValue == 'Check Out') {
        this.getCheckOutData();
      }
      if (selectedValue == 'Check In Slip') {
        this.getCheckInSlipList();
      }
      // this.getAllotmentList(this.obj);
      // }
      setTimeout(() => {
        (ev as InfiniteScrollCustomEvent).target.complete();
      }, 1500);
    }
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
  public getbutton(btn, data) {
    if (btn == 'CIN') {
      console.log(this.selectedGuest.length);
      if (this.selectedGuest.length <= 0) {
        this.commonService.showErrorToast("Please select atleast one guest")
        return
      } else if (this.selectedGuest.length >= 1) {
        let finalGuestIds = []
        let finalGuests;
        this.selectedGuest.forEach(el => {
          if (el.bookingId == data.req_id) {
            finalGuestIds.push(el.guestId)
          }
        })
        if (finalGuestIds.length <= 0) {
          this.commonService.showErrorToast("Please select atleast one guest")
          return
        }

        finalGuests = {
          cId: this.selectCity,
          fromDate: data.from_date,
          gId: this.selectGH,
          hidval: finalGuestIds.map(x => x).join("','"),
          req_id: data.req_id,
          toDate: data.to_date
        }
        console.log(finalGuests)

        this.goToCheckInView(finalGuests, btn)

      }
    }
    if (btn == 'CANALO') {
      this.viewAllotmentRoomByReqId(data)
    }
    if (btn == 'COUT') {
      // console.log(data);
      if (this.selectedGuest.length <= 0) {
        this.commonService.showErrorToast("Please select all guest")
        return;
      } else if (this.selectedGuest.length >= 1) {
        this.finalGuestIds = []
        let finalGuests;
        this.selectedGuest.forEach(el => {
          if (el.bookingId == data.req_id) {
            this.finalGuestIds.push(el.guestId)
          }
        })
        if (this.finalGuestIds.length <= 0) {
          this.commonService.showErrorToast("Please select atleast one guest")
          return;
        }
        console.log("finalGuestIds", this.finalGuestIds, this.finalGuestIds.length)
        let gList = []
        for (let i = 0; i < data.guestDetails.length; i++) {
          if (data.guestDetails[i].status == 'CIN') {
            gList.push(data.guestDetails[i])
            console.log(gList)
          }
        }
        console.log(this.finalGuestIds)
        console.log("selectedGuest", this.finalGuestIds.length, gList.length)
        if (this.finalGuestIds.length !== gList.length) {
          this.commonService.showErrorToast("Please select all guest")
          return;
        } else {
          finalGuests = {
            ghId: this.selectCity,
            blockId: this.selectGH,
            reqId: data.req_id,
            hidval: this.finalGuestIds.map(x => x).join("','"),
            frmdata: data.from_date,
            toData: data.to_date
          }
          // console.log(finalGuests)

          this.goToCheckOutView(finalGuests, btn)
        }
      }
    }
    if (btn == 'CINSLIP') {
      this.viewCheckSlip(data);
    }
    if (btn == 'EditCIN' || btn == 'CANCIN') {
      console.log(this.selectedGuest)
      if (this.selectedGuest.length <= 0) {
        this.commonService.showErrorToast("Please select atleast one guest")
        return;
      } else if (this.selectedGuest.length >= 1) {
        this.finalGuestIds = []
        let finalGuests;
        this.selectedGuest.forEach(el => {
          if (el.bookingId == data.req_id) {
            this.finalGuestIds.push(el.guestId)
          }
        })
        if (this.finalGuestIds.length <= 0) {
          this.commonService.showErrorToast("Please select atleast one guest")
          return;
        }
        finalGuests = {
          ghId: this.selectCity,
          blockId: this.selectGH,
          reqId: data.req_id,
          hidval: this.finalGuestIds.map(x => x).join("','"),
          fromDate: data.from_date,
          toDate: data.to_date
        }
        console.log(finalGuests)

        this.showCheckInDetails(finalGuests, btn)
      }
    }
  }

  public async showCheckInDetails(finalGuests, btn) {
    const modal = await this.modalcontroller.create({
      component: EditCancelCheckinoutComponent,
      componentProps: { finalGuests, btn }
    });
    modal.onDidDismiss().then(async (data) => {
      this.resetPaginationData();
      this.infiniteScrollEnabled = false;
      await this.getCheckOutData();
      this.infiniteScrollEnabled = true;
    })
    return await modal.present();
  }

  public async viewCheckSlip(trId) {
    let data = {
      "cId": this.selectCity,
      "gId": this.selectGH,
      "trId": trId
    }
    const modal = await this.modalcontroller.create({
      component: ViewCheckinslipComponent,
      componentProps: { data }
    });
    modal.onDidDismiss().then(async (data) => {
      this.resetPaginationData()
      this.infiniteScrollEnabled = false;
      await this.getCheckInSlipList();
      this.infiniteScrollEnabled = true;

    })
    return await modal.present();
  }

  public async goToCheckOutView(data, btnType) {
    const modal = await this.modalcontroller.create({
      component: ViewCheckinoutModalComponent,
      componentProps: { data, btnType }
    });
    modal.onDidDismiss().then(async (data) => {
      console.log("dhfdjhfjdhf")
      this.resetPaginationData();
      this.infiniteScrollEnabled = false;
      await this.getCheckOutData();
      this.infiniteScrollEnabled = true;
      this.finalGuestIds = [];
      this.selectedGuest = [];
      console.log("onModalDissmiss", this.finalGuestIds)
    })
    return await modal.present();
  }

  public async goToCheckInView(data, btnType) {
    const modal = await this.modalcontroller.create({
      component: ViewCheckinoutModalComponent,
      componentProps: { data, btnType, shiftId: this.shiftId }
    });
    modal.onDidDismiss().then(async (data) => {
      // await modal.onWillDismiss();
      this.resetPaginationData()
      this.infiniteScrollEnabled = false;
      console.log("After Modal Close 1")
      await this.getCheckInData();
      this.infiniteScrollEnabled = true;
      this.finalGuestIds = [];
      this.selectedGuest = [];
      // console.log("onModalDissmiss", this.finalGuestIds)
    })
    return await modal.present();
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
    this.finalGuestIds = [];
    this.selectedGuest = [];
    this.isFirstApiHit = true;
    this.pageNo = 1;
    this.obj.pageNo = 1;
    this.checkInData = [];
    this.checkOutdata = [];
    this.checkInSlipdata = [];
    this.infiniteScrollEnabled = true;
  }
  public async getButtonName(btnName) {
    this.resetPaginationData();
    console.log("btn is", btnName);
    if (btnName === 'Check In') {
      this.obj.bookingId = "",
        this.obj.category = "",
        this.obj.name = "",
        this.obj.pageNo = 1
      this.checkInData = []

      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btnName;
        this.headerName = 'Check-In Requests'
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftId = res.data.shiftId;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getCheckInData();
            } else if (this.shiftStatus == false) {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: this.shiftMsg,
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
    if (btnName === 'Check Out') {

      this.obj.bookingId = "",
        this.obj.cId = "",
        this.obj.category = "",
        this.obj.gId = "",
        this.obj.name = "",
        this.obj.pageNo = 1
      this.checkOutdata = []

      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = btnName;
        this.headerName = 'Check-Out Requests'
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getCheckOutData();
            } else if (this.shiftStatus == false) {
              const alert = await this.alertController.create({
                cssClass: 'my-custom-class',
                message: this.shiftMsg,
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
    if (btnName === 'Check In Slip') {

      this.obj2.bookingId = "",
        this.obj2.cId = "",
        this.obj2.gId = "",
        this.obj2.pageNo = 1
      this.checkInSlipdata = []
      //check in slip
      this.selectedValue = btnName;
      this.headerName = 'Check-In Slip'
      this.getCheckInSlipList()
    }
  }

  public async getCheckInSlipList() {

    // this.obj2.bookingId = "",
      this.obj2.cId = this.selectCity,
      this.obj2.gId = this.selectGH,
      this.obj2.pageNo = this.pageNo
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.checkInSlipDetails(this.obj2).subscribe(
      res => {
        this.checkInSlipdata = this.checkInSlipdata.concat(res.data.Data.data);
        if (this.isFirstApiHit) {
          loading.dismiss();
          this.isFirstApiHit = false;
        }
        console.log("Check In Dta", this.checkInSlipdata);
      },
      err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      () => {
        loading.dismiss()
      })
  }

  public async getGuestHouseList(event) {
    this.selectGH = ''
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    console.log("city name is", event.target.value);
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(
      res => {
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
      }
    )
  }

  public async getCheckInData() {
    this.obj.cId = this.selectCity,
      this.obj.gId = this.selectGH,
      this.obj.pageNo = this.pageNo
    console.log(this.obj.pageNo, this.obj, "&&&&&&&&&" + this.pageNo)
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getCheckinDetailsForMobile(this.obj).subscribe(
      res => {
        if (res.status == 1) {
          this.checkInData = this.checkInData.concat(res.data.data);
          console.log(this.checkInData)
          if (this.isFirstApiHit) {
            loading.dismiss();
            this.isFirstApiHit = false;
          }
        } else {
          if (this.isFirstApiHit) {
            loading.dismiss();
          }
        }
      },
      err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
      },
      () => {
        loading.dismiss()
      }
    )

  }

  public selectGuest(event, bookingId, guestData) {
    if (event.target.checked) {
      this.selectedGuest.push({
        bookingId: bookingId,
        guestId: guestData.guestId
      });
    }

    if (!event.target.checked) {
      this.selectedGuest.forEach((ele, ind) => {
        if (ele.bookingId == bookingId) {
          this.selectedGuest.splice(ind, 1);
        }
      })
    }

    console.log(this.selectedGuest)
  }

  public selectGuestForCout(event, bookingId, guestData) {
    if (event.target.checked) {
      if (guestData.status == 'CIN') {
        this.selectedGuest.push({
          bookingId: bookingId,
          guestId: guestData.guestId
        });
      }
    }

    if (!event.target.checked) {
      this.selectedGuest.forEach((ele, ind) => {
        if (ele.bookingId == bookingId) {
          this.selectedGuest.splice(ind, 1);
        }
      })
    }

    console.log(this.selectedGuest)
  }

  public async getCheckOutData() {
    // this.obj.fromDate = "",
    //   this.obj.bookingId = "",
    //   this.obj.category = "",
    //   this.obj.name = "",
      // console.log("getCheckInSlip")
      // this.obj.cId = this.selectCity,
      //   this.obj.gId = this.selectGH,

      //   this.obj.pageNo = this.pageNo
      this.obj.cId = this.selectCity,
      this.obj.gId = this.selectGH,

      this.obj.pageNo = this.pageNo
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.checkOutDetails(this.obj).subscribe(
      res => {
        this.checkOutdata = this.checkOutdata.concat(res.data.data);
        if (this.isFirstApiHit) {
          loading.dismiss();
          this.isFirstApiHit = false;
        }
        console.log("CheckOut Dta", this.checkOutdata);
      }, err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
      }, () => {
        loading.dismiss();
      })
  }

  public async viewAllotmentRoomByReqId(data) {
    const obj = {
      cId: this.selectCity,
      gId: this.selectGH,
      reqId: data.req_id
    }
    const modal = await this.modalcontroller.create({
      component: CancelAllotmentComponent,
      componentProps: { obj }
    });
    modal.onDidDismiss().then(async (data) => {
      this.resetPaginationData()
      this.infiniteScrollEnabled = false;
      await this.getCheckInData();
      this.infiniteScrollEnabled = true;
      this.finalGuestIds = [];
      this.selectedGuest = [];
      console.log("onModalDissmiss", this.finalGuestIds)
    })
    return await modal.present();

    // const loading = await this.loadingController.create({
    //   message: 'Loading...'
    // });
    // loading.present();
    // this.mdmService.viewAllotmentRoomByReqIdForMobile(obj).subscribe(
    //   res => {
    //     this.checkInSlipdata = res.data.Data.data;
    //     loading.dismiss();
    //     console.log("Check In Dta", this.checkInSlipdata);
    //   },
    //   err => {
    //     loading.dismiss();
    //     this.commonService.showErrorToast("Something Went Wrong.")
    //   })
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

  ionViewDidLeave(): void {
    console.log("entered")
    this.resetPaginationData();
    this.selectedGuest = [];
    this.finalGuestIds = [];
  }

  // ionViewWillLeave() {
  //   this.finalGuestIds = []
  //   console.log("ionViewWillLeave", this.finalGuestIds)
  // }
  public openFilterModal() {
    if (this.selectCity === '' && this.selectGH === '') {

      this.commonService.showErrorToast('Provide City and Guest House');
    }
    else {
      console.log("in else block")
      this.createFormInstance();
      this.filtermodal.isOpen = true;
    }
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
      bookingId: [''],
      cId: [''],
      gId: [''],
      name: [''],
      pageNo: this.pageNo,
      category: [''],
      fromDate: [''],

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

  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingId === '' && data.cId === '' && data.gId === '' && data.name === '' && data.category === '' && data.fromDate === '') {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }
    if (data.fromDate) {
      data.fromDate = this.changeDateFormat(data.fromDate);
    }

    this.obj = {
      bookingId: data.bookingId,
      category: data.category,
      fromDate: data.fromDate,
      cId: this.selectCity,
      gId: this.selectGH,
      name: data.name,
      pageNo: 1
    }
    this.obj2 = {
      bookingId: data.bookingId,
      cId: this.selectCity,
      gId: this.selectGH,
      pageNo: 1
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    if (this.selectedValue == 'Check In') {
      this.mdmService.getCheckinDetailsForMobile(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.checkInData = res.data.data;
          console.log(this.checkInData);
          loading.dismiss();
          this.filtermodal.isOpen = false;
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

    else if (this.selectedValue == 'Check Out') {
      this.mdmService.checkOutDetails(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.checkOutdata = res.data.data;
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
    else {
      this.mdmService.checkInSlipDetails(this.obj2).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.checkInSlipdata = res.data.Data.data;
          this.filtermodal.isOpen = false;
        }
        loading.dismiss();
      }, error => {
        loading.dismiss();
      },
        () => {
          loading.dismiss()
        })
    }
  }

  handleRefresh(event) {
    this.resetPaginationData()
    setTimeout(() => {
      if (this.selectedValue == 'Check In') {
        this.obj.fromDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.name = "",
          this.obj.pageNo = 1
        this.checkInData = []
        this.getCheckInData();
        this.isFirstApiHit = true
        event.target.complete();

      }
      else if (this.selectedValue == 'Check Out') {
        this.obj.fromDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.name = "",
          this.obj.pageNo = 1
        this.checkOutdata = []
        this.getCheckOutData();
        this.isFirstApiHit = true
        event.target.complete();
      }
      else if (this.selectedValue == 'Check In Slip') {
        this.obj2.bookingId = "",
          this.obj2.cId = "",
          this.obj2.gId = "",
          this.obj2.pageNo = 1
        this.checkInSlipdata = []
        this.getCheckInSlipList();
      } else {}
      event.target.complete();
    }, 1000);
  }
  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }

}
