import { Component, OnInit } from '@angular/core';
import {CinemasService} from '../../services/cinemas.service'
import { Cinema, Hall, Projection, ProjectionDto, Movie, User } from "../model"
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from "../../services/user.service";
import { Role } from "../model";
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-cinemas',
  templateUrl: './cinemas.component.html',
  styleUrls: ['./cinemas.component.css']
})
export class CinemasComponent implements OnInit {

  cinemas : Cinema[];
  cinemaId : number;
  halls : Hall[];
  hall : Hall;
  hallForm: FormGroup;
  hallEditForm : FormGroup;
  projectionForm: FormGroup;
  projectionEditForm: FormGroup;
  cinemaForm : FormGroup;
  cinemaEditForm : FormGroup;
  projections : Projection[];
  projection : Projection;
  movies: Movie[];
  managers : User[];
  projectionDto = new ProjectionDto();
  cinema : Cinema;
  loggedIn: boolean;
  role= new Role();

  constructor(private cinemasService : CinemasService, private formBuilder: FormBuilder,
              private userService : UserService) { }

  ngOnInit(): void {
      this.loggedIn = this.userService.isLoggedIn();
      if(this.loggedIn){
        this.userService.getCurrentRole().subscribe(data =>{
            this.role = data;
        })
      }
    this.hallForm = this.formBuilder.group({
                mark: [''],
                capacity: ['']
            });

    this.hallEditForm = this.formBuilder.group({
                           markEdit: [''],
                           capacityEdit: ['']
                       });

    this.projectionForm = this.formBuilder.group({
                            hall: [''],
                            movie: [''],
                            numOfReservedCards: [''],
                            date: [''],
                            price: ['']
                        });

    this.projectionEditForm = this.formBuilder.group({
                           hallEdit1: [''],
                           movieEdit1: [''],
                           numOfReservedCardsEdit1: [''],
                           dateEdit1: [''],
                           priceEdit1: ['']

                        });

    this.cinemaForm = this.formBuilder.group({
                               name: [''],
                               address: [''],
                               phone: [''],
                               email: [''],
                               manager:['']
                            });

    this.cinemaEditForm = this.formBuilder.group({
                                   name: [''],
                                   address: [''],
                                   phone: [''],
                                   email: [''],
                                   manager:['']
                                });

    this.getCinemas();

    this.cinemasService.managers().subscribe(data =>{
      this.managers = data;
      alert(data  )
    })
  }

  hasRoleManager(){
     if(this.role.name === 'MANAGER'){
        return true
     }
     return false
  }


  getCinemas(): void{
    //Ako je rola manager
    this.cinemasService.getManagerCinemas().subscribe(data => {
          this.cinemas = data;
    });

    //Ako je rola admin
    this.cinemasService.allCinemas().subscribe(data =>{
      this.cinemas = data;
    })
  }

  getHalls(id): void {
    this.cinemasService.getCinemaHalls(id).subscribe(data => {
      this.cinemaId = id;
      this.halls = data;
    })
  }

  removeHall(id): void {
    this.cinemasService.removeHall(id).subscribe(data => {
      this.getHalls(this.cinemaId);
    })
  }

  onSubmit():void{
    this.cinemasService.newHall(this.hallForm.value,this.cinemaId).subscribe(data => {
          this.getHalls(this.cinemaId);
    });
  }

  editHall(id):void {
    this.cinemasService.getHall(id).subscribe(data => {
       this.hall = data;
       this.hallEditForm = this.formBuilder.group({
                       markEdit: [this.hall.mark],
                       capacityEdit: [this.hall.capacity]
                   });
    });
  }

  onSubmitEdit(): void {
    this.hall.mark = this.hallEditForm.value.markEdit;
    this.hall.capacity = this.hallEditForm.value.capacityEdit;
    this.cinemasService.updateHall(this.hall).subscribe(data => {
      this.getHalls(this.cinemaId);
    })
  }

  schedule(id):void {
    this.cinemasService.allProjections(id).subscribe(data => {
      this.projections = data;
      this.cinemaId = id;
      this.getHalls(this.cinemaId);
      this.getMovies();
    });

  }

  getMovies(){
      this.cinemasService.getMovies().subscribe(data => {
        this.movies = data;
      });
  }

  removeProjection(id): void{
    this.cinemasService.removeProjection(id).subscribe(data => {
        this.schedule(this.cinemaId);
    });
  }

  onSubmitNewProjection(){
    this.cinemasService.newProjection(this.projectionForm.value).subscribe(data => {
            this.schedule(this.cinemaId);
    });
  }

  editProjection(id):void{
    this.cinemasService.getProjection(id).subscribe(data => {
      this.projection = data;
      this.projectionEditForm = this.formBuilder.group({
                                 hallEdit1: [this.projection.hall],
                                 movieEdit1: [this.projection.movie],
                                 numOfReservedCardsEdit1: [this.projection.numOfReservedCards],
                                 dateEdit1: [this.projection.date],
                                 priceEdit1: [this.projection.price]

                              });
    });
  }

  onSubmitEditProjection(): void{
    this.projectionDto.id = this.projection.id;
    this.projectionDto.date = this.projectionEditForm.value.dateEdit1;
    this.projectionDto.price = this.projectionEditForm.value.priceEdit1;
    this.projectionDto.numOfReservedCards = this.projectionEditForm.value.numOfReservedCardsEdit1;
    this.projectionDto.hall = this.projectionEditForm.value.hallEdit1;
    this.projectionDto.movie = this.projectionEditForm.value.movieEdit1;

    this.cinemasService.updateProjection(this.projectionDto).subscribe(data => {
      this.schedule(this.cinemaId);
    })
  }

  onSubmitNewCinema():void{
    this.cinemasService.newCinema(this.cinemaForm.value).subscribe(data => {
      this.getCinemas();
    })
  }

  remove(id): void {
    this.cinemasService.removeCinema(id).subscribe(data => {
      this.getCinemas();
    })
  }

  editCinema(id):void{
    this.cinemasService.getCinema(id).subscribe(data => {
      this.cinema = data;
      this.cinemaEditForm = this.formBuilder.group({
                               name: [this.cinema.name],
                               address: [this.cinema.address],
                               phone: [this.cinema.phone],
                               email: [this.cinema.email],
                               manager:[this.cinema.manager]
                            });
    })

  }

  onSubmitEditCinema():void {

    this.cinema.name = this.cinemaEditForm.value.name;
    this.cinema.address = this.cinemaEditForm.value.address;
    this.cinema.phone = this.cinemaEditForm.value.phone;
    this.cinema.email = this.cinemaEditForm.value.email;
    this.cinema.manager = this.cinemaEditForm.value.manager;
    this.cinemasService.updateCinema(this.cinema).subscribe(data => {
      this.getCinemas();
    })
  }

}
