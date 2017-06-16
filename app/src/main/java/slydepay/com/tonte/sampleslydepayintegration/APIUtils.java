package slydepay.com.tonte.sampleslydepayintegration;

/**
 * Created by Tonte on 6/15/17.
 */

public class APIUtils {
//    public static final String createInvoiceURL = "https://app.slydepay.com/api/merchant/" ;
    private APIUtils() {}

    public static final String BASE_URL = "https://app.slydepay.com.gh/api/merchant/";

    public static ApiInterface getAPIService() {

        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
