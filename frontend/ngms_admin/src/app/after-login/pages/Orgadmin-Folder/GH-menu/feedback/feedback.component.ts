import { Component, OnInit, ViewChild } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { NavigationEnd, Router } from "@angular/router";
import {
  AlertController,
  InfiniteScrollCustomEvent,
  IonContent,
  IonModal,
  LoadingController,
  Platform,
} from "@ionic/angular";
import { MdmService } from "src/app/Services/MDM/mdm.service";
import { CommonService } from "src/app/Services/common/common.service";

@Component({
  selector: "app-feedback",
  templateUrl: "./feedback.component.html",
  styleUrls: ["./feedback.component.scss"],
})
export class FeedbackComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  @ViewChild("filtermodal") filtermodal: IonModal;

  filterForm: FormGroup;
  bookingId: any;

  public splitBtnClass = [
    { btnName: "Feedback Report", btnIcon: "eye-outline", active: false },
    { btnName: "Guest Feedback Report", btnIcon: "eye-outline", active: false },
    { btnName: "Total Feedback Report", btnIcon: "eye-outline", active: false },
    { btnName: "Comments Report", btnIcon: "eye-outline", active: false },
  ];
  guestHouseList: any;
  getAllCityList = [];
  selctGH = "";
  selctCity = "";
  selectedValue = "";
  feedbackList = [];
  count: any;
  public pageSize = 5;
  public pageNum = 1;
  public totalPages;
  backToTop: boolean = false;
  public isFirstApiHit = true;
  // isViewClicked:any=false;

  public obj = {
    cId: this.selctCity,
    gId: this.selctGH,
    reqType: "AL",
    name: "",
    bookingId: "",
    pageSize: this.pageSize,
    pageNum: this.pageNum,
  };
  constructor(
    private mdmService: MdmService,
    private router: Router,
    private platform: Platform,
    private loadingController: LoadingController,
    private commonservice: CommonService,
    private fb: FormBuilder,
    private alertController: AlertController,
  ) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function (event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.filtermodal.isOpen = false;
      processNextHandler();
    });
  }

  ngOnInit() { }

  onIonInfinite(ev) {
    this.pageSize = 5;
    if (this.totalPages !== this.obj.pageNum) {
      this.obj.pageNum = this.obj.pageNum + 1;

      this.getFeedbackDetails();
    }
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
  }

  ionViewWillEnter() {
    this.resetPaginationData();
    this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getFeedbackDetails();
    this.feedbackCardCount();
  }

  getCityList() {
    this.mdmService.getCityDataTableForMobile().subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          this.getAllCityList = res.data;
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  resetPaginationData() {
    this.pageSize = 5;
    this.pageNum = 1;
    this.obj.name = "",
      this.obj.bookingId = "",
      this.feedbackList = [];
    this.isFirstApiHit = true;
  }

  getGhName(event) {
    this.obj.gId = event.target.value
    this.resetPaginationData();
    if (event.target.value) {
      this.getFeedbackDetails();
      this.feedbackCardCount();
    }
  }
  async getGuestHouseList(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.selctGH = "";
    this.obj.cId = event.target.value
    console.log("city name is", event.target.value);
    this.mdmService
      .getCityWiseGUestHouse(event.target.value)
      .subscribe((res) => {
        console.log("result is", res);
        this.guestHouseList = res.data;
        this.resetPaginationData();
        loading.dismiss()
        if (this.guestHouseList.length == 1) {
          console.log("Inside IF Condition");
          console.log(res.data);
          this.selctGH = res.data[0].id;
          loading.dismiss()
        } else {
          this.getFeedbackDetails();
          this.feedbackCardCount();
        }
      });
  }
  async getFeedbackDetails() {
    const obj = {
      cId: this.obj.cId,
      gId: this.obj.gId,
      reqType: "AL",
      name: this.obj.name,
      bookingId: this.obj.bookingId,
      pageSize: this.obj.pageSize,
      pageNum: this.obj.pageNum,
    };
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    this.mdmService.getAdminFeedbackDetails(obj).subscribe(
      (res) => {
        this.feedbackList = this.feedbackList.concat(res.data);
        if (this.isFirstApiHit) {
          loading.dismiss();
          this.isFirstApiHit = false;
        }
        // this.feedbackList = res.data;
        console.log(this.feedbackList);
      },
      (err) => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
      },
      () => {
        loading.dismiss()
      }
    );
  }
  feedbackCardCount() {
    const obj = {
      cId: this.selctCity,
      gId: this.selctGH,
    };
    this.mdmService.getFeedBackCount(obj).subscribe((res) => {
      this.count = res.data;
    });
  }
  getViewBtn(bookingid, feedbackid, name) {
    this.router.navigate(
      ["after-login/pages/orgadmin/GH-menu/feedback/reply-feedback"],
      {
        queryParams: {
          feedback_Id: feedbackid,
          booking_Id: bookingid,
          name: name,
        },
      }
    );
    // this.isViewClicked=true;
  }


  public async checkCityAndGh() {
    if (this.selctCity == '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    if (this.selctGH == '') {
      const alert = await this.alertController.create({
        message: 'Please Select Guest house.',
        buttons: ['OK'],
      });
      await alert.present();
      return Promise.resolve(false);
    }
    if (this.selctCity != '' && this.selctGH != '') {
      return Promise.resolve(true)
    }
  }

  async getButtonName(btn) {
    console.log("button is", btn);
    if (btn == "Feedback Report") {
      this.router.navigate(
        ["after-login/pages/orgadmin/GH-menu/feedback/feedback-report"],
        { queryParams: { cityId: this.selctCity, ghid: this.selctGH } }
      );
    }
    if (btn == "Guest Feedback Report") {
      const status = await this.checkCityAndGh();
      if (status) {
        this.router.navigate(
          ["after-login/pages/orgadmin/GH-menu/feedback/guest-feedback-report"],
          { queryParams: { cityId: this.selctCity, ghid: this.selctGH } }
        );
      }

      // if (this.selctCity === "") {
      //   this.commonservice.showErrorToast("Select City and Guest House");
      // } else if (this.selctGH === "") {
      //   this.commonservice.showErrorToast("Select City and Guest House");
      // } else {
      //   this.router.navigate(
      //     ["after-login/pages/orgadmin/GH-menu/feedback/guest-feedback-report"],
      //     { queryParams: { cityId: this.selctCity, ghid: this.selctGH } }
      //   );
      // }
    }
    if (btn == "Total Feedback Report") {
      const loading = await this.loadingController.create({
        message: "Please wait...",
      });
      await loading.present();

      this.router.navigate(
        ["after-login/pages/orgadmin/GH-menu/feedback/total-feedback-report"],
        { queryParams: { cityId: this.selctCity, ghid: this.selctGH } }
      );
      this.router.events.subscribe((event) => {
        if (event instanceof NavigationEnd) {
          // Navigation has finished, dismiss the loading spinner.
          loading.dismiss();
        }
      });
    }
    if (btn == "Comments Report") {
      this.router.navigate(
        ["after-login/pages/orgadmin/GH-menu/feedback/comments-report"],
        { queryParams: { cityId: this.selctCity, ghid: this.selctGH } }
      );
    }
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  // handleRefresh(event) {
  //   setTimeout(() => {
  //     this.resetPaginationData();
  //     this.getFeedbackDetails();
  //     this.feedbackCardCount();
  //     event.target.complete();
  //   }, 2000);
  // }
  //   async ionViewWillEnter() {
  //     this.getFeedbackDetails();
  // }

  public openFilterModal() {
    this.createFormInstance();
    this.filtermodal.isOpen = true;
  }
  handleRefresh(event) {
    this.resetPaginationData();
    setTimeout(() => {

      this.obj.bookingId = "",
        this.obj.cId = this.selctCity,

        this.obj.gId = this.selctGH,

        this.obj.name = "",

        this.obj.pageNum = 1;
      this.obj.pageSize = 5;
      this.feedbackList = []
      this.getFeedbackDetails();
      this.isFirstApiHit = true
      event.target.complete();
    }, 2000);
  }

  public createFormInstance() {
    this.filterForm = this.fb.group({
      bookingId: [""],
      cId: [""],
      gId: [""],

      name: [""],

      reqType: ["AL"],
      pageSize: this.pageSize,
      pageNum: this.pageNum,
    });
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public async submitFilterForm(data) {
    console.log(data);
    if (
      data.bookingId === "" &&
      data.cId === "" &&
      data.gId === "" &&
      data.name === ""
    ) {
      this.commonservice.showErrorToast("Provide any field for filter");
      return;
    }
    this.bookingId = data.bookingId;

    this.obj = {
      cId: this.selctCity,
      gId: this.selctGH,
      reqType: "AL",
      name: data.name,
      bookingId: this.bookingId,
      pageSize: this.pageSize,
      pageNum: this.pageNum,
    };
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.getAdminFeedbackDetails(this.obj).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          loading.dismiss();
          this.feedbackList = res.data;
          console.log(this.feedbackList);
          this.filtermodal.isOpen = false;
        } else {
          loading.dismiss();
        }
      },
      (error) => {
        loading.dismiss();
      },
      () => {
        loading.dismiss()
      }
    );
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }
}
