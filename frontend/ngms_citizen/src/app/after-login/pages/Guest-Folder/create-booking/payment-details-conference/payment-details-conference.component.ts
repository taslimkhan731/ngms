import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ModalController, Platform } from '@ionic/angular';
import { CommonService } from 'src/app/Services/common/common.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Browser } from '@capacitor/browser'
import { AppConstants } from 'src/app/app.constants';
// import * as SockJS from 'sockjs-client';
// import { Client, StompSubscription } from '@stomp/stompjs';
// import * as SockJS from 'sockjs-client';
// import { Observable } from 'rxjs';
// import SockJS from 'sockjs-client';

@Component({
  selector: 'app-payment-details-conference',
  templateUrl: './payment-details-conference.component.html',
  styleUrls: ['./payment-details-conference.component.scss'],
})
export class PaymentDetailsConferenceComponent implements OnInit {
  @ViewChild('form') form: ElementRef;
  public clicked = false;
  paymentUrl: SafeResourceUrl;
  public confDetails;
  accessCode: any;
  encRequest: any;
  paymentURL: string;
  public paymentForm: FormGroup;
  formAction = 'https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction';

  // private stompClient: any;
  // private stompClient: Client;

  constructor(
    private commonService: CommonService,
    private mdmService: MdmService,
    private modalCtrl: ModalController,
    private location: Location,
    private router: Router,
    private sanitizer: DomSanitizer,
    private platform: Platform,
    private http: HttpClient,
    private fb: FormBuilder,
    // private appConstant: AppConstants,
    // private socket: Socket
  ) {
    this.createPaymentInstances();
  }

  ngOnInit() {
    this.confDetails = this.commonService.getStoredDetails();
    console.log("data aaaa", this.confDetails)
    this.encRequest = this.confDetails.encRequest
    this.accessCode = this.confDetails.accessCode
    console.log("dataaaaaaaaaaaaaaaaaa aaaa", this.encRequest)
    // this.paymentSubmit();
  }

  public goBack() {
    // this.location.back()
    this.router.navigate(["/after-login/pages/guest/create-booking",]);

  }

  public pay() {
    let bill = this.confDetails?.billId
    let obj = {
      "reqId": this.confDetails?.reqId,
      "billId": bill.toString(),
      "amount": this.confDetails?.advanceAmount
    }
    this.mdmService.updateBillRecordForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {

          this.router.navigate(['after-login/pages/guest/create-booking/paymentResponse'], { queryParams: { reqId: res.data.reqId, status: res.data.status } })
        } else {
          this.commonService.showErrorToast("Something Went Wrong!!")
        }
      }
    )
  }

  private createPaymentInstances() {
    this.paymentForm = this.fb.group({
      encRequest: '',
      access_code: ''
    });
  }

  getSafeUrl(): SafeResourceUrl {
    const url = `http://192.168.0.72:8080/ngms/pgMobileRequest.do?encRequest=${this.encRequest}&accessCode=${this.accessCode}`;
    // const uri = 'https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction';
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  async openBrowser() {
    this.clicked = true
    console.log('url', AppConstants.BASE_API_URL);
    console.log("encRequest", this.encRequest);
    console.log("accessCode", this.accessCode);
    // const url = `http://192.168.29.78:8080/ngms/pgMobileRequest.do?encRequest=${this.encRequest}&accessCode=${this.accessCode}`;
    const url = AppConstants.BASE_API_URL + `pgMobileRequest.do?encRequest=${this.encRequest}&accessCode=${this.accessCode}`;
    console.log('updated url', url);
    await Browser.open({ url: url });
    Browser.addListener('browserFinished',() => {
      console.log("Browser finished")
      this.router.navigate(['after-login/pages/guest/view-booking/conference'])
    })

  }

}
