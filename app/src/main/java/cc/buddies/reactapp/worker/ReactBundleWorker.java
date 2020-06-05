package cc.buddies.reactapp.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cc.buddies.reactapp.reactnative.BundleManager;
import cc.buddies.reactapp.utils.CompressUtils;
import cc.buddies.reactapp.utils.PropertiesUtils;

/**
 * React Bundle包初始化Worker
 */
public class ReactBundleWorker extends Worker {

    private static final String TAG = "ReactBundleWorker";

    public ReactBundleWorker(@NonNull final Context context, @NonNull final WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String assetsBundleName = BundleManager.BUNDLE_ASSET_NAME;
        String propertiesName = BundleManager.BundleProperties.BUNDLE_PROPERTIES_NAME;

        try {
            File outBundlePath = BundleManager.getReactNativeRootPath(getApplicationContext());
            File outBundleFile = new File(outBundlePath, assetsBundleName);

            // 加载bundle包配置
            File bundlePropertiesFile = new File(outBundlePath, propertiesName);
            Properties bundleProperties = PropertiesUtils.load(bundlePropertiesFile);

            // 如果没有bundle配置，清理当前目录，重新构造配置及bundle包
            if (bundleProperties == null) {
                FileUtils.deleteDirectory(outBundlePath);

                InputStream inputStream = getApplicationContext().getAssets().open(assetsBundleName);
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

            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "解压assets资源" + assetsBundleName + "失败！");
        }

        return Result.failure();
    }

    // 解压bundle包
//            CompressorInputStream in = new CompressorStreamFactory().createCompressorInputStream(inputStream);
//            IOUtils.copy(in, outputStream);
//            in.close();

    // String bundleVersion = bundleProperties != null ? bundleProperties.getProperty(BUNDLE_CONFIG_KEY_VERSION) : null;
}
