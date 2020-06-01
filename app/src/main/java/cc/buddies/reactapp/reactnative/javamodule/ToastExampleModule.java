package cc.buddies.reactapp.reactnative.javamodule;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Toast工具的JavaModule实现
 * <br/>
 * 参考{@link com.facebook.react.modules.toast.ToastModule}
 */
public class ToastExampleModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME = "ToastExample";

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @NonNull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @NonNull
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);

        return constants;
    }

    @Override
    public boolean hasConstants() {
        return true;
    }

    @ReactMethod
    public void show(String message, int duration) {
        _show(getReactApplicationContext(), message, duration);
    }

    @ReactMethod
    public void showShort(String message) {
        int duration = (int) getConstants().get(DURATION_SHORT_KEY);
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void showLong(String message) {
        int duration = (int) getConstants().get(DURATION_LONG_KEY);
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    private void _show(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

}
