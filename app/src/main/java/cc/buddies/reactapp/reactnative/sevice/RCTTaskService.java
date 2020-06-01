package cc.buddies.reactapp.reactnative.sevice;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

public class RCTTaskService extends HeadlessJsTaskService {

    private static final String TASK_KEY = "RCTTaskService";

    private static final long TIMEOUT = 5000;

    private static final boolean ALLOWED_IN_FOREGROUND = false;

    @Nullable
    @Override
    protected HeadlessJsTaskConfig getTaskConfig(final Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            return new HeadlessJsTaskConfig(TASK_KEY, Arguments.fromBundle(extras), TIMEOUT, ALLOWED_IN_FOREGROUND);
        }
        return null;
    }
}
