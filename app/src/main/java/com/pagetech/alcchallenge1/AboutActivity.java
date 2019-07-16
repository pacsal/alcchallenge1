package com.pagetech.alcchallenge1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    WebView alcWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        alcWebview = findViewById(R.id.alc_webview);
        alcWebview.getSettings().setJavaScriptEnabled(true);
        alcWebview.getSettings().setBuiltInZoomControls(true);
        alcWebview.setWebViewClient(new SSLTolerentWebViewClient());
        alcWebview.loadUrl("https://andela.com/alc/");
    }

    private class SSLTolerentWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(AboutActivity.this);
            String message = "SSL Certificate of "+error.getUrl()+" Warning.";
            switch (error.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    message = "The certificate of "+error.getUrl()+" authority is not trusted.";
                    break;
                case SslError.SSL_EXPIRED:
                    message = "The certificate of "+error.getUrl()+" has expired.";
                    break;
                case SslError.SSL_IDMISMATCH:
                    message = "The certificate Hostname mismatch.";
                    break;
                case SslError.SSL_NOTYETVALID:
                    message = "The certificate of "+error.getUrl()+" is not yet valid.";
                    break;
            }
            message += " Do you want to continue anyway?";

            builder.setTitle(getString(R.string.about_ssl_warning_text)+"");
            builder.setMessage(message);
            builder.setPositiveButton(getString(R.string.about_positive_text)+"", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton(getString(R.string.about_negative_text)+"", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
