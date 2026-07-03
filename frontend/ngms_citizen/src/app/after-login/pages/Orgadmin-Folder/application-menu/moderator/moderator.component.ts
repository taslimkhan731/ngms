import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrls: ['./moderator.component.scss'],
})
export class ModeratorComponent implements OnInit {
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Booking ID:', val: 'R435T5' },
        { key: 'Booking Date:', val: '30.11.2022' },
        { key: 'Purpose:', val: 'Non-official' },
        { key: 'Conveyance:', val: 'No' },
        { key: 'Booking Type:', val: 'Room' },
        { key: 'Status:', val: 'Cancelled' }
      ]
    },
    { title: 'Odisha Bhawan (Chennai)', bookingDetails: [{ key: 'Booking ID:', val: 'R435T6' }, { key: 'Booking Date:', val: '1.11.2022' }, { key: 'Purpose:', val: 'Non-official' }, { key: 'Conveyance:', val: 'No' }, { key: 'Booking Type:', val: 'Room' }, { key: 'Status:', val: 'Cancelled' }] },
    { title: 'Odisha Bhawan (Mumbai)', bookingDetails: [{ key: 'Booking ID:', val: 'R435T3' }, { key: 'Booking Date:', val: '10.11.2022' }, { key: 'Purpose:', val: 'Non-official' }, { key: 'Conveyance:', val: 'No' }, { key: 'Booking Type:', val: 'Room' }, { key: 'Status:', val: 'Cancelled' }] },
    { title: 'Odisha Bhawan (Delhi)', bookingDetails: [{ key: 'Booking ID:', val: 'R435T2' }, { key: 'Booking Date:', val: '25.11.2022' }, { key: 'Purpose:', val: 'Non-official' }, { key: 'Conveyance:', val: 'No' }, { key: 'Booking Type:', val: 'Room' }, { key: 'Status:', val: 'Cancelled' }] },
  ];
  public btnData = [
    { btnName: 'Save', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    // { btnName: 'Delete', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    // { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
    // { btnName: 'Extend', btnIcon: 'documents-outline', color: '#FF7B01', background: '#FFF2CC' },
    // { btnName: 'Feedback', btnIcon: 'mail-outline', color: '#0FA958', background: '#DAFEFE' },

  ];
  public splitBtnClass=[
    { btnName: 'Moderator', btnIcon: 'eye-outline', active:false },
    { btnName: 'Edit Moderator', btnIcon: 'eye-outline', active:false },
    { btnName: 'Cancel', btnIcon: 'eye-outline', active:false },
  ]
  selectedValue='Moderator';
  headingName='Moderator';
  constructor( public popoverController: PopoverController) { }

  ngOnInit() {}
  getButtonName(btn){
    this.selectedValue=btn.btnName;
    this.headingName=this.selectedValue;
    console.log("select", this.selectedValue);
  }
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
