import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-application-entry',
  templateUrl: './application-entry.component.html',
  styleUrls: ['./application-entry.component.scss'],
})
export class ApplicationEntryComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;

  private isFirstApiHit = true;
  selectGH = ''
  selectCity = ''
  public pageSize = 5;
  public totalPages;
  public pageNum = 1;
  backToTop: boolean = false;
  public splitBtnClass = [
    { btnName: 'Add', btnIcon: 'eye-outline', active: false },
    { btnName: 'Extend', btnIcon: 'eye-outline', active: false },
    { btnName: 'Split', btnIcon: 'eye-outline', active: false },
    { btnName: 'Cancel', btnIcon: 'eye-outline', active: false },
  ]
  obj = {
    bookingDate: "",
    bookingId: "",
    cId: "",
    category: "",
    gId: "",
    mobNo: "",
    purpose: "",
    reqType: "AL",
    name: "",
    pageSize: this.pageSize,
    pageNum: this.pageNum
  }
  applicationEntry = [];
  getAllCityList = [];
  items = [];
  guestHouseList = [];
  public categoryList = [];

  constructor(
    private router: Router,
    public popoverController: PopoverController,
    private alertController: AlertController,
    public loadingController: LoadingController,
    private platform: Platform,
    private commonService: CommonService,
    private mdmService: MdmService,
    private fb: FormBuilder
  ) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function(event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.filtermodal.isOpen = false;
      processNextHandler();
    });
    this.createFormInstance();
  }

  ngOnInit() {

  }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getAllotmentList(this.obj);
    this.getCategoryList();
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

  onIonInfinite(ev) {
    this.obj.pageSize = 5;
    console.log('total page', this.totalPages, 'obj page', this.obj.pageNum);
    if (this.totalPages !== this.obj.pageNum) {
      console.log("Entered........")
      this.obj.pageNum = this.obj.pageNum + 1;
      this.getAllotmentList(this.obj);
    }
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
  }

  public async getAllotmentList(objData) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAllotmentListDetails(objData).subscribe(res => {
      if (this.isFirstApiHit) {
        loading.dismiss();
        this.isFirstApiHit = false;
      }
      this.applicationEntry = this.applicationEntry.concat(res.data.data);
      this.totalPages = res.data.totalPages;
      console.log("resis ", this.applicationEntry)

    }, err => {
      if (this.isFirstApiHit) {
        loading.dismiss();
      }
    }, () => {

      loading.dismiss();

    })
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
      this.applicationEntry = []
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
    }, () => {
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
    console.log("On Click GH", this.applicationEntry)
    if (event.target.value) {
      this.getAllotmentList(this.obj)
    }
  }
  getbutton(button, bhawanName) {
    console.log("button type", button, bhawanName.title);
    localStorage.setItem("bookingDetails", JSON.stringify(bhawanName))
    // localStorage.setItem("currentLocation", 'after-login/pages/orgadmin/application-menu/application-entry')
    if (button == 'Edit') {
      this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/edit'], { queryParams: { bhawan_Name: bhawanName.title } });
    }
    else if (button == 'View') {
      this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/view'], { queryParams: { bhawan_Name: bhawanName.title } });
    }
    else if (button == 'Delete') {
      this.deleteFunction(bhawanName);
    }
  }

  async deleteFunction(bookingDetails) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to delete the booking request?',
      buttons: [
        {
          text: 'Yes',
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
    this.mdmService.cancelRequestForMobile(bookingDetails.bookingId,bookingDetails.mobile).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc);
          loading.dismiss();
          this.obj.pageSize = 5;
          // this.obj.pageNum = 1;
          this.applicationEntry = []
          this.getAllotmentList(this.obj)
        }
        else {
          this.commonService.showErrorToast(res.statusDesc);
          loading.dismiss();
        }

      },
      () => {
        loading.dismiss();
      })
  }

  async getButtonName(btnName) {
    this.isFirstApiHit = true;
    if (this.selectCity == '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: [
          {
            text: 'OK',
            handler: () => {
              this.gotToTop()
            },
          },
        ],
      });
      await alert.present();
      // this.commonService.showErrorToast('Select City');
      return;
    }
    if (this.selectGH == '') {
      const alert = await this.alertController.create({
        message: 'Please Select Guest house.',
        buttons: [
          {
            text: 'OK',
            handler: () => {
              this.gotToTop()
            },
          },
        ],
      });
      await alert.present();
      // this.commonService.showErrorToast('Select Guest house');
      return;
    }

    if (this.selectCity != '' && this.selectGH != '') {
      if (btnName == 'Add') {
        this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/add'], { queryParams: { city_id: this.selectCity, ghId: this.selectGH } });
      }
      if (btnName == 'Extend') {
        this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/extend'], { queryParams: { city_id: this.selectCity, gh_id: this.selectGH } });
      }
      if (btnName == 'Split') {
        this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/split'], { queryParams: { city_id: this.selectCity, ghId: this.selectGH } });
      }
      if (btnName == 'Cancel') {
        this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/cancel'], { queryParams: { city_id: this.selectCity, ghId: this.selectGH } });
      }
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
      this.obj.bookingDate = "",
        this.obj.bookingId = "",
        this.obj.cId = "",
        this.obj.category = "",
        this.obj.gId = "",
        this.obj.mobNo = "",
        this.obj.purpose = "",
        this.obj.name = "",
        this.obj.reqType = "AL",
        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
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
    this.filtermodal.isOpen = false;
  }
  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }
  // milan code start here

  refresh() {
    this.obj.bookingDate = "",
      this.obj.bookingId = "",
      this.obj.cId = "",
      this.obj.category = "",
      this.obj.gId = "",
      this.obj.mobNo = "",
      this.obj.purpose = "",
      this.obj.name = "",
      this.obj.reqType = "AL",
      this.obj.pageNum = 1;
    this.obj.pageSize = 5;
    this.getAllotmentList(this.obj);
  }

  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingDate === '' && data.bookingId === '' && data.cId === '' && data.category === '' && data.gId === '' && data.category === '' && data.gId === '' && data.mobNo === '' && data.purpose === '' && data.name === '') {
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
      reqType: "AL",
      pageSize: this.pageSize,
      pageNum: this.pageNum
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
    ()=> {
      loading.dismiss()
    })
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
    this.createFormInstance();
    this.filtermodal.isOpen = true;
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
      reqType: ['AL'],
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

  // milan code ends here
}
