import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocViewerComponent } from './doc-viewer/doc-viewer.component';
import { IonicModule } from '@ionic/angular';



@NgModule({
  declarations: [DocViewerComponent],
  imports: [
    CommonModule,
    IonicModule,
  ]
})
export class SharedModule { }
