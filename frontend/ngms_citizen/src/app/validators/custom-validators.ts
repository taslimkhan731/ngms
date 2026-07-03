// export class CustomValidators {
// }
import { FormControl, FormGroup } from '@angular/forms';

export function emailValidator(control: FormControl): { [key: string]: any } {
    const pattern = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidEmail: true };
    }
}



export function comboboxNumberValidator(
    control: FormControl
): { [key: string]: any } {
    console.log('Validation:', control.value);
    if (control.value === 0) {
        return { invalidField: true };
    }
}

export function ceNameValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_()\n@,/.$%&:' ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidCeName: true };
    }
}

export function comboboxValidator(
    control: FormControl
): { [key: string]: any } {
    const PATTERN = /^[A-Za-z-]*$/;
    const PATTERN1 = /^[0-9 -]*$/;
    // if (control.value === '') {
    //     return { invalidField: true };
    // }
    if (!PATTERN.test(control.value) && !PATTERN1.test(control.value)) {
        return { invalidField: true };
    }
}

export function comboboxStringValidator(control: FormControl): { [key: string]: any } {
    // console.log(control.value);
    if (control.value === 'NA') {
        return { invalidField: true };
    }
}

export function numberValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[0-9.]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidNumber: true };
    }
}
export function codeValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidCode: true };
    }
}
export function pacsNameValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidPacsName: true };
    }
}
export function nameValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidName: true };
    }
}

export function titleValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^(?![0-9_\@-]*$)^([0-9a-zA-Z _\@-]*)$/; // ^[a-zA-Z0-9_\-@ ]*$
    if (control.value && !pattern.test(control.value)) {
        return { invalidTitle: true };
    }
}

export function titleValidator2(control: FormControl): { [key: string]: any } {
    const pattern = /^([0-9a-zA-Z _\@-]*)$/; // ^[a-zA-Z0-9_\-@ ]*$
    if (control.value && !pattern.test(control.value)) {
        return { invalidTitle: true };
    }
}

export function userIdValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9 ]{5,20}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidUserId: true };
    }
}

export function addrValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_()\n@,/.$%&:' ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidAddr: true };
    }
}
export function licenseNoValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_()\&:/ ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidLicense: true };
    }
}
export function dateValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^((0[1-9]|[12]\d|3[01])-(0[1-9]|1[0-2])-[12]\d{3})$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidDate: true };
    }
}

export function linkValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_()&./' ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidLink: true };
    }
}
// add by Milan dt:-22.05.2022
// Password should be atleast 8 Characters which contains atleast 1 upper Character, 1 lower Character, 1 digit and 1 special Character
export function passwordValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidPassword: true };
    }
}

export function matchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
    return (group: FormGroup) => {
        const password = group.controls[passwordKey];
        const passwordConfirmation = group.controls[passwordConfirmationKey];
        if ((passwordConfirmation.value !== '') && (password.value !== passwordConfirmation.value)) {
            return passwordConfirmation.setErrors({ mismatchedPasswords: true });
        }
    };
}
// added by Amrita
// Date : 3/08/2018
export function resourceNameValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_&/ ]{2,50}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidResourceName: true };
    }
}
export function descValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_()\n@,/.$%&:' ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidDesc: true };
    }
}

export function urlValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9-_()@.&/' ]*$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidUrl: true };
    }
}
// added by Prachi
// Date : 3/08/2018
export function roleNameValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[a-zA-Z0-9- _& ]{2,50}$/;
    // const pattern = /^[a-zA-Z0-9-_]{6,20}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidRoleName: true };
    }

}
// added by Amrita on date: 27-08-2018  ^((\+)?(\d{2}[-]))?(\d{10}){1}?$

export function mobileNumberValidator(control: FormControl): { [key: string]: any } {

  // const pattern =/^(\+\d{1,3}[- ]?)?\d{10}$/;
    // if (control.value && !pattern.test(control.value)) {
    //     return { invalidMobileNumber: true };
    // }

    const pattern = /^[6-9]{1}[0-9]{9}$|^0[0-9]{9}$/; // Updated pattern by sagar
    if (control.value && !pattern.test(control.value)) {
      return { invalidMobileNumber: true };
    }
  }
export function panCardValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[A-Z]{5}\d{4}[A-Z]{1}?$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidPanNumber: true };
    }
}

