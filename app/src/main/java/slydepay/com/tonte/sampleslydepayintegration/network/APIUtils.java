package slydepay.com.tonte.sampleslydepayintegration.network;

/**
 * Created by Tonte on 6/15/17.
 */

public class APIUtils {
    private APIUtils() {}

    public static final String BASE_URL = "https://app.slydepay.com.gh/api/merchant/";

    public static ApiInterface getAPIService() {
return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
