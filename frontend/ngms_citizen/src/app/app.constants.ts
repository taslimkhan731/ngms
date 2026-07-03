import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})

export class AppConstants {

    // BASE URL FOR THE HTTP SERVICES
    public static get BASE_API_URL(): string {
        if (environment.production) {
            // PRODUCTION SERVER PATH
            const SERVER_LOC = (window.location.href).split('#')[0].split('/')[2];
            // JSON.parse(sessionStorage.getItem('apiServerLocationDtls'));
            // return 'http://' + SERVER_LOC + '/smartanmol-api/';
            // console.log('http://' + SERVER_LOC + '/ec-management/api/v1/');
            // return 'http://' + SERVER_LOC + '';
            // return 'http://203.193.144.93:9090/ngms_guest/';
            return 'https://bhawan.homeodisha.gov.in/ngms/';
            // return 'http://192.168.0.87:8081/ngms_guest/'
            // return 'http://192.168.0.126:8080/ngms_guest/'
            // return 'http://localhost:8081/ngms_guest/';
            // return 'http://192.168.0.87:8081/ngms_guest/'
            // return 'http://192.168.0.126:8080/ngms_guest/'
            // return 'http://localhost:9092/ngms_guest/';
            // return 'http://192.168.0.87:8081/ngms_guest/' //santanu

        } else {
            // DEVELOPMENT SERVER PATH
            // return 'http://192.168.0.87:8081/ngms_guest/' //santanu
            // return 'http://192.168.0.89:9099/ngms_guest/'; //prasad
            // return 'https://bhawan.homeodisha.gov.in/ngms/';
            return 'http://203.193.144.93:9090/';
            // return 'http://192.168.0.53:9090/ngms_guest/';
            // return 'http://192.168.0.117:8787/ngms/';//me
            // return 'http://192.168.0.117:8088/ngms_guest/' ; //Prerna
            // return 'http://192.168.0.72:8080/ngms/' ; //mohit update
            // return 'http://192.168.0.160:8888/ngms/'//Amit 8280009533:Test@1234
            //  android:usesCleartextTraffic="true">

        }
    }

    // For Financial year limit
    public static VIEW_FIN_YR_LIMIT = 3;
    public static SET_FIN_YR_LIMIT = 2;

    // AUTHORIZATION HEADER FOR GETTTING ACCESS TOKEN //
    public static get LOGIN_AUTHORIZATION_HEADER(): string { return 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'; }

    public static get AUTHORIZATION_HEADER(): string {
        let accessTokenDetails = localStorage.getItem('accessTokenDetails');
        if (accessTokenDetails) {
            return 'Bearer ' + JSON.parse(accessTokenDetails);
        }
    }

    // USED TO GET MENU TYPE //
    public static get MENU_TYPE(): object {
        return {
            VMENU: 101,
            HMENU: 102,
            CMENU: 103
        };
    }
    // STORE ACESS TOKEN COMPLETE DETAILS
    public static ACCESS_DETAILS: object = {};
    public static ROLE_ID;
    // DASHBOARD VALUE
    public static DASHBOARD_DRIVE = 'ALL';

    // SESSION TIMOUT VALUE IN SECONDS
    public static SESSION_CHECKER = 10;
    public static SESSION_PRE_CHECK = 300;

    // NORMAL ENCODING TIME
    public static ITEM_ENCODE_ITERATOR = 1;
    public static DECODE_ITERATOR = 1;
    public static DECODE_KEY = '0eef9a9d9ccb8ea0fbb21e03ad2c6609e1b98de53c0bcd084c23c0ca8376f6c7'; // 'MY SESSION STORAGE ENCODING TEST';

    // PASSWORD ENCODING TIME
    public static PASSWORD_ENCODING_TIMES = 2;
    public static PASSWORD_ENCODING_TIMES_WITH_SALT = 1;

    // RELOAD MESSAGE
    public static RELOAD_MESS = 'You are using an older version of project. So, click on okay to get new version of it';

    // LOOKUP MENU
    public static LOOKUP_MENU_MAX_LENGTH = 5;
}
