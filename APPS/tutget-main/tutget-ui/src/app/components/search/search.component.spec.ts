import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComponent } from './search.component'; // Update the import

describe('SearchComponent', () => { // Update the component name
  let component: SearchComponent; // Update the component name
  let fixture: ComponentFixture<SearchComponent>; // Update the component name

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SearchComponent], // Update the component name
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchComponent); // Update the component name
    component = fixture.componentInstance; // Update the component name
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
