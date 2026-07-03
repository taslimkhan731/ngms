import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { FeedbackModalComponent } from '../feedback-modal/feedback-modal.component';

@Component({
  selector: 'app-reply-feedback',
  templateUrl: './reply-feedback.component.html',
  styleUrls: ['./reply-feedback.component.scss'],
})
export class ReplyFeedbackComponent implements OnInit {
  feedback_Id: any;
  booking_Id: any;
  replyDetails: any;
  questionArray = [
    { key: 'Ease of Check In and Check out', value: 'checkin_checkout' },
    { key: 'Up Keep of the Room', value: 'keepup_room' },
    { key: 'Cleanliness and Hygiene', value: 'cleanliness_hygiene' },
    { key: 'Quality Of Food', value: 'food_quality' },
    { key: 'Conduct of staff', value: 'staff_conduct' },
    { key: 'Overall Feedback', value: 'overall_feedback' }
  ]
  checkincheckout: any;
  houseKeeping: any;
  clean: any;
  foodQuality: any;
  conductStaff: any;
  feedback: any;
  getFeedbackId: any;
  requestId:any;
  mobile:any;
  overAll:any;
  blockName:any;
  selctCity = '';
  selctGH = '';
  public pageSize = 5;
  public pageNum = 1;
  public guestName;

  
  constructor(private mdmService: MdmService,
    private route: ActivatedRoute,
    private location:Location,
    private modalcontroller: ModalController,
    private loadingController: LoadingController) {

  }

  ngOnInit() {
    // this.route.queryParams.subscribe((params) => {
    //   this.feedback_Id = params['feedback_Id'];
    //   this.booking_Id = params['booking_Id'];
    //   console.log("citygh", this.feedback_Id, this.booking_Id);
    // });
    // this.replyFeedbackDetails();
  }
  async showFeedback() {
    const modal = await this.modalcontroller.create({
      component: FeedbackModalComponent,
      componentProps: {
        overAll:this.overAll,
        mobile:this.mobile,
        requestId:this.requestId,
        blockName:this.blockName,
        feedbackId:this.getFeedbackId,
    
      }
    });
    modal.onDidDismiss().then((data) => {
      if(data.data == 1) {
        this.location.back();
      }
    })
    return await modal.present();
  }


  async replyFeedbackDetails() {
    console.log("feedback booking", this.feedback_Id, this.booking_Id);
    const obj = {
      requestId: this.booking_Id,
      feedbackId: this.feedback_Id,
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getReplyFeedbackDetails(obj).subscribe(res => {
      this.replyDetails = res.data;
    
      // this.checkincheckout=this.replyDetails .checkincheckout
      this.checkincheckout = res.data[0].checkincheckout;
      this.houseKeeping = res.data[0].housekeeping;
      this.clean = res.data[0].clean;
      this.foodQuality = res.data[0].food;
      this.conductStaff = res.data[0].conduct;
      this.feedback = res.data[0].feedbacksms;
      this.getFeedbackId = res.data[0].feedbackId;
      this.mobile=res.data[0].mobile;
      this.requestId=res.data[0].requestId;
      this.overAll=res.data[0].overall;
      this.blockName=res.data[0].block_name;
      loading.dismiss();
      this.openReplyFeedback(this.getFeedbackId);
    })
    console.log("oooooooooooooooooo", this.getFeedbackId)

  }


  async openReplyFeedback(data: any) {
    this.mdmService.openReplyFeedback(data).subscribe(async res => {
      console.log(res,"BBBBBBBBBBBBBB")
      if (res) {
        const obj = {
          cId: this.selctCity,
          gId: this.selctGH
        }
        this.mdmService.getFeedBackCount(obj).subscribe(res => {
          // this.count = res.data
        })

        const obj2 = {
          cId: this.selctCity,
          gId: this.selctGH,
          reqType: 'AL',
          name: '',
          bookingId: '',
          pageSize: this.pageSize,
          pageNum: this.pageNum
        }
        const loading = await this.loadingController.create({
          message: 'Please wait...'
        });
        loading.present();
        this.mdmService.getAdminFeedbackDetails(obj2).subscribe(res => {
          loading.dismiss();
          // this.feedbackList = res.data;
         
        },
          (err => {
            loading.dismiss();
          })
        )
      }
    });
  }



  async ionViewWillEnter() {
    this.route.queryParams.subscribe((params) => {
      this.feedback_Id = params['feedback_Id'];
      this.booking_Id = params['booking_Id'];
      this.guestName = params['name'];
      console.log("citygh", this.feedback_Id, this.booking_Id,params);
    });
    this.replyFeedbackDetails();
  }



}
