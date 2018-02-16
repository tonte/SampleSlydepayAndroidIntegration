package slydepay.com.tonte.sampleslydepayintegration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import slydepay.com.tonte.sampleslydepayintegration.model.APIResponse;
import slydepay.com.tonte.sampleslydepayintegration.model.PayOption;
import slydepay.com.tonte.sampleslydepayintegration.network.APIUtils;
import slydepay.com.tonte.sampleslydepayintegration.network.ApiClient;
import slydepay.com.tonte.sampleslydepayintegration.network.ApiInterface;

public class WebViewActivity extends AppCompatActivity {

    WebView web ;
    String url,orderCode,payToken;
    private ApiInterface apiClient;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        orderCode = getIntent().getStringExtra("orderCode");
        payToken = getIntent().getStringExtra("payToken");
        apiClient = APIUtils.getAPIService();
        setContentView(R.layout.activity_web_view);
        web = (WebView)findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.contains(App.CALLBACK_URL)){
                    // Success
                    //Check Payment Status and confirm payment on Slydepay
                    view.destroy();
                    dialog = new ProgressDialog(WebViewActivity.this);
                    dialog.setMessage("Confirming Transaction, Please Wait");
                    dialog.show();

                    Map<String,Object> map = new HashMap();
                    map.put("emailOrMobileNumber", App.EMAIL_OR_MOBILE_NUMBER);
                    map.put("merchantKey",App.MERCHANT_KEY);
                    map.put("orderCode",orderCode);
                    map.put("payToken",payToken);
                    map.put("confirmTransaction",true);


                   apiClient.checkStatus(map).enqueue(new Callback<APIResponse>() {
                       @Override
                       public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
//
                           if (response.body().getSuccess()){
                               String result = response.body().getResult().toString();
                               if (result.equals("CONFIRMED")){
                                   dialog.dismiss();
                                   Intent intent = new Intent(WebViewActivity.this, SuccessActivity.class);
                                   startActivity(intent);
                                   finish();
                               }
                               else if (result.equals("PENDING")) {

                               }
                               else if (result.equals("CANCELLED")) {

                               }
                               else if (result.equals("DISPUTED")) {

                               }
                           }

                           else{
                               if (response.body().getErrorMessage() != null){
                                   dialog.dismiss();
                                   Toast.makeText(WebViewActivity.this,response.body().getErrorMessage(),Toast.LENGTH_LONG).show();
                               }
                           }


                       }
                       @Override
                       public void onFailure(Call<APIResponse> call, Throwable t) {
                           dialog.dismiss();

                       }
                   });
                }

                }

            });






    }

}
