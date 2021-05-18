import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopClientComponent } from './shop-client.component';

describe('ShopClientComponent', () => {
  let component: ShopClientComponent;
  let fixture: ComponentFixture<ShopClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
