<?php

namespace App\Http\Controllers;

use App\Car;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\App;

class CarController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($locale)
    {
        App::setLocale($locale);
        //Get all cars
        $cars = Car::orderBy('retrieved', 'asc')->get();

        //Load view with all cars
        return view('car.index', compact('cars'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $car = new Car();
        $car->license_plate = $request->input('license_plate');
        $car->retrieved = false;

        $car->save();

        return redirect('/' . App::getLocale(). '/car/' . $car->id);
    }

    /**
     * Display the specified resource.
     *
     * @param Car $car
     * @return \Illuminate\Http\Response
     */
    public function show($locale, Car $car)
    {
        App::setLocale($locale);

        return view('car.show', compact('car'));
    }

    /**
     * Update the retrieved car
     *
     * @param Car $car
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function retrieve(Car $car)
    {
        $car->retrieved = true;
        $car->save();

        return redirect('/' . App::getLocale() . '/car');
    }

    public function checkLicense($license)
    {
        $car = Car::where('license_plate', $license)->first();

        return $car == null || $car->retrieved;
    }

    public function spoofCords()
    {
        return [['lat' => 37.77, 'lon' => -122.21], ['lat' => 21.29, 'lon' => -157.82], ['lat' => -18.14, 'lon' => 178.43], ['lat' => -27.46, 'lon' => 153.03]];
    }

    public function spoofPerson()
    {
        return [
            'id' => 1,
            'tracker' => [
                'manufacturer' => 'Skoda',
            ],
            'weight' => 1234,
            'licensePlate' => 'ab-123-a',
            'fueltype' => 'diesel',
            'buildYear' => Carbon::now()->year,
            'owner' => [
                'id' => 2,
                'citizenId' => 2,
                'firstName' => 'Luuk',
                'middleName' => 'de',
                'lastName' => 'Weijer',
                'address' => 'Leonard van Vechelstraat 51',
                'city' => 'tilburg',
            ],
            'perviousOwners' => [],
        ];
    }
}
