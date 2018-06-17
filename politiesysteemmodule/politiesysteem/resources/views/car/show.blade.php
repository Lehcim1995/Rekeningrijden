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
                                <h4>{{ __('carshow.carinfo') }}</h4>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>{{ __('carshow.brand') }}</label><br>
                                        <label>{{ __('carshow.license') }}</label><br>
                                        <label>{{ __('carshow.weight') }}</label><br>
                                        <label>{{ __('carshow.fuel') }}</label><br>
                                        <label>{{ __('carshow.year') }}</label><br>
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
                                <h4>{{ __('carshow.ownerinfo') }}</h4>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>{{ __('carshow.name') }}</label><br>
                                        <label>{{ __('carshow.bsn') }}</label><br>
                                        <label>{{ __('carshow.address') }}</label><br>
                                        <label>{{ __('carshow.city') }}</label><br>
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
                        <div class="row">
                            <div class="col-md-8">
                                <h4>{{ __('carshow.ridehistory') }}</h4>
                            </div>
                            <div class="col-md-2">
                                <span style="float: right; margin-top: 5px; font-size: 17px">{{ __('carshow.checkpoints') }}
                                    : </span>
                            </div>
                            <div class="col-md-1">
                                <input id="checkpointAmount" type="number" value="10" class="form-control"
                                       style="width: 4vw; text-align: center;">
                            </div>
                            <div class="col-md-1">
                                <h4><i id="map-spinner" class="fa fa-refresh fa-spin"
                                       style="float:right; margin-top: 6px; cursor:pointer" onclick="refreshMap()"></i>
                                </h4>
                            </div>
                        </div>
                    </div>
                    <div id="map-holder" class="card-body">
                        <div id="map" style="height: 43vh ;  width: 100% !important; min-height: 100% !important"></div>
                    </div>
                </div>

                @if(!$car->retrieved)
                    <div class="row" style="margin-top: 1.5vh">
                        <div class="col-md-12">
                            <button class="btn btn-primary" style="float: right"
                                    onclick="window.location.href = '/car/{{ $car->id }}/retrieve'">{{ __('carshow.carfound') }}</button>
                        </div>
                    </div>
                @endif
            </div>
        </div>
    </div>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <script>
        let license = '{{ $car->license_plate }}';

        //Generate the map to show last locations
        function generateMap(routes) {
            if (routes.length != 0) {
                $('#map').html('');

                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 11,
                    center: {lat: routes[0][1].lat, lng: routes[0][1].lng},
                    mapTypeId: 'terrain'
                });

                routes.forEach(function (route) {
                    var flightPath = new google.maps.Polyline({
                        path: route,
                        geodesic: true,
                        strokeColor: '#FF0000',
                        strokeOpacity: 1.0,
                        strokeWeight: 1
                    });

                    flightPath.setMap(map);
                });
            } else {
                $('#map').html('<h1 style="text-align: center;">Er is geen route beschikbaar</h1>');
            }

            $('#map-spinner').removeClass('fa-spin');
        }

        //Get data when page is loaded
        $(document).ready(function () {
            getCarInfo();
        });

        //Get the last location the car was registered
        function getLastPoints(carId) {
            axios.get('http://192.168.25.135:8080/verplaatsingsmodule/rest/verplaatsing/' + carId + '/waypoints?limit=' + $('#checkpointAmount').val())
                .then(function (response) {
                    console.log(response.data);
                    parseData(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                })
        }

        //Parse data for Google maps
        function parseData(data) {
            var routes = [];
            var route = [];

            data.forEach(function (movement) {
                movement.checkpoints.forEach(function(checkpoint){
                    route.push({'lat': checkpoint.lat, 'lng': checkpoint.lon});
                });
            });
            routes.push(route);

            console.log(routes);
            generateMap(routes)
        }

        //Use AJAX to get car info
        function getCarInfo() {
            axios.get('http://192.168.25.135:8080/rekeningadministratiemodule/rest/vehicle/' + license)
                .then(function (response) {
                    getLastPoints(response.data.ID);
                    displayCarInfo(response.data);
                })
                .catch(function (error) {
                    console.log(error);
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
            if (!$('#map-spinner').hasClass('fa-spin')) {
                $('#map-spinner').addClass('fa-spin');
                getLastPoints();
            }
        }
    </script>

    {{--Google api script include--}}
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDXdAc0zDyz_bjoXzZRLGv8IfzxphVlhfU"></script>
@endsection