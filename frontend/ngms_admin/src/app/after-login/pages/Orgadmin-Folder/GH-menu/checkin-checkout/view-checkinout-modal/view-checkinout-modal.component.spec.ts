import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ViewCheckinoutModalComponent } from './view-checkinout-modal.component';

describe('ViewCheckinoutModalComponent', () => {
  let component: ViewCheckinoutModalComponent;
  let fixture: ComponentFixture<ViewCheckinoutModalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCheckinoutModalComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ViewCheckinoutModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
