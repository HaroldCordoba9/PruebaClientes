import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalNewClientComponent } from './modal-new-client.component';

describe('ModalNewClientComponent', () => {
  let component: ModalNewClientComponent;
  let fixture: ComponentFixture<ModalNewClientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalNewClientComponent]
    });
    fixture = TestBed.createComponent(ModalNewClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
