import { DatePipe } from '@angular/common';
import { HttpParams } from '@angular/common/http';
import { Component, ElementRef, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NavParams, ModalController, AlertController, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-modify-moderation',
  templateUrl: './modify-moderation.component.html',
  styleUrls: ['./modify-moderation.component.scss'],
})
export class ModifyModerationComponent implements OnInit {

  public modalData;
  public type;
  public bookingId;
  public modId;
  public mrrId;
  public cityId;
  public ghId;
  public roomDetails;
  public roomNos;
  // public bedDetails;
  public bedNos = '';
  public count = 0;
  public drmList = [];
  public booked = false;
  public prevOrderNo = '';
  public prevOrderDt;
  public reason = '';

  // public selectedRoom;
  selectedRooms: { [date: string]: string } = {};
  public selectedIndex;
  public selectedRoomDetails = [];

  @ViewChildren("room") room: QueryList<ElementRef>;
  @ViewChildren("rent") rent: QueryList<ElementRef>;
  @ViewChildren("roomBtn") roomBtn: QueryList<ElementRef>;

  activeButtonIndex: number = -1;

  constructor(
    private navParams: NavParams,
    private modalController: ModalController,
    private alertController: AlertController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private loadingController: LoadingController,
    private datePipe:DatePipe
  ) { }

  ngOnInit() {

  }

