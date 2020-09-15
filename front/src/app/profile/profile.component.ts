import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { User } from "../model";
import {Role} from "../model";
import { UserService } from "../../services/user.service";
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {


  user:User;
  rolesFront: Role[];
  readOnly: boolean;

  profile= new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username : new FormControl(''),
    phone : new FormControl(''),
    email : new FormControl(''),
    birthday: new FormControl(''),
    role: new FormControl('')
  });

  newPass: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService) { }

  ngOnInit(): void {
    this.readOnly = false;

    this.userService.getCurrentUser().subscribe(data=>{
        this.user = data;
        console.log(data.birthDay);
        this.profile.patchValue({
          firstName: this.user.firstName,
          lastName: this.user.lastName,
          phone: this.user.phone,
          email: this.user.email,
          birthday: this.user.birthDay,
          username: this.user.username,
          role: this.user.role
        })
        console.log(this.user.role)
    })

    this.newPass = this.formBuilder.group({
        currentPass: [''],
        newPass: ['']
    });

    this.userService.getRoles().subscribe(data=>{
        this.rolesFront = data;
        console.log(data);
    });
  }

  changeRole(e){
      this.profile.patchValue({
          role: e.target.value
      })
      console.log(e.target.value);
  }

  newPassword(){
      this.userService.newPass(this.newPass.value.currentPass, this.newPass.value.newPass).subscribe(data =>{
          alert("Successful!");
      })
  }

  onSubmit(){

  }

}
