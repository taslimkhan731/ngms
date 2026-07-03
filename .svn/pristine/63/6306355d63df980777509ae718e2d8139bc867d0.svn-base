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
    public forgotPasswordResendOtp(data: object,) {
        return this.httpClient.postAuthWithoutCaptcha(AppConstants.BASE_API_URL + 'resendOTP.do', data);
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

    public viewBookingHistory(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getGuestBookingHistoryForMobile.do', data);
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
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/adminsaveBookingDetails.do', data);
    }

    public extendBooking(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateBookingExtensionForMobile.do', data);
    }

    public getRequestData(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getRequest.do?reqId=' + id);
    }
    public getPaymentReceiptData(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getPaymentReceiptForMobile.do?reqId=' + id);
    }

    public getBookingDetailsByBookingId(obj) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getBookingDetailsByBookingIdForMobile.do', obj);
    }
    public getConferenceBookingDetailsByBookingId(obj) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getConferenceBookingDetailsByBookingIdForMobile.do', obj);
    }
    // -----------------------------------------------------------------ADMIN API--------------------------------------------------------------

    public getAdminDashboradCount(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAdminDashboradCountForMobile.do', data);
    }
    public getAllotmentListDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllotmentLstDtlsAccToReq.do', data);
    }
    public getCityDataTableForMobile() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getCityDataTableForMobile.do');
    }
    public getCityWiseGUestHouse(obj: any) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getGuestHouseListDetails.do?id=' + obj);
    }
    public getModerationCancellationList(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCancellationModLstDtlsForMobile.do', data);
    }
    public getModerationModifyList(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getModListForAllotmentForMobile.do', data);
    }
    public editModeratorCancel(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/cancelModerationRequestForMobile.do?reqId=' + data);
    }
    public getCheckForShifta(cityid, ghid) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/checkForShift.do?cityId=' + cityid + '&blockId=' + ghid + '&page=ALLOTMENT');
    }
    public getAllotmentList(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getModListForAllotmentForMobile.do', data);
    }
    public getBillingList(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllBillingListForMobile.do', data);
    }
    public showBillingList(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getShowBillListForMobile.do', data);
    }
    public checkOutDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCheckOutDetailsForMobile.do', data);
    }
    public checkInSlipDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewCheckinSlipDetailsForMobile.do', data);
    }
    public getCancelReservationist(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCancellationModLstDtlsForMobile.do', data);
    }
    public cancelReservationOfModerator(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelModRequestForMobile.do?reqId=' + id);
    }
    public getCanRoomDetailsList(id, type) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getCanRoomDetailsForMobile.do?type=' + type + '&bookingId=' + id);
    }
    public getCategoryData() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getCategoryDetailsForMobile.do');
    }
    public getPurposeOfVisitDetails(data) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getPurposeDetailsForMobile.do', data);
    }
    public getDesignationDetailsList(data) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getDesignationDetailsForMobile.do?id=' + data);
    }
    public adminsaveBookingDetails(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/adminsaveBookingDetails.do', data);
        // return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/adminsaveBookingDetails/' + type + '/' + reqType + '.do', data);
    }
    public getApproveListDetails(cityid, ghid) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/allProcessApplicationListForMobile.do?cityName=' + cityid + '&guesthouseName=' + ghid);
    }
    public getConvayanceListDetails(cityid, ghid) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getAllConveyanceDataForApproveForMobile.do?cityName=' + cityid + '&guesthouseName=' + ghid);
    }
    public getModifiedListDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllModifiedDataForApproveForMobile.do', data);
    }
    public getCancelListDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllCancelDataForApproveForMobile.do', data);
    }
    public getConferenceListDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAdminConfrenceRequestDetailsForMobile.do', data);
    }
    public saveProcessApplicationList(data) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/saveProcessApplicationListForMobile.do?cityId=' + data.cityId + '&guesthouseId=' + data.guesthouseId + '&sign=' + data.sign + '&selected=' + data.selected);
    }
    public allDraftApplicationList(cityId, guesthouseId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/allDraftApplicationListForMobile.do?cityName=' + cityId + '&guesthouseName=' + guesthouseId);
    }
    public cancelProcessRequest(processId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelProcessRequestForMobile.do?processId=' + processId);
    }
    public confirmProcess(processId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/confirmProcessForMobile.do?id=' + processId);
    }
    // -------------------------------------------------------------Approve----------------------------------------------------
    public getLocList() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getLocList.do');
    }

    // -------------------------------------------------------------Receptionist----------------------------------------------------
    public receptionistReport(data) {
        console.log("report data", data);
        // return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getShiftReportList.do?cId='+data.cId+'&gId='+data.gId+'&shiftId='+data.shiftId+'&createdBy='+data.createdBy+'&createdDate='+data.createdDate);
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getShiftReportList.do', data);
    }
    public addNewShift(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveNewShiftData.do', data);
    }
    public updateShiftData(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateShiftData.do', data);
    }
    public closePreviousShift(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/closePreviousShift.do', data);
    }
    // ------------------------------------------------feedback-----------------------------------------
    public getAdminFeedbackDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAdminFeedbackDetailsListForMobile.do',data);
        // return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAdminFeedbackDetailsListForMobile.do?cId=' + data.cId + '&gId=' + data.gId + '&reqType=' + data.reqType + '&name=' + data.name + '&bookingId=' + data.bookingId);
    }
    public getFeedBackCount(data) {
        //     const params = new HttpParams()
        // .set('cId', data.cId)
        // .set('gId', data.gId);
        // return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getTotalFeedbackCountForMobile.do', { params });
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getTotalFeedbackCountForMobile.do?cId=' + data.cId + '&gId=' + data.gId);
    }
    public getFeedbackReportDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getFeedbackReportnewForMobile.do', data);
    }
    public guestFeedbackDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/guestFeedbackReportDetailsForMobile.do', data);
    }
    public guestFeedbackCommentDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCommentFeedbackReportForMobile.do', data);
    }
    public guestTotalFeedbackDetails(data) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getTotalFeedbackReportForMobile.do?cId=' + data.cId + '&gId=' + data.gId + '&fromDate=' + data.fromDate + '&toDate=' + data.toDate + '&year=' + data.year + '&month=' + data.month);
    }
    public saveFeedbackRepliedDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveFeedbackRepliedDetailsForMobile.do', data);
    }
    // public getReplyFeedbackDetails(data) {
    //     return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getFeedbackRepliedDetailsForMobile.do',data);
    // }

    public getReplyFeedbackDetails(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getFeedbackRepliedDetailsForMobile.do', data);
    }
    public openReplyFeedback(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/openReplyFeedbackForMobile.do?feedbackId=' + id);
    }


    public getCurrentShift(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCurrentShift.do', data);
    }
    public checkCurrentShift(data) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/checkForShift.do?cityId=' + data.cityId + '&blockId=' + data.blockId + '&page=');
    }


    // Dipti Block Starts

    public splitViewForMobile(obj) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/splitViewForMobile.do?city=' + obj.city + '&gHouse=' + obj.gh + '&pageNum=' + obj.pageNo + '&pageSize=' + obj.pageSize + '&nameForSplit=' + obj.nameForSplit+ '&bookingIdForSplit=' + obj.bookingIdForSplit );
    }
    public getCancellationLstDtlsAccToReqForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCancellationLstDtlsAccToReqForMobile.do', data);
    }
    public cancelRequest1ForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/cancelRequest1ForMobile.do', data);
    }
    public partialCancelForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/partialCancelForMobile.do', data);
    }
    public getSplitBookingDetailsForMobile(data) {
        return this.httpClient.getAuthWithHeader(AppConstants.BASE_API_URL + 'mobile/getSplitBookingDetailsForMobile.do', data);
    }
    public addOrModifyModeratorDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/addOrModifyModeratorDetailsForMobile.do', data);
    }
    public partialCancelById(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/partialCancelByIdForMobile.do?bookingId=' + id + '&type=MOD');
    }
    public saveCancelModerationForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveCancelModerationForMobile.do', data);
    }
    public getTotalRoomsForMobile(bookingId, cityId, ghId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getTotalRoomsForMobile.do?bookingId=' + bookingId + '&cityId=' + cityId + '&ghId=' + ghId);
    }
    public cancelReservationForAllot(bookingId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelReservationForAllotForMobile.do?reqId=' + bookingId);
    }
    public partialCancelForAllot(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/partialCancelAllotForMobile.do', data);
    }
    public cancelAllotmentForMobile(bookingId, modId, mrrId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelAllotmentForMobile.do?bookingId=' + bookingId + '&modId=' + modId + '&mrrId=' + mrrId);
    }
    public allotRoomsForMobile(data, type) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/allotRoomsForMobile.do?type=' + type, data);
    }
    public modModifyRoomForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/modModifyRoomForMobile.do', data);
    }
    public getTotalBedsForMobile(bookingId, cityId, ghId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getTotalBedsForMobile.do?bookingId=' + bookingId + '&cityId=' + cityId + '&ghId=' + ghId);
    }
    public checkShift(cityId, ghId, page) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/checkForShift.do?cityId=' + cityId + '&blockId=' + ghId + '&page=' + page);
    }
    public getCheckinDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getCheckinDetailsForMobile.do', data)
    }
    public viewCheckinDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewCheckinDetailsForMobile.do', data)
    }
    public getAllReceiptListForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllReceiptListForMobile.do', data)
    }
    public viewCheckOutDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewCheckOutDetailsForMobile.do', data)
    }
    public getAllReceiptForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllReceiptListForMobile.do', data)
    }
    public getConfListForModerationForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfListForModerationForMobile.do', data)
    }
    public getModListForViewForMobile(cityId, ghId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getModListForViewForMobile.do?cId=' + cityId + '&gId=' + ghId)
    }
    public allProcessApplicationListmoderatorViewForMobile(cityId, ghId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/allProcessApplicationListmoderatorViewForMobile.do?cityName=' + cityId + '&guesthouseName=' + ghId)
    }
    public getAllConferenceDataForApproveForMobile(cityId, ghId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getAllConferenceDataForApproveForMobile.do?cityName=' + cityId + '&guesthouseName=' + ghId)
    }
    public getAllotTimeAllConveyanceDataForApproveForMobile(cityId, ghId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getAllotTimeAllConveyanceDataForApproveForMobile.do?cityName=' + cityId + '&guesthouseName=' + ghId)
    }
    public getAllCancelDataForApproveAllotTimeForMobile(cId, gId) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllCancelDataForApproveAllotTimeForMobile.do', { cId: cId, gId: gId })
    }
    public getAllModifiedDataForApproveAllotTimeForMobile(cId, gId) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAllModifiedDataForApproveAllotTimeForMobile.do', { cId: cId, gId: gId })
    }
    public showBillDetailsForMobile(bookingId, billId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/showBillDetailsForMobile.do?bookingId=' + bookingId + '&billId=' + billId)
    }
    public getBillDetailsByBookingIdForMobile(bookingId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getBillDetailsByBookingIdForMobile.do?bookingId=' + bookingId)
    }
    public cancelCheckoutDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/cancelCheckoutDetailsForMobile.do', data)
    }
    public getReceiptDetailsForMobile(bookingId, billId) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getReceiptDetailsForMobile.do?bookingId=' + bookingId + '&billId=' + billId)
    }
    public saveReceiptForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveReceiptForMobile.do', data)
    }
    public getOccupancyForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getOccupancyForMobile.do', data)
    }
    public saveBillDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveBillDetailsForMobile.do', data)
    }
    public billingViewForMobile() {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/billingViewForMobile.do')
    }
    public saveCheckOutDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveCheckOutDetailsForMobile.do', data)
    }
    public viewIndividualCheckinSlipDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewIndividualCheckinSlipDetailsForMobile.do', data)
    }
    public viewCheckInCancelDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewCheckInCancelDetailsForMobile.do', data)
    }
    public editCheckInCancelDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/editCheckInCancelDetailsForMobile.do', data)
    }
    public updateCheckOutCancelDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateCheckOutCancelDetailsForMobile.do', data)
    }
    public getConfrenceRequestDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getAdminConfrenceRequestDetailsForMobile.do', data)
    }
    public cancelRequestForMobile(reqId, mobile) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelRequestForMobile.do?reqId=' + reqId + '&mobile=' + mobile)
    }
    public receptionistaddBookingDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/receptionistaddBookingDetailsForMobile.do', data)
    }
    public getExtensionCheckinDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getExtensionCheckinDetailsForMobile.do', data)
    }
    public editBillingDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/editBillingDetailsForMobile.do', data)
    }
    public saveRecipetDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveRecipetDetailsForMobile.do', data)
    }
    public saveBillingDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveBillingDetailsForMobile.do', data)
    }
    public cancelConfrenceDetailsForMobile(reqId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelConfrenceDetailsForMobile.do?ReqId=' + reqId)
    }
    public getRecieptDetailsForMobile(billId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getRecieptDetailsForMobile.do?billId=' + billId)
    }
    public getConfrenceTypeDetails(obj) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfrenceTypeDetailsForMobile.do', obj);
    }
    public saveConferenceDetails(data: object,type,reqType) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/adminReceptionistaddBookingDetailsForConfrenceForMobile.do?type=' + type + '&reqType=' + reqType , data);
    }
    public getConfrenceTimePeriodDetailsForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfrenceTimePeriodDetailsForMobile.do', data)
    }
    public getConfrenceAmountForMobile(data: object) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getConfrenceAmountForMobile.do', data)
    }
    public checkAvailability(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/checkAvailabilityForMobile.do', data);
    }
    public viewExtensionCheckinDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewExtensionCheckinDetailsForMobile.do', data);
    }
    public saveExtensionCheckinDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveExtensionCheckinDetailsForMobile.do', data);
    }
    public saveCheckinDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveCheckinDetailsForMobile.do', data);
    }
    public viewAllotmentRoomByReqIdForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewAllotmentRoomByReqIdForMobile.do', data);
    }
    public getGadRequestTableDataForExtension(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getGadRequestTableDataForExtensionForMobile.do', data);
    }
    public updateBookingExtensionForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateAdminBookingExtensionForMobile.do', data);
    }
    public getVehicleAllotedDataForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getVehicleAllotedDataForMobile.do', data);
    }
    public getDriverDetailsForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/getDriverDetailsForMobile.do', data);
    }
    public saveVehileAllotMentForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/saveVehileAllotMentForMobile.do', data);
    }
    public editVehileAllotMentViewForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/editVehileAllotMentViewForMobile.do', data);
    }
    public updateVehileAllotMentForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/updateVehileAllotMentForMobile.do', data);
    }
    public viewVehicleAllotmentReportForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/viewVehicleAllotmentReportForMobile.do', data);
    }
    public confModeration(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/confModerationForMobile.do', data);
    }
    public cancelAllotRoomPerReqIdForMobile(data) {
        return this.httpClient.postWithAccessToken(AppConstants.BASE_API_URL + 'mobile/cancelAllotRoomPerReqIdForMobile.do', data);
    }
    public cancelVehicleAllotmentForMobile(reqId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelVehicleAllotmentForMobile.do?ReqId=' + reqId);
    }
    public cancelBillForMobile(reqId,billId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/cancelBillForMobile.do?bookingId=' + reqId + '&billId=' + billId);
    }
    public getReceiptPdfForMobile(recId) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getReceiptPdfForMobile.do?receiptId=' + recId);
    }
    public getDetailsByprocessId(id) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getDetailsByprocessIdForMobile.do?id=' + id);
    }
    public getAllDetailsByProcessidforOfficeOrderForMobile(id,type) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getAllDetailsByProcessidforOfficeOrderForMobile.do?id=' + id + '&type=' + type);
    }

    // Dipti Block Ends
    //sandip block starts
    public getShiftDetails(cId,gId,createdBy,createdDate) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getOpenShiftReportListForMobile.do?cId='+cId+'&gId='+gId+'&createdBy='+createdBy+'&createdDate='+createdDate+'');
    }
    public getDetailsByShiftId(Shiftid) {
        return this.httpClient.getAuth(AppConstants.BASE_API_URL + 'mobile/getCurrentOpenShiftForMobile.do?shiftId='+Shiftid);
    }
    //sandip block ends







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
