import { Component, OnInit, ViewChild } from '@angular/core';
import { PopoverController, LoadingController, ModalController, AlertController, IonContent, Platform, InfiniteScrollCustomEvent, IonModal } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { AddEditVehicleComponent } from './add-edit-vehicle/add-edit-vehicle.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-vehicle-allotment',
  templateUrl: './vehicle-allotment.component.html',
  styleUrls: ['./vehicle-allotment.component.scss'],
})
export class VehicleAllotmentComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('filtermodal') filtermodal: IonModal;


  filterForm: FormGroup;
  backToTop: boolean = false;
  selectGH = ''
  selectCity = ''
  selectedValue = '';
  public headerName = 'Vehicle Request List'
  shiftStatus: any;
  shiftMsg: any;
  public pageSize = 5;
  public pageNum = 1;
  public allotmentList;
  public allotmentReportList = [];
  getAllCityList = [];
  private isFirstApiHit = true;

  public splitBtnClass = [
    { btnName: 'View Vehicle Allotment', btn: 'VALLOT', btnIcon: 'eye-outline', active: false },
    { btnName: 'Vehicle Allotment Report', btn: 'VREPORT', btnIcon: 'eye-outline', active: false },
  ]
  public btnData = [
    { btnName: 'Add Allotment', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
  ];
  guestHouseList: any;

  obj = {
    "cId": this.selectCity,
    "gId": this.selectGH,
    "bookingId": "",
    "name": "",
    "pageNo": 0,
  }
  obj2 = {
    "cId": this.selectCity,
    "gId": this.selectGH,
    "bookingId": "",
    "name": "",
    "pageNum": 1,
    "pageSize": 5
  }

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
    this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
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

  async getbutton(data, btn) {
    let cityAndGh = {
      cityId: this.selectCity,
      ghId: this.selectGH
    }
    if (btn == 'ADD' || btn == 'EDIT') {
      const modal = await this.modalcontroller.create({
        component: AddEditVehicleComponent,
        componentProps: { vehicleData: data, btn, cityAndGh }
      });
      modal.onDidDismiss().then(() => {
        this.getVehicleAllotmentList();
      })
      return await modal.present();
    }
    if (btn == 'CAN') {
      const alert = await this.alertController.create({
        cssClass: 'alertCancel',
        header: 'Are you sure?',
        message: 'Are you sure to cancel the vehicle allotment?',
        buttons: [
          {
            text: 'Yes',
            cssClass: 'alertButton',
            handler: () => {
              this.cancelVehicleAllotmentForMobile(data.reqId);
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

  public async cancelVehicleAllotmentForMobile(reqId) {
    console.log(reqId)
    const loading = await this.loadingController.create({
      message: 'Please Wait...'
    });
    loading.present();
    this.mdmService.cancelVehicleAllotmentForMobile(reqId).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast("Allotment Cancelled.");
          this.getVehicleAllotmentList()
          loading.dismiss();
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

  async getGuestHouseList(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.selectGH = ''
    console.log("city name is", event.target.value);
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      console.log("result is", res);
      this.guestHouseList = res.data;
      if (this.guestHouseList.length == 1) {
        console.log("Inside IF Condition")
        console.log(res.data)
        this.selectGH = res.data[0].id;
      }
      loading.dismiss();
    }, err => {
      loading.dismiss()
    }, () => {
      loading.dismiss()
    })
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
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
    this.pageSize = 5;
    this.pageNum = 1;
    this.allotmentReportList = []
  }

  async getButtonName(btnName) {
    console.log("hgfj")
    if (btnName === 'View Vehicle Allotment') {
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = "VALLOT";
        this.headerName = "Vehicle Request List"
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            // this.shiftId = res.data.shiftId;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getVehicleAllotmentList();
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
    if (btnName == "Vehicle Allotment Report") {
      this.resetPaginationData()
      console.log("Vehicle Allotment Report")
      const status = await this.checkCityAndGh()
      console.log(status)
      if (status) {
        this.selectedValue = "VREPORT";
        this.headerName = "Vehicle Report List"
        this.mdmService.checkShift(this.selectCity, this.selectGH, "").subscribe(
          async res => {
            this.shiftMsg = res.data.message;
            // this.shiftId = res.data.shiftId;
            this.shiftStatus = res.data.status;
            if (this.shiftStatus == true) {
              this.getVehicleAllotmentReport();
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

  public async getVehicleAllotmentList() {
    let obj = {
      "cId": this.selectCity,
      "gId": this.selectGH,
      "bookingId": "",
      "name": "",
      "pageNo": 0,
    }

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getVehicleAllotedDataForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.allotmentList = res.data.data;
          console.log(this.allotmentList)
          loading.dismiss();
        } else {
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
      },
      () => {
        loading.dismiss();
      }
    )
  }
  public async getVehicleAllotmentReport() {
    let obj = {
      "cId": this.selectCity,
      "gId": this.selectGH,
      "pageSize": this.pageSize,
      "pageNum": this.pageNum
    }

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.viewVehicleAllotmentReportForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.allotmentReportList = this.allotmentReportList.concat(res.data.Data);
          console.log(this.allotmentReportList)
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
        loading.dismiss();
      }
    )
  }
  onIonInfinite(ev) {
    this.pageSize = 5;
    this.pageNum = this.pageNum + 1;
    this.getVehicleAllotmentReport();

    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
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
  ionViewDidLeave(): void {
    // this.selectGH = ''
    // this.selectCity = ''
    this.pageSize = 5;
    this.pageNum = 1;
    this.isFirstApiHit = true;
    this.allotmentReportList = []
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



  public createFormInstance() {
    this.filterForm = this.fb.group({
      bookingId: [''],
      cId: [''],
      gId: [''],
      name: [''],
      pageNo: 0
    })
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }


  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingId === '' && data.cId === '' && data.gId === '' && data.name === '') {
      this.commonService.showErrorToast('Provide any field for filter');
      return;
    }

    this.obj = {
      bookingId: data.bookingId,
      cId: this.selectCity,
      gId: this.selectGH,
      name: data.name,
      pageNo: 0
    }
    this.obj2 = {
      cId: this.selectCity,
      gId: this.selectGH,
      bookingId: data.bookingId,
      name: data.name,
      pageNum: 1,
      pageSize: 5
    }

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    if (this.selectedValue = "VALLOT") {
      this.mdmService.getVehicleAllotedDataForMobile(this.obj).subscribe(res => {
        console.log(res);
        if (res.status === 1) {
          this.allotmentList = res.data.data;
          console.log(this.allotmentList);
          this.filtermodal.isOpen = false;
          loading.dismiss();
          // this.viewVehicleAllotmentReport();
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
      this.viewVehicleAllotmentReport();
    }

  }


  async viewVehicleAllotmentReport() {
    //  this.obj2.cId=this.selectCity;
    //  this.obj2.gId=this.selectGH
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewVehicleAllotmentReportForMobile(this.obj2).subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.allotmentReportList = res.data.Data;
        console.log(this.allotmentList);
        loading.dismiss()
        this.filtermodal.isOpen = false;
      } else {
        loading.dismiss()
      }
    },
      error => {
        loading.dismiss()
      },
      () => {
        loading.dismiss()
      })
  }

  handleRefresh(event) {
    this.resetPaginationData()
    setTimeout(() => {
      this.obj.bookingId = "",
      this.obj.cId = "",
      this.obj.gId = "",
      this.obj.name = ""
      this.allotmentList = []
      this.getVehicleAllotmentList();
      this.viewVehicleAllotment();
      this.isFirstApiHit = true
      event.target.complete();
    }, 2000);
  }

  viewVehicleAllotment() {
    setTimeout(() => {
      this.obj.bookingId = "",
        this.obj.cId = "",
        this.obj.gId = "",
        this.obj.name = ""
      this.allotmentReportList = []
      this.getVehicleAllotmentReport();
    }, 2000)
  }
}



