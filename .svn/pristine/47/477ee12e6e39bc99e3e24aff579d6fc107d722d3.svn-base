import { DatePipe } from '@angular/common';
import { Component, ElementRef, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController, LoadingController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-edit-cancel-checkinout',
  templateUrl: './edit-cancel-checkinout.component.html',
  styleUrls: ['./edit-cancel-checkinout.component.scss'],
})
export class EditCancelCheckinoutComponent implements OnInit {

  @ViewChildren("rollNo") rollNo: QueryList<ElementRef>;
  @ViewChildren("coutTime") coutTime: QueryList<ElementRef>;
  @ViewChildren("advAmount") advAmount: QueryList<ElementRef>;

  public responseData = [];
  public type;
  public filterObj;
  public maxDate;
  public minDate;
  public maxTime;
  public minTime;
  public currentDate = new Date().toISOString().slice(0, 16);
  public extraGuest;

  constructor(
    private modalCtrl: ModalController,
    private loadingCtrl: LoadingController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private navParams: NavParams,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    console.log("Inside ngOninit")
  }
  ionViewWillEnter() {
    console.log("Inside ionViewWillEnter")
    this.filterObj = this.navParams.data.finalGuests;
    this.type = this.navParams.data.btn;
    console.log(this.type)
    // console.log("filterObj", this.filterObj.fromDate)
    this.getCheckinDetails()
  }

