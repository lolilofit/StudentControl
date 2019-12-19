import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UtilsService} from './utils.service';
import {catchError} from 'rxjs/operators';

const url = 'http://localhost:8080/api/attendance/';

export interface Attendance {
  studId: number;
  lessonId: number;
  status: string;
}

@Injectable({
  providedIn: 'root'
})

export class AttendanceService {

  constructor(private http: HttpClient) { }

  loadByStudent(id: number) {
    return this.http.get(url + 'student/' + id + '/', UtilsService.headers).pipe(
      catchError(UtilsService.handleError)
    );
  }
}
