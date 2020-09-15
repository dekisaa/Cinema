import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import { Reservation } from "../app/model"

@Injectable({
  providedIn: 'root'
})
export class ResservationService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  reserve(projection_id: number, num_of_cards: number){
      return this.http.post(this.baseUrl + 'reservation/?projection_id=' + projection_id + '&num_of_cards=' + num_of_cards, null);
  }
  getAll(): Observable<any>{
      return this.http.get(this.baseUrl + 'reservation/getAll');
  }

  buy(id: number){
      return this.http.put(this.baseUrl + 'reservation/?id=' + id, null);
  }

  cancel(id : number){
      return this.http.delete(this.baseUrl + 'reservation/?reservation_id=' + id);
  }
}
