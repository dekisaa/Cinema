import { Component, OnInit } from '@angular/core';
import {MovieService} from '../../services/movie.service'
import {Movie} from "../model"
import {FormControl,FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-my-movies',
  templateUrl: './my-movies.component.html',
  styleUrls: ['./my-movies.component.css']
})
export class MyMoviesComponent implements OnInit {

  watched : Movie[];
  watchedRated : Movie[];
  watchedNoRated : Movie[];
  movieId: number;
  rateForm: FormGroup;

  constructor(private movieService : MovieService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.rateForm = this.formBuilder.group({
        value: ['']
    })

    this.movieService.watched().subscribe(data => {
      this.watched = data;
    })

    this.movieService.watchedRated().subscribe(data => {
      this.watchedRated = data;
    })

    this.movieService.watchedNoRated().subscribe(data => {
       this.watchedNoRated = data;
    })
  }

  onSubmit(){
    this.movieService.rate(this.movieId,this.rateForm.value.value).subscribe(data => {
      this.movieService.watchedRated().subscribe(data => {
            this.watchedRated = data;
          })

          this.movieService.watchedNoRated().subscribe(data => {
             this.watchedNoRated = data;
          })
    })
  }

  rateId(id){
    this.movieId = id;
  }
}
