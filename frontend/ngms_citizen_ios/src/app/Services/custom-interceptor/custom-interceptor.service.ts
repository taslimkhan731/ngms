import { Injectable } from '@angular/core';
import { HttpEvent, HttpRequest, HttpHandler, HttpInterceptor, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { retry, catchError, map, tap, retryWhen, delay, concatMap } from 'rxjs/operators';
import { ClientErrorHandlerService } from '../ErrorHandler/client-error-handler.service';
import { Router } from '@angular/router';

//@ 17-01-2022 vicky
export const retryCount = 1;
export const retryDelayMS  = 2000;
export const errorMsgs  = {
  '0': {
    errCode: 0,
    errName: 'Failed To Load Response',
    errDesc: 'Sorry, server is not responding at this movement, Pleasse try after sometime',
    buttonMsg: 'Back',
    redirectLink: ''
  },
  '401': {
    errCode: 401,
    errName: 'Access Denied',
    errDesc: 'Sorry, You are not authorized! , Your requested couldn\'t be authenticated',
    buttonMsg: 'Login',
    redirectLink: '/landing-page/login'
  },
  '403': {
    errCode: 403,
    errName: 'Access forbidden',
    errDesc: 'Sorry, Access to the requested resource is forbidden!, You don\'t have permission to access this page',
    buttonMsg: 'Back',
    redirectLink: ''
  },
  '404': {
    errCode: 404,
    errName: 'Page Not Found',
    errDesc: 'Sorry, We can\'t find that page! , Either something went wrong or page doesn\'t exist anymore',
    buttonMsg: 'Back',
    redirectLink: ''
  },
  '500': {
    errCode: 500,
    errName: 'Internal Server Error',
    errDesc: 'Sorry, We had some technical problem! , Either something went wrong or some problem occured during last operation',
    buttonMsg: 'Back',
    redirectLink: ''
  },
  'default':  {
    errCode: 'Error',
    errName: 'Unknown Error',
    errDesc: 'Something went wrong',
    buttonMsg: 'Login',
    redirectLink: ''
  }
};


@Injectable({
  providedIn: 'root'
})
export class CustomInterceptorService implements HttpInterceptor {

  public ifErrorHandled: boolean = false;

	constructor(private clientErrorHandlerService: ClientErrorHandlerService, private router: Router) {
		// console.log('constructor : HttpInterceptor');
	}

	intercept(request: HttpRequest < any > , next: HttpHandler): Observable < HttpEvent < any >> {
		// console.warn('intercept INVOKED');

    if(1) { //check url startsWith word (for intercept).
      return next.handle(request).pipe(
        retryWhen(error =>
            error.pipe(
                concatMap((error, count) => {
                    // console.warn('interceptor concatMap');
    
                    if (count < retryCount) {
                        if (error.status == 401) {
                            console.warn('interceptor retry');
                            return of(error); //  'returns back' from here and try again with out throwing error and continuing next line.
                            // can handel refresh token here.
                        }
                    }
                    return throwError(error); // throws error to the catchError
                }),
                delay(retryDelayMS),
            )
        ),
        map((event: HttpEvent<any>) => { // if no error
            if (event instanceof HttpResponse) {
                // console.warn('interceptor success map');
                return event;
            }
        }),
        catchError((error) => {
          if (error instanceof HttpErrorResponse) {
              console.warn('catchError HttpErrorResponse', error.error, error.status, error.statusText, error.type);
              if (error.error instanceof ErrorEvent) {
                  console.error("catchError Error Event");
              } else {
                  switch (error.status) {
                      case 401:  // login
                          this.router.navigate(['/landing-page/login'], { queryParams: errorMsgs[error.status] });
                          this.ifErrorHandled = true;
                          break;
                      case 403: // forbidden
                          this.router.navigate(['/landing-page/login'], { queryParams: errorMsgs[error.status] }); // window.location.href = "index.html#/error";
                          this.ifErrorHandled = true;
                          break;
                      case 500: // internal server error
                          this.router.navigate(['/error'], { queryParams: errorMsgs[error.status] });
                          this.ifErrorHandled = true;
                          break;
                      case 404: // page not found
                          this.router.navigate(['/error'], { queryParams: errorMsgs[error.status] });
                          this.ifErrorHandled = true;
                          break;
                      case 400: // bad request
                          // this.router.navigate(['/error'], { queryParams: errorMsgs[error.status] });
                          // this.ifErrorHandled = true;
                          break;
                  }
              }
          } else {
              console.error("Other Errors");
          }
      
          if (this.ifErrorHandled) {
              console.log('return back');
              return of(error);
          } else {
              console.log('throw error back to the subscriber');
              return throwError(error);
              // this.clientErrorHandlerService.handleError(error);    
          }
      
        })
      );
    } else {
      return next.handle(request);
    }

    // tap(evt => { // can use tap operator if we use return next.handle(request).do(
    //   if (evt instanceof HttpResponse) {
    // }),
		// .catch((response) => {
		//   return Observable.throw("some error");
		// });   
	}

}


    /////////////////////////////////////////////////////////////////////////////////////////////
    // return next.handle(cloneReq).pipe(
    //     retry(1),
    //     catchError((error: HttpErrorResponse) => {
    //         if (error.status === 401) {
    //             // refresh token
    //             // redirect to the login route
    //             // or show a modal
    //         } else {
    //             return throwError(error);
    //         }
    //     })
    // );
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // const httpRequest = request.clone({
    //   headers: new HttpHeaders({
    //     // 'Cache-Control': 'no-cache',
    //     // 'Pragma': 'no-cache',
    //     // 'Expires': '-1',
    //     // 'sdsds': '-8888888'
    //   })
    // });

    /////////////////////////////////////////////////////////////////////////////////////////////
    // const token: string = localStorage.getItem('token');
    // if (token) {
    //     request = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });
    // }

    /////////////////////////////////////////////////////////////////////////////////////////////
    // let clonedRequest = request;
    // if (!clonedRequest.headers.has('Content-Type')) {
    //   clonedRequest = clonedRequest.clone({ headers: clonedRequest.headers.append('Content-Type', 'application/json') });
    // }
    // clonedRequest = clonedRequest.clone({ headers: clonedRequest.headers.append('Accept', 'application/json') });
    // clonedRequest = clonedRequest.clone({ headers: clonedRequest.headers.append('Access-Control-Allow-Origin', '*') });
    // clonedRequest = clonedRequest.clone({ headers: clonedRequest.headers.append('Access-Control-Allow-Methods', '*') });
    // clonedRequest = clonedRequest.clone({ headers: clonedRequest.headers.append('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept') });
    // clonedRequest = clonedRequest.clone({ headers: clonedRequest.headers.append('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE') });

    /////////////////////////////////////////////////////////////////////////////////////////////
    // const headers = new Headers();
    // const headers = request.headers
    //     .append('Content-Type', 'application/json')
    //     .append('Accept', 'application/json')
    //     .append('Access-Control-Allow-Origin', '*')
    //     .append('Access-Control-Allow-Methods', '*')
    //     .append('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    // const cloneReq = request.clone({ headers });

    // .set('Authorization', `Bearer ${sessionStorage.getItem('authToken')}`);
    /////////////////////////////////////////////////////////////////////////////////////////////