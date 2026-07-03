import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-moderator-cancel',
  templateUrl: './moderator-cancel.component.html',
  styleUrls: ['./moderator-cancel.component.scss'],
})
export class ModeratorCancelComponent implements OnInit {

  public form: FormGroup;
  public modCanData;
  public dataComingFrom;;

  constructor(
    private fb: FormBuilder,
    private modalCtrl: ModalController,
    private navParams: NavParams,
    private commonService: CommonService,
    private mdmService:MdmService,
    private loadingCtrl:LoadingController,
    private alertController:AlertController
  ) {
    this.createFormInstance()
  }

  createFormInstance() {
    this.form = this.fb.group({
      modReason: [''],
      remarks: [''],
    })
  }

  ngOnInit() {
    this.modCanData = this.navParams.data.modData;
    this.dataComingFrom = this.navParams.data.dataComingFrom;
    console.log(this.dataComingFrom)
  }

  public async submit(data) {
    console.log(data)
    if (this.form.get('modReason').value == '' || this.form.get('modReason').value == null) {
      this.commonService.showErrorToast("Please Select the reason for cancellation.")
      return;
    }
    if (this.form.get('modReason').value == 'others' && (this.form.get('remarks').value == '' || this.form.get('remarks').value == null) ) {
      this.commonService.showErrorToast("Please provide remark.")
      return;
    }
    let postData = {
      bookingId:this.modCanData.bookingId,
      cityId:this.modCanData.cityId,
      ghId:this.modCanData.ghId,
      modReason:this.form.get('modReason').value,
      remarks:this.form.get('remarks').value
    }
    console.log(postData)

    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to Cancel?',
      buttons: [
        {
          text: 'Yes,Cancel it',
          cssClass: 'alertButton',
          handler: () => {
            this.cancel(postData)
          },
        },
        {
          text: 'No',
          handler: () => {
            console.log('Confirm No');
          },
        },

      ],
    });
    await alert.present();

  }

  public async cancel(postData) {
    const loader = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loader.present();
    this.mdmService.saveCancelModerationForMobile(postData).subscribe(
      res => {
        if(res.status == 1) {
          loader.dismiss()
          this.commonService.showSuccessToast(res.statusDesc)
          this.modalCtrl.dismiss();
        } else {
          loader.dismiss()
          this.commonService.showErrorToast(res.statusDesc)
          this.modalCtrl.dismiss();
        }
      },
      err=> {
        loader.dismiss()
      },
      ()=> {
        loader.dismiss()
      }
    )
  }

  public goBack() {
    this.modalCtrl.dismiss();
  }

}
