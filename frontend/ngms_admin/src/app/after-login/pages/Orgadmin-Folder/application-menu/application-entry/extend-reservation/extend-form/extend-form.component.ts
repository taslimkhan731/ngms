import { DatePipe, Location } from '@angular/common';
import { HttpParams } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import * as moment from 'moment';
import { format, addHours, parseISO } from 'date-fns';

@Component({
  selector: 'app-extend-form',
  templateUrl: './extend-form.component.html',
  styleUrls: ['./extend-form.component.scss'],
})
export class ExtendFormComponent implements OnInit {

  @ViewChild('coutDate', { static: false }) coutDate: any;
  public bookingForm: FormGroup;
  public isSubmitBtnClicked = false;
  categoryList: any;
  public reqType = [
    {
      title: 'Room',
      value: 'R',
      isAllowed: true
    },
    {
      title: 'Room with Conveyance',
      value: 'RC',
      isAllowed: true
    },
    {
      title: 'Conveyance',
      value: 'C',
      isAllowed: true
    },
    // {
    //   title: 'Conference Hall/Auditorium Hall/ Open Teras',
    //   value: 'CON',
    //   isAllowed: true
    // }
  ]
  public allBookingData;

  public requestType = 'R';
  cityId: any;
  visitPurpose: any;
  public buttonName = 'Submit'
  public isAllowArrDep = false;
  public totalCount = 0;
  public DesignationList = [];
  public enableNeedConveyance = true
  public purposeVisit = false;
  public currentDate = new Date().toISOString().slice(0, 10);
  public arrivalPlaceList;
  public pathName: any;
  public arrPla;
  public depPla;
  public depPlaceList;
  public arrTime;
  public depTime;
  public guestDetails;
  public fileName = '';
  public officialDocFileName = '';
  public gDocFileName = '';
  isDisable: any;
  public purpose;
  public designation;
  dropdown: any;
  public bookingData;
  public title = "Add";
  public showArr = false;
  public showDeparture = false;
  public splitData;
  public roomType;
  public guestHouseList = []
  public nextDay;
  public nextDayDt;

  constructor(private mdmService: MdmService,
    private fb: FormBuilder,
    private navparams: NavParams,
    private commonService: CommonService,
    private modal: ModalController,
    private datePipe: DatePipe,
    private loadingController: LoadingController) {
    this.createFormInstance();
  }

  public createFormInstance() {
    this.bookingForm = this.fb.group({
      bookingId: [''],
      catg: [null],
      bookingType: ['room'],
      noOfRooms: [1],
      noOfBedsDormitory: [1],
      bookingFor: ['others'],
      purp: [''],
      desg: [null],
      duringstay: ['0'],
      frmDt: [''],
      toDt: ['', Validators.required],
      noOfAdults: [0],
      gMobile: [null],
      gDocFile: [null],
      gDocName: [null],
      suppDocFile: [null],
      suppDocName: [null],
      catgDocFile: [null],
      catgDocName: [null],
      paramType: [''],
      gDocType: [null],
      reqType: [null],
      blockId: [null],
      noOfChildren: [0],
      mob: [''],
      emailId: [''],
      offAddr: [''],
      travelModeFr: [''],
      flNoFr: [''],
      arrDt: [''],
      arrTm: [''],
      arrPla: [''],
      travelModeTo: [''],
      flNoTo: [''],
      depDt: [''],
      depTm: [''],
      depPla: [''],
      catgType: [''],
      noOfDays: [''],
      arrTravel: [0],
      deptTravel: [0],
      splitCity: [''],
      splitGHouse: [''],
      list: this.fb.array([])
    })
  }

  ngOnInit() {

  }

  ionViewWillEnter() {
    this.bookingData = this.navparams.data.data;
    this.getCategoryList();
    this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
    this.bookingForm.disable();
    this.bookingForm.get('toDt').enable();
    this.isDisable = true;
    this.dropdown = null;
  }

  public getCityName(event) {
    this.bookingForm.get('splitCity').setValue(event)
    this.mdmService.getCityWiseGUestHouse(event).subscribe(res => {
      this.guestHouseList = res.data;
      console.log(this.guestHouseList)
    })
  }

