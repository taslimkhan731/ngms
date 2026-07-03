import { HttpClient, HttpHeaders, HttpRequest, HttpHandler, HttpEvent, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { Observable } from 'rxjs/internal/Observable';
import { catchError } from 'rxjs/operators';
import { ClientErrorHandlerService } from '../ErrorHandler/client-error-handler.service';
import { AppConstants } from '../../../../src/app/app.constants';
import { Params } from '@angular/router';

@Injectable({
    providedIn: 'root'
})

export class HttpClientService {
    // accessToken = 'Bearer ' + JSON.parse(localStorage.getItem('accessTokenDetails'))['token'];
    constructor(
        private http: HttpClient,
        private location: Location,
        private clientErrorHandler: ClientErrorHandlerService
    ) { }

    // USED FOR METHOD TYPE GET & CONTENT TYPE IS JSON
    getWithoutRGrp(url: string, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
        // .set('Authorization', accessToken);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        // console.log(options);
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE POST & CONTENT TYPE IS JSON
    postWithoutRGrp(url: string, data: object, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', 'accessToken');
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // FOR GET AUTHORIZATION
    getAuth(url: string, opt?: object): Observable<any> {
        // console.log("Appconstant" , AppConstants.AUTHORIZATION_HEADER);
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('TokenKey', AppConstants.AUTHORIZATION_HEADER);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }
    postAuthWithoutCaptcha(url: string, data: object): Observable<any> {
        const accessToken = 'jhgjhgjhgjhgjhgj';
        const options = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
            }),
            withCredentials: true
        };
        console.log('dafdssssssssdfggggggggggggggggggggggggggggggggggggggggggs')
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    getAuthWithHeader(url: string, opt: HttpParams): Observable<any> {
        // console.log("Appconstant" + AppConstants.AUTHORIZATION_HEADER);
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('TokenKey', AppConstants.AUTHORIZATION_HEADER);

        const options = {
            headers: HEADERS,
            params: opt
        };
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // FOR GET AUTHORIZATION
    getAuthResponse(url: string, opt?: object): Observable<any> {
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/x-www-form-urlencoded')
            // .set('Origin', (window.location.href).split('#')[0].split('/')[2]);
            .set('Authorization', AppConstants.LOGIN_AUTHORIZATION_HEADER);
        const options = opt ? opt : {
            headers: HEADERS,
            // observe: 'response',
            withCredentials: true
        };
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // SEND AUTHORIZATION DETAILS TO GET ACCESS

    // postAuth(url: string, data: object, opt?: object): Observable<any> {
    // const HEADERS = new HttpHeaders()
    //     .set('Content-Type', 'application/json')
    // .set('Authorization', AppConstants.LOGIN_AUTHORIZATION_HEADER);
    // const options = opt ? opt : {
    //     headers: new HttpHeaders({ 'Content-Type': 'application/json', 'captchaKey': captchaKey}),
    //     withCredentials: true
    // };
    postAuth(url: string, data: object, captchaKey): Observable<any> {
        const accessToken = 'jhgjhgjhgjhgjhgj';
        const options = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                // 'Access-Control-Allow-Credentials': 'true',
                // 'Access-Control-Allow-Origin': '*',
                // 'Access-Control-Allow-Headers': 'proxyId,X-Requested-With,captchaKey,content-type, Authorization,caseno,Set-Cookie,Origin',
                'captchaKey': captchaKey,
                // 'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                // 'Connection': 'keep-alive',
                // 'Access-Control-Expose-Headers': 'xsrf-token,captchaKey,Authorization,proxyId,caseno,Set-Cookie,Origin',
                // 'Access-Control-Max-Age': '1800',

                // 'Access-Control-Allow-Credentials': 'true',
                // 'Authorization': accessToken
                //                 .append('content-type','application/x-www-form-urlencoded')

                // headers=headers.delete("content-type","application/json")  //delete content-type='application/json'
            }),
            withCredentials: true
        };
        // console.log('dafdssssssssdfggggggggggggggggggggggggggggggggggggggggggs')
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    postWithoutAccessToken(url: string, data: object, opt?: object): Observable<any> {
        const HEADERS = new HttpHeaders()
        // .set('Content-Type', 'application/json')
        // .set('Authorization', AppConstants.LOGIN_AUTHORIZATION_HEADER);
        const options = opt ? opt : {
            headers: HEADERS,
            withCredentials: true
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }
    postWithoutAccessTokenForPayment(url: string, data: object, opt?: object): Observable<any> {
        const HEADERS = new HttpHeaders()
        // .set('Content-Type', 'application/json')
        // .set('Authorization', AppConstants.LOGIN_AUTHORIZATION_HEADER);
        const options = opt ? opt : {
            headers: HEADERS,
            withCredentials: true
        };
        return this.http.post(url, data ).pipe(catchError(this.clientErrorHandler.handleError));
    }
    postWithAccessToken(url: string, data: object, opt?: object): Observable<any> {
        // console.log('log',AppConstants.AUTHORIZATION_HEADER);
        // const HEADERS = new HttpHeaders()
        // .set('Content-Type', 'application/json')
        // .set('Authorization', AppConstants.AUTHORIZATION_HEADER);
        // const options = opt ? opt : {
        //     headers: HEADERS,
        //     withCredentials: false
        // };
        // return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
        const options = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'TokenKey':AppConstants.AUTHORIZATION_HEADER,
            })
        };
        // console.log('dafdssssssssdfggggggggggggggggggggggggggggggggggggggggggs')
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }
    getBlobRP(url: string, extraParams?: object, opt?: object): Observable<any> {
        const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const headers = new HttpHeaders()
            .set('TokenKey', accessToken);
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: headers,
            // observe: 'response',
            params: {},
            responseType: 'blob'
        };
        if (extraParams !== undefined) {
            for (const param of Object.keys(extraParams)) {
                options['params'][param] = extraParams[param];
            }
        }
        return this.http.get(url, options);
    }
    postAuthOld(url: string, data: object, opt?: object): Observable<any> {
        const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            // .set('x-auth', accessToken)
            .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
            .set('Pragma', 'no-cache')
            .set('Expires', '-1')
            .set('Access-Control-Allow-Credentials', 'true')
            .set('Access-Control-Allow-Methods', 'GET, POST')
            .set('Access-Control-Max-Age', '1800')
            .set('X-Frame-Options', 'SAMEORIGIN')
            .set('X-XSS-Protection', '1; mode=block');
        const options = opt ? opt : {
            headers: HEADERS,
            withCredentials: true
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }
    // FOR AUTHENTICATION WITH OUT RGID
    getNoAuth(url: string, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
        // .set('Authorization', accessToken);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        // console.log(options);
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));

    }
    // FOR AUTHENTICATION WITH OUT RGID
    getRPNoAuth(url: string, extraParams: object, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
        // .set('Authorization', accessToken);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        for (const param of Object.keys(extraParams)) {
            options['params'][param] = extraParams[param];
        }
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE GET & CONTENT TYPE IS JSON
    get(url: string, opt?: object): Observable<any> {
        // console.log('rGId', this.getRgId().toString());
        const accessToken = AppConstants.AUTHORIZATION_HEADER ? AppConstants.AUTHORIZATION_HEADER : '';
        // console.log(accessToken);
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            // .set('x-auth', accessToken)
            .set('Authorization', accessToken);
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        // console.log(options);
        // return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
        return this.http.get(url).pipe(catchError(this.clientErrorHandler.handleError));
    }
    getLogin(url: string, opt: any,): Observable<any> {
        const accessToken = 'jhgjhgjhgjhgjhgj';
        const options = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'captchaKey': opt,

            }),
        };
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }
    // USED FOR METHOD TYPE GET & CONTENT TYPE IS JSON
    getByCN(url: string, opt?: object): Observable<any> {
        // const data = this.appStorage.getSessionAttribute('assignedPetitionerDetails');
        // console.log('data.caseNo+++', data.caseNo);
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
        // .set('Authorization', accessToken)
        // .set('rGId', this.getRgId().toString())
        // .set('caseno', data.caseNo);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };

        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE GET & CONTENT TYPE IS FORM DATA
    getFD(url: string, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            // .set('x-auth', accessToken)
            .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
            .set('Pragma', 'no-cache')
            .set('Expires', '-1')
            .set('Access-Control-Allow-Credentials', 'true')
            .set('Access-Control-Allow-Methods', 'GET, POST')
            .set('Access-Control-Max-Age', '1800')
            .set('X-Frame-Options', 'SAMEORIGIN')
            .set('X-XSS-Protection', '1; mode=block');
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE GET & REQUEST PARAMETERS
    getRP(url: string, extraParams: object, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            // .set('Authorization', accessToken)
            .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
            .set('Pragma', 'no-cache')
            .set('Expires', '-1')
            .set('Access-Control-Allow-Credentials', 'true')
            .set('Access-Control-Allow-Methods', 'GET, POST')
            .set('Access-Control-Max-Age', '1800')
            .set('X-Frame-Options', 'SAMEORIGIN')
            .set('X-XSS-Protection', '1; mode=block');
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        for (const param of Object.keys(extraParams)) {
            options['params'][param] = extraParams[param];
        }
        return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    put(url: string, data: object, opt?: object): Observable<any> {
        const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json; charset=utf-8')
            .set('Authorization', accessToken)
        const options = opt ? opt : {
            headers,
        };
        return this.http.put(url, data, options);
    }

    // USED FOR METHOD TYPE POST & CONTENT TYPE IS JSON
    post(url: string, data: object, opt?: object): Observable<any> {
        const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
        // .set('x-auth', accessToken)
        // .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
        // .set('Pragma', 'no-cache')
        // .set('Expires', '-1')
        // .set('Access-Control-Allow-Credentials', 'true')
        // .set('Access-Control-Allow-Methods', 'GET, POST')
        // .set('Access-Control-Max-Age', '1800')
        // .set('X-Frame-Options', 'SAMEORIGIN')
        // .set('X-XSS-Protection', '1; mode=block')
        // .set('Authorization', accessToken);
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // for Nalco POST Api (different content-type)
    postNalco(url: string, data: object, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/x-www-form-urlencoded')
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }


    // USED FOR METHOD TYPE DELETE & CONTENT TYPE IS JSON
    delete(url: string, data?: object, opt?: object): Observable<any> {
        const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            // .set('x-auth', accessToken)
            // .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
            // .set('Pragma', 'no-cache')
            // .set('Expires', '-1')
            // .set('Access-Control-Allow-Credentials', 'true')
            // .set('Access-Control-Allow-Methods', 'GET, POST')
            // .set('Access-Control-Max-Age', '1800')
            // .set('X-Frame-Options', 'SAMEORIGIN')
            // .set('X-XSS-Protection', '1; mode=block')
            .set('Authorization', accessToken);
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.delete(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE POST & HEADER TYPE IS JSON
    postWithOutAccessToken(url: string, data: any, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
        // .set('Authorization', accessToken)
        // .set('ackNo', data);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }
    postRH(url: string, data: any, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            // .set('Authorization', accessToken)
            .set('ackNo', data);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.post(url, null, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    postFP(url: string, data: any, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
        // .set('Authorization', accessToken)
        // .set('ackNo', data);
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE POST & CONTENT TYPE IS FORM DATA
    postFD(url: string, data: FormData, opt?: object): Observable<any> {
        const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const headers = new HttpHeaders()
        // .set('Authorization', accessToken);
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers,
            params: {}
        };
        // console.log('data..................>>>>', data);
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE POST & REQUEST PARAMETER
    postRP(url: string, data: object, extraParams: object, opt?: object): Observable<any> {
        // const accessToken = AppConstants.AUTHORIZATION_HEADER;
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json')
            // .set('Authorization', accessToken)
            .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
            .set('Pragma', 'no-cache')
            .set('Expires', '-1')
            .set('Access-Control-Allow-Credentials', 'true')
            .set('Access-Control-Allow-Methods', 'GET, POST')
            .set('Access-Control-Max-Age', '1800')
            .set('X-Frame-Options', 'SAMEORIGIN')
            .set('X-XSS-Protection', '1; mode=block');
        // .set('rGId', this.getRgId().toString());
        const options = opt ? opt : {
            headers: HEADERS,
            params: {}
        };
        for (const param of Object.keys(extraParams)) {
            options['params'][param] = extraParams[param];
        }
        return this.http.post(url, data, options).pipe(catchError(this.clientErrorHandler.handleError));
    }

    // USED FOR METHOD TYPE GET & RESPONSE TYPE IS BLOB
    // getBlobRP(url: string, extraParams?: object, opt?: object): Observable<any> {
    //     const HEADERS = new HttpHeaders()
    //         .set('Cache-Control', 'private, no-cache, no-store, must-revalidate')
    //         .set('Pragma', 'no-cache')
    //         .set('Expires', '-1')
    //         .set('Access-Control-Allow-Credentials', 'true')
    //         .set('Access-Control-Allow-Methods', 'GET, POST')
    //         .set('Access-Control-Max-Age', '1800')
    //         .set('X-Frame-Options', 'SAMEORIGIN')
    //         .set('X-XSS-Protection', '1; mode=block');
    //     const options = opt ? opt : {
    //         headers: HEADERS,
    //         // observe: 'response',
    //         params: {},
    //         responseType: 'blob'
    //     };
    //     if (extraParams !== undefined) {
    //         for (const param of Object.keys(extraParams)) {
    //             options['params'][param] = extraParams[param];
    //         }
    //     }
    //     return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    // }

    getCAPTCHA(url: string): Observable<any> {
        const HEADERS = new HttpHeaders()
            .set('Content-Type', 'application/json');
        const options = {
            headers: HEADERS,
            responseType: 'blob' as 'json',
            withCredentials: true,
        };
        return this.http.get(url, options);
    }

    getCaptchaWithCaptchaKeyHeader(url: string): Observable<any> {
        const options = {
            headers: new HttpHeaders({ 'Content-Type': 'applicant/json' }),
            observe: 'response' as 'response',
            responseType: 'blob' as 'json',
            // withCredentials: true,
        };
        return this.http.get(url, options);
    }
    getCaptcha(url: string): Observable<any> {
        return this.http.get(url, {
            observe: 'response' as 'response',
            responseType: 'blob' as 'json',
        });
    }
    // getCaptcha(url: string): Observable<any> {
    //     console.log("Fetching CAPTCHA image...");
    //     return this.http.get(url, { responseType: 'blob' }).pipe(
    //         catchError((error: any) => {
    //             console.error("Error fetching CAPTCHA:", error);
    //             throw error; // You can handle the error as needed
    //         })
    //     );
    // }
    // USED FOR METHOD TYPE GET & RESPONSE TYPE IS BLOB
    // getCAPTCHA(url: string, extraParams?: object, opt?: object): Observable<any> {
    //     const accessToken = AppConstants.AUTHORIZATION_HEADER;
    //     const HEADERS = new HttpHeaders()
    //         .set('Authorization', accessToken);
    //     const options = opt ? opt : {
    //         headers: HEADERS,
    //         params: {},
    //         responseType: 'blob'
    //     };
    //     if (extraParams !== undefined) {
    //         for (const param of Object.keys(extraParams)) {
    //             options['params'][param] = extraParams[param];
    //         }
    //     }
    //     return this.http.get(url, options).pipe(catchError(this.clientErrorHandler.handleError));
    // }
}
