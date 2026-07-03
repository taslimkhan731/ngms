import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { DomSanitizer } from '@angular/platform-browser';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { Route, Router } from '@angular/router';
import { ViewReceiptComponent } from './view-receipt/view-receipt.component';
import { MakeReceiptComponent } from './make-receipt/make-receipt.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.scss'],
})
export class ReceiptComponent implements OnInit {
  selectedValue = '';
  selectGH = '';
  selectCity = '';
  shiftStatus: any;
  shiftMsg: any;
  public recieptData = []
  public recieptList = [];
  getAllCityList = [];
  public pageSize = 5;
  public pageNum = 1;
  public isFirstApiHit = true;
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public categoryList = [];

  backToTop: boolean = false;

  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  ];
  public splitBtnClass = [
    { btnName: 'Receipt', btnIcon: 'eye-outline', active: false },
    { btnName: 'Receipt List', btnIcon: 'eye-outline', active: false },
  ]
  guestHouseList: any;
  headingName: string;
  public filterObj = {
    bookingId: "",
    cId: "",
    category: "",
    fromDate: "",
    gId: "",
    mobNo: "",
    name: "",
    type: "",
    pageSize: this.pageSize,
    pageNum: this.pageNum
  }


  constructor(public popoverController: PopoverController,
    private router: Router,
    private platform: Platform,
    private alertController: AlertController,
    private loadingController: LoadingController,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private fb: FormBuilder,
  ) {
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

  onIonInfinite(ev, selectedValue) {
    this.filterObj.pageSize = 5;
    // if (this.totalPages !== this.obj.pageNum) {
    this.filterObj.pageNum = this.filterObj.pageNum + 1;
    // this.filObject1.pageNum=this.filObject1.pageNum+1;
    if (selectedValue == 'Receipt') {
      this.getAllReceiptForMobile();
    }
    if (selectedValue == 'Receipt List') {
      this.getAllReceiptListForMobile();
    }
    // this.getAllotmentList(this.obj);
    // }
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
  }

  public resetPaginationData() {
    console.log("resetPaginationData")
    this.isFirstApiHit = true;
    this.filterObj.pageNum = 1;
    this.filterObj.pageSize = 5;
    this.recieptData = [];
    this.recieptList = [];
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
    const loading = await this.loadingController.create({
      message: 'Please wait..'
    })
    loading.dismiss()
    this.selectGH = ''
    console.log("city name is", event.target.value);
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(
      res => {
        console.log("result is", res);
        this.guestHouseList = res.data;
        loading.dismiss();
        if (this.guestHouseList.length == 1) {
          console.log("Inside IF Condition")
          console.log(res.data)
          this.selectGH = res.data[0].id;
        }
      },
      err => {
        loading.dismiss();
      },
      () => {
        loading.dismiss();
      })
  }

  getBhawanName() { }

  public async getbutton(btn, data) {
    if (btn == 'View') {
      const modal = await this.modalcontroller.create({
        component: ViewReceiptComponent,
        componentProps: { bookingId: data.bookingId, billId: data.billId }
      });
      modal.onDidDismiss().then(() => {
        this.resetPaginationData();
        this.getAllReceiptForMobile();
      })
      return await modal.present();
      // this.router.navigate(['after-login/pages/orgadmin/GH-menu/receipt/view-receipt'], { queryParams: { bookingId: data.bookingId, billId: data.billId } })
    }
    if (btn == 'Delete') {
      this.deleteFunction(data)
    }
    if (btn == 'viewReceipt') {
      const modal = await this.modalcontroller.create({
        component: MakeReceiptComponent,
        componentProps: { receiptData: data, type: btn }
      });
      modal.onDidDismiss().then(() => {
        this.resetPaginationData();
        this.getAllReceiptListForMobile();
      })
      return await modal.present();
    }
  }

  async deleteFunction(bookingDetails) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to cancel the bill?',
      buttons: [
        {
          text: 'Yes,Cancel it',
          cssClass: 'alertButton',
          handler: () => {
            this.deleteBooking(bookingDetails);

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

  public async deleteBooking(bookingDetails) {
    console.log("book", bookingDetails);
    const loading = await this.loadingController.create({
      message: 'Please Wait...'
    });
    loading.present();
    this.mdmService.cancelBillForMobile(bookingDetails.bookingId, bookingDetails.billId).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          this.commonService.showSuccessToast('Bill Cancelled Successfully');
          this.resetPaginationData();
          this.getAllReceiptForMobile()
        }
        else {
          loading.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
        }

      },
      err => {
        loading.dismiss();
      },
      () => {
        loading.dismiss();
      })
  }


  async getButtonName(btn) {
    this.resetPaginationData()
    if (this.selectCity === '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
    }
    else if (this.selectGH === '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
    }
    else {
      this.selectedValue = btn.btnName;
      this.headingName = this.selectedValue;
      console.log("button name is", btn.btnName);
      if (btn.btnName == 'Receipt') {

        this.filterObj.fromDate = "",
          this.filterObj.bookingId = "",
          // this.filterObj.cId = "",
          this.filterObj.category = "",
          // this.filterObj.gId = "",
          this.filterObj.mobNo = "",

          this.filterObj.name = "",
          this.filterObj.type = ""

        this.filterObj.pageNum = 1;
        this.filterObj.pageSize = 5;
        this.recieptData = []

        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getAllReceiptForMobile();
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
      if (btn.btnName == 'Receipt List') {
        this.filterObj.fromDate = "",
          this.filterObj.bookingId = "",
          // this.filterObj.cId = "",
          this.filterObj.category = "",
          // this.filterObj.gId = "",
          this.filterObj.mobNo = "",

          this.filterObj.name = "",
          this.filterObj.type = ""

        this.filterObj.pageNum = 1;
        this.filterObj.pageSize = 5;
        this.recieptList = []

        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getAllReceiptListForMobile();
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
  }

  public async getAllReceiptForMobile() {
    this.filterObj.cId = this.selectCity;
    this.filterObj.gId = this.selectGH;
    this.filterObj.type = "BILL";
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAllReceiptForMobile(this.filterObj).subscribe(
      res => {
        if (res.status == 1) {
          this.recieptData = this.recieptData.concat(res.data);
          if (this.isFirstApiHit) {
            loading.dismiss();
            // console.log(loading,"xxxx")
            this.isFirstApiHit = false;
          }
          console.log(this.recieptData)
        } else {
          if (this.isFirstApiHit) {
            loading.dismiss();
          }
        }
      }, () => {
        loading.dismiss();
      }
    )

  }
  public async getAllReceiptListForMobile() {
    this.filterObj.cId = this.selectCity;
    this.filterObj.gId = this.selectGH;
    this.filterObj.type = "RECT";
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAllReceiptForMobile(this.filterObj).subscribe(
      res => {
        if (res.status == 1) {
          if (this.isFirstApiHit) {
            loading.dismiss();
            this.isFirstApiHit = false;
          }
          this.recieptList = this.recieptList.concat(res.data);
          console.log(this.recieptData)
        } else {
          if (this.isFirstApiHit) {
            loading.dismiss();
          }
        }
      },
      err=> {
        loading.dismiss()
      }, () => {
        loading.dismiss();
      }
    )

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
      category: [''],
      gId: [''],
      mobNo: [''],
      name: [''],
      fromDate: [''],

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

  filterHandleRefresh(event) {
    setTimeout(() => {

      if (this.selectedValue == 'Receipt') {
        this.filterObj.fromDate = "",
          this.filterObj.bookingId = "",
          this.filterObj.cId = "",
          this.filterObj.category = "",
          this.filterObj.gId = "",
          this.filterObj.mobNo = "",

          this.filterObj.name = "",
          this.filterObj.type = ""

        this.filterObj.pageNum = 1;
        this.filterObj.pageSize = 5;
        this.recieptData = []
        this.getAllReceiptForMobile();
        this.isFirstApiHit = true
        // event.target.complete();
      }
      else if (this.selectedValue == 'Receipt List') {
        this.filterObj.fromDate = "",
          this.filterObj.bookingId = "",
          this.filterObj.cId = "",
          this.filterObj.category = "",
          this.filterObj.gId = "",
          this.filterObj.mobNo = "",

          this.filterObj.name = "",
          this.filterObj.type = ""

        this.filterObj.pageNum = 1;
        this.filterObj.pageSize = 5;
        this.recieptList = []
        this.getAllReceiptListForMobile();
        this.isFirstApiHit = true
        // event.target.complete();
      }
      else {}
      event.target.complete();

    }, 500);
  }
  //    callAnother (){
  //     setTimeout(() => {
  //   this.getAllReceiptListForMobile();
  // }, 500);
  // }

  public async submitFilterForm(data) {
    console.log(data);
    if (!data.bookingId && !data.cId && !data.category && !data.gId && !data.mobNo && !data.name && !data.fromDate) {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }

    if (data.fromDate) {
      data.fromDate = this.changeDateFormat(data.fromDate);
    }

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.filterObj = {
      bookingId: data.bookingId,
      cId: this.selectCity,
      category: data.category,
      fromDate: data.fromDate,
      gId: this.selectGH,
      mobNo: data.mobNo.toString(),
      name: data.name,
      pageSize: this.pageSize,
      pageNum: this.pageNum,
      type: "BILL"
    };

    this.mdmService.getAllReceiptForMobile(this.filterObj).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          this.recieptData = res.data;
          console.log(this.recieptData);
          loading.dismiss();
          this.callTypeRect({
            ...this.filterObj,
            type: "RECT"
          });
        } else {
          loading.dismiss();
        }
      },
      (error) => {
        loading.dismiss();
        // Handle the error here (e.g., log or show a user-friendly message).
      }
    );
  }

  callTypeRect(data) {
    this.mdmService.getAllReceiptForMobile(data).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          this.recieptList = res.data;
          this.filtermodal.isOpen = false;
        }
      },
      (error) => {
        // Handle the error here (e.g., log or show a user-friendly message).
      }
    );
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }


}
