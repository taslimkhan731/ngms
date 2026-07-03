import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { BillDetailsComponent } from '../bill-details/bill-details.component';

@Component({
  selector: 'app-create-bill',
  templateUrl: './create-bill.component.html',
  styleUrls: ['./create-bill.component.scss'],
})
export class CreateBillComponent implements OnInit {

  public bookingId;
  public billDetails;
  public advAmount = 0;
  public roomRent;
  public foodCharge;
  public newspaper = 0;
  public vehicle = 0;
  public telephone = 0;
  public additional = 0;
  public noc = 0;
  public cityId;
  public ghId;

  constructor(
    private location: Location,
    private navParams: NavParams,
    private mdmService: MdmService,
    private commonService: CommonService,
    private loadingController: LoadingController,
    private modalController: ModalController,
    private route: ActivatedRoute
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    // this.route?.queryParams.subscribe((params) => {
    //   this.bookingId = params['data'],
    //     this.cityId = params['cId'],
    //     this.ghId = params['gId']
    // })
    this.bookingId = this.navParams.data.data;
    this.cityId = this.navParams.data.cId;
    this.ghId = this.navParams.data.gId;
    this.getBillDetailsByBookingIdForMobile();
  }

  public async getBillDetailsByBookingIdForMobile() {
    const loader = await this.loadingController.create({
      message: "Please wait..."
    });
    loader.present();
    this.mdmService.getBillDetailsByBookingIdForMobile(this.bookingId).subscribe(
      res => {
        if (res.status == 1) {
          this.billDetails = res.data;
          this.setAdvAmount(this.billDetails.guestDetails);
          loader.dismiss();
        } else {
          loader.dismiss();
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!")
      }
    )
  }

  public async createBill(data, type) {
    data.charges = [
      { "id": null, "name": "Room Rent", "desc": this.roomRent },
      { "id": null, "name": "Food", "desc": this.foodCharge },
      { "id": null, "name": "Vehicle", "desc": this.vehicle },
      { "id": null, "name": "News paper", "desc": this.newspaper },
      { "id": null, "name": "Telephone", "desc": this.telephone },
      { "id": null, "name": "Additional", "desc": this.additional },
      { "id": null, "name": "NOC", "desc": this.noc },
    ]
    console.log(data)
    const modal = await this.modalController.create({
      component: BillDetailsComponent,
      componentProps: { data, type, cityId: this.cityId, ghId: this.ghId, advanceAmount: this.advAmount }
    })
    modal.onDidDismiss().then((data) => {
      //  this.location.back();
      if (data.data == 1) {
        this.modalController.dismiss();
      }
    })
    return await modal.present();
  }

  public setAdvAmount(data) {
    let d;
    d = data;
    d.forEach(el => {
      this.advAmount = this.advAmount + parseInt(el.advanceAmount)
    });
    console.log(this.advAmount)

  }

  public goBack() {
    this.modalController.dismiss();
  }

}
