import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';

@Component({
  selector: 'app-current-booking',
  templateUrl: './current-booking.component.html',
  styleUrls: ['./current-booking.component.scss'],
})
export class CurrentBookingComponent implements OnInit {
  selctGH = ''
  selctCity = ''
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'From Date:', val: '30.11.2022' },
        { key: 'To Date:', val: '30.11.2022' },
        { key: 'Purpose:', val: 'Non-official' },
        { key: 'Mobile No:', val: '977765644' },
        { key: 'Email ID:', val: 'ansx@gmail.com' },
        { key: 'Name:', val: 'Non-official' },
        
      ]
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'From Date:', val: '30.11.2022' },
        { key: 'To Date:', val: '30.11.2022' },
        { key: 'Purpose:', val: 'Non-official' },
        { key: 'Mobile No:', val: '977765644' },
        { key: 'Email ID:', val: 'ansx@gmail.com' },
        { key: 'Name:', val: 'Non-official' },
        
      ]
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'From Date:', val: '30.11.2022' },
        { key: 'To Date:', val: '30.11.2022' },
        { key: 'Purpose:', val: 'Non-official' },
        { key: 'Mobile No:', val: '977765644' },
        { key: 'Email ID:', val: 'ansx@gmail.com' },
        { key: 'Name:', val: 'Non-official' },
        
      ]
    },
    // { title: 'Odisha Bhawan (Chennai)', bookingDetails:
    //  [{ key: 'Booking ID:', val: 'R435T6' }, { key: 'Booking Date:', val: '1.11.2022' }, { key: 'Purpose:', val: 'Non-official' }, { key: 'Conveyance:', val: 'No' }, { key: 'Booking Type:', val: 'Room' }, { key: 'Status:', val: 'Cancelled' }] },
    // { title: 'Odisha Bhawan (Mumbai)', bookingDetails: [{ key: 'Booking ID:', val: 'R435T3' }, { key: 'Booking Date:', val: '10.11.2022' }, { key: 'Purpose:', val: 'Non-official' }, { key: 'Conveyance:', val: 'No' }, { key: 'Booking Type:', val: 'Room' }, { key: 'Status:', val: 'Cancelled' }] },
    // { title: 'Odisha Bhawan (Delhi)', bookingDetails: [{ key: 'Booking ID:', val: 'R435T2' }, { key: 'Booking Date:', val: '25.11.2022' }, { key: 'Purpose:', val: 'Non-official' }, { key: 'Conveyance:', val: 'No' }, { key: 'Booking Type:', val: 'Room' }, { key: 'Status:', val: 'Cancelled' }] },
  ];
  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    { btnName: 'Delete', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
    // { btnName: 'Extend', btnIcon: 'documents-outline', color: '#FF7B01', background: '#FFF2CC' },
    // { btnName: 'Feedback', btnIcon: 'mail-outline', color: '#0FA958', background: '#DAFEFE' },

  ];
  public splitBtnClass=[
    { btnName: 'New Request', btnIcon: 'eye-outline' },
    { btnName: 'Moderation', btnIcon: 'eye-outline' },
    // { btnName: 'Cancel', btnIcon: 'eye-outline' },
    // { btnName: 'Modify', btnIcon: 'eye-outline' },
    

  ]
  constructor(public popoverController: PopoverController,
    private router: Router) { }

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
  getButtonName(btnName){
    if (btnName == 'New Request') {
      if (this.selctCity != '' && this.selctGH != '') {
        this.router.navigate(['after-login/pages/orgadmin/add'],{ queryParams: { Query_Name: 'current-booking'}});
      } else {
        alert('Select your city & Guest House');
      }
    }
  }
}
