import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';

@Component({
  selector: 'app-extention-checkin',
  templateUrl: './extention-checkin.component.html',
  styleUrls: ['./extention-checkin.component.scss'],
})
export class ExtentionCheckinComponent implements OnInit {
  selctGH = ''
  selctCity = ''
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Requested ID:', val: 'R435T5' },
        { key: 'From Date:', val: '30.11.2022' },
        { key: 'To Date:', val: '30.11.2022' },
        { key: 'Rooms:', val: '1039-R' },
      ],guestList:[
        { key:'Subrat Kumar Sarangi'},
        { key:'Krushna Ch Panigrahi'},

      ]
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Requested ID:', val: 'R435T5' },
        { key: 'From Date:', val: '30.11.2022' },
        { key: 'To Date:', val: '30.11.2022' },
        { key: 'Rooms:', val: '1039-R' },
        { key: 'Conveyance:', val: 'No' },
      ],guestList:[
        { key:'Subrat Kumar Sarangi'},
        { key:'Krushna Ch Panigrahi'},

      ]
    },
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Requested ID:', val: 'R435T5' },
        { key: 'From Date:', val: '30.11.2022' },
        { key: 'To Date:', val: '30.11.2022' },
        { key: 'Rooms:', val: '1039-R' },
        { key: 'Conveyance:', val: 'No' },
      ],
      guestList:[
        { key:'Subrat Kumar Sarangi'},
        { key:'Krushna Ch Panigrahi'},

      ]
    },
   
  ];
  public btnData = [
    { btnName: 'Extention', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
  ];
  public splitBtnClass = [
    { btnName: 'Extention Checkin', btnIcon: 'eye-outline', active: false },
  ]
  constructor(private router: Router,
    public popoverController: PopoverController) { }

  ngOnInit() {}
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
        this.router.navigateByUrl('after-login/pages/orgadmin/add');
      } else {
        alert('Select your city & Guest House');
      }
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
