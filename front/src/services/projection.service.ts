import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProjectionService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getProjections(): Observable<any>{
        return this.http.get(this.baseUrl + "projection/getAll")
    }
  findProjectionByMovieId(id: number): Observable<any>{
      return this.http.get(this.baseUrl + "projection/findProjectionsByMovieId?id=" + id);
  }
  findById(id: number): Observable<any>{
      return this.http.get(this.baseUrl + "projection/findById?id=" + id);
  }
}
