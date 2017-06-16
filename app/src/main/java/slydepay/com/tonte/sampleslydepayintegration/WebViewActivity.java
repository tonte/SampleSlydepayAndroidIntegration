package slydepay.com.tonte.sampleslydepayintegration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView web ;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");

        setContentView(R.layout.activity_web_view);
        web = (WebView)findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        Toast.makeText(WebViewActivity.this,url,Toast.LENGTH_LONG).show();
        web.loadUrl(url);





    }

}
