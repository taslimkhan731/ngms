import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AfterLoginPage } from './after-login.page';

const routes: Routes = [
  {
    path: '', component: AfterLoginPage, children: [
      { path: '', redirectTo: 'pages', pathMatch: 'full' },
      {
        path: 'pages',
        loadChildren: () => import('./pages/pages.module').then(m => m.PagesPageModule)
      },
     
    ],
  },
  // {
  //   path: 'orgadmin',
  //   loadChildren: () => import('./orgadmin/orgadmin.module').then(m => m.OrgadminModule)
  // },
  // {
  //   path: 'components',
  //   loadChildren: () => import('./components/components.module').then(m => m.ComponentsPageModule)
  // }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AfterLoginPageRoutingModule { }
