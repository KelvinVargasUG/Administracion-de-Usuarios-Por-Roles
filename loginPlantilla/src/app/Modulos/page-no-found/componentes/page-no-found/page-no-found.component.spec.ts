import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNoFoundComponent } from './page-no-found.component';

describe('PageNoFoundComponent', () => {
  let component: PageNoFoundComponent;
  let fixture: ComponentFixture<PageNoFoundComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageNoFoundComponent]
    });
    fixture = TestBed.createComponent(PageNoFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
