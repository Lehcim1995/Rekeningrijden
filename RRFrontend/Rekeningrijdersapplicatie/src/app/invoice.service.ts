import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class InvoiceService {

  private getInvoicesByCar = "http://localhost:8080/administratie/api/vehicle/";
  constructor(protected httpClient: HttpClient, private router: Router) {


  }

  getcars()
  {

  }

  getInvoiceByCar(licensePlate:string)
  {
    return this.httpClient.get(`${this.getInvoicesByCar}/${licensePlate}`)
  }





}
