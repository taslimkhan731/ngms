import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehicle-allotment',
  templateUrl: './vehicle-allotment.component.html',
  styleUrls: ['./vehicle-allotment.component.scss'],
})
export class VehicleAllotmentComponent implements OnInit {
  public cardData = [
    {
      title: 'Odisha Bhawan (Delhi)',
      bookingDetails: [
        { key: 'Requested ID:', val: 'R435T5' },
      ],
      guestList: [
        { key: 'Guest Name ', val: 'Subrat Kumar Sarangi' },
        { key: 'Gender ', val: 'Male' },
        { key: 'Department  ', val: 'Govt.' },
        { key: 'Designation  ', val: 'Officer' },
      ],
      vehicleRequestDetailsFrom: [
        { key: 'Mode of Travel  ', val: 'Flight' },
        { key: 'Train/Flight Number  ', val: '1234' },
        { key: 'Arrival Place', val: 'bbsr' },
        { key: 'Arrival Time', val: '11-05-2020 10:10' },
      ],
      vehicleRequestDetailsTo:[
        { key: 'Mode of Travel  ', val: 'Flight' },
        { key: 'Train/Flight Number  ', val: '1234' },
        { key: 'Arrival Place', val: 'bbsr' },
        { key: 'Arrival Time', val: '11-05-2020 10:10' },
      ]

    },
   
  ];
  public btnData = [
    { btnName: 'Add Allotment', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
  ];
  constructor() { }

  ngOnInit() { }
  getbutton(button, bhawanName) {

  }

}
