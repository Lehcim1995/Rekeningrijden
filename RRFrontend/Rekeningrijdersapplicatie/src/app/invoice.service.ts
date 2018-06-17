import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import * as moment from 'moment';

@Injectable()
export class InvoiceService {

  public invoices: any = [];
  public vehicles: any = [];


  // private getInvoicesByCar = "http://localhost:8080/administratie/api/vehicle/";
  private getMovementsForCarWithMonthUL = "http://localhost:8080/verplaatsingsmodule/rest/verplaatsing";
  private getPDF = "http://localhost:8080/rekeningadministratiemodule/rest/invoice";
  private getInvoices = "http://localhost:8080/rekeningadministratiemodule/rest/invoice/cpr";
  private getCarOfOwner = "http://localhost:8080/rekeningadministratiemodule/rest/vehicle/cars";
  private getCarByCarTracker  = "http://localhost:8080/rekeningadministratiemodule/api/vehicle/getcarbycartracker";
  private payInvoiceURL  = "http://localhost:8080/rekeningadministratiemodule/rest/invoice/pay";



  constructor(protected httpClient: HttpClient, private router: Router) {


  }

  getInvoicesByCPR(cpr:string){
    return this.httpClient.get(`${this.getInvoices}/${cpr}`,{observe: 'response'});
  }
  public getCarByTrackerid(trackerId){
    console.log(`${this.getCarByCarTracker}/${trackerId}`);
    return this.httpClient.get(`${this.getCarByCarTracker}/${trackerId}`,{observe: 'response'});
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

    let newDate : Date;
    newDate = new Date(parseInt(year), parseInt(lastMonth), 1);
    newDate.setHours(-1);
    let lastDayOfLastMonth = newDate.getDate();
    console.log("dayofPreviousmonth ( "+ lastMonth + ") = ", lastDayOfLastMonth);

    console.log(`${this.getMovementsForCarWithMonthUL}/${carId}/waypoints?startdate=${lastDayOfLastMonth}/${lastMonth}/${year}&enddate=01/${nextMonth}/${year}`);
    return this.httpClient.get(`${this.getMovementsForCarWithMonthUL}/${carId}/waypoints?startdate=${lastDayOfLastMonth}/${lastMonth}/${year}&enddate=01/${nextMonth}/${year}`)
  }

  public downloadPDF(invoiceid: string){
    window.open(`${this.getPDF}/${invoiceid}/download`, "_blank");
  }

  setInvoices(invoices : any){
    this.invoices = invoices;
    console.log("invoices set");
  }

  public payInvoice(invoiceId : any){
    this.httpClient.post(`${this.payInvoiceURL}/${invoiceId}`,{observe: 'response'}).subscribe(
      (res) => {
        console.log(res);
        if (res != null) {


        }
      },
      err => {
        if (err.status == 304) {
          alert("Paymentstatus not succefully changed");
        }
      }
    );
  }

  public getCarsOfOwner(cpr :any)
  {
    return this.httpClient.get(`${this.getCarOfOwner}/${cpr}`,{observe: 'response'});
  }

  setCars(body: any) {
    this.vehicles = body;
    console.log("vehicles set");
  }
}
