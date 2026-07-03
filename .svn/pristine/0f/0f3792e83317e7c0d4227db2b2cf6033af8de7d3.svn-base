import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../app.constants";
import { HttpClientService } from "../httpClient/http-client.service";
import { Observable } from "rxjs/internal/Observable";
// import { THIS_EXPR } from "@angular/compiler/src/output/output_ast";
// import { id } from "@swimlane/ngx-charts";
import { AuthService } from "../authentication/auth.service";

@Injectable({
    providedIn: "root",
})
export class MdmService {

    public UAC_URL;
    public MDM_URL;
    public FAMILY_URL;
    public REG_URL;
    public AUTH_URL;
    public EXAMAGENCY_URL;
    public SINGLEAGENCYTAB_URL;
    public RESOURCE_REGISTER_URL;
    public PAYMENT_URL;
    public ANNEXURE_URL;
    public GRIEVANCE_URL;
    public MANAGEMENT_URL;
    public TEMP_RESOURCE_REGISTER_URL;
    public PROFILE_URL;
    constructor(
        private httpClient: HttpClientService,
        private appConstants: AppConstants
    ) { }

    public registration(data: object, captchaKey) {
        return this.httpClient.postAuth(AppConstants.BASE_API_URL + 'mobileRegistration.do', data, captchaKey);
    }

    public createPassword(data: object) {
        return this.httpClient.postWithOutAccessToken(AppConstants.BASE_API_URL + 'createUserForMobile.do', data);
    }

    public validateForgotPasswordOtp(data: object) {
        return this.httpClient.postWithOutAccessToken(AppConstants.BASE_API_URL + 'validateForgotPasswordOtp.do', data);
    }

    public forgotPasswordOtp(data: object, captchaKey) {
        return this.httpClient.postAuth(AppConstants.BASE_API_URL + 'mobileresetPasswordRequest.do', data, captchaKey);
    }

