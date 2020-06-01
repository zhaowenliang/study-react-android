package cc.buddies.reactapp.reactnative;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;

public interface FileConstants {

    String TAG = "React-Native-File";

    /**
     * 获取ReactNative热更文件根路径
     *
     * @param context Context
     * @return File
     */
    @NonNull
    static File getReactNativeRootPath(@NonNull Context context) {
        File rootFile = new File(context.getFilesDir(), "react-native");
        if (!rootFile.exists()) {
            boolean mkdirs = rootFile.mkdirs();
            Log.d(TAG, "创建react-native存储目录" + (mkdirs ? "成功" : "失败"));
        }
        return new File(context.getFilesDir(), "react-native");
    }

}
