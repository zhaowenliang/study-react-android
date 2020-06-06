package cc.buddies.reactapp.task;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

import cc.buddies.reactapp.reactnative.BundleManager;
import cc.buddies.reactapp.utils.CompressUtils;
import cc.buddies.reactapp.utils.PropertiesUtils;

/**
 * 初始化assets下的ReactBundle包任务
 */
public class AssetsReactBundleCallable implements Callable<Boolean> {

    private static final String TAG = "初始化bundle包任务";

    private WeakReference<Context> mContextWeakReference;

    public AssetsReactBundleCallable(Context context) {
        this.mContextWeakReference = new WeakReference<>(context);
    }

    @Override
    public Boolean call() throws Exception {
        String assetsBundleName = BundleManager.BUNDLE_ASSET_NAME;
        String propertiesName = BundleManager.BundleProperties.BUNDLE_PROPERTIES_NAME;

        Context context;
        if (mContextWeakReference != null && mContextWeakReference.get() != null) {
            context = mContextWeakReference.get();
        } else {
            throw new Exception("context is null!");
        }

        try {
            File outBundlePath = BundleManager.getReactNativeRootPath(context.getApplicationContext());
            File outBundleFile = new File(outBundlePath, assetsBundleName);

            // 加载bundle包配置
            File bundlePropertiesFile = new File(outBundlePath, propertiesName);
            Properties bundleProperties = PropertiesUtils.load(bundlePropertiesFile);

            // 如果没有bundle配置，清理当前目录，重新构造配置及bundle包
            if (bundleProperties == null) {
                FileUtils.deleteDirectory(outBundlePath);

                InputStream inputStream = context.getApplicationContext().getAssets().open(assetsBundleName);
                FileUtils.copyInputStreamToFile(inputStream, outBundleFile);
                CompressUtils.decompress(outBundleFile, outBundlePath);

                // 创建bundle配置
                Map<String, Object> bundleParams = new HashMap<>();
                bundleParams.put(BundleManager.BundleProperties.BUNDLE_CONFIG_KEY_VERSION, BundleManager.BUNDLE_VERSION);
                PropertiesUtils.write(bundlePropertiesFile, bundleParams, null);

                Log.i(TAG, "创建bundle包配置成功！");
            } else {
                Log.i(TAG, "已存在bundle包配置！");
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "解压assets资源" + assetsBundleName + "失败！");
        }

        return false;
    }
}
