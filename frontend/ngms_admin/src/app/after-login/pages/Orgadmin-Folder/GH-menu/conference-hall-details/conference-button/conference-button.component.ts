import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertController, LoadingController, ModalController, NavParams } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { mobileNumberValidator } from 'src/app/validators/custom-validators';
import * as numberToWords from 'number-to-words';

@Component({
  selector: 'app-conference-button',
  templateUrl: './conference-button.component.html',
  styleUrls: ['./conference-button.component.scss'],
})
export class ConferenceButtonComponent implements OnInit {

  public ghId;
  public type;
  public headerName: string;
  public conferenceForm: FormGroup;
  public confData;
  public isDisable;
  public isSubmitBtnClicked = false;
  public totalAmountText;
  public totalAmountValue = 0;
  public amountNeedtoPayText;
  public amountNeedtoPayvalue = 0;
  public totalAmountPaid = 0;
  public paymentData;
  public receiptStatus;
  public receiptDate;
  public receiptId;
  public guestDetails;
  public billDate;
  public modeOfpayment;
  public modeOfpayment_amount;
  public transactionNo;
  public currDate = new Date();
  public amountAndWords;
  public amountInR;
  public disableCash = true;
  public disablePos = true;
  public disableOnline = true;
  public cashAmount;
  public posAmount;
  public onlineAmount;
  public fcashAmount;
  public fposAmount;
  public fonlineAmount;
  public showPosTransation = false;
  public additionalCharge = 0;
  freceiptStatus;
  freceiptDate;
  freceiptId;
  fbillDate;
  fmodeOfpayment;
  fmodeOfpayment_amount;
  ftransactionNo;


  constructor(private navParams: NavParams,
    private fb: FormBuilder,
    private loadingController: LoadingController,
    private alertController: AlertController,
    private mdmservice: MdmService,
    private commonService: CommonService,
    private modalctrl: ModalController) {
    this.createFormInstance()
  }

  private createFormInstance() {
    this.conferenceForm = this.fb.group({
      blockId: [''],
      name: ['', Validators.required],
      mobileNo: ['', Validators.compose([Validators.required, mobileNumberValidator])],
      address: ['', Validators.required],
      fromDate: [''],
      capacity: [''],
      categoryType: [''],
      confCatId: [''],
      confCatDesc: [''],
      confUseId: [''],
      confUseDesc: [''],
      amount: [''],
      strtTime: [''],
      endTime: [''],
      advanceAmount: [''],
      additonalCharge: [''],
    })
  }

  ngOnInit() { }

  ionViewWillEnter() {
    this.type = this.navParams.data.button;
    this.confData = this.navParams.data.confData;
    this.ghId = this.navParams.data.ghId;
    this.guestDetails = this.confData.name + " , " + this.confData.address;
    this.setConferenceData();
    console.log("conferenceButton", this.confData);
    if (this.type === 'View') {
      this.conferenceForm.get('fromDate').setValue(this.confData.fromDate.slice(0, 10));
      this.conferenceForm.disable();
      this.headerName = 'View Guest Details'
    }
    else if (this.type === 'ViewBill') {
      this.headerName = 'Billing Details'
      this.setViewBillData();
    }
    else if (this.type === 'Receipt') {
      this.headerName = 'Payment Receipt'
      this.getReceiptDetails();
    }
    else if (this.type === 'Edit') {
      this.conferenceForm.disable();
      this.conferenceForm.get('fromDate').setValue(this.confData.fromDate.slice(0, 10));
      this.conferenceForm.get('name').enable();
      this.conferenceForm.get('address').enable();
      this.conferenceForm.get('mobileNo').enable();
      this.headerName = 'Edit Guest Details'
    }
    else if (this.type === 'Payment') {
      this.headerName = 'Make Receipt';
      this.setDataForMakeReceipt()
    }
    else if (this.type === 'CRBill') {
      this.headerName = "Create Bill";
      this.setDataForCreateBill();
    }
  }

  public setDataForCreateBill() {
    if (this.confData.billStatus == 'NB') {
      this.amountNeedtoPayvalue = this.confData.advanceAmount;
      this.totalAmountText = "Total Amount";
      this.totalAmountValue = parseInt(this.confData.charge) + parseInt(this.confData.additonalCharge);
    }
    if (this.confData.billStatus == 'P') {
      // this.totalAmountText = "Total Amount(Amount+Additional Charge)";
      this.amountNeedtoPayvalue = this.confData.pendingAmount;
      this.totalAmountText = "Total Amount";
      this.totalAmountValue = parseInt(this.confData.charge) + parseInt(this.confData.additonalCharge);
    }
  }

