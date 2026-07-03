import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';
import { NavParams, ModalController, LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-office-order',
  templateUrl: './office-order.component.html',
  styleUrls: ['./office-order.component.scss'],
})
export class OfficeOrderComponent implements OnInit {
  public processId;
  public type;
  public allPreviewData;
  public content: string;

  constructor(
    private navParams: NavParams,
    private modalCtrl: ModalController,
    private loadingController: LoadingController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private pdfGenerator: PDFGenerator) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.processId = this.navParams.data.processId;
    this.type = this.navParams.data.type;
    this.getAllDetailsByProcessid();
  }

  public splitName(name) {
    return name.split(',')[0]
  }

  public goBack() {
    this.modalCtrl.dismiss()
  }
  publi

  public async getAllDetailsByProcessid() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getAllDetailsByProcessidforOfficeOrderForMobile(this.processId, this.type).subscribe(res => {
      if (res.status == 1) {
        this.allPreviewData = res.data;
        console.log(this.allPreviewData)
      }
      loading.dismiss();
    }, err => {
      this.commonService.showSuccessToast("Something Went Wrong.")
      loading.dismiss();
    }, () => {
      loading.dismiss();

    })
  }

  public printPreview() {
    this.content = document.getElementById('printPreview').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'Download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }
}
