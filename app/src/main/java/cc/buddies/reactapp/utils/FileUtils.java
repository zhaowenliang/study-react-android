package cc.buddies.reactapp.utils;

import org.apache.commons.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import cc.buddies.reactapp.callback.IOStreamCallback;

/**
 * 封装文件操作工具
 * <pre>
 *     基础操作使用apache commons-io库.
 *     {@link org.apache.commons.io.FileUtils}
 *     commons-io库下的目录及文件的复制/移动操作可以自动创建目录及文件。
 *
 *     文件名相关操作
 *     {@link org.apache.commons.io.FilenameUtils}
 * </pre>
 */
public class FileUtils {

    /**
     * 保存输入流到文件中
     *
     * @param inputStream 输入流
     * @param totalLength 总大小
     * @param outFile     输出文件
     * @param callback    进度回调
     */
    public static void writeStreamToFile(final InputStream inputStream, final long totalLength, final File outFile, final IOStreamCallback callback) {
        try (InputStream bufferedInputStream = IOUtils.toBufferedInputStream(inputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFile))) {
            int currentLength = 0;

            int length;
            byte[] bytes = new byte[8192];
            while ((length = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, length);
                currentLength += length;

                if (callback != null)
                    callback.onProgress(currentLength, totalLength);
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.onException(e);
            }
        }

        if (callback != null) {
            callback.onFinish(outFile);
        }
    }

}
