import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyReservationsAndMoviesComponent } from './my-reservations-and-movies.component';

describe('MyReservationsAndMoviesComponent', () => {
  let component: MyReservationsAndMoviesComponent;
  let fixture: ComponentFixture<MyReservationsAndMoviesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyReservationsAndMoviesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyReservationsAndMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
