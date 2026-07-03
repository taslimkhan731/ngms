import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddGuestComponent } from './Orgadmin-Folder/application-menu/add-guest/add-guest.component';
import { ApplicationEntryComponent } from './Orgadmin-Folder/application-menu/application-entry/application-entry.component';
import { SplitReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/split-reservation/split-reservation.component';
import { ApproverComponent } from './Orgadmin-Folder/application-menu/approver/approver.component';
import { ModeratorComponent } from './Orgadmin-Folder/application-menu/moderator/moderator.component';
import { AllotmentComponent } from './Orgadmin-Folder/GH-menu/allotment/allotment.component';
import { BillingComponent } from './Orgadmin-Folder/GH-menu/billing/billing.component';
import { CheckinCheckoutComponent } from './Orgadmin-Folder/GH-menu/checkin-checkout/checkin-checkout.component';
import { CurrentBookingComponent } from './Orgadmin-Folder/GH-menu/current-booking/current-booking.component';
import { ExtentionCheckinComponent } from './Orgadmin-Folder/GH-menu/extention-checkin/extention-checkin.component';
import { OccupancyChartComponent } from './Orgadmin-Folder/GH-menu/occupancy-chart/occupancy-chart.component';
import { ReceiptComponent } from './Orgadmin-Folder/GH-menu/receipt/receipt.component';
import { ReceptionistShiftComponent } from './Orgadmin-Folder/GH-menu/receptionist-shift/receptionist-shift.component';
import { VehicleAllotmentComponent } from './Orgadmin-Folder/GH-menu/vehicle-allotment/vehicle-allotment.component';
// import { CitizenDashboardComponent } from './citizen-dashboard/citizen-dashboard.component';
// import { CreateBookingComponent } from './create-booking/create-booking.component';
// import { Demo1Component } from './demo-folder/demo1/demo1.component';
// import { Demo2Component } from './demo-folder/demo2/demo2.component';


// import { ViewBookingComponent } from './view-booking/view-booking.component';
import { PagesPage } from './pages.page';
import { AllotmentCancelComponent } from './Orgadmin-Folder/GH-menu/allotment/allotment-cancel/allotment-cancel.component';
import { AddGuestDetailsComponent } from './Orgadmin-Folder/application-menu/application-entry/add-guest-details/add-guest-details.component';
import { ConferenceHallDetailsComponent } from './Orgadmin-Folder/GH-menu/conference-hall-details/conference-hall-details.component';
import { FeedbackComponent } from './Orgadmin-Folder/GH-menu/feedback/feedback.component';
import { ViewProfileComponent } from './Orgadmin-Folder/profile/view-profile/view-profile.component';
import { EditProfileComponent } from './Orgadmin-Folder/profile/edit-profile/edit-profile.component';
import { CancelReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/cancel-reservation/cancel-reservation.component';
import { ReplyFeedbackComponent } from './Orgadmin-Folder/GH-menu/feedback/reply-feedback/reply-feedback.component';
import { CommentFeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/comment-feedback-report/comment-feedback-report.component';
import { TotalFeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/total-feedback-report/total-feedback-report.component';
import { GuestFeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/guest-feedback-report/guest-feedback-report.component';
import { FeedbackReportComponent } from './Orgadmin-Folder/GH-menu/feedback/feedback-report/feedback-report.component';
import { ViewReceiptComponent } from './Orgadmin-Folder/GH-menu/receipt/view-receipt/view-receipt.component';
import { CreateBillComponent } from './Orgadmin-Folder/GH-menu/billing/create-bill/create-bill.component';
import { DraftListComponent } from './Orgadmin-Folder/application-menu/approver/draft-list/draft-list.component';
import { CurrentBookingModerationComponent } from './Orgadmin-Folder/GH-menu/current-booking/current-booking-moderation/current-booking-moderation.component';
import { ExtendReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/extend-reservation/extend-reservation.component';
import { DashboardComponent } from './Orgadmin-Folder/dashboard/dashboard.component';
import { ShiftComponent } from './Orgadmin-Folder/GH-menu/shift/shift.component';


const routes: Routes = [
  {
    path: '', component: PagesPage, children: [

      { path: '', redirectTo: 'orgadmin', pathMatch: 'full' },
      // {
      //   path: 'guest', children: [
      //     { path: '', component: CitizenDashboardComponent },
      //     { path: 'dashboard', component: CitizenDashboardComponent },
      //     {
      //       path: 'create-booking', children: [
      //         { path: '', component: CreateBookingComponent },
      //         { path: 'view', component: CreateBookingComponent },
      //         { path: 'edit', component: CreateBookingComponent }
      //       ]
      //     },
      //     { path: 'Feedback', component: FeedbackComponent },
      //     { path: 'change-password', component: ChangePasswordComponent },
      //     {
      //       path:'view-booking',children:[
      //         { path: 'guest-house', component: ViewBookingComponent },
      //         { path: 'conveyance', component: ConveyanceComponent },
      //       ]
      //     },
      //     {
      //       path:'profile',children:[
      //         { path: 'view-profile', component: ViewProfileComponent },
      //         { path: 'edit-profile', component: UpdateProfileComponent },
      //       ]
      //     }
      //   ]
      // },
      {
        path: 'orgadmin',
        children: [
          { path: '', component: DashboardComponent },
          // { path: '', component: ApplicationEntryComponent },
          {
            path: 'profile', children: [
              { path: 'view-profile', component: ViewProfileComponent },
              { path: 'edit-profile', component: EditProfileComponent },
            ]
          },
          // { path: 'application-entry', component: ApplicationEntryComponent },
          {
            path: 'application-menu', children: [
              { path: '', component: ApplicationEntryComponent },
              {
                path: 'application-entry', children: [
                  { path: '', component: ApplicationEntryComponent },
                  { path: 'add', component: AddGuestDetailsComponent },
                  { path: 'extend', component: ExtendReservationComponent },
                  { path: 'edit', component: AddGuestDetailsComponent },
                  { path: 'modEdit', component: AddGuestDetailsComponent },
                  { path: 'view', component: AddGuestDetailsComponent },
                  { path: 'updateSplit', component: AddGuestDetailsComponent },
                  { path: 'split', component: SplitReservationComponent },
                  { path: 'cancel', component: CancelReservationComponent },
                ]
              },
            ]
          },

          { path: 'moderator', component: ModeratorComponent },
          { path: 'approver', component: ApproverComponent },
          { path: 'draft-list', component: DraftListComponent },

          {
            path: 'GH-menu', children: [
              {
                path: 'allotment', children: [
                  { path: '', component: AllotmentComponent, },
                  { path: 'allotmentCancel', component: AllotmentCancelComponent }
                ]
              },
              {
                path: 'billing', children: [
                  { path: '', component: BillingComponent },
                  { path: 'createBill', component: CreateBillComponent }
                ]
              },
              {
                path: 'receipt', children: [
                  { path: '', component: ReceiptComponent },
                  { path: 'view-receipt', component: ViewReceiptComponent }
                ]
              },
              { path: 'Receptionist-shift', component: ReceptionistShiftComponent },
              { path: 'vehicle-allotment', component: VehicleAllotmentComponent },
              { path: 'checkin-checkout', component: CheckinCheckoutComponent },
              { path: 'occupancy-chart', component: OccupancyChartComponent },
              { path: 'extention-checkin', component: ExtentionCheckinComponent },
              {
                path: 'current-booking', children: [
                  { path: '', component: CurrentBookingComponent },
                  { path: 'moderation', component: CurrentBookingModerationComponent }
                ]
              },
              { path: 'conference-hall-details', component: ConferenceHallDetailsComponent },
              // { path: 'feedback', component: FeedbackComponent },
              {
                path: 'feedback', children: [
                  { path: '', component: FeedbackComponent, },
                  { path: 'reply-feedback', component: ReplyFeedbackComponent },
                  { path: 'feedback-report', component: FeedbackReportComponent },
                  { path: 'guest-feedback-report', component: GuestFeedbackReportComponent },
                  { path: 'total-feedback-report', component: TotalFeedbackReportComponent },
                  { path: 'comments-report', component: CommentFeedbackReportComponent }

                ]
              },
              { path: 'shift', component: ShiftComponent }
            ]
          }
        ]
      }

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesPageRoutingModule { }
