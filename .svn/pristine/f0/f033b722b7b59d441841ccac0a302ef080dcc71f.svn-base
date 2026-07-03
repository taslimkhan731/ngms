import { Component, OnInit } from '@angular/core';
import { LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import * as numberToWords from 'number-to-words';
import { DatePipe } from '@angular/common';
import { PDFGenerator } from '@ionic-native/pdf-generator/ngx';

@Component({
  selector: 'app-bill-details',
  templateUrl: './bill-details.component.html',
  styleUrls: ['./bill-details.component.scss'],
})
export class BillDetailsComponent implements OnInit {

  public bookingId;
  public billId;
  public modalData;
  public guestHouseAndCityName;
  public billDetails;
  public balToBePaid;
  public balToBePaidInWords;
  public currDate = new Date();
  public currTime;
  public billDate;
  public type;
  // public charges : { [id: string]: string } = {};
  public totalBill;
  public advanceAmount;
  public masterCharges;
  public cityId;
  public ghId;
  public content: string;

  constructor(
    private modalCtrl: ModalController,
    private loadingCtrl: LoadingController,
    private mdmService: MdmService,
    private commonService: CommonService,
    private navParams: NavParams,
    private datePipe: DatePipe,
    private pdfGenerator: PDFGenerator
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.modalData = this.navParams.data.data;
    this.type = this.navParams.data.type;
    this.cityId = this.navParams.data.cityId;
    this.ghId = this.navParams.data.ghId;
    this.bookingId = this.modalData.bookingId;
    this.billId = this.modalData.billId;
    if (this.type == 'billing') {
      this.setBillDetails();
      this.billingViewForMobile()
      this.advanceAmount = this.navParams.data.advanceAmount;
    } else if (this.type == 'Bill List') {
      this.showBillDetails();
    }
  }

  public async billingViewForMobile() {
    const loader = await this.loadingCtrl.create({
      message: "Please wait..."
    });
    loader.present();
    this.mdmService.billingViewForMobile().subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.masterCharges = res.data;
          console.log(this.masterCharges)
        } else {
          loader.dismiss();
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!")
      },
      ()=> {
        loader.dismiss()
      }
    )
  }

  public setBillDetails() {
    this.billDetails = this.modalData;
    // let charges = [];
    // charges.push(this.billDetails?.charges);
    console.log("billDetails", this.billDetails)
    console.log("Charges", this.billDetails?.charges)
    this.billDate = this.datePipe.transform(this.currDate, 'dd-MM-yyyy HH:mm');
    let roomRent = 0, food = 0, newspaper = 0, vehicle = 0, tel = 0, additional = 0, noc = 0;

    for (let i = 0; i < this.billDetails?.charges.length; i++) {
      console.log(this.billDetails?.charges[i]?.name)
      if (this.billDetails?.charges[i]?.name == 'Room Rent') {
        roomRent = this.billDetails?.charges[i]?.desc;
      }
      if (this.billDetails?.charges[i]?.name == 'Food') {
        food = this.billDetails?.charges[i]?.desc;
      }
      if (this.billDetails?.charges[i]?.name == 'Vehicle') {
        vehicle = this.billDetails?.charges[i]?.desc;
      }
      if (this.billDetails?.charges[i]?.name == 'News paper') {
        newspaper = this.billDetails?.charges[i]?.desc;
      }
      if (this.billDetails?.charges[i]?.name == 'Telephone') {
        tel = this.billDetails?.charges[i]?.desc;
      }
      if (this.billDetails?.charges[i]?.name == 'Additional') {
        additional = this.billDetails?.charges[i]?.desc;
      }
      if (this.billDetails?.charges[i]?.name == 'NOC') {
        noc = this.billDetails?.charges[i]?.desc;
      }
    }
    this.totalBill = Math.round(roomRent + food + vehicle + newspaper + tel + additional + noc);
    this.balToBePaid = Math.round(parseFloat(this.totalBill) - parseFloat(this.advanceAmount));
    this.balToBePaidInWords = this.convertNumberToWords(this.balToBePaid)
    console.log(this.totalBill, this.advanceAmount)
  }

  public async showBillDetails() {
    const loader = await this.loadingCtrl.create({
      message: "Please wait..."
    });
    loader.present();
    this.mdmService.showBillDetailsForMobile(this.bookingId, this.billId).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.billDetails = res.data;
          this.billDate = this.billDetails.billDate;
          this.guestHouseAndCityName = this.billDetails.guestDetails[0].ghName + ", " + this.billDetails.guestDetails[0].cityName;
          this.balToBePaid = Math.round(parseFloat(this.billDetails.total) - parseFloat(this.billDetails.advanceAmount))
          this.balToBePaidInWords = this.convertNumberToWords(this.balToBePaid)
        } else {
          loader.dismiss()
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!")
      },
      ()=> {
        loader.dismiss()
      }
    )
  }

  public async submitBill() {
    let guestIds =[];
    let charges : { [id: string]: string } = {};
    let bookId;
    for (let i = 0; i < this.billDetails.guestDetails.length; i++) {
      bookId=this.billDetails.guestDetails[i].bookingId
      guestIds.push(this.billDetails.guestDetails[i].guestId)
    }
    
    for (let i = 0; i < this.billDetails?.charges.length; i++) {
      for (let j = 0; j < this.masterCharges.length; j++) {
       if(this.billDetails?.charges[i].name == this.masterCharges[j]?.charges_title) {
        charges[this.masterCharges[j].charges_id] = this.billDetails.charges[i].desc;
       }
      }
    }

    let data = {
      "bookingId": bookId,
      "cityId": this.cityId,
      "ghId": this.ghId,
      "guestIds": guestIds,
      "charges": charges,
      "advance": this.advanceAmount,
      "nocDate": this.billDetails.nocDate
    }

    const loader = await this.loadingCtrl.create({
      message: "Please wait..."
    });
    loader.present();
    this.mdmService.saveBillDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          loader.dismiss();
          this.commonService.showSuccessToast('Bill submitted successfully')
          this.modalCtrl.dismiss(1);
        } else {
          loader.dismiss()
          this.commonService.showSuccessToast(res.statusDesc)
        }
      },
      err => {
        loader.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!")
      },
      ()=> {
        loader.dismiss()
      }
    )
    
  }

  public convertNumberToWords(number: number): string {
    console.log(number)
    return numberToWords.toWords(number);
  }

  public printBill(){
    this.content = document.getElementById('PrintBill').innerHTML;
    this.content = `${this.commonService.style}${this.content}`;
    console.log("content is", this.content)
    let options = {
      documentSize: 'A4',
      type: 'share',
      fileName: 'download.pdf'
    };
    this.pdfGenerator.fromData(this.content, options)
      .then((base64) => {
        console.log('OK', base64);
      }).catch((error) => {
        console.log('error', error);
      });
  }

  public goBack() {
    this.modalCtrl.dismiss();
  }
}
