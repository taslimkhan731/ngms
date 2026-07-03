import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertController, LoadingController, ModalController, PopoverController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { ViewDetailsComponent } from '../../GH-menu/allotment/view-details/view-details.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { OfficeOrderComponent } from './office-order/office-order.component';

@Component({
  selector: 'app-approver',
  templateUrl: './approver.component.html',
  styleUrls: ['./approver.component.scss'],
})
export class ApproverComponent implements OnInit {
  selctGH = ''
  selctCity = ''
  selectedValue = '';
  // public selectAllChecked = false;
  public headingName = "Draft List";
  public splitBtnClass = [
    { id: 'openDraftModal', btnName: 'Draft List', btnIcon: 'eye-outline', active: false },
    { id: '', btnName: 'Approve List', btnIcon: 'eye-outline', active: false }
  ]
  getAllGHListByCityId: any;
  allotmentList = [];
  conveyanceList = [];
  modifiedList = [];
  canceList = [];
  conferenceList = [];
  selectedPageSize = 10;
  pageSized = [10, 25, 100];
  enableDraftList = true;
  enableApproveList = false;
  enableApproveSec = false;
  getAllCityList = [];
  allChecked = false;
  myform: FormGroup;
  checkedAllotmentList = [];
  approvedBy = Number(3);
  apprivedList = [];


  constructor(private router: Router,
    private mdmService: MdmService,
    private commonService: CommonService,
    private fb: FormBuilder,
    private modalcontroller: ModalController,
    private loadingCtrl:LoadingController,
    private popover: PopoverController,
    private alertController: AlertController) {
    this.createFormInstance();
  }

  private createFormInstance() {
    this.myform = this.fb.group({
      isChecked: [],
      selectAllChecked:[false]
    })
  }

  ngOnInit() { }

