import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { FilterPageComponent } from 'src/app/after-login/pages/filter-page/filter-page.component';
import { Location } from '@angular/common';
import { CommonService } from 'src/app/Services/common/common.service';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-draft-list',
  templateUrl: './draft-list.component.html',
  styleUrls: ['./draft-list.component.scss'],
})
export class DraftListComponent implements OnInit {

  listType = 'Allotment List';
  allotmentList = [
    { id: 1, isChecked: false, bookingId: 100001, reqDate: '13-07-2023', frmDt: '13-07-2023', toDt: '15-07-2023', catType: 'abc' },
    { id: 2, isChecked: false, bookingId: 100002, reqDate: '13-07-2023', frmDt: '13-07-2023', toDt: '15-07-2023', catType: 'abc' },
    { id: 3, isChecked: false, bookingId: 100003, reqDate: '13-07-2023', frmDt: '13-07-2023', toDt: '15-07-2023', catType: 'abc' }
  ];
  conveyanceList = [];
  modifiedList = [];
  canceList = [];
  conferenceList = [];

  checkedAllotmentList = [];
  checkedConveyanceList = [];
  checkedModifiedList = [];
  checkedCanceList = [];
  checkedConferenceList = [];

  public allChecked = false;
  public myform: FormGroup;


  // selctGH = ''
  // selctCity = ''
  // public cardData = [
  //   {
  //     title: 'Odisha Bhawan (Delhi)',
  //     bookingDetails: [
  //       { key: 'Approved ID:', val: 'R435T5' },
  //       { key: 'Created Date:', val: '30.11.2022' },
  //       { key: 'Status:', val: 'Temporary Confirm' },
  //     ]
  //   },
  //   {
  //     title: 'Odisha Bhawan (Chennai)',
  //     bookingDetails: [
  //       { key: 'Approved ID:', val: 'R435T5' },
  //       { key: 'Created Date:', val: '30.11.2022' },
  //       { key: 'Status:', val: 'Temporary Confirm' },
  //     ]
  //   },
  //   {
  //     title: 'Odisha Bhawan (Mumbai)',
  //     bookingDetails: [
  //       { key: 'Approved ID:', val: 'R435T5' },
  //       { key: 'Created Date:', val: '30.11.2022' },
  //       { key: 'Status:', val: 'Temporary Confirm' },
  //     ]
  //   },
  //   {
  //     title: 'Odisha Bhawan (Delhi)',
  //     bookingDetails: [
  //       { key: 'Approved ID:', val: 'R435T5' },
  //       { key: 'Created Date:', val: '30.11.2022' },
  //       { key: 'Status:', val: 'Temporary Confirm' },
  //     ]
  //   },
  // ];
  // public btnData = [
  //   { btnName: 'View', btnIcon: 'eye-outline', color: '#0078FF', background: '#E1EDFF' },
  //   { btnName: 'Confirm', btnIcon: 'pencil-outline', color: '#0AA0A0', background: '#DAFEFE' },
  //   { btnName: 'Cancel', btnIcon: 'close-outline', color: '#E91E34', background: '#FFE2E5' },
  // ];
  // selectedValue = 'Draft List';
  // public splitBtnClass = [
  //   { btnName: 'Approve List', btnIcon: 'eye-outline', active: false },
  //   { btnName: 'Draft List', btnIcon: 'eye-outline', active: false },
  //   { btnName: 'Confirmed List', btnIcon: 'eye-outline', active: false },
  // ]

  constructor(private router: Router,
    public popoverController: PopoverController,
    private location: Location,
    private commonService: CommonService,
    private mdmService: MdmService,
    private fb: FormBuilder) {
    this.createFormInstance();
  }

  private createFormInstance() {
    this.myform = this.fb.group({
      isChecked: []
    })
  }

  ngOnInit() {
    console.log('inside ngOnInit', this.commonService.getStoredDetails());
    const obj = this.commonService.getStoredDetails();
    if (obj?.btnName === 'Allotment List') {
      this.listType = obj?.btnName;
      this.getApproveList(obj);
    } else if (obj?.btnName === 'Conveyance List') {
      this.listType = obj?.btnName;
      this.getConveyanceList(obj);
    } else if (obj?.btnName === 'Modified List') {
      this.listType = obj?.btnName;
      this.getmodifiedList(obj);
    } else if (obj?.btnName === 'Cancel List') {
      this.listType = obj?.btnName;
      this.getCancelList(obj);
    } else if (obj?.btnName === 'Conference List') {
      this.listType = obj?.btnName;
    }
  }

  // getListByBtnName(btnName) {
  //   if (btnName === 'Allotment List') { }
  // }

