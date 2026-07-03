import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // { path: '', redirectTo: 'folder/Inbox', pathMatch: 'full' },
  { path: '', redirectTo: 'before-login-landing', pathMatch: 'full' },
  {
    path: 'before-login-landing',
    loadChildren: () => import('./before-login-landing/before-login-landing.module').then(m => m.BeforeLoginLandingPageModule)
  },
  {
    path: 'after-login',
    loadChildren: () => import('./after-login/after-login.module').then(m => m.AfterLoginPageModule)
  },
  {
    path: 'splash',
    loadChildren: () => import('./splash/splash.module').then( m => m.SplashPageModule)
  },
  
  // { path: 'error', component: ErrorDisplayComponent },
  //Wild Card Route for 404 request
  // { path: '**', component: ErrorDisplayComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules, useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
