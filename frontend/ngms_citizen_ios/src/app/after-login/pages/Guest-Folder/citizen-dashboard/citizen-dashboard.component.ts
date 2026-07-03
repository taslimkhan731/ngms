import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-citizen-dashboard',
  templateUrl: './citizen-dashboard.component.html',
  styleUrls: ['./citizen-dashboard.component.scss'],
})
export class CitizenDashboardComponent implements OnInit {
  headerToolbar = 'Dashboard'
  dashboard = []
  constructor(private router: Router,
    private mdmService: MdmService,
    private commonService: CommonService,
    public loadingController: LoadingController
  ) {


  }

  ngOnInit() {
    this.guestHouseDetails();
  }

  public async guestHouseDetails() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getGuestHouse().subscribe(res => {
      if (res.status == 1) {
        loading.dismiss();
        this.dashboard = res.data
      }
      else {
        loading.dismiss()
        this.commonService.showErrorToast(res.statusDesc);
      }
    }, err=> {
      loading.dismiss();
      this.commonService.showErrorToast("Something Went Wrong.")
    })
  }

  public goToBooking(bhawanDetails) {
    this.router.navigate(['after-login/pages/guest/create-booking'], { queryParams: { bhawan_Id: bhawanDetails.ghId, bhawan_Name: bhawanDetails.ghName, cityId: bhawanDetails.cityId } });
  }
}
