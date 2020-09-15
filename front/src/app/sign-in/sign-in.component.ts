import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service"

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder:FormBuilder, private userService : UserService) { }

  ngOnInit(): void {
     this.loginForm = this.formBuilder.group({
            username: [''],
            password: ['']
        });
  }

  onSubmit(){
    this.userService.signIn(this.loginForm.value);
  }
}
