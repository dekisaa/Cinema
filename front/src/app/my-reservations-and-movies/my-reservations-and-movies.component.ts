import { Component, OnInit } from '@angular/core';
import { Reservation } from "../model";
import { ResservationService } from "../../services/resservation.service";

@Component({
  selector: 'app-my-reservations-and-movies',
  templateUrl: './my-reservations-and-movies.component.html',
  styleUrls: ['./my-reservations-and-movies.component.css']
})
export class MyReservationsAndMoviesComponent implements OnInit {

  reservations: Reservation[];
  bought: boolean;

  constructor(private resservationService: ResservationService) { }

  ngOnInit(): void {
      this.resservationService.getAll().subscribe(data => {
          this.reservations = data;
      })
  }

  buy(id: number){
      this.resservationService.buy(id).subscribe(data =>{
          this.resservationService.getAll().subscribe(data => {
              this.reservations = data;
          })
          alert("Cards Bought")
      })
  }

  cancel(id : number){
      this.resservationService.cancel(id).subscribe(data => {
          this.resservationService.getAll().subscribe(data => {
                        this.reservations = data;
                    })
          alert("Reservation canceled")
      })
  }

}
