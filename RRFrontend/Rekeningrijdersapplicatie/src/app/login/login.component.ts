import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileService} from "../profile.service";
import {InvoiceService} from "../invoice.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(private fb: FormBuilder, private profileService: ProfileService, private invoiceService: InvoiceService) {
    this.form = this.fb.group({
      cpr: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  login() {
    const val = this.form.value;
    if (val.cpr && val.password) {
      this.profileService.login(val.cpr, val.password);
      this.invoiceService.getInvoicesByCPR(this.profileService.getCPR()).subscribe(
        (res:any) => {

          console.log(res.body);
          this.invoiceService.setInvoices(res.body);
          //set list of invoices in service
          if (res != null) {

          }
        },
        err => {
          if (err.status == 401) {
            alert("Not logged in!");
          }
        }
      );

      this.invoiceService.getCarsOfOwner(this.profileService.getCPR()).subscribe(
        (res:any) => {
          console.log("cars", res.body);
          this.invoiceService.setCars(res.body);
          if (res != null) {

          }
        },
        err => {
          console.log("Something went wrong while getting the cars of the logged in user");
        }
      );

    }
  }

}
