package slydepay.com.tonte.sampleslydepayintegration.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tonte on 6/15/17.
 */

public class ApiClient {


    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseURL) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
