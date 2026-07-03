import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TotalFeedbackReportComponent } from './total-feedback-report.component';

describe('TotalFeedbackReportComponent', () => {
  let component: TotalFeedbackReportComponent;
  let fixture: ComponentFixture<TotalFeedbackReportComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ TotalFeedbackReportComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TotalFeedbackReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
