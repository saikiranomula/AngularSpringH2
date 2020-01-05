import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable , of } from 'rxjs';
import { catchError, map, tap  } from 'rxjs/operators';
import { User } from './user';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({providedIn: 'root'})
export class UserService {
  public API = '//localhost:8080';
  public USER_API = this.API + '/users';
  public NEW_USER_API = this.API + '/newuser';


  constructor(private http: HttpClient) {
  }
  //earlier starts version works fine
  getAll(): Observable<any> {
    return this.http.get(this.API + '/users');
  }

  get(id: string) {
    return this.http.get(this.USER_API + '/' + id);
  }

  save(user: any): Observable<any> {
    let result: Observable<any>;
    if (user.href) {
      result = this.http.put(user.href, user);
    } else {
      result = this.http.post(this.USER_API, user);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
  // earlier ends

  //snomula starts
  //////// Save methods //////////
  /** GET heroes from the server */
  getUsers (): Observable<User[]> {
    return this.http.get<User[]>(this.API + '/users')
      .pipe(
        tap(_ => console.log('fetched heroes'))
        //,     catchError(this.handleError<Hero[]>('getUsers', []))
      );
  }
  /** POST: add a new user to the server */
  addUser (user: any): Observable<any> {
    return this.http.post<any>(this.NEW_USER_API, user, httpOptions).pipe(
      tap((newUser: any) => console.log(`added user w/ id=${newUser.id}`)),
            catchError(this.handleError<User>('addUser'))
    );
  }

  /** DELETE: delete the user from the server */
  deleteUser (user: any | number): Observable<any> {
    const id = typeof user === 'number' ? user : user.id;
    const url = `${this.USER_API}/${id}`;

    return this.http.delete<any>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted user id=${id}`)),
            catchError(this.handleError<User>('deleteUser'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(message);
  //  this.messageService.add(`HeroService: ${message}`);
  }

  register(user: User) {
    return this.http.post(this.API + '/users/register', user);
  }

  //snomula ends
}
