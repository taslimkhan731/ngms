import { Component, OnInit } from '@angular/core';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { PopoverController } from '@ionic/angular';

@Component({
  selector: 'app-checkin-checkout',
  templateUrl: './checkin-checkout.component.html',
  styleUrls: ['./checkin-checkout.component.scss'],
})
export class CheckinCheckoutComponent implements OnInit {
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'From Date:', val: '11-05-2020' },
        { key: 'To Date:', val: '11-05-2020' },
        { key: 'Rooms:', val: '106' },
      ],
      // guestList: [
      //   // { key: 'Guest Name ', val: 'Subrat Kumar Sarangi' },
      //   // { key: 'Guest Name ', val: 'Subrat Kumar Sarangi' },
      //   // { key: 'Guest Name ', val: 'Subrat Kumar Sarangi' },
      //   // { key: 'Guest Name ', val: 'Subrat Kumar Sarangi' },
      //   // { key: 'Gender ', val: 'Male' },
      //   // { key: 'Department  ', val: 'Govt.' },
      //   // { key: 'Designation  ', val: 'Officer' },
      //   // {guestName:'Subrat Kumar Sarangi',Gender:"Male",Dept:'Govt',desg:'Officer'},
      //   // {guestName:'Subrat Kumar Sarangi',Gender:"Male",Dept:'Govt',desg:'Officer'},
      //   // {guestName:'Subrat Kumar Sarangi',Gender:"Male",Dept:'Govt',desg:'Officer'}
      // ],
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'From Date:', val: '11-05-2020' },
        { key: 'To Date:', val: '11-05-2020' },
        { key: 'Rooms:', val: '106' },
      ],
      guestList: [
        { key: 'Guest Name ', val: 'Subrat Kumar Sarangi' },
        { key: 'Gender ', val: 'Male' },
        { key: 'Department  ', val: 'Govt.' },
        { key: 'Designation  ', val: 'Officer' },
      ],
    },
  ];
  public guestDetails = [
    { Name: 'ndghf', desg: 'jchcb', dept: 'lkch' },
    { Name: 'ndghf', desg: 'jchcb', dept: 'lkch' },
    { Name: 'ndghf', desg: 'jchcb', dept: 'lkch' },
  ]
  public btnData = [
    { btnName: 'Checkout ', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  ];
  public splitBtnClass = [
    { btnName: ' Checkin', btnIcon: 'eye-outline', active: false },
    { btnName: ' Checkout', btnIcon: 'eye-outline', active: false },
  ]
  constructor(public popoverController: PopoverController) {

  }

  ngOnInit() { }
  getbutton(button, bhawanName) {

  }
  getButtonName(btnName) {
    console.log("btn is", btnName);
 
  }
  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
  }
}
