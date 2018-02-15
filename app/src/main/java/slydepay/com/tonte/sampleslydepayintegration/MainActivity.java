package slydepay.com.tonte.sampleslydepayintegration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import slydepay.com.tonte.sampleslydepayintegration.model.APIResponse;
import slydepay.com.tonte.sampleslydepayintegration.model.InvoiceDetails;
import slydepay.com.tonte.sampleslydepayintegration.model.PayOption;
import slydepay.com.tonte.sampleslydepayintegration.network.APIUtils;
import slydepay.com.tonte.sampleslydepayintegration.network.ApiInterface;

public class MainActivity extends AppCompatActivity {
    Button payButton;
    ProgressDialog dialog;
    private ApiInterface apiClient;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = APIUtils.getAPIService();

        setContentView(R.layout.activity_main);
        payButton = (Button) findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter an amount",Toast.LENGTH_SHORT).show();
                }
                else{
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setMessage("Creating Invoice, Please Wait");
                    dialog.show();
                    createInvoice();
                }
    }
        });




    }



    public void listPayOptions(){
        Map<String,Object> map = new HashMap();
        map.put("emailOrMobileNumber", App.EMAIL_OR_MOBILE_NUMBER);
        map.put("merchantKey",App.MERCHANT_KEY);
        apiClient.payOptions(map).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                Gson gson = new Gson();
                String json = gson.toJson(response.body().getResult()); // serializes target to Json
                Type listType = new TypeToken<ArrayList<PayOption>>() {}.getType();
                ArrayList<PayOption> result = gson.fromJson(json, listType); // deserializes json into ArrayList<PayOption>

            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }


    public void createInvoice() {

        final Map<String,Object> map = new HashMap();
        map.put("emailOrMobileNumber", App.EMAIL_OR_MOBILE_NUMBER);
        map.put("merchantKey",App.MERCHANT_KEY);
        map.put("amount",Double.parseDouble(editText.getText().toString()));
        map.put("orderCode",UUID.randomUUID().toString());

        apiClient.createInvoice(map).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                dialog.dismiss();
                if (response.body().getSuccess()){
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body().getResult()); // serializes result to Json
                    InvoiceDetails result = gson.fromJson(json, InvoiceDetails.class); // deserializes json into InvoiceDetails object

                    Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                    intent.putExtra("url","https://app.slydepay.com/paylive/detailsnew.aspx?pay_token=" +result.getPayToken());
                    intent.putExtra("orderCode",map.get("orderCode").toString());
                    intent.putExtra("payToken",result.getPayToken());

                    startActivity(intent);
                }
                else{
                    if (response.body().getErrorMessage() != null){
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,response.body().getErrorMessage(),Toast.LENGTH_LONG).show();
                    }
                }



            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                dialog.dismiss();
                Log.d("request",call.toString());
                Toast.makeText(MainActivity.this,call.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
