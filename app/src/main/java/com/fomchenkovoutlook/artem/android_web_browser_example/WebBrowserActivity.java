package com.fomchenkovoutlook.artem.android_web_browser_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fomchenkovoutlook.artem.android_web_browser_example.client.WebBrowserViewClient;

import static com.fomchenkovoutlook.artem.android_web_browser_example.constants
        .ConstantsInterface.ProtocolsInterface.*;

public class WebBrowserActivity
        extends AppCompatActivity {

    private EditText etWebSite;

    private WebView wvWebSite;

    private class WebViewSwipeListener
        implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        WebViewSwipeListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }

        void onSwipeRight() {}

        void onSwipeLeft() {}

        private final class GestureListener
                extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }

    private void goToWebSite(String address) {
        if (!address.startsWith(HTTP_PROTOCOL)
                || !address.startsWith(HTTPS_PROTOCOL)) {
            address = HTTPS_PROTOCOL + address;
        }

        wvWebSite.loadUrl(address);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        etWebSite = findViewById(R.id.et_web_site);

        ImageButton ibGoToWebSite = findViewById(R.id.ib_go_to_web_site);
        ibGoToWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebSite(etWebSite.getText().toString());
            }
        });

        wvWebSite = findViewById(R.id.wv_web_site);
        wvWebSite.setWebViewClient(new WebBrowserViewClient(etWebSite));
        wvWebSite.getSettings().setJavaScriptEnabled(true);
        wvWebSite.setVerticalScrollBarEnabled(true);
        wvWebSite.setHorizontalScrollBarEnabled(true);
        wvWebSite.setOnTouchListener(new WebViewSwipeListener(this) {

            @Override
            void onSwipeRight() {
                super.onSwipeRight();

                if (wvWebSite.canGoBack()) {
                    wvWebSite.goBack();

                    etWebSite.setText(wvWebSite.getUrl());
                }
            }

            @Override
            void onSwipeLeft() {
                super.onSwipeLeft();

                if (wvWebSite.canGoForward()) {
                    wvWebSite.goForward();

                    etWebSite.setText(wvWebSite.getUrl());
                }
            }
        });
    }
}
