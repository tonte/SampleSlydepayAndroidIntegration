package slydepay.com.tonte.sampleslydepayintegration.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import slydepay.com.tonte.sampleslydepayintegration.model.APIResponse;

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
    @POST ("invoice/checkstatus")
    Call<APIResponse> checkStatus(@Body Map<String, Object> options);


}
