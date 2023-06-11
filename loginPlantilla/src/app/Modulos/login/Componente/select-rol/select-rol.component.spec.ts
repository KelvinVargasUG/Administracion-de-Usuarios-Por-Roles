import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectRolComponent } from './select-rol.component';

describe('SelectRolComponent', () => {
  let component: SelectRolComponent;
  let fixture: ComponentFixture<SelectRolComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectRolComponent]
    });
    fixture = TestBed.createComponent(SelectRolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
