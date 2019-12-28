import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UtilsService } from './utils.service';
import { Student } from './students.service';

const url = 'http://localhost:8080/api/group/';

export interface Group {
  id: number;
  groupNum: string;
  groupStudents: Array<Student>;
}

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  constructor(private utils: UtilsService) { }

  load(): Observable<Array<Group>> {
    return this.utils.getByUrl<Array<Group>>(url);
  }
}
