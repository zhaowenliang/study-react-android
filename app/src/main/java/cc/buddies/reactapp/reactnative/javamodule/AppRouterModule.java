package cc.buddies.reactapp.reactnative.javamodule;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

/**
 * 路由模块提供者
 */
public class AppRouterModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME = "AppRouter";

    public AppRouterModule(@NonNull final ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void navigation(@NonNull String url, @Nullable ReadableMap params) {
        StringBuilder logBuilder = new StringBuilder("路由: " + url);
        if (params != null) {
            logBuilder.append("    ")
                    .append("参数: ")
                    .append(params.toHashMap().toString());
        }
        Log.d(MODULE_NAME, logBuilder.toString());
    }

}
