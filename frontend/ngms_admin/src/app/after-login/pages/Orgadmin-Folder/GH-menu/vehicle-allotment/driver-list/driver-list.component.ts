import { Component, OnInit } from '@angular/core';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styleUrls: ['./driver-list.component.scss'],
})
export class DriverListComponent implements OnInit {

  public cityAndGhId;
  public driverList;

  constructor(
    private navparam: NavParams,
    private modal: ModalController,
    private loadingCtrl: LoadingController,
    private mdmService: MdmService,
    private commonService: CommonService,
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.cityAndGhId = this.navparam.data.cityAndGh;
    this.getDriverList()
  }

  public async getDriverList() {
    let data = {
      "cId": this.cityAndGhId.cityId,
      "gId": this.cityAndGhId.ghId
    }
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getDriverDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          this.driverList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss()
        }
        // console.log("Check In Dta", this.checkInSlipdata);
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      ()=> {
        loading.dismiss()
      })
  }

  public async view(data){}
  public async addDriver(data){
    this.modal.dismiss(data)
  }

  public goBack(){
    this.modal.dismiss()
  }
}
