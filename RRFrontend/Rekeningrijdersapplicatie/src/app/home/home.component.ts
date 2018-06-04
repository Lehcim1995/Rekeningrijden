import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  vehicles: any;

  selectedCar: any;
  searchText: any;

  lastCar: any;

  constructor() {
  }

  ngOnInit() {
    this.vehicles = [
      {name: "auto 1", licenseplate: "XL 89 555", classification: "Benzine", trackerID: "17453", ownerID: "8"},
      {name: "auto 2", licenseplate: "XL 54 423", classification: "Diesel", trackerID: "17482", ownerID: "8"}
    ];

    this.selectedCar = "";

  }

//vehicletracker id en owner id
  chooseCar(vehicle) {
    this.lastCar = vehicle.trackerID;
    //servicecall met vehicletrackerid en ownerid

    //invoices wordt returnwaarde van servicecall

    //mockcode for testing
    if (+vehicle.trackerID == 17453) {
      this.selectedCar = vehicle;
    }
    if (+vehicle.trackerID == 17482) {
      this.selectedCar = vehicle;
    }



  }

  goBack() {
    this.selectedCar = '';
  }

}