  public getBhawanName(event) {
    this.bookingForm.get('splitGHouse').setValue(event);
  }


  public editViewBooking(param) {
    if (param.length != 0) {
      this.pathName = param[0].path;
      console.log("pathName", this.pathName);
      if (this.pathName == 'edit') {
        this.buttonName = 'Update';
        this.title = 'Update';
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
      }
      if (this.pathName == 'view') {
        this.isDisable = true;
        this.dropdown = null;
        this.title = "View"
        this.buttonName = ''
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
        this.bookingForm.disable();

      }
      if (this.pathName == 'updateSplit') {
        console.log('inside updateSplit');
        this.buttonName = 'Save';
        this.title = 'Add';
        // this.splitData = this.commonService.getStoredDetails();
        this.setBookingData(this.bookingData)
        this.bookingForm.get('frmDt').disable();
        this.bookingForm.get('toDt').disable();
        // this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
      }
    }
  }


  public async getBookingDetailsByBookingId(id) {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (id) {
      const params = new HttpParams()
        .set('bookingId', id)
      loader.present();
      this.mdmService.getBookingDetailsByBookingId(params).subscribe(
        res => {
          if (res.status == 1) {
            this.allBookingData = res.data;
            this.setBookingData(this.allBookingData);
            loader.dismiss();
          } else if (res.status == 0) {
            loader.dismiss();
            this.commonService.showErrorToast(res.statusDesc);
          }
        },
        err=> {
          loader.dismiss()
          this.commonService.showErrorToast('Something went wrong.Please try again.');
          this.modal.dismiss();
        },
        () => {
          loader.dismiss()
        });
    }
  }

  // get Category details
  getCategoryList() {
    this.mdmService.getCategoryData().subscribe(res => {
      this.categoryList = res.data;
    })
  }
  // get category wise booking Type
  changeCategory(event) {
    console.log("Inside Change Category")
    this.bookingForm.get('purp').setValue('');
    this.bookingForm.get('desg').setValue('');
    this.bookingForm.get('catg').setValue(event);
    this.bookingForm.get('reqType').disable();
    // console.log(event.target.value)
    // this.getDesignation(event.target.value)
    if (event === '') {
      this.DesignationList = [];
      this.visitPurpose = [];
      this.bookingForm.get('reqType').setValue('');
      this.bookingForm.get('reqType').disable();
    } else if (event === 'C1') {
      this.enableNeedConveyance = true;
      this.reqType[1].isAllowed = true;
      this.reqType[2].isAllowed = true;
      this.bookingForm.get('reqType').setValue('');
      this.getDesignation(this.bookingForm.get('catg').value);
      this.getPurposeOfVisit();
    } else if (event === 'C2' || event === 'C3' || event === 'C4') {
      this.bookingForm.get('duringstay').setValue('0');
      this.enableNeedConveyance = false;
      this.reqType[1].isAllowed = false;
      this.reqType[2].isAllowed = false;
      this.bookingForm.get('reqType').setValue('');
      this.getDesignation(this.bookingForm.get('catg').value);
      this.getPurposeOfVisit();
    }
  }

  public onChangePurposeVisit(event) {
    if (event.target.value === '1') {
      this.purposeVisit = true;
    } else {
      this.purposeVisit = false;
    }
  }

  // get booking type wise form
  changerequestType(event) {
    console.log("Change Request Type")
    this.requestType = event.target.value;
  }
  // get Purpose of visit
  getPurposeOfVisit() {
    const params = new HttpParams()
      .set('blockId', this.cityId)
      .set('catgId', this.bookingForm.get("catg").value);
    // const obj = {
    //   blockId: this.cityId,
    //   catgId: 'C1'
    // }

    this.mdmService.getPurposeOfVisitDetails(params).subscribe(
      res => {
        if (res.status == 1) {
          this.visitPurpose = res.data;
          if (this.purpose) {
            this.bookingForm.get('purp').setValue(this.purpose);
          }
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
        }
      },
      () => {
        // this.commonService.showErrorToast('Something went wrong');
      });
    // this.mdmService.getPurposeOfVisitDetails(obj).subscribe(res => {
    //   this.visitPurpose = res.data;
    // })
  }
  getDesignation(data) {
    this.mdmService.getDesignationDetailsList(data).subscribe(
      res => {
        if (res.status == 1) {
          this.DesignationList = res.data;
          this.bookingForm.get('desg').setValue(this.designation);
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something Went Wrong!');
        }
      },
      err=> {
        this.commonService.showErrorToast('Something Went Wrong!');
      },
      () => {
      });
  }
  goBack() {
    this.modal.dismiss(0);
  }

