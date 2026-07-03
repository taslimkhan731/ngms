import { Injectable } from '@angular/core';
import { AppConstants } from '../../../app/app.constants';
import { StorageService } from '../storage/storage.service';
@Injectable({
  providedIn: 'root'
})
export class SessionCheckerService {
  private sessionNotValid = false;
  private timeOutId: any;

  constructor(
      // private authService: AuthenticationService,
      private appStorage: StorageService
  ) {

  }

  getSessionValid(): Boolean {
      if (this.appStorage.getSessionAttribute('isLoggedIn')) {
          return this.sessionNotValid;
      } else {
          return null;
      }
  }

  private setSessionValid(data: boolean) {
      this.sessionNotValid = data;
  }

  syncSession(data: number) {
      this.timeOutId = setTimeout(() => {
          this.setSessionValid(true);
      }, (data - AppConstants.SESSION_PRE_CHECK) * 1000); // data
  }

  resetSession() {
      this.setSessionValid(false);
      // console.log(this.timeOutId);
      if (this.timeOutId !== undefined) {
          clearTimeout(this.timeOutId);
      }

  }
}
