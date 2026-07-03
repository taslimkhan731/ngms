import { Injectable, OnDestroy } from '@angular/core';
import { EncryptionService } from './../encryption/encryption.service';

@Injectable({
    providedIn: 'root'
})
export class StorageService implements OnDestroy {
    private AppStorage: object = {};
    private accessDetailsLocalStorage: object = {
        "user": {
            "id": 1,
            "userName": "orgadmin",
            "title": "CEO",
            "firstName": "vicky",
            "lastName": "gupta",
            "mobileNo": "7894561234",
            "emailId": "orgadmin@gmail.com",
            "roleId": 1,
            "statusId": 1,
            "createdOn": null,
            "createdBy": "admin",
            "updatedOn": "22/3/22 4:36 PM",
            "updatedBy": "orgadmin",
            "profilePic": null
        },
        "roleName": "ORGADMIN",
        "roleCode": "ROLE_ORGADMIN",
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvcmdhZG1pbiIsInJvbGUiOiJST0xFX09SR0FETUlOIiwidGtuVHlwZSI6IkFDQ0VTU19UT0tFTiIsImlhdCI6MTY0ODAxMzIyNSwiZXhwIjoxNjQ4MzczMjI1fQ.88IGFLfp49oQtHPdXrkCoUCdw1GyqqW8YZWhNsLLnA0PeCRXZVeiKYaKKIES9Vks_BuluXHKkwXFRvNBn2q-oQ",
        "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvcmdhZG1pbiIsInJvbGUiOiJST0xFX09SR0FETUlOIiwidGtuVHlwZSI6IlJFRlJFU0hfVE9LRU4iLCJpYXQiOjE2NDgwMTMyMjUsImV4cCI6MTY0ODAyMDQyNX0.f9NEFM0Ij02OjQZBpycU2yMz1U3jWi38at-ZRpFiTmQe3F0wLOzfsFu5saNYpMXN1Lr3PM9BuulbYpSoxyJ8kg",
        "status": "success"
    };

    constructor(
        private encryptService: EncryptionService
    ) {
        this.checkIsAlreadyAvailable();
    }

    private checkIsAlreadyAvailable() {
        if (sessionStorage.getItem('ANGULARSESSIONID')) {
            this.updateSessionStorage(JSON.parse(this.encryptService.decodeTotalSession(sessionStorage.getItem('ANGULARSESSIONID'))));
        } else {
            this.createNewSessionDetails();
        }
    }

    private createNewSessionDetails() {
        const DATA = {
            'id': this.encryptService.generateSalt(20),
            'name': 'Storing Details Locally'
        };
        this.setSessionStorage(DATA);
    }
    private getSessionStorageDetails() {
        this.checkIsAlreadyAvailable();
        this.AppStorage = JSON.parse(this.encryptService.decodeTotalSession(sessionStorage.getItem('ANGULARSESSIONID')));
    }
    private setSessionStorage(data) {
        // console.log(JSON.stringify(data), this.encryptService.encodeTotalSession(JSON.stringify(data)));
        sessionStorage.setItem('ANGULARSESSIONID', this.encryptService.encodeTotalSession(JSON.stringify(data)));
    }
    private updateSessionStorage(data) {
        sessionStorage.removeItem('ANGULARSESSIONID');
        // console.log(data);
        this.setSessionStorage(data);
    }
    public removeSessionStorageDetails() {
        if (sessionStorage.getItem('ANGULARSESSIONID')) {
            sessionStorage.removeItem('ANGULARSESSIONID');
        }
    }

    private getSessionId() {
        this.getSessionStorageDetails();
        return String(this.AppStorage['id']);
    }

    setSessionAttribute(attrName: string, data: any) {
        this.getSessionStorageDetails();
        const DATA = {}; DATA[attrName] = this.encryptService.encodeKeyValue(JSON.stringify(data), this.getSessionId());
        console.log(this.AppStorage, DATA);
        Object.assign(this.AppStorage, DATA);
        this.updateSessionStorage(data);
    }

    getSessionAttribute(attrName: string) {
        this.getSessionStorageDetails();
        if (this.AppStorage[attrName] !== undefined) {
            return JSON.parse(this.encryptService.decodeKeyValue(this.AppStorage[attrName], this.getSessionId()));
        } else {
            return null;
        }
    }

    removeSessionAttribute(attrName: string) {
        this.getSessionStorageDetails();
        delete this.AppStorage[attrName];
        this.updateSessionStorage(this.AppStorage);
    }


    /////////////////////////////////////////////// LOCAL STORAGE //////////////////////////////////////////////
    /////////////////////////////////////////////// LOCAL STORAGE //////////////////////////////////////////////

    public removeIfExistsLocalStorageByAttribute(attrName) {
        if (localStorage.getItem[attrName]) {
            localStorage.removeItem(attrName);
        }
    }
    public clearAllLocalStorage() {
        localStorage.clear();
    }
    public setLocalStorageByAttribute(attrName: string, value: any) {
        this.removeIfExistsLocalStorageByAttribute(attrName);
        localStorage.setItem(attrName, JSON.stringify(value));
    }
    public getLocalStorageByAttribute(attrName: string) {
        // console.log('getLocalStorageByAttribute', localStorage.getItem(attrName));
        return JSON.parse(localStorage.getItem(attrName));
    }

    public getRoleFromLocalStorage() {
        return this.getAccessDetailsFromLocalStorage('roleCode');
    }
    public getUserDataFromLocalStorage() {
        return this.getAccessDetailsFromLocalStorage('user');
    }
    public getTokenFromLocalStorage() {
        return this.getAccessDetailsFromLocalStorage('token');
    }
    public getAccessDetailsFromLocalStorage(parameter?: string) {
        const ACCESS_TOKEN_DETAILS = JSON.parse(localStorage.getItem('accessTokenDetails'));
        // console.log('getAccessDetailsFromLocalStorage', ACCESS_TOKEN_DETAILS, parameter);
        if (ACCESS_TOKEN_DETAILS && parameter && ACCESS_TOKEN_DETAILS[parameter]) {
            return ACCESS_TOKEN_DETAILS[parameter];
        } else {
            return ACCESS_TOKEN_DETAILS;
        }
    }
    /////////////////////////////////////////////// LOCAL STORAGE //////////////////////////////////////////////
    /////////////////////////////////////////////// LOCAL STORAGE //////////////////////////////////////////////
    ngOnDestroy() {
        console.log('storage service ngOnDestroy called');
        localStorage.setItem('on destroy storage service', JSON.stringify('bye'));
        // window.location.href="localhost:3001";
        // alert('sdfsdf');
    }
}
