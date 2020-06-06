package cc.buddies.reactapp.thread;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;

public class AppThreadFactory {

    @NonNull
    public static ExecutorService getExecutor() {
        // 如果"AsyncTask.THREAD_POOL_EXECUTOR"未实现"ExecutorService"接口，则会抛出异常。
        // 开发中发现异常，应当解决此问题。
        // 在android29的SDK中"AsyncTask.THREAD_POOL_EXECUTOR"的实现类为"ThreadPoolExecutor"。
        return (ExecutorService) AsyncTask.THREAD_POOL_EXECUTOR;
    }

}
