import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ModalController, LoadingController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';

@Component({
  selector: 'app-view-checkinslip',
  templateUrl: './view-checkinslip.component.html',
  styleUrls: ['./view-checkinslip.component.scss'],
})
export class ViewCheckinslipComponent implements OnInit {
  public content: string;
  public slipDetails;
  public filterData;
  public guestDetails;
  public currDate = new Date();
  constructor(
    private modalCtrl: ModalController,
    private loadingCtrl: LoadingController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private navParams: NavParams,
    private datePipe: DatePipe,
    private pdfGenerator: PDFGenerator
  ) { }

  ngOnInit() {}

  ionViewWillEnter(){
    this.filterData = this.navParams.data.data;
    this.getIndividualCheckinSlipDetail();
  }

  public async getIndividualCheckinSlipDetail(){
 
    const loader = await this.loadingCtrl.create({
      message: "Please wait..."
    });
    loader.present();
    this.mdmService.viewIndividualCheckinSlipDetailsForMobile(this.filterData).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.slipDetails = res.data.data;
          this.guestDetails = this.slipDetails.name;
          if(this.slipDetails.department != '' && this.slipDetails.department != null) {
            this.guestDetails = this.guestDetails + ',' + this.slipDetails.department;
          }
          if(this.slipDetails.designation != '' && this.slipDetails.designation != null) {
            this.guestDetails = this.guestDetails + ',' + this.slipDetails.designation;
          }
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!")
      },
      ()=>{
        loader.dismiss()
      }
    )
  }

  public printCheckInSlip(reqId){
    this.content = document.getElementById('printCINSlip').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'CheckInSlip_' + reqId +'.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }

  public goBack(){
    this.modalCtrl.dismiss();
  }

}
