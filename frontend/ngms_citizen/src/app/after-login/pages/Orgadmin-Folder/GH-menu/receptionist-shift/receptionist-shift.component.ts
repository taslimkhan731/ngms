import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-receptionist-shift',
  templateUrl: './receptionist-shift.component.html',
  styleUrls: ['./receptionist-shift.component.scss'],
})
export class ReceptionistShiftComponent implements OnInit {
  selectedValue='Add Shift';
  public splitBtnClass = [
    { btnName: 'Add Shift', btnIcon: 'eye-outline', active: false },
    { btnName: 'Change Shift', btnIcon: 'eye-outline', active: false },
    { btnName: 'Close Shift', btnIcon: 'eye-outline', active: false },
    { btnName: 'Report', btnIcon: 'eye-outline', active: false },
  ]
  constructor() { }

  ngOnInit() {}
  getButtonName(btnName) {
    this.selectedValue=btnName;
  }


}
