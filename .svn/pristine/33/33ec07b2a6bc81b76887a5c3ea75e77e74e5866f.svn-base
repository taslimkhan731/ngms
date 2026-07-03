import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, LoadChildren, Router } from '@angular/router';
import { LoadingController, ModalController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { ExtendFormComponent } from './extend-form/extend-form.component';

@Component({
  selector: 'app-extend-reservation',
  templateUrl: './extend-reservation.component.html',
  styleUrls: ['./extend-reservation.component.scss'],
})
export class ExtendReservationComponent implements OnInit {

  public cId;
  public gId;
  public searchForm: FormGroup;
  public isSubmitBtnClicked = false;
  public showError = false;
  public showNoAvail = false;
  public responseData = [];
  public obj = {
    "cId": '',
    "gId": '',
    "bookingId": '',
    "name": '',
    "desg": "",
    "fromDate": ""
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private modal: ModalController,
    private commonService: CommonService,
    private loadingCtrl: LoadingController,
    private mdmService: MdmService,
    private location: Location,
    private fb: FormBuilder
  ) {
    this.createSearchForm();
  }

  private createSearchForm() {
    this.searchForm = this.fb.group({
      reqId: [''],
      name: [''],
      mob: [''],
    })
  }

  ngOnInit() { }

  ionViewWillEnter() {
    this.route.queryParams.subscribe((params) => {
      this.cId = params['city_id'];
      this.gId = params['gh_id'];
    });
  }

  public transformText(event: KeyboardEvent) {
    let text = (event.target as HTMLInputElement).value.toUpperCase()
    this.searchForm.get('reqId').setValue(text)
  }

  public async submit(data) {
    this.isSubmitBtnClicked = true;
    this.responseData = []
    this.obj.cId = this.cId;
    this.obj.gId = this.gId;
    this.obj.bookingId = this.searchForm.get('reqId').value;
    this.obj.name = this.searchForm.get('name').value;
    this.obj.desg = this.searchForm.get('mob').value;

    if ((this.searchForm.get('reqId').value == '' || this.searchForm.get('reqId').value == null) && (this.searchForm.get('name').value == '' || this.searchForm.get('name').value == null)&& (this.searchForm.get('mob').value == '' || this.searchForm.get('mob').value == null)) {
      this.showError = true;
      return;
    } else {
      this.showError = false;
    }

    // if (this.searchForm.valid) {
    const loading = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getGadRequestTableDataForExtension(this.obj).subscribe(
      res => {
        if (res.status == 1) {
          this.responseData = res.data;
          if (res.data.length == 0) {
            this.showNoAvail = true;
          } else {
            this.showNoAvail = false;
          }
          loading.dismiss();
        } else {
          this.commonService.showErrorToast(res.statusDesc)
          loading.dismiss();
        }
      },
      err => {
        loading.dismiss();
        this.commonService.showErrorToast("Something Went Wrong.")
      },
      ()=> {
        loading.dismiss();
      })
    // }

  }

  public async goToExtendForm(data) {
    const modal = await this.modal.create({
      component: ExtendFormComponent,
      componentProps: { data }
    });
    modal.onDidDismiss().then((data) => {
      this.goBack();
      console.log(data)
    })
    return await modal.present();
  }

  public goBack() {
    this.location.back();
  }

  public reset() {
    this.searchForm.reset();
    this.responseData = [];
    this.obj.cId = '';
    this.obj.gId = '';
    this.obj.bookingId = '';
    this.obj.name = '';
    this.isSubmitBtnClicked = false;
    this.showNoAvail = false;
  }

  public handleRefresh(event) { }
}
