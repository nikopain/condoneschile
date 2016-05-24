package usm.cc.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by niko on 17/05/2016.
 */
public class apiConnection {

    //REST
    public static final String BASE_URL = "http://45.55.159.85/api/";

    public static Retrofit getClient() {

        OkHttpClient httpClient = new OkHttpClient();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
