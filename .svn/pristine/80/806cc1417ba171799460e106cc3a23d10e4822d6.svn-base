import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, IonModal, LoadingController, ModalController, Platform, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { ExtensionCheckinModalComponent } from './extension-checkin-modal/extension-checkin-modal.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-extention-checkin',
  templateUrl: './extention-checkin.component.html',
  styleUrls: ['./extention-checkin.component.scss'],
})
export class ExtentionCheckinComponent implements OnInit {

  @ViewChild('filtermodal') filtermodal: IonModal;

  filterForm: FormGroup;
  public categoryList = [];

  public filObject = {
    bookingId: "",
    cId: "",
    category: "",
    fromDate: "",
    gId: "",
    mobNo: "",
    name: "",
    reqType: 'AL',
    pageSize: 5,
    pageNum: 1,

  }
  selectGH = ''
  selectCity = ''
  selectedValue = '';
  guestHouseList: any;
  shiftStatus: any;
  shiftMsg: any;
  public selectedGuest = []
  public finalGuestIds = []
  getAllCityList = [];
  public extensionCINData;

  constructor(public popoverController: PopoverController,
    private loadingController: LoadingController,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private platform: Platform,
    private commonService: CommonService,
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

  public getbutton(btn, data) {
    if (btn == 'CIN') {
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
        console.log(gList)
        console.log("selectedGuest", gList, gList.length)
        if (this.finalGuestIds.length !== gList.length) {
          this.commonService.showErrorToast("Please select all guest")
          return;
        } else {
          finalGuests = {
            "cId": this.selectCity,
            "gId": this.selectGH,
            "req_id": data.req_id,
            "existing_req_id": data.ext_req_id,
            "hidval": this.finalGuestIds.map(x => x).join("','"),
            "fromDate": data.from_date
          }

          // console.log(finalGuests)
          this.goToCheckInView(finalGuests)
        }
      }
    }
  }

  public async goToCheckInView(finalGuests) {
    const modal = await this.modalcontroller.create({
      component: ExtensionCheckinModalComponent,
      componentProps: { finalGuests }
    });
    modal.onDidDismiss().then(() => {
      this.getExtensionCINData();
      this.finalGuestIds = [];
      this.selectedGuest = [];
      console.log("onModalDissmiss", this.finalGuestIds)
    })
    return await modal.present();
  }

  public selectGuest(event, bookingId, guestData) {
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

  // public selectGuest(event, bookingId, guestData) {
  //   if (event.target.checked) {
  //     this.selectedGuest.push({
  //       bookingId: bookingId,
  //       guestId: guestData.guestId
  //     });
  //   }

  //   if (!event.target.checked) {
  //     this.selectedGuest.forEach((ele, ind) => {
  //       if (ele.bookingId == bookingId) {
  //         this.selectedGuest.splice(ind, 1);
  //       }
  //     })
  //   }

  //   console.log(this.selectedGuest)
  // }

  public async extensionCIN() {
    const status = await this.checkCityAndGh()
    console.log(status)
    if (status) {
      this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
        async res => {
          this.shiftMsg = res.data.message;
          this.shiftStatus = res.data.status;
          if (this.shiftStatus == true) {
            this.getExtensionCINData();
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

  public async getExtensionCINData() {
    let obj = {
      "cId": "01",
      "gId": "01",
      "bookingId": "",
      "category": "",
      "fromDate": "",
      "mobNo": "",
      "name": ""
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getExtensionCheckinDetailsForMobile(obj).subscribe(
      res => {
        this.extensionCINData = res.data;
        console.log("Check In Dta", this.extensionCINData);
        loading.dismiss()
      },
      err => {
        this.commonService.showErrorToast("Something Went Wrong.")
        loading.dismiss
      },
      () => {
        loading.dismiss()
      })
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



  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
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
      category: [''],
      gId: [''],
      mobNo: [''],
      name: [''],
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
    if (data.bookingId === '' && data.cId === '' && data.category === '' && data.gId === '' && data.category === '' && data.gId === '' && data.mobNo === '' && data.name === '' && data.fromDate === '') {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }


    this.filObject = {
      bookingId: data.bookingId,
      cId: this.selectCity,
      category: data.category,
      fromDate: data.fromDate,
      gId: this.selectGH,
      mobNo: data.mobNo.toString(),
      name: data.name,
      reqType: 'AL',
      pageSize: 5,
      pageNum: 1,

    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getExtensionCheckinDetailsForMobile(this.filObject).subscribe(
      res => {
        console.log(res);
        if (res.status === 1) {
          loading.dismiss();
          this.extensionCINData = res.data.data;
          console.log(this.extensionCINData);
          this.filtermodal.isOpen = false;
        } else {
          loading.dismiss();
        }
      }, error => {
        loading.dismiss();
      },
      () => {
        loading.dismiss()
      })
  }

  filterHandleRefresh(event) {
    setTimeout(() => {
      this.filObject.fromDate = "",
        this.filObject.bookingId = "",
        this.filObject.cId = "",
        this.filObject.category = "",
        this.filObject.gId = "",
        this.filObject.mobNo = "",
        this.filObject.name = "",

        this.filObject.pageNum = 1;
      this.filObject.pageSize = 5;

      this.extensionCINData = []
      this.getExtensionCINData();

      // this.isFirstApiHit = true
      event.target.complete();
    }, 2000);
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }
}
