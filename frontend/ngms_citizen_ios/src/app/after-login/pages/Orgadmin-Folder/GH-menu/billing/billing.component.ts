import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss'],
})
export class BillingComponent implements OnInit {
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'Name:', val: 'Deepak' },
        { key: 'From Date:', val: 'No' },
        { key: 'To Date:', val: 'Room' },
        { key: 'Visitors Category:', val: 'Governer' },
        { key: 'Purpose:', val: 'Non-Offcial' },
        { key: 'No. of Adult:', val: '2' },
        { key: 'No. of Children:', val: '0' },
        { key: 'Advance Amount:', val: '400' },
      ]
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'Name:', val: 'Deepak' },
        { key: 'From Date:', val: 'No' },
        { key: 'To Date:', val: 'Room' },
        { key: 'Visitors Category:', val: 'Governer' },
        { key: 'Purpose:', val: 'Non-Offcial' },
        { key: 'No. of Adult:', val: '2' },
        { key: 'No. of Children:', val: '0' },
        { key: 'Advance Amount:', val: '400' },
      ]
    },
  ];
  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  ];
 
  constructor(public popoverController: PopoverController) { }

  ngOnInit() {}
  async presentPopover(ev: any){
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
  }
}
