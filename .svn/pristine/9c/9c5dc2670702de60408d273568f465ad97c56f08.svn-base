import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CitizenDashboardComponent } from './Guest-Folder/citizen-dashboard/citizen-dashboard.component';
import { CreateBookingComponent } from './Guest-Folder/create-booking/create-booking.component';
import { ViewBookingComponent } from './Guest-Folder/view-booking/view-booking.component';
import { AddGuestComponent } from './Orgadmin-Folder/application-menu/add-guest/add-guest.component';
import { ApplicationEntryComponent } from './Orgadmin-Folder/application-menu/application-entry/application-entry.component';
import { SplitReservationComponent } from './Orgadmin-Folder/application-menu/application-entry/split-reservation/split-reservation.component';
import { ApproverComponent } from './Orgadmin-Folder/application-menu/approver/approver.component';
import { ConfirmListComponent } from './Orgadmin-Folder/application-menu/approver/confirm-list/confirm-list.component';
import { DraftListComponent } from './Orgadmin-Folder/application-menu/approver/draft-list/draft-list.component';
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
import { ConveyanceComponent } from './Guest-Folder/conveyance/conveyance.component';
import { ChangePasswordComponent } from './Guest-Folder/change-password/change-password.component';
import { FeedbackComponent } from './Guest-Folder/feedback/feedback.component';
import { ViewProfileComponent } from './Guest-Folder/profile/view-profile/view-profile.component';
import { UpdateProfileComponent } from './Guest-Folder/profile/update-profile/update-profile.component';
import { PaymentDetailsConferenceComponent } from './Guest-Folder/create-booking/payment-details-conference/payment-details-conference.component';
import { PaymentResponseComponent } from './Guest-Folder/create-booking/payment-response/payment-response.component';
import { ViewConferenceComponent } from './Guest-Folder/view-booking/view-conference/view-conference.component';
import { VieweditConferenceComponent } from './Guest-Folder/create-booking/viewedit-conference/viewedit-conference.component';

const routes: Routes = [
  {
    path: '', component: PagesPage, children: [
      //   { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      //   { path: 'dashboard', component: CitizenDashboardComponent },
      //   {
      //     path: 'create-booking',
      //     children: [
      //       { path: '', component: CreateBookingComponent },
      //       { path: 'view', component: CreateBookingComponent },
      //       { path: 'edit', component: CreateBookingComponent }
      //     ]
      //   },
      //   { path: 'view-booking', component: ViewBookingComponent },
      //   {path:'demo-filee',
      //   children:[
      //     { path: 'demo-1', component: Demo1Component },
      //     { path: 'demo-2', component: Demo2Component },
      //   ]
      // }
      { path: '', redirectTo: 'guest', pathMatch: 'full' },
      {
        path: 'guest', children: [
          { path: '', component: CitizenDashboardComponent },
          { path: 'dashboard', component: CitizenDashboardComponent },
          {
            path: 'create-booking', children: [
              { path: '', component: CreateBookingComponent },
              { path: 'view', component: CreateBookingComponent },
              { path: 'edit', component: CreateBookingComponent },
              { path: 'viewConference', component: VieweditConferenceComponent },
              { path: 'editConference', component: VieweditConferenceComponent },
              { path: 'paymentDetails', component: PaymentDetailsConferenceComponent },
              { path: 'paymentResponse', component: PaymentResponseComponent },
            ]
          },
          { path: 'Feedback', component: FeedbackComponent },
          { path: 'change-password', component: ChangePasswordComponent },
          {
            path: 'view-booking', children: [
              { path: 'guest-house', component: ViewBookingComponent },
              { path: 'conveyance', component: ConveyanceComponent },
              { path: 'conference', component: ViewConferenceComponent },
            ]
          },
          {
            path: 'profile', children: [
              { path: 'view-profile', component: ViewProfileComponent },
              { path: 'edit-profile', component: UpdateProfileComponent },
            ]
          }
        ]
      },
      {
        path: 'orgadmin', children: [
          { path: 'application-entry', component: ApplicationEntryComponent },
          { path: 'add', component: AddGuestComponent },
          { path: 'split', component: SplitReservationComponent },
          { path: 'add', component: AddGuestComponent },
          { path: 'edit', component: AddGuestComponent },
          { path: 'view', component: AddGuestComponent },
          { path: 'moderator', component: ModeratorComponent },
          { path: 'approver', component: ApproverComponent },
          { path: 'draft-list', component: DraftListComponent },
          { path: 'Confirm-list', component: ConfirmListComponent },


          {
            path: 'GH-menu', children: [
              { path: 'allotment', component: AllotmentComponent },
              { path: 'billing', component: BillingComponent },
              { path: 'receipt', component: ReceiptComponent },
              { path: 'Receptionist-shift', component: ReceptionistShiftComponent },
              { path: 'vehicle-allotment', component: VehicleAllotmentComponent },
              { path: 'checkin-checkout', component: CheckinCheckoutComponent },
              { path: 'occupancy-chart', component: OccupancyChartComponent },
              { path: 'extention-checkin', component: ExtentionCheckinComponent },
              { path: 'current-booking', component: CurrentBookingComponent },

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
