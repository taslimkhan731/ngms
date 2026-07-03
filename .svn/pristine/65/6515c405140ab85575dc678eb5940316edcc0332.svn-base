import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PopoverController, AlertController, Platform, LoadingController, IonContent, ModalController } from '@ionic/angular';
import { differenceInDays, format, parse, parseISO } from 'date-fns';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CancelModalComponent } from './cancel-modal/cancel-modal.component';

@Component({
  selector: 'app-cancel-reservation',
  templateUrl: './cancel-reservation.component.html',
  styleUrls: ['./cancel-reservation.component.scss'],
})
export class CancelReservationComponent implements OnInit {

  backToTop: boolean = false;
  cancelReservationList: any;
  public cityId;
  public GhId;
  @ViewChild(IonContent, { static: true }) content: IonContent;

  constructor(
    private router: Router,
    public popoverController: PopoverController,
    private alertController: AlertController,
    private loadingController: LoadingController,
    private modalcontroller:ModalController,
    private route: ActivatedRoute,
    private platform: Platform,
    private commonService: CommonService,
    private mdmService: MdmService,) { }

  ngOnInit() {
 
  }

  ionViewWillEnter(){
    this.route.queryParams.subscribe((params) => {
      this.cityId = params['city_id'];
      this.GhId = params['ghId'];
      console.log("City id", this.cityId);
      console.log("GH id", this.GhId);
    });

    this.getcancelReservationList();
  }

  public async getcancelReservationList() {
    console.log("Inside getSplitView Method")
    let obj = {
      cId: this.cityId,
      gId: this.GhId
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getCancellationLstDtlsAccToReqForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.cancelReservationList = res.data;
          loading.dismiss();
        } else {
          loading.dismiss()
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong!!!")
      },
      ()=> {
        loading.dismiss()
      }
    )
  }

  public async cancelReservation(type,data){
    const modal = await this.modalcontroller.create({
      component: CancelModalComponent,
      componentProps: { type, data }
    });

    modal.onDidDismiss().then((data) => {
      this.getcancelReservationList()
    })
    return await modal.present();
  }

  public goBack() {
    this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry']);
  }

  handleRefresh(event) {
    setTimeout(() => {
      // this.getAllotmentList(this.obj)
      event.target.complete();
    }, 2000);
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  public formatTime(dateValue: string): string {
    const parsedDate = parseISO(dateValue);
    return format(parsedDate, 'dd-MM-yyyy');
  }

  public checkIfOneDay(data) {
    let frmDt = this.changeDateFormatyymmdd(data.fromDate.split(' ')[0])
    let toDt = this.changeDateFormatyymmdd(data.toDate.split(' ')[0])

    frmDt = this.formatTime(frmDt);
    toDt = this.formatTime(toDt);

    const parsedCancelFrmDt = parse(frmDt, 'dd-MM-yyyy', new Date());
    const parsedCancelToDt = parse(toDt, 'dd-MM-yyyy', new Date());
    const daysDifference = differenceInDays(parsedCancelToDt, parsedCancelFrmDt);
    console.log(daysDifference)
    return daysDifference;
  }

}
