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
  public loginType;
  public selectedMenu: string = "Home";
  public labels = ['Family', 'Friends', 'Notes', 'Work', 'Travel', 'Reminders'];
  public menus = []
  public menusForOrgadminOrApprover = [   {
    title: 'Dashboard',
    icon: 'home',
    url: '/after-login/pages/orgadmin'
  },
  {
    title: 'Application Menu',
    icon: 'grid',
    submenus: [
      {
        title: 'Application Entry',
        url: '/after-login/pages/orgadmin/application-menu/application-entry',
        icon: 'create'
      },
      {
        title: 'Moderator',
        url: '/after-login/pages/orgadmin/moderator',
        icon: 'library'
      },

      {
        title: 'Allotment',
        url: '/after-login/pages/orgadmin/GH-menu/allotment',
        icon: 'bag-add'
      },
    ]
  },
  {
    title: 'GH Menu',
    icon: 'options',
    submenus: [
      {
        title: 'Approve',
        url: '/after-login/pages/orgadmin/approver',
        icon: 'checkmark-done'
      },
      {
        title: 'Receptionist Shift',
        url: '/after-login/pages/orgadmin/GH-menu/Receptionist-shift',
        icon: 'people'
      },
      {
        title: 'Vehicle Allotment',
        url: '/after-login/pages/orgadmin/GH-menu/vehicle-allotment',
        icon: 'car-sport'
      },
      {
        title: 'CheckIn/CheckOut',
        url: '/after-login/pages/orgadmin/GH-menu/checkin-checkout',
        icon: 'repeat'
      },
      {
        title: 'Billing',
        url: '/after-login/pages/orgadmin/GH-menu/billing',
        icon: 'newspaper'
      },
      {
        title: 'Receipt',
        url: '/after-login/pages/orgadmin/GH-menu/receipt',
        icon: 'reader'
      },
      {
        title: 'Occupancy Chart',
        url: '/after-login/pages/orgadmin/GH-menu/occupancy-chart',
        icon: 'list'
      },
      {
        title: 'Extension Checkin',
        url: '/after-login/pages/orgadmin/GH-menu/extention-checkin',
        icon: 'trending-up'
      }, {
        title: 'Current Booking',
        url: '/after-login/pages/orgadmin/GH-menu/current-booking',
        icon: 'pricetag'
      },
      {
        title: 'Conference Hall',
        url: '/after-login/pages/orgadmin/GH-menu/conference-hall-details',
        icon: 'home'
      },
      {
        title: 'Feedback',
        url: '/after-login/pages/orgadmin/GH-menu/feedback',
        icon: 'stats-chart'
      },
       {
        title: 'Shift',
        url: '/after-login/pages/orgadmin/GH-menu/shift',
        icon: 'stats-chart'
      }
    ]
  },]
  public menusForReceptionist = [
    {
      title: 'Dashboard',
      icon: 'home',
      url: '/after-login/pages/orgadmin'
    },
    {
      title: 'Application Menu',
      icon: 'grid',
      submenus: [
        {
          title: 'Allotment',
          url: '/after-login/pages/orgadmin/GH-menu/allotment',
          icon: 'bag-add'
        },
      ]
    },
    {
      title: 'GH Menu',
      icon: 'options',
      submenus: [
        {
          title: 'Receptionist Shift',
          url: '/after-login/pages/orgadmin/GH-menu/Receptionist-shift',
          icon: 'people'
        },
        {
          title: 'Vehicle Allotment',
          url: '/after-login/pages/orgadmin/GH-menu/vehicle-allotment',
          icon: 'car-sport'
        },
        {
          title: 'CheckIn/CheckOut',
          url: '/after-login/pages/orgadmin/GH-menu/checkin-checkout',
          icon: 'repeat'
        },
        {
          title: 'Billing',
          url: '/after-login/pages/orgadmin/GH-menu/billing',
          icon: 'newspaper'
        },
        {
          title: 'Receipt',
          url: '/after-login/pages/orgadmin/GH-menu/receipt',
          icon: 'reader'
        },
        {
          title: 'Occupancy Chart',
          url: '/after-login/pages/orgadmin/GH-menu/occupancy-chart',
          icon: 'list'
        },
        {
          title: 'Extension Checkin',
          url: '/after-login/pages/orgadmin/GH-menu/extention-checkin',
          icon: 'trending-up'
        }, {
          title: 'Current Booking',
          url: '/after-login/pages/orgadmin/GH-menu/current-booking',
          icon: 'pricetag'
        },
        {
          title: 'Conference Hall',
          url: '/after-login/pages/orgadmin/GH-menu/conference-hall-details',
          icon: 'home'
        },
      ]
    },
  ]
  public menusForManager =[
    {
      title: 'Dashboard',
      icon: 'home',
      url: '/after-login/pages/orgadmin'
    },
    {
      title: 'Application Menu',
      icon: 'grid',
      submenus: [
        {
          title: 'Allotment',
          url: '/after-login/pages/orgadmin/GH-menu/allotment',
          icon: 'bag-add'
        },
      ]
    },
    {
      title: 'GH Menu',
      icon: 'options',
      submenus: [
        {
          title: 'Receptionist Shift',
          url: '/after-login/pages/orgadmin/GH-menu/Receptionist-shift',
          icon: 'people'
        },
        {
          title: 'Vehicle Allotment',
          url: '/after-login/pages/orgadmin/GH-menu/vehicle-allotment',
          icon: 'car-sport'
        },
        {
          title: 'CheckIn/CheckOut',
          url: '/after-login/pages/orgadmin/GH-menu/checkin-checkout',
          icon: 'repeat'
        },
        {
          title: 'Billing',
          url: '/after-login/pages/orgadmin/GH-menu/billing',
          icon: 'newspaper'
        },
        {
          title: 'Receipt',
          url: '/after-login/pages/orgadmin/GH-menu/receipt',
          icon: 'reader'
        },
        {
          title: 'Occupancy Chart',
          url: '/after-login/pages/orgadmin/GH-menu/occupancy-chart',
          icon: 'list'
        },
        {
          title: 'Extension Checkin',
          url: '/after-login/pages/orgadmin/GH-menu/extention-checkin',
          icon: 'trending-up'
        }, {
          title: 'Current Booking',
          url: '/after-login/pages/orgadmin/GH-menu/current-booking',
          icon: 'pricetag'
        },
        {
          title: 'Conference Hall',
          url: '/after-login/pages/orgadmin/GH-menu/conference-hall-details',
          icon: 'home'
        },
        {
          title: 'Feedback',
          url: '/after-login/pages/orgadmin/GH-menu/feedback',
          icon: 'stats-chart'
        },
      ]
    },
  ]
  public menusForModerator = [   {
    title: 'Dashboard',
    icon: 'home',
    url: '/after-login/pages/orgadmin'
  },
  {
    title: 'Application Menu',
    icon: 'grid',
    submenus: [
      {
        title: 'Application Entry',
        url: '/after-login/pages/orgadmin/application-menu/application-entry',
        icon: 'create'
      },
      {
        title: 'Moderator',
        url: '/after-login/pages/orgadmin/moderator',
        icon: 'library'
      },

      {
        title: 'Allotment',
        url: '/after-login/pages/orgadmin/GH-menu/allotment',
        icon: 'bag-add'
      },
    ]
  },
  {
    title: 'GH Menu',
    icon: 'options',
    submenus: [
      {
        title: 'Approve',
        url: '/after-login/pages/orgadmin/approver',
        icon: 'checkmark-done'
      },
      {
        title: 'Occupancy Chart',
        url: '/after-login/pages/orgadmin/GH-menu/occupancy-chart',
        icon: 'list'
      },
      {
        title: 'Conference Hall',
        url: '/after-login/pages/orgadmin/GH-menu/conference-hall-details',
        icon: 'home'
      },

    ]
  },]
  iconchange: boolean = false;
  name: string;
  activeMenu: any
  openAccordion: number = -1;
  userName: any;
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public alertController: AlertController,
    private mdmService: MdmService,
  ) { }

  ngOnInit() {
    // console.log(this.activatedRoute)
    // console.log(this.router)
    // console.log(this.activatedRoute.snapshot.paramMap.get('title'));
    // this.folder = this.activatedRoute.snapshot.paramMap.get('title');
    // this.folder = 'Inbox';

    // this.activeMenu=localStorage.getItem('selected_menu')
    this.useProfileDetails()
    this.loginType = localStorage.getItem('loginType');
    this.allowResource();
  }

  ionViewWillEnter(){
    this.loginType = localStorage.getItem('loginType');
    this.allowResource();
  }


  toggleAccordion(index: number) {
    this.openAccordion = this.openAccordion === index ? -1 : index;
  }

  public allowResource(){
    if(this.loginType == 'ORGADMIN' || this.loginType == 'APPROVER') {
      this.menus = this.menusForOrgadminOrApprover
    } else if(this.loginType == 'MANAGER') {
      this.menus = this.menusForManager
    } else if(this.loginType == 'MODERATOR') {
      this.menus = this.menusForModerator
    } else if(this.loginType == 'RECEPTIONIST') {
      this.menus = this.menusForReceptionist
    }
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
        console.log("Nmae is", this.userName)
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
    this.router.navigate(['/after-login/pages/orgadmin/profile/view-profile'])
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
            this.router.navigateByUrl('/before-login-landing/login');
            localStorage.clear();
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
}
