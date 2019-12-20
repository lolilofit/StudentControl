import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  private headers: { headers: HttpHeaders } = { headers: new HttpHeaders({Accept: 'application/json'})};

  public handleError(error: HttpErrorResponse) {
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

  public getByUrl<T>(url: string): Observable<T> {
    return this.http.get<T>(url, this.headers).pipe(
      catchError(this.handleError)
    );
  }

  constructor(private http: HttpClient) { }
}
