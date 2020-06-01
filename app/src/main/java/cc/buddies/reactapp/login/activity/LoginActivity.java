package cc.buddies.reactapp.login.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;

public class LoginActivity extends ReactActivity {

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "LoginComponent";
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getMainComponentName());
    }
}
