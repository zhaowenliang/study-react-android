package cc.buddies.reactapp.utils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CompressUtils {

    /**
     * 解压缩
     *
     * @param srcFile 压缩文件
     * @param outDir  输出路径
     * @throws IOException IO操作异常
     */
    public static void decompress(@NonNull File srcFile, @NonNull File outDir) throws IOException {
        try (ZipFile zipFile = new ZipFile(srcFile)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File outFile = new File(outDir, entry.getName());

                // 防止解压文件带有"../"而将文件解压到对应目录以外，造成安全漏洞。
                if (entry.getName() != null && entry.getName().contains("../")) {
                    continue;
                }

                if (entry.isDirectory()) {
                    //noinspection ResultOfMethodCallIgnored
                    outFile.mkdirs();
                    continue;
                }

                if (!outFile.getParentFile().exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    outFile.getParentFile().mkdirs();
                }

                if (!outFile.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    outFile.createNewFile();
                }

                try (InputStream inputStream = zipFile.getInputStream(entry); OutputStream outputStream = new FileOutputStream(outFile)) {
                    int temp;
                    byte[] buf = new byte[1024];
                    while ((temp = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, temp);
                    }
                }
            }
        }
    }

}
