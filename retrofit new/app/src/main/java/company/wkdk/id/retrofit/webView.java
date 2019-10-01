package company.wkdk.id.retrofit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class webView extends AppCompatActivity {

    WebView webView;
    ProgressBar loader;


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        Intent intent = getIntent();
        String link=intent.getStringExtra("link");

        webView = findViewById(R.id.webView);
        loader= findViewById(R.id.loader);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    loader.setVisibility(View.GONE);
                } else {
                    loader.setVisibility(View.VISIBLE);
                }
            }
        });


    }


}
