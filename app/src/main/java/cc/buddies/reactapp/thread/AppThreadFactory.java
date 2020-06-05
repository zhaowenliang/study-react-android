package cc.buddies.reactapp.thread;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;

public class AppThreadFactory {

    @NonNull
    public static ExecutorService getExecutor() throws Exception {
        if (AsyncTask.THREAD_POOL_EXECUTOR instanceof ExecutorService) {
            return (ExecutorService) AsyncTask.THREAD_POOL_EXECUTOR;
        } else {
            throw new Exception("AsyncTask.THREAD_POOL_EXECUTOR 未实现 ExecutorService 接口!");
        }
    }

}
