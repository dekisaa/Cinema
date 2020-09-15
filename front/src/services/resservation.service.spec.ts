import { TestBed } from '@angular/core/testing';

import { ResservationService } from './resservation.service';

describe('ResservationService', () => {
  let service: ResservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
