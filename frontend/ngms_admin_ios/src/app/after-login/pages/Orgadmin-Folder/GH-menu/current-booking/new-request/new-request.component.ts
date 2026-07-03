import { DatePipe } from '@angular/common';
import { HttpParams } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IonContent, LoadingController, ModalController, NavParams, PopoverController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { mobileNumberValidator, emailValidator, numberValidator } from 'src/app/validators/custom-validators';
import { TermsandconditionComponent } from '../termsandcondition/termsandcondition.component';
import { format, addHours, parseISO } from 'date-fns';

@Component({
  selector: 'app-new-request',
  templateUrl: './new-request.component.html',
  styleUrls: ['./new-request.component.scss'],
})
export class NewRequestComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('arrDt', { static: false }) arrDt: any;
  @ViewChild('arrTm', { static: false }) arrTm: any;
  @ViewChild('depDt', { static: false }) depDt: any;
  @ViewChild('depTm', { static: false }) depTm: any;
  @ViewChild('dateInput', { static: false }) dateInput: any;
  @ViewChild('startTime', { static: false }) startTime: any;
  public bookingForm: FormGroup;
  public createConferenceForm: FormGroup;
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
    {
      title: 'Conference Hall/Auditorium Hall/ Open Terrace',
      value: 'CON',
      isAllowed: true
    }
  ]
  public allBookingData;
  public fileTypeFlag = false;
  public fileSizeflag = false;
  public requestType = 'R';
  cityId: any;
  ghId: any;
  public purpType;
  visitPurpose: any;
  public buttonName = 'Submit'
  public isAllowArrDep = false;
  public totalCount = 0;
  public DesignationList = [];
  public enableNeedConveyance = true
  public purposeVisit = false;
  public currentDate = new Date().toISOString().slice(0, 10);
  public nextDayDt
  public nextDay = new Date();
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
  isDisable = true;
  public isDisable1 = true;
  public purpose;
  public designation;
  dropdown: any;
  public bookingData;
  public title = "Add";
  public showArr = false;
  public showDeparture = false;
  public splitData;
  public roomType;
  public guestHouseList = [];
  public timePeriodData = []
  public confType;
  public isAvail = -1;
  public isAllowConference = false;
  public agreeToTnc = false;
  public conferenceAmount;
  public showStartEndTime = false;
  public isSubmitBtnClicked = false;

  constructor(private mdmService: MdmService,
    private fb: FormBuilder,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    public commonService: CommonService,
    private modalController: ModalController,
    private navparams: NavParams,
    private popoverController: PopoverController,
    private loadingController: LoadingController) {
    this.createFormInstance();
    this.createConferenceInstance();
  }

  public createFormInstance() {
    this.bookingForm = this.fb.group({
      bookingId: [''],
      catg: [null],
      bookingType: ['room'],
      noOfRooms: ['1', Validators.compose([numberValidator])],
      noOfBedsDormitory: ['1', Validators.compose([numberValidator])],
      bookingFor: ['others'],
      purp: ['', Validators.required],
      desg: ['', Validators.required],
      duringstay: ['0'],
      frmDt: [''],
      toDt: [''],
      noOfAdults: ['', Validators.required],
      gMobile: ['', Validators.compose([Validators.required, mobileNumberValidator])],
      gDocFile: [null],
      gDocName: [null, Validators.required],
      suppDocFile: [null],
      suppDocName: [null],
      catgDocFile: [null],
      catgDocName: [null],
      paramType: [''],
      gDocType: [null, Validators.required],
      reqType: [null],
      blockId: [null],
      noOfChildren: [0],
      mob: ['', Validators.compose([mobileNumberValidator])],
      emailId: ['', Validators.compose([emailValidator])],
      // emailId: [''],
      offAddr: ['', Validators.required],
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
      list: this.fb.array([])
    })
  }

  public createConferenceInstance() {
    this.createConferenceForm = this.fb.group({
      blockId: [''],
      cityId: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      mobileNo: ['', Validators.compose([Validators.required, mobileNumberValidator])],
      confCatId: ['', Validators.required],
      confUseId: ['', Validators.required],
      fromDate: ['', Validators.required],
      strtTime: [''],
      endTime: [''],
      amount: ['', Validators.required],
      capacity: ['', Validators.required],
      // advanceAmount: [''],
      categoryType: [''],
      chkAvail: []
    })
  }

  ngOnInit() {

  }

  ionViewWillEnter() {
    this.getCategoryList();

    this.bookingForm.get('duringstay').setValue('0');
    if (this.bookingForm.get('catg').value === null) {
      this.bookingForm.get('reqType').disable();
    }
    this.cityId = this.navparams.data.cityAndGhIds.cityId;
    this.ghId = this.navparams.data.cityAndGhIds.ghId;
    console.log(this.cityId,this.ghId)
    this.bookingForm.get('blockId').setValue(this.ghId);
    // this.bookingForm.get('cityId').setValue(this.cityId);
    this.title = this.navparams.data.type;
    this.bookingData = this.navparams.data?.bookingData
    this.editViewBooking(this.title);

    this.nextDay.setDate(this.nextDay.getDate() + 1)
    this.nextDayDt = this.nextDay.toISOString().slice(0, 10)
    console.log(this.nextDayDt)
    this.bookingForm.get('toDt').setValue(this.datePipe.transform(this.nextDayDt+ " " + "08:00",'dd-MM-yyyy 08:00'));
    this.bookingForm.get('frmDt').setValue(this.datePipe.transform(this.currentDate+ " " + "09:00",'dd-MM-yyyy 09:00'));
    console.log(this.datePipe.transform(this.currentDate+ " " + "09:00",'dd-MM-yyyy 09:00'))
    console.log(this.bookingForm.get('toDt').value)
    this.bookingForm.get('noOfDays').setValue(1);
  }

  public editViewBooking(param) {
    console.log("Inside EditViewBooking")
    this.pathName = param;
    if (this.pathName == 'Edit') {
      this.buttonName = 'Update';
      this.title = 'Update';
      this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
    }
    if (this.pathName == 'View') {
      this.isDisable = false;
      this.dropdown = null;
      this.title = "View"
      this.buttonName = ''
      this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
      this.bookingForm.disable();

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
        err => {
          loader.dismiss()
          this.commonService.showErrorToast('Something went wrong');
        },
        ()=> {
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
    this.requestType = 'R'
    this.bookingForm.get("reqType").setValue(this.requestType)
    console.log("Inside Change Category",this.requestType)
    // this.bookingForm.get('purp').reset();
    this.bookingForm.get('desg').reset();
    this.bookingForm.get('catg').setValue(event);

    console.log()
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
      this.bookingForm.get('reqType').enable();
      if (this.pathName == 'View') {
        this.bookingForm.get('reqType').disable();
      }
      this.getDesignation(this.bookingForm.get('catg').value);
      this.getPurposeOfVisit();
    } else if (event === 'C2' || event === 'C3' || event === 'C4') {
      this.bookingForm.get('duringstay').setValue('0');
      this.enableNeedConveyance = false;
      this.reqType[1].isAllowed = false;
      this.reqType[2].isAllowed = false;
      this.bookingForm.get('reqType').setValue('');
      this.bookingForm.get('reqType').enable();
      if (this.pathName == 'View') {
        this.bookingForm.get('reqType').disable();
      }
      this.getDesignation(this.bookingForm.get('catg').value);
      this.getPurposeOfVisit();
    }
  }

  public onChangePurposeVisit(event) {
    console.log("&&&&&&&&&&&&&&&&&&&onChangePurposeVisit");
    if (event.target.value === "2") {
      this.purposeVisit = false;
      this.bookingForm.get("suppDocName").reset();
      this.bookingForm.get("suppDocFile").reset();
    } else {
      this.purposeVisit = true;
      // this.purpType = event.target.value;
      if (event.target.value == '1') {
        this.purpType = "Official"
      }
      if (event.target.value == '3') {
        this.purpType = "Patient"
      }
      if (event.target.value == '4') {
        this.purpType = "Student"
      }
    }
  }

  // get booking type wise form
  changerequestType(event) {
    this.requestType = event.target.value ? event.target.value : this.requestType;
    this.bookingForm.get("reqType").setValue(event.target.value ? event.target.value : this.requestType)

    console.log("Change Request Type",this.requestType)
    this.isSubmitBtnClicked = false
    if (event.target.value == 'C') {
      this.bookingForm.get("frmDt").reset()
      this.bookingForm.get("toDt").reset()
    }

    if (event.target.value == 'CON') {
      this.isAllowConference = true;
      this.createConferenceForm.get('blockId').setValue(this.ghId)
      this.createConferenceForm.get('cityId').setValue(this.cityId)
      let catg = this.bookingForm.get("catg").value;
      this.createConferenceForm.get('categoryType').setValue(catg)
      this.getConfrenceTypeDetails();
      console.log(this.isAllowConference)
    } else {
      this.isAllowConference = false;
      console.log(this.isAllowConference)
    }
  }

  public async getConfrenceTypeDetails() {
    let obj = {
      "gId": this.ghId
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getConfrenceTypeDetails(obj).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          this.confType = res.data.data;
          console.log(this.confType)
        }
        else {
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast(err.ststusDesc)
      },
      ()=> {
        loading.dismiss()
      })
  }

  // get Purpose of visit
  getPurposeOfVisit() {
    const params = new HttpParams()
      .set('blockId', this.ghId)
      .set('catgId', this.bookingForm.get("catg").value);
    // const obj = {
    //   blockId: this.cityId,
    //   catgId: 'C1'
    // }

    this.mdmService.getPurposeOfVisitDetails(params).subscribe(
      res => {
        if (res.status == 1) {
          this.visitPurpose = res.data;
          this.bookingForm.get("purp").setValue(this.purpose);

        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
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
      err => {
        this.commonService.showErrorToast('Something went wrong');
      });
  }
  goBack() {
    this.modalController.dismiss();
  }

  get getGuestDetails() {
    return this.bookingForm.get('list') as FormArray;
  }
  public getAdultRowCount() {
    this.clearFormData();
    const noOfAdults = this.bookingForm.get('noOfAdults').value || 0;
    const noOfChildren = this.bookingForm.get('noOfChildren').value || 0;
    console.log(this.guestDetails)
    console.log(this.pathName === "edit" && this.guestDetails?.list?.length > 0);
    if ((this.pathName === 'edit' || this.pathName == 'modEdit') && this.guestDetails?.list?.length > 0) {
      let totalCount = (noOfAdults + noOfChildren);
      for (let i = 0; i < totalCount; i++) {
        const guestArray = this.fb.group({
          guestId: [this.guestDetails.list[i]?.guestId ? this.guestDetails.list[i]?.guestId : "",],
          guestSlNo: [i + 1],
          guestName: [this.guestDetails.list[i]?.guestName ? this.guestDetails.list[i].guestName : '', Validators.required],
          guestAge: [this.guestDetails.list[i]?.guestAge ? this.guestDetails.list[i].guestAge : '', Validators.required],
          guestGender: [this.guestDetails.list[i]?.guestGender ? this.guestDetails.list[i].guestGender : '', Validators.required],
          guestDesignation: [this.guestDetails.list[i]?.guestDesignation ? this.guestDetails.list[i].guestDesignation : ''],
          guestDepartment: [this.guestDetails.list[i]?.guestDepartment ? this.guestDetails.list[i].guestDepartment : '']
        });
        this.getGuestDetails.push(guestArray);
      }
    } else {
      const totalCount = noOfAdults + noOfChildren;

      for (let i = 0; i < totalCount; i++) {
        const guestArray = this.fb.group({
          guestId: [i + 1],
          guestSlNo: [i + 1],
          guestName: ['', Validators.required],
          guestAge: ['', Validators.required],
          guestGender: ['', Validators.required],
          guestDesignation: [''],
          guestDepartment: ['']
        });
        this.getGuestDetails.push(guestArray);
      }
    }
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
      let frmDt = new Date(this.bookingForm.get('frmDt').value);
      let toDt = new Date(this.bookingForm.get('toDt').value);
      let days = Math.floor((Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) - Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) / (1000 * 60 * 60 * 24));
      this.bookingForm.get('noOfDays').setValue(days);
    }
  }

  public OnselectNewFile(event, filename, file) {
    this.bookingForm.get(filename).reset();
    this.bookingForm.get(file).reset();
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'pdf'];

    const files = event.target.files[0];
    const fileName = files.name;
    const fileExtension = fileName.split('.').pop().toLowerCase();
    const fileSizeMB = files.size / (1024 * 1024);

    if (!allowedExtensions.includes(fileExtension)) {
      this.fileTypeFlag = true;
      this.commonService.showErrorToast('Only jpg, jpeg, png, pdf file types are allowed.');
      return;
    } else {
      this.fileTypeFlag = false;
    }

    if (fileSizeMB > 2) {
      this.commonService.showErrorToast('File size exceeds the maximum limit of 2MB');
      this.fileSizeflag = true;
      return;
    } else {
      this.fileSizeflag = false;
    }

    const reader = new FileReader();
    reader.readAsDataURL(files);

    reader.onload = () => {
      this.bookingForm.get(filename).setValue(event.target.files[0].name);
      this.bookingForm.get(file).setValue(reader.result);
    };
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
          if (this.pathName === 'View' || this.pathName === 'Edit') {
            if (this.bookingForm.get("travelModeFr").touched || this.bookingForm.get("travelModeFr").dirty) {
              this.bookingForm.get("arrPla").patchValue("");
              console.log("1st", this.bookingForm.get("arrPla").value, "hghh");
            } else {
              this.bookingForm.get("arrPla").setValue(this.arrPla);
            }
          }
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
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
          if (this.pathName === 'View' || this.pathName === 'Edit') {
            this.bookingForm.get('depPla').setValue(this.depPla);
          }
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
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

  public changeDateFormatForConference(date) {
    this.createConferenceForm.get('chkAvail').reset();
    this.isAvail = -1
    this.showAvailCheckbox();
    // console.log(date)
    // let objectDate = new Date(date);
    // let day = String(objectDate.getDate()).padStart(2, '0');
    // let month = String(objectDate.getMonth() + 1).padStart(2, '0');
    // let year = objectDate.getFullYear();
    // let formatDate = day + "-" + month + "-" + year;
    // return formatDate;
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

      let catg = data?.catg;
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
      let frmDt = data?.frmDt;
      let toDt = data?.toDt;

      console.log(data?.frmDt.slice(0, 10), data?.frmDt.slice(11, 16))

      this.bookingForm.get('bookingId').setValue(data?.bookingId);
      this.bookingForm.get('reqType').setValue(data?.roomType);
      this.bookingForm.get('bookingType').setValue(data?.bookingType);
      this.bookingForm.get('noOfRooms').setValue(data?.noOfRooms);
      this.bookingForm.get('noOfBedsDormitory').setValue(data?.noOfBedsDormitory);
      this.bookingForm.get('bookingFor').setValue(data?.bookingFor);
      this.bookingForm.get('duringstay').setValue(data?.duringstay);
      this.bookingForm.get('arrDt').setValue(data?.arrDt ? data?.arrDt : '');
      this.bookingForm.get('depDt').setValue(data?.depDt ? data?.depDt : '');
      this.bookingForm.get('frmDt').setValue(data?.frmDt ? data?.frmDt: '');
      this.bookingForm.get('toDt').setValue(data?.toDt ? data?.toDt: '');
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
      // this.bookingForm.get('mob').setValue(data?.mob);
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
      // this.bookingForm.get('arrPla').setValue(data?.arrPla);
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
      this.bookingForm.get('noOfDays').setValue(data?.noOfDays);

      this.clearFormData();
      this.guestDetails = data;
      data.list.forEach((ITEM, i) => {
        let list = this.bookingForm.get('list') as FormArray;
        const guestArray = this.fb.group({
          guestId: [ITEM !== undefined ? ITEM.guestId : ''],
          guestSlNo: [i + 1],
          guestName: [ITEM !== undefined ? ITEM.guestName : ''],
          guestAge: [ITEM !== undefined ? ITEM.guestAge : ''],
          guestGender: [ITEM !== undefined ? ITEM.guestGender : ''],
          guestDesignation: [ITEM !== undefined ? ITEM.guestDesignation : ''],
          guestDepartment: [ITEM !== undefined ? ITEM.guestDepartment : '']
        })
        list.push(guestArray);

      });
      // console.log((data.catgDocName.split('\\').length));
    }
  }

  public onChangeFromDt() {
    this.bookingForm.get('toDt').reset();
    this.bookingForm.get('noOfDays').reset();
    console.info("Inside onChangeFromDt")
  }

  public onChangeBookingType(event) {
    if (event == "room") {
      this.bookingForm.get("noOfBedsDormitory").setValue("");
      this.bookingForm.get("noOfRooms").setValue("1");
    } else if (event == "dormitory") {
      this.bookingForm.get("noOfRooms").setValue("");
      this.bookingForm.get("noOfBedsDormitory").setValue("1");
    }
  }


  public validateFormDate() {
    if (this.bookingForm.get('reqType').value == 'R' || this.bookingForm.get('reqType').value == 'RC') {
      if (this.bookingForm.get('bookingType').value == 'room') {
        this.bookingForm.get('noOfBedsDormitory').reset()
        if (this.bookingForm.get('noOfRooms').value == '0' || this.bookingForm.get('noOfRooms').value == '' || this.bookingForm.get('noOfRooms').value == null) {
          this.commonService.showErrorToast("Atleast 1 room is required.")
          return false;
        }
      }

      if (this.bookingForm.get('bookingType').value == 'dormitory') {
        this.bookingForm.get('noOfRooms').reset()
        if (this.bookingForm.get('noOfBedsDormitory').value == '0' || this.bookingForm.get('noOfBedsDormitory').value == '' || this.bookingForm.get('noOfBedsDormitory').value == null) {
          this.commonService.showErrorToast("Atleast 1 bed is required.")
          return;
        }
      }
    }

    if (this.bookingForm.get("purp").value == "" || this.bookingForm.get('purp').value == null) {
      this.commonService.showErrorToast("Please Select Purpose.");
      return false;
    }

    if (this.bookingForm.get('noOfAdults').value == '' || this.bookingForm.get('noOfAdults').value == null || this.bookingForm.get('noOfAdults').value == '0') {
      this.commonService.showErrorToast("Atleast 1 adult is required.")
      return false;
    }

    if (this.bookingForm.get('catg').value != 'C4') {
      if (this.bookingForm.get("catgType").value == "" || this.bookingForm.get('catgType').value == null) {
        this.commonService.showErrorToast("Please Select Category Document Type.");
        return false;
      }
      if (this.bookingForm.get('catgDocName').value == '' || this.bookingForm.get('catgDocName').value == null) {
        this.commonService.showErrorToast("Please Upload Category Document.")
        return false;
      }
    }

    console.log("=========", this.bookingForm.get('purp').value, "====", this.bookingForm.get('suppDocName').value)
    if (this.bookingForm.get('purp').value != '2') {
      if (this.bookingForm.get('suppDocName').value == '' || this.bookingForm.get('suppDocName').value == null) {
        this.commonService.showErrorToast("Please Upload " + this.purpType + " Document.")
        return false;
      }
    }

    if (this.bookingForm.get('reqType').value == 'RC' || this.bookingForm.get('reqType').value == 'C') {
      if (this.showArr == false && this.showDeparture == false) {
        this.commonService.showErrorToast("Please Select Conveyance Details.")
        return false;
      }
    }

    if (this.showArr == true) {
      if (this.bookingForm.get('travelModeFr').value == '' || this.bookingForm.get("travelModeFr").value == null || this.bookingForm.get('flNoFr').value == '' || this.bookingForm.get('flNoFr').value == null ||
        this.bookingForm.get('arrDt').value == '' || this.bookingForm.get('arrDt').value == null || this.bookingForm.get('arrTm').value == '' || this.bookingForm.get('arrTm').value == null || this.bookingForm.get('arrPla').value == '' || this.bookingForm.get('arrPla').value == null) {
        this.commonService.showErrorToast("Please fill up arrival details.");
        return false;
      }
    }
    if (this.showDeparture == true) {
      if (this.bookingForm.get('travelModeTo').value == '' || this.bookingForm.get('travelModeTo').value == null || this.bookingForm.get('flNoTo').value == '' || this.bookingForm.get('flNoTo').value == null || this.bookingForm.get('depDt').value == '' || this.bookingForm.get('depDt').value == null || this.bookingForm.get('depTm').value == '' || this.bookingForm.get('depTm').value == null || this.bookingForm.get('depPla').value == '' || this.bookingForm.get('depPla').value == null) {
        this.commonService.showErrorToast("Please fill up Departure details.");
        return false;
      }
    }

    if (this.bookingForm.get("bookingFor").value == 'self') {
      if (this.bookingForm.get('mob').value == '' || this.bookingForm.get('mob').value == null) {
        this.commonService.showErrorToast("Please Enter Mobile Number.");
        return false;
      }
    }

    if (this.bookingForm.get('reqType').value == 'R' || this.bookingForm.get('reqType').value == 'RC') {
      if (this.bookingForm.get('frmDt').value == '') {
        this.commonService.showErrorToast("Please provide Check-in Date.")
        return false;
      }
      if (this.bookingForm.get('toDt').value == '') {
        this.commonService.showErrorToast("Please provide Check-out Date.")
        return false;
      }
    }

    if (this.bookingForm.get('bookingFor').value == 'others') {
      if (this.bookingForm.get('gMobile').value == null || this.bookingForm.get('gMobile').value == '') {
        this.commonService.showErrorToast("Please Enter guest mobile number")
        return false;
      } else if (this.bookingForm.get('gDocType').value == null) {
        this.commonService.showErrorToast("Please Enter guest document type.")
        return false;
      } else if (this.bookingForm.get('gDocName').value == '' || this.bookingForm.get('gDocName').value == null) {
        this.commonService.showErrorToast("Please Upload Guest Document.")
        return false;
      }
    }
    // if (this.bookingForm.get('bookingFor').value == 'self') {
    //   this.bookingForm.get('gMobile').reset()
    //   this.bookingForm.get('gDocType').reset()
    //   this.bookingForm.get('gDocFile').reset()
    //   this.bookingForm.get('gDocName').reset()
    // }
  }


  gotToTop() {
    this.content.scrollToTop(1000);
  }
  public async submitBookingDetails(formValue) {
    this.isSubmitBtnClicked = true;
    console.log('test', formValue.frmDt,formValue.toDt)
    if (this.bookingForm.get('catg').value === null) {
      this.commonService.showErrorToast("Select Category");
      this.gotToTop();
      return
    }
    else if (this.bookingForm.get('reqType').value == '') {
      this.commonService.showErrorToast("Select Booking Type")
      this.gotToTop();
      return
    }
    else {
      const loader = await this.loadingController.create({
        message: 'Please wait...'
      });
      if (this.bookingForm.invalid) {
        this.commonService.showErrorToast("Please Fill up all the required details.")
        return
      }
      formValue.frmDt = formValue.frmDt ? formValue.frmDt : '';
      formValue.toDt = formValue.toDt ? formValue.toDt : '';
      formValue.arrDt = formValue.arrDt ? formValue.arrDt : '';
      formValue.depDt = formValue.depDt ? formValue.depDt : '';
      // formValue.frmDt = formValue.frmDt ? formValue.frmDt + ' 09:00' : '';
      // formValue.toDt = formValue.toDt ? formValue.toDt + ' 08:00' : '';

      formValue.arrTm = formValue.arrTm ? this.onTimeChange(formValue.arrTm, 'arrTime') : '';
      formValue.depTm = formValue.depTm ? this.onTimeChange(formValue.depTm, 'depTime') : '';
      if (this.bookingForm.get('bookingType').value == 'room') {
        this.bookingForm.get('noOfBedsDormitory').setValue('')
      }
      if (this.bookingForm.get('bookingType').value == 'dormitory') {
        this.bookingForm.get('noOfRooms').setValue('')
      }
      let i, child = 0, adult = 0;
      for (i = 0; i < formValue.list.length; i++) {
        if (formValue.list[i].guestAge <= 12)
          child++;
        else {
          adult++;
        }
      }
      if (this.bookingForm.get('noOfChildren').value < child || this.bookingForm.get('noOfAdults').value < adult) {
        this.commonService.showErrorToast('Please check the number of children and adults');
        return;
      }
      if (child < this.bookingForm.get('noOfChildren').value) {
        this.commonService.showErrorToast("Age of Childrens must be less than 12 years.")
        return;
      }
      let isReadyToSend = this.validateFormDate();
      console.log("isReadyToSend", isReadyToSend)

      if (isReadyToSend !== false) {
        if (this.bookingForm.valid) {
          if (this.bookingForm.get('bookingFor').value == 'others') {
            if (this.bookingForm.get('mob').value == '' || this.bookingForm.get('mob').value == null) {
              formValue.mob = formValue.gMobile;
            }
          }
          console.log("formvalue", formValue)
          formValue.mob = formValue.gMobile;
          if (this.pathName == 'Edit') {
            formValue.paramType = 'update';
            console.log(formValue)
            loader.present();
            this.mdmService.receptionistaddBookingDetailsForMobile(formValue).subscribe(
              res => {
                if (res.status == 1) {
                  loader.dismiss();
                  this.commonService.showSuccessToast(res.msg);
                  this.goBack();
                  // this.router.navigateByUrl('after-login/pages/orgadmin/application-menu/application-entry');
                } else if (res.status == 0) {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                  this.bookingForm.get('frmDt').setValue(null);
                  this.bookingForm.get('toDt').setValue(null);
                  this.bookingForm.get('arrDt').setValue('');
                  this.bookingForm.get('depDt').setValue('');
                } else {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                }
              },
              err => {
                loader.dismiss();
                this.commonService.showErrorToast('Something went wrong');
                this.bookingForm.get('frmDt').setValue(null);
                this.bookingForm.get('toDt').setValue(null);
                this.bookingForm.get('arrDt').setValue('');
                this.bookingForm.get('depDt').setValue('');
              },
              () => {
                loader.dismiss();
              });
          } else {
            formValue.paramType = 'add';
            console.log(formValue)
            loader.present();
            this.mdmService.receptionistaddBookingDetailsForMobile(formValue).subscribe(
              res => {
                if (res.status) {
                  loader.dismiss()
                  this.commonService.showSuccessToast(res.msg);
                  this.goBack()
                  this.bookingForm.reset();
                } else if (res.status == 0) {
                  loader.dismiss()
                  this.commonService.showErrorToast(res.statusDesc);
                  this.bookingForm.get('frmDt').setValue(null);
                  this.bookingForm.get('toDt').setValue(null);
                  this.bookingForm.get('arrDt').setValue(null);
                  this.bookingForm.get('depDt').setValue(null);
                } else {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                }
              },
              err => {
                loader.dismiss()
                this.commonService.showErrorToast('Something went wrong');
                this.bookingForm.get('frmDt').setValue(null);
                this.bookingForm.get('toDt').setValue(null);
                this.bookingForm.get('arrDt').setValue(null);
                this.bookingForm.get('depDt').setValue(null);
              },
              () => {
                loader.dismiss()
              });
          }
        }
      }
    }
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

  showAvailCheckbox() {
    console.log("Inside showAvailCheckbox")
    if (this.createConferenceForm.get('confCatId').value == '' || this.createConferenceForm.get('confUseId').value == '' || this.createConferenceForm.get('fromDate').value == '') {
      this.isDisable1 = true;
    } else if (this.createConferenceForm.get('confUseId').value == '4') {
      console.log("Inside showAvailCheckbox", this.createConferenceForm.get('confUseId').value)
      console.log(this.createConferenceForm.get('strtTime').value)
      if (this.createConferenceForm.get('strtTime').value == null || this.createConferenceForm.get('strtTime').value == '') {
        console.log(this.createConferenceForm.get('strtTime').value)
        this.isDisable1 = true
      } else {
        this.isDisable1 = false
      }
    }
    else { this.isDisable1 = false };
    console.log("isDisable1", this.isDisable1)
  }

  public async getConfrenceTimePeriodDetailsForMobile(event) {
    this.createConferenceForm.get('confUseId').reset();
    this.createConferenceForm.get('chkAvail').reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    console.log(event.target.value)
    const loader = await this.loadingController.create();
    let obj = {
      "gId": this.ghId,
      "cId": this.cityId,
      "conCatId": event.target.value
    }
    this.mdmService.getConfrenceTimePeriodDetailsForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.timePeriodData = res.data.data;
          loader.dismiss();
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      }
    );
  }
  public async getConfrenceAmountForMobile(event) {
    console.log(event.target.value)
    this.createConferenceForm.get('chkAvail').reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    const loader = await this.loadingController.create();
    let obj = {
      "conCatId": this.createConferenceForm.get('confCatId').value,
      "conUseId": event.target.value,
      "gId": this.ghId
    }
    this.mdmService.getConfrenceAmountForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.conferenceAmount = res.data.data;
          let amount = res.data.data.split(',')
          this.createConferenceForm.get('amount').setValue(amount[0])
          this.createConferenceForm.get('capacity').setValue(amount[1])
          if (event.target.value == 4) {
            this.showStartEndTime = true
          } else {
            this.showStartEndTime = false;
            this.createConferenceForm.get('strtTime').reset();
            this.createConferenceForm.get('endTime').reset();
          }
          loader.dismiss();
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      }
    );
  }

  public async checkAvail(event) {
    let data = {
      "conUseId": this.createConferenceForm.get('confUseId').value,
      "conCatId": this.createConferenceForm.get('confCatId').value,
      "formDate":this.createConferenceForm.get('fromDate').value,
      "startTime": this.createConferenceForm.get('strtTime').value ? this.createConferenceForm.get('strtTime').value : "",
      "endTime": this.createConferenceForm.get('endTime').value ? this.createConferenceForm.get('endTime').value : "",
      "gId": this.ghId
    }
    if (event.target.checked) {
      const loader = await this.loadingController.create({
        message: 'Please wait...'
      });
      loader.dismiss()
      this.mdmService.checkAvailability(data).subscribe(
        res => {
          loader.dismiss()
          console.log(res)
          this.isAvail = res.data;
        },
        err => {
          loader.dismiss();
          this.commonService.showErrorToast("Something Went Wrong!!")
        },
        ()=> {
          loader.dismiss()
        }
      )
    }
  }

  public addTwoHour2EndTime(event) {
    this.createConferenceForm.get('chkAvail').reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    let selectedDate = this.createConferenceForm.get('fromDate').value
    console.log("selectedDate", selectedDate)
    let selectedTime = event.target.value
    console.log("selectedTime", selectedTime)

    if (selectedDate == '') {
      this.commonService.showErrorToast("Please Select from date first !")
      this.createConferenceForm.get('strtTime').reset()
      return;
    }

    let finalDateTime = selectedDate + ":" + selectedTime + ":00Z";
    console.log("finalDateTime", finalDateTime)

    const givenTime = new Date(finalDateTime);
    const forwardTime = new Date(givenTime.getTime() + (2 * 60 * 60 * 1000));
    const forwardTimeStr = forwardTime.toISOString().slice(11, 16);
    console.log("time", forwardTimeStr);

    this.createConferenceForm.get('endTime').setValue(forwardTimeStr);
  }

  public async submitConferenceData(data) {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    this.isSubmitBtnClicked = true;
    if (this.createConferenceForm.invalid) {
      this.commonService.showErrorToast("Please Fill up all the required details!!")
      return;
    }

    // if (this.validateAdAmount()) {
    //   this.commonService.showErrorToast("Advance amount can't be greater than amount.")
    //   this.createConferenceForm.get('advanceAmount').reset();
    //   return;
    // }

    // if (this.createConferenceForm.get('advanceAmount').value == "") {
    //   this.commonService.showErrorToast("Please Enter Advance amount.")
    //   return;
    // }
    if (this.agreeToTnc == false) {
      this.commonService.showErrorToast("Please check terms and condition.")
      return;
    }

    // const modal = await this.modalcontroller.create({
    //   component: PaymentDetailsConferenceComponent,
    //   componentProps: { data }
    // });
    // return await modal.present();
    console.log(data.fromDate)
    // data.chkAvail
    let postData = {
      "cityId": data.cityId,
      "blockId": data.blockId,
      "name": data.name,
      "mobileNo": data.mobileNo,
      "address": data.address,
      "fromDate": data.fromDate,
      "capacity": data.capacity,
      "categoryType": data.categoryType,
      "confCatId": data.confCatId,
      "confUseId": data.confUseId,
      "amount": data.amount,
      "strtTime": data.strtTime ? data.strtTime : '',
      "endTime": data.endTime ? data.endTime : ''
    }
    // postData.fromDate = this.changeDateFormat(postData.fromDate);
    console.log(postData)
    loader.present();
    this.mdmService.saveConferenceDetails(postData,'add','RC').subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.commonService.showSuccessToast(res.data.msg)
          this.modalController.dismiss();
        } else if (res.status == 0) {
          loader.dismiss()
          this.commonService.showErrorToast(res.data.msg)

        } else {
          loader.dismiss();
          this.commonService.showErrorToast("Something Went Wrong!!")
        }
      }, err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      },
      ()=> {
        loader.dismiss()
      }
    )

    console.log(data)
  }

  public onChangeTnC(event) {
    if (event.target.checked) {
      this.agreeToTnc = true;
    }
    if (!event.target.checked) {
      this.agreeToTnc = false;
    }
  }

  public openTermsAndConditions() {
    this.presentPopover();
  }

  async presentPopover() {
    const popover = await this.popoverController.create({
      component: TermsandconditionComponent,
      translucent: true,
      componentProps: {
        data: this.cityId
      },
      backdropDismiss: true // Allow dismissing the popover by clicking the backdrop
    });

    // Present the popover and set a custom CSS class for the backdrop
    popover.present().then(() => {
      const backdrop: HTMLIonBackdropElement = popover.querySelector('ion-backdrop');
      if (backdrop) {
        backdrop.classList.add('custom-popover-backdrop');
      }
    });
  }

  public extractHHMM(datetime) {
    const parsedDate = new Date(datetime);
    const timePart = this.datePipe.transform(parsedDate, 'HH:mm');
    return timePart;
  }

  public setTo(time, key, name) {
    console.log("dfghjk", time);
    time = this.extractHHMM(time)
    // this.createBookingForm.get(key).setValue(moment(time).format("h:mm A"));
    const parsedTime = parseISO(`1970-01-01T${time}`);
    const t = format(parsedTime, 'HH:mm')
    console.log("t", t)
    this[name].get(key).setValue(t);
    console.log("time::::", this[name].get(key).value);

    if (key == 'strtTime') {
      this.createConferenceForm.get("chkAvail").reset();
      this.isAvail = -1;
      this.showAvailCheckbox();
      const newTime = addHours(parsedTime, 2);
      const t1 = format(newTime, 'HH:mm')
      this.createConferenceForm.get('endTime').setValue(t1);
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
  transformText(event, i) {
    const inputValue = event.target.value;
    const words = inputValue.split(' '); // Split the input by spaces
    const capitalizedWords = words.map(word => {
      return word.charAt(0).toUpperCase() + word.slice(1); // Capitalize each word
    });
    const capitalizedValue = capitalizedWords.join(' '); // Join the words back together with spaces
    this.getGuestDetails.controls[i].get('guestName').setValue(capitalizedValue);
  }
  transformTextForConf(event) {
    const inputValue = event.target.value;
    const words = inputValue.split(' '); // Split the input by spaces
    const capitalizedWords = words.map(word => {
      return word.charAt(0).toUpperCase() + word.slice(1); // Capitalize each word
    });
    const capitalizedValue = capitalizedWords.join(' '); // Join the words back together with spaces
    this.createConferenceForm.get('name').setValue(capitalizedValue);
  }
  public viewFile(data,filename) {
    console.log("viewfile for implementaton",data,filename)
    filename = filename.split('\/')[(filename.split('\/').length) - 1];
    this.commonService.viewFile(data,filename);
  }
}
