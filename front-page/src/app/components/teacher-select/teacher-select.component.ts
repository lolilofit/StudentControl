import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Student, StudentsService} from '../../services/students.service';
import {Attendance, AttendanceService} from '../../services/attendance.service';
import {Subscription} from 'rxjs';
import {Teacher, TeachersService} from '../../services/teachers.service';

@Component({
  selector: 'app-teacher-select',
  templateUrl: './teacher-select.component.html',
  styleUrls: ['./teacher-select.component.css']
})
export class TeacherSelectComponent implements OnInit, OnDestroy {
  constructor(private currentRoute: ActivatedRoute,
              private teachersService: TeachersService,
              private attendanceService: AttendanceService) { }
  private studentSubscription: Subscription;
  private attendanceSubscription: Subscription;
  private initTeacher: Teacher;
  private initAttendances: Array<Attendance>;

  get attendances() {
    return this.initAttendances;
  }

  ngOnInit() {
    const id = parseInt(this.currentRoute.snapshot.paramMap.get('id'), 10);
    this.studentSubscription = this.teachersService.loadById(id).subscribe(student => this.initTeacher = student);
    this.attendanceSubscription = this.attendanceService.loadByTeacher(id).subscribe(attendances => this.initAttendances = attendances);
  }

  ngOnDestroy(): void {
    this.studentSubscription.unsubscribe();
    this.attendanceSubscription.unsubscribe();
  }}
