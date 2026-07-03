import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { PagesPageRoutingModule } from './pages-routing.module';
import { PagesPage } from './pages.page';
import { ApplicationEntryComponent } from './Orgadmin-Folder/application-menu/application-entry/application-entry.component';
import { ApproverComponent } from './Orgadmin-Folder/application-menu/approver/approver.component';
import { ModeratorComponent } from './Orgadmin-Folder/application-menu/moderator/moderator.component';
import { AllotmentComponent } from './Orgadmin-Folder/GH-menu/allotment/allotment.component';
import { BillingComponent } from './Orgadmin-Folder/GH-menu/billing/billing.component';
import { CurrentBookingComponent } from './Orgadmin-Folder/GH-menu/current-booking/current-booking.component';
import { ReceiptComponent } from './Orgadmin-Folder/GH-menu/receipt/receipt.component';
import { AddGuestComponent } from './Orgadmin-Folder/application-menu/add-guest/add-guest.component';
import { FilterPageComponent } from './filter-page/filter-page.component';
import { OccupancyChartComponent } from './Orgadmin-Folder/GH-menu/occupancy-chart/occupancy-chart.component';
import { ExtentionCheckinComponent } from './Orgadmin-Folder/GH-menu/extention-checkin/extention-checkin.component';
import { SplitReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/split-reservation/split-reservation.component';
import { ReceptionistShiftComponent } from './Orgadmin-Folder/GH-menu/receptionist-shift/receptionist-shift.component';
import { VehicleAllotmentComponent } from './Orgadmin-Folder/GH-menu/vehicle-allotment/vehicle-allotment.component';
import { CheckinCheckoutComponent } from './Orgadmin-Folder/GH-menu/checkin-checkout/checkin-checkout.component';
import { DashboardComponent } from './Orgadmin-Folder/dashboard/dashboard.component';
import { ChangePasswordComponent } from './Orgadmin-Folder/change-password/change-password.component';
import { EditProfileComponent } from './Orgadmin-Folder/profile/edit-profile/edit-profile.component';
import { ViewProfileComponent } from './Orgadmin-Folder/profile/view-profile/view-profile.component';
import { AllotmentCancelComponent } from './Orgadmin-Folder/GH-menu/allotment/allotment-cancel/allotment-cancel.component';
import { AddGuestDetailsComponent } from './Orgadmin-Folder/application-menu/application-entry/add-guest-details/add-guest-details.component';
import { ModeratorSaveComponent } from './Orgadmin-Folder/application-menu/moderator/moderator-save/moderator-save.component';
import { ConferenceHallDetailsComponent } from './Orgadmin-Folder/GH-menu/conference-hall-details/conference-hall-details.component';
import { FeedbackComponent } from './Orgadmin-Folder/GH-menu/feedback/feedback.component';
import { ConferenceButtonComponent } from './Orgadmin-Folder/GH-menu/conference-hall-details/conference-button/conference-button.component';
import { AddchangecanAllotmentComponent } from './Orgadmin-Folder/GH-menu/allotment/addchangecan-allotment/addchangecan-allotment.component';
import { CancelReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/cancel-reservation/cancel-reservation.component';
import { CancelModalComponent } from './Orgadmin-Folder/application-menu/application-entry/cancel-reservation/cancel-modal/cancel-modal.component';
import { ModeratorCancelComponent } from './Orgadmin-Folder/application-menu/moderator/moderator-cancel/moderator-cancel.component';
import { CommentFeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/comment-feedback-report/comment-feedback-report.component';
import { FeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/feedback-report/feedback-report.component';
import { GuestFeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/guest-feedback-report/guest-feedback-report.component';
import { ReplyFeedbackComponent } from './Orgadmin-Folder/GH-menu/feedback/reply-feedback/reply-feedback.component';

import { TotalFeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/total-feedback-report/total-feedback-report.component';
import { ViewCheckinoutModalComponent } from './Orgadmin-Folder/GH-menu/checkin-checkout/view-checkinout-modal/view-checkinout-modal.component';
import { ViewReceiptComponent } from './Orgadmin-Folder/GH-menu/receipt/view-receipt/view-receipt.component';
import { MakeReceiptComponent } from './Orgadmin-Folder/GH-menu/receipt/make-receipt/make-receipt.component';
import { DraftListComponent } from './Orgadmin-Folder/application-menu/approver/draft-list/draft-list.component';
import { ModifyModerationComponent } from './Orgadmin-Folder/application-menu/moderator/modify-moderation/modify-moderation.component';
import { PreviewAllComponent } from './Orgadmin-Folder/GH-menu/allotment/preview-all/preview-all.component';
import { BillDetailsComponent } from './Orgadmin-Folder/GH-menu/billing/bill-details/bill-details.component';
import { CreateBillComponent } from './Orgadmin-Folder/GH-menu/billing/create-bill/create-bill.component';
import { CancelCheckoutComponent } from './Orgadmin-Folder/GH-menu/billing/cancel-checkout/cancel-checkout.component';
import { ViewCheckinslipComponent } from './Orgadmin-Folder/GH-menu/checkin-checkout/view-checkinslip/view-checkinslip.component';
import { EditCancelCheckinoutComponent } from './Orgadmin-Folder/GH-menu/checkin-checkout/edit-cancel-checkinout/edit-cancel-checkinout.component';
import { NewRequestComponent } from './Orgadmin-Folder/GH-menu/current-booking/new-request/new-request.component';
import { CurrentBookingModerationComponent } from './Orgadmin-Folder/GH-menu/current-booking/current-booking-moderation/current-booking-moderation.component';
import { ExtensionCheckinModalComponent } from './Orgadmin-Folder/GH-menu/extention-checkin/extension-checkin-modal/extension-checkin-modal.component';
import { TermsandconditionComponent } from './Orgadmin-Folder/GH-menu/current-booking/termsandcondition/termsandcondition.component';
import { CancelAllotmentComponent } from './Orgadmin-Folder/GH-menu/checkin-checkout/cancel-allotment/cancel-allotment.component';
import { ExtendReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/extend-reservation/extend-reservation.component';
import { ExtendFormComponent } from './Orgadmin-Folder/application-menu/application-entry/extend-reservation/extend-form/extend-form.component';
import { AddEditVehicleComponent } from './Orgadmin-Folder/GH-menu/vehicle-allotment/add-edit-vehicle/add-edit-vehicle.component';
import { DriverListComponent } from './Orgadmin-Folder/GH-menu/vehicle-allotment/driver-list/driver-list.component';
import { ViewDetailsComponent } from './Orgadmin-Folder/GH-menu/allotment/view-details/view-details.component';
import { ConferenceModComponent } from './Orgadmin-Folder/application-menu/moderator/conference-mod/conference-mod.component';
import { FeedbackModalComponent } from './Orgadmin-Folder/GH-menu/feedback/feedback-modal/feedback-modal.component';
import { ConfirmationComponent } from './Orgadmin-Folder/application-menu/approver/confirmation/confirmation.component';
import { TooltipModule } from 'ng2-tooltip-directive';
import { ShiftComponent } from './Orgadmin-Folder/GH-menu/shift/shift.component';
import { CloseShiftComponent } from './Orgadmin-Folder/GH-menu/shift/close-shift/close-shift.component';
import { OfficeOrderComponent } from './Orgadmin-Folder/application-menu/approver/office-order/office-order.component';
// import { ViewBookingComponent } from './view-booking/view-booking.component';
// import { CreateBookingComponent } from './create-booking/create-booking.component';
// import { CitizenDashboardComponent } from './citizen-dashboard/citizen-dashboard.component';
// import { ModalViewComponent } from './modal-view/modal-view.component';
// import { HeadingToolbarComponent } from '../heading-toolbar/heading-toolbar.component';
// import { Demo1Component } from './demo-folder/demo1/demo1.component';
// import { Demo2Component } from './demo-folder/demo2/demo2.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule, ReactiveFormsModule,
    IonicModule,
    TooltipModule,
    PagesPageRoutingModule,
  ],
  declarations:
    [
      PagesPage,
      ApplicationEntryComponent,
      ApproverComponent,
      DraftListComponent,
      ModeratorComponent,
      AllotmentComponent,
      BillingComponent,
      CurrentBookingComponent,
      ReceiptComponent,
      AddGuestComponent,
      FilterPageComponent,
      OccupancyChartComponent,
      ExtentionCheckinComponent,
      SplitReservationComponent,

      ReceptionistShiftComponent,
      VehicleAllotmentComponent,
      CheckinCheckoutComponent,
      DashboardComponent,
      ChangePasswordComponent,
      EditProfileComponent,
      ViewProfileComponent,
      AllotmentCancelComponent,
      AddGuestDetailsComponent,
      ModeratorSaveComponent,
      ConferenceHallDetailsComponent,
      FeedbackComponent,
      ConferenceButtonComponent,
      FeedbackComponent,
      CommentFeedbackReportComponent,
      FeedbackReportComponent,
      ReplyFeedbackComponent,
      FeedbackModalComponent,
      TotalFeedbackReportComponent,
      GuestFeedbackReportComponent,
      AddchangecanAllotmentComponent,
      ModeratorSaveComponent,
      CancelReservationComponent,
      CancelModalComponent,
      ModeratorCancelComponent,
      ViewCheckinoutModalComponent,
      ViewReceiptComponent,
      MakeReceiptComponent,
      ModifyModerationComponent,
      PreviewAllComponent,
      BillDetailsComponent,
      CreateBillComponent,
      CancelCheckoutComponent,
      ViewCheckinslipComponent,
      EditCancelCheckinoutComponent,
      NewRequestComponent,
      CurrentBookingModerationComponent,
      ExtensionCheckinModalComponent,
      TermsandconditionComponent,
      CancelAllotmentComponent,
      ExtendReservationComponent,
      ExtendFormComponent,
      AddEditVehicleComponent,
      DriverListComponent,
      ViewDetailsComponent,
      ConferenceModComponent,
      ConfirmationComponent,
      ShiftComponent,
      CloseShiftComponent,
      OfficeOrderComponent
    ]
})
export class PagesPageModule { }