  get getGuestDetails() {
    return this.bookingForm.get('list') as FormArray;
  }
  public getAdultRowCount() {
    this.clearFormData();
    if (this.pathName == 'edit') {
      let prevLength = this.guestDetails?.list?.length;
      let noOfAdults = this.bookingForm.get('noOfAdults').value;
      let noOfChildren = this.bookingForm.get('noOfChildren').value;
      let currLength = noOfAdults + noOfChildren;
      this.totalCount = currLength - prevLength;
      this.totalCount = Math.abs(this.totalCount);
      this.setGuestDetailsList(this.guestDetails)
      for (let i = 0; i < this.totalCount; i++) {
        const guestArray = this.fb.group({
          guestId: [i + 1],
          guestSlNo: [i + 1],
          guestName: [''],
          guestAge: [''],
          guestGender: [''],
          guestDesignation: [''],
          guestDepartment: ['']
        })
        this.getGuestDetails.push(guestArray);
      }
    } else {
      let noOfAdults = this.bookingForm.get('noOfAdults').value;
      let noOfChildren = this.bookingForm.get('noOfChildren').value;
      this.totalCount = noOfAdults + noOfChildren;
      for (let i = 0; i < this.totalCount; i++) {
        const guestArray = this.fb.group({
          guestId: [i + 1],
          guestSlNo: [i + 1],
          guestName: [''],
          guestAge: [''],
          guestGender: [''],
          guestDesignation: [''],
          guestDepartment: ['']
        })
        this.getGuestDetails.push(guestArray);
      }
    }
    // }
  }

  public setGuestDetailsList(data) {
    data.list.forEach((ITEM) => {
      let list = this.bookingForm.get('list') as FormArray;
      const guestArray = this.fb.group({
        guestId: [ITEM !== undefined ? ITEM.guestId : ''],
        guestSlNo: [ITEM !== undefined ? ITEM.guestSlNo : ''],
        guestName: [ITEM !== undefined ? ITEM.guestName : ''],
        guestAge: [ITEM !== undefined ? ITEM.guestAge : ''],
        guestGender: [ITEM !== undefined ? ITEM.guestGender : ''],
        guestDesignation: [ITEM !== undefined ? ITEM.guestDesignation : ''],
        guestDepartment: [ITEM !== undefined ? ITEM.guestDepartment : '']
      })
      list.push(guestArray);
    });
  }


  public clearFormData() {
    let formData = this.bookingForm.get('list') as FormArray;
    while (formData.length != 0) {
      formData.removeAt(0);
    }
  }

