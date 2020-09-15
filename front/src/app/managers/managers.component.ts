import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CinemasService} from '../../services/cinemas.service'
import {UserService} from '../../services/user.service'
import {User } from "../model"

@Component({
  selector: 'app-managers',
  templateUrl: './managers.component.html',
  styleUrls: ['./managers.component.css']
})
export class ManagersComponent implements OnInit {

  managerForm :FormGroup;
  managers : User[];

  constructor(private formBuilder: FormBuilder, private cinemasService : CinemasService, private userService : UserService) { }

  ngOnInit(): void {
    this.managerForm = this.formBuilder.group({
            firstName: [''],
            lastName: [''],
            phone: [''],
            email: [''],
            birthDay: [''],
            username: [''],
            password: ['']
    });

    this.cinemasService.managers().subscribe(data => {
      this.managers = data;
    })
  }

  onSubmit():void{
    this.userService.newManager(this.managerForm.value).subscribe(data => {
      this.cinemasService.managers().subscribe(data => {
            this.managers = data;
          })
    })
  }

  enable(id):void{
     this.userService.enableManager(true, id).subscribe(data => {
        this.cinemasService.managers().subscribe(data => {
            this.managers = data;
        })
     })
  }

  disable(id):void{
    this.userService.enableManager(false, id).subscribe(data => {
        this.cinemasService.managers().subscribe(data => {
            this.managers = data;
        })
    })
  }

}
