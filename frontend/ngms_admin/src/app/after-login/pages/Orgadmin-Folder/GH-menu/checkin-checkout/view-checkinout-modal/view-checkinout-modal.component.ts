import { DatePipe } from '@angular/common';
import { Component, ElementRef, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NavParams, ModalController, LoadingController, AlertController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-view-checkinout-modal',
  templateUrl: './view-checkinout-modal.component.html',
  styleUrls: ['./view-checkinout-modal.component.scss'],
})
export class ViewCheckinoutModalComponent implements OnInit {

  public modalData;
  public btntype;
  public headerName;
  public responseData;
  public currentDate = new Date().toISOString().slice(0, 16);
  public maxDate;
  public minDate;
  public maxTime;
  public minTime;
  public extraGuest = "";
  public shiftId;
  @ViewChildren("coutDate") coutDate: QueryList<ElementRef>;
  @ViewChildren("rollNo") rollNo: QueryList<ElementRef>;
  @ViewChildren("cinTime") cinTime: QueryList<ElementRef>;
  @ViewChildren("advAmount") advAmount: QueryList<ElementRef>;

  constructor(private navParams: NavParams,
    private modalcontroller: ModalController,
    private datePipe: DatePipe,
    private mdmService: MdmService,
    private loadingController: LoadingController,
    private commonService: CommonService) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.modalData = this.navParams.data.data;
    this.btntype = this.navParams.data.btnType;
    this.shiftId = this.navParams.data.shiftId;
    console.log("FinalData", this.modalData)
    if (this.btntype == 'CIN') {
      this.headerName = "Check In"
      this.viewCheckinDetails(this.modalData)
    }
    if (this.btntype == 'COUT') {
      this.headerName = "Check Out"
      this.viewCheckoutDetails(this.modalData)
    }
  }

  public async viewCheckinDetails(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewCheckinDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          this.responseData = res.data;
          console.log("viewCheckinDetailsForMobile", this.responseData)
          loading.dismiss()
          this.setMinMaxDateForCIN();
        } else {
          loading.dismiss()
        }
      },
      () => {
        loading.dismiss();
        console.log("Something Went Wrong")
      },
    )
  }
  public async viewCheckoutDetails(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewCheckOutDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          this.responseData = res.data;
          console.log("viewCheckinDetailsForMobile", this.responseData);
          loading.dismiss()
          this.setMinMaxDateForCOUT();
        } else {
          loading.dismiss()
        }
      },
      () => {
        loading.dismiss();
        console.log("Something Went Wrong")
      },
    )
  }

  public setMinMaxDateForCOUT() {
    let frmDate = this.datePipe.transform(this.modalData.frmdata.split('-').reverse().join('-'), 'yyyy-MM-dd');
    let toDate = this.datePipe.transform(this.modalData.toData.split('-').reverse().join('-'), 'yyyy-MM-dd');
    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;
    date.setDate(date.getDate())
    let currDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    console.log("jgh", currDate, frmDate, toDate)
    if (currDate <= toDate) {
      console.log("if Part")
      this.minDate = this.datePipe.transform(frmDate, 'yyyy-MM-dd HH:mm')
      this.maxDate = this.currentDate.slice(0, 10) + "T" + currentTime;
    } else {
      console.log("else part")
      this.minDate = this.datePipe.transform(frmDate, 'yyyy-MM-dd HH:mm')
      this.maxDate =this.datePipe.transform(toDate, 'yyyy-MM-dd HH:mm')
      console.log(this.currentDate, currentTime)
    }
    console.log("jgh", this.maxDate, this.minDate)

    // let frmDate = new Date(this.modalData.frmdata)
    // let toDate = new Date(this.modalData.toData)
    // const date = new Date();
    // const hours = date.getHours();
    // const minutes = date.getMinutes();
    // let currentTime = `${this.formatTimeComponent(hours)}:${this.formatTimeComponent(minutes)}`;
    // date.setDate(date.getDate())
    // if (date.setHours(0, 0, 0, 0) <= frmDate.setHours(0, 0, 0, 0)) {
    //   console.log("if Part")
    //   this.minDate = this.datePipe.transform(frmDate, 'yyyy-dd-MM HH:mm')
    //   this.maxDate = this.currentDate;
    // } else {
    //   console.log("else part")
    //   this.minDate = this.currentDate.slice(0, 10) + "T" + currentTime;
    //   this.maxDate = this.currentDate.slice(0, 10) + "T" + currentTime;
    //   console.log(this.currentDate, currentTime)
    // }
  }

  public setMinMaxDateForCIN() {
    console.log("Entered")
    // dateString.split('-').reverse().join('-');
    let frmDate = this.datePipe.transform(this.modalData.fromDate.split('-').reverse().join('-'), 'yyyy-MM-dd');
    let toDate = this.datePipe.transform(this.modalData.toDate.split('-').reverse().join('-'), 'yyyy-MM-dd');

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
      console.log('min date', this.minDate, this.maxDate)
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


  public async submit() {
    let roomDetails = []
    let isCorrect = false;
    for (let i = 0; i < this.responseData?.guestDetails.length; i++) {
      console.log(this.responseData?.guestDetails[i].reqId)
      this.coutDate.forEach((input: ElementRef, j) => {
        if (this.responseData.guestDetails[i].guestId == this.coutDate.toArray()[j]?.nativeElement.id) {
          if (this.coutDate.toArray()[j].nativeElement.value == '') {
            console.log("Inside If")
            this.commonService.showErrorToast("Please provide checkout time")
            isCorrect = false;
          } else {
            console.log("Inside Else")
            let coutDt = this.coutDate.toArray()[j].nativeElement.value;
            console.log(coutDt)
            console.log(this.datePipe.transform(coutDt, 'dd-MM-yyyy'))
            coutDt = coutDt.slice(0, 10)
            let coutTime = (this.coutDate.toArray()[j].nativeElement.value).slice(11, 16)
            roomDetails.push({
              "gstId": this.responseData.guestDetails[i].guestId,
              "trId": this.responseData.guestDetails[i].trId,
              "ReqId": this.responseData.guestDetails[i].reqId,
              "checkOut": this.datePipe.transform(coutDt, 'dd-MM-yyyy') + " " + coutTime,
              "parentTrId": this.responseData.guestDetails[i].parentTrId
            })
            isCorrect = true;
          }
        }
      })
    }

    let data = {
      "city": this.modalData.ghId,
      "gHouse": this.modalData.blockId,
      "roomDetails": roomDetails
    }
    if (isCorrect) {
      console.log("Submit", data)
      const loading = await this.loadingController.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.saveCheckOutDetailsForMobile(data).subscribe(
        res => {
          if (res.status == 1) {
            loading.dismiss();
            this.commonService.showSuccessToast("CheckOut Successfull.")
            this.modalcontroller.dismiss();
          } else {
            loading.dismiss();
            this.commonService.showErrorToast(res.statusDesc)
          }
        },
        ()=> {
          loading.dismiss()
        }
      )
    }
  }

  public async submitCheckIn() {
    // let rollNo, coutTime, advAmount;
    console.log("dfgdjgh")
    let isCorrect = false;
    let total = 0;
    let data = [];
    let sameDate = true;
    let prevDate = true;
    for (let i = 0; i < this.responseData.guestDetails.length; i++) {
      console.log("=======>   ", i)
      this.rollNo.forEach((r: ElementRef, j) => {
        if (this.responseData.guestDetails[i].guestId == this.rollNo.toArray()[j]?.nativeElement.id) {
          if (this.rollNo.toArray()[j].nativeElement.value == '') {
            console.log("Inside No Roll No")
            this.commonService.showErrorToast("Please provide register no")
            isCorrect = false;
          } else {
            console.log(this.rollNo.toArray()[j]?.nativeElement.value)
            this.cinTime.forEach((c: ElementRef, k) => {
              console.log(this.cinTime.toArray()[k]?.nativeElement.id)
              if (this.responseData.guestDetails[i].guestId == this.cinTime.toArray()[k]?.nativeElement.id) {
                console.log(this.cinTime.toArray()[k]?.nativeElement.value)
                if (this.cinTime.toArray()[k].nativeElement.value == '') {
                  console.log("Inside No Time")
                  this.commonService.showErrorToast("Please Provide time")
                  isCorrect = false;
                } else {
                  this.advAmount.forEach((a: ElementRef, l) => {
                    if (this.responseData.guestDetails[i].guestId == this.advAmount.toArray()[l]?.nativeElement.id) {
                      console.log(this.rollNo.toArray()[j]?.nativeElement.value)
                      // if (this.advAmount.toArray()[l].nativeElement.value == '') {
                      //   console.log("Inside No Advance Amount")
                      //   this.commonService.showErrorToast("Please Provide advance amount.")
                      //   isCorrect = false;
                      // } else {
                      total = total + parseInt(this.advAmount.toArray()[l].nativeElement.value)
                      let d = (this.cinTime.toArray()[k].nativeElement.value).slice(0, 10);
                      let t = (this.cinTime.toArray()[k].nativeElement.value).slice(11, 16);

                      const date = new Date();
                      const hours = date.getHours();
                      const minutes = date.getMinutes();
                      date.setDate(date.getDate())
                      let currDate = date.toISOString().slice(0, 10)
                      console.log("currDate", currDate)
                      let fromDate = this.changeDateFormatyymmdd(this.modalData.fromDate.split(' ')[0])
                      // fromDate = fromDate

                      let inTime = d
                      // fromDate = fromDate + " " + currentTime

                      console.log("currDate < inTime", fromDate, d, currDate, currDate < inTime)
                      if (currDate == fromDate || currDate > fromDate) {
                        if (fromDate != inTime) {
                          sameDate = false;
                        }
                      } else {
                        prevDate = false;
                      }

                      data.push({
                        "gstId": this.responseData.guestDetails[i].guestId,
                        "rollNo": this.rollNo.toArray()[j].nativeElement.value,
                        "intime": this.datePipe.transform(d, 'dd-MM-yyyy') + " " + t,
                        "children": '0',
                        "advncamount": this.advAmount.toArray()[l].nativeElement.value,
                        "adults": '1',
                        "category": this.responseData.selectDesgination.id,
                        "purpose": this.responseData.selectPurpose,
                        "room": this.responseData.checkinRoom[0].type,
                      })
                      isCorrect = true;
                      // }
                    }
                  })
                }
              }
            })
          }
        }
      })
    }
    if (total > parseInt(this.responseData?.roomRent)) {
      this.commonService.showErrorToast("Advance Amount cann't be greater than total amount.")
      isCorrect = false;
    }

    if (!prevDate) {
      this.commonService.showErrorToast("You can't check-in someone before it's from Date");
      isCorrect = false;
    }
    let finalData = {
      ReqId: this.responseData.guestDetails[0].reqId,
      shiftId: this.shiftId,
      city: this.modalData.cId,
      extraGuest: this.extraGuest,
      gHouse: this.modalData.gId,
      roomDetails: data
    }


    if (isCorrect) {
      console.log("Final Data", finalData)

      const loading = await this.loadingController.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.saveCheckinDetailsForMobile(finalData).subscribe(
        res => {
          if (res.status == 1) {
            loading.dismiss();
            this.commonService.showSuccessToast("CheckIn Successfull.")
            this.modalcontroller.dismiss();
          } else {
            loading.dismiss();
            this.commonService.showErrorToast(res.statusDesc)
          }
        },
      
        ()=> {
          loading.dismiss()
        }
      )
    }
  }

  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  private formatTimeComponent(component: number): string {
    return component < 10 ? `0${component}` : component.toString();
  }

  public getName(data) {
    let finalName = '';
    if (data.designation == '' && data.department == '') {
      finalName = data.name;
    } else if (data.designation == '') {
      finalName = data.name + "," + data.department
    } else if (data.department == '') {
      finalName = data.name + "," + data.designation
    } else {
      finalName = data.name + "," + data.department + "," + data.designation
    }
    return finalName
  }

  goBack() {
    this.modalcontroller.dismiss();
  }

}
