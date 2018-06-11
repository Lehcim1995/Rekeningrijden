import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  password : any;
  email : any;
  cpr : any;

  constructor(private profileService: ProfileService) { }

  ngOnInit() {

  }

  register(){
    this.profileService.register(this.email, this.cpr).subscribe(
      (res) => {
        let owner = res;
        console.log(res.body.password);
        if (res.body.password != null) {
          this.password = res.body.password;
          localStorage.setItem('loggedInUser', JSON.stringify({email: this.email, cpr: this.cpr}));
          localStorage.setItem('email', this.email);
          localStorage.setItem('cpr', this.cpr);
        }
      },
      err => {
        if (err.status == 401) {
          console.log("Not authorized to register");
          alert("Not authorized to register");
        }
      }
    );
  }

}
