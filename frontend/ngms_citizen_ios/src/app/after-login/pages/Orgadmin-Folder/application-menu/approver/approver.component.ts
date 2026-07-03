import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-approver',
  templateUrl: './approver.component.html',
  styleUrls: ['./approver.component.scss'],
})
export class ApproverComponent implements OnInit {
  selectedValue='Approve List';
  public splitBtnClass = [
    { btnName: 'Approve List', btnIcon: 'eye-outline', active: false },
    { btnName: 'Draft List', btnIcon: 'eye-outline', active: false },
    { btnName: 'Confirmed List', btnIcon: 'eye-outline', active: false },
  ]
  constructor(private router: Router,) { }

  ngOnInit() {}
  getButtonName(btnName) {
    this.selectedValue=btnName;
    console.log("button is",btnName);
    if(btnName=='Draft List')
    {
      this.router.navigateByUrl('after-login/pages/orgadmin/draft-list');
    }
    if(btnName=='Confirmed List')
    {
      this.router.navigateByUrl('after-login/pages/orgadmin/Confirm-list');
    }
  }

}
