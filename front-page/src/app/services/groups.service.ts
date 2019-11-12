import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UtilsService } from './utils.service';
import { Student } from './students.service';

const url = 'http://localhost:8080/api/group/';

export interface Group {
  id: number;
  groupNum: number;
  groupStudents: Array<Student>;
}

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  constructor(private http: HttpClient) { }

  load(): Observable<Array<Group>> {
    return this.http.get<Array<Group>>(url, UtilsService.headers).pipe(
      catchError(UtilsService.handleError)
    );
  }
}
