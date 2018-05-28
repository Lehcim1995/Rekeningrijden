<?php

//Home/welcome routes
Route::get('/', function () {
    return view('welcome');
});

//Auth routes
Auth::routes();

//Only allow logged in users
Route::middleware(['auth'])->group(function () {
    //Home view
    Route::get('/home', 'HomeController@index')->name('home');

    //Car routes
    Route::get('/car', 'CarController@index');

    Route::post('/car', 'CarController@store');

    Route::get('/car/{car}', 'CarController@show');

    Route::get('/car/{car}/retrieve', 'CarController@retrieve');

    Route::get('/cords', 'CarController@cords');

    Route::get('/spoofPerson', 'CarController@spoofPerson');
});

