import { Component, OnInit } from '@angular/core';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-cancel-allotment',
  templateUrl: './cancel-allotment.component.html',
  styleUrls: ['./cancel-allotment.component.scss'],
})
export class CancelAllotmentComponent implements OnInit {
  public params;
  public allotedRoomData = []
  public selectedRoom = []
  constructor(
    private navParams:NavParams,
    private loadingController: LoadingController,
    public modalcontroller: ModalController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private alertController: AlertController
  ) { }

  ngOnInit() {}

  ionViewWillEnter(){
    this.params = this.navParams.data.obj;
    console.log(this.params)
    this.viewAllotmentRoomByReqId()
  }

  public async viewAllotmentRoomByReqId() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.viewAllotmentRoomByReqIdForMobile(this.params).subscribe(
      res => {
        this.allotedRoomData = res.data;
        loading.dismiss();
        console.log("allotedRoomData", this.allotedRoomData);
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      ()=>{
        loading.dismiss()
      })
  }

  public onSelect(event) {
    console.log(event.target.value)
    if (event.target.checked) {
      this.selectedRoom.push(event.target.value);
    }

    if (!event.target.checked) {
      this.selectedRoom.forEach((ele, ind) => {
        console.log(ele)
        if (ele == event.target.value) {
          this.selectedRoom.splice(ind, 1);
        }
      })
    }

    console.log(this.selectedRoom.map(x=> x).join("','"))
  }

  public async cancelAllotRoom(){
    if (this.selectedRoom.length <= 0) {
      this.commonService.showErrorToast("Please select atleast one room")
      return;
    }
    let data = {
      "selectCheckAllotRoom":this.selectedRoom.map(x=> x).join("','")
    }
    console.log(data)
     
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.cancelAllotRoomPerReqIdForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          console.log(res.data)
          loading.dismiss();
          this.commonService.showSuccessToast(res.statusDesc)
        } else if (res.status == 0) {
          loading.dismiss()
          this.commonService.showErrorToast(res.statusDesc)
        }
      },
      err => {
        loading.dismiss()
        this.commonService.showErrorToast("Something Went Wrong!!")
      },
      () => {
        this.selectedRoom = []
        loading.dismiss()
      }
    )



  }




}
