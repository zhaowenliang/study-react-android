package cc.buddies.reactapp.reactnative;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import cc.buddies.reactapp.BuildConfig;
import cc.buddies.reactapp.reactnative.reactpackage.AppReactPackage;

public class AppReactNativeHost extends ReactNativeHost {

    public AppReactNativeHost(final Application application) {
        super(application);
    }

    @Override
    public boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    @Override
    protected ReactInstanceManager createReactInstanceManager() {
        // 添加ReactContext异步创建监听
        // reactInstanceManager.addReactInstanceEventListener(context -> { /* ReactContext初始化 */ });
        return super.createReactInstanceManager();
    }

    /**
     * N端对R端提供包
     */
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.asList(
                new MainReactPackage(),
//                new RNGestureHandlerPackage(),
                new AppReactPackage());
    }

    /**
     * JS启动页
     */
    @Override
    protected String getJSMainModuleName() {
        return BundleManager.JS_MAIN_MODULE_NAME;
    }

    /**
     * asset目录bundle文件名称
     */
    @NonNull
    @Override
    protected String getBundleAssetName() {
        return BundleManager.BUNDLE_NAME;
    }

    /**
     * bundle文件路径
     */
    @Nullable
    @Override
    protected String getJSBundleFile() {
        File rootPath = BundleManager.getReactNativeRootPath(getApplication().getApplicationContext());
        String filename = getBundleAssetName();
        File bundleFile = new File(rootPath, filename);
        return bundleFile.getAbsolutePath();
    }
}
