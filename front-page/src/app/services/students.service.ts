import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

const url = 'localhost:8080/api/student';
const httpHeaders = { headers: new HttpHeaders({Accept: 'application/json'})};

export interface StudentInterface {
  name: string;
  group: number;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class StudentsService {
  constructor(private http: HttpClient) { }

  private static handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

  load(): Observable<Array<StudentInterface>> {
    return this.http.get<Array<StudentInterface>>(url, httpHeaders).pipe(
      catchError(StudentsService.handleError)
    );
  }
}
