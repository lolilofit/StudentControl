import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Student, StudentsService} from '../../services/students.service';
import {Subscription} from 'rxjs';
import {Attendance, AttendanceService} from '../../services/attendance.service';

@Component({
  selector: 'app-attendance-container',
  templateUrl: './attendance-container.component.html',
  styleUrls: ['./attendance-container.component.css']
})
export class AttendanceContainerComponent implements OnInit, OnDestroy {
  constructor(private currentRoute: ActivatedRoute,
              private studentService: StudentsService,
              private attendanceService: AttendanceService) { }
  private studentSubscription: Subscription;
  private attendanceSubscription: Subscription;
  private initStudent: Student;
  private initAttendances: Array<Attendance>;

  get attendances() {
    return this.initAttendances;
  }
  ngOnInit() {
    const id = parseInt(this.currentRoute.snapshot.paramMap.get('id'), 10);
    this.studentSubscription = this.studentService.loadById(id).subscribe(student => this.initStudent = student);
    this.attendanceSubscription = this.attendanceService.loadByStudent(id).subscribe(attendances => this.initAttendances = attendances);
  }

  ngOnDestroy(): void {
    this.studentSubscription.unsubscribe();
    this.attendanceSubscription.unsubscribe();
  }
}
