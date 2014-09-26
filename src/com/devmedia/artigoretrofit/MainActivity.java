package com.devmedia.artigoretrofit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devmedia.artigoretrofit.interfaces.OpenWeatherService;
import com.devmedia.artigoretrofit.pojos.City;

public class MainActivity extends ActionBarActivity {

	TextView tvCidade;
	EditText etCidade;
	TextView tvTemp;
	Button btnParserGson;
	Button btnParserJObject;

	RestAdapter restAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etCidade = (EditText) findViewById(R.id.etCidade);
		tvTemp = (TextView) findViewById(R.id.tvTemp);
		btnParserGson = (Button) findViewById(R.id.btnParserGson);
		btnParserJObject = (Button) findViewById(R.id.btnParserJObject);

		/*
		 * Criando o RestAdapter Trecho 01
		 */
		restAdapter = new RestAdapter.Builder().setEndpoint(
				"http://api.openweathermap.org/data/2.5/").build();

		/*
		 * Implementação da interface Trecho 02
		 */

		btnParserGson.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				OpenWeatherService openWeatherService = restAdapter
						.create(OpenWeatherService.class);

				/*
				 * Fazendo a chamada ao serviço Trecho 03
				 */

				openWeatherService.getWeatherByCity(etCidade.getText()
						.toString(), new Callback<City>() {

					@Override
					public void failure(RetrofitError error) {

						Log.e("Error Retrofit", error.getMessage());
					}

					@Override
					public void success(City city, Response resp) {

						tvTemp.setText(city.getMain().getTemp().toString());

					}
				});
			}
		});

		btnParserJObject.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				RestAdapter restAdapterParser = new RestAdapter.Builder()
						.setConverter(new ExampleConverter())
						.setErrorHandler(new ErrosHandler())
						.setEndpoint("http://api.openweathermap.org/data/2.5/")
						.build();

				OpenWeatherService openWeatherService = restAdapterParser
						.create(OpenWeatherService.class);

				openWeatherService.getWeatherByCityWithConverter(etCidade
						.getText().toString(), new Callback<City>() {

					@Override
					public void failure(RetrofitError error) {
						Log.e("Error Retrofit", error.getMessage());
					}

					@Override
					public void success(City city, Response resp) {

						tvTemp.setText(city.getMain().getTemp().toString());
					}
				});
			}
		});
	}
}
