import { Injectable, OnInit } from '@angular/core';
import { HttpClientService } from '../httpClient/http-client.service';
import { map, debounceTime, distinctUntilChanged, switchMap, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs/internal/Observable';
import { of } from 'rxjs/internal/observable/of';
// import { StorageService } from '../storage/storage.service';
import { DatePipe, formatDate } from '@angular/common';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { AppConstants } from '../../app.constants';
// import { Config } from 'protractor';
// import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
// import { MdmService } from '../MDM/mdm.service';
import { LoadingController, ModalController, ToastController } from '@ionic/angular';
import { Filesystem, Directory, FilesystemDirectory } from '@capacitor/filesystem';
import { FileOpener } from '@capawesome-team/capacitor-file-opener';
import { BASE64_ENCODED_FONT } from '../base64EncodedFont';

@Injectable({
    providedIn: 'root'
})
export class CommonService implements OnInit {
    loading: HTMLIonLoadingElement;
    private storedDetails: any; // (primary purpose) used for data storing on edit button clicked from a row of a table.
    private storedTemporary: any; // store (secondary purpose)
    public path: string | undefined;
    public base64String: string | undefined;
    public copyToAppDirectory: boolean = false;
    private params = {
        initialReg: null,
        agencyReg: null,
        centerWiseReg: null
    };

    private genCodes = null;
    loadingStatus: boolean=false;
    allotmentCancelData=[]
    // loading = false;

    public style = `<style>
    @font-face {
      font-family: 'lato';
      src: url(data:font/opentype;base64,${BASE64_ENCODED_FONT}) format('truetype');
      // font-weight: 600;
    }
    body {
      font-family: 'lato', sans-serif;
      // font-weight: bolder !important;
    }
  </style>`;

    constructor(
        private httpClient: HttpClientService,
        // private datePipe: DatePipe,
        // private mdmService: MdmService,
        public loadingController: LoadingController,
        public modalcontroller: ModalController,
        public toast: ToastController
    ) {
        // console.log('Common Service constructor');
        // this.mdmService.getAllGenCodes().subscribe(
        //     res => {
        //         console.log(res);
        //         this.genCodes = res;
        //     },
        //     error => {
        //         console.log('error', error);
        //     }
        // );
    }

    ngOnInit(): void {
        console.log('common service ngoninit');

        // this.mdmService.getAllGenCodes().subscribe(
        //     res => { 
        //         console.log(res);
        //         this.genCodes = res;
        //     },
        //     error => {
        //         console.log('error', error);
        //     }
        // );
    }
    public async showSuccessToast(message) {
        const toast = await this.toast.create({
            message: message,
            duration: 3000,
            position: 'bottom',
            color: 'success'
        });
        toast.present();
    }
    public async showErrorToast(message) {
        const toast = await this.toast.create({
            message: message,
            duration: 3000,
            position: 'bottom',
            color: 'danger'
        });
        toast.present();
    }

    // async showLoading() {
    //     this.loading = true;
    //     return await this.loadingController.create({
    //     }).then(a => {
    //         a.present().then(() => {
    //             console.log('presented');
    //             if (!this.loading) {
    //                 a.dismiss().then(() => console.log('abort presenting'));
    //             }
    //         });
    //     });
    // }
    async showLoading() {
        this.loading = await this.loadingController.create({
            message: 'Please Wait',
            translucent: true,
        });
        this.loading.present();
    }
    async hideLoading() {
        this.loading.dismiss();
    }

    simpleLoader() {
        this.loadingController.create({
          message: 'Loading...'
        }).then((response) => {
          response.present();
        });
      }
      dismissLoader() {
        this.loadingController.dismiss().then((response) => {
          console.log('Loader closed!', response);
        }).catch((err) => {
          console.log('Error occured : ', err);
        });
      }
    
      async present() {
        this.loadingStatus = true;
        return await this.loadingController.create({
          // duration: 5000,
        }).then(a => {
          a.present().then(() => {
            console.log('presented');
            if (!this.loadingStatus) {
              a.dismiss().then(() => console.log('abort presenting'));
            }
          });
        });
      }
    
      async dismissLoaderFun() {
        this.loadingStatus = false;
        return await this.loadingController.dismiss().then(() => console.log('dismissed'));
      }
    // async hideLoading() {
    //     this.loading = false;
    //     return await this.loadingController.dismiss().then(() => console.log('dismissed'));
    // }

    public storeDetails(data: any) {
        this.storedDetails = data;
        console.log('Common service : SETTING storedDetails', this.storedDetails);
    }
    public getStoredDetails() {
        console.log('Common service : GETTING storedDetails', this.storedDetails);
        return this.storedDetails;
    }
    public storeTemporary(data: any) {
        this.storedTemporary = data;
        console.log('Common service : SETTING storedTemporary', this.storedTemporary);
    }
    public getStoreTemporary() {
        console.log('Common service : GETTING storedTemporary', this.storedTemporary);
        return this.storedTemporary;
    }
    public setAllotmentCancelData(data:any){
        this.allotmentCancelData[0]=data;
    }
    public getAllotmentCancelData(){
        return this.allotmentCancelData
    }

    public getParams() {
        return this.params;
    }
    public setParams(key: String, data: object) {
        this.params[key.toString()] = data;
        // this.params = params;
    }

    public async getGencodes(pId?) {
        console.log('gencode called', this.genCodes, pId);
        if (pId) {
            return this.genCodes.filter(gen => gen.parentId === pId);
        } else {
            return this.genCodes;
        }
        // if (!this.genCodes) {  
        // this.mdmService.getAllGenCodes().subscribe(
        //     res => { 
        //         console.log(res);
        //         this.genCodes = res;
        //     },
        //     error => {
        //         console.log('error', error);
        //     }
        // );          
        // if (pId) {
        //     return this.genCodes.filter(gen => gen.parentId === pId);
        // } else {
        //     return this.genCodes;
        // }
        // } else {
        //     console.log('getGencodes')
        //     if (pId) {
        //         return this.genCodes.filter(gen => gen.parentId === pId);
        //     } else {
        //         return this.genCodes;
        //     }
        // }

    }
    public setGencodes(data) {
        this.genCodes = data;
        // this.params = params;
    }

    public getImage(imageType: string, imageId: string) {
        console.log('-------------inside  common service' + imageType + imageId);
        return this.httpClient.getBlobRP(AppConstants.BASE_API_URL + 'sysadmin/imagedownload/' + imageType + '_' + imageId);
    }
    // public logout() {
    //     const accessToken = JSON.parse(localStorage.getItem('accessTokenDetails'))['access_token'];
    //     return this.httpClient.get(AppConstants.BASE_API_URL + 'logout/removeAccessToken?access_token=' + accessToken);
    // }
    public saveAndDownloadFile(fileData: Blob, fileName: string) {
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(fileData);
        link.setAttribute('download', fileName);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }


    public getFinyearList(limit: number) { // , finYrUpto?: number
        return this.httpClient.get(AppConstants.BASE_API_URL + 'sysadmin/getAllFisyrOnly').pipe(
            map(response => response['response'].sort((F1, F2) => F2.id - F1.id).slice(0, limit)));
    }

    public searchEntries(url: string, term, data?: any) {
        console.log(term);
        if (term.length !== 0) {
            return this.httpClient.postRP(AppConstants.BASE_API_URL + url, data, this.getParams()); // { 'searchBy': term }
        }
    }

    public search(terms: Observable<string>, url: string, data?: any) {
        console.log(terms);
        return terms.pipe(debounceTime(400), distinctUntilChanged(),
            switchMap(term => term ? this.searchEntries(url, term, data) : of([])),
            catchError(error => of([]))
        );
    }

    // Here Onwards new Coding Starts

    // USED TO GET LOOKUP MENUS
    public getLookUpMenus() {
        // const MENUS: MenuModel[] = this.appStorage.getSessionAttribute('menus');
        // return MENUS.filter(menu => menu.lookUpMenuName === this.appStorage.getSessionAttribute('lookUpMenuId'));
    }

    // transformDate(date) {
    //     console.log(date);
    //     return this.datePipe.transform(date, 'dd-MM-yyyy'); // whatever format you need.
    // }

    getConfigResponse(): Observable<HttpResponse<any>> {
        return this.httpClient.get('http://203.193.144.93:8080/waterResource/index.html#/payment-status', { observe: 'response' });
    }

    // method to render image using product type and it's id
    getMediaById(profilePic: string): string {
        const accessToken = JSON.parse(localStorage.getItem('accessTokenDetails'))['access_token'];
        return AppConstants.BASE_API_URL + 'base/getUserMediaDetails?fileName=' + profilePic + '&access_token=' + accessToken;
    }

    async viewFile(data, fileName) {
        // if (data.split(';')[0].split('/')[1] == 'pdf') {
          //this.downloadAndDisplayPdf(data,'example.pdf')
          this.writeFile(data, fileName + '.' + data.split(';')[0].split('/')[1]);
        // } else {
        //   const modal = await this.modalcontroller.create({
        //     component: DocViewerComponent,
        //     componentProps: { data }
        //   });
        //   modal.onDidDismiss().then((data) => {
        //   })
        //   return await modal.present();
        // }
    
      }
      public async writeFile(base64String, name): Promise<void> {
        this.path = undefined;
        await Filesystem.requestPermissions();
        if (base64String && name) {
          await Filesystem.writeFile({
            data: base64String,
            path: name,
            directory: Directory.External,
          });
          const { uri } = await Filesystem.getUri({
            path: name,
            directory: Directory.External,
          });
          this.path = uri;
        }
        this.openFile();
      }
      public async openFile(): Promise<void> {
        if (!this.path) {
          return;
        }
        await FileOpener.openFile({ path: this.path });
        console.log('File opened');
      }

}
