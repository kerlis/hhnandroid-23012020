package me.doapps.appdhn.connection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.doapps.appdhn.BuildConfig;
import me.doapps.appdhn.models.Alert;
import me.doapps.appdhn.models.Token;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Leandro on 10/27/17.
 */

public interface MgpApi {

    String BASE_URL_MGP = BuildConfig.BASE_URL_MGP;
    String BASE_URL = BuildConfig.BASE_URL;
    String FIREBASE_URL = BuildConfig.FIREBASE_URL;

    Retrofit RETROFIT_MGP = new Retrofit.Builder()
            .baseUrl(BASE_URL_MGP)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   Retrofit RETROFIT_FIREBASE = new Retrofit.Builder()
            .baseUrl(FIREBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("usuario")
    Call<JsonObject> getRegisterUsuario(@Body Token token);

    @FormUrlEncoded
    @POST("message/list")
    Call<JsonObject>  getListPush(@Field("PER_PAGE") int page);

    @GET("cnat/RepUtl10SisJson.php")
    Call<JsonArray> getListReport();

    @GET(BuildConfig.NOTIFICATION)
    Call<Alert> getNotification();
}