import { Component, OnInit, ViewChild } from "@angular/core";
import { IonModal, LoadingController, ModalController, Platform } from "@ionic/angular";
import { MdmService } from "src/app/Services/MDM/mdm.service";
import { CloseShiftComponent } from "./close-shift/close-shift.component";
import { FormBuilder, FormGroup } from "@angular/forms";
import { CommonService } from "src/app/Services/common/common.service";

@Component({
  selector: "app-shift",
  templateUrl: "./shift.component.html",
  styleUrls: ["./shift.component.scss"],
})
export class ShiftComponent implements OnInit {

  @ViewChild("filtermodal") filtermodal: IonModal;

  filterForm: FormGroup;
  constructor(
    private mdmService: MdmService,
    private modalcontroller: ModalController,
    private loadingController: LoadingController,
    private fb: FormBuilder,
    private platform: Platform,
    private commonservice: CommonService,

  ) {
    this.platform.backButton.subscribeWithPriority(9999, (processNextHandler) => {
      document.addEventListener("backbutton", function (event) {
        event.preventDefault(); event.stopPropagation();
      }, false);
      this.filtermodal.isOpen = false;
      processNextHandler();
    });
  }
  public obj = {
    cId: "",
    gId: "",
    createdDate: "",
    createdBy: ""
  };
  public cId: string = '';
  public gId: string = '';
  public createdBy: string = '';
  public createdDate: string = '';
  public guestHouseShiftDetails: any;
  guestHouseList: any;
  selectGH = '';
  selectCity = '';
  public getAllCityList = [];
  public city: string;
  public guestHouse: string;
  ngOnInit() { this.getShiftDetails(); }
  ionViewWillEnter() {
    this.getShiftDetails();
    // this.getCityList();
    let details = localStorage.getItem('dataFromToken');
    console.log(JSON.parse(details))
    this.getAllCityList = JSON.parse(details).location;
    console.log(this.getAllCityList)
  }
  getGuestHouseList(event) {
    const selectedCity = this.getAllCityList.find(city => city.cityId === event.target.value);
    if (selectedCity) {
      const cityName = selectedCity.cityName;
      this.city = cityName;
      this.cId = event.target.value;
    }
    console.log("city name is", event.target.value);
    this.selectGH = ''
    this.mdmService.getCityWiseGUestHouse(event.target.value).subscribe(res => {
      console.log("result is", res);
      this.guestHouseList = res.data;
      if (this.guestHouseList.length == 1) {
        console.log("Inside IF Condition")
        console.log(res.data)
        this.selectGH = res.data[0].id;
      } else {
        this.getShiftDetails();
      }
    })
  }
  getBhawanName(event) {
    const selectedGhouse = this.guestHouseList.find(gHouse => gHouse.id === event.target.value);
    if (selectedGhouse) {
      const gHouseName = selectedGhouse.name;
      this.guestHouse = gHouseName;
    }
    console.log("gh name", event.target.value);
    console.log(this.guestHouse);
    this.gId = event.target.value;
    this.getShiftDetails();
  }
  async getShiftDetails() {
    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.getShiftDetails(this.cId, this.gId, this.createdBy, this.createdDate).subscribe(
      (res) => {
        loading.dismiss();
        console.log(res);
        this.guestHouseShiftDetails = res.data;
      },
      (err) => {
        loading.dismiss();
      },
      () => {
        loading.dismiss()
      }
    );
  }
  async closeShift(id) {
    const modal = await this.modalcontroller.create({
      component: CloseShiftComponent,
      componentProps: { id }
    });
    modal.onDidDismiss().then(() => {
      this.getShiftDetails();
    });
    return await modal.present();
  }


  //filter


  public changeDateFormat(date) {
    const dateComponents = date.split("-");
    const year = dateComponents[0];
    const month = dateComponents[1];
    const day = dateComponents[2];
    const formattedDate = `${day}-${month}-${year}`;
    return formattedDate;

  }
  public openFilterModal() {
    this.createFormInstance();
    this.filtermodal.isOpen = true;
  }


  public createFormInstance() {
    this.filterForm = this.fb.group({
      cId: [""],
      gId: [""],
      createdBy: [""],
      createdDate: [""],
    });
  }
  public cancel() {
    this.filtermodal.isOpen = false;
  }

  public async submitFilterForm(data) {
    console.log(data);
    if (
      data.createdBy === "" &&
      data.cId === "" &&
      data.gId === "" &&
      data.createdDate === ""
    ) {
      this.commonservice.showErrorToast("Provide any field for filter");
      return;
    }
    if (data.createdDate) {
      data.createdDate = this.changeDateFormat(data.createdDate);
    }


    this.obj.cId = this.selectCity,
      this.obj.gId = this.selectGH,
      this.obj.createdDate = data.createdDate,
      this.obj.createdBy = data.createdBy

    const loading = await this.loadingController.create({
      message: "Please wait...",
    });
    loading.present();
    this.mdmService.getShiftDetails(this.obj.cId, this.obj.gId, this.obj.createdBy, this.obj.createdDate).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 1) {
          loading.dismiss();
          this.guestHouseShiftDetails = res.data;
          this.filtermodal.isOpen = false;
        } else {
          loading.dismiss()
        }
      },
      (error) => {
        loading.dismiss();
      },
      () => {
        loading.dismiss()
      }
    );
  }
  filterHandleRefresh(event) {
    setTimeout(() => {
      this.createdDate = "",
        this.createdBy = "",
        this.obj.cId = "",
        this.obj.gId = "",
        this.guestHouseShiftDetails = []
      this.getShiftDetails()
      event.target.complete();
    }, 2000);
  }


}