  public setConferenceData() {
    this.conferenceForm.get('blockId').setValue(this.confData.blockId);
    this.conferenceForm.get('name').setValue(this.confData.name);
    this.conferenceForm.get('mobileNo').setValue(this.confData.mobile);
    this.conferenceForm.get('address').setValue(this.confData.address);
    this.conferenceForm.get('fromDate').setValue(this.confData.fromDate);
    this.conferenceForm.get('capacity').setValue(this.confData.capacity);
    this.conferenceForm.get('categoryType').setValue(this.confData.category);
    this.conferenceForm.get('confCatId').setValue(this.confData.confrenceId);
    this.conferenceForm.get('confCatDesc').setValue(this.confData.confrenceDesc);
    this.conferenceForm.get('confUseId').setValue(this.confData.confreqnceUseId);
    this.conferenceForm.get('confUseDesc').setValue(this.confData.confreqnceUseDesc);
    this.conferenceForm.get('amount').setValue(this.confData.charge);
    this.conferenceForm.get('strtTime').setValue(this.confData.confreqnceUseId == '4' ? this.confData.fromDate.slice(11, 16) : '');
    this.conferenceForm.get('endTime').setValue(this.confData.confreqnceUseId == '4' ? this.confData.toDate.slice(11, 16) : '');
    this.conferenceForm.get('advanceAmount').setValue(this.confData.advanceAmount);
    this.conferenceForm.get('additonalCharge').setValue(this.confData.additonalCharge);
  }

  public setViewBillData() {
    if (this.confData.billStatus == 'FB' || this.confData.billStatus == 'F') {
      this.totalAmountText = "Total Amount(Amount+Additional Charge)";
      this.totalAmountValue = parseInt(this.confData.pendingAmount) + parseInt(this.confData.advanceAmount)
      if (this.confData.billStatus == "FB") {
        this.amountNeedtoPayText = "Amount Need To Pay";
        this.amountNeedtoPayvalue = this.confData.pendingAmount;
      }
      if (this.confData.billStatus == "F") {
        this.totalAmountPaid = parseInt(this.confData.pendingAmount) + parseInt(this.confData.advanceAmount);
        this.amountNeedtoPayText = "Amount Paid";
        this.amountNeedtoPayvalue = this.confData.pendingAmount;
      }
    } else {
      this.totalAmountText = "Total Amount";
    }

    if (this.confData.billStatus == "P" || this.confData.billStatus == "B") {
      if (this.confData.billStatus == "B") {
        this.amountNeedtoPayText = "Amount Need To Pay";
      }
      if (this.confData.billStatus == "P") {
        this.amountNeedtoPayText = "Amount Paid";
      }
      this.totalAmountValue = parseInt(this.confData.pendingAmount) + parseInt(this.confData.advanceAmount)
      this.amountNeedtoPayvalue = this.confData.advanceAmount;
    }
  }

