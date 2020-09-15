import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { RegistrationComponent } from './registration/registration.component';
import { ProfileComponent } from './profile/profile.component';
import { AddCinemaComponent } from './add-cinema/add-cinema.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import {AuthInterceptor} from './interceptors/auth.interceptor';
import { SearchComponent } from './search/search.component'
import { CinemasComponent } from './cinemas/cinemas.component';
import { HallsComponent } from './halls/halls.component';
import { ResservationsComponent } from './resservations/resservations.component';
import { ManagersComponent } from './managers/managers.component';
import { MyReservationsAndMoviesComponent } from './my-reservations-and-movies/my-reservations-and-movies.component';
import { MyMoviesComponent } from './my-movies/my-movies.component';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    RegistrationComponent,
    ProfileComponent,
    AddCinemaComponent,
    SearchComponent,
    CinemasComponent,
    HallsComponent,
    ResservationsComponent,
    ManagersComponent,
    MyReservationsAndMoviesComponent,
    MyMoviesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
