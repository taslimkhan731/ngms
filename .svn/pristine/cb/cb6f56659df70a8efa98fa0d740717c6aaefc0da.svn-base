import { Component, Input, OnInit } from '@angular/core';
import { AlertController, LoadingController, PopoverController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss'],
})
export class ConfirmationComponent implements OnInit {

  @Input() data: any;
  public processDetails;
  constructor(
    private popover: PopoverController,
    private alertController: AlertController,
    private loading:LoadingController,
    private mdmService: MdmService,
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    console.log(this.data)
    this.getDetailsByprocessId(this.data.processId);
  }

  public async getDetailsByprocessId(id) {
    const loader = await this.loading.create({
      message:'Please wait...'
    })
    loader.present();
    this.mdmService.getDetailsByprocessId(id).subscribe(res => {
      if (res.status === 1) {
        this.processDetails = res.data;
        loader.dismiss();
      } else {
        loader.dismiss();
      }
    }, error => {
      console.log(error);
      loader.dismiss();
    },
    ()=> {
      loader.dismiss()
    })
  }

  public async onClickApproveBtn(){
    const loader = await this.loading.create({
      message:'Please wait...'
    })
    loader.present();
    this.mdmService.confirmProcess(this.data.processId).subscribe(async res => {
      console.log(res);
      if (res.status === 1) {
        this.popover.dismiss()
        const alert = await this.alertController.create({
          message: res.statusDesc,
          buttons: ['OK'],
        });
        await alert.present();
        loader.dismiss();
        // this.getApproveList();
        return Promise.resolve(false);
      } else {
        loader.dismiss()
      }
    }, err => {
      loader.dismiss();
    },
    ()=> {
      loader.dismiss();
    })
  }

  public dismiss() {
    this.popover.dismiss()
  }

}
