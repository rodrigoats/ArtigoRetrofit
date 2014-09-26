package com.devmedia.artigoretrofit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import com.devmedia.artigoretrofit.pojos.City;
import com.devmedia.artigoretrofit.pojos.Main;


public class ExampleConverter implements Converter {
	
	/* Trecho 01 */
	@Override
	public Object fromBody(TypedInput body, Type type)
			throws ConversionException {

		City city = new City();
		Main main = new Main();

		try {

			/* Trecho 02 */
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					body.in()));
			StringBuilder out = new StringBuilder();
			String newLine = System.getProperty("line.separator");
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
				out.append(newLine);
			}

			/* Trecho 03 */
			JSONObject jsonCity = new JSONObject(out.toString());

			JSONObject jsonMain = jsonCity.getJSONObject("main");
			
			main.setTemp(jsonMain.optDouble("temp"));
			city.setMain(main);

		} catch (IOException | JSONException e) {

			e.printStackTrace();
		}

		return city;
	}

	/* Trecho 04 */
	@Override
	public TypedOutput toBody(Object arg0) {

		return null;
	}

}
