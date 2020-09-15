import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SignInComponent} from './sign-in/sign-in.component';
import {RegistrationComponent} from './registration/registration.component';
import {AddCinemaComponent} from './add-cinema/add-cinema.component';
import {ProfileComponent} from './profile/profile.component';
import {SearchComponent} from './search/search.component';
import {CinemasComponent} from './cinemas/cinemas.component';
import {ResservationsComponent} from './resservations/resservations.component';
import {ManagersComponent} from './managers/managers.component';
import {MyMoviesComponent} from './my-movies/my-movies.component';
import {MyReservationsAndMoviesComponent} from './my-reservations-and-movies/my-reservations-and-movies.component';


const routes: Routes = [
  {path: 'login', component: SignInComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'add-cinema', component: AddCinemaComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'search', component: SearchComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'cinemas', component: CinemasComponent},
  {path: 'resservations', component: ResservationsComponent},
  {path: 'cinemas', component: CinemasComponent},
  {path: 'managers', component: ManagersComponent},
  {path: 'my-reservations-and-movies', component: MyReservationsAndMoviesComponent},
  {path: 'my_movies', component:MyMoviesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
