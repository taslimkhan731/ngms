import { Injectable } from '@angular/core';
import { Location } from '@angular/common';

@Injectable({
    providedIn: 'root'
})
export class UtilsService {

    constructor(
        private location: Location
    ) { }    

    public goBack() {
        this.location.back();
    }

    public checkFormControlValidity(isSubmitBtnClicked, formGroup, formControlName) {
        // console.log(formGroup, formControlName);
        if (
            (isSubmitBtnClicked && formGroup.controls[formControlName].invalid) ||
            (formGroup.controls[formControlName].invalid && (formGroup.controls[formControlName].touched || formGroup.controls[formControlName].dirty))
        ) {
            return true;
        } else {
            return false;
        }
    }

    public goCurrentLocPath(): string {
        return this.location.path();
    }
    // USED TO GET UPLOADED FILE PATH
    public getUploadedFileName(filePath: string) {
        if (typeof filePath !== 'undefined') {
            const FILE_ARY = filePath.split(/(\\|\/)/);
            return FILE_ARY[FILE_ARY.length - 1];
        } else {
            return '';
        }
    }

    // ONLY NUMBERS
    public checkIsNumber(event) {
        const y = Number(event.key);
        const x = event.which || event.keycode;
        if (x === 32) {
            return false;
        } else if (!isNaN(y)) {
            return true;
        } else {
            if (x === 8 || (x >= 35 && x <= 40) || x === 46) {
                return true;
            } else {
                return false;
            }
        }
    }
    // ONLY DECIMAL VALUES ALLOWED
    public checkIsDecimal(event) {
        const isShiftKey = event.shiftKey;
        const y = Number(event.key);
        const x = event.which || event.keycode;
        if (x === 32) {
            return false;
        } else if (!isNaN(y)) {
            return true;
        } else {
            if (x === 8 || (x >= 35 && x <= 40) || x === 46 || (x === 190 && !isShiftKey)) {
                return true;
            } else {
                return false;
            }
        }
    }
    // used to get financial year according to months
    public getCurrentFinancialYear() {
        const date = new Date();
        const CURR_MONTH = date.getUTCMonth();
        if (CURR_MONTH > 2) {
            const CURR_YR = date.getUTCFullYear();
            const NEXT_YR = (date.getUTCFullYear() + 1).toString().substr(-2);
            return (CURR_YR + '-' + NEXT_YR);
        } else {
            const CURR_YR = date.getUTCFullYear().toString().substr(-2);
            const PRE_YR = date.getUTCFullYear() - 1;
            return (PRE_YR + '-' + CURR_YR);
        }
    }

    public removeEmptyObjectsFromArray(data: object[]) {
        const NON_EMPTY_ARRAY = [];
        // console.log('sending data ', data);
        data.forEach(item => {
            const OBJECT_KEYS = Object.keys(item);
            // console.log('Object Keys ', OBJECT_KEYS);
            let valueCounter = 0;
            OBJECT_KEYS.forEach(Key => {
                if (item[Key] === null) {
                    valueCounter++;
                } else if (item[Key] === 0) {
                    valueCounter++;
                } else if (item[Key] === false) {
                    valueCounter++;
                }
            });
            // console.log('valuecounter ', valueCounter, OBJECT_KEYS.length);
            if (valueCounter !== OBJECT_KEYS.length) {
                NON_EMPTY_ARRAY.push(item);
            }
        });
        return NON_EMPTY_ARRAY;
    }

