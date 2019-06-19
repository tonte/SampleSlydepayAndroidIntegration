package slydepay.com.tonte.sampleslydepayintegration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import slydepay.com.tonte.sampleslydepayintegration.model.APIResponse;
import slydepay.com.tonte.sampleslydepayintegration.model.InvoiceDetails;
import slydepay.com.tonte.sampleslydepayintegration.network.APIUtils;
import slydepay.com.tonte.sampleslydepayintegration.network.ApiInterface;

public class MainActivity extends AppCompatActivity {
    Button payButton;
    ProgressDialog dialog;
    private ApiInterface apiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = APIUtils.getAPIService();

        setContentView(R.layout.activity_main);
        payButton = (Button) findViewById(R.id.button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setMessage("Creating Invoice, Please Wait");
                    dialog.show();
                    createInvoice();
            }
        });
    }


    public void createInvoice(){
        final Map<String,Object> parameters = new HashMap();
        parameters.put("emailOrMobileNumber",     App.EMAIL_OR_MOBILE_NUMBER);
        parameters.put("merchantKey",App.MERCHANT_KEY);
        parameters.put("amount",0.01);
        parameters.put("orderCode",UUID.randomUUID().toString());

        apiClient.createInvoice(parameters).enqueue(new   Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                //First we dismiss the progressDialog
                dialog.dismiss();

                // this checks whether the invoice is created successfully
                if (response.body().getSuccess()){
                    // serialize response to Json
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body().getResult());
                    // deserializes json into InvoiceDetails object
                    InvoiceDetails invoiceDetails = gson.fromJson(json,  InvoiceDetails.class);
                    //fetch orderCode and payToken from result and pass them to our navigateToWebViewActivity function
                    String orderCode = invoiceDetails.getOrderCode();
                    String payToken = invoiceDetails.getPayToken();
                    navigateToWebViewActivity(orderCode,payToken);
                }
                //if create invoice was unsuccessful
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
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void navigateToWebViewActivity(String orderCode,String payToken){
        Intent intent = new     Intent(MainActivity.this,WebViewActivity.class);
        intent.putExtra("url","https://app.slydepay.com/paylive/detailsnew.aspx?pay_token=" +payToken);
        intent.putExtra("orderCode",orderCode);
        intent.putExtra("payToken",payToken);
        startActivity(intent);

    }
}
