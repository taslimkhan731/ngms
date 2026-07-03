import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MdmService } from 'src/app/Services/MDM/mdm.service';
import { CommonService } from 'src/app/Services/common/common.service';
import { EncryptionService } from 'src/app/Services/encryption/encryption.service';
import { matchingPasswords, passwordValidator } from 'src/app/validators/custom-validators';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss'],
})
export class ChangePasswordComponent implements OnInit {
  changePasswordForm: FormGroup;
  isSubmitBtnClicked = false;
  public showError = false;
  constructor(
    private fb: FormBuilder,
    public encryptService: EncryptionService,
    private mdmService: MdmService,
    private commonService: CommonService,
    private router: Router,
  ) {
    this.createChangepasswordForm()
  }

  ngOnInit() {

  }
  createChangepasswordForm() {
    this.changePasswordForm = this.fb.group({
      old_password: [, Validators.required],
      password: [, Validators.compose([Validators.required,passwordValidator])],
      cpassword: [, Validators.compose([Validators.required])]
    }, {
      validator: matchingPasswords('password', 'cpassword')
    })
  }
  changePassword(formValue) {
    // if (formValue.password !== formValue.cpassword) {
    //   this.showError = true;
    // } else {
    //   this.showError = false
    // }
    this.isSubmitBtnClicked = true;
    const obj = {
      // password:this.encryptService.encodePassword(this.changePasswordForm.get('password').value, 1),
      oldPassword: this.encryptService.encodePassword(formValue.old_password, 1),
      password: this.encryptService.encodePassword(formValue.password, 1),
      confPassword: this.encryptService.encodePassword(formValue.cpassword, 1)
    }
    if (this.changePasswordForm.valid) {
      this.mdmService.changePassword(obj).subscribe(res => {
        if (res.status == 1) {
          this.commonService.showSuccessToast(res.statusDesc);
          this.changePasswordForm.reset();
          this.router.navigate(['/before-login-landing/login']);
        }
        else {
          this.commonService.showErrorToast(res.statusDesc);
        }
      })
    }

  }

}
