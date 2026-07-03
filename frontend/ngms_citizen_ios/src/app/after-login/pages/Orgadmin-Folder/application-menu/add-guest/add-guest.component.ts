import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NavParams } from '@ionic/angular';

@Component({
  selector: 'app-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.scss'],
})
export class AddGuestComponent implements OnInit {
  addGuestDetailsForm: FormGroup;
  public segmentVal = 'room';
  bookingType = [
    {
      title: 'Room',
      value: 'room'
    },
    {
      title: 'Room with Conveyance',
      value: 'roomWithConveyance'
    },
    {
      title: 'Conveyance',
      value: 'conveyance'
    }, {
      title: 'Conference Hall/Auditorium Hall/ Open Teras',
      value: 'conferenceHall'
    }
  ]
  pathName: any;
  bhawanName: any;
  QueryData: any;
  constructor(private fb: FormBuilder,
    private route: ActivatedRoute,
    public activeRoute: ActivatedRoute,
    private router: Router,
  ) {
    this.createGuestBookingDetails()
  }

  ngOnInit() {

    this.route.queryParams.subscribe((params) => {
      this.QueryData = params['Query_Name'];
      console.log(" q data is ", this.QueryData);
    });

    this.route.queryParams.subscribe((params) => {
      this.bhawanName = params['bhawan_Name'];
    });
    this.activeRoute.url.subscribe(params => {
      this.editViewGuestBooking(params);
    });
  }
  public show() {
    this.segmentVal = this.addGuestDetailsForm.get('selectBookingType').value;
    console.log("segment", this.segmentVal);
  }
  createGuestBookingDetails() {
    this.addGuestDetailsForm = this.fb.group({
      categoryType: [],
      selectBookingType: [],
      check: [],
      noOfRoom: [],
      bookingFor: ['Others'],
      visitPurpose: [],
      designation: [],
      needConveyance: [],
      fromDate: [],
      toDate: [],
      noOfDays: [],
      adult: [],
      children: [],
      emailID: [],
      categoryDocType: [],
      categoryDocUpload: [],
      officeAddress: [],
      mobileNo: [],
      docType: [],
      docUpload: [],
      modeOfTravel: [],
      flightNo: [],
      arrivalDate: [],
      arrivalTime: [],
      arrivalPlace: [],
      modeOfTravelDept: [],
      flightNoDept: [],
      deptDate: [],
      deptTime: [],
      deptPlace: []
    });
  }
  editViewGuestBooking(param) {
    if (param.length != 0) {
      this.pathName = param[0].path;
      if (this.pathName == 'edit') {
        this.setBookingData()
      }
      if (this.pathName == 'view') {
        this.addGuestDetailsForm.disable();
        this.setBookingData();
      }
    }
  }
  setBookingData() {
    console.log("view or edit")
    // this.addGuestDetailsForm.controls.guestHouseName.setValue('Odisha ');
    this.addGuestDetailsForm.controls.categoryType.setValue('1');
    this.addGuestDetailsForm.controls.selectBookingType.setValue('room');
    this.addGuestDetailsForm.controls.check.setValue('room');
    this.addGuestDetailsForm.controls.fromDate.setValue('2023-09-05');
    this.addGuestDetailsForm.controls.toDate.setValue('2023-09-05');
    this.addGuestDetailsForm.controls.noOfRoom.setValue('2');
    this.addGuestDetailsForm.controls.noOfBeds.setValue('3');
    this.addGuestDetailsForm.controls.mobileNo.setValue('9777608680');
    this.addGuestDetailsForm.controls.emailID.setValue('pbebismita@gmail.com');
    this.addGuestDetailsForm.controls.officeAddress.setValue('Cuttack');
    this.addGuestDetailsForm.controls.category.setValue('4');
    this.addGuestDetailsForm.controls.visitPurpose.setValue('1');
    this.addGuestDetailsForm.controls.designation.setValue('2');
    this.addGuestDetailsForm.controls.needConveyance.setValue('no');
    this.addGuestDetailsForm.controls.modeOfTravel.setValue('2');
    this.addGuestDetailsForm.controls.flightNo.setValue('123');
    this.addGuestDetailsForm.controls.arrivalDate.setValue('2023-09-05');
    this.addGuestDetailsForm.controls.arrivalTime.setValue('20:14');
    this.addGuestDetailsForm.controls.arrivalPlace.setValue('1');
    this.addGuestDetailsForm.controls.modeOfTravelDept.setValue('2');
    this.addGuestDetailsForm.controls.flightNoDept.setValue('565');
    this.addGuestDetailsForm.controls.deptDate.setValue('2023-09-05');
    this.addGuestDetailsForm.controls.deptTime.setValue('20:14');
    this.addGuestDetailsForm.controls.deptPlace.setValue('1');
  }
  submitGuestDetails(formValue) {

  }
  backButton() {
    if (this.QueryData == 'current-booking') {
      this.router.navigateByUrl('after-login/pages/orgadmin/GH-menu/current-booking');
    }
    else if (this.QueryData == 'application-entry') {
      this.router.navigateByUrl('after-login/pages/orgadmin/application-entry');
    }
  }
}
