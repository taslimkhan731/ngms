import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ReplyFeedbackComponent } from './reply-feedback.component';

describe('ReplyFeedbackComponent', () => {
  let component: ReplyFeedbackComponent;
  let fixture: ComponentFixture<ReplyFeedbackComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ReplyFeedbackComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ReplyFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
