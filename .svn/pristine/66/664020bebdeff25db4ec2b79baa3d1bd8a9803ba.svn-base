import { Component, Input, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';


@Component({
  selector: 'app-termsandcondition',
  templateUrl: './termsandcondition.component.html',
  styleUrls: ['./termsandcondition.component.scss'],
})
export class TermsandconditionComponent implements OnInit {

  @Input() data: any;
  public inclExcl;
  constructor(
    private poppver: PopoverController
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    console.log(this.data)
    this.setData()
  }

  public setData() {
    if (this.data !== '04') {
      this.inclExcl = "The tariff will be inclusive of Electricity and Service charges.";
    } else {
      this.inclExcl = "The tariff will be exclusive of Electricity, AC and Service charges.";
    }
  }

  public dismiss() {
    this.poppver.dismiss()
  }


}
