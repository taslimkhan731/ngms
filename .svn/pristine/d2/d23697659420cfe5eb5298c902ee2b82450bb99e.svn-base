import { Component, OnInit } from '@angular/core';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-feedback-details',
  templateUrl: './feedback-details.component.html',
  styleUrls: ['./feedback-details.component.scss'],
})
export class FeedbackDetailsComponent implements OnInit {

  public feedbackData;
  public responseData = [];
  public feedback = [
    {id:'1',img:'../../../assets/afterlogin/angry.png',desc:'Very Poor'},
    {id:'2',img:'../../../assets/afterlogin/sad (1).png',desc:'Poor'},
    {id:'3',img:'../../../assets/afterlogin/scared.png',desc:'Average'},
    {id:'4',img:'../../../assets/afterlogin/smile (1).png',desc:'Good'},
    {id:'5',img:'../../../assets/afterlogin/smile.png',desc:'Excellent'},
  ]
  constructor(
    private navParams:NavParams,
    private loadingController:LoadingController,
    private mdmService:MdmService,
    private commonService:CommonService,
    private modalController:ModalController
  ) { }

  ngOnInit() {}

  ionViewWillEnter(){
    this.feedbackData = this.navParams.data.feedbackData;
    console.log(this.feedbackData)
    this.getFeedbackRepliedDetailsForMobile();
  }

  public async getFeedbackRepliedDetailsForMobile(){
    let obj = {
      feedbackId:this.feedbackData.feedbackId,
      requestId:this.feedbackData.bookingId
    }

    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getFeedbackRepliedDetailsForMobile(obj).subscribe(
      res=> {
        if(res.status == 1) {
          loading.dismiss();
          this.responseData = res.data;
        } else {
          loading.dismiss()
          this.commonService.showErrorToast(res.statusDesc)
        }
      }, err=> {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },()=>{
        loading.dismiss();
      }
    )
  }

  public getImage(id) {
    const data = this.feedback.find((item) => item.id === id);
    if (data) {
      return data.img;
    }
    return ''; // Return a default value if data is not found
  }
  
  public getValue(id) {
    const data = this.feedback.find((item) => item.id === id);
    if (data) {
      return data.desc;
    }
    return ''; // Return a default value if data is not found
  }
  

  public goBack(){
    this.modalController.dismiss()
  }

}
