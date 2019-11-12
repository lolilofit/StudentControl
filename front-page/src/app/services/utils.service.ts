import { Injectable } from '@angular/core';
import {HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  public static headers: { headers: HttpHeaders } = { headers: new HttpHeaders({Accept: 'application/json'})};

  public static handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.statusText}, ` +
        `Backend returned number ${error.status}, ` +
        `body was: ${error.error} `);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

  constructor() { }
}
