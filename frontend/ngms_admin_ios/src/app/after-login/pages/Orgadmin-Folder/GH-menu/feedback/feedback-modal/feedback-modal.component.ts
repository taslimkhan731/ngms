import { Component, OnInit } from '@angular/core';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { combineAll } from 'rxjs/operators';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-feedback-modal',
  templateUrl: './feedback-modal.component.html',
  styleUrls: ['./feedback-modal.component.scss'],
})
export class FeedbackModalComponent {

  name: string;
  // preDefinedText: string = "We are happy that you had a pleasant experience at Odisha Niwas. We are committed to provide quality services to our Guests. We are encouraged by your feedback and will strive harder to provide even better services in future.";
  showTextarea: boolean = false;
  overAll: any;
  requestId: any;
  mobile: any;
  repliedSms: any;
  repliedCustomSms: string = '';
  templateId: any;
  blockName: any;
  feedbackId: any;
  maximumLength: number = 100;
  characterCount: number = 0;

  constructor(private modalCtrl: ModalController,
    private navParams: NavParams,
    private commonService:CommonService,
    private mdmService: MdmService,
    private loadingController: LoadingController
  ) { }

  // customCounterFormatter(inputLength: number, maxLength: number) {
  //   return `${maxLength - inputLength} characters remaining`;
  // }

  countCharacters(event: any) {
    this.characterCount = event.target.value.length
  }

  cancel() {
    return this.modalCtrl.dismiss(null, 'cancel');
  }

  confirm() {
    return this.modalCtrl.dismiss(this.name, 'confirm');
  }
  ionViewWillEnter() {

    this.overAll = this.navParams.data.overAll;
    this.requestId = this.navParams.data.requestId;
    this.mobile = this.navParams.data.mobile;
    this.blockName = this.navParams.data.blockName;
    this.feedbackId = this.navParams.data.feedbackId;

    if (this.overAll == 5 || this.overAll == 4) {
      this.repliedSms = 'We are happy that you had a pleasant experience at ' + this.blockName + '. We are committed to provide quality services to our Guests. We are encouraged by your feedback and will strive harder to provide even better services in future.';
      this.templateId = '1107164967721333473';
    } else if (this.overAll == 3) {
      this.repliedSms = 'We are disappointed that you did not have a very pleasant experience at ' + this.blockName + '. We are committed to provide quality services to our Guests. Your feedback is being reviewed and we shall undertake necessary corrective actions to prevent any recurrence.';
      this.templateId = '1107164967740329102';
    } else {
      this.repliedSms = 'Please accept our sincere apologies for an unsatisfactory experience during your recent visit to ' + this.blockName + '. We are committed to provide the best service to our guests and shall make necessary improvements to prevent any recurrence henceforth.';
      this.templateId = '1107164967750152268';
    }
  }

  async onSubmit() {
    const obj = {
      mobile: this.mobile,
      feedbackId: this.feedbackId,
      requestId: this.requestId,
      repliedSms: this.repliedSms,
      repliedCustomSms: this.repliedCustomSms,
      templateId: this.templateId
    }


    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.saveFeedbackRepliedDetails(obj).subscribe(res => {
      if(res.status == 1) {
        loading.dismiss();
        this.commonService.showSuccessToast(res.statusDesc)
        this.modalCtrl.dismiss(1);
      } else {
        loading.dismiss();
        this.commonService.showSuccessToast(res.statusDesc)
      }
    },
      (err => {
        loading.dismiss();
      })
    )

  }

  public onChange(event) {
    if(event.target.checked) {
      this.repliedCustomSms = ''
    }
  }
}


