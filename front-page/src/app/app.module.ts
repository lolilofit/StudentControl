import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { StudentsService } from './services/students.service';
import { HttpClientModule } from '@angular/common/http';
import { StudentComponent } from './components/student/student.component';
import { GroupComponent } from './components/group/group.component';
import { GroupContainerComponent } from './components/group-container/group-container.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    GroupComponent,
    GroupContainerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [StudentsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
