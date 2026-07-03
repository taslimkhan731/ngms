import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from 'src/app/after-login/pages/filter-page/filter-page.component';

@Component({
  selector: 'app-confirm-list',
  templateUrl: './confirm-list.component.html',
  styleUrls: ['./confirm-list.component.scss'],
})
export class ConfirmListComponent implements OnInit {
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Approved ID:', val: 'R435T5' },
        { key: 'Created Date:', val: '30.11.2022' },
        { key: 'Created By:', val: 'Orgadmin' },
      ]
    },
    {
      title: 'Odisha Bhawan (Chennai)',
      bookingDetails: [
        { key: 'Approved ID:', val: 'R435T5' },
        { key: 'Created Date:', val: '30.11.2022' },
        { key: 'Created By:', val: 'Orgadmin' },
      ]
    },
    {
      title: 'Odisha Bhawan (Mumbai)',
      bookingDetails: [
        { key: 'Approved ID:', val: 'R435T5' },
        { key: 'Created Date:', val: '30.11.2022' },
        { key: 'Created By:', val: 'Orgadmin' },
      ]
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Approved ID:', val: 'R435T5' },
        { key: 'Created Date:', val: '30.11.2022' },
        { key: 'Created By:', val: 'Orgadmin' },
      ]
    },
  ];
  public btnData = [
    { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
  ];
  selectedValue = 'Confirmed List';
  public splitBtnClass = [
    { btnName: 'Approve List', btnIcon: 'eye-outline', active: false },
    { btnName: 'Draft List', btnIcon: 'eye-outline', active: false },
    { btnName: 'Confirmed List', btnIcon: 'eye-outline', active: false },
  ]
  constructor(public popoverController: PopoverController,
    private router: Router,) { }

  ngOnInit() { }
  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
  }
  getButtonName(btnName) {
    this.selectedValue = btnName;
    console.log("button is", btnName);
    if (btnName == 'Approve List') {
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    }
    if (btnName == 'Draft List') {
      this.router.navigateByUrl('after-login/pages/orgadmin/draft-list');
    }
  }

}
