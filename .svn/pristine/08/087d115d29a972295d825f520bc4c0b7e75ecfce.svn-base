import { HttpParams } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InfiniteScrollCustomEvent, IonContent, IonModal, LoadingController, Platform, PopoverController } from '@ionic/angular';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';

@Component({
  selector: 'app-split-reservation',
  templateUrl: './split-reservation.component.html',
  styleUrls: ['./split-reservation.component.scss'],
})
export class SplitReservationComponent implements OnInit {
  @ViewChild(IonContent, { static: true }) content: IonContent;
  public cityId;
  public GhId;
  public cardData = []
  public splitViewData = []
  public selectedGuest = []
  private isFirstApiHit = true;
  public pageSize = 5;
  public pageNum = 1;
  public totalPages;
  backToTop: boolean = false;
  @ViewChild('filtermodal') filtermodal: IonModal;
  filterForm: FormGroup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public popoverController: PopoverController,
    public loadingController: LoadingController,
    private platform: Platform,
    private fb: FormBuilder,
    private commonService: CommonService,
    private mdmService: MdmService,) {
    this.createFormInstance();
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.cityId = params['city_id'];
      this.GhId = params['ghId'];
      console.log("City id", this.cityId);
      console.log("GH id", this.GhId);
    });
  }

  ionViewWillEnter() {
    this.getSplitViewData()
  }

  onIonInfinite(ev) {
    this.pageSize = 5;
    this.pageNum = this.pageNum + 1;
    this.getSplitViewData()
    setTimeout(() => {
      (ev as InfiniteScrollCustomEvent).target.complete();
    }, 1500);
  }

  ionViewDidLeave() {
    this.pageSize = 5;
    this.pageNum = 1;
    this.splitViewData = [];
    this.isFirstApiHit = true;
  }


  public async getSplitViewData() {
    console.log("Inside getSplitView Method")
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    if (this.isFirstApiHit) {
      loading.present();
    }
    let obj = {
      city: this.cityId,
      gh: this.GhId,
      pageNo: this.pageNum,
      pageSize: this.pageSize,
      bookingIdForSplit: this.filterForm.get('bookingId').value ? this.filterForm.get('bookingId').value : undefined,
      nameForSplit: undefined
    }
    this.mdmService.splitViewForMobile(obj).subscribe(
      res => {
        if (res.status == 1) {
          this.splitViewData = this.splitViewData.concat(res.data);
          if (this.isFirstApiHit) {
            loading.dismiss();
            this.isFirstApiHit = false;
          }
          console.log(res)
        } else if (res.status == 0) {
          // this.commonService.showErrorToast("No Details Found!!")
          if (this.isFirstApiHit) {
            loading.dismiss();
          }
        }

      },
      err => {
        if (this.isFirstApiHit) {
          loading.dismiss();
        }
        this.commonService.showErrorToast("Something Went Wrong!!")
      },
      () => {
        loading.dismiss()
      }
    )
  }

  public selectGuest(guestId, bookingId, event) {
    if (event.target.checked) {
      this.selectedGuest.push({ guestId: guestId, bookingId: bookingId });
    }

    if (!event.target.checked) {
      this.selectedGuest.forEach((ele, ind) => {
        console.log(ele)
        if (ele.guestId == guestId) {
          this.selectedGuest.splice(ind, 1);
        }
      })
    }
    console.log(this.selectedGuest.map(x => x).join(","))
  }

  public async splitGuest(bookingId) {
    if (this.selectedGuest.length <= 0) {
      this.commonService.showErrorToast("Please select guest to split.")
      return;
    } else if (this.selectedGuest.length >= 1) {
      let finalGuestIds = [];
      this.selectedGuest.forEach(el => {
        console.log(el.bookingId == bookingId, el, bookingId)
        if (el.bookingId == bookingId) {
          finalGuestIds.push(el.guestId)
        }
      })

      if (finalGuestIds.length <= 0) {
        this.commonService.showErrorToast("Please select guest to split.")
        return
      }

      let selectedGuest;
      selectedGuest = "'" + finalGuestIds.join("', '") + "'"
      console.log(selectedGuest)
      const params = new HttpParams()
        .set('bookingId', bookingId)
        .set('guestList', selectedGuest);
      const loading = await this.loadingController.create({
        message: 'Please wait...'
      });
      loading.present();
      this.mdmService.getSplitBookingDetailsForMobile(params).subscribe(
        res => {
          if (res.status == 1) {
            console.log(res.data)
            loading.dismiss();
            // this.commonService.storeDetails(res.data)
            localStorage.removeItem("bookingDetails");
            localStorage.setItem("bookingDetails", JSON.stringify(res.data))
            this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry/updateSplit']);

            // this.commonService.showSuccessToast("statusDesc")
          } else if (res.status == 0) {
            this.commonService.showErrorToast(res.statusDesc)
            loading.dismiss();
          }
        },
        err => {
          this.commonService.showErrorToast("Something Went Wrong!!")
          loading.dismiss();
        },
        () => {
          this.selectedGuest = []
          loading.dismiss();
        }
      )
    }
  }

  public goBack() {
    this.router.navigate(['after-login/pages/orgadmin/application-menu/application-entry']);
  }

  gotToTop() {
    this.content.scrollToTop(1000);
  }

  getScrollPos(data) {
    if (data.detail.scrollTop > this.platform.height()) {
      this.backToTop = true;
    } else {
      this.backToTop = false;
    }
  }

  public openFilterModal() {
    this.pageSize = 5;
    this.pageNum = 1;
    this.splitViewData = [];
    this.isFirstApiHit = true;
    this.createFormInstance();
    this.filtermodal.isOpen = true;
  }

  public createFormInstance() {
    this.filterForm = this.fb.group({
      bookingId: [''],
    })
  }

  public async submitFilterForm(data) {
    console.log(data);
    if (data.bookingId === '') {
      this.commonService.showErrorToast('Provide Booking ID');
      return;
    }
   await this.getSplitViewData();
   this.filtermodal.isOpen = false;
  }
  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.filterForm.get('bookingId').setValue(text)
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }
  handleRefresh(event) {
    setTimeout(() => {
      this.pageSize = 5;
      this.pageNum = 1;
      this.splitViewData = [];
      this.isFirstApiHit = true;
      this.filterForm.get('bookingId').reset();
      this.getSplitViewData();
      this.isFirstApiHit = true
      event.target.complete();
    }, 2000);
  }

}
