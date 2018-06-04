import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  @Input() selectedCar: any;
  lastInvoice: any;
  selectedInvoice: any;
  invoices: any;
  invoices1: any;
  invoices2: any;
  selectedInvoices: any;
  carFilter: any;
  timeFilter: any;

  constructor() {
  }

  ngOnInit() {
    this.selectedInvoice = [];
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
      {
        date: Date(),
        licenseplate: "XL 89 555",
        price: "124",
        trackerID: "17453",
        kmAmmount: "80",
        invoiceID: "541234"
      },
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
      {
        date: Date(),
        licenseplate: "XL 54 423",
        price: "1283",
        trackerID: "17482",
        kmAmmount: "701",
        invoiceID: "543"
      },
      {
        date: Date(),
        licenseplate: "XL 54 423",
        price: "311",
        trackerID: "17482",
        kmAmmount: "210",
        invoiceID: "898939"
      }
    ];
    if (this.selectedCar != '' && this.selectedCar != undefined) {
      if (+this.selectedCar.trackerID == 17453) {
        this.invoices = this.invoices1;
      }
      if (+this.selectedCar.trackerID == 17482) {
        this.invoices = this.invoices2;
      }
    }

    this.invoices = this.invoices1.concat(this.invoices2);

    this.selectedInvoices = [];
    this.carFilter = '';
    this.timeFilter = [];

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

  addInvoice(invoice: any) {
    this.selectedInvoices.push(invoice);
  }


}
