import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CurrentBookingModerationComponent } from './current-booking-moderation.component';

describe('CurrentBookingModerationComponent', () => {
  let component: CurrentBookingModerationComponent;
  let fixture: ComponentFixture<CurrentBookingModerationComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentBookingModerationComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(CurrentBookingModerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
