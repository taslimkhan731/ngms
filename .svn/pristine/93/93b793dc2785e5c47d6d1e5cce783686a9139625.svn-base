import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from 'src/app/after-login/pages/filter-page/filter-page.component';

@Component({
  selector: 'app-draft-list',
  templateUrl: './draft-list.component.html',
  styleUrls: ['./draft-list.component.scss'],
})
export class DraftListComponent implements OnInit {
  selctGH = ''
  selctCity = ''
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Approved ID:', val: 'R435T5' },
        { key: 'Created Date:', val: '30.11.2022' },
        { key: 'Status:', val: 'Temporary Confirm' },
      ]
    },
    { title: 'Odisha Bhawan (Chennai)', 
    bookingDetails: [
      { key: 'Approved ID:', val: 'R435T5' },
      { key: 'Created Date:', val: '30.11.2022' },
      { key: 'Status:', val: 'Temporary Confirm' },
    ]
   },
    { title: 'Odisha Bhawan (Mumbai)',
    bookingDetails: [
      { key: 'Approved ID:', val: 'R435T5' },
      { key: 'Created Date:', val: '30.11.2022' },
      { key: 'Status:', val: 'Temporary Confirm' },
    ]
         },
    { title: 'Odisha Bhawan (Delhi)', 
    bookingDetails: [
      { key: 'Approved ID:', val: 'R435T5' },
      { key: 'Created Date:', val: '30.11.2022' },
      { key: 'Status:', val: 'Temporary Confirm' },
    ]
     },
  ];
  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
    { btnName: 'Confirm', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
    { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  ];
  selectedValue='Draft List';
  public splitBtnClass = [
    { btnName: 'Approve List', btnIcon: 'eye-outline', active: false },
    { btnName: 'Draft List', btnIcon: 'eye-outline', active: false },
    { btnName: 'Confirmed List', btnIcon: 'eye-outline', active: false },
  ]

  constructor(private router: Router,
    public popoverController: PopoverController) { }

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
  getButtonName(btnName) {
    this.selectedValue=btnName;
    console.log("button is",btnName);
    if(btnName=='Approve List')
    {
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    }
    if(btnName=='Confirmed List')
    {
      this.router.navigateByUrl('after-login/pages/orgadmin/Confirm-list');
    }
  }
}
