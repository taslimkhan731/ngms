import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { PaymentDetailsConferenceComponent } from './payment-details-conference.component';

describe('PaymentDetailsConferenceComponent', () => {
  let component: PaymentDetailsConferenceComponent;
  let fixture: ComponentFixture<PaymentDetailsConferenceComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentDetailsConferenceComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(PaymentDetailsConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
