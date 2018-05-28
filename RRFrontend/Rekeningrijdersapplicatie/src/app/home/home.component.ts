import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  vehicles: any;

  invoices: any;
  invoices1: any;
  invoices2: any;

  selectedInvoice: any;

  lastCar: any;
  lastInvoice: any;

  constructor() {
  }

  ngOnInit() {
    this.vehicles = [
      {name: "auto 1", licenseplate: "XL 89 555", classification: "Benzine", trackerID: "17453", ownerID: "8"},
      {name: "auto 2", licenseplate: "XL 54 423", classification: "Diesel", trackerID: "17482", ownerID: "8"}
    ];


    this.invoices = [];
    this.invoices1 = [
      {
        date: Date(),
        licenseplate: "XL 89 555",
        price: "1500",
        trackerID: "17453",
        kmAmmount: "549",
        invoiceID: "123123"
      },
      {date: Date(), licenseplate: "XL 89 555", price: "124", trackerID: "17453", kmAmmount: "80", invoiceID: "541234"},
      {date: Date(), licenseplate: "XL 89 555", price: "1413", trackerID: "17453", kmAmmount: "423", invoiceID: "423"}
    ];
    this.invoices2 = [
      {
        date: Date(),
        licenseplate: "XL 54 423",
        price: "34232",
        trackerID: "17482",
        kmAmmount: "89477",
        invoiceID: "34"
      },
      {date: Date(), licenseplate: "XL 54 423", price: "4", trackerID: "17482", kmAmmount: "2", invoiceID: "1"},
      {date: Date(), licenseplate: "XL 54 423", price: "1283", trackerID: "17482", kmAmmount: "701", invoiceID: "543"},
      {date: Date(), licenseplate: "XL 54 423", price: "311", trackerID: "17482", kmAmmount: "210", invoiceID: "898939"}
    ];
    this.selectedInvoice = [];
  }

//vehicletracker id en owner id
  chooseCar(vehicle) {
    this.selectedInvoice = '';
    this.invoices = [];

    this.lastCar = vehicle.trackerID;
    //servicecall met vehicletrackerid en ownerid

    //invoices wordt returnwaarde van servicecall

    //mockcode for testing
    if (+vehicle.trackerID == 17453) {
      this.invoices = this.invoices1;
    }
    if (+vehicle.trackerID == 17482) {
      this.invoices = this.invoices2;
    }

  }

  chooseInvoice(invoice: any) {
    this.lastInvoice = invoice.invoiceID;
    //servicecall met invoiceid en ownerid

    //invoicesdetails wordt returnwaarde van servicecall

    //mockcode for testing
    if (invoice.invoiceID == 123123) {
      this.selectedInvoice = this.invoices[0];
    }

  }

}
