import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IonModal, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-comment-feedback-report',
  templateUrl: './comment-feedback-report.component.html',
  styleUrls: ['./comment-feedback-report.component.scss'],
})
export class CommentFeedbackReportComponent implements OnInit {

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
  obj = {
    
    cId: "",
    gId: "",
    fromDate:"",
    toDate:"",
    year:"",
    month:""
    
  }

  city_Id='';
  gh_Id='';
  commentFeedback: any;

  constructor(private route: ActivatedRoute,
    private mdmService: MdmService,
    private router: Router,
    private loadingController: LoadingController,
    private fb: FormBuilder,
    private commonservice: CommonService) { }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.city_Id = params['cityId'];
      this.gh_Id = params['ghid'];
      console.log("citygh", this.city_Id, this.gh_Id);
    });
    this.getCommentFeedback();
  }
  async getCommentFeedback() {
    const obj = {
      cId: this.city_Id==undefined?'':this.city_Id,
      gId: this.gh_Id==undefined?'':this.gh_Id,
      fromDate: '',
      toDate: '',
      year: '',
      month: ''
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
      
    });
    loading.present();
    this.mdmService.guestFeedbackCommentDetails(obj).subscribe(res => {
      loading.dismiss();
      this.commentFeedback = res.data;
    },
    err=> {
      loading.dismiss()
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

      cId: this.city_Id,
      gId: this.gh_Id,
      toDate:data.toDate,
      fromDate:data.fromDate,
      year:data.year,
      month:data.month
    }
    
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.guestFeedbackCommentDetails(this.obj).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          loading.dismiss();
          this.commentFeedback=res.data;
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
        this.obj.fromDate="",
        this.obj.toDate="",
        this.obj.month="",
        this.obj.year="",
        this.obj.cId = "" ,  
        this.obj.gId = "",
      this.commentFeedback = []
      this.getCommentFeedback()
      event.target.complete();
    }, 2000);
  }
 

}
