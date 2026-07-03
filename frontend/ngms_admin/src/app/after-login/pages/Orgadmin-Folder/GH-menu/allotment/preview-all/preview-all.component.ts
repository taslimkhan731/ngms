import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';
import { ModalController, NavParams } from '@ionic/angular';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-preview-all',
  templateUrl: './preview-all.component.html',
  styleUrls: ['./preview-all.component.scss'],
})
export class PreviewAllComponent implements OnInit {

  public allPreviewData;
  previewProcessList = []
  previewCancelledList = []
  previewConferenceList = []
  previewModifiedList = []
  previewConveyanceList = []
  public content: string;

  constructor(
    private navParams:NavParams,
    private commonService:CommonService,
    private modalCtrl:ModalController,
    private pdfGenerator: PDFGenerator,
    private location:Location
  ) { }

  ngOnInit() {}

  ionViewWillEnter(){
    this.allPreviewData = this.navParams.data.allPreviewData;
    console.log("allPreviewData",this.allPreviewData)
  }

  public splitName(name) {
   return name.split(',')[0]
  }

  public goBack(){
    this.modalCtrl.dismiss()
  }
  public printPreview(){
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
