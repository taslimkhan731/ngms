import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
  constructor(private formFB: FormBuilder,
    private mdmService: MdmService,
    private commonService: CommonService,
    private router: Router,) {
    this.createFirstLogin();
  }

  ngOnInit() {
  }
  createFirstLogin() {
    this.firstLogin = this.formFB.group({
      selectDocType: ['', Validators.compose([Validators.required])],
      uploadDoc: ['',, Validators.compose([Validators.required])]
    })
  }
  handleUpload(event) {
    const reader = new FileReader();
    console.log("event is", event.target.files);
    if (event.target.files && event.target.files.length) {
      this.filename = event.target.files[0].name;
      console.log("event is", this.filename);
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        console.log("doc is", reader.result)
        this.doc = reader.result;
      };
    }
  }
  FirstLoginDoc(formValue) {
    const obj = {
      "docType": formValue.selectDocType,
      'userType': 'GUEST',
      'supportDocumentName': this.filename,
      'supportDocumentFile': this.doc,

    }
    this.mdmService.firstLoginDoc(obj).subscribe(res => {
      if (res.status == 1) {
        this.commonService.showSuccessToast(res.statusDesc);
        this.router.navigate(['/after-login']);
      }
      else{
        this.commonService.showErrorToast(res.statusDesc);
      }
    })
  }
}
