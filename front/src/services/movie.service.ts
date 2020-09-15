import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getMovies(): Observable<any>{
      return this.http.get(this.baseUrl + "movie/getAll");
  }

  findById(id : number): Observable<any>{
      return this.http.get(this.baseUrl + "movie/?id=" + id);
  }

  search(name: string, description: string, genre: string, rating: number, startDate: string, endDate: string, price : number): Observable<any>{
      return this.http.get(this.baseUrl + "movie/search?name=" + name + "&description=" + description + "&genre=" + genre + "&rating=" + rating +"&startDate="
                                        + startDate + "&endDate=" + endDate + "&price=" + price)
  }

  watched():Observable<any>{
    return this.http.get(this.baseUrl + "movie/watched");
  }

  watchedRated():Observable<any>{
      return this.http.get(this.baseUrl + "movie/watched_rated");
    }

  watchedNoRated():Observable<any>{
      return this.http.get(this.baseUrl + "movie/watched_no_rated");
  }

  rate(id,value):Observable<any> {
    return this.http.post(this.baseUrl + "rate?movie_id=" + id + '&value=' + value, null)
  }

}
