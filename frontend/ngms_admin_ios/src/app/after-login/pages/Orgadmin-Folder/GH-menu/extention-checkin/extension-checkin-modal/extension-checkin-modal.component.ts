import { DatePipe } from '@angular/common';
import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NavParams, ModalController, LoadingController, AlertController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-extension-checkin-modal',
  templateUrl: './extension-checkin-modal.component.html',
  styleUrls: ['./extension-checkin-modal.component.scss'],
})
export class ExtensionCheckinModalComponent implements OnInit {
  public modalData;
  public responseData;
  public selectedGuestIds;

  constructor(private navParams: NavParams,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private loadingController: LoadingController,
    private commonService: CommonService) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.modalData = this.navParams.data.finalGuests;
    this.selectedGuestIds = this.modalData.hidval.split("','");
    console.log(this.modalData, this.selectedGuestIds)
    this.viewExtensionCheckinDetailsForMobile(this.modalData)
  }

  public async viewExtensionCheckinDetailsForMobile(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewExtensionCheckinDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          this.responseData = res.data;
          console.log("viewCheckinDetailsForMobile", this.responseData)
          loading.dismiss()
        } else {
          loading.dismiss()
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

  private formatTimeComponent(component: number): string {
    return component < 10 ? `0${component}` : component.toString();
  }

  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  public async submit() {
    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;
    date.setDate(date.getDate())
    let currDate = date.toISOString().slice(0, 10) + " " + currentTime
    console.log("currDate", currDate)
    let fromDate = this.changeDateFormatyymmdd(this.modalData.fromDate.split(' ')[0])
    fromDate = fromDate + " " + currentTime

    console.log("currDate < fromDate", currDate < fromDate)
    if (currDate < fromDate) {
      this.commonService.showErrorToast("You can't checkin before it's from date.")
      return;
    }
    let guestDetails = [];

    for (let i = 0; i < this.responseData.length; i++) {
      guestDetails.push({
        "gstId": this.responseData[i].guestId,
        "ReqId": this.responseData[i].reqId,
        "roll": this.responseData[i].rollNo,
        "intime": this.responseData[i].arrivalTime,
        "advncAmount": this.responseData[i].advncAmount,
        "adults": this.responseData[i].adults,
        "children": this.responseData[i].childrenBelow,
        "newGstId": this.selectedGuestIds[i]
      })
    }
    let data = {
      "city": this.modalData.cId,
      "gHouse": this.modalData.gId,
      "newReqId": this.modalData.req_id,
      "guestDetails": guestDetails
    }

    console.log("Data", data)
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.saveExtensionCheckinDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          this.modalcontroller.dismiss()
          this.commonService.showSuccessToast(res.statusDesc)
        } else {
          loading.dismiss()
          this.commonService.showSuccessToast(res.statusDesc)
        }
      },
      err => {
        loading.dismiss()
      },
      () => {
        loading.dismiss();
      }
    )

  }

  goBack() {
    this.modalcontroller.dismiss();
  }

}
