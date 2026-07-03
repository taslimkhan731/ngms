import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { MdmService } from '../Services/MDM/mdm.service';

@Component({
  selector: 'app-after-login',
  templateUrl: './after-login.page.html',
  styleUrls: ['./after-login.page.scss'],
})

export class AfterLoginPage implements OnInit {
  imageUrl = localStorage.getItem('Profile_Pic');
  public selectedMenu;
  public labels = ['Family', 'Friends', 'Notes', 'Work', 'Travel', 'Reminders'];
  public menus = [
    {
      title: 'Dashboard',
      url: '/after-login/pages/guest/dashboard',
      icon: 'home',
    },
    // {
    //   title: 'View Booking',
    //   url: '/after-login/pages/guest/view-booking',
    //   icon: 'home'
    // },
    {
      title: 'View Application',
      icon: 'business',
      submenus: [
        {
          title: 'Odisha Bhawan',
          url: '/after-login/pages/guest/view-booking/guest-house',
          icon: 'save'
        },
        {
          title: 'Conference',
          url: '/after-login/pages/guest/view-booking/conference',
          icon: 'save'
        },
        // {
        //   title: 'Conveyance',
        //   url: '/after-login/pages/guest/view-booking/conveyance',
        //   icon: 'eye'
        // },
      ]
    },
    {
      title: 'Change Password',
      url: '/after-login/pages/guest/change-password',
      icon: 'document-lock',
    },
    {
      title: 'Feedback',
      url: '/after-login/pages/guest/Feedback',
      icon: 'mail-unread',
    },
    // {
    //   title: 'Application Menu',
    //   icon: 'home',
    //   submenus:[
    //     {
    //       title: 'Application Entry',
    //       url: '/after-login/pages/orgadmin/application-entry',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Moderator',
    //       url: '/after-login/pages/orgadmin/moderator',
    //       icon: 'eye'
    //     },

    //     {
    //       title: 'Allotment',
    //       url: '/after-login/pages/orgadmin/GH-menu/allotment',
    //       icon: 'eye'
    //     },
    //   ]
    // },
    // {
    //   title: 'GH Menu',
    //   icon: 'home',
    //   submenus:[
    //     {
    //       title: 'Approver',
    //       url: '/after-login/pages/orgadmin/approver',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Receptionist Shift',
    //       url: '/after-login/pages/orgadmin/GH-menu/Receptionist-shift',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Vehicle Allotment',
    //       url: '/after-login/pages/orgadmin/GH-menu/vehicle-allotment',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'CheckIn/CheckOut',
    //       url: '/after-login/pages/orgadmin/GH-menu/checkin-checkout',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Billing',
    //       url: '/after-login/pages/orgadmin/GH-menu/billing',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Receipt',
    //       url: '/after-login/pages/orgadmin/GH-menu/receipt',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Occupancy Chart',
    //       url: '/after-login/pages/orgadmin/GH-menu/occupancy-chart',
    //       icon: 'eye'
    //     },
    //     {
    //       title: 'Extention Checkin',
    //       url: '/after-login/pages/orgadmin/GH-menu/extention-checkin',
    //       icon: 'eye'
    //     },{
    //       title: 'Current Booking',
    //       url: '/after-login/pages/orgadmin/GH-menu/current-booking',
    //       icon: 'eye'
    //     },

    //   ]
    // },

  ]
  iconchange: boolean = false;
  name: string;
  activeMenu: any
  userName: any;
  gender:any;
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public alertController: AlertController,
    private mdmService: MdmService,
  ) { }

  ngOnInit() {
    this.selectedMenu='Dashboard'
    // console.log(this.activatedRoute)
    // console.log(this.router)
    // console.log(this.activatedRoute.snapshot.paramMap.get('title'));
    // this.folder = this.activatedRoute.snapshot.paramMap.get('title');
    // this.folder = 'Inbox';

    // this.activeMenu=localStorage.getItem('selected_menu')
    this.useProfileDetails()
  }

  // ngOnInit() {
  //   const path = window.location.pathname.split("folder/")[1];
  //   if (path !== undefined) {
  //     this.selectedIndex = this.appPages.findIndex(
  //       page => page.title.toLowerCase() === path.toLowerCase()
  //     );
  //   }
  // }
  useProfileDetails() {
    this.mdmService.getProfileDetails().subscribe(res => {
      if (res.status == 1) {
        this.userName = res.data.firstName;
        this.gender = res.data.guestGender;
        console.log("Nmae is", res.data )
      }
    })
  }
  refreshing(event: any) {
    console.log("Begin async operation");
    setTimeout(() => {
      console.log("Async operation has ended");
      event.target.complete();
    }, 2000);
  }

  getUrl(menu) {
    this.selectedMenu = menu.title;
    this.router.navigate([menu.url]);
  }
  getProfile() {
    this.selectedMenu='';
    this.router.navigate(['/after-login/pages/guest/profile/view-profile'])
  }
  goToLink(url: string) {
    window.open(url, "_blank");
  }
  async logout() {
    const alert = await this.alertController.create({
      cssClass: 'my-custom-class',
      header: 'Logout Confirmation',
      message: 'Are you sure to logout from app?',
      buttons: [
        {
          text: 'Yes',
          handler: () => {
            localStorage.clear();
            this.router.navigateByUrl('/before-login-landing/login');
          },
        },

        {
          text: 'No',
          handler: () => {
            console.log('Confirm No');
          },
        },

      ],
    });
    await alert.present();
  }
  async ionViewWillEnter (){
    this.selectedMenu='Dashboard'
    this.useProfileDetails()
  }
}