  ionViewWillEnter() {
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    // this.getCityList();
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
    this.allotmentList = [];
    this.modifiedList = [];
    this.canceList = [];
    this.conveyanceList = [];
    this.selctGH = '';
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      if (res.status === 1) {
        this.getAllGHListByCityId = res.data;
        if(this.getAllGHListByCityId.length == 1) {
          console.log("Inside IF Condition")
          console.log(res.data)
          this.selctGH = res.data[0].id;
        }
        loading.dismiss();
      } else {
        loading.dismiss();
      }
    }, error => {
      console.log(error);
      loading.dismiss();
    }, ()=> {
      loading.dismiss();
    })
  }

  getAllList(event) {
    if (this.selctCity !== '' && this.selctGH !== '') {
      // this.getApproveList();
      // this.getConveyanceList();
      // this.getmodifiedList();
      // this.getCancelList();
      // this.getConferenceList();
    }
  }

  async getButtonName(btnName) {
   this.myform.get('selectAllChecked').setValue(false);
   this.myform.get('isChecked').setValue(false);
   this.checkedAllotmentList = []
   this.allChecked = false;
    if (this.selctCity === '') {
      const alert = await this.alertController.create({
        message: 'Select City',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    } else if (this.selctGH === '') {
      const alert = await this.alertController.create({
        message: 'Select Guest House',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    console.log("button is", btnName);
    if (btnName == 'Approve List') {
      this.enableDraftList = false;
      this.enableApproveList = true;
      this.selectedValue = btnName;
      this.headingName = 'Approve List'
      this.getApproveList();
    } else if (btnName == 'Draft List') {
      this.enableDraftList = true;
      this.enableApproveList = false;
      this.selectedValue = btnName;
      this.headingName = 'Draft List'
      // if (this.selctCity === '') {
      //   alert("Select City");
      // } else if (this.selctGH === '') {
      //   alert("Select Guest House");
      // } else {
      this.enableApproveSec = true;
      this.getAllotmentList();
      this.getConveyanceList();
      this.getmodifiedList();
      this.getCancelList();
      this.getConferenceList();
      // }
    }
  }

  async getApproveList() {
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.allDraftApplicationList(this.selctCity, this.selctGH).subscribe(res => {
      console.log(res);
      this.apprivedList = res.data;
      loading.dismiss();
      // if (res.status === 1) {
      //   this.allotmentList = res.data;
      // }
    }, error => {
      console.log(error);
      loading.dismiss()
    },
    ()=> {
      loading.dismiss();
    })
  }

  getAllotmentList() {
    this.mdmService.getApproveListDetails(this.selctCity, this.selctGH).subscribe(res => {
      if (res.status === 1) {
        this.allotmentList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  getConveyanceList() {
    this.mdmService.getConvayanceListDetails(this.selctCity, this.selctGH).subscribe(res => {
      if (res.status === 1) {
        this.conveyanceList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  getmodifiedList() {
    const obj = {
      cId: this.selctCity,
      gId: this.selctGH
    }
    this.mdmService.getModifiedListDetails(obj).subscribe(res => {
      if (res.status === 1) {
        this.modifiedList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  getCancelList() {
    const obj = {
      cId: this.selctCity,
      gId: this.selctGH
    }
    this.mdmService.getCancelListDetails(obj).subscribe(res => {
      if (res.status === 1) {
        this.canceList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  getConferenceList() {
    const obj = {
      cId: this.selctCity,
      gId: this.selctGH
    }
    this.mdmService.getAllConferenceDataForApproveForMobile(this.selctCity,this.selctGH).subscribe(res => {
      if (res.status === 1) {
        this.conferenceList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  onClickViewAllBtn(listType) {
    const obj = {
      'btnName': listType,
      'cityId': this.selctCity,
      'ghId': this.selctGH
    }
    console.log('list type', obj);
    this.commonService.storeDetails(obj);
    if (this.selctCity === '') {
      alert("Select City");
    } else if (this.selctGH === '') {
      alert("Select Guest House");
    } else {
      this.router.navigateByUrl('after-login/pages/orgadmin/draft-list');
    }
  }

  checkAll(event) {
    this.allChecked = !this.allChecked;
    this.myform.get('isChecked').setValue(this.allChecked);
    this.clearFromArray(this.checkedAllotmentList);
    if (event.target.checked) {
      for (const obj of this.allotmentList) {
        this.checkedAllotmentList.push(obj);
      }
    }

    console.log(this.checkedAllotmentList)
  }

  clearFromArray(arrayName: any) {
    if (arrayName) {
      arrayName.splice(0, arrayName.length);
    }
  }

  OnCheckedOne(item, event) {
    if (event.target.checked) {
      this.checkedAllotmentList.push(item);
    }

    if (!event.target.checked) {
      this.checkedAllotmentList.forEach((ele, ind) => {
        if (ele.id == item.id) {
          this.checkedAllotmentList.splice(ind, 1);
        }
      })
    }
    console.log(this.checkedAllotmentList)
  }

  async saveDraftList() {
    const loader = await this.loadingCtrl.create({
      message:'Please wait..'
    })

    let bookingIdList = [];
    let selected = '';
    if (this.allotmentList.length > 0 && (!this.allChecked && this.checkedAllotmentList.length == 0)) {
      const alert = await this.alertController.create({
        message: 'Please approve atlease one item',
        buttons: ['OK'],
      });
      await alert.present();
      return;
    }
    if(this.allotmentList.length == 0 && this.conveyanceList.length == 0 && this.modifiedList.length == 0 && this.canceList.length == 0 && this.conferenceList.length == 0) {
      const alert = await this.alertController.create({
        message: 'No data to Approve',
        buttons: ['OK'],
      });
      await alert.present();
      return;
    }
    if (this.allChecked) {
      selected = 'ALL';
    } else if (this.checkedAllotmentList.length) {
      this.checkedAllotmentList.forEach(ele => {
        if (ele.bookingId) {
          bookingIdList.push(ele.bookingId);
        }
      });
      selected = bookingIdList.toString();
    }
    const data = { 'cityId': this.selctCity, 'guesthouseId': this.selctGH, 'sign': this.approvedBy, 'selected': selected !== '' ? selected : bookingIdList }
    console.log('data', data);
    loader.present();
    this.mdmService.saveProcessApplicationList(data).subscribe(async res => {
      console.log(res);
      if (res.status === 1) {
        const alert = await this.alertController.create({
          message: res.statusDesc,
          buttons: ['OK'],
        });
        await alert.present();
        this.getAllotmentList();
        this.getConveyanceList();
        this.getmodifiedList();
        this.getCancelList();
        this.getConferenceList();
        this.myform.get('selectAllChecked').setValue(false);
        this.myform.get('isChecked').setValue(false);
        this.checkedAllotmentList = []
        loader.dismiss();
        return Promise.resolve(false);
      } else {
        loader.dismiss();
      }
    }, error => {
      console.log(error);
      loader.dismiss();
    },()=> {
      loader.dismiss();
    })
  }

  onChangeApprovedBy(event) {
    this.approvedBy = event.target.value;
    console.log(event.target.value, this.approvedBy);
  }

  changePageSize() { }

  async onClickApproveBtn(item) {
    const popover = await this.popover.create({
      component: ConfirmationComponent,
      translucent: true,
      componentProps: {
        data: item
      },
      backdropDismiss: false // Allow dismissing the popover by clicking the backdrop
    });

    // Present the popover and set a custom CSS class for the backdrop
    popover.present().then(() => {
      const backdrop: HTMLIonBackdropElement = popover.querySelector('ion-backdrop');
      if (backdrop) {
        backdrop.classList.add('custom-popover-backdrop');
      }
    });

    popover.onDidDismiss().then((data)=> {
      this.getApproveList();
    })
  }

  async onClickViewBtn(item) {
    console.log(item);
    const modal = await this.modalcontroller.create({
      component: OfficeOrderComponent,
      componentProps: { processId:item.processId, type:'draft'}
    });
    return await modal.present();

  }
  async onClickRejectBtn(item) {
    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to cancel?',
      buttons: [
        {
          text: 'Yes,Cancel it',
          cssClass: 'alertButton',
          handler: () => {
            this.mdmService.cancelProcessRequest(item.processId).subscribe(async res => {
              console.log(res);
              if (res.status === 1) {
                const alert = await this.alertController.create({
                  message: res.statusDesc,
                  buttons: ['OK'],
                });
                await alert.present();
                this.getApproveList();
                return Promise.resolve(false);
              }
            })
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

  public async goToViewDetails(bookingId,type) {
    const modal = await this.modalcontroller.create({
      component: ViewDetailsComponent,
      componentProps: { bookingId,viewType:type}
    });
    return await modal.present();
  }

}
