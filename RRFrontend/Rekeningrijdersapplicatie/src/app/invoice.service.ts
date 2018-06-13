import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import * as moment from 'moment';

@Injectable()
export class InvoiceService {

  public invoices: any = [];


  // private getInvoicesByCar = "http://localhost:8080/administratie/api/vehicle/";
  private getMovementsForCarWithMonthUL = "http://localhost:8080/verplaatsingsmodule/rest/verplaatsing";
  private getPDF = "http://localhost:8080/rekeningadministratiemodule/rest/invoice";
  private getInvoices = "http://localhost:8080/rekeningadministratiemodule/rest/invoice/cpr";



  constructor(protected httpClient: HttpClient, private router: Router) {


  }

  getcars() {

  }
  getInvoicesByCPR(cpr:string){
    return this.httpClient.get(`${this.getInvoices}/${cpr}`,{observe: 'response'});
  }

  public getCarIdFromLicenseplate(licenseplate: any)
  {
    //restcall to get car with licenseplate
      return "402";
  }

  public getMovementsForCarWithMonth(carId: string, invoiceDate: any) {

    let date = new Date();
    console.log("formatted time",moment(invoiceDate).format());


    let thisMonth = moment(invoiceDate).format("MM");
    console.log("thismonth", thisMonth);

    let lastMonth = moment(invoiceDate).subtract(1, 'months').format("MM");
    console.log("lastmonth", lastMonth);

    let nextMonth = moment(invoiceDate).add(1, 'months').format("MM");
    console.log("lastmonth", nextMonth);

    let thisDay = moment(invoiceDate).format("DD");
    console.log("thisday", thisDay);

    // let lastDayOfLastMonth = moment().format("MM");
    // console.log("lastDayOfLastMonth", lastDayOfLastMonth);

    let year = moment(invoiceDate).format("YYYY");
    console.log("year", year);

    invoiceDate.setDate(1);
    invoiceDate.setHours(-1);
    let lastDayOfLastMonth = invoiceDate.getDate();
    console.log("dayofPreviousmonth", lastDayOfLastMonth);

    //let carId = 402;

    console.log(`${this.getMovementsForCarWithMonthUL}/${carId}/waypoints?startdate=${lastDayOfLastMonth}/${lastMonth}/${year}&enddate=01/${nextMonth}/${year}`);
    return this.httpClient.get(`${this.getMovementsForCarWithMonthUL}/${carId}/waypoints?startdate=${lastDayOfLastMonth}/${lastMonth}/${year}&enddate=01/${nextMonth}/${year}`)
  }

  downloadPDF(invoiceid: string){
    invoiceid = "4";

    this.httpClient.get(`${this.getPDF}/${invoiceid}/download`,{observe: 'response'}).subscribe(
      (res) => {
        console.log(res);
        if (res != null) {
          //redirect to new page to download pdf
          this.router.navigateByUrl('/home');
        }
      },
      err => {
        if (err.status == 401) {
          alert("Not logged in!");
        }
      }
    );
  }

  setInvoices(invoices : any){
    this.invoices = invoices;
    console.log("invoices set");
  }

}
