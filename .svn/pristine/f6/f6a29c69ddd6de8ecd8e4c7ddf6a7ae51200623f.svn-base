import { DatePipe, Location } from '@angular/common';
import { HttpParams } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IonContent, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { emailValidator, mobileNumberValidator, numberValidator } from 'src/app/validators/custom-validators';
import { format, addHours, parseISO } from 'date-fns';

@Component({
  selector: 'app-add-guest-details',
  templateUrl: './add-guest-details.component.html',
  styleUrls: ['./add-guest-details.component.scss'],
})
export class AddGuestDetailsComponent implements OnInit {

  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('dateInput', { static: false }) dateInput: any;
  @ViewChild('coutDate', { static: false }) coutDate: any;
  @ViewChild('arrDt', { static: false }) arrDt: any;
  @ViewChild('arrTm', { static: false }) arrTm: any;
  @ViewChild('depDt', { static: false }) depDt: any;
  @ViewChild('depTm', { static: false }) depTm: any;
  public bookingForm: FormGroup;
  public fileTypeFlag = false;
  public fileSizeflag = false;
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
  public isSubmitBtnClicked = false;
  public requestType = 'R';
  public cityId: any;
  public ghId: any;
  public visitPurpose: any;
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
  public isDisable = true;
  public purpose;
  public designation;
  public disableForExt = false;
  public bookingData;
  public title = "Add";
  public purpType;
  public showArr = false;
  public showDeparture = false;
  public splitData;
  public roomType;
  public maxDate;
  public guestHouseList = []
  getAllCityList = [];
  constructor(private mdmService: MdmService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    public commonService: CommonService,
    private location: Location,
    private cdr: ChangeDetectorRef,
    private datePipe: DatePipe,
    private loadingController: LoadingController) {
    this.createFormInstance();
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
      gMobile: ['', Validators.compose([mobileNumberValidator])],
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
      mob: ['', Validators.compose([mobileNumberValidator])],
      emailId: ['', Validators.compose([emailValidator])],
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
      arrTravel: [null],
      deptTravel: [null],
      splitCity: [''],
      splitGHouse: [''],
      list: this.fb.array([])
    })
  }

  ngOnInit() {

  }

  async ionViewWillEnter() {
    this.bookingData = localStorage.getItem("bookingDetails")
    this.bookingData = JSON.parse(this.bookingData)
    // this.bookingForm.get('bookingFor').disable();

    console.log(this.bookingData)
    await this.getCategoryList();

    this.bookingForm.get('duringstay').setValue('0');
    if (this.bookingForm.get('catg').value === null) {
      this.bookingForm.get('reqType').disable();
    }
    this.route.queryParams.subscribe((params) => {
      this.cityId = params['city_id'];
      this.ghId = params['ghId'];
      this.bookingForm.get('blockId').setValue(this.ghId);
      console.log("this.route.queryParams", params)
    });
    this.route?.url?.subscribe(params => {
      this.editViewBooking(params);
      console.log("this.route.url", params)
    });
  }

  public isEnableRoomOrBed(event) {
    if (event == "room") {
      this.bookingForm.get("noOfBedsDormitory").setValue("");
      this.bookingForm.get("noOfRooms").setValue("1");
    } else if (event == "dormitory") {
      this.bookingForm.get("noOfRooms").setValue("");
      this.bookingForm.get("noOfBedsDormitory").setValue("1");
    }
  }

  setSelectValue(value: any) {
    console.log("Inside setSelectValue", typeof (value))
    this.bookingForm.get('splitGHouse')?.setValue(value);
    this.cdr.detectChanges();
  }

  getCityList() {
    // this.mdmService.getCityDataTableForMobile().subscribe(res => {
    //   console.log(res);
    //   if (res.status === 1) {
    //     this.getAllCityList = res.data;
    //   }
    // }, error => {
    //   console.log(error);
    // })
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
  }

  public async getGuestHouseList(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait..'
    })
    console.log("Inside getCityName", "......", this.bookingForm.get('splitGHouse').value, ".......")
    // this.bookingForm.get('splitCity').setValue(event)
    loading.present();
    this.mdmService.getCityWiseGUestHouse(event).subscribe(res => {
      this.guestHouseList = res.data;
      this.bookingForm.get('splitGHouse')?.setValue(this.ghId);
      loading.dismiss();
      console.log(this.guestHouseList, this.bookingForm.get('splitGHouse').value)
    }, err => {
      loading.dismiss();
    }, () => {
      loading.dismiss();
    })
  }

  public getBhawanName(event) {
    console.log(event)
    // this.bookingForm.get('splitGHouse').setValue(event);
  }

  public editViewBooking(param) {
    if (param.length != 0) {
      this.pathName = param[0].path;
      if (this.pathName == 'edit') {
        this.buttonName = 'Update';
        this.title = 'Update';
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
        // this.bookingForm.get('bookingFor').disable()
      }
      if (this.pathName == 'modEdit') {
        this.buttonName = 'Update';
        this.title = 'Update';
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
        // this.bookingForm.get('bookingFor').enable()
      }
      if (this.pathName == 'view') {
        this.isDisable = false;
        this.title = "View"
        this.buttonName = ''
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
        this.bookingForm.disable();
        // this.bookingForm.get('bookingFor').disable()
      }
      if (this.pathName == 'updateSplit') {
        this.getCityList();
        this.buttonName = 'Save';
        this.title = 'Add';
        // this.splitData = this.commonService.getStoredDetails();
        // this.getCityName(this.bookingData.blockId)
        this.setBookingData(this.bookingData)
        this.bookingForm.get('frmDt').disable();
        this.bookingForm.get('toDt').disable();
        this.bookingForm.get('noOfAdults').disable();
        this.bookingForm.get('noOfChildren').disable();
        // this.bookingForm.get('bookingFor').disable()
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
        err => {
          loader.dismiss()
          this.commonService.showErrorToast('Something went wrong');
        },
        ()=>{
          loader.dismiss()
        });
    }
  }

  // get Category details
  // async getCategoryList() {
  //   console.log("Inside getCategoryList")
  //   await this.mdmService.getCategoryData().subscribe(res => {
  //     this.categoryList =  res.data;
  //     console.log(this.categoryList)
  //   })
  // }
  async getCategoryList() {
    console.log("Inside getCategoryList");
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    try {
      const response = await this.mdmService.getCategoryData().toPromise();
      this.categoryList = response.data;
      console.log(this.categoryList);
      loading.dismiss();
      // Any additional logic that depends on categoryList can be placed here
    } catch (error) {
      console.error("Error fetching category data:", error);
      loading.dismiss();
    }
  }

  // get category wise booking Type
  changeCategory(event) {
    this.requestType = 'R'
    this.bookingForm.get("reqType").setValue(this.requestType)
    this.bookingForm.get('purp').reset();
    this.bookingForm.get('desg').reset();
    this.bookingForm.get('catg').setValue(event);

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
      if (this.pathName == 'view') {
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
    this.isSubmitBtnClicked = false
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
          // if (this.purpose) {
          //   this.bookingForm.get('purp').setValue(this.purpose);
          // }
          if (this.pathName === 'view' || this.pathName === 'edit' || this.pathName === 'updateSplit' || this.pathName === 'modEdit') {
            // this.bookingForm.get('arrPla').setValue(this.arrPla);
            if (this.bookingForm.get("catg").touched || this.bookingForm.get("catg").dirty) {
              this.bookingForm.get("purp").patchValue("");
              console.log("1st", this.bookingForm.get("purp").value, "hghh");
            } else {
              this.bookingForm.get("purp").setValue(this.purpose);
            }
          }
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
  async getDesignation(data) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getDesignationDetailsList(data).subscribe(
      res => {
        if (res.status == 1) {
          this.DesignationList = res.data;
          if (this.pathName === 'view' || this.pathName === 'edit' || this.pathName === 'updateSplit' || this.pathName === 'modEdit') {
            // this.bookingForm.get('arrPla').setValue(this.arrPla);
            if (this.bookingForm.get("catg").touched || this.bookingForm.get("catg").dirty) {
              this.bookingForm.get("desg").patchValue("");
              console.log("1st", this.bookingForm.get("desg").value, "hghh");
            } else {
              this.bookingForm.get("desg").setValue(this.designation);
            }
          }
          // this.bookingForm.get('desg').setValue(this.designation);
          loading.dismiss();
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something Went Wrong!');
          loading.dismiss();
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
        loading.dismiss();
      },
      () => {
        loading.dismiss()
      });
  }
  goBack() {
    this.location.back()
    // let currLocation = localStorage.getItem("currentLocation")
    // this.router.navigateByUrl(currLocation);
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
      let frmDt = new Date(this.formattedDate(this.bookingForm.get('frmDt').value));
      let toDt = new Date(this.formattedDate(this.bookingForm.get('toDt').value));
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
    // if (event.target.files && event.target.files[0]) {
    //   const reader = new FileReader();
    //   reader.onload = () => {
    //     this.bookingForm.get(filename).setValue(event.target.files[0].name);
    //     this.bookingForm.get(file).setValue(reader.result);
    //   };
    //   reader.readAsDataURL(event.target.files[0]);
    // }
  }

  public async onChangeModeOfArrTravel(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    this.bookingForm.get('arrPla').setValue('');
    if (event == "T" || event == "t") {
      event = "t";
    }
    if (event == "F" || event == "f") {
      event = "f";
    }
    const params = new HttpParams()
      .set('id', this.ghId)
      .set('type', event);
    loading.present();
    this.mdmService.getFlightTrainList(params).subscribe(
      res => {
        if (res.status == 1) {
          this.arrivalPlaceList = res.data;
          if (this.pathName === 'view' || this.pathName === 'edit' || this.pathName === 'updateSplit' || this.pathName === 'modEdit') {
            // this.bookingForm.get('arrPla').setValue(this.arrPla);
            if (this.bookingForm.get("travelModeFr").touched || this.bookingForm.get("travelModeFr").dirty) {
              this.bookingForm.get("arrPla").patchValue("");
              console.log("1st", this.bookingForm.get("arrPla").value, "hghh");
            } else {
              this.bookingForm.get("arrPla").setValue(this.arrPla);
            }
           
          }
          loading.dismiss();
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
          loading.dismiss();
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
        loading.dismiss();
      });
  }

  public async onChangeModeOfDepTravel(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    this.bookingForm.get('depPla').setValue('');
    if (event == "T" || event == "t") {
      event = "t";
    }
    if (event == "F" || event == "f") {
      event = "f";
    }
    const params = new HttpParams()
      .set('id', this.ghId)
      .set('type', event);
    loading.present();
    this.mdmService.getFlightTrainList(params).subscribe(
      res => {
        if (res.status == 1) {
          this.depPlaceList = res.data;
          if (this.pathName === 'view' || this.pathName === 'edit' || this.pathName === 'updateSplit' || this.pathName === 'modEdit') {
            this.bookingForm.get('depPla').setValue(this.depPla);
          }
          loading.dismiss();
        } else if (res.status == 0) {
          this.commonService.showErrorToast('Something went wrong');
          loading.dismiss();
        }
      },
      err => {
        this.commonService.showErrorToast('Something went wrong');
        loading.dismiss();
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
  public async setBookingData(data) {
    console.log("Inside setBookingData", data)
    if (data !== undefined) {
      if(data.ext == '1') {
        this.isDisable = false;
        this.disableForExt = true;
        // this.bookingForm.disable();
        this.bookingForm.get('frmDt').enable()
        this.bookingForm.get('toDt').enable()
      }
      this.fileName = data.catgDocName ? data.catgDocName.split('\/')[(data.catgDocName.split('\/').length) - 1] : '';
      this.officialDocFileName = data.suppDocName ? data.suppDocName.split('\/')[(data.suppDocName.split('\/').length) - 1] : '';
      this.gDocFileName = data.gDocName ? data.gDocName.split('\/')[(data.gDocName.split('\/').length) - 1] : '';
      this.cityId = data?.cityId;
      this.ghId = data?.blockId;
      this.requestType = data?.roomType;
      if (this.requestType == 'RC' || this.requestType == 'C') {
        this.isAllowArrDep = true;
      } else if (this.requestType == 'R') {
        this.isAllowArrDep = false;
      }

      if (this.pathName == 'updateSplit') {
        await this.getGuestHouseList(data?.cityId)
        console.log("Inside updateSplit", this.guestHouseList, this.bookingForm.get('splitGHouse').value)
        this.bookingForm.get('splitCity').setValue(data?.cityId);
        // this.bookingForm.get('splitGHouse').setValue(this.ghId);
        console.log("Inside updateSplit", this.guestHouseList, this.bookingForm.get('splitGHouse').value)

        // this.cdr.detectChanges();
        // this.setSelectValue(data?.blockId);
      }
      console.log(this.categoryList)
      let catg = data?.catg;
      this.bookingForm.get('catg').setValue(data?.catg);
      console.log(catg)

      this.purpose = data?.purp;
      this.designation = data?.desg;
      this.changeCategory(data?.catg)
      this.showArr = data?.arrivalConv;
      this.showDeparture = data?.departueConv;
      this.bookingForm.get('arrTravel').setValue(this.showArr ? 1 : null)
      this.bookingForm.get('deptTravel').setValue(this.showDeparture ? 1 : null)

      this.arrPla = data?.arrPla;
      console.log("arrPla", data?.arrPla);
      this.depPla = data?.depPla;
      this.onTimeChange(data?.arrTm, 'arrTime');
      this.onTimeChange(data?.depTm, 'depTime');
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
      // this.bookingForm.get('arrDt').setValue(this.changeDateFormatyymmdd(data?.arrDt ? data?.arrDt : null));
      // this.bookingForm.get('depDt').setValue(this.changeDateFormatyymmdd(data?.depDt ? data?.depDt : null));
      // this.bookingForm.get('frmDt').setValue(data?.frmDt ? this.changeDateFormatyymmdd(data?.frmDt.split(' ')[0]) : '');
      // this.bookingForm.get('toDt').setValue(data?.toDt ? this.changeDateFormatyymmdd(data?.toDt.split(' ')[0]) : '');
      this.bookingForm.get('arrDt').setValue(data?.arrDt ? data?.arrDt : '');
      this.bookingForm.get('depDt').setValue(data?.depDt ? data?.depDt : '');
      this.bookingForm.get('frmDt').setValue(data?.frmDt ? data?.frmDt.slice(0, 10) : '');
      if (this.bookingForm.get('reqType').value == 'R' || this.bookingForm.get('reqType').value == 'RC') {
        this.onChangeFromDt();
      }
      this.bookingForm.get('toDt').setValue(data?.toDt ? data?.toDt.slice(0, 10) : '');
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
        this.bookingForm.get('travelModeFr').setValue(data?.travelModeFr);
        this.onChangeModeOfArrTravel('t');
      }
      if (data?.travelModeFr == 'F' || data?.travelModeFr == 'f') {
        this.bookingForm.get('travelModeFr').setValue(data?.travelModeFr);
        this.onChangeModeOfArrTravel('f');
      }
      this.bookingForm.get('flNoFr').setValue(data?.flNoFr);
      // let arrTm = data?.arrTm.split(' ')[0];
      this.bookingForm.get('arrTm').setValue(data?.arrTm?.split(' ')[0]);
      // this.bookingForm.get('arrPla').setValue(data?.arrPla);
      if (data?.travelModeTo == 'T' || data?.travelModeFr == 't') {
        this.bookingForm.get('travelModeTo').setValue(data?.travelModeFr);
        this.onChangeModeOfDepTravel('t');
      }
      if (data?.travelModeTo == 'F' || data?.travelModeFr == 'f') {
        this.bookingForm.get('travelModeTo').setValue(data?.travelModeFr);
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
  public resetOnChange() {
    this.bookingForm.get('toDt').reset();
    this.bookingForm.get('noOfDays').reset();
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
  }

  public formattedDate(date) {
    const parts = date.split("-");
    const formattedDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);

    return formattedDate;
  }

  public onChangeFromDt() {
    console.log("onchange")
    let frmDt = this.bookingForm.get('frmDt').value;
    const formattedDate = this.formattedDate(frmDt);
    console.log(formattedDate)
    const nextDate = new Date(formattedDate);
    nextDate.setDate(nextDate.getDate() + 1); // Calculate the next date (increase by one day)
    // Format the next date as "YYYY-MM-DD" and update the form value
    this.maxDate = nextDate.toISOString().substring(0, 10)
    console.log("maxdate,,,,,",this.maxDate)
  }

  public validateFormDate() {
    if (this.bookingForm.get('bookingType').value == 'room') {
      if (this.bookingForm.get('noOfRooms').value == '0' || this.bookingForm.get('noOfRooms').value == '' || this.bookingForm.get('noOfRooms').value == null) {
        this.commonService.showErrorToast("Atleast 1 room is required.");
        return false;
      }
    }

    if (this.bookingForm.get('bookingType').value == 'dormitory') {
      if (this.bookingForm.get('noOfBedsDormitory').value == '0' || this.bookingForm.get('noOfBedsDormitory').value < '0' || this.bookingForm.get('noOfBedsDormitory').value == '' || this.bookingForm.get('noOfBedsDormitory').value == null) {
        this.commonService.showErrorToast("Atleast 1 bed is required.");
        return false;
      }
    }

    if (this.bookingForm.get("purp").value == "" || this.bookingForm.get('purp').value == null) {
      this.commonService.showErrorToast("Please Select Purpose.");
      return false;
    }

    if (this.bookingForm.get('reqType').value == 'R' || this.bookingForm.get('reqType').value == 'RC') {
      if (this.bookingForm.get('frmDt').value == '' || this.bookingForm.get('frmDt').value == null) {
        this.commonService.showErrorToast("Please provide Check-in Date.")
        return false;
      }
      if (this.bookingForm.get('toDt').value == '' || this.bookingForm.get('toDt').value == null) {
        this.commonService.showErrorToast("Please provide Check-out Date.")
        return false;
      }
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

    if (this.bookingForm.get("purp").value != "2") {
      if (this.bookingForm.get("suppDocName").value == "" || this.bookingForm.get("suppDocName").value == null) {
        this.commonService.showErrorToast("Please Upload " + this.purpType + " Document.");
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




  public async submitBookingDetails(formValue) {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.bookingForm.get('catg').value === null) {
      this.commonService.showErrorToast("Select Category");
      this.gotToTop();
      return;
    }
    else if (this.bookingForm.get('reqType').value == '') {
      this.commonService.showErrorToast("Select Booking Type");
      this.gotToTop();
      return;
    } else {
      this.isSubmitBtnClicked = true;
      // formValue.frmDt = this.bookingForm.get('frmDt').value ? this.changeDateFormat(this.bookingForm.get('frmDt').value) : '';
      // formValue.toDt = this.bookingForm.get('toDt').value ? this.changeDateFormat(this.bookingForm.get('toDt').value) : '';
      // formValue.arrDt = this.bookingForm.get('arrDt').value ? this.changeDateFormat(this.bookingForm.get('arrDt').value) : '';
      // formValue.depDt = this.bookingForm.get('depDt').value ? this.changeDateFormat(this.bookingForm.get('depDt').value) : '';
      formValue.frmDt = this.bookingForm.get('frmDt').value ? this.bookingForm.get('frmDt').value : '';
      formValue.toDt = this.bookingForm.get('toDt').value ? this.bookingForm.get('toDt').value : '';
      formValue.arrDt = this.bookingForm.get('arrDt').value ? this.bookingForm.get('arrDt').value : '';
      formValue.depDt = this.bookingForm.get('depDt').value ? this.bookingForm.get('depDt').value : '';
      formValue.frmDt = formValue.frmDt ? formValue.frmDt + ' 09:00' : '';
      formValue.toDt = formValue.toDt ? formValue.toDt + ' 08:00' : '';

      formValue.arrTm = formValue.arrTm ? this.onTimeChange(formValue.arrTm, 'arrTime') : '';
      formValue.depTm = formValue.depTm ? this.onTimeChange(formValue.depTm, 'depTime') : '';
      console.log(formValue.arrTm, this.bookingForm.get('arrTm').value)

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

      if (isReadyToSend !== false) {
        if (this.bookingForm.invalid) {
          this.commonService.showErrorToast("Please fill the required details.");
          return;
        }
        if (this.bookingForm.valid) {
          if (this.bookingForm.get('bookingFor').value == 'others') {
            if (this.bookingForm.get('mob').value == '' || this.bookingForm.get('mob').value == null) {
              formValue.mob = formValue.gMobile;
            }
          }
          if (this.pathName == 'edit' || this.pathName == 'modEdit') {
            formValue.paramType = 'update';
            loader.present();
            this.mdmService.updateBookingDetails(formValue).subscribe(
              res => {
                if (res.status == true) {
                  loader.dismiss();
                  this.commonService.showSuccessToast(res.msg);
                  this.goBack();
                  // this.router.navigateByUrl('after-login/pages/orgadmin/application-menu/application-entry');
                } else if (res.status == false) {
                  console.log("hjgfhj")
                  loader.dismiss();
                  if(!res.msg) {
                    this.commonService.showErrorToast("Something Went Wrong");
                  } else {
                    this.commonService.showErrorToast(res.msg);
                  }
                  this.bookingForm.get('frmDt').setValue(null);
                  this.bookingForm.get('toDt').setValue(null);
                  this.bookingForm.get('arrDt').setValue('');
                  this.bookingForm.get('depDt').setValue('');
                  this.bookingForm.get('arrTm').setValue('');
                  this.bookingForm.get('depTm').setValue('');
                }
              },
              err => {
                loader.dismiss();
                console.log("dfjdfjh")
                this.commonService.showErrorToast("Something Went Wrong");
                this.bookingForm.get('frmDt').setValue(null);
                this.bookingForm.get('toDt').setValue(null);
                this.bookingForm.get('arrDt').setValue('');
                this.bookingForm.get('depDt').setValue('');
                this.bookingForm.get('arrTm').setValue('');
                this.bookingForm.get('depTm').setValue('');
              },
              () => {
                loader.dismiss();
              });
          } else if (this.pathName == 'updateSplit') {
            formValue.paramType = 'updateSplit';
            console.log(formValue)
            loader.present();
            this.mdmService.updateBookingDetails(formValue).subscribe(
              res => {
                if (res.status == 1) {
                  loader.dismiss();
                  this.commonService.showSuccessToast(res.msg);
                  this.goBack();
                  // this.router.navigateByUrl('after-login/pages/orgadmin/application-menu/application-entry');
                } else if (res.status == 0) {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.msg);
                  this.bookingForm.get('frmDt').setValue(null);
                  this.bookingForm.get('toDt').setValue(null);
                  this.bookingForm.get('arrDt').setValue('');
                  this.bookingForm.get('depDt').setValue('');
                  this.bookingForm.get('arrTm').setValue('');
                  this.bookingForm.get('depTm').setValue('');
                }
              },
              err => {
                loader.dismiss();
                this.commonService.showErrorToast("Something Went Wrong");
                this.bookingForm.get('frmDt').setValue(null);
                this.bookingForm.get('toDt').setValue(null);
                this.bookingForm.get('arrDt').setValue('');
                this.bookingForm.get('depDt').setValue('');
                this.bookingForm.get('arrTm').setValue('');
                this.bookingForm.get('depTm').setValue('');
              },
              () => {
                loader.dismiss();
              });
          } else {
            formValue.paramType = 'add';
            console.log("dgfjdgfj")
            loader.present();
            this.mdmService.adminsaveBookingDetails(formValue).subscribe(
              res => {
                if (res.status) {
                  loader.dismiss()
                  this.commonService.showSuccessToast(res.msg);
                  // this.router.navigateByUrl('after-login/pages/guest/view-booking/guest-house');
                  // this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry'])
                  this.goBack()
                  this.bookingForm.reset();
                } else if (res.status == 0) {
                  loader.dismiss()
                  this.commonService.showErrorToast(res.msg);
                  this.bookingForm.get('frmDt').setValue(null);
                  this.bookingForm.get('toDt').setValue(null);
                  this.bookingForm.get('arrDt').setValue(null);
                  this.bookingForm.get('depDt').setValue(null);
                  this.bookingForm.get('arrTm').setValue(null);
                  this.bookingForm.get('depTm').setValue(null);
                }
              },
              err => {
                loader.dismiss()
                console.log(err)
                this.commonService.showErrorToast("Something Went Wrong");
                this.bookingForm.get('frmDt').setValue(null);
                this.bookingForm.get('toDt').setValue(null);
                this.bookingForm.get('arrDt').setValue(null);
                this.bookingForm.get('depDt').setValue(null);
                this.bookingForm.get('arrTm').setValue(null);
                this.bookingForm.get('depTm').setValue(null);
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
    console.log("Inside conveyanceCheck")
    if (event.detail.checked) {
      if (event.target.value == 'arrTravel') {
        this.bookingForm.get('arrTravel').setValue(1);
        this.showArr = true;
      }
      if (event.target.value == 'deptTravel') {
        this.bookingForm.get('deptTravel').setValue(1)
        this.showDeparture = true;
      }
    }
    else if (!event.detail.checked) {
      if (event.target.value == 'arrTravel') {
        this.bookingForm.get('arrTravel').setValue(null);
        this.showArr = false;
        // this.bookingForm.get('travelModeFr').reset();
        // this.bookingForm.get('flNoFr').reset();
        // this.bookingForm.get('arrDt').reset();
        // this.bookingForm.get('arrTm').reset();
        // this.bookingForm.get('arrPla').reset();
      }
      if (event.target.value == 'deptTravel') {
        this.bookingForm.get('deptTravel').setValue(null)
        this.showDeparture = false;
        // this.bookingForm.get('travelModeTo').reset();
        // this.bookingForm.get('flNoTo').reset();
        // this.bookingForm.get('depDt').reset();
        // this.bookingForm.get('depTm').reset();
        // this.bookingForm.get('depPla').reset();
      }
    }
    console.log(this.bookingForm.get('arrTravel').value)
    console.log(this.bookingForm.get('deptTravel').value)

  }

  gotToTop() {
    this.content.scrollToTop(1000);
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
}

