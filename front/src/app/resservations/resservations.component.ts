import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Projection } from "../model";
import {FormControl,FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectionService} from "../../services/projection.service";
import {ResservationService} from "../../services/resservation.service";


@Component({
  selector: 'app-resservations',
  templateUrl: './resservations.component.html',
  styleUrls: ['./resservations.component.css']
})
export class ResservationsComponent implements OnInit {

  projectionId: number;
  projection: Projection;
  projectionForm= new FormGroup({
    date: new FormControl({value:'' ,disabled: true}),
    price: new FormControl({value:'' ,disabled: true}),
    numberOfFreeCards: new FormControl({value:'' ,disabled: true}),
    hall: new FormControl({value:'' ,disabled: true}),
    movie: new FormControl({value:'' ,disabled: true}),
    numberOfReservedCardsFront: new FormControl('1')
  });
    reserve: FormGroup;
  constructor(private activatedRoute: ActivatedRoute,
              private projectionService: ProjectionService,
              private resservationService: ResservationService) { }

  ngOnInit(): void {
      this.activatedRoute.queryParams.subscribe(params => {
              this.projectionId = params['id'];
              this.projectionService.findById(this.projectionId).subscribe(data =>{
                     this.projection = data;
                     this.projectionForm.patchValue({
                               date: this.projection.date,
                               price: this.projection.price,
                               numberOfFreeCards: this.projection.hall.capacity - this.projection.numOfReservedCards,
                               hall: this.projection.hall.mark,
                               movie: this.projection.movie.name
                           })
              })
            });



  }

  noCards(){
      if(this.projection.hall.capacity - this.projection.numOfReservedCards <= 0){
          return  false;
      }
      return true;
  }

  changeReserve(e){
        this.projectionForm.patchValue({
                numberOfReservedCardsFront: e.target.value
              })
    }

  onSubmit(){
  if((this.projection.hall.capacity - this.projection.numOfReservedCards) >= this.projectionForm.controls['numberOfReservedCardsFront'].value){
      this.resservationService.reserve(this.projectionId, this.projectionForm.controls['numberOfReservedCardsFront'].value).subscribe(data => {
      this.projectionService.findById(this.projectionId).subscribe(data =>{
                           this.projection = data;
                           this.projectionForm.patchValue({
                                     date: this.projection.date,
                                     price: this.projection.price,
                                     numberOfFreeCards: this.projection.hall.capacity - this.projection.numOfReservedCards,
                                     hall: this.projection.hall.mark,
                                     movie: this.projection.movie.name
                                 })
                    })
          alert("Reserved!");
      })
  }else{
      alert("No space in hall!")
  }
  }

}