  public async updateConferenceDetails() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    this.isSubmitBtnClicked = true;
    if (this.conferenceForm.valid) {
      let data = {
        "reqId": this.confData.bookingId,
        "mobileNo": this.conferenceForm.get('mobileNo').value,
        "address": this.conferenceForm.get('address').value,
        "capacity": this.conferenceForm.get('capacity').value,
        "name": this.conferenceForm.get('name').value
      }
      loading.present();
      this.mdmservice.editBillingDetailsForMobile(data).subscribe(
        res => {
          if (res.status == 1) {
            this.commonService.showSuccessToast('Edit conference hall Successful');
            loading.dismiss()
            this.modalctrl.dismiss();

          } else {
            this.commonService.showErrorToast(res.statusDesc);
            loading.dismiss()
          }
        }, err => {
          this.commonService.showErrorToast("Something Went Wrong.Please try again.")
          loading.dismiss()
        },
        () => {
          loading.dismiss();
        })
    }

  }

  // getRecieptDetailsForMobile
  public async getReceiptDetails() {
    let billId = this.confData.billId;
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmservice.getRecieptDetailsForMobile(billId).subscribe(
      res => {
        if (res.status == 1) {
          this.paymentData = res.data.data;
          loading.dismiss()
          this.setReceiptViewData();
        } else {
          this.commonService.showErrorToast(res.statusDesc)
          loading.dismiss()
        }
      }, err => {
        this.commonService.showErrorToast("Something Went Wrong!")
        loading.dismiss();
      },
      () => {
        loading.dismiss();
      })
  }

  public setReceiptViewData() {
    for (let i = 0; i < this.paymentData.length; i++) {
      if (this.paymentData[i].recStatus == 'A') {
        this.receiptStatus = this.paymentData[i].recStatus;
        this.receiptDate = this.paymentData[i].receiptDate;
        this.receiptId = this.paymentData[i].recId;
        this.billDate = this.paymentData[i].billDate;
        this.modeOfpayment = this.paymentData[i].modeOfpayment;
        // this.modeOfpayment_amount = this.paymentData[i].amount;
        if(this.modeOfpayment=='cash'){
          this.cashAmount = this.paymentData[i].amount;
        }
        if(this.modeOfpayment=='pos'){
          this.posAmount = this.paymentData[i].amount;
        }
        if(this.modeOfpayment=='online'){
          this.onlineAmount = this.paymentData[i].amount;
        }
        this.transactionNo = this.paymentData[i].pos_tr_num;
      }
      if (this.paymentData[i].recStatus == 'F') {
        this.freceiptStatus = this.paymentData[i].recStatus;
        this.freceiptDate = this.paymentData[i].receiptDate;
        this.freceiptId = this.paymentData[i].recId;
        this.fbillDate = this.paymentData[i].billDate;
        this.fmodeOfpayment = this.paymentData[i].modeOfpayment;
        // this.fmodeOfpayment_amount = this.paymentData[i].amount;
        if(this.fmodeOfpayment=='cash'){
          this.fcashAmount = this.paymentData[i].amount;
        }
        if(this.fmodeOfpayment=='pos'){
          this.fposAmount = this.paymentData[i].amount;
        }
        if(this.fmodeOfpayment=='online'){
          this.fonlineAmount = this.paymentData[i].amount;
        }
        this.ftransactionNo = this.paymentData[i].pos_tr_num;
      }
    }
  }

  public setDataForMakeReceipt() {
    if (this.confData.billStatus == 'B') {
      let words = this.convertNumberToWords(this.confData.advanceAmount);
      this.amountAndWords = this.confData.advanceAmount + "(" + words + " only)";
      this.amountInR = this.confData.advanceAmount;
    }
    if (this.confData.billStatus == 'FB') {
      let words = this.convertNumberToWords(this.confData.pendingAmount);
      this.amountAndWords = this.confData.pendingAmount + "(" + words + " only)";
      this.amountInR = this.confData.pendingAmount;
    }
  }

  getCheckboxId(checkbox: HTMLInputElement, event) {
    let id = checkbox.id;
    let isChecked = event.target.checked;
    let total = this.amountInR;
    if (id === 'cash') {
      // this.posAmount = 0;
      // this.onlineAmount = 0;
      if (isChecked) {
        this.disableCash = false;
        let posAmount = this.posAmount || 0;
        // let onlineAmount = this.onlineAmount;
        console.log(total, posAmount)
        let cashAmount = total - posAmount;
        this.cashAmount = cashAmount;
      } else {
        this.disableCash = true;
        this.cashAmount = 0;
      }
    }
    if (id === 'pos') {
      // this.cashAmount = 0;
      // this.onlineAmount = 0
      if (isChecked) {
        this.disablePos = false
        let cashAmount = this.cashAmount || 0;
        // let onlineAmount = this.onlineAmount
        let posAmount = total - cashAmount;
        this.posAmount = posAmount
        this.showPosTransation = true;
        // $('#postx').prop('hidden', false);
      } else {
        this.showPosTransation = false;
        this.disablePos = true
        this.posAmount = 0
      }
    }
    // if (id === 'online') {
    //   // this.cashAmount = 0;
    //   // this.posAmount = 0
    //   if (isChecked) {
    //     this.disableOnline = false;
    //     let cashAmount = this.cashAmount
    //     let posAmount = this.posAmount;
    //     let onlineAmount = total - cashAmount - posAmount;
    //     // this.onlineAmount = onlineAmount;
    //   } else {
    //     this.disableOnline = true;
    //     // this.onlineAmount = 0
    //   }
    // }
  }

  public check(event, type) {
    let total = this.amountInR;
    let cashAmount = parseFloat(this.cashAmount || 0);
    let posAmount = parseFloat(this.posAmount || 0);
    // let onlineAmount = parseFloat(this.onlineAmount || 0);
    let totalAmount = cashAmount + posAmount;
    if (totalAmount > total) {
      this.commonService.showErrorToast("Amount should not be greater than total.")
      if (type == 'cash') {
        this.cashAmount = 0
      }
      if (type == 'pos') {
        this.posAmount = 0
      }
      // if (type == 'online') {
      //   this.onlineAmount = 0
      // }
    }
  }

  public async submitReceipt(cash, pos) {
    let mode = {};
    let total = 0;
    let status = 'P';
    if (this.confData.billStatus == 'FB') {
      status = 'F';
    }
    if (!cash && !pos) {
      this.commonService.showErrorToast("Please select payment mode")
      return;
    }
    if (cash) {
      if (this.cashAmount == '' || this.cashAmount === '0') {
        this.commonService.showErrorToast('Cash amount should not be empty or zero if cash is selected');
        return false;
      }
      mode['cash'] = parseFloat(this.cashAmount);
      total = parseInt(this.cashAmount);
    }
    if (pos) {
      if (this.posAmount == '' || this.posAmount === '0') {
        this.commonService.showErrorToast('POS amount should not be empty or zero if cash is selected');
        return false;
      }
      if (this.transactionNo == '' || this.transactionNo == null) {
        this.commonService.showErrorToast('Please provide transaction number.');
        return false;
      }
      mode['pos'] = parseFloat(this.posAmount);
      total = total + parseInt(this.posAmount)
    }
    // if (online) {
    //   if (this.onlineAmount == '' || this.onlineAmount === '0') {
    //     this.commonService.showErrorToast('Online amount should not be empty or zero if cash is selected');
    //     return false;
    //   }
    //   mode['online'] = parseFloat(this.onlineAmount);
    //   total = total + this.onlineAmount;
    // }

    if (total != this.amountInR) {
      this.commonService.showErrorToast("Please provide requested amount.");
      return false;
    }

    console.log("mode", mode)

    let data = {
      billId: this.confData.billId,
      ghId: this.ghId,
      mode: mode,
      paymentReqId: this.confData.bookingId,
      transactionNo: this.transactionNo ? this.transactionNo : '',
      amount: this.amountInR.toString(),
      status: status,
    };
    console.log(data);

    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to submit the receipt?',
      buttons: [
        {
          text: 'Yes',
          // cssClass: 'alertButton',
          handler: () => {
            this.saveRecipetDetailsForMobile(data);
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

  public async saveRecipetDetailsForMobile(data) {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });

    loader.present();
    this.mdmservice.saveRecipetDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc);
          this.modalctrl.dismiss();
          loader.dismiss();
        } else if (res.status == 0) {
          loader.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
        }
      },
      err => {
        loader.dismiss()
        this.commonService.showErrorToast('Something went wrong');
      },
      () => {
        loader.dismiss();
      });
  }
  public async saveBillingDetailsForMobile(data) {
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });

    loader.present();
    this.mdmservice.saveBillingDetailsForMobile(data).subscribe(
      res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc);
          this.modalctrl.dismiss();
          loader.dismiss();
        } else if (res.status == 0) {
          loader.dismiss();
          this.commonService.showErrorToast(res.statusDesc);
        }
      },
      err => {
        loader.dismiss()
        this.commonService.showErrorToast('Something went wrong');
      },
      () => {
        loader.dismiss();
      });
  }

  public async submitBill() {
    let data = {
      reqId: this.confData.bookingId,
      additonalCharges: this.additionalCharge.toString(),
      status: this.confData.billStatus
    };
    console.log(data);

    const alert = await this.alertController.create({
      cssClass: 'alertCancel',
      header: 'Are you sure?',
      message: 'Are you sure to submit the Bill?',
      buttons: [
        {
          text: 'Yes',
          // cssClass: 'alertButton',
          handler: () => {
            this.saveBillingDetailsForMobile(data);
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

  public updateValue() {
    console.log("2nd")
    let amount = parseInt(this.confData.charge) + this.additionalCharge;
    console.log(this.additionalCharge, parseInt(this.confData.charge) + this.additionalCharge)
    this.totalAmountValue = amount;
    if (this.confData.billStatus !== 'NB') {
      this.amountNeedtoPayvalue = amount - parseInt(this.confData.advanceAmount)
    }
    console.log("this.amountNeedtoPayvalue")
  }


  public onlyNumberKey(event) {
    console.log("first")
    var ASCIICode = (event.which) ? event.which : event.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
      return false;
    return true;
  }

  public convertNumberToWords(number: number): string {
    console.log(number)
    return numberToWords.toWords(number);
  }

  goBack() {
    this.modalctrl.dismiss();
  }

  transformTextForConf(event) {
    const inputValue = event.target.value;
    const words = inputValue.split(' '); // Split the input by spaces
    const capitalizedWords = words.map(word => {
      return word.charAt(0).toUpperCase() + word.slice(1); // Capitalize each word
    });
    const capitalizedValue = capitalizedWords.join(' '); // Join the words back together with spaces
    this.conferenceForm.get('name').setValue(capitalizedValue);
  }

}
