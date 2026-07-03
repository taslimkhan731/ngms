import { Component, OnInit } from '@angular/core';
import { LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.scss'],
})
export class ViewProfileComponent implements OnInit {
  profileData: any;
  contactNo: any;
  name: any;
  emailId: any;
  gender: any;
  dob: any;
  identyProof: any;
  residenceAddrs: any;
  docName: any;
  doc: any;

  constructor( private mdmService: MdmService,
    public loadingController: LoadingController,
    public commonService: CommonService,) { }

  ngOnInit() {
    this.useProfileDetails()
  }

  async useProfileDetails() {
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getProfileDetails().subscribe(res => {
      if (res.status == 1) {
        this.profileData=res.data;
        this.contactNo= this.profileData.contactNo;
        this.name= this.profileData.firstName;
        this.emailId= this.profileData.email;
        this.gender= this.profileData.genderDesc;
        this.dob= this.profileData.dateOfBirth;
        this.identyProof= this.profileData.docType;
        this.residenceAddrs=this.profileData.resAddr;
        this.docName=this.profileData.profileDocumentName;
        this.doc=this.profileData.profileDocumentFile
        console.log("profile details", this.profileData);
        loading.dismiss();
      }
      else{
        loading.dismiss();
        this.commonService.showErrorToast(res.statusDesc);
      }
    },
    err=>{
      loading.dismiss();
    })
  }

}