    public isFilevalidForUpload(uFile: File, fileType: number, minFileSize?: number, maxFileSize?: number) { // FILE, FILE TYPE(20, 30, 40, 50), In MB, In MB
        console.log(uFile, uFile.name, uFile.size, uFile.type);
        let isValid = false;
        const MIME_TYPE = uFile.type;
        const FILE_EXTENSION = uFile.name.toUpperCase().split('.');

        // Double Extension
        console.log('Double Extension Check');
        if (FILE_EXTENSION.length !== 2) { return this.showFileUploadErrorMessage('Upload a Valid File', uFile.name); }
        // Extension
        console.log('Extension Check');
        switch (fileType) {
            case 20: // IMG
                if (FILE_EXTENSION[1] === 'JPEG' || FILE_EXTENSION[1] === 'JPG') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a Valid File', uFile.name); }
                break;
            case 30: // IMG || PDF
                if (FILE_EXTENSION[1] === 'JPEG' || FILE_EXTENSION[1] === 'JPG' || FILE_EXTENSION[1] === 'PDF') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a Valid File', uFile.name); }
                break;
            case 40: // XLSX
                if (FILE_EXTENSION[1] === 'XLSX' || FILE_EXTENSION[1] === 'XLS') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a Valid File', uFile.name); }
                break;
            case 50: // PDF
                if (FILE_EXTENSION[1] === 'PDF') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a Valid File', uFile.name); }
                break;
            case 60: // PDF,XLSX
                if (FILE_EXTENSION[1] === 'PDF' || FILE_EXTENSION[1] === 'DOCX' || FILE_EXTENSION[1] === 'DOC' || FILE_EXTENSION[1] === 'XLSX' || FILE_EXTENSION[1] === 'XLS') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a Valid File', uFile.name); }
                break;
        }
        // Size
        if (maxFileSize !== undefined) {
            console.log('Max file size Check');
            if (uFile.size < maxFileSize * 1000 * 1024) {
                isValid = true;
            } else {
                return this.showFileUploadErrorMessage('File must be less than ' + maxFileSize + 'mb', uFile.name);
            }
        }
        if (minFileSize !== undefined) {
            console.log('Min file size Check');
            if (uFile.size > minFileSize * 1024) {
                isValid = true;
            } else {
                return this.showFileUploadErrorMessage('File must be greater than ' + minFileSize + 'kb', uFile.name);
            }
        }
        // MIME type
        console.log('MIME type check');
        switch (fileType) {
            case 20: // IMG
                if (MIME_TYPE === 'image/jpeg' || MIME_TYPE === 'image/jpg') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a valid file', uFile.name); }
                break;
            case 30: // IMG || PDF
                if (MIME_TYPE === 'image/jpeg' || MIME_TYPE === 'image/jpg' || MIME_TYPE === 'application/pdf') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a valid file', uFile.name); }
                break;
            case 40: // XLSX
                if (MIME_TYPE === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' || MIME_TYPE === 'application/vnd.ms-excel') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a valid file', uFile.name); }
                break;
            case 50: // PDF
                if (MIME_TYPE === 'application/pdf') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a valid file', uFile.name); }
                break;
            case 60: // PDF,XLSX
                if (MIME_TYPE === 'application/pdf' || MIME_TYPE === 'application/msword' || MIME_TYPE === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' || MIME_TYPE === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' || MIME_TYPE === 'application/vnd.ms-excel') { isValid = true; } else { return this.showFileUploadErrorMessage('Upload a valid file', uFile.name); }
                break;
        }
        return isValid;
    }

    private showFileUploadErrorMessage(message, fileName) {
        console.log(fileName + ' file not valid');
        return false;
    }

    private dateParse(data: Date): string {
        console.log(data, typeof data);
        const DATE: string = (data.getDate() < 9 ? '0' + data.getDate() : data.getDate()) + '-' + (data.getMonth() <= 8 ? '0' + (data.getMonth() + 1) : (data.getMonth() + 1)) + '-' + (data.getFullYear());
        return DATE;
    }

    private dateFormat(data: string): Date {
        const DATE_ARR = data.split('-');
        const DATE = new Date(DATE_ARR[1] + '/' + DATE_ARR[0] + '/' + DATE_ARR[2]);
        return DATE;
    }

    public displayFormData(formdata) {
        // formData.append('formFile', String(this.documentFile));
        // for (const index of formData.entries()) {
        //   console.log('keyyyyyyyy', index, formData[index]);
        // }
        new Response(formdata).text().then(console.log);
    }


}
