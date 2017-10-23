package com.tyondo.apps.tyondoenterprise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    public   ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.siteProgressBar);


        mWebView = findViewById(R.id.siteWebView);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new SiteWebViewClient(progressBar));

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Use remote resource
        mWebView.loadUrl(getResources().getString(R.string.remoteUrl));

        // Stop local links and redirects from opening in browser instead of WebView
        // mWebView.setWebViewClient(new SiteWebViewClient());

        // Use local resource
        // mWebView.loadUrl(getResources().getString(R.string.localUrl));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    { //if back key is pressed
        if((keyCode == KeyEvent.KEYCODE_BACK)&& mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            AlertDialog.Builder alertDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            //setting alert title
            alertDialogueBuilder.setTitle("Exit");
            //setting the message
            alertDialogueBuilder.setMessage(getResources().getString(R.string.site_exit_message))
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //if the button is clicked, close the current activity
                            MainActivity.this.finish();

                            // super.onBackPressed();
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //if this button is selected just close the dialogue and do nothing else
                            dialogInterface.cancel();
                        }
                    });
            //creating the dialogue
            AlertDialog alertDialog = alertDialogueBuilder.create();
            //displaying it
            alertDialog.show();
        }
    }
}
