<?php

//Home/welcome routes
Route::get('/', function () {
    return view('welcome');
});

Route::get('/home', 'HomeController@index')->name('home')->middleware('auth');

//Auth routes
Auth::routes();

//Car routes
Route::get('/car', 'CarController@index')->middleware('auth');

Route::post('/car', 'CarController@store')->middleware('auth');

Route::get('/car/{car}', 'CarController@show')->middleware('auth');
