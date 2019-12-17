import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { StudentsService } from './services/students.service';
import { HttpClientModule } from '@angular/common/http';
import { StudentComponent } from './components/student/student.component';
import { GroupComponent } from './components/group/group.component';
import { GroupContainerComponent } from './components/group-container/group-container.component';
import {AttendanceService} from './services/attendance.service';
import { AttendanceContainerComponent } from './components/attendance-container/attendance-container.component';
import { AttendanceComponent } from './components/attendance/attendance.component';
import { RouterModule } from '@angular/router';
import { routing } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    GroupComponent,
    GroupContainerComponent,
    AttendanceContainerComponent,
    AttendanceComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    routing
  ],
  providers: [StudentsService, AttendanceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
