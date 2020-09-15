import {  AfterViewInit, Component } from '@angular/core';
import {UserService} from "../services/user.service";
import { Role } from "./model";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  loggedIn: boolean;
  role= new Role();

  constructor(private userService : UserService){}

  ngOnInit() {
    this.loggedIn = this.userService.isLoggedIn();
    if(this.loggedIn){
      this.userService.getCurrentRole().subscribe(data =>{
          this.role = data;
      })
    }
  }

  hasRoleAdmin(){
      if(this.role.name === 'ADMIN'){
      return true
      }
      return false
  }

  hasRoleManager(){
      if(this.role.name === 'MANAGER'){
      return true
      }
      return false}
   hasRoleUser(){
      if(this.role.name === 'VIEWER'){
      return true
      }
      return false
      }


  logout(){
    this.userService.logout();
  }



}
