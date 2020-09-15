import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service"

@Component({
  selector: 'app-add-cinema',
  templateUrl: './add-cinema.component.html',
  styleUrls: ['./add-cinema.component.css']
})
export class AddCinemaComponent implements OnInit {

  registrationForm: FormGroup;
  cinemaForm: FormGroup;

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
  this.cinemaForm = this.formBuilder.group({
      name: [''],
      address: [''],
      phone: [''],
      email: [''],

  })
  }

  addManager(){
  }

  onSubmit(){
  }

}
