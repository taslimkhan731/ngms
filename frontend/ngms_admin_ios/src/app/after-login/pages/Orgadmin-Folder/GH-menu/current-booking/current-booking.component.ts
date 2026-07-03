import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { NewRequestComponent } from './new-request/new-request.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-current-booking',
  templateUrl: './current-booking.component.html',
  styleUrls: ['./current-booking.component.scss'],
})
export class CurrentBookingComponent implements OnInit {

  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public categoryList = [];
  selectGH = ''
  selectCity = ''
  private isFirstApiHit = true;
  backToTop: boolean = false;
  shiftStatus: any;
  shiftMsg: any;
  @ViewChild(IonContent, { static: true }) content: IonContent;
  public pageSize = 5;
  public totalPages;
  public pageNum = 1;
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
    "pageSize": this.pageSize,
    "pageNum": this.pageNum
  }
  applicationEntry = [];
  guestHouseList: any;
  getAllCityList = [];

  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    { btnName: 'Delete', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
    // { btnName: 'Extend', btnIcon: 'documents-outline', color: '#FF7B01', background: '#FFF2CC' },
    // { btnName: 'Feedback', btnIcon: 'mail-outline', color: '#0FA958', background: '#DAFEFE' },

  ];
  public splitBtnClass = [
    { btnName: 'New Request', btnIcon: 'eye-outline' },
    { btnName: 'Moderation', btnIcon: 'eye-outline' },
    // { btnName: 'Cancel', btnIcon: 'eye-outline' },
    // { btnName: 'Modify', btnIcon: 'eye-outline' },
  ]
  constructor(
    private router: Router,
    public popoverController: PopoverController,
    private alertController: AlertController,
    public loadingController: LoadingController,
    public modalController: ModalController,
    private platform: Platform,
    private commonService: CommonService,
    private mdmService: MdmService,
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
  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
  }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getAllotmentList(this.obj);
    this.getCategoryList()
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

  public resetFilter() {
    this.obj.bookingDate = "",
      this.obj.bookingId = "",
      this.obj.cId = this.selectCity,
      this.obj.category = "",
      this.obj.gId = this.selectGH,
      this.obj.mobNo = "",
      this.obj.purpose = "",
      this.obj.name = "",
      this.obj.reqType = "GH",
      this.obj.pageNum = 1;
    this.obj.pageSize = 5;
    this.obj.status = "";
  }

  async getButtonName(btnName) {
    if (this.selectCity == '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
      // this.commonService.showErrorToast('Select City');
      return;
    }
    if (this.selectGH == '') {
      const alert = await this.alertController.create({
        message: 'Please Select Guest house.',
        buttons: ['OK'],
      });
      await alert.present();
      // this.commonService.showErrorToast('Select Guest house');
      return;
    }

    if (this.selectCity != '' && this.selectGH != '') {
      let cityAndGhIds = {
        cityId: this.selectCity,
        ghId: this.selectGH
      }
      if (btnName == 'New Request') {
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              const modal = await this.modalController.create({
                component: NewRequestComponent,
                componentProps: { cityAndGhIds, type: 'Add' }
              });
              modal.onDidDismiss().then((data) => {
                this.resetFilter();
                this.obj.pageSize = 5;
                this.obj.pageNum = 1;
                this.applicationEntry = [];
                this.isFirstApiHit = true;
                this.getAllotmentList(this.obj)
              })
              return await modal.present();
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
        console.log(btnName)

        // this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/add'], { queryParams: { city_id: this.selectCity } });
      }
      if (btnName == 'Moderation') {
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.router.navigate(['after-login/pages/orgadmin/GH-menu/current-booking/moderation'], { queryParams: { city_id: this.selectCity, ghId: this.selectGH,obj:JSON.stringify(this.obj) } });
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

  async getGuestHouseList(event) {
    console.log("city name is", event.target.value);
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.selectGH = ''
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      console.log("result is", res);
      this.guestHouseList = res.data;
      this.obj.cId = event.target.value;
      this.obj.pageSize = 5;
      this.obj.pageNum = 1;
      this.applicationEntry = [];
      this.isFirstApiHit = true;
      if (this.guestHouseList.length == 1) {
        console.log("Inside IF Condition")
        console.log(res.data)
        this.selectGH = res.data[0].id;
      } else {
        console.log("On Click City465467856438", this.applicationEntry)
        this.getAllotmentList(this.obj)
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
    console.log("gh name", event.target.value);
    this.obj.gId = event.target.value;
    this.obj.pageSize = 5;
    this.obj.pageNum = 1;
    this.applicationEntry = [];
    this.isFirstApiHit = true;
    if (event.target.value) {
      this.getAllotmentList(this.obj)
    }
  }

  async getbutton(button, bhawanName) {
    console.log("button type", button, bhawanName);
    // localStorage.setItem("bookingDetails", JSON.stringify(bhawanName))
    let cityAndGhIds = {
      cityId: this.selectCity,
      ghId: this.selectGH
    }
    // localStorage.setItem("currentLocation", 'after-login/pages/orgadmin/application-menu/application-entry')
    if (button == 'Edit') {
      const modal = await this.modalController.create({
        component: NewRequestComponent,
        componentProps: { cityAndGhIds, type: 'Edit', bookingData: bhawanName }
      });
      modal.onDidDismiss().then((data) => {
        this.resetFilter();
        this.obj.pageSize = 5;
        this.obj.pageNum = 1;
        this.applicationEntry = [];
        this.isFirstApiHit = true;
        this.getAllotmentList(this.obj)
      })
      return await modal.present();
    }
    else if (button == 'View') {
      const modal = await this.modalController.create({
        component: NewRequestComponent,
        componentProps: { cityAndGhIds, type: 'View', bookingData: bhawanName }
      });
      modal.onDidDismiss().then((data) => {
        this.resetFilter()
        this.obj.pageSize = 5;
        this.obj.pageNum = 1;
        this.applicationEntry = [];
        this.isFirstApiHit = true;
        this.getAllotmentList(this.obj)
      })
      return await modal.present();
    } else if (button == 'Delete') {
      this.deleteFunction(bhawanName);
    }
  }

  async deleteFunction(bookingDetails) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to delete the booking request ?',
      buttons: [
        {
          text: 'Yes,Delete it',
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
    this.mdmService.cancelRequestForMobile(bookingDetails.bookingId, bookingDetails.mobile).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc);
          this.obj.pageSize = 5;
          this.obj.pageNum = 1;
          this.applicationEntry = [];
          this.isFirstApiHit = true;
          loading.dismiss();
          this.getAllotmentList(this.obj)
        }
        else {
          loading.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
        }
      }, err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      () => {
        loading.dismiss();
      })
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
      this.applicationEntry = this.applicationEntry.concat(res.data.data);
      this.totalPages = res.data.totalPages;
      console.log("resis ", this.applicationEntry)
      if (this.isFirstApiHit) {
        loading.dismiss();
        this.isFirstApiHit = false;
      }
    }, err => {
      if (this.isFirstApiHit) {
        loading.dismiss();
      }
    },
    ()=> {
      loading.dismiss()
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

  // handleRefresh(event) {
  //   setTimeout(() => {
  //     this.obj.pageSize = 5;
  //     this.obj.pageNum = 1;
  //     this.applicationEntry = [];
  //     this.isFirstApiHit = true;
  //     this.getAllotmentList(this.obj)
  //     event.target.complete();
  //   }, 2000);
  // }

  handleRefresh(event) {
    setTimeout(() => {
      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.cId = this.selectCity,
        this.obj.category = "",
        this.obj.gId = this.selectGH,
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        this.obj.reqType = "GH",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.obj.status = "",
        this.applicationEntry = []
      this.getAllotmentList(this.obj);
      this.isFirstApiHit = true
      event.target.complete();
    }, 2000);
  }

  ionViewDidLeave(): void {
    // this.selectGH = ''
    // this.selectCity = ''
    this.obj.pageSize = 5;
    this.obj.pageNum = 1;
    this.applicationEntry = [];
    this.isFirstApiHit = true;
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
      reqType: ['GH'],
      status: [''],
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

  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingDate === '' && data.bookingId === '' && data.cId === '' && data.category === '' && data.gId === '' && data.category === '' && data.gId === '' && data.mobNo === '' && data.purpose === '' && data.name === '' && data.status === '') {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }
    if (data.bookingDate) {
      data.bookingDate = this.changeDateFormat(data.bookingDate);
    }
    this.obj = {
      bookingDate: data.bookingDate,
      bookingId: data.bookingId,
      cId: data.cId,
      category: data.category,
      gId: data.gId,
      mobNo: data.mobNo.toString(),
      name: data.name,
      purpose: data.purpose,
      reqType: "GH",
      pageSize: this.pageSize,
      pageNum: this.pageNum,
      status: data.status

    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAllotmentListDetails(this.obj).subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        loading.dismiss();
        this.applicationEntry = res.data.data;
        console.log(this.applicationEntry);
        this.filtermodal.isOpen = false;
      } else {
        loading.dismiss()
      }
    }, error => {
      loading.dismiss();
    },
    ()=>{
      loading.dismiss()
    })
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }
}
