import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IonModal, LoadingController } from '@ionic/angular';
import { Chart, registerables } from 'chart.js'; Chart.register(...registerables);
import { MdmService } from 'src/app/Services/MDM/mdm.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {

  @ViewChild('categoryCanvas') private categoryCanvas: ElementRef;
  @ViewChild('purposeCanvas') private purposeCanvas: ElementRef;
  @ViewChild('bookingCanvas') private bookingCanvas: ElementRef;

  @ViewChild('popover') popover;
  @ViewChild('filtermodal') filtermodal: IonModal;
  filterForm: FormGroup;

  public categoryChart: any;
  public purposeChart: any;
  public bookingChart: any;

  public isOpen = false;
  public currentDate;
  public dashboardData;

  public getAllCityList = [];
  public guestHouseList = [];
  totalCategory: number;
  totalPurpose: any;
  selectGH='';

  constructor(
    private fb: FormBuilder,
    private mdmService: MdmService,
    public loadingController: LoadingController
  ) {
    this.createFormInstance();
  }

  public createFormInstance() {
    this.filterForm = this.fb.group({
      city: [''],
      gHouse: [''],
      date: ['']
    })
  }

  ngOnInit() {
    this.getCurrentDate();
  }

  ionViewWillEnter() {
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    this.submitFilterForm(this.filterForm.value);
    this.getCurrentDate();
    // this.getCityList();
  }

  public getCurrentDate() {
    const today = new Date();
    const day = today.getDate();
    const month = today.getMonth() + 1;
    const year = today.getFullYear();

    this.currentDate = `${day < 10 ? '0' : ''}${day}-${month < 10 ? '0' : ''}${month}-${year}`;
    this.filterForm.get('date').setValue(this.currentDate);
  }

  public getCityList() {
    this.mdmService.getCityDataTableForMobile().subscribe(res => {
      if (res.status === 1) {
        this.getAllCityList = res.data;
      }
    }, error => {
    })
  }

  public async getGuestHouseList(event) {
    this.selectGH=''
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      loading.dismiss()
      this.guestHouseList = res.data;
      if (this.guestHouseList.length == 1) {
        this.selectGH = res.data[0].id;
        this.filterForm.get('gHouse').setValue(this.selectGH);
      }
    }, err => {
      loading.dismiss()
    },
    ()=> {
      loading.dismiss()
    })
  }

  public categoryChartMethod() {

    if (this.dashboardData.c1Count === 0 && this.dashboardData.c2Count === 0 && this.dashboardData.c3Count === 0 && this.dashboardData.c4Count === 0) {
      this.totalCategory = 100;
      console.log("categoryChartMethod", this.totalCategory)
    }
    else {
      this.totalCategory = 0;
      console.log("categoryChartMethod", this.totalCategory)
    }
    this.categoryChart = new Chart(this.categoryCanvas?.nativeElement, {
      type: "doughnut",
      data: {
        labels: ['Category 1:', 'Category 2:', 'Category 3 :', 'Category 4:', 'Not Available'],
        datasets: [{
          label: '',
          data: [this.dashboardData?.c1Count, this.dashboardData?.c2Count, this.dashboardData?.c3Count, this.dashboardData?.c4Count, this.totalCategory],
          backgroundColor: [
            '#aff7f7',
            '#FFBBCA',
            '#EFEDC6',
            '#B4F8C8',
            'rgba(97, 97, 97, 1)'
          ]
        }]
      }
    });
    // this.categoryChart.destroy();
  }

  public purposeChartMethod() {
    if (this.dashboardData.p1Count === 0 && this.dashboardData.p2Count === 0 && this.dashboardData.p3Count === 0 && this.dashboardData.p4Count === 0) {
      this.totalPurpose = 100;
    }
    else {
      this.totalPurpose = 0;
    }
    this.purposeChart = new Chart(this.purposeCanvas?.nativeElement, {
      type: "doughnut",
      data: {
        labels: ['Official :', 'Private :', 'Patient:', 'Student:', 'Not Available'],
        datasets: [{
          label: '',
          data: [this.dashboardData?.p1Count, this.dashboardData?.p2Count, this.dashboardData?.p3Count, this.dashboardData?.p4Count, this?.totalPurpose],
          backgroundColor: [
            '#A58CB3',
            '#838BC2',
            '#EAE7FA',
            '#D9A1A0',
            'rgba(97, 97, 97, 1)'
          ]
        }]
      }
      
    });
    // this.purposeChart.destroy();
  }

  public bookingChartMethod() {
    this.bookingChart = new Chart(this.bookingCanvas.nativeElement, {
      type: 'bar',
      data: {
        labels: [this.dashboardData?.bar[0].days, this.dashboardData?.bar[1].days, this.dashboardData?.bar[2].days, this.dashboardData?.bar[3].days, this.dashboardData?.bar[4].days],
        datasets: [{
          // label: '# of Votes',
          data: [this.dashboardData?.bar[0].count, this.dashboardData?.bar[1].count, this.dashboardData?.bar[2].count, this.dashboardData?.bar[3].count, this.dashboardData?.bar[4].count],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        plugins: {
          legend: {
            display: false // Hide the legend (colored bar)
          }
        },
        scales: {
          y: {
            display: false // Hide the y-axis labels
          }
        }
      }
    });
    // this.purposeChart.destroy();
  }

  public presentPopover(e: Event) {
    this.popover.event = e;
    this.isOpen = true;
  }

  public cancel() {
    this.createFormInstance();
    this.filtermodal.dismiss('cancel');
  }

  public async submitFilterForm(data) {
    console.log("totalpurposeeee", this.totalPurpose)
    this.purposeChart?.destroy();
    this.bookingChart?.destroy();
    this.categoryChart?.destroy();
    if (data.city === "") {
      data.city = "ALL"
    }
    if (data.gHouse === "") {
      data.gHouse = "ALL"
    }
    if (data.date !== "") {
      if (this.checkDateFormat(data.date)) {
        data.date = this.changeDateFormat(data.date);
      }
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAdminDashboradCount(data).subscribe(res => {
      if (res.status === 1) {
        this.dashboardData = res.data;
        console.log(this.dashboardData);
        console.log('current time', this.currentDate);
        loading.dismiss();
        if (data.date) {
          this.currentDate = data.date;
        } else {
          this.getCurrentDate();
        }
        console.log('current', this.currentDate);
        this.filtermodal.isOpen = false;
        if (this.dashboardData) {
          this.categoryChartMethod();
          this.purposeChartMethod();
          this.bookingChartMethod();
        }
      } else {
        loading.dismiss()
      }
    }, error => {
      loading.dismiss();
    },
    ()=> {
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

  public refresh() {
    this.getCurrentDate();
    const data = {
      "city": "",
      "gHouse": "",
      "date": this.currentDate
    }
    this.submitFilterForm(data);
  }

  public checkDateFormat(dateString) {
    const dateFormats = [
      /^\d{4}-\d{2}-\d{2}$/,   // YYYY-MM-DD
      // /^\d{2}\/\d{2}\/\d{4}$/, // MM/DD/YYYY
      // /^\d{2}-\d{2}-\d{4}$/,   // DD-MM-YYYY
    ];
    for (const format of dateFormats) {
      if (format.test(dateString)) {
        return true;
      }
    }
    return false;
  }

  public openFilterModal() {
    this.createFormInstance();
    this.filtermodal.isOpen = true;
  }


}
