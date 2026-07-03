import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// import { AppConstants } from '@app/app.constants';
// import { environment } from 'environments/environment';

@Injectable({
    providedIn: 'root'
})
export class VersionCheckerService {
    currentHash = '{{POST_BUILD_ENTERS_HASH_HERE}}';

    constructor(
        private http: HttpClient
    ) { }

    // buildVersionCheck(url, frequency = 1000 * 60 * 10) {
    //     const me = this;
    //     if (environment.versionCheck) {
    //         setInterval(() => {
    //             try {
    //                 me.checkVersion(url);
    //             } catch (err) {
    //             }
    //         }, frequency);
    //     }
    // }

    // private checkVersion(url) {
    //     const me = this;
    //     me.http.get(url + '?t=' + new Date().getTime())
    //         .subscribe(
    //             (response: any) => {
    //                 const hash = response.hash;
    //                 const hashChanged = me.hasHashChanged(me.currentHash, hash);
    //                 if (hashChanged) {
    //                     console.log('Need to reload the browser...');
    //                     const IS_USER_WANT_TO_RELOAD = confirm(AppConstants.RELOAD_MESS);
    //                     if (IS_USER_WANT_TO_RELOAD || !IS_USER_WANT_TO_RELOAD) {
    //                         window.location.reload(true);
    //                     }
    //                 }
    //                 me.currentHash = hash;
    //             },
    //             (error) => {
    //                 // console.log(error, 'Could not get version');
    //             }
    //         );
    // }

    // private hasHashChanged(currentHash, newHash) {
    //     return currentHash !== newHash;
    // }
}
