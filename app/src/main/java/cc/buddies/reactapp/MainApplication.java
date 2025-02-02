package cc.buddies.reactapp;

import android.app.Application;
import android.content.Context;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.InvocationTargetException;

import cc.buddies.reactapp.reactnative.AppReactNativeHost;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new AppReactNativeHost(this);

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    }

    /**
     * Loads Flipper in React Native templates. Call this in the onCreate method with something like
     * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
     *
     * @param context              Context
     * @param reactInstanceManager ReactInstanceManager
     */
    private static void initializeFlipper(Context context, ReactInstanceManager reactInstanceManager) {
        if (BuildConfig.DEBUG) {
            try {
                // We use reflection here to pick up the class that initializes Flipper, since Flipper library is not available in release mode
                Class<?> aClass = Class.forName("cc.buddies.reactapp.ReactNativeFlipper");
                aClass
                        .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
                        .invoke(null, context, reactInstanceManager);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