    public getGuestHouse() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getGHList.do');
    }

    public getProfileDetails() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/mobileUserProfile.do');
    }

    public getFeedbackList() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getMobileFeedbackList.do');
    }
    public getUserRequestList(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getUserRequestListForMobile.do', data);
    }

    public viewBookingHistory(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getGuestBookingHistoryForMobile.do', data);
    }
    public getConfrenceRequestDetailsForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getGuestConfrenceRequestDetailsForMobile.do', data);
    }

    public changePassword(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/mobileChangePasswordOnValid.do', data);
    }

    public updateProfile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateProfileMobile.do', data);
    }

    public getDesignationDetails(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getDesignationDetailsForMobile.do?id=' + id);
    }

    public getCategoryDetails() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getCategoryDetailsForMobile.do');
    }

    public userProfile() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/mobileUserProfile.do');
    }

    public getFlightTrainList(data) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getFlightTrainList.do', data);
    }

    public getPurposeDetails(data: HttpParams) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getPurposeDetailsForMobile.do', data);
    }

    public firstLoginDoc(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/guestDocumentUploadForMobile.do', data);
    }

    public getDeleteBooking(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/deleteApplicationForMobile.do?reqId=' + id);
    }

    public getPaymentPDF(id) {
        console.log(id);
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getPdfPaymentReceiptMobile.do?reqId=' + id);
    }

    public getBillPDF(id) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/showBillDetailsForMobile.do', id);
    }

    public getBillPDFPrint(bookingId, billId) {
        return this.httpClient.getBlobRP(AppConstants.BASE_API_URL + 'mobile/downloadBillPdfForMobile.do?bookingId=' + bookingId + '&billId=' + billId);
    }

    public getDownloadPDF(id) {
        return this.httpClient.getBlobRP(AppConstants.BASE_API_URL + 'mobile/downloadApplicationForMobile.do?reqId=' + id);
    }

    public getCancelBooking(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/cancelApplicationForMobile.do', data);
    }

    public giveFeedback(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveMobileFeedbackDetails.do', data);
    }

    public saveBookingDetails(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveBookingDetails.do', data);
    }

    public updateBookingDetails(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveBookingDetails.do', data);
    }

    public extendBooking(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateGuestBookingExtensionForMobile.do', data);
    }

    public getRequestData(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getRequest.do?reqId=' + id);
    }
    public getPaymentReceiptData(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getPaymentReceiptForMobile.do?reqId=' + id);
    }
    public getRecieptDetailsForMobile(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getRecieptDetailsForMobile.do?billId=' + id);
    }

    public getBookingDetailsByBookingId(obj) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getBookingDetailsByBookingIdForMobile.do', obj);
    }
    public ResendOtp(data: object,) {
        return this.httpClient.postAuthWithoutCaptcha(AppConstants.BASE_API_URL + 'resendOTP.do', data);
    }
    public ResendOtpRegistration(data: object,) {
        return this.httpClient.postAuthWithoutCaptcha(AppConstants.BASE_API_URL + 'resendOTPForGuest.do', data);
    }

    public getConfrenceTimePeriodDetailsForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfrenceTimePeriodDetailsForMobile.do', data)
    }
    public getConfrenceAmountForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfrenceAmountForMobile.do', data)
    }

    public saveConferenceDetails(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/guestReceptionistaddBookingDetailsForConfrenceFormobile.do', data);
    }

    public callPaymentPage(FORM) {
        
        return this.httpClient.postWithoutAccessTokenForPayment('https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction',FORM);
    }


    public editBillingDetailsForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/editBillingDetailsForMobile.do', data);
    }
    public updateBillRecordForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateBillRecordForMobile.do', data);
    }
    public partialCancelForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/partialCancelForMobile.do', data);
    }

    public cancelConfrenceDetailsForMobile(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelConfrenceDetailsForMobile.do?ReqId=' + id);
    }
    public getNoc(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getNocForMobile.do?bookingId=' + id);
    }
    public getDuesPaymentReceiptForMobile(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getDuesPaymentReceiptForMobile.do?reqId=' + id);
    }
    public getConfrenceTypeDetails(obj) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfrenceTypeDetailsForMobile.do', obj);
    }
    public checkAvailability(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/checkAvailabilityForMobile.do', data);
    }
    public getFeedbackRepliedDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getFeedbackRepliedDetailsForMobile.do', data);
    }

    public showFile(data,id){
        let accessTokenDetails = localStorage.getItem('accessTokenDetails')
        return this.httpClient.getBlobRP(AppConstants.BASE_API_URL + 'mobile/imagedownloadMobile.do?imageCode='+data+id);
        // return this.httpClient.getBlobRP(AppConstants.BASE_API_URL + 'mobile/imagedownloadMobile.do?imageCode='+data+id + '&accToken=' + accessTokenDetails);
    }

    // public showPayment(accessCode,encRequest){
    //     return this.httpClient.postWithAccessToken (AppConstants.BASE_API_URL + 'mobile/pgMobileRequest.do',accessCode,encRequest);
    // }

    // public showPayment(encRequest,accessCode,) {
        
    //     return this.httpClient.postWithoutAccessTokenForPayment(`pgMobileRequest.do?encRequest=${encRequest}&accessCode=${accessCode}`);
    // }


















    // public saveDocument(data: FormData) {
    //     // var headers = new HttpHeaders(
    //     //   {
    //     //     'Content-Type': 'multipart/form-data',
    //     //     'Authorization': 'Bearer ' + AppConstants.AUTHORIZATION_HEADER
    //     //   }
    //     // );
    //     var header = {
    //         headers: new HttpHeaders()
    //             .set('Authorization', `${AppConstants.AUTHORIZATION_HEADER}`)
    //         // .set('Content-Type', 'multipart/form-data')
    //     }
    //     return this.httpClient.post(this.TEMP_RESOURCE_REGISTER_URL + "saveTemporaryResourceExcel", data, header);
    // }
    // public getExcelUploadStatusDetailsByExcelId(excelId) {
    //     return this.httpClient.get(this.TEMP_RESOURCE_REGISTER_URL + "getViewTempResourceExcelHistory/" + excelId);
    // }

    // // profile
    // public updateProfile(data) {
    //     return this.httpClient.post(this.PROFILE_URL + "updateProfileDetails", data);
    // }
    // public profileDetails() {
    //     return this.httpClient.get(this.PROFILE_URL + "userProfileDetails");
    // }



    // public saveDocument(data: FormData) {
    //     // var headers = new HttpHeaders(
    //     //   {
    //     //     'Content-Type': 'multipart/form-data',
    //     //     'Authorization': 'Bearer ' + AppConstants.AUTHORIZATION_HEADER
    //     //   }
    //     // );
    //     var header = {
    //         headers: new HttpHeaders()
    //             .set('Authorization', `${AppConstants.AUTHORIZATION_HEADER}`)
    //         // .set('Content-Type', 'multipart/form-data')
    //     }
    //     return this.httpClient.post(this.TEMP_RESOURCE_REGISTER_URL + "saveTemporaryResourceExcel", data, header);
    // }

}