  getApproveList(obj) {
    console.log(obj);
    // this.mdmService.getApproveListDetails().subscribe(res => {
    //   if (res.status === 1) {
    //     this.allotmentList = res.data;
    //   }
    // }, error => {
    //   console.log(error);
    // })
  }

  getConveyanceList(obj) {
    console.log(obj);
    // this.mdmService.getConvayanceListDetails(this.selctCity, this.selctGH).subscribe(res => {
    //   if (res.status === 1) {
    //     this.conveyanceList = res.data;
    //   }
    // }, error => {
    //   console.log(error);
    // })
  }

  getmodifiedList(obj) {
    console.log(obj);
    // const obj = {
    //   cId: this.selctCity,
    //   gId: this.selctGH
    // }
    // this.mdmService.getModifiedListDetails(obj).subscribe(res => {
    //   if (res.status === 1) {
    //     this.modifiedList = res.data;
    //   }
    // }, error => {
    //   console.log(error);
    // })
  }

  getCancelList(obj) {
    console.log(obj);
    // const obj = {
    //   cId: this.selctCity,
    //   gId: this.selctGH
    // }
    // this.mdmService.getCancelListDetails(obj).subscribe(res => {
    //   if (res.status === 1) {
    //     this.canceList = res.data;
    //   }
    // }, error => {
    //   console.log(error);
    // })
  }

  // async presentPopover(ev: any) {
  //   const popover = await this.popoverController.create({
  //     component: FilterPageComponent,
  //     cssClass: 'my-custom-class',
  //     event: ev,
  //     translucent: true,
  //   });
  //   await popover.present();
  // }
  // getButtonName(btnName) {
  //   this.selectedValue = btnName;
  //   console.log("button is", btnName);
  //   if (btnName == 'Approve List') {
  //     this.router.navigateByUrl('after-login/pages/orgadmin/approver');
  //   }
  //   if (btnName == 'Confirmed List') {
  //     this.router.navigateByUrl('after-login/pages/orgadmin/Confirm-list');
  //   }
  // }

  goToBackPage() {
    this.location.back();
  }

  checkAll(event) {
    this.allChecked = !this.allChecked;
    this.myform.get('isChecked').setValue(this.allChecked);
    if (this.listType === 'Allotment List') {
      this.clearFromArray(this.checkedAllotmentList);
      if (event.target.checked) {
        for (const obj of this.allotmentList) {
          this.checkedAllotmentList.push(obj);
        }
      }
    } else if (this.listType === 'Conveyance List') {
      this.clearFromArray(this.checkedConveyanceList);
      if (event.target.checked) {
        for (const obj of this.conveyanceList) {
          this.checkedConveyanceList.push(obj);
        }
      }
    } else if (this.listType === 'Modified List') {
      this.clearFromArray(this.checkedModifiedList);
      if (event.target.checked) {
        for (const obj of this.modifiedList) {
          this.checkedModifiedList.push(obj);
        }
      }
    } else if (this.listType === 'Cancel List') {
      this.clearFromArray(this.checkedCanceList);
      if (event.target.checked) {
        for (const obj of this.canceList) {
          this.checkedCanceList.push(obj);
        }
      }
    } else if (this.listType === 'Conference List') {
      this.clearFromArray(this.checkedConferenceList);
      if (event.target.checked) {
        for (const obj of this.conferenceList) {
          this.checkedConferenceList.push(obj);
        }
      }
    }
  }

  clearFromArray(arrayName: any) {
    if (arrayName) {
      arrayName.splice(0, arrayName.length);
    }
  }

  OnCheckedOne(item, event) {
    if (event.target.checked) {
      this.checkedAllotmentList.push(item);
    }

    if (!event.target.checked) {
      this.checkedAllotmentList.forEach((ele, ind) => {
        if (ele.id == item.id) {
          this.checkedAllotmentList.splice(ind, 1);
        }
      })
    }
  }

  onClickSelectBtn() {
    if (this.listType === 'Allotment List') {
      localStorage.setItem('checkedAllotmentList', JSON.stringify(this.checkedAllotmentList))
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    } else if (this.listType === 'Conveyance List') {
      localStorage.setItem('checkedConveyanceList', JSON.stringify(this.checkedConveyanceList))
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    } else if (this.listType === 'Modified List') {
      localStorage.setItem('checkedModifiedList', JSON.stringify(this.checkedModifiedList))
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    } else if (this.listType === 'Cancel List') {
      localStorage.setItem('checkedCanceList', JSON.stringify(this.checkedCanceList))
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    } else if (this.listType === 'Conference List') {
      localStorage.setItem('checkedConferenceList', JSON.stringify(this.checkedConferenceList))
      this.router.navigateByUrl('after-login/pages/orgadmin/approver');
    }
  }
}
