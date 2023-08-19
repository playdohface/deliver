import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryAdminComponent } from './delivery-admin.component';

describe('DeliveryAdminComponent', () => {
  let component: DeliveryAdminComponent;
  let fixture: ComponentFixture<DeliveryAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeliveryAdminComponent]
    });
    fixture = TestBed.createComponent(DeliveryAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
