package com.devmedia.artigoretrofit;

import android.util.Log;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class ErrosHandler implements ErrorHandler{

	@Override
	public Throwable handleError(RetrofitError error) {
		
		Log.i("retrofit", error.getCause().toString());
		return error;
	}

}
