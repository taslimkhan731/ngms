import { HttpClient, HttpParams } from "@angular/common/http";
import { AfterViewInit, Component, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import { FormGroup, FormBuilder, FormArray, Validators } from "@angular/forms";
import { ActivatedRoute, NavigationStart, Router } from "@angular/router";
import { IonContent, LoadingController, ModalController, PopoverController, } from "@ionic/angular";
import { MdmService } from "src/app/Services/MDM/mdm.service";
import { CommonService } from "src/app/Services/common/common.service";
import { emailValidator, mobileNumberValidator, numberValidator, } from "src/app/validators/custom-validators";
import { DatePipe, Location } from "@angular/common";
import { TermsConditionComponent } from "./terms-condition/terms-condition.component";
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Filesystem, Directory, FilesystemDirectory } from '@capacitor/filesystem';
import { format, addHours, parseISO } from 'date-fns';
import { DocViewerComponent } from "src/app/shared/doc-viewer/doc-viewer.component";
import { FileOpener } from '@capawesome-team/capacitor-file-opener';
declare var window;
@Component({
  selector: "app-create-booking",
  templateUrl: "./create-booking.component.html",
  styleUrls: ["./create-booking.component.scss"]
})
export class CreateBookingComponent implements OnInit, AfterViewInit {
  public createBookingForm: FormGroup;
  public createConferenceForm: FormGroup;
  public age: any;
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild('dateInput', { static: false }) dateInput: any;
  @ViewChild('coutDate', { static: false }) coutDate: any;
  @ViewChild('arrDt', { static: false }) arrDt: any;
  @ViewChild('arrTm', { static: false }) arrTm: any;
  @ViewChild('depDt', { static: false }) depDt: any;
  @ViewChild('depTm', { static: false }) depTm: any;
  @ViewChild('fromDate', { static: false }) fromDate: any;
  @ViewChild('strtTime', { static: false }) strtTime: any;
  public reqType = [
    {
      title: "Room",
      value: "R",
      isAllowed: true,
    },
    {
      title: "Room with Conveyance",
      value: "RC",
      isAllowed: true,
    },
    {
      title: "Conveyance",
      value: "C",
      isAllowed: true,
    },
    {
      title: "Conference/Auditorium Hall/ Open Terrace",
      value: "CON",
      isAllowed: true,
    },
  ];
  showModals: boolean[] = [false, false, false];
  public pathName: any;
  public buttonName = "Submit";
  public bhawanName: any;
  public adultRow: number;
  public totalCount = 0;
  public roomOrBed = "room";
  public bhawanId: number;
  public categoryList = [];
  public DesignationList = [];
  public purposeList = [];
  public isAllowArrDep = false;
  public enableNeedConveyance = true;
  public userProfileDetails;
  public purposeVisit = false;
  public purpType;
  public arrivalPlaceList;
  public depPlaceList;
  public currentDate = new Date().toISOString().slice(0, 10);
  public minDate;
  public nextDayDt;
  public nextDay = new Date();
  public arrTime;
  public depTime;
  public bookingData;
  public allBookingData;
  public purpose;
  public designation;
  public arrPla;
  public depPla;
  public roomType;
  public guestDetails;
  public prevAdCount = 0;
  public prevChCount = 0;
  public fileName = "";
  public officialDocFileName = "";
  public gDocFileName = "";
  public showArr = false;
  public showDeparture = false;
  public isAllowConference = false;
  public cityId;
  public timePeriodData = [];
  public confType;
  public conferenceAmount;
  public showStartEndTime = false;
  public agreeToTnc = false;
  public isAvail = -1;
  public isDisable = true;
  public isSubmitBtnClicked = false;
  public fileTypeFlag = false;
  public fileSizeflag = false;
  public maxDate;
  public hallType = "Conference Hall";
  public pdfUrl: SafeResourceUrl;
  isEdit = true;
  dropdown: any;
  inputTxt: boolean;
  categoryDocName: any;
  accessCode: any;
  encRequest: any;
  public path: string | undefined;
  public base64String: string | undefined;
  public copyToAppDirectory: boolean = false;
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private commonService: CommonService,
    private popoverController: PopoverController,
    private modalcontroller: ModalController,
    private mdmService: MdmService,
    private datePipe: DatePipe,
    private loadingController: LoadingController,
    private location: Location,
    private sanitizer: DomSanitizer,
  ) {
    this.createBookingDetails();
    this.createConferenceInstance();
  }

  public createBookingDetails() {
    this.createBookingForm = this.fb.group({
      bookingId: [""],
      catg: [null],
      bookingType: ["room"],
      noOfRooms: ["1", Validators.compose([numberValidator])],
      noOfBedsDormitory: ["", Validators.compose([numberValidator])],
      bookingFor: ["", Validators.required],
      purp: ["", Validators.required],
      desg: ["", Validators.required],
      duringstay: ["0"],
      frmDt: [""],
      toDt: [""],
      noOfAdults: ["", Validators.required],
      gMobile: ["", Validators.compose([mobileNumberValidator])],
      gDocFile: [null],
      gDocName: [null],
      suppDocFile: [null],
      suppDocName: [null],
      catgDocFile: [null],
      catgDocName: [null],
      paramType: [""],
      gDocType: [null],
      reqType: [null],
      blockId: [null],
      noOfChildren: [0],
      mob: ["", Validators.compose([Validators.required, mobileNumberValidator])],
      emailId: ["", Validators.compose([emailValidator])],
      offAddr: ["", Validators.required],
      travelModeFr: [""],
      flNoFr: [""],
      arrDt: [""],
      arrTm: [""],
      arrPla: [""],
      travelModeTo: [""],
      flNoTo: [""],
      depDt: [""],
      depTm: [""],
      depPla: [""],
      catgType: [""],
      noOfDays: [""],
      arrTravel: [0],
      deptTravel: [0],
      list: this.fb.array([]),
    });
  }

  public createConferenceInstance() {
    this.createConferenceForm = this.fb.group({
      blockId: [""],
      name: ["", Validators.required],
      address: ["", Validators.required],
      mobileNo: ["", Validators.compose([Validators.required, mobileNumberValidator])],
      confCatId: ["", Validators.required],
      confUseId: ["", Validators.required],
      fromDate: ["", Validators.required],
      strtTime: [""],
      endTime: [""],
      amount: ["", Validators.required],
      capacity: [""],
      // advanceAmount: [''],
      categoryType: [""],
      chkAvail: [],
    });
  }

  ngOnInit() {
    console.log("Inside ngOnInit");
    const formattedDate = this.formattedDate(this.currentDate);
    console.log(formattedDate)
    const next7Date = new Date()
    next7Date.setDate(next7Date.getDate() + 7);
    this.nextDay.setDate(this.nextDay.getDate() + 1);
    console.log(next7Date)
    this.minDate = next7Date.toISOString().substring(0, 10);
    // this.nextDayDt = new Date(this.nextDayDt).toISOString().slice(0,10)
    console.log(this.currentDate);
    this.nextDayDt = this.nextDay.toISOString().slice(0, 10);
    this.router.events.subscribe((e) => {
      if (e instanceof NavigationStart) {
        console.log("Inside NavigationStart");
        this.bookingData = localStorage.getItem("bookingDetails");
        this.bookingData = JSON.parse(this.bookingData);
        this.isSubmitBtnClicked = false;
      }
    });
    this.bookingData = localStorage.getItem("bookingDetails");
    this.bookingData = JSON.parse(this.bookingData);
    console.log("LocalStorage Data", this.bookingData);
    // this.bookingData = this.commonService.getStoredDetails();
    this.getCategoryDetails();
    // this.createBookingForm.get('noOfDays').disable();
    this.createBookingForm.get("duringstay").setValue("0");
    if (this.createBookingForm.get("catg").value === null) {
      this.createBookingForm.get("reqType").disable();
    }
    this.route.queryParams.subscribe((params) => {
      this.bhawanId = params["bhawan_Id"];
      this.cityId = params["cityId"];
      this.bhawanName = params["bhawan_Name"];
      this.createBookingForm.get("blockId").setValue(this.bhawanId);
    });
    this.route?.url?.subscribe((params) => {
      this.editViewBooking(params);
    });
    console.log("bhawanId::::", this.bhawanId);
  }
  openModal(index: number) {
    console.log("djfhjdf")
    this.showModals[index] = true;
  }

  ionViewWillEnter(){
    this.userProfile();
  }
  ngAfterViewInit() {
    this.userProfile();
    console.log("Inside ngAfterViewInit");
    this.bookingData = localStorage.getItem("bookingDetails");
  }

  public editViewBooking(param) {
    if (param.length != 0) {
      this.pathName = param[0].path;
      if (this.pathName == "edit") {
        console.log("inside edit");
        this.buttonName = "Update";
        this.reqType[3].isAllowed = false;
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
      }
      if (this.pathName == "view") {
        this.isEdit = false;
        this.dropdown = null;
        console.log("inside view");
        this.buttonName = "";
        this.reqType[3].isAllowed = false;
        this.getBookingDetailsByBookingId(this.bookingData?.bookingId);
        this.createBookingForm.get("reqType").disable();
        this.createBookingForm.disable();
      }
    }
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

  oneTimeTransformText(event) {
    const inputValue = event.target.value;
    const words = inputValue.split(' '); // Split the input by spaces
    const capitalizedWords = words.map(word => {
      return word.charAt(0).toUpperCase() + word.slice(1); // Capitalize each word
    });
    const capitalizedValue = capitalizedWords.join(' '); // Join the words back together with spaces
    this.createConferenceForm.get('name').setValue(capitalizedValue);
  }



  public async getBookingDetailsByBookingId(id) {
    console.log("Inside getBookingDetailsByBookingId", id);
    const loader = await this.loadingController.create({
      message: "Please wait...",
    });
    if (id) {
      const params = new HttpParams().set("bookingId", id);
      loader.present();
      this.mdmService.getBookingDetailsByBookingId(params).subscribe(
        (res) => {
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
        },
        () => {
          loader.dismiss();
          // this.commonService.showErrorToast("Something went wrong");
        }
      );
    }
  }

  get getGuestDetails() {
    return this.createBookingForm.get("list") as FormArray;
  }

  public getAdultRowCount() {
    this.clearFormData();
    const noOfAdults = this.createBookingForm.get("noOfAdults").value || 0;
    const noOfChildren = this.createBookingForm.get("noOfChildren").value || 0;
    console.log(noOfAdults, noOfChildren);
    console.log(
      this.pathName === "edit" && this.guestDetails?.list?.length > 0
    );
    if (this.pathName === "edit" && this.guestDetails?.list?.length > 0) {
      let totalCount = noOfAdults + noOfChildren;
      console.log("totalCount", totalCount);
      console.log("guestDetails", this.guestDetails);
      for (let i = 0; i < totalCount; i++) {
        const guestArray = this.fb.group({
          guestId: [this.guestDetails.list[i]?.guestId ? this.guestDetails.list[i]?.guestId : "",],
          guestSlNo: [i + 1],
          guestName: [this.guestDetails.list[i]?.guestName ? this.guestDetails.list[i].guestName : "", Validators.required,],
          guestAge: [this.guestDetails.list[i]?.guestAge ? this.guestDetails.list[i].guestAge : "", Validators.required,],
          guestGender: [this.guestDetails.list[i]?.guestGender ? this.guestDetails.list[i].guestGender : "", Validators.required,],
          guestDesignation: [this.guestDetails.list[i]?.guestDesignation ? this.guestDetails.list[i].guestDesignation : "",],
          guestDepartment: [this.guestDetails.list[i]?.guestDepartment ? this.guestDetails.list[i].guestDepartment : "",],
          // guestMobile: [this.guestDetails.list[i]?.guestMobile ? this.guestDetails.list[i].guestMobile : ''],
          // guestDocType: [this.guestDetails.list[i]?.guestDocType ? this.guestDetails.list[i].guestDocType : ''],
          // guestDocPath: [this.guestDetails.list[i]?.guestDocPath ? this.guestDetails.list[i].guestDocPath : '']
        });

        this.getGuestDetails.push(guestArray);

      }
    } else {
      const totalCount = noOfAdults + noOfChildren;

      for (let i = 0; i < totalCount; i++) {
        const guestArray = this.fb.group({
          guestId: [i + 1],
          guestSlNo: [i + 1],
          guestName: ["", Validators.required],
          guestAge: ["", Validators.required],
          guestGender: ["", Validators.required],
          guestDesignation: [""],
          guestDepartment: [""],
        });
        this.getGuestDetails.push(guestArray);

      }
      console.log(this.createBookingForm.get("bookingFor").value);
      if (this.createBookingForm.get("bookingFor").value == "self") {
        const initialValues = {
          guestName: this.userProfileDetails.firstName,
          guestAge: this.age,
          guestGender: this.userProfileDetails.guestGender,

        };
        this.getGuestDetails.controls[0].patchValue(initialValues);
        // console.log(this.getGuestDetails.controls[0]);
      }
    }
  }

  public show(event) {
    this.createBookingForm.get("reqType").setValue(event.target.value ? event.target.value : "R")
    this.isSubmitBtnClicked = false
    if (event.target.value == "RC" || event.target.value == "C") {
      this.isAllowArrDep = true;
    } else {
      this.isAllowArrDep = false;
    }

    if (event.target.value == "C") {
      this.createBookingForm.get("frmDt").reset();
      this.createBookingForm.get("toDt").reset();
    }

    if (event.target.value == "CON") {
      this.isAllowConference = true;
      this.createConferenceForm.get("blockId").setValue(this.bhawanId);
      let catg = this.createBookingForm.get("catg").value;
      this.createConferenceForm.get("categoryType").setValue(catg);
      this.getConfrenceTypeDetails();
      console.log(this.isAllowConference);
    } else {
      this.isAllowConference = false;
      console.log(this.isAllowConference);
    }
  }

  public async getConfrenceTypeDetails() {
    let obj = {
      gId: this.bhawanId,
    };
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.getConfrenceTypeDetails(obj).subscribe(
      (res) => {
        if (res.status == 1) {
          loading.dismiss();
          this.confType = res.data.data;
          console.log(this.confType);
        } else {
          loading.dismiss();
        }
      },
      (err) => {
        loading.dismiss();
        this.commonService.showErrorToast(err.ststusDesc);
      }
    );
  }

  public validateFormDate() {
    // console.log(this.createBookingForm.get("toDt").value);
    if (this.createBookingForm.get("bookingType").value == "room") {
      if (this.createBookingForm.get("noOfRooms").value == "0" || this.createBookingForm.get("noOfRooms").value == "" || this.createBookingForm.get("noOfRooms").value == null) {
        this.commonService.showErrorToast("Atleast 1 room is required.");
        return false;
      }
    }
    if (this.createBookingForm.get("bookingType").value == "dormitory") {
      if (this.createBookingForm.get("noOfBedsDormitory").value == "0" || this.createBookingForm.get("noOfBedsDormitory").value < "0" || this.createBookingForm.get("noOfBedsDormitory").value == "" || this.createBookingForm.get("noOfBedsDormitory").value == null) {
        this.commonService.showErrorToast("Atleast 1 bed is required.");
        return false;
      }
    }

    if (this.createBookingForm.get("purp").value == "" || this.createBookingForm.get('purp').value == null) {
      this.commonService.showErrorToast("Please Select Purpose.");
      return false;
    }

    if (this.createBookingForm.get("reqType").value == "R" || this.createBookingForm.get("reqType").value == "RC") {
      if (this.createBookingForm.get("frmDt").value == "" || this.createBookingForm.get("frmDt").value == null) {
        this.commonService.showErrorToast("Please provide Check-in Date.");
        return false;
      }
      if (this.createBookingForm.get("toDt").value == "" || this.createBookingForm.get("toDt").value == null) {
        this.commonService.showErrorToast("Please provide Check-out Date.");
        return false;
      }
    }

    if (this.createBookingForm.get("noOfAdults").value == "" || this.createBookingForm.get("noOfAdults").value == null || this.createBookingForm.get("noOfAdults").value == "0") {
      this.commonService.showErrorToast("Atleast 1 adult is required.");
      return false;
    }

    if (this.createBookingForm.get("catg").value != "C4") {
      if (this.createBookingForm.get("catgType").value == "" || this.createBookingForm.get('catgType').value == null) {
        this.commonService.showErrorToast("Please Select Category Document Type.");
        return false;
      }
      if (this.createBookingForm.get("catgDocName").value == "" || this.createBookingForm.get("catgDocName").value == null) {
        this.commonService.showErrorToast("Please Upload Category Document.");
        return false;
      }
    }

    console.log("=========", this.createBookingForm.get("purp").value, "====", this.createBookingForm.get("suppDocName").value);
    if (this.createBookingForm.get("purp").value != "2") {
      if (this.createBookingForm.get("suppDocName").value == "" || this.createBookingForm.get("suppDocName").value == null) {
        this.commonService.showErrorToast("Please Upload " + this.purpType + " Document.");
        return false;
      }
    }

    if (this.createBookingForm.get("reqType").value == "RC" || this.createBookingForm.get("reqType").value == "C") {
      if (this.showArr == false && this.showDeparture == false) {
        this.commonService.showErrorToast("Please Select Conveyance Details.");
        return false;
      }
    }

    if (this.showArr == true) {
      console.log("gfhjg", this.createBookingForm.get("travelModeFr").value);
      if (this.createBookingForm.get("travelModeFr").value == "" || this.createBookingForm.get("travelModeFr").value == null || this.createBookingForm.get("flNoFr").value == "" || this.createBookingForm.get("flNoFr").value == null ||
        this.createBookingForm.get("arrDt").value == "" || this.createBookingForm.get("arrDt").value == null || this.createBookingForm.get("arrTm").value == "" || this.createBookingForm.get("arrTm").value == null || this.createBookingForm.get("arrPla").value == "" || this.createBookingForm.get("arrPla").value == null) {
        console.log("dfgddddddd")
        this.commonService.showErrorToast("Please fill up arrival details.");
        return false;
      }
    }
    if (this.showDeparture == true) {
      if (this.createBookingForm.get("travelModeTo").value == "" || this.createBookingForm.get("travelModeTo").value == null || this.createBookingForm.get("flNoTo").value == "" || this.createBookingForm.get("flNoTo").value == null || this.createBookingForm.get("depDt").value == "" || this.createBookingForm.get("depDt").value == null ||
        this.createBookingForm.get("depTm").value == "" || this.createBookingForm.get("depTm").value == null || this.createBookingForm.get("depPla").value == "" || this.createBookingForm.get("depPla").value == null) {
        this.commonService.showErrorToast("Please fill up Departure details.");
        return false;
      }
    }

    if (this.createBookingForm.get("bookingFor").value == "others") {
      console.log(this.createBookingForm.get("gDocName").value);
      if (this.createBookingForm.get("gMobile").value == null || this.createBookingForm.get("gMobile").value == "") {
        this.commonService.showErrorToast("Please Enter guest mobile number");
        return false;
      } else if (this.createBookingForm.get("gDocType").value == null) {
        this.commonService.showErrorToast("Please Enter guest document type.");
        return false;
      } else if (this.createBookingForm.get("gDocName").value == "" || this.createBookingForm.get("gDocName").value == null) {
        this.commonService.showErrorToast("Please Upload Guest Document.");
        return false;
      }
    }

    // if (this.createBookingForm.get('bookingFor').value == 'self') {
    //   this.createBookingForm.get('gMobile').reset()
    //   this.createBookingForm.get('gDocType').reset()
    //   this.createBookingForm.get('gDocFile').reset()
    //   this.createBookingForm.get('gDocName').reset()
    // }
  }

  public async submitBookingDetails(formValue) {
    console.log("test", this.createBookingForm.get("reqType").value);
    if (this.createBookingForm.get("catg").value === null) {
      this.commonService.showErrorToast("Select Category");
      this.goToTop()
      return;
    } else if (this.createBookingForm.get("reqType").value == "") {
      this.commonService.showErrorToast("Select Booking Type");
      this.goToTop()
      return;
    } else {
      this.isSubmitBtnClicked = true;
      const loader = await this.loadingController.create({
        message: "Please wait...",
      });
      formValue.frmDt = formValue.frmDt ? formValue.frmDt : "";
      formValue.toDt = formValue.toDt ? formValue.toDt : "";
      formValue.arrDt = formValue.arrDt ? formValue.arrDt : "";
      formValue.depDt = formValue.depDt ? formValue.depDt : "";
      // formValue.frmDt = formValue.frmDt ? this.changeDateFormat(formValue.frmDt) : "";
      // formValue.toDt = formValue.toDt ? this.changeDateFormat(formValue.toDt) : "";
      // formValue.arrDt = formValue.arrDt ? this.changeDateFormat(formValue.arrDt) : "";
      // formValue.depDt = formValue.depDt ? this.changeDateFormat(formValue.depDt) : "";
      formValue.frmDt = formValue.frmDt ? formValue.frmDt + " 09:00" : "";
      formValue.toDt = formValue.toDt ? formValue.toDt + " 08:00" : "";

      formValue.arrTm = formValue.arrTm ? this.onTimeChange(formValue.arrTm, "arrTime") : "";
      formValue.depTm = formValue.depTm ? this.onTimeChange(formValue.depTm, "depTime") : "";

      let i, child = 0, adult = 0;
      for (i = 0; i < formValue.list.length; i++) {
        console.log(formValue.list[i]?.guestAge);
        if (formValue.list[i].guestAge <= 12)
          child++;
        else {
          adult++;
        }
      }
      if (this.createBookingForm.get('noOfChildren').value < child || this.createBookingForm.get('noOfAdults').value < adult) {
        this.commonService.showErrorToast('Please check the number of children and adults');
        return;
      }
      if (child < this.createBookingForm.get("noOfChildren").value) {
        this.commonService.showErrorToast("Age of Childrens must be less than 12 years.");
        return;
      }

      let isReadyToSend = this.validateFormDate();
      console.log("isReadyToSend", isReadyToSend);

      if (isReadyToSend !== false) {
        if (this.createBookingForm.invalid) {
          this.commonService.showErrorToast("Please fill the required details.");
          return;
        }
        if (this.createBookingForm.valid) {
          console.log("formValue", formValue);
          if (this.pathName == "edit") {
            formValue.paramType = "update";
            loader.present();
            this.mdmService.updateBookingDetails(formValue).subscribe(
              (res) => {
                if (res.status == 1) {
                  console.log("mes",)
                  loader.dismiss();
                  this.commonService.showSuccessToast(res.statusDesc);
                  console.log("msg")
                  this.router.navigateByUrl("after-login/pages/guest/view-booking/guest-house");
                } else if (res.status == 0) {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                } else {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                }
              },
              () => {
                loader.dismiss();
                this.createBookingForm.get("frmDt").setValue("");
                this.createBookingForm.get("toDt").setValue("");
                this.createBookingForm.get("arrDt").setValue("");
                this.createBookingForm.get("arrTm").setValue("");
                this.createBookingForm.get("depDt").setValue("");
                this.createBookingForm.get("depTm").setValue("");
              }
            );
          } else {
            formValue.paramType = "add";
            loader.present();
            this.mdmService.saveBookingDetails(formValue).subscribe(
              (res) => {
                if (res.status == 1) {
                  loader.dismiss();
                  this.commonService.showSuccessToast(res.statusDesc);
                  // this.router.navigateByUrl('after-login/pages/guest/view-booking/guest-house');
                  this.router.navigate(["after-login/pages/guest/view-booking/guest-house"]);
                  this.createBookingForm.reset();
                } else if (res.status == 0) {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                  this.createBookingForm.get("frmDt").setValue("");
                  this.createBookingForm.get("toDt").setValue("");
                  this.createBookingForm.get("arrDt").setValue("");
                  this.createBookingForm.get("arrTm").setValue("");
                  this.createBookingForm.get("depDt").setValue("");
                  this.createBookingForm.get("depTm").setValue("");
                } else {
                  loader.dismiss();
                  this.commonService.showErrorToast(res.statusDesc);
                }
              },
              err => {
                loader.dismiss();
                this.createBookingForm.get("frmDt").setValue("");
                this.createBookingForm.get("toDt").setValue("");
                this.createBookingForm.get("arrDt").setValue("");
                this.createBookingForm.get("arrTm").setValue("");
                this.createBookingForm.get("depDt").setValue("");
                this.createBookingForm.get("depTm").setValue("");
              },
              () => {
                loader.dismiss();
                // this.commonService.showErrorToast("Something went wrong");
                this.createBookingForm.get("frmDt").setValue("");
                this.createBookingForm.get("toDt").setValue("");
                this.createBookingForm.get("arrDt").setValue("");
                this.createBookingForm.get("arrTm").setValue("");
                this.createBookingForm.get("depDt").setValue("");
                this.createBookingForm.get("depTm").setValue("");
              },
            );
          }
        }
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


  public setBookingData(data) {
    console.log("Inside setbookingdata 1st", data?.travelModeTo);
    if (data !== undefined) {
      this.bhawanId = data?.blockId;
      console.log(this.bhawanId);
      this.fileName = data.catgDocName ? data.catgDocName.split("\/")[data.catgDocName.split("\/").length - 1] : "";
      this.officialDocFileName = data.suppDocName ? data.suppDocName.split("\/")[data.suppDocName.split("\/").length - 1] : "";
      console.log("officialDocFileName", this.officialDocFileName);
      this.gDocFileName = data.gDocName ? data.gDocName.split("\/")[data.gDocName.split("\/").length - 1] : "";
      // this.suppDocFileName = data.suppDocName ? data.suppDocName.split('\\')[(data.suppDocName.split('\\').length) - 1] : '';

      this.roomType = data?.roomType;
      if (this.roomType == "RC" || this.roomType == "C") {
        this.isAllowArrDep = true;
      } else if (this.roomType == "R") {
        this.isAllowArrDep = false;
      }
      this.createBookingForm.get("catg").setValue(data?.catg);
      this.roomOrBed = data?.bookingType;
      this.purpose = data?.purp;
      this.designation = data?.desg;
      this.changeCategory(data?.catg);
      this.showArr = data?.arrivalConv;
      this.showDeparture = data?.departueConv;

      this.arrPla = data?.arrPla;
      console.log("arrPla", data?.arrPla);

      this.depPla = data?.depPla;
      this.prevAdCount = data?.noOfAdults;
      this.prevChCount = data?.noOfChildren;

      this.onTimeChange(data?.arrTm, "arrTime");
      this.onTimeChange(data?.depTm, "depTime");
      this.createBookingForm.get("bookingId").setValue(data?.bookingId);
      this.createBookingForm.get("reqType").setValue(data?.roomType);
      this.createBookingForm.get("bookingType").setValue(data?.bookingType);
      this.createBookingForm.get("noOfRooms").setValue(data?.noOfRooms);
      this.createBookingForm.get("noOfBedsDormitory").setValue(data?.noOfBedsDormitory);
      this.createBookingForm.get("bookingFor").setValue(data?.bookingFor);
      this.createBookingForm.get("duringstay").setValue(data?.duringstay);
      this.createBookingForm.get("arrDt").setValue(data?.arrDt ? data?.arrDt : '');
      this.createBookingForm.get("depDt").setValue(data?.depDt ? data?.depDt : "");
      // this.createBookingForm.get("arrTime").setValue(data?.arrTm ? data?.arrTm : null);
      // this.createBookingForm.get("depTime").setValue(data?.depTm ? data?.depTm : "");
      this.createBookingForm.get("frmDt").setValue(data?.frmDt ? data?.frmDt.slice(0, 10) : "");
      this.createBookingForm.get("toDt").setValue(data?.toDt ? data?.toDt.slice(0, 10) : "");
      if (this.createBookingForm.get('reqType').value == 'R' || this.createBookingForm.get('reqType').value == 'RC') {
        this.onChangeFromDt();
      }
      this.createBookingForm.get("noOfAdults").setValue(data?.noOfAdults);
      this.createBookingForm.get("gMobile").setValue(data?.gMobile);
      this.createBookingForm.get("gDocFile").setValue(data?.gDocFile);
      this.createBookingForm.get("gDocName").setValue(this.gDocFileName);
      this.createBookingForm.get("suppDocFile").setValue(data?.suppDocFile);
      this.createBookingForm.get("suppDocName").setValue(this.officialDocFileName);
      this.createBookingForm.get("catgDocFile").setValue(data?.catgDocFile);
      this.createBookingForm.get("catgDocName").setValue(this.fileName);
      this.createBookingForm.get("paramType").setValue(data?.paramType);
      this.createBookingForm.get("gDocType").setValue(data?.gDocType);
      this.createBookingForm.get("blockId").setValue(data?.blockId);
      this.createBookingForm.get("noOfChildren").setValue(data?.noOfChildren);
      this.createBookingForm.get("mob").setValue(data?.mob);
      this.createBookingForm.get("emailId").setValue(data?.emailId);
      this.createBookingForm.get("offAddr").setValue(data?.offAddr);
      if (data?.travelModeFr == "T" || data?.travelModeFr == "t") {
        this.createBookingForm.get("travelModeFr").setValue(data?.travelModeFr);
        console.log("IonChange ArrTravel Func");
        this.onChangeModeOfArrTravel("t");
      }
      if (data?.travelModeFr == "F" || data?.travelModeFr == "f") {
        this.createBookingForm.get("travelModeFr").setValue(data?.travelModeFr);
        this.onChangeModeOfArrTravel("f");
      }
      this.createBookingForm.get("flNoFr").setValue(data?.flNoFr);
      this.createBookingForm.get("arrTm").setValue(data?.arrTm ? this.arrTime : "");
      if (data?.travelModeTo == "T" || data?.travelModeTo == "t") {
        this.createBookingForm.get("travelModeTo").setValue(data?.travelModeTo);
        this.onChangeModeOfDepTravel("t");
      }
      if (data?.travelModeTo == "F" || data?.travelModeTo == "f") {
        this.createBookingForm.get("travelModeTo").setValue(data?.travelModeTo);
        this.onChangeModeOfDepTravel("f");
      }
      this.createBookingForm.get("flNoTo").setValue(data?.flNoTo);
      this.createBookingForm.get("depTm").setValue(data?.depTm ? this.depTime : "");
      this.createBookingForm.get("catgType").setValue(data?.catgType);
      this.createBookingForm.get("noOfDays").setValue(data?.noOfDays);
      this.clearFormData();
      this.guestDetails = data;
      data.list.forEach((ITEM, i) => {
        let list = this.createBookingForm.get("list") as FormArray;
        const guestArray = this.fb.group({
          guestId: [ITEM !== undefined ? ITEM.guestId : ""],
          guestSlNo: [i + 1],
          guestName: [ITEM !== undefined ? ITEM.guestName : ""],
          guestAge: [ITEM !== undefined ? ITEM.guestAge : ""],
          guestGender: [ITEM !== undefined ? ITEM.guestGender : ""],
          guestDesignation: [ITEM !== undefined ? ITEM.guestDesignation : ""],
          guestDepartment: [ITEM !== undefined ? ITEM.guestDepartment : ""],
        });
        list.push(guestArray);
      });
    }
  }

  public setGuestDetailsList(data) {
    data.list.forEach((ITEM) => {
      let list = this.createBookingForm.get("list") as FormArray;
      const guestArray = this.fb.group({
        guestId: [ITEM !== undefined ? ITEM.guestId : ""],
        guestSlNo: [ITEM !== undefined ? ITEM.guestSlNo : ""],
        guestName: [ITEM !== undefined ? ITEM.guestName : ""],
        guestAge: [ITEM !== undefined ? ITEM.guestAge : ""],
        guestGender: [ITEM !== undefined ? ITEM.guestGender : ""],
        guestDesignation: [ITEM !== undefined ? ITEM.guestDesignation : ""],
        guestDepartment: [ITEM !== undefined ? ITEM.guestDepartment : ""],
      });
      list.push(guestArray);
    });
  }

  public clearFormData() {
    let formData = this.createBookingForm.get("list") as FormArray;
    while (formData.length != 0) {
      formData.removeAt(0);
    }
  }

  public changeCategory(event) {
    this.createBookingForm.get("reqType").setValue("R")
    console.log("Inside changeCategory", event);
    this.createBookingForm.get("purp").reset();
    this.createBookingForm.get("desg").reset();
    // this.createBookingForm.get("bookingFor").setValue("");
    // this.createConferenceForm.get("name").setValue("");
    this.clearConveyance();
    if (event === "") {
      this.DesignationList = [];
      this.purposeList = [];
      this.createBookingForm.get("reqType").setValue("");
      this.createBookingForm.get("reqType").disable();
    } else if (event === "C1") {
      this.enableNeedConveyance = true;
      this.reqType[1].isAllowed = true;
      this.reqType[2].isAllowed = true;
      this.createBookingForm.get("reqType").setValue("");
      this.createBookingForm.get("reqType").enable();
      if (this.pathName == "view") {
        this.createBookingForm.get("reqType").disable();
      }
      this.getDesignationDetails();
      this.getPurposeDetails();
    } else if (event === "C2" || event === "C3" || event === "C4") {
      this.createBookingForm.get("duringstay").setValue("0");
      this.enableNeedConveyance = true;
      this.reqType[1].isAllowed = false;
      this.reqType[2].isAllowed = false;
      if (this.pathName == 'view') {
        this.reqType[3].isAllowed = false;
      }
      this.createBookingForm.get("reqType").setValue("");
      this.createBookingForm.get("reqType").enable();
      if (this.pathName == "view") {
        this.createBookingForm.get("reqType").disable();
      }
      this.getDesignationDetails();
      this.getPurposeDetails();
    }
  }

  public getDesignationDetails() {
    this.mdmService
      .getDesignationDetails(this.createBookingForm.get("catg").value)
      .subscribe(
        (res) => {
          if (res.status == 1) {
            console.log("Inside getDesignationDetails");
            this.DesignationList = res.data;
            if (
              this.createBookingForm.get("catg").touched ||
              this.createBookingForm.get("catg").dirty
            ) {
              this.createBookingForm.get("desg").setValue("");
            } else {
              this.createBookingForm.get("desg").setValue(this.designation);
            }
          } else if (res.status == 0) {
            this.commonService.showErrorToast("Something went wrong");
          }
        },
        () => {
          // this.commonService.showErrorToast("Something went wrong");
        }
      );
  }

  public getPurposeDetails() {
    const params = new HttpParams()
      .set("blockId", this.bhawanId)
      .set("catgId", this.createBookingForm.get("catg").value);
    this.mdmService.getPurposeDetails(params).subscribe(
      (res) => {
        if (res.status == 1) {
          console.log("Inside getPurposeDetails");
          this.purposeList = res.data;
          if (this.purpose) {
            this.createBookingForm.get("purp").setValue(this.purpose);
          }
        } else if (res.status == 0) {
          this.commonService.showErrorToast("Something went wrong");
        }
      },
      () => {
        // this.commonService.showErrorToast("Something went wrong");
      }
    );
  }

  public isEnableRoomOrBed(event) {
    if (event == "room") {
      this.roomOrBed = "room";
      this.createBookingForm.get("noOfBedsDormitory").setValue("");
      this.createBookingForm.get("noOfRooms").setValue("1");
    } else if (event == "dormitory") {
      this.roomOrBed = "dormitory";
      this.createBookingForm.get("noOfRooms").setValue("");
      this.createBookingForm.get("noOfBedsDormitory").setValue("1");
    }
  }

  public getCategoryDetails() {
    this.mdmService.getCategoryDetails().subscribe(
      (res) => {
        if (res.status == 1) {
          this.categoryList = res.data;
        } else if (res.status == 0) {
          this.commonService.showErrorToast("Something went wrong");
        }
      },
      () => {
        // this.commonService.showErrorToast("Something went wrong");
      }
    );
  }

  public userProfile() {
    this.mdmService.userProfile().subscribe(
      (res) => {
        if (res.status == 1) {
          this.userProfileDetails = res.data;
          this.createBookingForm.get("mob").setValue(res.data.contactNo);
          this.createBookingForm.get("emailId").setValue(res.data.email);
        } else if (res.status == 0) {
          this.commonService.showErrorToast("Something went wrong");
        }
      },
      () => {
        // this.commonService.showErrorToast("Something went wrong");
      }
    );
  }

  public onChangePurposeVisit(event) {
    console.log("&&&&&&&&&&&&&&&&&&&onChangePurposeVisit");
    if (event.target.value === "2") {
      this.purposeVisit = false;
      this.createBookingForm.get("suppDocName").reset();
      this.createBookingForm.get("suppDocFile").reset();
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

  public calculateDateDiff() {
    console.log("calculateDateDiff")
    this.createBookingForm.get("arrDt").setValue(null);
    this.createBookingForm.get("depDt").setValue(null);
    if (this.createBookingForm.get("frmDt").value !== null && this.createBookingForm.get("toDt").value !== null) {
      let frmDt = new Date(this.formattedDate(this.createBookingForm.get("frmDt").value));
      let toDt = new Date(this.formattedDate(this.createBookingForm.get("toDt").value));
      console.log(frmDt, toDt, this.createBookingForm.get("frmDt").value, this.createBookingForm.get("toDt").value);
      let days = Math.floor(
        (Date.UTC(toDt.getFullYear(), toDt.getMonth(), toDt.getDate()) -
          Date.UTC(frmDt.getFullYear(), frmDt.getMonth(), frmDt.getDate())) /
        (1000 * 60 * 60 * 24)
      );
      this.createBookingForm.get("noOfDays").setValue(days);
      console.log("noOfDays", this.createBookingForm.get("noOfDays").value)
    }
  }

  public resetOnChange() {
    console.log("sdjdf")
    this.createBookingForm.get("toDt").reset();
    this.createBookingForm.get("noOfDays").reset();
  }

  public formattedDate(date) {
    const parts = date.split("-");
    const formattedDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);

    return formattedDate;
  }
  public changeDateFormatyymmdd(date) {
    console.log(date);
    if (date) {
      const objectDate = new Date(date.split("-").reverse().join("-"));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  public openDatepicker(name) {
    console.log("hgjhjhgj")
    // console.log("Attempting to open datepicker for element with id:", name);
    // const element = document.getElementById(name);
    // console.log(element)
    // if (element) {
    //   element.click();
    // } else {
    //   console.error("Element not found with id:", name);
    // }
    this.dateInput.nativeElement.click();
  }

  public changeDateFormat() {
    this.createConferenceForm.get("chkAvail").reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    // console.log(date);
    // let objectDate = new Date(date);
    // let day = String(objectDate.getDate()).padStart(2, "0");
    // let month = String(objectDate.getMonth() + 1).padStart(2, "0");
    // let year = objectDate.getFullYear();
    // let formatDate = day + "-" + month + "-" + year;
    // return formatDate;
  }

  public onChangeFromDt() {
    let frmDt = this.createBookingForm.get("frmDt").value;
    console.log(frmDt);

    const formattedDate = this.formattedDate(frmDt);
    console.log(formattedDate)
    const nextDate = new Date(formattedDate);
    const next7Date = new Date(formattedDate)
    console.log(nextDate, next7Date);
    nextDate.setDate(nextDate.getDate() + 1); // Calculate the next date (increase by one day)
    next7Date.setDate(next7Date.getDate() + 7); // Calculate the next date (increase by 7 day)
    // Format the next date as "YYYY-MM-DD" and update the form value
    // console.log(nextDate.toISOString().substring(0, 10));
    this.maxDate = nextDate.toISOString().substring(0, 10);
    // this.minDate = next7Date.toISOString().substring(0, 10);

    console.error("Inside onChangeFromDt", this.maxDate);
    // console.error("Inside onChangeFromDt", this.minDate);
  }

  public onChangeBookingFor(event) {
    console.log("&&&&&&&&&&&&&");
    if (event.detail.value === 'self') {
      this.createBookingForm.get('noOfAdults').setValue(1);
    } else {
      this.createBookingForm.get('noOfAdults').setValue('');
    }

    if (this.pathName !== "view" && this.pathName !== "edit") {

      if (event.detail.value === 'self') {
        this.getGuestDetails.clear();
        const guestArray = this.fb.group({
          guestId: [1],
          guestSlNo: [1],
          guestName: [''],
          guestAge: [''],
          guestGender: [''],
          guestDesignation: [''],
          guestDepartment: ['']
        })
        this.getGuestDetails.push(guestArray);


        const dob = this.userProfileDetails.dateOfBirth;
        this.calculateAge(dob);
        const initialValues = {
          guestName: this.userProfileDetails.firstName,
          guestAge: this.age,
          guestGender: this.userProfileDetails.guestGender,

        };
        this.getGuestDetails.controls[0].patchValue(initialValues);

        this.createBookingForm.get('noOfAdults').setValue(1);
      } else {
        this.getGuestDetails.controls[0].reset();
        this.createBookingForm.get("gMobile").reset();
        this.createBookingForm.get("gDocType").reset();
        this.createBookingForm.get("gDocFile").reset();
        this.createBookingForm.get("gDocName").reset();
        // this.createBookingForm.get('noOfAdults').setValue('0');
      }
    }
  }

  calculateAge(dateOfBirth: string) {
    const parts = dateOfBirth.split('-');
    if (parts.length === 3) {
      const day = parseInt(parts[0], 10);
      const month = parseInt(parts[1], 10) - 1; // Month is 0-indexed
      const year = parseInt(parts[2], 10);

      const birthDate = new Date(year, month, day);
      const currentDate = new Date();

      // Calculate the difference in years
      this.age = currentDate.getFullYear() - birthDate.getFullYear();
      console.log(this.age,currentDate.getFullYear(),birthDate.getFullYear())
    }
  }

  public onChangeModeOfArrTravel(event) {
    this.createBookingForm.get("arrPla").patchValue("");
    console.log("1st", this.createBookingForm.get("arrPla").value, "hghh");

    if (event == "T" || event == "t") {
      event = "t";
    }
    if (event == "F" || event == "f") {
      event = "f";
    }
    const params = new HttpParams().set("id", this.bhawanId).set("type", event);
    this.mdmService.getFlightTrainList(params).subscribe(
      (res) => {
        if (res.status == 1) {
          this.arrivalPlaceList = res.data;

          if (this.pathName === "view" || this.pathName === "edit") {
            if (this.createBookingForm.get("travelModeFr").touched || this.createBookingForm.get("travelModeFr").dirty) {
              this.createBookingForm.get("arrPla").patchValue("");
              console.log("1st", this.createBookingForm.get("arrPla").value, "hghh");
            } else {
              this.createBookingForm.get("arrPla").setValue(this.arrPla);
            }
          }
        }
      },
      (err) => {
        console.error(err);
      }
    );
    console.log("1st", this.createBookingForm.get("arrPla").value, "hghh");
  }

  public onChangeModeOfDepTravel(event) {
    this.createBookingForm.get("depPla").setValue("");
    if (event == "T" || event == "t") {
      event = "t";
    }
    if (event == "F" || event == "f") {
      event = "f";
    }
    const params = new HttpParams().set("id", this.bhawanId).set("type", event);
    this.mdmService.getFlightTrainList(params).subscribe(
      (res) => {
        if (res.status == 1) {
          this.depPlaceList = res.data;
          if (this.pathName === "view" || this.pathName === "edit") {
            if (this.createBookingForm.get("travelModeTo").touched || this.createBookingForm.get("travelModeTo").dirty) {
              this.createBookingForm.get("depPla").patchValue("");
              console.log("1st", this.createBookingForm.get("arrPla").value, "hghh");
            } else {
              this.createBookingForm.get("depPla").setValue(this.depPla);
            }
          }
        }
      },
      (err) => {
        console.error(err);
      }
    );
  }

  public OnselectNewFile(event, filename, file) {
    this.createBookingForm.get(filename).reset();
    this.createBookingForm.get(file).reset();
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
      this.createBookingForm.get(filename).setValue(event.target.files[0].name);
      this.createBookingForm.get(file).setValue(reader.result);
      // console.log('File uploaded:', this.filename);
    };
    // if (event.target.files && event.target.files[0]) {
    //   const reader = new FileReader();
    //   reader.onload = () => {
    //     this.createBookingForm.get(filename).setValue(event.target.files[0].name);
    //     this.createBookingForm.get(file).setValue(reader.result);
    //   };
    //   reader.readAsDataURL(event.target.files[0]);
    // }
  }

  public onTimeChange(event, time) {
    if (event) {
      var timeSplit = event.split(":"),
        hours,
        minutes,
        meridian;
      hours = timeSplit[0];
      minutes = timeSplit[1];
      if (hours > 12) {
        meridian = "PM";
        hours -= 12;
      } else if (hours < 12) {
        meridian = "AM";
        if (hours == 0) {
          hours = 12;
        }
      } else {
        meridian = "PM";
      }
      this[time] = hours + ":" + minutes + " " + meridian;
      return this[time];
    }
  }

  public clearConveyance() {
    this.createBookingForm.get("arrTravel").setValue(0);
    this.showArr = false;
    this.createBookingForm.get("travelModeFr").reset();
    this.createBookingForm.get("flNoFr").reset();
    this.createBookingForm.get("arrDt").reset();
    this.createBookingForm.get("arrTm").reset();
    this.createBookingForm.get("arrPla").reset();

    this.createBookingForm.get("deptTravel").setValue(0);
    this.showDeparture = false;
    this.createBookingForm.get("travelModeTo").reset();
    this.createBookingForm.get("flNoTo").reset();
    this.createBookingForm.get("depDt").reset();
    this.createBookingForm.get("depTm").reset();
    this.createBookingForm.get("depPla").reset();
  }

  public conveyanceCheck(event) {
    if (event.detail.checked) {
      if (event.target.value == "arrTravel") {
        this.createBookingForm.get("arrTravel").setValue(1);
        this.showArr = true;
      }
      if (event.target.value == "deptTravel") {
        this.createBookingForm.get("deptTravel").setValue(1);
        this.showDeparture = true;
      }
      console.log(this.showArr)
      console.log(this.showDeparture)
    } else {
      if (event.target.value == "arrTravel") {
        this.createBookingForm.get("arrTravel").setValue(0);
        this.showArr = false;
        this.createBookingForm.get("travelModeFr").reset();
        this.createBookingForm.get("flNoFr").reset();
        this.createBookingForm.get("arrDt").reset();
        this.createBookingForm.get("arrTm").reset();
        this.createBookingForm.get("arrPla").reset();
      }
      if (event.target.value == "deptTravel") {
        this.createBookingForm.get("deptTravel").setValue(0);
        this.showDeparture = false;
        this.createBookingForm.get("travelModeTo").reset();
        this.createBookingForm.get("flNoTo").reset();
        this.createBookingForm.get("depDt").reset();
        this.createBookingForm.get("depTm").reset();
        this.createBookingForm.get("depPla").reset();
      }
    }
  }

  showAvailCheckbox() {
    console.log("Inside showAvailCheckbox");
    if (
      this.createConferenceForm.get("confCatId").value == "" ||
      this.createConferenceForm.get("confUseId").value == "" ||
      this.createConferenceForm.get("fromDate").value == ""
    ) {
      this.isDisable = true;
    } else if (this.createConferenceForm.get("confUseId").value == "4") {
      console.log("Inside showAvailCheckbox", this.createConferenceForm.get("confUseId").value);
      console.log(this.createConferenceForm.get("strtTime").value);
      if (
        this.createConferenceForm.get("strtTime").value == null ||
        this.createConferenceForm.get("strtTime").value == ""
      ) {
        console.log(this.createConferenceForm.get("strtTime").value);
        this.isDisable = true;
      } else {
        this.isDisable = false;
      }
    } else {
      this.isDisable = false;
    }
    console.log("IsDisable", this.isDisable);
  }

  public setHallType(hallId) {
    if (hallId == 1)
      this.hallType = 'Multipurpose Hall';
    else if (hallId == 2)
      this.hallType = ' Open Terrace';
    else if (hallId == 3)
      this.hallType = 'Auditorium';
    else if (hallId == 4)
      this.hallType = 'Conference Hall';
    else if (hallId == 5)
      this.hallType = 'Lounge';
  }

  public async getConfrenceTimePeriodDetailsForMobile(event) {
    this.setHallType(event.target.value)
    this.createConferenceForm.get("confUseId").reset();
    this.createConferenceForm.get("chkAvail").reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    console.log(event.target.value);
    const loader = await this.loadingController.create();
    let obj = {
      gId: this.bhawanId,
      conCatId: event.target.value,
    };
    this.mdmService.getConfrenceTimePeriodDetailsForMobile(obj).subscribe(
      (res) => {
        if (res.status == 1) {
          this.timePeriodData = res.data.data;
          loader.dismiss();
        } else {
          loader.dismiss();
        }
      },
      () => {
        loader.dismiss();
        // this.commonService.showErrorToast("Something Went Wrong!!");
      }
    );
  }
  public async getConfrenceAmountForMobile(event) {
    console.log(event.target.value);
    this.createConferenceForm.get("chkAvail").reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    const loader = await this.loadingController.create();
    let obj = {
      conCatId: this.createConferenceForm.get("confCatId").value,
      conUseId: event.target.value,
      gId: this.bhawanId,
    };
    this.mdmService.getConfrenceAmountForMobile(obj).subscribe(
      (res) => {
        if (res.status == 1) {
          this.conferenceAmount = res.data.data;
          let amount = res.data.data.split(",");
          this.createConferenceForm.get("amount").setValue(amount[0]);
          this.createConferenceForm.get("capacity").setValue(amount[1]);
          if (event.target.value == 4) {
            this.showStartEndTime = true;
          } else {
            this.showStartEndTime = false;
            this.createConferenceForm.get("strtTime").reset();
            this.createConferenceForm.get("endTime").reset();
          }
          loader.dismiss();
        } else {
          loader.dismiss();
        }
      },
      () => {
        loader.dismiss();
        // this.commonService.showErrorToast("Something Went Wrong!!");
      }
    );
  }

  public addTwoHour2EndTime(event) {
    console.log("Inside addTwoHour2EndTime")
    this.createConferenceForm.get("chkAvail").reset();
    this.isAvail = -1;
    this.showAvailCheckbox();
    let selectedDate = this.createConferenceForm.get("fromDate").value;
    console.log("selectedDate", selectedDate);
    let selectedTime = event.target.value;
    console.log("selectedTime", selectedTime);

    if (selectedDate == "") {
      this.commonService.showErrorToast("Please Select from date first !");
      this.createConferenceForm.get("strtTime").reset();
      return;
    }

    let finalDateTime = selectedDate + ":" + selectedTime + ":00Z";
    console.log("finalDateTime", finalDateTime);

    const givenTime = new Date(finalDateTime);
    const forwardTime = new Date(givenTime.getTime() + 2 * 60 * 60 * 1000);
    const forwardTimeStr = forwardTime.toISOString().slice(11, 16);
    console.log("time", forwardTimeStr);

    this.createConferenceForm.get("endTime").setValue(forwardTimeStr);
  }

  public validateAdAmount() {
    let totalAmount = this.createConferenceForm.get("amount").value;
    let enteredAmount = this.createConferenceForm.get("advanceAmount").value;
    // let enteredAmount = event.target.value
    console.log("Inside validateAdAmount");

    if (enteredAmount > totalAmount) {
      console.log("Inside validateAdAmount Comp");
      return true;
    }
  }

  public async submitConferenceData(data) {

    this.isSubmitBtnClicked = true;
    if (this.createConferenceForm.invalid) {
      this.commonService.showErrorToast("Please Fill up all the required details!!");
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
      this.commonService.showErrorToast("Please check terms and condition.");
      return;
    }

    // const modal = await this.modalcontroller.create({
    //   component: PaymentDetailsConferenceComponent,
    //   componentProps: { data }
    // });
    // return await modal.present();
    console.log(data.fromDate);
    // data.fromDate = this.changeDateFormat(data.fromDate);
    console.log(data.fromDate);
    const loader = await this.loadingController.create({
      message: "Please wait...",
    });
    loader.dismiss();
    this.mdmService.saveConferenceDetails(data).subscribe(
      (res) => {
        console.log('res', res);
        if (res.status == 1) {
          this.commonService.storeDetails(res.data);
          this.accessCode = res.data.accessCode;
          this.encRequest = res.data.encRequest;
          console.log('accessCode', this.accessCode);
          console.log('encRequest', this.encRequest);
          const paymentParams = {
            param1: this.accessCode,
            param2: this.encRequest,
          };
          loader.dismiss();

          this.router.navigate(["/after-login/pages/guest/create-booking/paymentDetails",], {
            state: paymentParams,
          });
          // this.router.navigateByUrl("/payment",)
        } else if (res.status == 0) {
          loader.dismiss()
          this.commonService.showErrorToast(res.data.msg);
        } else {
          loader.dismiss();
          this.commonService.showErrorToast("Something Went Wrong!!");
        }
      },
      err=>{
        loader.dismiss();
      },
      ()=>{
        loader.dismiss()
      });

    console.log(data);
  }

  ionViewDidLeave() {
    this.allBookingData = [];
    this.createConferenceInstance();
    this.createBookingDetails();
    localStorage.removeItem("bookingDetails");
  }

  public async goBack() {
    this.location.back();
  }

  async presentPopover() {
    const popover = await this.popoverController.create({
      component: TermsConditionComponent,
      translucent: true,
      componentProps: {
        data: this.cityId,
      },
      backdropDismiss: true, // Allow dismissing the popover by clicking the backdrop
    });

    // Present the popover and set a custom CSS class for the backdrop
    popover.present().then(() => {
      const backdrop: HTMLIonBackdropElement =
        popover.querySelector("ion-backdrop");
      if (backdrop) {
        backdrop.classList.add("custom-popover-backdrop");
      }
    });
  }

  public onChangeTnC(event) {
    if (event.target.checked) {
      this.agreeToTnc = true;
    }
    if (!event.target.checked) {
      this.agreeToTnc = false;
    }
  }

  public async checkAvail(event) {
    console.log("checkAvail")
    let data = {
      conUseId: this.createConferenceForm.get("confUseId").value,
      conCatId: this.createConferenceForm.get("confCatId").value,
      formDate: this.createConferenceForm.get("fromDate").value,
      startTime: this.createConferenceForm.get("strtTime").value ? this.createConferenceForm.get("strtTime").value : "",
      endTime: this.createConferenceForm.get("endTime").value ? this.createConferenceForm.get("endTime").value : "",
      gId: this.bhawanId,
    };
    console.log("data", data)
    if (event.target.checked) {
      const loader = await this.loadingController.create({
        message: "Please wait...",
      });
      loader.dismiss();
      this.mdmService.checkAvailability(data).subscribe(
        (res) => {
          console.log(res);
          this.isAvail = res.data;
          loader.dismiss()
        },
        err => {
          loader.dismiss()
        },
        () => {
          loader.dismiss();
          // this.commonService.showErrorToast("Something Went Wrong!!");
        }
      );
    }
  }

  public openTermsAndConditions() {
    this.presentPopover();
  }

  goToTop() {
    this.content.scrollToTop(1000);
  }

  base64ToArrayBuffer(base64: string): Uint8Array {
    const prefix = "data:application/pdf;base64,";
    base64 = base64.slice(prefix.length);
    console.log(base64)
    const binaryString: string = window.atob(base64);
    const binaryLen: number = binaryString.length;
    const bytes: Uint8Array = new Uint8Array(binaryLen);

    for (let i: number = 0; i < binaryLen; i++) {
      const ascii: number = binaryString.charCodeAt(i);
      bytes[i] = ascii;
    }
    return bytes;
  }


  saveByteArray(reportName, byte) {
    var blob = new Blob([byte], { type: "application/pdf" });
    var link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    var fileName = reportName;
    link.download = fileName;
    console.log("Inside saveByteArray")
    link.click();
    console.log("Clicked")
  };
  public async openFile(): Promise<void> {
    if (!this.path) {
      return;
    }
    await FileOpener.openFile({ path: this.path });
    console.log('File opened');
  }

  public async writeFile(base64String, name): Promise<void> {
    this.path = undefined;
    await Filesystem.requestPermissions();
    if (base64String && name) {
      await Filesystem.writeFile({
        data: base64String,
        path: name,
        directory: Directory.External,
      });
      const { uri } = await Filesystem.getUri({
        path: name,
        directory: Directory.External,
      });
      this.path = uri;
    }
    this.openFile();
  }
  async viewFile(data, fileName) {
    // if (data.split(';')[0].split('/')[1] == 'pdf') {
    //this.downloadAndDisplayPdf(data,'example.pdf')
    this.writeFile(data, fileName + '.' + data.split(';')[0].split('/')[1]);
    // } else {
    //   const modal = await this.modalcontroller.create({
    //     component: DocViewerComponent,
    //     componentProps: { data }
    //   });
    //   modal.onDidDismiss().then((data) => {
    //   })
    //   return await modal.present();
    // }

  }

  async downloadAndDisplayPdf(base64Data: string, filename: string) {
    console.log("hdjhxjchjxc")
    const blob = this.base64ToBlob(base64Data, 'application/pdf');
    const dataUrl = await this.blobToDataUrl(blob);

    const directory = Directory.Documents; // Choose appropriate directory
    const result = await Filesystem.writeFile({
      path: `${directory}/${filename}`,
      data: dataUrl, // Use the data URL string
      directory: FilesystemDirectory.Documents,
      recursive: true,
    });

    if (result) {
      const pdfFilePath = result.uri;
      this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(pdfFilePath);
    } else {
      console.log("dfjgdjjjjjjjjjjjj")
      // Handle error
    }
  }

  base64ToBlob(base64Data: string, contentType: string): Blob {
    const prefix = "data:application/pdf;base64,";
    base64Data = base64Data.slice(prefix.length);
    const byteCharacters = atob(base64Data);
    const byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
      const slice = byteCharacters.slice(offset, offset + 512);
      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }
    return new Blob(byteArrays, { type: contentType });
  }

  async blobToDataUrl(blob: Blob): Promise<string> {
    return new Promise((resolve) => {
      const reader = new FileReader();
      reader.onload = (event) => {
        resolve(event.target.result as string);
      };
      reader.readAsDataURL(blob);
    });
  }
  public onInput(event: any) {
    const inputValue = event.target.value;
    this.createBookingForm.patchValue({
      mob: inputValue.replace(/[^0-9]/g, '')
    });
  }
}