  ionViewWillEnter() {
    this.modalData = this.navParams.data.data;
    this.type = this.navParams.data.type;
    this.bookingId = this.modalData.bookingId;
    this.modId = this.modalData.modId;
    this.mrrId = this.modalData.mrrId;
    this.cityId = this.navParams.data.cityAndGhIds.cityId;
    this.ghId = this.navParams.data.cityAndGhIds.ghId;
    console.info("Data Passed to this component:::::::::", this.modalData);
    this.prevOrderNo = this.modalData?.rowNo;
    const parts = this.modalData?.prevOrderDt.split('-');
    const formattedDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);
    this.prevOrderDt = this.datePipe.transform(formattedDate,'yyyy-MM-dd') 
    this.reason = this.modalData?.remarks;
    if (this.modalData.type == "Room") {
      this.getRoomDetails(this.modalData);
    } else {
      this.getDormitoryDetails(this.modalData);
    }



  }

  public async getRoomDetails(data) {
    const params = new HttpParams()
      .set("bookingId", this.bookingId)
      .set("cityId", this.cityId)
      .set("ghId", this.ghId);
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService
      .getTotalRoomsForMobile(this.bookingId, this.cityId, this.ghId)
      .subscribe((res) => {
        if (res.status == 1) {
          console.log(res);
          this.roomDetails = res.data;
          this.roomNos = this.roomDetails.roomNos;
          this.drmList = this.roomDetails.drmList;
          loading.dismiss()
          console.log(this.drmList);
          this.setRoomDetails(this.drmList);
        } else {
          this.commonService.showErrorToast(res.statusDesc);
          loading.dismiss()
        }
      },err=>{
        loading.dismiss()
      },
      ()=> {
        loading.dismiss()
      });
  }

  public setRoomDetails(roomdata) {
    if (this.modalData?.type != 'Room') {
      this.drmList.forEach((drm) => {
        let d = [];
        d.push(drm);
        console.log(d);
        d.forEach((ele) => {
          if (ele.mrrId == this.mrrId) {
            console.log(ele.mrrId == this.mrrId);
            this.booked = true;
            console.log(this.booked);
          } else {
            this.booked = false;
          }
        });
      });
    }
    console.log(roomdata)
    console.log("###################################")
    for (let i = 0; i < roomdata.length; i++) {
      const element = roomdata[i];
      let room = roomdata[i].roomDetails;
      for (let j = 0; j < room.length; j++) {
        let r = roomdata[i].roomDetails[j]
        // console.log("::::::::::", r)
        for (let k = 0; k < r.roomDetails.length; k++) {
          console.log("fhjd", r.roomDetails[k])
          if (r.roomDetails[k].mrrId == this.mrrId) {
            this.selectedRoomDetails.push({
              fromDate: element.dateStr,
              roomNo: r.roomDetails[k].roomNo,
              roomRent: r.roomDetails[k].roomRent,
              allotId: r.roomDetails[k].allotId,
              bedNo: r.roomDetails[k].bedNo
            })
            this.selectedRooms[element.dateStr] = r.roomDetails[k].roomNo + r.roomDetails[k].bedNo;
          }
        }
        if (r.allotId != null) {
          console.log("dfhjj")
          if (r.mrrId == this.mrrId) {
            console.log("::::::::::", r)

            this.selectedRoomDetails.push({
              fromDate: element.dateStr,
              roomNo: r.roomNo,
              roomRent: r.roomRent,
              allotId: r.allotId,
              bedNo: r.bedNo
            })
            this.selectedRooms[element.dateStr] = r.roomNo;
          }
        }
      }
    }
    console.log("###################################")
    console.log("selectedRoomDetails", this.selectedRoomDetails)
    console.log("selectedRooms", this.selectedRooms)
  }

  public selectRoom(data, r, roomOrBedNo, type) {
    console.log(this.selectedRooms);
    const updatedRoomDetails = [...this.selectedRoomDetails];

    this.rent.forEach((input: ElementRef, i) => {
      if (data.dateStr === this.rent.toArray()[i].nativeElement.id) {
        console.log(this.rent.toArray()[i].nativeElement.value);
        this.rent.toArray()[i].nativeElement.value = ""
        for (let j = updatedRoomDetails.length - 1; j >= 0; j--) {
          const el = updatedRoomDetails[j];
          if (el.fromDate === data.dateStr) {
            updatedRoomDetails.splice(j, 1);
            console.log("After Splice ====", j, updatedRoomDetails);
          }
        }

        // for (const el of this.selectedRoomDetails) {
        //   if (el.fromDate === data.dateStr) {
        //     this.selectedRoomDetails.splice(i,1)
        //     console.log("After Splice====",i,this.selectedRoomDetails)
        //   }
        // }
      }
    })
    this.selectedRoomDetails = updatedRoomDetails;
    console.log("Final:::", this.selectedRoomDetails)
    if (this.modalData?.type != 'Room') {
      // this.selectedRooms[data.dateStr] = r.bedNo;
      this.selectedRooms[data.dateStr] = r.roomNo + '/ ' + r.bedNo;
      this.room.forEach((input: ElementRef, i) => {
        if (data.dateStr == this.room.toArray()[i].nativeElement.id) {
          this.room.toArray()[i].nativeElement.value = r.roomNo + "," + r.bedNo;
        }
        console.log(this.room.toArray()[i].nativeElement.id);
      });
    } else {
      if (type == 'bed') {
        this.selectedRooms[data.dateStr] = r.roomNo + roomOrBedNo;
      } else {
        this.selectedRooms[data.dateStr] = r.roomNo;
      }
      console.log(data.dateStr)
      this.room.forEach((input: ElementRef, i) => {
        if (data.dateStr == this.room.toArray()[i].nativeElement.id) {
          if (type == 'bed') {
            this.room.toArray()[i].nativeElement.value = r.roomNo + "," + roomOrBedNo;
          } else {
            this.room.toArray()[i].nativeElement.value = r.roomNo;
          }
        }
        console.log(this.room.toArray()[i].nativeElement.id);
      });
    }
  }

  isRoomSelected(room, date: string, roomOrBedNo, type): boolean {
    if (this.modalData.type == 'Room') {
      if (type == 'bed') {
        return this.selectedRooms[date] === room.roomNo + roomOrBedNo;
      } else {
        return this.selectedRooms[date] === room.roomNo;
      }
    }
    if (this.modalData.type == 'Bed') {
      return this.selectedRooms[date] === room.roomNo + '/ ' + room.bedNo;
    }
  }

  public checkCount(data) {
    let count = 0;
    data.forEach(el => {
      if (el.mrrId != null && el.mrrId != this.mrrId) {
        count++;
      }
    });
    return count;
  }

  public generateRange(n: number): number[] {
    return Array(n).fill(0).map((_, index) => index);
  }

  public async onInputFocus(data, rent) {
    const element = rent;
    const elementId = element.id;
    let actionTaken = false;
  
    this.room.forEach(async (input: ElementRef, i) => {
      if (data.dateStr === this.room.toArray()[i].nativeElement.id) {
        if (this.room.toArray()[i].nativeElement.value == '') {
          console.log("4657537845");
  
          // Check if the action has already been taken
          if (!actionTaken) {
            console.log("tttt");
            console.log(this.rent.toArray()[i].nativeElement.value);
            this.rent.toArray()[i].nativeElement.value = "";
  
            const alert = await this.alertController.create({
              message: "Please Select Room First.",
              buttons: [
                {
                  text: 'OK',
                  cssClass: 'alertButton',
                  handler: () => {
                    // Handle the alert button click if needed
                  },
                }
              ],
            });
            await alert.present();
  
            actionTaken = true; // Set the flag to true to indicate that the action has been taken
          }
        }
      }
    });
  }
  public async addRent(event, data, drm) {
    console.log(drm)
    console.log(event.target.value);
    console.log("7676767676767676767676767676", this.selectedRoomDetails, data.dateStr)
    // if (event.target.value != "") {
    this.room.forEach(async (input: ElementRef, i) => {
      if (data.dateStr === this.room.toArray()[i].nativeElement.id) {
        console.log(this.room.toArray()[i].nativeElement.value);

        if (this.modalData?.type != 'Room') {
          let rNo = this.room.toArray()[i].nativeElement.value.split(',');
          console.log(rNo)
          let roomNo = rNo[0];
          let bedNo = rNo[1];
          if (this.selectedRoomDetails.length > 0) {
            let index;
            index = this.selectedRoomDetails.findIndex(r => r.fromDate === data.dateStr);
            // const existingReservation = this.selectedRoomDetails.find(r => r.fromDate === data.dateStr);
            console.log(index)
            if (index === -1) {
              this.selectedRoomDetails.push({
                bedNo: bedNo,
                fromDate: data.dateStr,
                roomNo: roomNo,
                roomRent: event.target.value,
                allotId: drm.allotId
              });
              console.log('Reservation pushed successfully!', this.selectedRoomDetails);
            } else {
              this.selectedRoomDetails.splice(index, 1, {
                bedNo: bedNo,
                fromDate: data.dateStr,
                roomNo: roomNo,
                roomRent: event.target.value,
                allotId: drm.allotId

              });
              console.log('Reservation already exists for the given fromDate.', this.selectedRoomDetails);
            }
          } else {
            this.selectedRoomDetails.push({
              bedNo: bedNo,
              fromDate: data.dateStr,
              roomNo: roomNo,
              roomRent: event.target.value,
              allotId: drm.allotId

            });
            console.log('Reservation fgjhdgh for the given fromDate.', this.selectedRoomDetails);

          }
        } else {
          let rNo = this.room.toArray()[i].nativeElement.value
          rNo = rNo.split(",");
          // roomNo = bedNo[0];
          // bedNo = bedNo[1];
          // console.log(rNo.split(","))
          if (this.selectedRoomDetails.length > 0) {
            let index;
            index = this.selectedRoomDetails.findIndex(r => r.fromDate === data.dateStr);
            // const existingReservation = this.selectedRoomDetails.find(r => r.fromDate === data.dateStr);
            console.log("####################################", index)
            if (index === -1) {
              console.log("Entered...")
              this.selectedRoomDetails.push({
                fromDate: data.dateStr,
                roomNo: rNo[0],
                bedNo: rNo[1],
                roomRent: event.target.value,
                allotId: drm.allotId
              });
              console.log('Reservation pushed successfully!', this.selectedRoomDetails);
            } else {
              console.log("Entered...")
              this.selectedRoomDetails.splice(index, 1, {
                fromDate: data.dateStr,
                roomNo: rNo[0],
                bedNo: rNo[1],
                roomRent: event.target.value,
                allotId: drm.allotId

              });
              console.log('Reservation already exists for the given fromDate.', this.selectedRoomDetails);
            }
          } else {
            console.log("Entered...")
            this.selectedRoomDetails.push({
              fromDate: data.dateStr,
              roomNo: rNo[0],
              bedNo: rNo[1],
              roomRent: event.target.value,
              allotId: drm.allotId

            });
          }
        }

      }
    });
  }

  public onChangePrevOrderNo(event) {
    this.prevOrderNo = event.target.value;
  }

  public onChangeReason(event) {
    this.reason = event.target.value;
  }

  public verifyData(): boolean {
    console.log("this.drmList.length", this.drmList.length)
    console.log("this.selectedRoomDetails.length", this.selectedRoomDetails.length)

    if (this.drmList.length !== this.selectedRoomDetails.length) {
      console.log(false);
      return false;
    }

    for (const el of this.selectedRoomDetails) {
      if (el.roomNo === '' || el.roomRent === '') {
        console.log(false);
        return false;
      }
    }

    console.log(true);
    return true;
  }

  public async allotRoom() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });

      if (this.prevOrderNo === '') {
        this.commonService.showErrorToast("Please Provide Previous Order No.")
        return;
      }
      if (this.prevOrderDt === '' || this.prevOrderDt == null) {
        this.commonService.showErrorToast("Please Provide Previous Order Date.")
        return;
      }
    

    const canSubmit = await this.verifyData();
    console.log('canSubmit', canSubmit);

    if (!canSubmit) {
      if (this.modalData?.type === "Bed") {
        this.commonService.showErrorToast("Please provide bed no and rent");
      } else if (this.modalData?.type === "Room") {
        this.commonService.showErrorToast("Please provide room no and rent");
      }
      return false;
    }

    if (canSubmit) {
      let finalData;

        finalData = {
          prevOrderNo: this.prevOrderNo,
          reason: this.reason,
          prevOrderDt: this.datePipe.transform(this.prevOrderDt,'dd-MM-yyyy'),
          roomDetails: this.selectedRoomDetails,
        };

  //       {
  //         "prevOrderNo": "l245799",
  //         "reason": "eughobcti fyibdeio ruovboro yovxsr",
  //         "prevOrderDt": "02-10-2023",
  //         "bookingId": "POBD32742",
  //         "roomDetails": [
  //                 {
  //                         "fromDate": "12-10-2023 09:00 - 13-10-2023 08:00",
  //                         "roomNo": "109",
  //                         "roomRent": "1000",
  //                         "allotId": "86549"
  //                 },
  //                 {
  //                         "fromDate": "13-10-2023 09:00 - 14-10-2023 08:00",
  //                         "roomNo": "109",
  //                         "roomRent": "1000",
  //                         "allotId": "86550"
  //                 },
  //                 {
  //                         "fromDate": "14-10-2023 09:00 - 15-10-2023 08:00",
  //                         "roomNo": "109",
  //                         "roomRent": "1000",
  //                         "allotId": "86551"
  //                 }
  //         ]
  // }

      loading.present()
      console.log(finalData)
      this.mdmService.modModifyRoomForMobile(finalData).subscribe(
        async res => {
          if (res.status === 1) {
            loading.dismiss();
            const alert = await this.alertController.create({
              message: "Rooms modified successfully",
              buttons: [
                {
                  text: 'OK',
                  cssClass: 'alertButton',
                  handler: () => {
                    this.modalController.dismiss();
                  },
                }
              ],
            });
            await alert.present();
          } else {
            loading.dismiss();
            const alert = await this.alertController.create({
              message: res.statusDesc,
              buttons: ['OK'],
            });
            await alert.present();
          }
        }, err=> {
          loading.dismiss();
          this.commonService.showErrorToast("Something Went Wrong")
        },
        () => {
          loading.dismiss()
        }
      )
    }
  }

  public async getDormitoryDetails(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService
      .getTotalBedsForMobile(this.bookingId, this.cityId, this.ghId)
      .subscribe((res) => {
        if (res.status == 1) {
          console.log(res);
          this.roomDetails = res.data;
          this.roomNos = this.roomDetails.roomNos;
          this.drmList = this.roomDetails.drmList;
          loading.dismiss()
          console.log(this.drmList);
          this.setBedDetails(this.drmList);
        } else {
          this.commonService.showErrorToast(res.statusDesc);
          loading.dismiss()
        }
      }, err => {
        loading.dismiss()
      },
      ()=> {
        loading.dismiss()
      });
  }

  public setBedDetails(roomdata) {
    // let data = []
    // this.roomNos.forEach(el => {
    //   // console.log(el)
    //   roomdata.forEach(room => {
    //     // console.log(room)
    //     data['date'] = room.dateStr;
    //     room.roomDetails.forEach(rd => {
    //       if(rd.roomNo == el) {
    //         const room = this.drmList.find(room => room.roomDetails.some(detail => detail.roomNo === rd.roomNo));
    //         console.log("Room",room)
    //         if (room) {
    //           console.log(room.roomDetails.filter(detail => detail.roomNo === rd.roomNo).map(detail => detail))
    //           return room.roomDetails.filter(detail => detail.roomNo === rd.roomNo).map(detail => detail.bedNo);
    //         }
    //         // data['beds'] = rd;
    //         // console.log(data)
    //       }
    //     });
    //   });
    // });

    for (let i = 0; i < roomdata.length; i++) {
      const element = roomdata[i];
      let room = roomdata[i].roomDetails;
      for (let j = 0; j < room.length; j++) {
        let r = roomdata[i].roomDetails[j]
        if (r.allotId != null) {
          if (r.mrrId == this.mrrId) {
            console.log("::::::::::", r)

            this.selectedRoomDetails.push({
              bedNo: r.bedNo,
              fromDate: element.dateStr,
              roomNo: r.roomNo,
              roomRent: r.roomRent,
              allotId: r.allotId
            })
            this.selectedRooms[element.dateStr] = r.roomNo + '/ ' + r.bedNo;
          }

        }
      }
    }
  }

  goBack() {
    this.modalController.dismiss();
  }

}
