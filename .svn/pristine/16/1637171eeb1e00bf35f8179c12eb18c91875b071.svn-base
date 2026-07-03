import { Component, OnInit, ViewChild } from '@angular/core';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { AlertController, InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
// import { ModalViewComponent } from '../../../Guest-Folder/modal-view/modal-view.component';
import { ConferenceButtonComponent } from './conference-button/conference-button.component';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/Services/common/common.service';
import { filter } from 'rxjs/operators';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-conference-hall-details',
  templateUrl: './conference-hall-details.component.html',
  styleUrls: ['./conference-hall-details.component.scss'],
})
export class ConferenceHallDetailsComponent implements OnInit {

  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public infiniteScrollEnabled = true;
  selectedValue = '';
  selectGH = ''
  selectCity = ''
  headingName = '';
  guestHouseList: any;
  public conferenceHallList = [];
  backToTop: boolean = false;
  getAllCityList = [];
  public pageSize = 5;
  public pageNum = 1;
  public isFirstApiHit = true;
  @ViewChild(IonContent, { static: true }) content: IonContent;
  public filterObj = {
    "cId": "",
    "gId": "",
    "bookingId": "",
    "bookingDate": "",
    "mobNo": "",
    "name": "",
    "reqType": "GH",
    "pageSize": this.pageSize,
    "pageNum": this.pageNum
  }
  constructor(
    private popoverController: PopoverController,
    private loadingController: LoadingController,
    private mdmService: MdmService,
    private datePipe: DatePipe,
    private router: Router,
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

  onIonInfinite(ev) {
    console.log("onIonInfinite")
    if (this.infiniteScrollEnabled) {
      this.filterObj.pageSize = 5;
      // if (this.totalPages !== this.obj.pageNum) {
      this.filterObj.pageNum = this.filterObj.pageNum + 1;
      this.getConferenceHallDetails(this.filterObj);
      // }
      setTimeout(() => {
        (ev as InfiniteScrollCustomEvent).target.complete();
      }, 1500);
    }
  }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getConferenceHallDetails(this.filterObj);
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
    console.log("city name is", event.target.value);
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(
      res => {
        console.log("result is", res);
        this.guestHouseList = res.data;
        this.filterObj.cId = event.target.value;
        this.filterObj.pageSize = 5;
        this.filterObj.pageNum = 1;
        this.conferenceHallList = [];
        this.isFirstApiHit = true;
        if (this.guestHouseList.length == 1) {
          console.log("Inside IF Condition")
          console.log(res.data)
          this.selectGH = res.data[0].id;
        } else {
          console.log("On Click City465467856438", this.conferenceHallList)
          this.getConferenceHallDetails(this.filterObj)
        }
        loading.dismiss()
      }, err => {
        loading.dismiss();
      },
      () => {
        loading.dismiss();
      })

  }
  getBhawanName(event) {
    this.filterObj.gId = event.target.value;
    this.filterObj.pageSize = 5;
    this.filterObj.pageNum = 1;
    this.conferenceHallList = []
    this.isFirstApiHit = true;
    this.infiniteScrollEnabled = true;
    if (event.target.value) {
      this.getConferenceHallDetails(this.filterObj)
    }
  }

  public async getConferenceHallDetails(obj) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getConfrenceRequestDetailsForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.conferenceHallList = this.conferenceHallList.concat(res.data.data);
          console.log(this.conferenceHallList)
          if (this.isFirstApiHit) {
            loading.dismiss();
            this.isFirstApiHit = false;
          }
        } else {
          this.commonservice.showErrorToast(res.statusDesc)
          if (this.isFirstApiHit) {
            loading.dismiss();
          }
        }
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss();
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
    this.filterObj.pageSize = 5;
    this.filterObj.pageNum = 1;
    this.conferenceHallList = []
    this.isFirstApiHit = true;
    this.infiniteScrollEnabled = true;
  }


  async getbutton(button, data) {
    console.log("button type", button);
    if (button == 'Delete') {
      const alert = await this.alertController.create({
        cssClass: 'alertCancel',
        header: 'Are you sure?',
        message: 'Are you sure to cancel the Conference Details?',
        buttons: [
          {
            text: 'Yes',
            // cssClass: 'alertButton',
            handler: () => {
              this.cancelConferenceData(data);
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
    } else if (button == 'Payment' || button == 'CRBill') {
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        const modal = await this.modalcontroller.create({
          component: ConferenceButtonComponent,
          componentProps: { button, confData: data, ghId: this.selectGH }
        });
        modal.onDidDismiss().then(async (data) => {
          this.resetPaginationData()
          console.log("ConferenceButtonComponent Payment")
          this.infiniteScrollEnabled = false;
          await this.getConferenceHallDetails(this.filterObj);
          this.infiniteScrollEnabled = true;
        })
        return await modal.present();
      }
    } else {
      const modal = await this.modalcontroller.create({
        component: ConferenceButtonComponent,
        componentProps: { button, confData: data, ghId: this.selectGH }
      });
      modal.onDidDismiss().then(async (data) => {
        this.resetPaginationData()
        this.infiniteScrollEnabled = false;
        console.log("ConferenceButtonComponent else")
        await this.getConferenceHallDetails(this.filterObj);
        this.infiniteScrollEnabled = true;
      })
      return await modal.present();
    }
  }

  public async cancelConferenceData(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.cancelConfrenceDetailsForMobile(data.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          this.commonservice.showSuccessToast('Application cancel successful');
          loading.dismiss()
        } else {
          this.commonservice.showErrorToast(res.statusDesc);
          loading.dismiss()
        }
      }, err => {
        this.commonservice.showErrorToast("Something Went Wrong!")
        loading.dismiss()
      },
      () => {
        loading.dismiss();
        this.filterObj.pageSize = 5;
        this.filterObj.pageNum = 1;
        this.conferenceHallList = [];
        this.isFirstApiHit = true;
        this.getConferenceHallDetails(this.filterObj);
      })
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }



  public openFilterModal() {
    this.createFormInstance();
    this.filtermodal.isOpen = true;
  }
  filterHandleRefresh(event) {
    setTimeout(() => {
      this.filterObj.bookingDate = "",
        this.filterObj.bookingId = "",
        this.filterObj.cId = "",

        this.filterObj.gId = "",
        this.filterObj.mobNo = "",

        this.filterObj.name = "",
        this.filterObj.reqType = "GH",
        this.filterObj.pageNum = 1;
      this.filterObj.pageSize = 5;
      this.conferenceHallList = []
      this.getConferenceHallDetails(this.filterObj);
      this.isFirstApiHit = true
      event.target.complete();
    }, 2000);
  }


  public createFormInstance() {
    this.filterForm = this.fb.group({
      bookingDate: [''],
      bookingId: [''],
      cId: [''],
      gId: [''],
      mobNo: [''],
      name: [''],
      reqType: ['GH'],
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
    if (data.bookingDate === '' && data.bookingId === '' && data.cId === '' && data.gId === '' && data.mobNo === '' && data.name === '') {
      this.commonservice.showErrorToast('Provide any field for filter');
      return;
    }
    if (data.bookingDate) {
      data.bookingDate = this.changeDateFormat(data.bookingDate);
    }
    this.filterObj = {
      bookingDate: data.bookingDate,
      bookingId: data.bookingId,
      cId: data.cId,
      gId: data.gId,
      mobNo: data.mobNo,
      name: data.name,
      reqType: "GH",
      pageSize: this.pageSize,
      pageNum: this.pageNum,

    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getConferenceListDetails(this.filterObj).subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.conferenceHallList = res.data.data;
        console.log(this.conferenceHallList);
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

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }

}
