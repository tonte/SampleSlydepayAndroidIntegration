package slydepay.com.tonte.sampleslydepayintegration;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Tonte on 6/15/17.
 */

public interface ApiInterface {
    @Headers({
            "Accept: application/json",
            "Content-Type:application/json"
    })
    @POST ("invoice/create")
    Call<APIResponse> createInvoice(@Body Map<String, Object> options);

}
