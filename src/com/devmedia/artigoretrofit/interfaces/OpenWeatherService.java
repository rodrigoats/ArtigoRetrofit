package com.devmedia.artigoretrofit.interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

import com.devmedia.artigoretrofit.pojos.City;

public interface OpenWeatherService {
	
	/*Trecho 01*/
	@GET("/weather?lang=pt")
	/*Trecho 02*/
	void getWeatherByCity(@Query("q") String q, Callback<City> callback);

	
	@Headers("Cache-Control: max-age=640000")
	@GET("/weather?lang=pt")
	void getWeatherByCityWithConverter(@Query("q") String q, Callback<City> callback);
}
