import { Injectable } from '@angular/core';
import { UtilsService } from './utils.service';

const url = 'http://localhost:8080/api/subject/';

export interface Lesson {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class LessonsService {

  constructor(private utils: UtilsService) { }

  public load() {
    return this.utils.getByUrl<Array<Lesson>>(url);
  }
}
