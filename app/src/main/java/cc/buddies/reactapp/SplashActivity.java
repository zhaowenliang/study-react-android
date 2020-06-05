package cc.buddies.reactapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import cc.buddies.reactapp.task.InitReactBundleCallable;
import cc.buddies.reactapp.thread.AppThreadFactory;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    private static final long SPLASH_TIME = 1500;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initReactBundle();
    }

    private void initReactBundle() {
        long beginTimeMillis = System.currentTimeMillis();

        try {
            ExecutorService executor = AppThreadFactory.getExecutor();
            Future<Boolean> submit = executor.submit(new InitReactBundleCallable(getApplicationContext()));

            boolean result = submit.get();
            Log.i(TAG, "初始化assets-bundle结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long subTime = System.currentTimeMillis() - beginTimeMillis;
        long delayTime = subTime < 0 ? 0 : SPLASH_TIME - subTime;
        new Handler().postDelayed(this::next, delayTime);
    }

    private void next() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
