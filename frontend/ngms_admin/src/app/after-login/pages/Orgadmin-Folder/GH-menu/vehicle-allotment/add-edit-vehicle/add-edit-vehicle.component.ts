import { Component, OnInit } from '@angular/core';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { DriverListComponent } from '../driver-list/driver-list.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import * as moment from 'moment';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-add-edit-vehicle',
  templateUrl: './add-edit-vehicle.component.html',
  styleUrls: ['./add-edit-vehicle.component.scss'],
})
export class AddEditVehicleComponent implements OnInit {

  public vehicleData;
  public dataForEdit = [];
  public type;
  public cityAndGhId;
  public selectedDriver;
  public tripStatus;
  public myform: FormGroup;
  public isSubmitBtnClicked = false;

  constructor(
    private navparam: NavParams,
    private modalCtrl: ModalController,
    private commonService: CommonService,
    private loadingCtrl: LoadingController,
    private datePipe: DatePipe,
    private mdmService: MdmService,
    private fb: FormBuilder
  ) {
    this.createFormInstance()
  }

  private createFormInstance() {
    this.myform = this.fb.group({
      arrVehDetails: [''],
      arrVehicleNo: [''],
      arrProNo: [''],
      arrProName: [''],
      arrDriverName: [''],
      arrDriverNo: [''],
      arrStartTime: [''],
      arrEndTime: [''],

      depVehDetails: [''],
      depVehicleNo: [''],
      depProNo: [''],
      depProName: [''],
      depDriverName: [''],
      depDriverNo: [''],
      depStartTime: [''],
      depEndTime: [''],

      arrCheckBox: [1],
      depCheckBox: [1],
      arrRadio: [0],
      depRadio: [0],
    })
  }

  ngOnInit() { }

  ionViewWillEnter() {
    this.vehicleData = this.navparam.data.vehicleData;
    console.log("vehicleData", this.vehicleData)
    this.type = this.navparam.data.btn;
    this.cityAndGhId = this.navparam.data.cityAndGh;
    if (this.type == 'EDIT') {
      this.getDataForEdit()
    }
  }

