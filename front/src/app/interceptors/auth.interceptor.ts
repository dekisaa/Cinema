import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {UserService} from "../../services/user.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor
{

  constructor(private _router: Router, private userService:UserService) { }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
  {
    let token = this.userService.getToken();
    console.log("TU")
    if (token != "null"){
      console.log("MOZE")
      request = request.clone({headers: request.headers.set('Authorization', 'Bearer ' + token)});
    }

    return next.handle(request).pipe(
                                       catchError((error: HttpErrorResponse) => {
                                         let errorMsg = '';
                                         if (error.error instanceof ErrorEvent) {
                                           alert(error.error.message);
                                         }
                                         else {
                                          alert(error.error.message);
                                         }
                                         console.log(errorMsg);
                                         return throwError(errorMsg);
                                       }));
  }
}
