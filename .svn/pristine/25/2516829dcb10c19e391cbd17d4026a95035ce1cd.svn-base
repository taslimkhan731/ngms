import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';

@Component({
  selector: 'app-occupancy-chart',
  templateUrl: './occupancy-chart.component.html',
  styleUrls: ['./occupancy-chart.component.scss'],
})
export class OccupancyChartComponent implements OnInit {
  public splitBtnClass=[
    { btnName: 'Occupancy', btnIcon: 'eye-outline' },
  ]
  constructor(public popoverController: PopoverController) { }

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
}
