import { HttpParams } from '@angular/common/http';
import { Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { ModeratorCancelComponent } from '../moderator-cancel/moderator-cancel.component';

@Component({
  selector: 'app-moderator-save',
  templateUrl: './moderator-save.component.html',
  styleUrls: ['./moderator-save.component.scss'],
})
export class ModeratorSaveComponent implements OnInit {

  public modalData;
  public modData;
  public allBookingData;
  public arrivalDetails;
  public departDetails;
  public isConv;
  public showYes;
  public showNo = true;
  public showDuringStayYes;
  public type;
  public btnName;
  public modType;
  public headerName;
  public blockId;
  public dataComingFrom;

  public form: FormGroup;
  @ViewChildren('gh') gh: QueryList<ElementRef>;
  @ViewChildren('bed') bed: QueryList<ElementRef>;

  constructor(
    private navParams: NavParams,
    private fb: FormBuilder,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private loadingController: LoadingController,
    private alertCtrl: AlertController,
    private commonService: CommonService) {
    this.createFormInstance();
  }
  //  You have to add atleast one room or bed
  createFormInstance() {
    this.form = this.fb.group({
      type: [''],
      cityId: [''],
      blockId: [''],
      bookingId: [''],
      noOfRooms: [''],
      noOfBedsDormitory: [''],
      catg: [''],
      desg: [''],
      ext: [false],
      duringstay: [0],
      convayance: [0],
      roomType: [''],
      isArrivalConv: [],
      isDepartueConv: []
    })
  }

  ngOnInit() {

  }

  ionViewWillEnter() {
    console.log("ngOnInit")
    this.modalData = this.navParams.data;
    this.modData = this.modalData.data;
    this.btnName = this.modalData.btnName;
    console.info(this.modalData)
    this.getBookingDetailsByBookingId();
    this.type = this.modalData.type;
    this.dataComingFrom = this.modalData.dataComingFrom;
    console.log("TypeOfRoom", this.type)
    if (this.btnName == 'save') {
      this.modType = 'ADD';
      this.headerName = "Add"
    } else if (this.btnName == 'updateMod') {
      this.modType = 'UPDATE';
      this.headerName = 'Update';
      this.setRoomBedNoToInputField()
    }


  }

  public setRoomBedNoToInputField() {
    console.log("ngAfterViewInit", this.modType)

    this.gh.changes.subscribe(() => {
      this.gh.toArray().forEach(el => {
        if (this.allBookingData.cityId == '01') {
          if (this.allBookingData.blockId == '01') {
            this.gh.toArray()[0].nativeElement.value = this.allBookingData.noOfRooms;
            this.bed.changes.subscribe(() => {
              this.bed.toArray().forEach(el => {
                this.bed.toArray()[0].nativeElement.value = this.allBookingData.noOfBedsDormitory;
              })
            })
          }
          if (this.allBookingData.blockId == '02') {
            this.gh.toArray()[1].nativeElement.value = this.allBookingData.noOfRooms;
            this.bed.changes.subscribe(() => {
              this.bed.toArray().forEach(el => {
                this.bed.toArray()[1].nativeElement.value = this.allBookingData.noOfBedsDormitory;
              })
            })
          }
          if (this.allBookingData.blockId == '03') {
            this.gh.toArray()[2].nativeElement.value = this.allBookingData.noOfRooms;
            this.bed.changes.subscribe(() => {
              this.bed.toArray().forEach(el => {
                this.bed.toArray()[2].nativeElement.value = this.allBookingData.noOfBedsDormitory;
              })
            })
          }
        }
        if (this.allBookingData.cityId == '02') {
          if (this.allBookingData.blockId == '04') {
            this.gh.toArray()[0].nativeElement.value = this.allBookingData.noOfRooms;
            this.bed.changes.subscribe(() => {
              this.bed.toArray().forEach(el => {
                this.bed.toArray()[0].nativeElement.value = this.allBookingData.noOfBedsDormitory;
              })
            })
          }
        }
        if (this.allBookingData.cityId == '03') {
          if (this.allBookingData.blockId == '05') {
            this.gh.toArray()[0].nativeElement.value = this.allBookingData.noOfRooms;
            this.bed.changes.subscribe(() => {
              this.bed.toArray().forEach(el => {
                this.bed.toArray()[0].nativeElement.value = this.allBookingData.noOfBedsDormitory;
              })
            })
          }
        }
        if (this.allBookingData.cityId == '04') {
          if (this.allBookingData.blockId == '06') {
            this.gh.toArray()[0].nativeElement.value = this.allBookingData.noOfRooms;
            this.bed.changes.subscribe(() => {
              this.bed.toArray().forEach(el => {
                this.bed.toArray()[0].nativeElement.value = this.allBookingData.noOfBedsDormitory;
              })
            })
          }
        }
      });
    });
  }

  public async getBookingDetailsByBookingId() {
    console.log("getBookingDetailsByBookingId")
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.modData?.bookingId) {
      const params = new HttpParams()
        .set('bookingId', this.modData?.bookingId)
      loader.present();
      this.mdmService.getBookingDetailsByBookingId(params).subscribe(
        res => {
          if (res.status == 1) {
            this.allBookingData = res.data;
            console.log(this.allBookingData)
            this.setBookingData();
            loader.dismiss();
          } else if (res.status == 0) {
            loader.dismiss();
            this.commonService.showErrorToast(res.statusDesc);
          }
        },
        err => {
          loader.dismiss()
          this.commonService.showErrorToast('Something went wrong');
        },
        () => {
          loader.dismiss()
        });
    }
  }

  public setBookingData() {
    console.log("setBookingData")
    this.blockId = this.allBookingData.blockId;
    this.form.get('blockId').setValue(this.blockId)
    this.form.get('cityId').setValue(this.allBookingData.cityId)
    let modeOfTravelFrom = this.allBookingData.travelModeFr == 'T' ? 'Train' : this.allBookingData.travelModeFr == 'F' ? 'Flight' : '';
    let modeOfTravelTo = this.allBookingData.travelModeTo == 'T' ? 'Train' : this.allBookingData.travelModeTo == 'F' ? 'Flight' : '';

    this.isConv = (this.allBookingData.isArrivalConv || this.allBookingData.isDepartueConv) ? 1 : 0;

    if (this.isConv == 1) {
      this.showYes = true;
      if (this.type == 'Conveyance') {
        this.showNo = false;
      }
      if (this.allBookingData.isArrivalConv) {
        this.arrivalDetails = modeOfTravelFrom + " : " + this.allBookingData.flNoFr + ' at ' + this.allBookingData.arrDt + " " + this.allBookingData.arrTm + ' in ' + this.allBookingData.arrPla;
      } else {
        this.arrivalDetails = "Arrival Not Requested.";
      }

      if (this.allBookingData.isDepartueConv) {
        this.departDetails = modeOfTravelTo + " : " + this.allBookingData.flNoTo + ' at ' + this.allBookingData.depDt + " " + this.allBookingData.depTm + ' in ' + this.allBookingData.depPla;
      } else {
        this.departDetails = "Departure Not Requested."
      }
      this.form.get('convayance').setValue('1')
      console.log(this.form.get('convayance').value)

    } else {
      this.form.get('convayance').setValue('0')
      console.log(this.form.get('convayance').value)
      this.arrivalDetails = "No Coveyance Requested";
      this.departDetails = "No Coveyance Requested";
      this.showYes = false;
      if (this.type == 'Conveyance') {
        this.showNo = false;
      }
    }

    if (this.allBookingData.duringstay == 1) {
      this.form.get('duringstay').setValue('1');
      this.showDuringStayYes = true
    } else {
      this.form.get('duringstay').setValue('0');
      this.showDuringStayYes = false
    }
    console.log(this.btnName)
    // this.gh.forEach((element, index) => console.log(element));
    this.form.get('ext').setValue(this.allBookingData.ext == '2' ? true : false)

    if (this.btnName == 'updateMod') {
      console.log(this.allBookingData)
      console.log("fhf", this.gh.toArray())
      this.form.get('blockId').setValue(this.blockId)
      this.form.get('noOfRooms').setValue(this.allBookingData.noOfRooms);
      this.form.get('noOfBedsDormitory').setValue(this.allBookingData.noOfBedsDormitory);
    }
    if (this.btnName == 'save') {
      console.log(this.allBookingData)
      console.log("fhf", this.gh.toArray())
      this.form.get('blockId').setValue(this.blockId)
    }


  }

  onKeyUp(i, gh, data) {
    let element = gh;
    let elementId = element.id;
    this.blockId = data.id;
    console.log("Block ID", elementId);
    // console.log(this.gh[1].nativeElement?.id)
    console.log(this.gh.toArray())
    this.gh.forEach((input: ElementRef, i) => {
      console.log(elementId, input.nativeElement.id, this.form.get('cityId').value);
      if (elementId == input.nativeElement.id) {
        if (this.form.get('cityId').value == '01') {
          console.log("Inside cityId 01")
          if (i == 0) {
            this.gh.toArray()[1].nativeElement.value = '';
            this.gh.toArray()[2].nativeElement.value = '';
            this.bed.toArray()[1].nativeElement.value = '';
            this.bed.toArray()[2].nativeElement.value = '';
            this.form.get('noOfRooms').setValue(input.nativeElement.value ? input.nativeElement.value : "");
            console.log("i == 0", "noOfRoom", this.form.get('noOfRooms').value);

          }
          if (i == 1) {
            this.gh.toArray()[0].nativeElement.value = "";
            this.gh.toArray()[2].nativeElement.value = "";
            this.bed.toArray()[0].nativeElement.value = "";
            this.bed.toArray()[2].nativeElement.value = "";
            // console.log("noOfRoom", input.nativeElement.value);
            this.form.get('noOfRooms').setValue(input.nativeElement.value ? input.nativeElement.value : "");
            console.log("i == 1", "noOfRoom", this.form.get('noOfRooms').value);
          }
          if (i == 2) {
            this.gh.toArray()[0].nativeElement.value = "";
            this.gh.toArray()[1].nativeElement.value = "";
            this.bed.toArray()[0].nativeElement.value = "";
            this.bed.toArray()[1].nativeElement.value = "";
            console.log("noOfRoom", input.nativeElement.value);
            this.form.get('noOfRooms').setValue(input.nativeElement.value ? input.nativeElement.value : "");
            console.log("i == 2", "noOfRoom", this.form.get('noOfRooms').value);
          }
        }
        // if (this.form.get('cityId').value == '02' || this.form.get('cityId').value == '03' || this.form.get('blockId').value == '04' || this.form.get('blockId').value == '05' || this.form.get('blockId').value == '06') {
        if (this.form.get('cityId').value == '02' || this.form.get('cityId').value == '03' || this.form.get('cityId').value == '04') {
          this.form.get('noOfRooms').setValue(input.nativeElement.value ? input.nativeElement.value : "");
          console.log(this.form.get('noOfRooms').value)
        }
      }
    });
    this.bed.forEach((input: ElementRef, i) => {
      // console.log(input,i);
      if (elementId == input.nativeElement.id) {
        if (this.form.get('cityId').value == '01') {
          if (i == 0) {
            this.bed.toArray()[1].nativeElement.value = '';
            this.bed.toArray()[2].nativeElement.value = '';
            this.gh.toArray()[1].nativeElement.value = '';
            this.gh.toArray()[2].nativeElement.value = '';
            console.log("noOfBed", input.nativeElement.value);
            this.form.get('noOfBedsDormitory').setValue(input.nativeElement.value ? input.nativeElement.value : "")
            console.log("noOfBedsDormitory", this.form.get('noOfBedsDormitory').value);
          }
          if (i == 1) {
            this.gh.toArray()[0].nativeElement.value = "";
            this.gh.toArray()[2].nativeElement.value = "";
            this.bed.toArray()[0].nativeElement.value = "";
            this.bed.toArray()[2].nativeElement.value = "";
            console.log("noOfBed", input.nativeElement.value);
            this.form.get('noOfBedsDormitory').setValue(input.nativeElement.value ? input.nativeElement.value : "")
            console.log("noOfBedsDormitory", this.form.get('noOfBedsDormitory').value);

          }
          if (i == 2) {
            this.gh.toArray()[0].nativeElement.value = "";
            this.gh.toArray()[1].nativeElement.value = "";
            this.bed.toArray()[0].nativeElement.value = "";
            this.bed.toArray()[1].nativeElement.value = "";
            console.log("noOfBed", input.nativeElement.value);
            this.form.get('noOfBedsDormitory').setValue(input.nativeElement.value ? input.nativeElement.value : "")
            console.log("noOfBedsDormitory", this.form.get('noOfBedsDormitory').value);

          }
        }

        if (this.form.get('cityId').value == '02' || this.form.get('cityId').value == '03' || this.form.get('cityId').value == '04') {
          this.form.get('noOfBedsDormitory').setValue(input.nativeElement.value ? input.nativeElement.value : "");
          console.log(this.form.get('noOfBedsDormitory').value)
        }
      }
    });
  }

  public async submitModeratorDetails(type) {
    if (this.type != 'Conveyance') {
      console.log("noOfRooms", this.form.get('noOfRooms').value)
      console.log("noOfBedsDormitory", this.form.get('noOfBedsDormitory').value)
      if (this.form.get('noOfRooms').value < 1 && this.form.get('noOfBedsDormitory').value < 1) {
        this.commonService.showErrorToast("You have to add atleast one room or bed.")
        return;
      }
    } else {
      this.form.get('noOfRooms').disable();
      this.form.get('noOfBedsDormitory').disable();
    }

    this.form.get('blockId').setValue(this.blockId);
    this.form.get('type').setValue(type);
    this.form.get('cityId').setValue(this.allBookingData.cityId);
    this.form.get('bookingId').setValue(this.allBookingData.bookingId);
    this.form.get('catg').setValue(this.allBookingData.catg);
    this.form.get('desg').setValue(this.allBookingData.desg);
    this.form.get('roomType').setValue(this.allBookingData.roomType);
    this.form.get('isArrivalConv').setValue(this.allBookingData.isArrivalConv);
    this.form.get('isDepartueConv').setValue(this.allBookingData.isDepartueConv);
    let val = this.form.get('ext').value;
    console.log(val)
    // if(val == true) {
    //   console.log("ifff")
    //   this.form.get('ext').setValue('2')
    // } else {
    //   console.log("else")
    //   this.form.get('ext').setValue('0')
    // }
    let postdata = this.form.value;
    postdata.ext = (val == true ? '2' : '0')

    console.log(postdata)
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    loader.present();

    this.mdmService.addOrModifyModeratorDetailsForMobile(postdata).subscribe(
      async res => {
        if (res.status == 1) {
          loader.dismiss();
          const alert = await this.alertCtrl.create({
            message: 'Moderation data Successfully Submitted ',
            buttons: [
              {
                text: 'OK',
                cssClass: 'alertButton',
                handler: () => {
                  this.modalcontroller.dismiss();
                },
              },
            ],
          });
          await alert.present()
          // this.modalcontroller.dismiss();
        } else {
          loader.dismiss();
          const alert = await this.alertCtrl.create({
            message: res.statusDesc,
            buttons: [
              {
                text: 'OK',
                cssClass: 'alertButton',
                handler: () => {
                  this.modalcontroller.dismiss();
                },
              },
            ],
          });
          await alert.present();
        }
      },
      async err => {
        loader.dismiss();
        const alert = await this.alertCtrl.create({
          message: "Something went wrong",
          buttons: [
            {
              text: 'OK',
              cssClass: 'alertButton',
              handler: () => {
                this.modalcontroller.dismiss();
              },
            },
          ],
        });
        await alert.present();
      },
      () => {
        loader.dismiss()
      }
    )
  }

  public async cancel(modData) {
    const modal = await this.modalcontroller.create({
      component: ModeratorCancelComponent,
      componentProps: { modData }
    });
    modal.onDidDismiss().then((data) => {
      this.modalcontroller.dismiss();
    })
    return await modal.present();
  }

  public goBack() {
    this.modalcontroller.dismiss();
  }

  public viewFile(data, filename) {
    console.log("viewfile for implementaton",data,filename)
    filename = filename.split('\/')[(filename.split('\/').length) - 1];
    this.commonService.viewFile(data, filename);
  }

}