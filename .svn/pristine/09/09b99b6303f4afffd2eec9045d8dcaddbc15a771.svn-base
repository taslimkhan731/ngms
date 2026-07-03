import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { Router } from '@angular/router';
import { CancelCheckoutComponent } from './cancel-checkout/cancel-checkout.component';
import { CreateBillComponent } from './create-bill/create-bill.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonService } from 'src/app/Services/common/common.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss'],
})
export class BillingComponent implements OnInit {
  selectedValue = '';
  selectGH = ''
  selectCity = ''
  headingName = 'Billing';
  shiftStatus: any;
  shiftMsg: any;
  getAllCityList = []
  public pageSize = 5;
  public pageNum = 1;
  public isFirstApiHit = true;
  public totalPages;

  obj = {
    bookingId: "",
    cId: "",
    category: "",
    fromDate: "",
    gId: "",
    mobNo: "",
    name: "",
    purpose: "",
    pageSize: this.pageSize,
    pageNum: this.pageNum
  }
  obj2 = {
    bookingId: "",
    cId: "",
    category: "",
    fromDate: "",
    gId: "",
    mobNo: "",
    name: "",
    purpose: "",
    billDate: "",
    pageSize: this.pageSize,
    pageNum: this.pageNum
  }
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public categoryList = [];
  backToTop: boolean = false;

  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  ];
  guestHouseList: any;
  public splitBtnClass = [
    { btnName: 'Billing', btnIcon: 'eye-outline', active: false },
    { btnName: 'Bill List', btnIcon: 'eye-outline', active: false },
  ]
  approveStatus: any;
  getBillingListData = [];
  showBillingListData = [];
  constructor(public popoverController: PopoverController,
    private router: Router,
    private modalController: ModalController,
    private loadingController: LoadingController,
    private platform: Platform,
    private alertController: AlertController,
    private mdmService: MdmService,
    private commonService: CommonService,
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
    this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList);
    this.getCategoryList();
  }

  ionViewDidLeave() {
    this.resetPaginationData();
    // this.selectCity = '';
    // this.selectGH = '';
    // this.selectedValue = ''
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
    this.obj.pageSize = 5;
    this.obj2.pageSize = 5;
    // if (this.totalPages !== this.obj.pageNum) {
    this.obj.pageNum = this.obj.pageNum + 1;
    this.obj2.pageNum = this.obj2.pageNum + 1;
    this.pageNum = this.pageNum + 1;
    if (selectedValue == 'Billing') {
      this.getBillingList();
    }
    if (selectedValue == 'Bill List') {
      this.showBillingList();
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
    this.pageNum = 1;
    this.pageSize = 5;
    this.obj.pageNum = 1;
    this.obj2.pageNum = 1;
    this.getBillingListData = [];
    this.showBillingListData = [];
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
      message: 'Please wait...'
    });
    loading.present();
    this.selectGH = ''
    this.obj.cId = event.target.value;
    this.obj2.cId = event.target.value;
    console.log("city name is", event.target.value);
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      console.log("result is", res);
      this.guestHouseList = res.data;
      if (this.guestHouseList.length == 1) {
        console.log("Inside IF Condition")
        console.log(res.data)
        this.selectGH = res.data[0].id;
      }
      loading.dismiss()
    }, err => {
      loading.dismiss()
    },
      () => {
        loading.dismiss()
      })
  }
  getBhawanName(event) {
    this.obj.gId = event.target.value
    this.obj2.gId = event.target.value
  }

  async getButtonName(btn) {
    this.resetPaginationData();
    console.log("butttttonnnnn", btn);
    if (this.selectCity === '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
    }
    else if (this.selectGH === '') {
      const alert = await this.alertController.create({
        message: 'Please Select Guest house.',
        buttons: ['OK'],
      });
      await alert.present();
    }
    else {
      console.log("button name is", btn.btnName);
      if (btn.btnName == 'Billing') {

        this.obj.bookingId = "",

          this.obj.category = "",
          this.obj.fromDate = "",

          this.obj.mobNo = "",
          this.obj.name = "",
          this.obj.purpose = "",
          this.obj.pageSize = 5,
          this.obj.pageNum = 1
        this.getBillingListData = []

        console.log("this is Billing")
        this.selectedValue = btn.btnName;
        this.headingName = 'Billing';
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getBillingList();
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
      if (btn.btnName == 'Bill List') {


        this.obj.fromDate = "",
          this.obj.bookingId = "",
          // this.obj.billDate ="",

          this.obj.category = "",

          this.obj.mobNo = "",
          this.obj.name = "",
          this.obj.purpose = "",
          this.obj.pageNum = 1;
        this.obj.pageSize = 5;
        this.showBillingListData = []


        this.selectedValue = btn.btnName;
        this.headingName = 'Billing List';
        console.log("this is Bill List");
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.showBillingList();
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
  async getBillingList() {
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
    this.mdmService.getBillingList(this.obj).subscribe(
      res => {
        if (res.status == 1) {
          this.getBillingListData = this.getBillingListData.concat(res.data);
          // this.getBillingListData = res.data
          this.totalPages = res.data.totalPages;
          if (this.isFirstApiHit) {
            loading.dismiss();
            this.isFirstApiHit = false;
          }
          console.log(this.getBillingListData)
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
        loading.dismiss();
      })
  }
  async showBillingList() {
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
    this.mdmService.showBillingList(this.obj).subscribe(
      res => {
        if (res.status == 1) {
          if (this.isFirstApiHit) {
            loading.dismiss();
            this.isFirstApiHit = false;
          }
          this.showBillingListData = this.showBillingListData.concat(res.data);
          // this.showBillingListData =res.data;
          this.totalPages = res.data.totalPages;
          console.log(this.showBillingListData)
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
      })
  }

  public async showBillDetails(data, type) {
    const modal = await this.modalController.create({
      component: BillDetailsComponent,
      componentProps: { data, type }
    })

    return await modal.present();
  }

  public async createBill(bookingId) {
    const modal = await this.modalController.create({
      component: CreateBillComponent,
      componentProps: { data: bookingId, gId: this.selectGH, cId: this.selectCity }
    })
    modal.onDidDismiss().then((data) => {
      this.resetPaginationData();
      this.getBillingList();
    })

    return await modal.present();
    // this.router.navigate(['after-login/pages/orgadmin/GH-menu/billing/createBill'], { queryParams: { data: bookingId, gId: this.selectGH, cId: this.selectCity } })
  }

  public async cancelCheckOut(bookingId) {
    const modal = await this.modalController.create({
      component: CancelCheckoutComponent,
      componentProps: { bookingId }
    })
    modal.onDidDismiss().then((data) => {
      this.resetPaginationData();
      this.getBillingList();
    })

    return await modal.present();
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
  // filter
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
    console.log("res");
    this.mdmService.getCategoryData().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.categoryList = res.data;

      }
    })
  }


  filterHandleRefresh(event) {
    this.resetPaginationData();
    setTimeout(() => {
      if (this.selectedValue == 'Billing') {
        this.obj.fromDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.mobNo = "",
          this.obj.name = "",
          this.obj.purpose = ""
        this.obj.pageNum = 1;
        this.obj.pageSize = 5;
        this.getBillingListData = []

        this.getBillingList();
        this.isFirstApiHit = true
        event.target.complete();
      }
      else {
        console.log("else blck ")
        this.obj2.fromDate = "",
          this.obj2.bookingId = "",
          this.obj2.billDate = "",
          this.obj2.cId = "",
          this.obj2.category = "",
          this.obj2.gId = "",
          this.obj2.mobNo = "",
          this.obj2.name = "",
          this.obj2.purpose = "",
          this.obj.fromDate = "",
          this.obj.bookingId = "",
          this.obj.cId = "",
          this.obj.category = "",
          this.obj.gId = "",
          this.obj.mobNo = "",
          this.obj.name = "",
          this.obj.purpose = ""
        this.obj2.pageNum = 1;
        this.obj2.pageSize = 5;
        this.showBillingListData = []
        this.showBillingList();
        this.isFirstApiHit = true
        event.target.complete();
      }


    }, 500);
  }


  public createFormInstance() {
    this.filterForm = this.fb.group({

      bookingId: [''],
      fromDate: [''],
      billDate: [''],
      cId: [''],
      category: [''],
      gId: [''],
      mobNo: [''],
      name: [''],
      purpose: [''],


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
    if (data.bookingId === '' && data.cId === '' && data.gId === '' && data.category === '' && data.mobNo === '' && data.purpose === '' && data.name === '' && data.fromDate === '' && data.billDate === '') {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }
    if (data.fromDate) {
      data.fromDate = this.changeDateFormat(data.fromDate);

    }
    if (data.billDate) {
      data.billDate = this.changeDateFormat(data.billDate);
    }
    this.obj = {

      bookingId: data.bookingId,
      fromDate: data.fromDate,
      cId: this.selectCity,
      category: data.category,
      gId: this.selectGH,
      mobNo: data.mobNo.toString(),
      name: data.name,
      purpose: data.purpose,
      pageSize: 5,
      pageNum: 1

    }
    this.obj2 = {
      bookingId: data.bookingId,
      fromDate: data.fromDate,
      billDate: data.billDate,
      cId: data.cId,
      category: data.category,
      gId: data.gId,
      mobNo: data.mobNo.toString(),
      name: data.name,
      purpose: data.purpose,
      pageSize: 5,
      pageNum: 1
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.selectedValue == 'Billing') {
      loading.present();
      this.mdmService.getBillingList(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.getBillingListData = res.data;
          this.filtermodal.isOpen = false;
          // this.showBilling(this.obj2);
          loading.dismiss();
        } else {
          loading.dismiss()
        }
      },
        error => {
          loading.dismiss();
          console.error("Error in showBillingList:", error);
        }, () => {
          loading.dismiss()
        })
    } else {
      this.showBilling(this.obj2);
    }
  }

  public async showBilling(data) {
    this.mdmService.showBillingList(data).subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.showBillingListData = res.data;
        this.filtermodal.isOpen = false;
      }
    },
      error => {
        console.error("Error in showBillingList:", error);
      })

  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }
}
