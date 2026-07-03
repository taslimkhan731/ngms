import { Location } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { format, parseISO } from 'date-fns';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { nameValidator, mobileNumberValidator, emailValidator } from 'src/app/validators/custom-validators';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss'],
})
export class EditProfileComponent implements OnInit {
  @ViewChild('dateInput', { static: false }) dateInput: any;
  public profileUpdateForm: FormGroup;
  uploadDoc: any;
  login: any;
  displayName = ''
  mobileNo: any;
  dob: any;
  emailId: any;
  address: any;
  IdentityProof: any;
  gender = '';
  public profileData; 
  public fileTypeFlag = false;
  public fileSizeflag = false;
  public currentDate = new Date().toISOString().slice(0, 10);
  identiproofArray = [
    { 'id': 'Aadhar', 'name': 'Aadhar' },
    { 'id': 'Pan No', 'name': 'Pan Card' },
    { 'id': 'Passport', 'name': 'passport' },
    { 'id': 'DL', 'name': 'Driving Licene' },
    { 'id': 'Bank Passbook with photo attached', 'name': 'Bank Passbook with photo attached' },
    { 'id': 'Voter ID Card', 'name': 'Voter Id Card' },
    { 'id': 'Office ID Card with Photo', 'name': 'Office Id Card with Photo' },
  ]
  public isSubmitBtnClicked = false;
  docNmae: any;
  doc: string | ArrayBuffer;
  filename: any;
  proof: any;
  guestGender: any;
  documentName: any;
  documentFile: string | ArrayBuffer;
  constructor(
    private fb: FormBuilder,
    private mdmService: MdmService,
    private commonService: CommonService,
    private router: Router,
    private location: Location,
    public loadingController: LoadingController
  ) {
    this.createUpdateBooking();
  }

  ngOnInit() {
    this.useProfileDetails();
  }
  createUpdateBooking() {
    this.profileUpdateForm = this.fb.group({
      login: [''],
      displayName: ['', Validators.compose([Validators.required, nameValidator])],
      mobileNo: ['', Validators.compose([Validators.required, mobileNumberValidator])],
      dob: ['', Validators.required],
      emailId: ['', Validators.compose([ emailValidator])],
      residenceAddr: ['', Validators.required],
      gender: ['', Validators.required],
      identityProof: ['', Validators.required],
      docName: [null],

    })
  }
  handleUpload(event) {
    const reader = new FileReader();
    console.log("event is", event.target.files);
    if (event.target.files && event.target.files.length) {
      this.docNmae = event.target.files[0].name;
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        console.log("doc is", reader.result)
        this.doc = reader.result;
      };
    }
  }

  public changeDateFormat(date) {
    const parsedDate = parseISO(date);
    this.profileUpdateForm.get('dob').setValue(format(parsedDate, 'dd-MM-yyyy'))
    console.log("final date", this.profileUpdateForm.get('dob').value)
  }

  public changeDateFormatyymmdd(date) {
    if (date) {
      const objectDate = new Date(date.split('-').reverse().join('-'));
      const newDateString = objectDate.toISOString().slice(0, 10);
      return newDateString;
    }
  }

  public openDatepicker(name) {
    this[name].nativeElement.click();
  }
  public async useProfileDetails() {
    console.log("Inside get method")
    const loader = await this.loadingController.create({
      message: 'Please wait...'
    });
    loader.present();
    this.mdmService.getProfileDetails().subscribe(res => {
      if (res.status == 1) {
        loader.dismiss();
        console.log("data is", res.data);
        this.profileData = res.data
        this.profileUpdateForm.get('login').setValue(res.data.userId);
        console.log("data is", this.profileUpdateForm.get('login').value);
        this.profileUpdateForm.get('displayName').setValue(res.data.firstName);
        this.profileUpdateForm.get('mobileNo').setValue(res.data.contactNo);
        this.profileUpdateForm.get('dob').setValue(res.data.dateOfBirth);
        // this.profileUpdateForm.get('dob').setValue(res.data.dateOfBirth ? moment(res.data.dateOfBirth).format('DD-MM-YYYY') : '');
        this.profileUpdateForm.get('emailId').setValue(res?.data?.email);
        this.profileUpdateForm.get('residenceAddr').setValue(res.data.resAddr);
        this.profileUpdateForm.get('gender').setValue(res.data.guestGender);
        this.profileUpdateForm.get('identityProof').setValue(res.data.docType);
        this.documentName = res.data.profileDocumentName;
        this.documentFile = res.data.profileDocumentFile;
      } else {
        loader.dismiss();
      }
    }, err => {
      loader.dismiss();
    },
    ()=>{
      loader.dismiss()
    })
  }
  getProof(event) {
    console.log("get proof", event.target.value);
    this.IdentityProof = event.target.value
  }
  getGender(event) {
    console.log("get gender", event.target.value);
    this.gender = event.target.value
  }

  public OnselectNewFile(event) {
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
      this.documentName = event.target.files[0].name;
      this.documentFile = reader.result
    };
  }
  async editProfileForm(formValue) {
    this.isSubmitBtnClicked = true;
    const obj = {
      userType: this.profileData.userType,
      userId: formValue.login,
      name: formValue.displayName,
      mobile: formValue.mobileNo,
      email: formValue.emailId,
      resAddr: formValue.residenceAddr,
      guestGender: formValue.gender,
      dob: this.profileUpdateForm.get('dob').value,
      docType: formValue.identityProof,
      customFile: this.documentFile,
    }
    console.info(obj)
    if(this.fileTypeFlag) {
      this.commonService.showErrorToast('Only jpg, jpeg, png, pdf file types are allowed.');
      return;
    }

    if(this.fileSizeflag) {
      this.commonService.showErrorToast('File size exceeds the maximum limit of 2MB');
      return;
    }
    const loading = await this.loadingController.create({
      message: 'Please wait...'
    });
    loading.present();
    this.mdmService.updateProfile(obj).subscribe(res => {
      if (res.status == 1) {
        loading.dismiss();
        this.commonService.showSuccessToast(res.statusDesc);
        this.router.navigate(['/after-login/pages/orgadmin/profile/view-profile']);
      }
      else {
        loading.dismiss();
        this.commonService.showErrorToast(res.statusDesc);
      }
    })
  }



  public goBack() {
    this.location.back()
  }

//   saltKey: 
// userType: ORGADMIN
// userId: orgadmin
// name: Orgadmin
// email: prasad.sahoo@silicontechlab.com
// resAddr: bbsr
// guestGender: M
// dob: 02-10-2023
// docType: Pan No
// customFile: (binary)

}
