import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { GuestFeedbackReportComponent } from './guest-feedback-report.component';

describe('GuestFeedbackReportComponent', () => {
  let component: GuestFeedbackReportComponent;
  let fixture: ComponentFixture<GuestFeedbackReportComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestFeedbackReportComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(GuestFeedbackReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
