@extends('layouts.app')

@section('content')
    <style>
        .fa-refresh {
            cursor: pointer !important;
        }
    </style>

    <div class="container">
        <div class="row">
            <div class="col-lg-12">

                <div class="card">
                    <div class="card-header">
                        <h4>{{ $car->license_plate }}
                            <span style="float: right">
                                @if($car->retrieved)
                                    <i class="fa fa-check" aria-hidden="true"
                                       style="color: green;"></i>
                                @else
                                    <i class="fa fa-times" aria-hidden="true" style="color: red;"></i>
                                @endif
                            </span>
                        </h4>
                    </div>

                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <h4>Auto informatie</h4>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>Merk</label><br>
                                        <label>Kenteken</label><br>
                                        <label>Gewicht</label><br>
                                        <label>Brandstof</label><br>
                                        <label>Bouwjaar</label><br>
                                    </div>

                                    <div class="col-md-6">
                                        <label id="carBrand"></label><br>
                                        <label id="carLicense"></label><br>
                                        <label id="carWeight"></label><br>
                                        <label id="carFuel"></label><br>
                                        <label id="carBuildYear"></label><br>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <h4>Eigenaar informatie</h4>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>Naam</label><br>
                                        <label>BSN</label><br>
                                        <label>Adres</label><br>
                                        <label>Woonplaats</label><br>
                                    </div>

                                    <div class="col-md-6">
                                        <label id="ownerName"></label><br>
                                        <label id="ownerBsn"></label><br>
                                        <label id="ownerAddress"></label><br>
                                        <label id="ownerCity"></label><br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <br>

                <div class="card">
                    <div class="card-header">
                        <h4>Rit gegevens <i id="map-spinner" class="fa fa-refresh fa-spin" style="float:right; margin-top: 6px; cursor:pointer" onclick="refreshMap()"></i></h4>
                    </div>
                    <div id="map-holder" class="card-body">
                        <div id="map" style="height: 43vh ;  width: 100% !important; min-height: 100% !important"></div>
                    </div>
                </div>

                @if(!$car->retrieved)
                    <div class="row" style="margin-top: 1.5vh">
                        <div class="col-md-12">
                            <button class="btn btn-primary" style="float: right" onclick="window.location.href = '/car/{{ $car->id }}/retrieve'">Auto terug gevonden</button>
                        </div>
                    </div>
                @endif
            </div>
        </div>
    </div>

    <script>
        var license = '{{ $car->license_plate }}';

        //Generate the map to show last locations
        function generateMap(route) {
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 3,
                center: {lat: route[0].lat, lng: route[1].lng},
                mapTypeId: 'terrain'
            });

            var flightPath = new google.maps.Polyline({
                path: route,
                geodesic: true,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 1
            });

            flightPath.setMap(map);
            //Stop the spinner
            $('#map-spinner').removeClass('fa-spin');
        }

        //Get data when page is loaded
        $(document).ready(function () {
            getCarInfo();
        });

        //Get the last location the car was registered
        function getLastPoints() {
//            $.get('[ip]:[port]/rekeningsadministratiemodule/rest/vehicle/' + license, function (data) {
            $.get('/cords' , function (data) {
                parseData(data)
            });
        }

        //Parse data for Google maps
        function parseData(data) {
            var route = [];

            for(var i = 0, y = data.length; i < y; i++) {
                route.push({'lat': data[i].lat, 'lng': data[i].lon});
            }

            generateMap(route)
        }

        //Use AJAX to get car info
        function getCarInfo() {
//            $.get('[ip]:[port]/rekeningsadministratiemodule/rest/vehicle/' + license + '/locationdata', function (data) {
            $.get('/spoofPerson', function (data) {
                displayCarInfo(data);
            });
        }

        //Display the information in the correct labels
        function displayCarInfo(data) {
            //Display all car info
            $('#carBrand').text(data.tracker.manufacturer);
            $('#carLicense').text(data.licensePlate);
            $('#carFuel').text(data.fueltype);
            $('#carWeight').text(data.weight);
            $('#carBuildYear').text(data.buildYear);

            //Display owner info
            $('#ownerName').text(data.owner.firstName + ' ' + data.owner.middleName + ' ' + data.owner.lastName);
            $('#ownerBsn').text(data.owner.citizenId);
            $('#ownerAddress').text(data.owner.address);
            $('#ownerCity').text(data.owner.city);
        }

        //Refresh the map
        function refreshMap() {
            if(!$('#map-spinner').hasClass('fa-spin')) {
                $('#map-spinner').addClass('fa-spin');
                getLastPoints();
            }
        }
    </script>

    {{--Google api script include--}}
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXdAc0zDyz_bjoXzZRLGv8IfzxphVlhfU&callback=getLastPoints"></script>
@endsection