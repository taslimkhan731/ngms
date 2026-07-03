import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IonModal, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-total-feedback-report',
  templateUrl: './total-feedback-report.component.html',
  styleUrls: ['./total-feedback-report.component.scss'],
})
export class TotalFeedbackReportComponent implements OnInit {
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
  selectedYear:any
 
  selectedOption:any='';
  filterForm: FormGroup;

  totalFeedback: any;
  public cId;
  public gId;
  obj = {
    
    cId: "",
    gId: "",
    fromDate:"",
    toDate:"",
    year:"",
    month:""
    
  }
 
  constructor(private mdmService: MdmService,
    private route:ActivatedRoute,
    private loadingController: LoadingController,
    private fb: FormBuilder,
    private commonservice: CommonService) { }

  ngOnInit() {
  }

  ionViewWillEnter(){
    this.route.queryParams.subscribe((params) => {
      this.cId = params['cityId']
      this.gId = params['ghid']
    })
    this.getTotalFeedback()
  }
  async getTotalFeedback() {
    // console.log("total feedback",this.totalFeedback)
    const obj = {
      cId: this.cId,
      gId: this.gId,
      fromDate: '',
      toDate: '',
      year: '',
      month: ''
    }
    const loading = await this.loadingController.create({
      message: 'Loading...'
    });
    loading.present();
    this.mdmService.guestTotalFeedbackDetails(obj).subscribe(res => {
      loading.dismiss();
      this.totalFeedback = res.data;
      console.log("total feedback",this.totalFeedback)
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

  onOptionChange(){

  }

  public createFormInstance() {
    this.filterForm = this.fb.group({
      fromDate: [""],
      toDate: [""],
      year:[""],
      month:[""],
    });
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public async submitFilterForm(data) {
    if ((data.fromDate === '' || data.toDate === '') && (data.year === '' ||  data.month === '')) {
      this.commonservice.showErrorToast('Provide Both field for filter');
      return;
    }
    console.log(data);
    if (
      data.fromDate === "" &&
      data.toDate === "" &&
      data.year === "" &&
      data.month === ""
    ) {
      this.commonservice.showErrorToast("Provide any field for filter");
      return;
    }
    if (data.fromDate || data.toDate) {
      data.fromDate = this.changeDateFormat(data.fromDate);
      data.toDate = this.changeDateFormat(data.toDate);
      console.log(data.fromDate,data.toDate)
    }

   
    this.obj={

      cId: this.cId,
      gId: this.gId,
      toDate:data.toDate,
      fromDate:data.fromDate,
      year:data.year,
      month:data.month
    }
    
    const loading = await this.loadingController.create({
      message: "Loading...",
    });
    loading.present();
    this.mdmService.guestTotalFeedbackDetails(this.obj).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          loading.dismiss();
          this.totalFeedback=res.data;
          this.filtermodal.isOpen = false;
        }
      },
      (error) => {
        loading.dismiss();
      }
    );
  }
  filterHandleRefresh(event) {
    setTimeout(() => {
        this.obj.fromDate="",
        this.obj.toDate="",
        this.obj.month="",
        this.obj.year="",
        this.obj.cId = "" ,  
        this.obj.gId = "",
      this.totalFeedback = []
      this.getTotalFeedback()
      event.target.complete();
    }, 2000);
  }
 

}
