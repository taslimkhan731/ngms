import { Component, OnInit } from "@angular/core";
import { LoadingController, ModalController } from "@ionic/angular";
import { MdmService } from "src/app/Services/MDM/mdm.service";
import { CommonService } from "src/app/Services/common/common.service";
import { FeedbackDetailsComponent } from "./feedback-details/feedback-details.component";

@Component({
  selector: "app-feedback",
  templateUrl: "./feedback.component.html",
  styleUrls: ["./feedback.component.scss"],
})
export class FeedbackComponent implements OnInit {
  FeedbackList = [];
  // FeedbackListt=[
  //   {'feedback_id':'25452','bookingId':'5646','name':'Gfdsd','fromdt':'12-09-2022','todt':'12-09-1208'},
  //   {'feedback_id':'25452','bookingId':'5646','name':'Gfdsd','fromdt':'12-09-2022','todt':'12-09-1208'},
  //   {'feedback_id':'25452','bookingId':'5646','name':'Gfdsd','fromdt':'12-09-2022','todt':'12-09-1208'},
  // ]
  constructor(
    private mdmService: MdmService,
    private commonService: CommonService,
    private loadingController: LoadingController,
    private modalcontroller: ModalController
  ) {}

  ngOnInit() {
    this.feedbackListDetails();
  }
  async feedbackListDetails() {
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.getFeedbackList().subscribe((res) => {
      if (res.status == 1) {
        loading.dismiss();
        this.FeedbackList = res.data;
      } else {
        this.commonService.showErrorToast(res.statusDesc);
      }
    });
  }

  public async viewDetails(data) {
    const modal = await this.modalcontroller.create({
      component: FeedbackDetailsComponent,
      componentProps: { feedbackData: data },
    });

    modal.onDidDismiss().then((data) => {
      this.feedbackListDetails();
    });
    return await modal.present();
  }
}
