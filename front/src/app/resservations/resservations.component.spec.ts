import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResservationsComponent } from './resservations.component';

describe('ResservationsComponent', () => {
  let component: ResservationsComponent;
  let fixture: ComponentFixture<ResservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
