@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <input id="plateSearch" type="text" class="form-control" placeholder="Zoek op kentekenplaat...">
                <table class="table table-striped" style="text-align: center;">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 60%; text-align: left">#</th>
                        <th scope="col" style="width: 10%;">kenteken</th>
                        <th scope="col" style="width: 20%;">Sinds</th>
                        <th scope="col" style="width: 10%;">Opgelost</th>
                    </tr>
                    </thead>
                    <tbody id="carsTable">
                    @foreach($cars as $car)
                        <tr onclick="window.location.href = '/car/{{ $car->id }}'" style="cursor: pointer;">
                            <th scope="row" style="text-align: left;">{{ $car->id }}</th>
                            <td>{{ $car->license_plate }}</td>
                            <td>{{ $car->created_at }}</td>
                            <td>
                                @if($car->retrieved)
                                    <i class="fa fa-check" aria-hidden="true" style="color: green; font-size: 15px;"></i>
                                @else
                                    <i class="fa fa-times" aria-hidden="true" style="color: red; font-size: 15px;"></i>
                                @endif
                            </td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>
            </div>
        </div>

        <form id="addCarForm" method="POST" action="/car">
            <div class="row">
                {{ csrf_field() }}
                <div class="col-lg-8">
                    <input name="license_plate" required class="form-control" placeholder="12-abc-3">
                </div>
                <div class="col-lg-3">
                    <button id="submitForm" type="submit" class="btn btn-primary">Voeg auto toe</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        $(document).ready(function(){
            $("#plateSearch").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#carsTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });

            $('#submitForm').click(function (e) {
                 e.preventDefault();

                 $.get('/as', function(result) {
                     //TODO: parse result and handle accordingly
                     $('#addCarForm').submit();
                 });
            });
        });
    </script>
@endsection