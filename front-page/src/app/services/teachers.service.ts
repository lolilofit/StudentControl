import { Injectable } from '@angular/core';
import {UtilsService} from './utils.service';
import {Observable} from 'rxjs';

const url = 'http://localhost:8080/api/teacher/';

export interface Teacher {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class TeachersService {

  constructor(private utils: UtilsService) { }

  load(): Observable<Array<Teacher>> {
    return this.utils.getByUrl<Array<Teacher>>(url);
  }

  loadById(id: number): Observable<Teacher> {
    return this.utils.getByUrl<Teacher>(url + id + '/');
  }
}
