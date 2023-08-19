import { TestBed } from '@angular/core/testing';

import { TransitLocationService } from './transit-location.service';

describe('TransitLocationService', () => {
  let service: TransitLocationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransitLocationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
