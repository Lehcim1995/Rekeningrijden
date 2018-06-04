import {Component, EventEmitter, AfterViewChecked, Input, OnInit, Output} from '@angular/core';

declare let paypal: any;

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit, AfterViewChecked {

  addScript: boolean = false;
  paypalLoad: boolean = true;

  @Input() selectedCar: any;
  lastInvoice: any;
  selectedInvoice: any;
  invoices: any;
  invoices1: any;
  invoices2: any;
  selectedInvoices: any;
  carFilter: any;
  timeFilter: any;
  rides: any[][] = [
    [
      {lat: 37.77, lon: -122.21}, {lat: 21.29, lon: -157.82}, {lat: -18.14, lon: 178.43}, {lat: -27.46, lon: 153.03}
    ],
    [{lat: 40.77, lon: -122.21}, {lat: 25.29, lon: -157.82}, {lat: -18.14, lon: 178.43}, {lat: -27.46, lon: 153.03}]
  ];
  lat: number = this.rides[0][0].lat;
  lng: number = this.rides[0][0].lon;
  //this parameter should be the total price of all the selected invoices
  amount: any = 100;

  paypalConfig = {
    env: 'sandbox',
    client: {
      sandbox: 'Aa-dYoaxN06RJoUM-jjVk1iO4aGa-hXQr2k2bRxWKytXYqV_jC5GYmhMvq3VLQgvzhLhOzPJeheFSKFO',
      production: 'AYL17kXYM_MH5s93vfaLeLnKuOENhU1IywuAv9cjXWBCO9ZVNbM-i58lUFYObofBfbTCjFd923t4-wEc'
    },
    commit: true,
    payment: (data, actions) => {
      return actions.payment.create({
        payment: {
          transactions: [
            { amount: { total: this.amount, currency: 'USD' } }
          ]
        }
      });
    },
    onAuthorize: (data, actions) => {
      return actions.payment.execute().then((payment) => {
        alert("payment succesfull");
      })
    }
  };


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

    if (!this.addScript) {
      this.addPaypalScript().then(() => {
        paypal.Button.render(this.paypalConfig, '#paypal-checkout-btn');
        this.paypalLoad = false;
      })
    }
  }

  ngAfterViewChecked(): void {

  }

  addPaypalScript() {
    this.addScript = true;
    return new Promise((resolve, reject) => {
      let scripttagElement = document.createElement('script');
      scripttagElement.src = 'https://www.paypalobjects.com/api/checkout.js';
      scripttagElement.onload = resolve;
      document.body.appendChild(scripttagElement);
    })
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
