import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service"

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private formBuilder:FormBuilder, private userService : UserService) { }

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
        firstName: [''],
        lastName: [''],
        phone: [''],
        email: [''],
        birthDay: [''],
        username: [''],
        password: [''],
        role: ['']
    });
  }

  onSubmit(){
    this.userService.signUp(this.registrationForm.value).subscribe(data => {
      alert("Successful!");
    });
  }
}