  public async getCheckinDetails() {
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewCheckInCancelDetailsForMobile(this.filterObj).subscribe(
      res => {
        if (res.status == 1) {
          this.responseData = res.data;
          loading.dismiss();
          if (this.type == 'EditCIN') {
            this.setMinMaxDate();
          }
        } else {
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      ()=> {
        loading.dismiss()
      }
    )
  }

  public convertToValidaDate(date) {
    let fDate = this.datePipe.transform(this.filterObj.fromDate.split('-').reverse().join('-'), 'yyyy-MM-dd');
    let fTime = date.slice(11, 16)
    return fDate + "T" + fTime;
  }

  public setMinMaxDate() {
    console.log("Entered")
    // dateString.split('-').reverse().join('-');
    let frmDate = this.datePipe.transform(this.filterObj.fromDate.split('-').reverse().join('-'), 'yyyy-MM-dd');
    let toDate = this.datePipe.transform(this.filterObj.toDate.split('-').reverse().join('-'), 'yyyy-MM-dd');

    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;
    date.setDate(date.getDate())
    let currDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    console.log("jgh", currDate, frmDate, toDate)
    if (currDate === frmDate) {
      console.log("if Part");
      this.minDate = this.currentDate.slice(0, 10) + "T" + currentTime;
      this.maxDate = this.currentDate.slice(0, 10) + "T" + currentTime;
      console.log('min date', this.minDate)
    }
    if (currDate > frmDate) {
      if (currDate < toDate) {
        console.log("else part");
        this.minDate = this.datePipe.transform(frmDate, 'yyyy-MM-dd HH:mm');
        this.maxDate = this.currentDate;
      } else if (currDate > toDate) {
        console.log("else part");
        this.minDate = this.datePipe.transform(frmDate, 'yyyy-MM-dd HH:mm');
        this.maxDate = this.datePipe.transform(toDate, 'yyyy-MM-dd HH:mm');
      } else {
        console.log("else part");
        this.minDate = this.datePipe.transform(frmDate, 'yyyy-MM-dd HH:mm');
        this.maxDate = this.datePipe.transform(toDate, 'yyyy-MM-dd HH:mm');
        console.log('min date', this.minDate)
      }
    }
  }

  private formatTimeComponent(component: number): string {
    return component < 10 ? `0${component}` : component.toString();
  }

  public async editCheckIn() {
    // let rollNo, coutTime, advAmount;
    let isCorrect = false;
    let data = []
    let total = 0;
    for (let i = 0; i < this.responseData.length; i++) {
      console.log("=======>   ", i)
      this.rollNo.forEach((r: ElementRef, j) => {
        if (this.responseData[i].guestId == this.rollNo.toArray()[j]?.nativeElement.id) {
          if (this.rollNo.toArray()[j].nativeElement.value == '') {
            console.log("Inside No Roll No")
            this.commonService.showErrorToast("Please provide register nno")
            isCorrect = false;
          } else {
            console.log(this.rollNo.toArray()[j]?.nativeElement.value)
            this.coutTime.forEach((c: ElementRef, k) => {
              console.log(this.coutTime.toArray()[k]?.nativeElement.id)
              if (this.responseData[i].guestId == this.coutTime.toArray()[k]?.nativeElement.id) {
                console.log(this.coutTime.toArray()[k]?.nativeElement.value)
                if (this.coutTime.toArray()[k].nativeElement.value == '') {
                  console.log("Inside No Time")
                  this.commonService.showErrorToast("Please Provide time")
                  isCorrect = false;
                } else {
                  this.advAmount.forEach((a: ElementRef, l) => {
                    if (this.responseData[i].guestId == this.advAmount.toArray()[l]?.nativeElement.id) {
                      console.log(this.rollNo.toArray()[j]?.nativeElement.value)
                      // if (this.advAmount.toArray()[l].nativeElement.value == '') {
                      //   console.log("Inside No Advance Amount")
                      //   this.commonService.showErrorToast("Please Provide advance amount.")
                      //   isCorrect = false;
                      // } else {
                        total = total + parseInt(this.advAmount.toArray()[l].nativeElement.value)
                      let d = (this.coutTime.toArray()[k].nativeElement.value).slice(0, 10);
                      let t = (this.coutTime.toArray()[k].nativeElement.value).slice(11, 16);
                      data.push({
                        "gstId": this.responseData[i].guestId,
                        "trId": this.responseData[i].trId,
                        "ReqId": this.responseData[i].reqId,
                        "roll": this.rollNo.toArray()[j].nativeElement.value,
                        "Intime": this.datePipe.transform(d, 'dd-MM-yyyy') + " " + t,
                        "advncAmount": this.advAmount.toArray()[l].nativeElement.value
                      })
                      isCorrect = true;
                    }
                    // }
                  })
                }
              }
            })
          }
        }
      })
    }
    let finalData = {
      city: this.filterObj.ghId,
      extraGuest: this.extraGuest,
      gHouse: this.filterObj.blockId,
      guestDetails: data
    }
      console.log(total,parseInt(this.responseData[0].totalAmount))
    if (total > parseInt(this.responseData[0].totalAmount)) {
      this.commonService.showErrorToast("Advance Amount cann't be greater than total amount.")
      isCorrect = false;
    }

    if (isCorrect) {
      console.log("Final Data", finalData)

      const loading = await this.loadingCtrl.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.editCheckInCancelDetailsForMobile(finalData).subscribe(
        res => {
          if (res.status == 1) {
            loading.dismiss();
            this.commonService.showSuccessToast("CheckIn Edit Successfull.")
            this.modalCtrl.dismiss();
          } else {
            loading.dismiss();
            this.commonService.showErrorToast(res.statusDesc)
          }
        },
        err => {
          loading.dismiss();
          this.commonService.showErrorToast("Something Went Wrong.")
        },
        ()=> {
          loading.dismiss()
        }
      )
    }
  }

  public async cancelCheckin() {
    // let rollNo, coutTime, advAmount;
    let data = []
    for (let i = 0; i < this.responseData.length; i++) {
      console.log("=======>   ", i)
      data.push({
        "gstId": this.responseData[i].guestId,
        "trId": this.responseData[i].trId,
        "ReqId": this.responseData[i].reqId,
        "parentTrId": this.responseData[i].parentTrId,
        "ext": this.responseData[i].ext,
        "advncAmount": this.responseData[i].advncAmount,
      })
      // this.rollNo.forEach((r: ElementRef, j) => {
      //   if (this.responseData[i].guestId == this.rollNo.toArray()[j]?.nativeElement.id) {

      //     console.log(this.rollNo.toArray()[j]?.nativeElement.value)
      //     this.coutTime.forEach((c: ElementRef, k) => {
      //       console.log(this.coutTime.toArray()[k]?.nativeElement.id)
      //       if (this.responseData[i].guestId == this.coutTime.toArray()[k]?.nativeElement.id) {
      //         console.log(this.coutTime.toArray()[k]?.nativeElement.value)

      //         this.advAmount.forEach((a: ElementRef, l) => {
      //           if (this.responseData[i].guestId == this.advAmount.toArray()[l]?.nativeElement.id) {
      //             console.log(this.rollNo.toArray()[j]?.nativeElement.value)

      //             let d = (this.coutTime.toArray()[k].nativeElement.value).slice(0, 10);
      //             let t = (this.coutTime.toArray()[k].nativeElement.value).slice(11, 16);
      //             data.push({
      //               "gstId": this.responseData[i].guestId,
      //               "trId": this.responseData[i].trId,
      //               "ReqId": this.responseData[i].reqId,
      //               "parentTrId": this.rollNo.toArray()[j].nativeElement.value,
      //               "ext": this.datePipe.transform(d, 'dd-MM-yyyy') + " " + t,
      //               "advncAmount": this.advAmount.toArray()[l].nativeElement.value
      //             })

      //           }
      //         })
      //       }
      //     })
      //   }
      // })
    }
    let finalData = {
      city: this.filterObj.ghId,
      gHouse: this.filterObj.blockId,
      roomDetails: data
    }

    console.log("Final Data", finalData)

    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.updateCheckOutCancelDetailsForMobile(finalData).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          this.commonService.showSuccessToast("CheckIn cancelled Successfully.")
          this.modalCtrl.dismiss();
        } else {
          loading.dismiss();
          this.commonService.showErrorToast(res.statusDesc)
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      ()=>{
        loading.dismiss()
      }
    )
  }

  public goBack() {
    this.modalCtrl.dismiss();
  }

}
