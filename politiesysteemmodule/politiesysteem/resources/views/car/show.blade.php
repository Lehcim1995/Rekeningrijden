@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">#{{ $car->id . ' ' . $car->license_plate }}</div>
                    <div class="card-body">
                        car info
                    </div>
                </div>

                <br>

                <div class="card">
                    <div class="card-header">Rit gegevens</div>
                    <div class="card-body">
                        kaart met alle ritten
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection