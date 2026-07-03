import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-view-details',
  templateUrl: './view-details.component.html',
  styleUrls: ['./view-details.component.scss'],
})
export class ViewDetailsComponent implements OnInit {
  public form: FormGroup;
  public bookingId;
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

  constructor(
    private fb: FormBuilder,
    private modalController: ModalController,
    private navParams: NavParams,
    private commonService: CommonService,
    private mdmService: MdmService,
    private loadingController: LoadingController
  ) {
    this.createFormInstance();
  }

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
      duringstay: [0],
      convayance: [0],
      roomType: [''],
      isArrivalConv: [],
      isDepartueConv: []
    })
  }

  ngOnInit() { }

  ionViewWillEnter() {
    this.bookingId = this.navParams.data.bookingId;
    this.type = this.navParams.data.viewType;
    console.log(this.bookingId);
    if (this.type == 'conference') {
      this.getConferenceBookingDetailsByBookingId();
    } else {
      this.getBookingDetailsByBookingId();
    }
  }

  public async getBookingDetailsByBookingId() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    const params = new HttpParams()
      .set('bookingId', this.bookingId)

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
      ()=> {
        loader.dismiss()
      });
  }
  public async getConferenceBookingDetailsByBookingId() {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    })
    const params = new HttpParams()
      .set('bookingId', this.bookingId)

    loader.present();
    this.mdmService.getConferenceBookingDetailsByBookingId(params).subscribe(
      res => {
        if (res.status == 1) {
          this.allBookingData = res.data;
          console.log(this.allBookingData)
          // this.setBookingData();
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
      ()=> {
        loader.dismiss()
      });
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
      if (this.allBookingData?.roomType == 'C') {
        this.showNo = false;
      }
      if (this.allBookingData.isArrivalConv) {
        this.arrivalDetails = modeOfTravelFrom + " : " + this.allBookingData.flNoFr + ' at ' + this.allBookingData.arrDt + " " +  this.allBookingData.arrTm +  ' in ' + this.allBookingData.arrPla;
      } else {
        this.arrivalDetails = "Arrival Not Requested.";
      }

      if (this.allBookingData.isDepartueConv) {
        this.departDetails = modeOfTravelTo + " : " + this.allBookingData.flNoTo + ' at ' + this.allBookingData.depDt +  " "+ this.allBookingData.depTm + ' in ' + this.allBookingData.depPla;
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
      if (this.allBookingData?.roomType == 'C') {
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


  }

  public viewFile(data,filename) {
    filename = filename.split('\/')[(filename.split('\/').length) - 1];
    this.commonService.viewFile(data,filename);
  }

  public goBack() {
    this.modalController.dismiss();
  }

}
