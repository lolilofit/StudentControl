import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UtilsService } from './utils.service';

const url = 'http://localhost:8080/api/student/';

export interface Student {
  id: number;
  name: string;
  group: string;
}

@Injectable({
  providedIn: 'root'
})
export class StudentsService {
  constructor(private http: HttpClient) { }

  load(): Observable<Array<Student>> {
    return this.http.get<Array<Student>>(url, UtilsService.headers).pipe(
      catchError(UtilsService.handleError)
    );
  }
}
