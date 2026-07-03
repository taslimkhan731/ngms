import { Component, OnInit } from '@angular/core';
import { AlertController, LoadingController, PopoverController } from '@ionic/angular';
import { FilterPageComponent } from '../../../filter-page/filter-page.component';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-occupancy-chart',
  templateUrl: './occupancy-chart.component.html',
  styleUrls: ['./occupancy-chart.component.scss'],
})
export class OccupancyChartComponent implements OnInit {
  selectedValue = '';
  selectGH = ''
  selectCity = ''
  headingName = ''
  public occList;
  getAllCityList = [];

  public splitBtnClass = [
    { btnName: 'Occupancy', btnIcon: 'eye-outline' },
    // { btnName: 'Print PDF', btnIcon: 'eye-outline' },
  ]
  guestHouseList: any;
  constructor(
    private popoverController: PopoverController,
    private loadingController: LoadingController,
    private mdmService: MdmService,
    private datePipe: DatePipe,
    private alertController: AlertController,
    private commonservice: CommonService) { }

  ngOnInit() { }

  ionViewWillEnter() {
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
  }

  getCityList() {
    this.mdmService.getCityDataTableForMobile().subscribe(res => {
      console.log(res);
      if (res.status === 1) {
        this.getAllCityList = res.data;
      }
    }, error => {
      console.log(error);
    })
  }

  async getGuestHouseList(event) {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.selectGH = ''
    console.log("city name is", event.target.value);
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(
      res => {
        console.log("result is", res.data);
        this.guestHouseList = res.data;
        if (this.guestHouseList.length == 1) {
          console.log("Inside IF Condition")
          console.log(res.data)
          this.selectGH = res.data[0].id;
        }
        loading.dismiss();
      },
      err => {
        loading.dismiss()
      },
      () => {
        loading.dismiss()
      })
  }
  getBhawanName(event) {
    console.log("gh name", event.target.value);
  }
  async getButtonName(btn) {
    if (this.selectCity === '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
    }
    else if (this.selectGH === '') {
      const alert = await this.alertController.create({
        message: 'Please Select City.',
        buttons: ['OK'],
      });
      await alert.present();
    }
    else {
      this.selectedValue = btn.btnName;
      this.headingName = this.selectedValue;
      this.getOccupancyForMobile()
    }
  }

  public async getOccupancyForMobile() {
    let obj = {
      "cId": this.selectCity,
      "gId": this.selectGH
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getOccupancyForMobile(obj).subscribe(
      res => {
        this.occList = res.data
        loading.dismiss()
      },
      err => {
        loading.dismiss();
      }, () => {
        loading.dismiss()
      })
  }

  public formatDate(checkInDt, toDt) {
    let checkInDate = this.datePipe.transform(checkInDt.split(' ')[0], 'dd.MM.yyyy');
    let toDate = this.datePipe.transform(toDt.split(' ')[0], 'dd.MM.yyyy');
    return checkInDate + " - " + toDate
  }

  public setExpectedArrDetails(data) {
    // let exfromDate = this.datePipe.transform(data.exfromDate.split(' ')[0], 'dd.MM.yyyy');
    // let exToDate = this.datePipe.transform(data.exToDate.split(' ')[0], 'dd.MM.yyyy');
    if (data.roomType == 'R' && data.expectType == 'BG') {
      return data.exName + " (" + data.exfromDate + " - " + data.exToDate + ")" + " (1 Bed)";
    }
    if (data.expectType == 'R') {
      if (data.exRoom == '1') {
        return data.exName + " (" + data.exfromDate + " - " + data.exToDate + ")" + " (" + data.exRoom + " Room)";
      } else {
        return data.exName + " (" + data.exfromDate + " - " + data.exToDate + ")" + " (" + data.exRoom + " Rooms)";
      }
    } else {
      if (data.exNob == '1') {
        return data.exName + " (" + data.exfromDate + " - " + data.exToDate + ")" + " (" + data.exNob + " Bed)";
      } else {
        return data.exName + " (" + data.exfromDate + " - " + data.exToDate + ")" + " (" + data.exNob + " Beds)";
      }
    }
  }

  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: FilterPageComponent,
      cssClass: 'my-custom-class',
      event: ev,
      translucent: true,
    });
    await popover.present();
  }
}
