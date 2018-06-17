<?php

//Home/welcome routes
Route::get('/', function () {
    return view('welcome');
});

//Auth routes
Auth::routes();

//Only allow logged in users
Route::middleware(['auth'])->group(function () {
    //Car routes
    Route::get('/{locale}/car', 'CarController@index');

    Route::get('/car', function () {
        return redirect('/' . App::getLocale() . '/car');
    });

    Route::post('/car', 'CarController@store');

    Route::get('/{locale}/car/{car}', 'CarController@show');

    Route::get('/car/{car}/retrieve', 'CarController@retrieve');

    Route::get('/spoofCords', 'CarController@spoofCords');

    Route::get('/spoofPerson', 'CarController@spoofPerson');

    Route::get('/car/check/{license}', 'CarController@checkLicense');

    Route::get('welcome/{locale}', function ($locale) {
        App::setLocale($locale);

        return redirect('/' . App::getLocale() . '/car');
    });
});

