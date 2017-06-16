package slydepay.com.tonte.sampleslydepayintegration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void createInvoice() {

        Map<String,Object> map = new HashMap();
        map.put("emailOrMobileNumber","tonteowuso@gmail.com");
        map.put("merchantKey",App.API_KEY);
        map.put("amount",1.0);
        map.put("orderCode",UUID.randomUUID().toString());

        apiClient.createInvoice(map).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                dialog.dismiss();
                Result result = response.body().getResult();
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                intent.putExtra("url","https://app.slydepay.com/paylive/detailsnew.aspx?pay_token=" +result.getPayToken());
                startActivity(intent);

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
