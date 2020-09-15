import { Component, OnInit } from '@angular/core';
import { Role,Movie,Projection } from "../model";
import {UserService} from "../../services/user.service";
import {MovieService} from "../../services/movie.service";
import {ProjectionService} from "../../services/projection.service";
import {FormControl,FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSortModule, Sort} from '@angular/material/sort';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  movie: Movie;
  movies: Movie[];
  projection: Projection;
  projections: Projection[];
  movieIdProjections: Projection[];
  searchForm: FormGroup;

  loggedIn: boolean;
  role= new Role();

  dates = new FormGroup({
      startDate: new FormControl(''),
      endDate: new FormControl(''),
      price: new FormControl('')
  })
  constructor(private userService : UserService,
            private formBuilder:FormBuilder, private movieService: MovieService,
              private projectionService: ProjectionService) { }

  ngOnInit(): void {
      this.loggedIn = this.userService.isLoggedIn();
      if(this.loggedIn){
        this.userService.getCurrentRole().subscribe(data =>{
            this.role = data;
        })
      }
    this.searchForm = this.formBuilder.group({
        name: [''],
        description: [''],
        genre: [''],
        rating: ['']
    })

    this.movieService.getMovies().subscribe(data => {
        this.movies = data;
    })
    this.projectionService.getProjections().subscribe(data => {
        this.projections = data;
    })
  }

  hasRoleUser(){
       if(this.role.name === 'VIEWER'){
          return true
       }
       return false
  }

  changeStart(e){
      this.dates.patchValue({
        startDate: e.target.value
      })
      console.log(e.target.value)
  }

  changeEnd(e){
      this.dates.patchValue({
        endDate: e.target.value
      })
      console.log(e.target.value)
  }

  changePrice(e){
        this.dates.patchValue({
          price: e.target.value
        })
        console.log(e.target.value)
    }

  schedules(id:number){
      this.projectionService.findProjectionByMovieId(id).subscribe(data => {
        console.log(data + "     projekcijeeeee")
        this.movieIdProjections = data;
      })
  }

  sort(sort: Sort){
      const data = this.movies.slice();
      if(!sort.active || sort.direction === ''){
          this.movies = data;
      }

      this.movies = data.sort((a, b) => {
          const isAsc = sort.direction === 'asc';
          switch (sort.active) {
              case 'name': return this.compare(a.name, b.name, isAsc);
              case 'description': return this.compare(a.description, b.description, isAsc);
              case 'genre': return this.compare(a.genre, b.genre, isAsc);
              case 'duration': return this.compare(a.duration, b.duration, isAsc);
              case 'rating': return this.compare(a.rating, b.rating, isAsc);
              default: return 0;
          }
      })
  }

  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  onSubmit(){
      this.movieService.search(this.searchForm.controls['name'].value, this.searchForm.controls['description'].value,
       this.searchForm.controls['genre'].value, this.searchForm.controls['rating'].value, this.dates.controls['startDate'].value,
                                this.dates.controls['endDate'].value, this.dates.controls['price'].value).subscribe(data => {
          this.movies = data;
      })
  }

  aName(){
    this.movies.sort(function(a, b){
      return a.name == b.name ? 0 : +(a.name > b.name) || -1;
    });
  }

  dName(){
    this.movies.sort(function(a, b){
          return a.name == b.name ? 0 : +(a.name < b.name) || -1;
        });
  }

  aDescription() {
   this.movies.sort(function(a, b){
        return a.description == b.description ? 0 : +(a.description > b.description) || -1;
      });
  }

  dDescription() {
  this.movies.sort(function(a, b){
        return a.description == b.description ? 0 : +(a.description < b.description) || -1;
      });
  }

  aGenre(){
    this.movies.sort(function(a, b){
            return a.genre == b.genre ? 0 : +(a.genre > b.genre) || -1;
          });
  }

  dGenre(){
    this.movies.sort(function(a, b){
            return a.genre == b.genre ? 0 : +(a.genre < b.genre) || -1;
          });
  }

  aDuration(){
    this.movies.sort(function(a, b){
                return a.duration == b.duration ? 0 : +(a.duration > b.duration) || -1;
              });
  }

  dDuration(){
    this.movies.sort(function(a, b){
                return a.duration == b.duration ? 0 : +(a.duration < b.duration) || -1;
              });
  }

  aRating(){
    this.movies.sort(function(a, b){
        return a.rating == b.rating ? 0 : +(a.rating > b.rating) || -1;
       });
  }

  dRating(){
    this.movies.sort(function(a, b){
        return a.rating == b.rating ? 0 : +(a.rating < b.rating) || -1;
       });
  }

}
