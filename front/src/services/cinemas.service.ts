import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CinemasService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getManagerCinemas(): Observable<any>{
        return this.http.get(this.baseUrl + 'cinema/manager_cinemas');
    }

  getCinemaHalls(id): Observable<any>{
    return this.http.get(this.baseUrl + 'cinema/halls?id=' + id);
  }

  removeHall(id): Observable<any>{
    return this.http.delete(this.baseUrl + 'hall?id=' + id);
  }

  newHall(hall, id): Observable<any>{
    return this.http.post(this.baseUrl + 'hall?cinemaId=' + id, hall);
  }

  getHall(id):Observable<any>{
    return this.http.get(this.baseUrl + 'hall?id=' + id);
  }

  updateHall(hall):Observable<any>{
    return this.http.put(this.baseUrl + 'hall',hall);
  }

  allProjections(id):Observable<any>{
    return this.http.get(this.baseUrl + 'cinema/all_projections?id=' + id);
  }

  removeProjection(id):Observable<any>{
    return this.http.delete(this.baseUrl + 'projection?id=' + id);
  }

  newProjection(projection):Observable<any>{
    return this.http.post(this.baseUrl + 'projection', projection);
  }

  getMovies():Observable<any>{
    return this.http.get(this.baseUrl + 'movie/all');
  }

  getProjection(id):Observable<any>{
    return this.http.get(this.baseUrl + 'projection/dto_value?id='+ id)
  }

  updateProjection(projection):Observable<any>{
      return this.http.put(this.baseUrl + 'projection',projection);
    }

  managers():Observable<any>{
    return this.http.get(this.baseUrl + 'user/managers');
  }

  newCinema(cinema):Observable<any>{
    return this.http.post(this.baseUrl + 'cinema', cinema);
  }

  allCinemas():Observable<any>{
    return this.http.get(this.baseUrl + 'cinema/all');
  }

  removeCinema(id):Observable<any>{
    return this.http.delete(this.baseUrl + 'cinema?id=' + id);
  }

  getCinema(id):Observable<any>{
    return this.http.get(this.baseUrl + 'cinema/dto?id=' + id);
  }

  updateCinema(cinema):Observable<any>{
    return this.http.put(this.baseUrl + 'cinema',cinema);
  }
}
