import { Injectable } from '@angular/core';
import { UtilsService } from './utils.service';
import { Student } from './students.service';
import { Lesson } from './lessons.service';

const url = 'http://localhost:8080/api/attendance/';

export interface Activity {
  subject: Lesson;
}

export interface Attendance {
  studId: number;
  student: Student;
  lessonId: number;
  activity: Activity;
  status: boolean;
  datetime: string;
}

@Injectable({
  providedIn: 'root'
})

export class AttendanceService {

  constructor(private utils: UtilsService) { }

  loadByStudent(id: number) {
    return this.utils.getByUrl<Array<Attendance>>(url + 'student/' + id + '/');
  }

  loadByTeacher(id: number) {
    return this.utils.getByUrl<Array<Attendance>>(url + 'teacher/' + id + '/');
  }
}
