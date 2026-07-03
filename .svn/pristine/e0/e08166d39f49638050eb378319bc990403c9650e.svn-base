import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { AppConstants } from 'src/app/app.constants';
// declare const sha512: any
// import * as variable from 'variableName';
// import { AppConstants } from '@app/app.constants';

@Injectable({
    providedIn: 'root'
})

export class EncryptionService {

    constructor(
    ) {
        console.log('EncryptionService generated salt', this.generateSalt(20));
    }

    //// PRIVATE //////
    private encryptData(data, key) {
        try {
            return CryptoJS.AES.encrypt(JSON.stringify(data), key).toString();
        } catch (error) {
            console.log(error);
        }
    }
    private decryptData(data, key) {
        try {
            const bytes = CryptoJS.AES.decrypt(data, key);
            if (bytes.toString()) {
                return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
            }
            return bytes;
        } catch (error) {
            console.log(error);
        }
    }
    private sha512Hash(data) {
        try {
            return CryptoJS.SHA512(data).toString();
        } catch (error) {
            console.log(error);
        }
    }
    //// PRIVATE //////


    ///////// PUBLIC ////////////
    public generateSalt(saltLength: number) {
        try {
            return CryptoJS.lib.WordArray.random(Math.ceil(saltLength / 2)).toString(CryptoJS.enc.Hex).slice(0, saltLength);
        } catch (error) {
            console.log(error);
        }
    }

    public encodePassword(data: string, noOfTimes: number) {
        let password = data;
        while (noOfTimes > 0) {
            password = this.sha512Hash(password);
            noOfTimes--;
        }
        return password;
    }

    public encryptPasswordWithSalt(password: string, salt: string) {
        console.log('salt: ', salt, 'password: ', password);
        const PASSWORD = this.encodePassword(password, AppConstants.PASSWORD_ENCODING_TIMES_WITH_SALT);
        const ENC_PASS = this.encodePassword(PASSWORD + '#' + salt, AppConstants.PASSWORD_ENCODING_TIMES_WITH_SALT);
        console.log('ENC_PASS: ', ENC_PASS);
        return ENC_PASS;
    }
    ///////// PUBLIC ////////////


    ////////////////////////////////////////////////////////////////////////////////////
    public encodeKeyValue(data: string, keyValue: string) {
        let itemValue = data; let counter = AppConstants.ITEM_ENCODE_ITERATOR;
        while (counter > 0) {
            itemValue = this.encryptData(data, keyValue);
            counter--;
        }
        return itemValue;
    }
    public decodeKeyValue(data: string, keyValue: string) {
        let itemValue = data; let counter = AppConstants.ITEM_ENCODE_ITERATOR;
        while (counter > 0) {
            // console.log('BEFORE ENCODING: ' + itemValue);
            itemValue = this.decryptData(data, keyValue);
            // console.log('AFTER ENCODING: ' + itemValue);
            counter--;
        }
        return itemValue;
    }
    ////////////////////////////////////////////////////////////////////////////////////


    public encodeTotalSession(data: string) {
        let itemValue = data; let counter = AppConstants.DECODE_ITERATOR;
        while (counter > 0) {
            // console.log('BEFORE ENCODING: ' + itemValue);
            itemValue = this.encryptData(data, AppConstants.DECODE_KEY);
            // console.log('AFTER ENCODING: ' + itemValue);
            counter--;
        }
        return itemValue;
    }

    public decodeTotalSession(data: string) {
        let itemValue = data; let counter = AppConstants.DECODE_ITERATOR;
        while (counter > 0) {
            // console.log('BEFORE ENCODING: ' + itemValue);
            itemValue = this.decryptData(data, AppConstants.DECODE_KEY);
            // console.log('AFTER ENCODING: ' + itemValue);
            counter--;
        }
        return itemValue;
    }
}
