package cc.buddies.reactapp.reactnative;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;

import cc.buddies.reactapp.BuildConfig;
import cc.buddies.reactapp.utils.StorageUtils;

public class BundleManager {

    private static final String TAG = "ReactBundleManager";

    // bundle包热更存储目录
    public static final String REACT_NATIVE_PATH_ROOT_NAME = "react-native";

    // bundle包主页名称
    public static final String JS_MAIN_MODULE_NAME = "index";

    // bundle包名称
    public static final String BUNDLE_NAME = "index.android.bundle";

    // bundle压缩包名称
    public static final String BUNDLE_ASSET_NAME = "index.android.zip";

    // assets目录下bundle包版本
    public static final String BUNDLE_VERSION = BuildConfig.BUNDLE_VERSION;

    /**
     * bundle包配置
     */
    public interface BundleProperties {
        // bundle包配置文件
        String BUNDLE_PROPERTIES_NAME = "bundle.properties";

        // bundle包配置版本号
        String BUNDLE_CONFIG_KEY_VERSION = "VERSION";
    }

    /**
     * 获取ReactNative热更文件根路径
     *
     * @param context Context
     * @return File
     */
    @NonNull
    public static File getReactNativeRootPath(@NonNull Context context) {
        File rootFile = new File(StorageUtils.getFilesDir(context), REACT_NATIVE_PATH_ROOT_NAME);
        if (!rootFile.exists()) {
            boolean mkdirs = rootFile.mkdirs();
            Log.d(TAG, "创建" + REACT_NATIVE_PATH_ROOT_NAME + "存储目录" + (mkdirs ? "成功" : "失败"));
        }
        return rootFile;
    }

}
