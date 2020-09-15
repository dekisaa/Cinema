import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import { User } from "../app/model"
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient, private cookie: CookieService) { }

  signUp(user: any){
        return this.http.post(this.baseUrl + 'auth/registration', user);
  }

  newManager(manager){
    return this.http.post(this.baseUrl + 'user/new_manager', manager);
  }

  enableManager(value, id): Observable<any>{
    console.log(value + " " + id)
    return this.http.post(this.baseUrl + 'user/enable?value=' + value + '&id=' + id,  null);
  }

  signIn(userLogin) {
        let params = new URLSearchParams();
        params.append('username',userLogin.username);
        params.append('password',userLogin.password);
        params.append('grant_type','password');

        const body = new HttpParams()
          .set('username', userLogin.username)
          .set('password', userLogin.password)
          .set('grant_type', 'password');

        let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("center:clientPassword")});

        let options = {headers:headers}

        return this.http.post(this.baseUrl+'oauth/token', body, options).subscribe(data=>{
          console.log(data);
          this.saveToken(data);
          window.location.href="/";
        },err=>{
          console.log(err);
        });

      }

  getRoles():Observable<any>{
        return this.http.get(this.baseUrl + 'roles/all');
      }

  getCurrentUser(): Observable<any>{
      return this.http.get(this.baseUrl + 'user/current_user');
  }

  getCurrentRole(): Observable<any>{
      return this.http.get(this.baseUrl + 'user/role');
  }

  newPass(currPass,newPass){
     return this.http.post(this.baseUrl + 'user/change_pass?pass1=' + currPass + '&pass2=' + newPass, null);
  }

      saveToken(token){
        var expireDate = new Date().getTime() + (1000 * token.expires_in);
        console.log(token.expires_in);
        console.log(token)
        this.cookie.set("access_token", token.access_token);
        console.log('Obtained Access token');
      }

      getToken(){
        return this.cookie.get("access_token");
      }

      isValid():boolean{
        if(this.getToken()==null){
          return false;
        }
      }

      isLoggedIn(){
      console.log(this.cookie.get("access_token"))
        if(this.cookie.get("access_token") == "null"){
          return false;
        }
         return true;
      }

      logout(){
        this.cookie.set("access_token", null);
        window.location.href="/";
      }

}
