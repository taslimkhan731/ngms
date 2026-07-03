import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController, ModalController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { Location } from '@angular/common';
import { DocViewerComponent } from 'src/app/shared/doc-viewer/doc-viewer.component';
import { Filesystem, Directory, FilesystemDirectory } from '@capacitor/filesystem';
import { FileOpener, FileOpenerPlugin } from '@capawesome-team/capacitor-file-opener';

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
  public path: string | undefined;
  public base64String: string | undefined;
  public copyToAppDirectory: boolean = false;

  constructor(
    private router:Router,
    private mdmService: MdmService,
    private commonService: CommonService,
    public loadingController: LoadingController,
    private modalcontroller:ModalController,
    private location:Location,
  ) { 
  }

  ngOnInit() {
    // this.useProfileDetails();
  }

  ionViewWillEnter(){
    this.useProfileDetails();
  }
  public async useProfileDetails(){
    console.log("Inside get profile details method")
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.getProfileDetails().subscribe(res => {
      if(res.status==1){
        loading.dismiss();
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
      }
      else{
        loading.dismiss();
        this.commonService.showErrorToast(res.statusDesc);
      }
     
    },
    err=>{
      loading.dismiss();
    }
    )
  }

  goTo(){
    // routerLink="/after-login/pages/guest/profile/edit-profile" 
    this.router.navigate(['/after-login/pages/guest/profile/edit-profile']);
  }
  public async writeFile(base64String, name): Promise<void> {
    this.path = undefined;
    await Filesystem.requestPermissions();
    if (base64String && name) {
      await Filesystem.writeFile({
        data: base64String,
        path: name,
        directory: Directory.External,
      });
      const { uri } = await Filesystem.getUri({
        path: name,
        directory: Directory.External,
      });
      this.path = uri;
    }
    this.openFile();
  }

  public async openFile(): Promise<void> {
    if (!this.path) {
      return;
    }
    await FileOpener.openFile({ path: this.path });
    console.log('File opened');
  }


  async view(){
    // if (this.doc.split(';')[0].split('/')[1] == 'pdf') {
      //this.downloadAndDisplayPdf(data,'example.pdf')
      this.writeFile(this.doc, this.docName + '.' + this.doc.split(';')[0].split('/')[1]);
      
    // } else {
    //   const modal = await this.modalcontroller.create({
    //     component: DocViewerComponent,
    //     componentProps: { data: this.doc }
    //   });
    //   modal.onDidDismiss().then((data) => {
    //   })
    //   return await modal.present();
    // }
  }

  public goBack(){
    this.location.back()
  }
 
}
