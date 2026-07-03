import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';

@Component({
  selector: 'app-application-entry',
  templateUrl: './application-entry.component.html',
  styleUrls: ['./application-entry.component.scss'],
})
export class ApplicationEntryComponent implements OnInit {
  selctGH = ''
  selctCity = ''
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
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Edit', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    { btnName: 'Delete', btnIcon: 'print-outline', color: '#BA18C5', background: '#F8E7FA' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },

  ];
  public splitBtnClass = [
    { btnName: 'Add', btnIcon: 'eye-outline', active: false },
    { btnName: 'Split', btnIcon: 'eye-outline', active: false },
    { btnName: 'Cancel', btnIcon: 'eye-outline', active: false },
  ]
  constructor(private router: Router,
    public popoverController: PopoverController,) { }

  ngOnInit() { }
  getbutton(button, bhawanName) {
    console.log("button type", button, bhawanName);
    if (button == 'Edit') {
      this.router.navigate(['after-login/pages/orgadmin/edit'], { queryParams: { bhawan_Name: bhawanName } });
    }
    else if (button == 'View') {
      this.router.navigate(['after-login/pages/orgadmin/view'], { queryParams: { bhawan_Name: bhawanName } });
    }
  }
  getButtonName(btnName) {
    if (btnName == 'Add') {
      if (this.selctCity != '' && this.selctGH != '') {
        // this.router.navigateByUrl('after-login/pages/orgadmin/add');
        this.router.navigate(['after-login/pages/orgadmin/add'],{ queryParams: { Query_Name: 'application-entry'}});
      } else {
        alert('Select your city & Guest House');
      }
    }
    if(btnName == 'Split'){
      this.router.navigateByUrl('after-login/pages/orgadmin/split');
    }
    if (btnName == 'Cancel') {
      if (this.selctCity != '' && this.selctGH != '') { 
      }
      else{
        alert('Select your city & Guest House');
      }
    }
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