export function aadharCardValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^((\+)?(\d{2}[-]))?(\d{12}){1}?$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidAadharNumber: true };
    }
}

export function bankAccNoValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[0-9][0-9]{8,17}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidAccountNumber: true };
    }
}

export function gstinNoValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^([0][1-9]|[1-2][0-9]|[3][0-5])([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9a-zA-Z]{1}[zZ]{1}[0-9a-zA-Z]{1})+$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidGstinNumber: true };
    }
}
export function httpUrlValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidUrl: true };
    }
}
export function nullValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^/;
    if (control.value && !pattern.test(control.value)) {
        return { invalid: true };
    }
}

export function numberComparator(firstNum: string, secondNum: string) {
    return (group: FormGroup) => {
        const num1 = group.controls[firstNum];
        const num2 = group.controls[secondNum];
        if (Number(num1.value) > Number(num2.value)) {
            return num1.setErrors({ isGreaterThan: true });
        } else {
            return null;
        }
    };
}
export function emailPasswordValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!*])[A-Za-z\d$@$!*]{0,20}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidPassword: true };
    }
}
export function pinNumberValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[0-9]{5}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidPinNumber: true };
    }
}

//Added by Dipti on date: 20-04-2022
export function landlineNumberValidation(control: FormControl): { [key: string]: any } {
    const pattern = /^[0-9]{4}[-][0-9]{6}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidLandlineNumber: true };
    }
}
//added By Santanu Sritam on date: 03/05/2022 for checking two alternative fields should'nt contain same values
export function matchingAlternativeFields(primaryOne: string, alternativeOne: string) {
    return (group: FormGroup) => {
        const primary = group.get(primaryOne);
        const alternative = group.get(alternativeOne);
        if ((alternative.value !== '') && (alternative.value === primary.value)) {
            // console.log('error')
            return alternative.setErrors({ identicalValueError: true });
        }
        return null;
    };
}
//added By Santanu Sritam on date: 03/05/2022
export function voterIdValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^([a-zA-Z]){3}([0-9]){7}?$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidVoterIdNumber: true };
    }
}
//added By Santanu Sritam on date: 03/05/2022
export function drivinglicenceValidator(control: FormControl): { [key: string]: any } {
    const pattern = /(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidDrivingLicenceNumber: true };
    }
}
//added By Santanu Sritam on date: 03/05/2022
export function passportValidator(control: FormControl): { [key: string]: any } {
    const pattern = /[A-Z]{1}[1-9]{1}[0-9]{5}[1-9]{1}/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidPassportNumber: true };
    }
}
//added By Santanu Sritam on date: 03/05/2022 for format yyyy-mm-dd
export function dateFormatValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/;
    console.log(control.value)
    if (control.value && !pattern.test(control.value)) {
        return { invalidDate: true };
    }
}
//Added by Dipti on date: 09-05-2022
export function excelFileFormatValidator(control: FormControl): { [key: string]: any } {
    const allowedExcelFileFormat = /(\.xlsx|\.xls|\.csv)$/;
    console.log(control.value)
    if (!allowedExcelFileFormat.exec(control.value)) {
        return { invalidExcelFileFormat: true };
    }
}
//added By Santanu Sritam on date: 16/05/2022
export function tanValidator(control: FormControl): { [key: string]: any } {
    const pattern = /[A-Z]{5}[0-9]{4}[A-Z]{1}/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidTanNumber: true };
    }
}
//Added by Dipti on date: 20-04-2022
export function updatedLandlineValidator(control: FormControl): { [key: string]: any } {
    const pattern = /^[0-9]{4}[-][0-9]{6,7}$/;
    if (control.value && !pattern.test(control.value)) {
        return { invalidLandlineNumber: true };
    }
}
/* export function unitValidator(unit: string, quant: string) {
    console.log(unit, quant);
    // if (this.isCalled) {
    return (group: FormGroup) => {
      const unitVal = group.controls[unit];
      console.log('this.phaseVal********', unitVal, this.phaseVal);
      if (Number(unitVal.parent.value.unit) >= Number(quant)) {
        console.log('quant', quant);
        console.log('(unitVal.parent.value >= quant', (unitVal.parent.value >= quant));
        return unitVal.setErrors({ invalid: true });
      }
    };
    // }
  } */



