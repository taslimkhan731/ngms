import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';

@Component({
  selector: 'app-first-login',
  templateUrl: './first-login.component.html',
  styleUrls: ['./first-login.component.scss'],
})
export class FirstLoginComponent implements OnInit {
  public firstLogin: FormGroup;
  mobileNo: any;
  userId: any;
  filename: any;
  doc: string | ArrayBuffer;
  public fileTypeFlag = false;
  public fileSizeflag = false;
  constructor(private formFB: FormBuilder,
    private mdmService: MdmService,
    private commonService: CommonService,
    private loadingController: LoadingController,
    private router: Router,) 
    {
    this.createFirstLogin();
  }

  ngOnInit() {
  }
  createFirstLogin() {
    this.firstLogin = this.formFB.group({
      selectDocType: ['', Validators.required],
      uploadDoc: ['', Validators.compose([Validators.required])]
    })
  }
  handleUpload(event) {
    // const reader = new FileReader();
    // console.log("event is", event.target.files);
    // if (event.target.files && event.target.files.length) {
    //   this.filename = event.target.files[0].name;
    //   console.log("event is", this.filename);
    //   const [file] = event.target.files;
    //   reader.readAsDataURL(file);
    //   reader.onload = () => {
    //     console.log("doc is", reader.result)
    //     this.doc = reader.result;
    //   };
    // }

      const allowedExtensions = ['jpg', 'jpeg', 'png', 'pdf'];
    
      const file = event.target.files[0];
      const fileName = file.name;
      const fileExtension = fileName.split('.').pop().toLowerCase();
      const fileSizeMB = file.size / (1024 * 1024);
    
      if (!allowedExtensions.includes(fileExtension)) {
        this.fileTypeFlag = true;
        this.commonService.showErrorToast('Only jpg, jpeg, png, pdf file types are allowed.');
        return;
      } else {
        this.fileTypeFlag = false;
      }

      if (fileSizeMB > 2) {
        this.commonService.showErrorToast('File size exceeds the maximum limit of 2MB');
        this.fileSizeflag = true;
        return;
      } else {
        this.fileSizeflag = false;
      }
    
      const reader = new FileReader();
      reader.readAsDataURL(file);
    
      reader.onload = () => {
        this.filename = fileName;
        this.doc = reader.result;
        console.log('File uploaded:', this.filename);
      };
  }
  async FirstLoginDoc(formValue) {
    const obj = {
      "docType": formValue.selectDocType,
      'userType': 'GUEST',
      'supportDocumentName': this.filename,
      'supportDocumentFile': this.doc,

    }
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });

    if(this.fileTypeFlag) {
      this.commonService.showErrorToast('Only jpg, jpeg, png, pdf file types are allowed.');
      return;
    }

    if(this.fileSizeflag) {
      this.commonService.showErrorToast('File size exceeds the maximum limit of 2MB');
      return;
    }
    loader.present();
    this.mdmService.firstLoginDoc(obj).subscribe(res => {
      if (res.status == 1) {
        loader.dismiss();
        this.commonService.showSuccessToast("Succesfully Submitted.");
        this.router.navigate(['/after-login']);
      }
      else{
        loader.dismiss();
        this.commonService.showErrorToast(res.statusDesc);
      }
    },
    err=> {
      loader.dismiss();
      this.commonService.showErrorToast("Something Went Wrong.");
    },
    ()=>{
      loader.dismiss()
    })
  }
}
