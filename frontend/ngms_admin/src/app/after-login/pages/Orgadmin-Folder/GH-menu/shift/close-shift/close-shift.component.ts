import { DatePipe, formatDate } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AlertController, LoadingController, ModalController, NavParams } from "@ionic/angular";
import { MdmService } from "src/app/Services/MDM/mdm.service";

@Component({
  selector: "app-close-shift",
  templateUrl: "./close-shift.component.html",
  styleUrls: ["./close-shift.component.scss"],
})
export class CloseShiftComponent implements OnInit {
  getCurrentShiftData: any;
  buttonName: string;
  number1: any;
  number2: any;
  closeShiftSum: number;
  currentDateTime: string;
  tomorrow: string | number | Date;
  tomorrowDateTime: string;
  currenTime: string;
  currentShift: string;
  constructor(
    private navParams: NavParams,
    private mdmService: MdmService,
    private loadingController: LoadingController,
    public datepipe: DatePipe,
    private formFB: FormBuilder,
    private alertController: AlertController,
    private modalCtrl: ModalController
  ) { }
  public id: String;
  public closeShiftForm: FormGroup;
  ngOnInit() {
    this.getShiftDataById();
    this.createCloseShift();
    this.currentDateTime = this.datepipe.transform((new Date), 'dd-MM-yyyy h:mm');
    this.currenTime = formatDate(new Date(), 'yyyy-MM-ddThh:mm', 'en-US', '+0530');
    console.log('current time', this.currenTime);
    this.tomorrow = new Date();
    this.datepipe.transform(this.tomorrow.setDate(new Date().getDate() + 1), 'dd-MM-yyyy');
    this.tomorrowDateTime = this.datepipe.transform(this.tomorrow, 'dd-MM-yyyy');
    const currentHour = new Date().getHours();
    if (currentHour >= 6 && currentHour < 14) {
      this.currentShift = '1';
    } else if (currentHour >= 14 && currentHour < 22) {
      this.currentShift = '2';
    } else {
      this.currentShift = '3';
    }
    const today = (new Date()).toLocaleString("EN-CA").slice(0, 10); // get local current date
  }
  ionViewWillEnter() {
    this.id = this.navParams.data.id;
    console.log("allPreviewData", this.id);
  }
  createCloseShift() {
    this.closeShiftForm = this.formFB.group({
      openTime: ["", Validators.compose([Validators.required])],
      closeTime: ["", Validators.compose([Validators.required])],
      refundBal: ["", Validators.compose([Validators.required])],
      regularBal: ["", Validators.compose([Validators.required])],
      totalBal: [""],
    });
  }

  public async getShiftDataById() {
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.getDetailsByShiftId(this.id).subscribe(
      (res) => {
        loading.dismiss();
        console.log(res);
        this.getCurrentShiftData = res.data;
        this.setCloseShiftData(this.getCurrentShiftData);
      },
      (err) => {
        loading.dismiss();
      },
      ()=>{
        loading.dismiss()
      }
    );
  }

  submitCloseShift(formvalue) {
    const obj = {
      closeTime: this.datepipe.transform(
        this.closeShiftForm.get("closeTime").value,
        "dd-MM-yyyy h:mm"
      ),
      openBalRefund: this.closeShiftForm.get("refundBal").value,
      openBalRegular: this.closeShiftForm.get("regularBal").value,
      openBalTotal: this.closeShiftForm.get("totalBal").value,
      shiftId: this.getCurrentShiftData.shiftId,
    };
    this.mdmService.closePreviousShift(obj).subscribe((res) => {
      this.showAlert(res.statusDesc);
    });
  }

  public async showAlert(msg) {
    const alert = await this.alertController.create({
      message: msg,
      buttons: [
        {
          text: "OK",
          handler: () => { this.modalCtrl.dismiss(); },
        },
      ],
    });
    await alert.present();
  }
  public goBack() {
    this.modalCtrl.dismiss();
  }
  setCloseShiftData(data) {
    this.closeShiftForm.controls.openTime.setValue(data?.createdTime);
    this.closeShiftForm.controls.refundBal.setValue(data?.openBalRefund);
    this.closeShiftForm.controls.regularBal.setValue(data?.openBalRegular);
    this.closeShiftForm.controls.totalBal.setValue(data?.openBalTotal);
    this.closeShiftForm.controls.closeTime.setValue(this.currenTime);
  }

  calculateClose1() {
    this.number1 = this.closeShiftForm.get("refundBal").value;
    this.number2 = this.closeShiftForm.get("regularBal").value;
    this.closeShiftSum = parseInt(this.number1) + parseInt(this.number2);
    this.closeShiftForm.controls.totalBal.setValue(this.closeShiftSum);
  }
}
function createCloseShift() {
  throw new Error("Function not implemented.");
}
