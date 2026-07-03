import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IonModal, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-feedback-report',
  templateUrl: './feedback-report.component.html',
  styleUrls: ['./feedback-report.component.scss'],
})
export class FeedbackReportComponent implements OnInit {
  monthMapping = {
    'January': '01',
    'February': '02',
    'March': '03',
    'April': '04',
    'May': '05',
    'June': '06',
    'July': '07',
    'August': '08',
    'September': '09',
    'October': '10',
    'November': '11',
    'December': '12'
  };
  monthsArray: string[] = Object.keys(this.monthMapping);

  @ViewChild("filtermodal") filtermodal: IonModal;
  yearsArray: any[] = [];
  selectedYear: any

  selectedOption: any = '';
  filterForm: FormGroup;
  selctGH = ''
  selctCity = ''
  guestHouseList: any;
  city_Id: any;
  gh_Id: any;
  feedbackReport: any;
  feedbackReportOverall: any;
  feedbackReportClean: any;
  feedbackReportConductDtaff: any;
  feedbackReportEaseCinCout: any;
  feedbackReportQualityFood: any;
  feedbackReportKeepUpRoom: any;

  obj = {

    cId: "",
    gId: "",
    fromDate: "",
    toDate: "",
    year: "",
    month: ""

  }
  getAllCityList = [];
  constructor(private mdmService: MdmService,
    private route: ActivatedRoute,
    private loadingController: LoadingController,
    private fb: FormBuilder,
    private commonservice: CommonService,
  ) { }

  ngOnInit() {
  }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
    this.getFeedbackReport();
  }

  getCityList() {
    this.mdmService.getCityDataTableForMobile().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.getAllCityList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  getGuestHouseList(event) {
    console.log("city name is", event.target.value);
    this.selctGH = ''
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      console.log("result is", res);
      this.guestHouseList = res.data;
      if (this.guestHouseList.length == 1) {
        console.log("Inside IF Condition")
        console.log(res.data)
        this.selctGH = res.data[0].id;
      } else {
        this.getFeedbackReport();
      }
    })
  }
  getghName(event) {
    console.log("city gh", this)
    if (event.target.value) {
      this.getFeedbackReport();
    }
  }
  async getFeedbackReport() {
    const obj = {
      cId: this.selctCity,
      gId: this.selctGH,
      fromDate: '',
      toDate: '',
      year: '',
      month: ''
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    // this.mdmService.getFeedbackReportDetails(obj).subscribe(res => {
    //   loading.dismiss();
    //   this.feedbackReportOverall = res.data.overallFeedback
    //   this.feedbackReportClean = res.data.cleanAndHygine
    //   this.feedbackReportConductDtaff = res.data.conductStaff
    //   this.feedbackReportEaseCinCout = res.data.easeofCinCout
    //   this.feedbackReportQualityFood = res.data.qualityFood
    //   this.feedbackReportKeepUpRoom = res.data.upKeepOfRoom
    // })
    this.mdmService.guestTotalFeedbackDetails(obj).subscribe(
      res => {
        if (res.status == 1) {
          loading.dismiss();
          this.feedbackReport = res.data;
          console.log("total feedback", this.feedbackReport)
        }
        else {
          console.log("data not fetched");
        }

      },err=> {
        loading.dismiss()
      },
      ()=>{
        loading.dismiss()
      })
  }

  public changeDateFormat(date) {
    const dateComponents = date.split("-");
    const year = dateComponents[0];
    const month = dateComponents[1];
    const day = dateComponents[2];
    const formattedDate = `${day}-${month}-${year}`;
    return formattedDate;

  }
  public openFilterModal() {
    this.createFormInstance();
    this.filtermodal.isOpen = true;
    this.generateYearObject();
  }

  generateYearObject() {
    const currentYear = new Date().getFullYear();
    for (let year = currentYear; year >= 1950; year--) {
      this.yearsArray.push(year.toString());
    }

  }

  onOptionChange() {

  }

  public createFormInstance() {
    this.filterForm = this.fb.group({
      fromDate: [""],
      toDate: [""],
      year: [""],
      month: [""],
    });
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public async submitFilterForm(data) {
    if ((data.fromDate === '' || data.toDate === '') && (data.year === '' || data.month === '')) {
      this.commonservice.showErrorToast('Provide Both field for filter');
      return;
    }

    console.log(data);
    if (
      data.fromDate === '' &&
      data.toDate === '' &&
      data.month === '' &&
      data.year === ''
    ) {
      console.log("Provide any field for filter")
      this.commonservice.showErrorToast("Provide any field for filter");
      return;
    }
    if (data.fromDate || data.toDate) {
      data.fromDate = this.changeDateFormat(data.fromDate);
      data.toDate = this.changeDateFormat(data.toDate);
      console.log(data.fromDate, data.toDate)
    }


    this.obj = {

      cId: this.selctCity,
      gId: this.selctGH,
      toDate: data.toDate,
      fromDate: data.fromDate,
      year: data.year,
      month: data.month
    }

    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.guestTotalFeedbackDetails(this.obj).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          loading.dismiss();
          this.feedbackReport = res.data
          this.filtermodal.isOpen = false;
        } else {
          loading.dismiss()
        }
      },
      (error) => {
        loading.dismiss();
      },
      ()=>{
        loading.dismiss()
      }
    );
  }
  filterHandleRefresh(event) {
    setTimeout(() => {
      this.obj.toDate = "",
        this.obj.fromDate = "",
        this.obj.cId = "",
        this.obj.gId = "",
        this.obj.year = "",
        this.obj.month = "",
        this.feedbackReport = []
      this.getFeedbackReport()
      event.target.complete();
    }, 2000);
  }

}
