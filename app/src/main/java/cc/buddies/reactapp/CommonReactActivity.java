package cc.buddies.reactapp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;

public class CommonReactActivity extends ReactActivity {

    private String mComponentName;

    public CommonReactActivity() {

    }

    public CommonReactActivity(String componentName) {
        this.mComponentName = componentName;
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        return mComponentName;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(mComponentName);
    }
}
