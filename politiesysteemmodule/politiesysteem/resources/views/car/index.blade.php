@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-striped" style="text-align: center;">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 60%; text-align: left">#</th>
                        <th scope="col" style="width: 10%;">kenteken</th>
                        <th scope="col" style="width: 20%;">Sinds</th>
                        <th scope="col" style="width: 10%;">Opgelost</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach($cars as $car)
                        <tr onclick="window.location.href = '/car/{{ $car->id }}'" style="cursor: pointer;">
                            <th scope="row" style="text-align: left;">{{ $car->id }}</th>
                            <td>{{ $car->license_plate }}</td>
                            <td>{{ $car->created_at }}</td>
                            <td>
                                @if($car->retrieved)
                                    <i class="fa fa-check" aria-hidden="true"
                                       style="color: green; font-size: 15px;"></i>
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

        <form method="POST" action="/car">
            <div class="row">
                {{ csrf_field() }}
                <div class="col-lg-8">
                    <input name="license_plate" required class="form-control" placeholder="12-abc-1">
                </div>
                <div class="col-lg-3">
                    <button type="submit" class="btn btn-primary">Voeg auto toe</button>
                </div>
            </div>
        </form>
    </div>
@endsection