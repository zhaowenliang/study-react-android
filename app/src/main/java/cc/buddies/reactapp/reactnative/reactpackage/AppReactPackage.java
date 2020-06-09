package cc.buddies.reactapp.reactnative.reactpackage;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.List;

import cc.buddies.reactapp.reactnative.javamodule.AppRouterModule;
import cc.buddies.reactapp.reactnative.javamodule.ToastExampleModule;
import cc.buddies.reactapp.reactnative.viewmanager.ReactImageViewManager;

public class AppReactPackage implements ReactPackage {

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull final ReactApplicationContext reactContext) {
        return Arrays.asList(
                new ToastExampleModule(reactContext),
                new AppRouterModule(reactContext));
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull final ReactApplicationContext reactContext) {
        return Arrays.asList(
//                new SafeAreaProviderManager(reactContext),
                new ReactImageViewManager(reactContext));
    }
}