  public async getDataForEdit() {
    let obj = {
      "cId": this.cityAndGhId.cityId,
      "gId": this.cityAndGhId.ghId,
      "reqId": this.vehicleData.reqId
    }
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.editVehileAllotMentViewForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.dataForEdit = res.data;
          loading.dismiss();
          this.setDataForEdit()
          console.log(this.dataForEdit)
        } else {
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
      },
      () => {
        loading.dismiss()
      }
    )

  }

  public setDataForEdit() {
    for (let i = 0; i < this.dataForEdit.length; i++) {
      if (this.dataForEdit[i].tripStatus == 'A') {
        this.myform.get('arrVehDetails').setValue(this.dataForEdit[i].vehicleDetails);
        this.myform.get('arrVehicleNo').setValue(this.dataForEdit[i].vehicleNo);
        this.myform.get('arrProName').setValue(this.dataForEdit[i].protocolName);
        this.myform.get('arrProNo').setValue(this.dataForEdit[i].protocolNo);
        this.myform.get('arrDriverName').setValue(this.dataForEdit[i].driverName);
        this.myform.get('arrDriverNo').setValue(this.dataForEdit[i].driverNo);
        this.myform.get('arrStartTime').setValue(this.convertDateFormat(this.dataForEdit[i].startTime));
        this.myform.get('arrEndTime').setValue(this.convertDateFormat(this.dataForEdit[i].endTime));
      }
      if (this.dataForEdit[i].tripStatus == 'D') {
        this.myform.get('depVehDetails').setValue(this.dataForEdit[i].vehicleDetails);
        this.myform.get('depVehicleNo').setValue(this.dataForEdit[i].vehicleNo);
        this.myform.get('depProName').setValue(this.dataForEdit[i].protocolName);
        this.myform.get('depProNo').setValue(this.dataForEdit[i].protocolNo);
        this.myform.get('depDriverName').setValue(this.dataForEdit[i].driverName);
        this.myform.get('depDriverNo').setValue(this.dataForEdit[i].driverNo);

        this.myform.get('depStartTime').setValue(this.convertDateFormat(this.dataForEdit[i].startTime));
        this.myform.get('depEndTime').setValue(this.convertDateFormat(this.dataForEdit[i].endTime));
      }
    }
  }

  public convertDateFormat(date) {
    const dateParts = date.split(' ');
    const datePart = dateParts[0].split('-');
    const timePart = dateParts[1].split(':');

    // Parse the date and time parts into a valid Date object
    const d = new Date(
      +datePart[2], // Year
      +datePart[1] - 1, // Month (months are 0-based in Date constructor)
      +datePart[0], // Day
      +timePart[0], // Hours
      +timePart[1] // Minutes
    );
    console.log(this.datePipe.transform(d, 'yyyy-MM-ddTHH:mm'))
    return this.datePipe.transform(d, 'yyyy-MM-ddTHH:mm');
  }

  public conveyanceCheck(event) {
    if (event.detail.checked) {
      if (event.target.value == 'arrCheckBox') {
        this.myform.get('arrCheckBox').setValue(1);
      }
      if (event.target.value == 'depCheckBox') {
        this.myform.get('depCheckBox').setValue(1)
      }
    } else {
      if (event.target.value == 'arrCheckBox') {
        this.myform.get('arrCheckBox').setValue(0);
      }
      if (event.target.value == 'depCheckBox') {
        this.myform.get('depCheckBox').setValue(0)
      }
    }
  }
  public conveyanceRadio(event) {
    // if (event.detail.checked) {
    console.log("Inside.....", event.target.value)
    if (event.target.value == 'arrRadio') {
      this.myform.get('arrRadio').setValue(1);
    }
    if (event.target.value == 'depRadio') {
      this.myform.get('depRadio').setValue(1)
    }
    // }
    //  else {
    //   if (event.target.value == 'arrRadio') {
    //     this.myform.get('arrRadio').setValue(0);
    //   }
    //   if (event.target.value == 'depRadio') {
    //     this.myform.get('depRadio').setValue(0)
    //   }
    // }
  }

  public async selectDriver(type) {
    const modal = await this.modalCtrl.create({
      component: DriverListComponent,
      componentProps: { cityAndGh: this.cityAndGhId }
    });
    modal.onDidDismiss().then((data) => {
      this.selectedDriver = data.data;
      this.setDriverDetails(type)
      console.log("selected Driver", this.selectedDriver)
    })
    return await modal.present();
  }

  public setDriverDetails(type) {
    if (type == 'arr') {
      this.myform.get('arrVehDetails').setValue(this.selectedDriver.vehicledesc);
      this.myform.get('arrVehicleNo').setValue(this.selectedDriver.vehicleNo);
      this.myform.get('arrProName').setValue(this.selectedDriver.protocolName);
      this.myform.get('arrProNo').setValue(this.selectedDriver.protocolMobile);
      this.myform.get('arrDriverName').setValue(this.selectedDriver.driverName);
      this.myform.get('arrDriverNo').setValue(this.selectedDriver.driverMobileNo);
    }
    if (type == 'dep') {
      this.myform.get('depVehDetails').setValue(this.selectedDriver.vehicledesc);
      this.myform.get('depVehicleNo').setValue(this.selectedDriver.vehicleNo);
      this.myform.get('depProName').setValue(this.selectedDriver.protocolName);
      this.myform.get('depProNo').setValue(this.selectedDriver.protocolMobile);
      this.myform.get('depDriverName').setValue(this.selectedDriver.driverName);
      this.myform.get('depDriverNo').setValue(this.selectedDriver.driverMobileNo);
    }
  }


  public async submitVehicleAllotment(data) {
    this.isSubmitBtnClicked = true;
    let postData = {
      cId: this.cityAndGhId.cityId,
      gId: this.cityAndGhId.ghId,
      reqId: this.vehicleData.reqId,
      vehId: this.vehicleData.vehId,
      arrival: this.vehicleData.arrival,
      departure: this.vehicleData.departure,
      // arrivalDetails:arrivalDetails,
      // departureDetails:departureDetails
    }

    let arrivalDetails;
    let departureDetails;

    if (this.vehicleData.arrival == '1' && (this.myform.get('arrCheckBox').value == 1 || this.myform.get('arrRadio').value == 1)) {
      arrivalDetails = {
        proName: this.myform.get('arrProName').value,
        proNo: this.myform.get('arrProNo').value,
        driverName: this.myform.get('arrDriverName').value,
        driverNo: this.myform.get('arrDriverNo').value,
        vehDetails: this.myform.get('arrVehDetails').value,
        vehicleNo: this.myform.get('arrVehicleNo').value,
        reqTime: this.vehicleData.arrivalDetails.stratTime,
        startTime: this.datePipe.transform(this.myform.get('arrStartTime').value, 'dd-MM-yyyy HH:mm'),
        endTime: this.datePipe.transform(this.myform.get('arrEndTime').value, 'dd-MM-yyyy HH:mm'),
        place: this.vehicleData.arrivalDetails.place
      };
      postData['arrivalDetails'] = arrivalDetails;

    } else {
      postData.arrival = '0'
    }
    if (this.vehicleData.departure == '1' && (this.myform.get('depCheckBox').value == 1 || this.myform.get('depRadio').value == 1)) {
      departureDetails = {
        proName: this.myform.get('depProName').value,
        proNo: this.myform.get('depProNo').value,
        driverName: this.myform.get('depDriverName').value,
        driverNo: this.myform.get('depDriverNo').value,
        vehDetails: this.myform.get('depVehDetails').value,
        vehicleNo: this.myform.get('depVehicleNo').value,
        reqTime: this.vehicleData.departureDetails.stratTime,
        startTime: this.datePipe.transform(this.myform.get('depStartTime').value, 'dd-MM-yyyy HH:mm'),
        endTime: this.datePipe.transform(this.myform.get('depEndTime').value, 'dd-MM-yyyy HH:mm'),
        place: this.vehicleData.departureDetails.place
      };
      postData['departureDetails'] = departureDetails
    } else {
      postData.departure = '0'
    }

    // console.log(this.datePipe.transform(this.myform.get('arrStartTime').value, 'dd-MM-yyyy HH:mm'),this.vehicleData.departureDetails.stratTime)

    let status = this.isReadyToSubmit()
    // console.log("status", status)
    if (status != false) {
      console.log("hjjjjjjjjjjjkj", postData)
      const loading = await this.loadingCtrl.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.saveVehileAllotMentForMobile(postData).subscribe(
        res => {
          if (res.status == 1) {
            loading.dismiss();
            this.commonService.showSuccessToast("Vehicle Alloted Successfully.")
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
  public async editVehicleAllotment(data) {
    this.isSubmitBtnClicked = true;
    let isArrival = "0";
    let isDeparture = "0";
    let arrivalDetails;
    let departureDetails;

    let postData = {
      cId: this.cityAndGhId.cityId,
      gId: this.cityAndGhId.ghId,
      reqId: this.dataForEdit[0].reqId,
    }

    for (let i = 0; i < this.dataForEdit.length; i++) {
      if (this.dataForEdit[i].tripStatus == 'A' && this.myform.get('arrRadio').value == 1) {
        isArrival = "1"

        arrivalDetails = {
          proName: this.myform.get('arrProName').value,
          proNo: this.myform.get('arrProNo').value,
          driverName: this.myform.get('arrDriverName').value,
          driverNo: this.myform.get('arrDriverNo').value,
          vehDetails: this.myform.get('arrVehDetails').value,
          vehicleNo: this.myform.get('arrVehicleNo').value,
          reqTime: this.dataForEdit[i].reqTime,
          startTime: this.datePipe.transform(this.myform.get('arrStartTime').value, 'dd-MM-yyyy HH:mm'),
          endTime: this.datePipe.transform(this.myform.get('arrEndTime').value, 'dd-MM-yyyy HH:mm'),
          place: this.dataForEdit[i].place,
          vehReqId: this.dataForEdit[i].vehicleRequestId
        };
        postData['arrivalDetails'] = arrivalDetails;

      } else {
        isArrival = '0';
      }
      if (this.dataForEdit[i].tripStatus == 'D' && this.myform.get('depRadio').value == 1) {
        isDeparture = "1"
        departureDetails = {
          proName: this.myform.get('depProName').value,
          proNo: this.myform.get('depProNo').value,
          driverName: this.myform.get('depDriverName').value,
          driverNo: this.myform.get('depDriverNo').value,
          vehDetails: this.myform.get('depVehDetails').value,
          vehicleNo: this.myform.get('depVehicleNo').value,
          reqTime: this.dataForEdit[i].reqTime,
          startTime: this.datePipe.transform(this.myform.get('depStartTime').value, 'dd-MM-yyyy HH:mm'),
          endTime: this.datePipe.transform(this.myform.get('depEndTime').value, 'dd-MM-yyyy HH:mm'),
          place: this.dataForEdit[i].place,
          vehReqId: this.dataForEdit[i].vehicleRequestId
        };
        postData['departureDetails'] = departureDetails
      } else {
        isDeparture = '0'
      }
    }

    postData['arrival'] = isArrival;
    postData['departure'] = isDeparture;


    let status = this.isEditFormReadyToSubmit()
    if (status != false) {
      console.log("hjjjjjjjjjjjkj", postData)
      const loading = await this.loadingCtrl.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.updateVehileAllotMentForMobile(postData).subscribe(
        res => {
          if (res.status == 1) {
            loading.dismiss();
            this.commonService.showSuccessToast("Vehicle Allotment Edited Successfully.")
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
  public checkFromDate(startTime, reportTime) {
    console.log(startTime, reportTime)
    console.log("startTime < reportTime", startTime < reportTime)
    if (startTime <= reportTime) {
      return true
    } else {
      return false;
    }
  }
  public checkToDate(endTime, reportTime) {
    console.log(endTime, reportTime)
    console.log("reportTime < endTime", reportTime < endTime)
    if (reportTime <= endTime) {
      return true
    } else {
      return false;
    }
  }

  public isReadyToSubmit() {
    if (this.vehicleData.arrival == '1' && this.myform.get('arrCheckBox').value == 1) {
      if (this.myform.get('arrVehDetails').value == '' || this.myform.get('arrVehicleNo').value == '' || this.myform.get('arrProNo').value == '' || this.myform.get('arrProName').value == '' || this.myform.get('arrDriverName').value == '' || this.myform.get('arrDriverNo').value == '' || this.myform.get('arrStartTime').value == '' || this.myform.get('arrEndTime').value == '') {
        this.commonService.showErrorToast("Please provide all arrival details")
        return false
      }
      console.log(this.myform.get('arrStartTime').value, this.vehicleData.arrivalDetails.stratTime)
      console.log(this.myform.get('arrEndTime').value, this.vehicleData.arrivalDetails.stratTime)
      const dateParts = this.vehicleData.arrivalDetails.stratTime.split(' ');
      const datePart = dateParts[0].split('-');
      const timePart = dateParts[1].split(':');

      // Parse the date and time parts into a valid Date object
      const d = new Date(
        +datePart[2], // Year
        +datePart[1] - 1, // Month (months are 0-based in Date constructor)
        +datePart[0], // Day
        +timePart[0], // Hours
        +timePart[1] // Minutes
      );
      console.log(this.datePipe.transform(d, 'yyyy-MM-ddTHH:mm'))
      let status = this.checkFromDate(this.datePipe.transform(this.myform.get('arrStartTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
      console.log(status)
      if (!status) {
        this.commonService.showErrorToast("Vehicle start time must be less from report time In Arrival.")
        return false;
      }
      let status1 = this.checkToDate(this.datePipe.transform(this.myform.get('arrEndTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
      console.log(status1)
      if (!status1) {
        this.commonService.showErrorToast("Vehicle end time must be greater from report time In Arrival !")
        return false;
      }
    }
    if (this.vehicleData.departure == '1' && this.myform.get('depCheckBox').value == 1) {
      if (this.myform.get('depVehDetails').value == '' || this.myform.get('depVehicleNo').value == '' || this.myform.get('depProNo').value == '' || this.myform.get('depProName').value == '' || this.myform.get('depDriverName').value == '' || this.myform.get('depDriverNo').value == '' || this.myform.get('depStartTime').value == '' || this.myform.get('depEndTime').value == '') {
        this.commonService.showErrorToast("Please provide all departure details")
        return false
      }
      const dateParts = this.vehicleData.departureDetails.stratTime.split(' ');
      const datePart = dateParts[0].split('-');
      const timePart = dateParts[1].split(':');

      // Parse the date and time parts into a valid Date object
      const d = new Date(
        +datePart[2], // Year
        +datePart[1] - 1, // Month (months are 0-based in Date constructor)
        +datePart[0], // Day
        +timePart[0], // Hours
        +timePart[1] // Minutes
      );
      console.log(this.datePipe.transform(d, 'yyyy-MM-ddTHH:mm'))
      let status = this.checkFromDate(this.datePipe.transform(this.myform.get('depStartTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
      console.log(status)
      if (!status) {
        this.commonService.showErrorToast("Vehicle start time must be less from report time In Departure.")
        return false;
      }
      let status1 = this.checkToDate(this.datePipe.transform(this.myform.get('depEndTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
      console.log(status1)
      if (!status1) {
        this.commonService.showErrorToast("Vehicle end time must be greater from report time In Departure !")
        return false;
      }
    }
  }

  public isEditFormReadyToSubmit() {
    for (let i = 0; i < this.dataForEdit.length; i++) {
      if (this.dataForEdit[i].tripStatus == 'A') {
        if (this.myform.get('arrVehDetails').value == '' || this.myform.get('arrVehicleNo').value == '' || this.myform.get('arrProNo').value == '' || this.myform.get('arrProName').value == '' || this.myform.get('arrDriverName').value == '' || this.myform.get('arrDriverNo').value == '' || this.myform.get('arrStartTime').value == '' || this.myform.get('arrEndTime').value == '') {
          this.commonService.showErrorToast("Please provide all arrival details")
          return false
        }
        console.log(this.myform.get('arrStartTime').value, this.dataForEdit[i].startTime)
        console.log(this.myform.get('arrEndTime').value, this.dataForEdit[i].endTime)
        const dateParts = this.dataForEdit[i].reqTime.split(' ');
        const datePart = dateParts[0].split('-');
        const timePart = dateParts[1].split(':');

        // Parse the date and time parts into a valid Date object
        const d = new Date(
          +datePart[2], // Year
          +datePart[1] - 1, // Month (months are 0-based in Date constructor)
          +datePart[0], // Day
          +timePart[0], // Hours
          +timePart[1] // Minutes
        );
        console.log(this.datePipe.transform(d, 'yyyy-MM-ddTHH:mm'))
        let status = this.checkFromDate(this.datePipe.transform(this.myform.get('arrStartTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
        console.log(status)
        if (!status) {
          this.commonService.showErrorToast("Vehicle start time must be less from report time In Arrival.")
          return false;
        }
        let status1 = this.checkToDate(this.datePipe.transform(this.myform.get('arrEndTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
        console.log(status1)
        if (!status1) {
          this.commonService.showErrorToast("Vehicle end time must be greater from report time In Arrival.")
          return false;
        }

      }
      if (this.dataForEdit[i].tripStatus == 'D') {
        if (this.myform.get('depVehDetails').value == '' || this.myform.get('depVehicleNo').value == '' || this.myform.get('depProNo').value == '' || this.myform.get('depProName').value == '' || this.myform.get('depDriverName').value == '' || this.myform.get('depDriverNo').value == '' || this.myform.get('arrStartTime').value == '' || this.myform.get('arrEndTime').value == '') {
          this.commonService.showErrorToast("Please provide all departure details")
          return false
        }
        const dateParts = this.dataForEdit[i].reqTime.split(' ');
        const datePart = dateParts[0].split('-');
        const timePart = dateParts[1].split(':');

        // Parse the date and time parts into a valid Date object
        const d = new Date(
          +datePart[2], // Year
          +datePart[1] - 1, // Month (months are 0-based in Date constructor)
          +datePart[0], // Day
          +timePart[0], // Hours
          +timePart[1] // Minutes
        );
        console.log(this.datePipe.transform(d, 'yyyy-MM-ddTHH:mm'))
        let status = this.checkFromDate(this.datePipe.transform(this.myform.get('depStartTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
        console.log(status)
        if (!status) {
          this.commonService.showErrorToast("Vehicle start time must be less from report time In Departure.")
          return false;
        }
        let status1 = this.checkToDate(this.datePipe.transform(this.myform.get('depEndTime').value, 'dd-MM-yyyy HH:mm'), this.datePipe.transform(d, 'dd-MM-yyyy HH:mm'));
        console.log(status1)
        if (!status1) {
          this.commonService.showErrorToast("Vehicle end time must be greater from report time In Departure !")
          return false;
        }
      }
    }
  }


  goBack() {
    this.modalCtrl.dismiss();
  }

}
