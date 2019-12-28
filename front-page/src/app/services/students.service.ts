import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
  constructor(private utils: UtilsService) { }

  load(): Observable<Array<Student>> {
    return this.utils.getByUrl<Array<Student>>(url);
  }
  loadById(id: number): Observable<Student> {
    return this.utils.getByUrl<Student>(url + id + '/');
  }
}