  public calculateDateDiff() {
    this.bookingForm.get('arrDt').setValue(null);
    this.bookingForm.get('depDt').setValue(null);
    if (this.bookingForm.get('frmDt').value !== null && this.bookingForm.get('toDt').value !== null) {
      let frmDt = new Date(this.formattedDate(this.bookingForm.get('frmDt').value));
      let toDt = new Date(this.formattedDate(this.bookingForm.get('toDt').value));
      let days = Math.floor((Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
      this.bookingForm.get('noOfDays').setValue(days);
    }
  }

  public formattedDate(date) {
    const parts = date.split("-");
    const formattedDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);

    return formattedDate;
  }

  public OnselectNewFile(event, filename, file) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.onload = () => {
        this.bookingForm.get(filename).setValue(event.target.files[0].name);
        this.bookingForm.get(file).setValue(reader.result);
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  public onChangeModeOfArrTravel(event) {
    this.bookingForm.get('arrPla').setValue('');
    if (event == "T" || event == "t") {
      event = "t";
    }
    if (event == "F" || event == "f") {
      event = "f";
    }
    const params = new HttpParams()
      .set('id', this.cityId)
      .set('type', event);
    this.mdmService.getFlightTrainList(params).subscribe(
      res => {
        if (res.status == 1) {
          this.arrivalPlaceList = res.data;
          // if (this.pathName === 'view' || this.pathName === 'edit' || this.pathName === 'updateSplit') {
            if (this.bookingForm.get("travelModeFr").touched || this.bookingForm.get("travelModeFr").dirty) {
              this.bookingForm.get("arrPla").patchValue("");
              console.log("1st", this.bookingForm.get("arrPla").value, "hghh");
            } else {
              this.bookingForm.get("arrPla").setValue(this.arrPla);
            }
          // }
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
        }
      },
      err=>{
        this.commonService.showErrorToast('Something went wrong');
      },
      () => {
      });
  }

  public onChangeModeOfDepTravel(event) {
    this.bookingForm.get('depPla').setValue('');
    if (event == "T" || event == "t") {
      event = "t";
    }
    if (event == "F" || event == "f") {
      event = "f";
    }
    const params = new HttpParams()
      .set('id', this.cityId)
      .set('type', event);
    this.mdmService.getFlightTrainList(params).subscribe(
      res => {
        if (res.status == 1) {
          this.depPlaceList = res.data;
          // if (this.pathName === 'view' || this.pathName === 'edit' || this.pathName === 'updateSplit') {
            this.bookingForm.get('depPla').setValue(this.depPla);
          // }
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
        }
      },
      err=>{
        this.commonService.showErrorToast('Something went wrong');
      },
      () => {
      });
  }

  public changeDateFormat(date) {
    let objectDate = new Date(date);
    let day = String(objectDate.getDate()).padStart(2, '0');
    let month = String(objectDate.getMonth() + 1).padStart(2, '0');
    let year = objectDate.getFullYear();
    let formatDate = day + "-" + month + "-" + year;
    return formatDate;
  }

  public onTimeChange(event, time) {
    if (event) {
      var timeSplit = event.split(':'),
        hours,
        minutes,
        meridian;
      hours = timeSplit[0];
      minutes = timeSplit[1];
      if (hours > 12) {
        meridian = 'PM';
        hours -= 12;
      } else if (hours < 12) {
        meridian = 'AM';
        if (hours == 0) {
          hours = 12;
        }
      } else {
        meridian = 'PM';
      }
      this[time] = (hours + ':' + minutes + ' ' + meridian);
      return this[time];
    }

  }
  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }
  public setBookingData(data) {
    console.log('setbookingdata', data);
    if (data !== undefined) {
      this.fileName = data.catgDocName ? data.catgDocName.split('\/')[(data.catgDocName.split('\/').length) - 1] : '';
      this.officialDocFileName = data.suppDocName ? data.suppDocName.split('\/')[(data.suppDocName.split('\/').length) - 1] : '';
      this.gDocFileName = data.gDocName ? data.gDocName.split('\/')[(data.gDocName.split('\/').length) - 1] : '';

      this.requestType = data?.roomType;
      if (this.requestType == 'RC' || this.requestType == 'C') {
        this.isAllowArrDep = true;
      } else if (this.requestType == 'R') {
        this.isAllowArrDep = false;
      }

      if (this.pathName == 'updateSplit') {
        console.log("Inside updateSplit")
        this.getCityName(data?.blockId)
        this.bookingForm.get('splitCity').setValue(data?.blockId);
        this.bookingForm.get('splitGHouse').setValue(data?.cityId);
      }

      this.bookingForm.get('catg').setValue(data?.catg);

      console.log("Catg", this.bookingForm.get('catg').value);
      this.purpose = data?.purp;
      this.designation = data?.desg;
      this.changeCategory(data?.catg)
      this.showArr = data?.arrivalConv;
      console.log("showArr", this.showArr)
      this.showDeparture = data?.departueConv;

      this.arrPla = data?.arrPla;
      this.depPla = data?.depPla;
      this.cityId = data?.blockId;
      this.onTimeChange(data?.arrTm, 'arrTime');
      this.onTimeChange(data?.depTm, 'depTime');
      // console.log( this.onTimeChange(data?.arrTm, 'arrTime'))
      // this.onChangeModeOfArrTravel(data?.travelModeFr);
      // this.onChangeModeOfDepTravel(data?.travelModeTo);
      // this.bookingForm.get('purp').setValue(data?.purp);
      // this.bookingForm.get('desg').setValue(data?.desg);

      this.bookingForm.get('bookingId').setValue(data?.bookingId);
      this.bookingForm.get('reqType').setValue(data?.roomType);
      this.bookingForm.get('bookingType').setValue(data?.bookingType);
      this.bookingForm.get('noOfRooms').setValue(data?.noOfRooms);
      this.bookingForm.get('noOfBedsDormitory').setValue(data?.noOfBedsDormitory);
      this.bookingForm.get('bookingFor').setValue(data?.bookingFor);
      this.bookingForm.get('duringstay').setValue(data?.duringstay);
      this.bookingForm.get('arrDt').setValue(data?.arrDt ? data?.arrDt : '');
      this.bookingForm.get('depDt').setValue(data?.depDt ? data?.depDt : '');
      this.bookingForm.get('frmDt').setValue(data?.toDt ? data?.toDt.slice(0, 10) : "");
      // this.bookingForm.get('toDt').setValue(data?.toDt ? this.changeDateFormatyymmdd(data?.toDt.split(' ')[0]) : '');
      let day = this.bookingForm.get('frmDt').value;
      console.log(day)
      this.nextDay = new Date(this.formattedDate(day));
      console.log(this.nextDay)
      this.nextDay.setDate(this.nextDay.getDate() + 1)
      this.nextDayDt = this.nextDay.toISOString().slice(0, 10)
      console.log(this.nextDayDt)
      this.bookingForm.get('noOfAdults').setValue(data?.noOfAdults);
      this.bookingForm.get('gMobile').setValue(data?.gMobile);
      this.bookingForm.get('gDocFile').setValue(data?.gDocFile);
      this.bookingForm.get('gDocName').setValue(this.gDocFileName);
      this.bookingForm.get('suppDocFile').setValue(data?.suppDocFile);
      this.bookingForm.get('suppDocName').setValue(this.officialDocFileName);
      this.bookingForm.get('catgDocFile').setValue(data?.catgDocFile);
      this.bookingForm.get('catgDocName').setValue(this.fileName);
      this.bookingForm.get('paramType').setValue(data?.paramType);
      this.bookingForm.get('gDocType').setValue(data?.gDocType);
      this.bookingForm.get('blockId').setValue(data?.blockId);
      this.bookingForm.get('noOfChildren').setValue(data?.noOfChildren);
      this.bookingForm.get('mob').setValue(data?.mob);
      this.bookingForm.get('emailId').setValue(data?.emailId);
      this.bookingForm.get('offAddr').setValue(data?.offAddr);

      if (data?.travelModeFr == 'T' || data?.travelModeFr == 't') {
        this.bookingForm.get('travelModeFr').setValue('t');
        this.onChangeModeOfArrTravel('t');
      }
      if (data?.travelModeFr == 'F' || data?.travelModeFr == 'f') {
        this.bookingForm.get('travelModeFr').setValue('f');
        this.onChangeModeOfArrTravel('f');
      }
      this.bookingForm.get('flNoFr').setValue(data?.flNoFr);
      // let arrTm = data?.arrTm.split(' ')[0];
      this.bookingForm.get('arrTm').setValue(data?.arrTm?.split(' ')[0]);
      // console.log("arrTm",arrTm)
      // this.createBookingForm.get('arrPla').setValue(data?.arrPla);
      if (data?.travelModeTo == 'T' || data?.travelModeFr == 't') {
        this.bookingForm.get('travelModeTo').setValue('t');
        this.onChangeModeOfDepTravel('t');
      }
      if (data?.travelModeTo == 'F' || data?.travelModeFr == 'f') {
        this.bookingForm.get('travelModeTo').setValue('f');
        this.onChangeModeOfDepTravel('f');
      }
      this.bookingForm.get('flNoTo').setValue(data?.flNoTo);
      this.bookingForm.get('depTm').setValue(data?.depTm?.split(' ')[0]);

      this.bookingForm.get('catgType').setValue(data?.catgType);
      // this.bookingForm.get('noOfDays').setValue(data?.noOfDays);

      this.clearFormData();
      this.guestDetails = data;
      data.list.forEach((ITEM) => {
        let list = this.bookingForm.get('list') as FormArray;
        const guestArray = this.fb.group({
          guestId: [ITEM !== undefined ? ITEM.guestId : ''],
          guestSlNo: [ITEM !== undefined ? ITEM.guestSlNo : ''],
          guestName: [ITEM !== undefined ? ITEM.guestName : ''],
          guestAge: [ITEM !== undefined ? ITEM.guestAge : ''],
          guestGender: [ITEM !== undefined ? ITEM.guestGender : ''],
          guestDesignation: [ITEM !== undefined ? ITEM.guestDesignation : ''],
          guestDepartment: [ITEM !== undefined ? ITEM.guestDepartment : '']
        })
        list.push(guestArray);
        console.log(list)

      });
      // console.log((data.catgDocName.split('\\').length));
    }
  }

  public onChangeFromDt() {
    this.bookingForm.get('toDt').reset();
    this.bookingForm.get('noOfDays').reset();
    console.info("Inside onChangeFromDt")
  }

  public async submitBookingDetails(formValue) {
    this.isSubmitBtnClicked = true;
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.bookingForm.invalid) {
      this.commonService.showErrorToast("Please Fill up all the required details.")
      return
    }

    // let toDt = this.bookingForm.get('toDt').value
    let toDt = this.datePipe.transform(this.formattedDate(this.bookingForm.get('toDt').value), 'dd-MM-yyyy 08:00')
    // console.log(this.datePipe.transform(toDt,'dd-MM-yyyy 08:00'))
    const dateObject = moment(toDt, 'DD-MM-YYYY HH:mm').toDate();
    let data = {
      "reqId": this.allBookingData.bookingId,
      "toDate": dateObject,
      "noOfDays": this.bookingForm.get('noOfDays').value
    }
    console.log(data)
    loader.present();
    this.mdmService.updateBookingExtensionForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.commonService.showSuccessToast(res.statusDesc);
          this.modal.dismiss(1)
        } else {
          loader.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
          // this.bookingForm.get('frmDt').setValue(null);
          // this.bookingForm.get('toDt').setValue(null);
          // this.bookingForm.get('arrDt').setValue('');
          // this.bookingForm.get('depDt').setValue('');
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast('Something went wrong');
        // this.bookingForm.get('frmDt').setValue(null);
        // this.bookingForm.get('toDt').setValue(null);
        // this.bookingForm.get('arrDt').setValue('');
        // this.bookingForm.get('depDt').setValue('');
      },
      () => {
        loader.dismiss();
      });
  }

  public conveyanceCheck(event) {
    if (event.detail.checked) {
      if (event.target.value == 'arrTravel') {
        this.bookingForm.get('arrTravel').setValue(1);
        this.showArr = true;
      }
      if (event.target.value == 'deptTravel') {
        this.bookingForm.get('deptTravel').setValue(1)
        this.showDeparture = true;
      }
    } else {
      if (event.target.value == 'arrTravel') {
        this.bookingForm.get('arrTravel').setValue(0);
        this.showArr = false;
        this.bookingForm.get('travelModeFr').reset();
        this.bookingForm.get('flNoFr').reset();
        this.bookingForm.get('arrDt').reset();
        this.bookingForm.get('arrTm').reset();
        this.bookingForm.get('arrPla').reset();
      }
      if (event.target.value == 'deptTravel') {
        this.bookingForm.get('deptTravel').setValue(0)
        this.showDeparture = false;
        this.bookingForm.get('travelModeTo').reset();
        this.bookingForm.get('flNoTo').reset();
        this.bookingForm.get('depDt').reset();
        this.bookingForm.get('depTm').reset();
        this.bookingForm.get('depPla').reset();
      }
    }

  }

  public changeToDDMMYYYY(date, key, formName) {
    console.log("Inside changeToDDMMYYYY", date);
    // this[formName].get(key).setValue(moment(date).format("DD-MM-YYYY"));
    // console.log("data set in",this[formName].get(key).value);

    const parsedDate = parseISO(date);
    this[formName].get(key).setValue(format(parsedDate, 'dd-MM-yyyy'))
    console.log("final date", this[formName].get(key).value)
  }
  public openDatepicker(name) {
    this[name].nativeElement.click();
  }
}
