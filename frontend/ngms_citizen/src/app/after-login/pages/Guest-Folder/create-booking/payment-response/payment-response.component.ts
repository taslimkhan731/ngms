import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-payment-response',
  templateUrl: './payment-response.component.html',
  styleUrls: ['./payment-response.component.scss'],
})
export class PaymentResponseComponent implements OnInit {

  public reqId;
  public status;
  constructor(
    private route: ActivatedRoute,

  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.reqId = params['reqId'];
      this.status = params['status'];
    })
  }

  public goBack(){
    
  }

}
