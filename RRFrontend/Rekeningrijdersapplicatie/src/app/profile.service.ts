import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable()
export class ProfileService {

  private postLoginURL = "http://localhost:8080/rekeningrijdersapplicatiemodule/rest/rekeningadministratie/login";
  public token: string;
  private loggedInUser: string;

  constructor(protected httpClient: HttpClient, private router: Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  public login(login: string, password: string) {
    let body = {login: login, password: password};
    this.httpClient.post(`${this.postLoginURL}`, body, {observe: 'response'}).subscribe(
      (res) => {
        let token = res.headers.get('Authorization');
        console.log(token);
        if (token != null) {
          this.token = token;
          localStorage.setItem('currentUser', JSON.stringify({username: login, token: token}));
          localStorage.setItem('loggedinuser', login);
          localStorage.setItem('token', token);
          this.router.navigateByUrl('/home');
        }
      },
      err => {
        if (err.status == 401) {
          console.log("Not logged in");
          alert("Not logged in!");
        }
      }
    );
  }

  public getLoggedInUser() {
    return localStorage.getItem('loggedinuser');
  }

  public setLoggedInUser(loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

}
