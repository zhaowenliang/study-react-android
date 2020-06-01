package cc.buddies.reactapp.reactnative;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public interface AppReactUtils {

    /**
     * 获取ReactApplication全局ReactNativeHost
     *
     * @param context Context
     * @return ReactNativeHost
     */
    @NonNull
    static ReactNativeHost getReactNativeHost(@NonNull Context context) {
        return ((ReactApplication) context.getApplicationContext()).getReactNativeHost();
    }

    /**
     * 获取ReactApplication全局ReactInstanceManager
     *
     * @return ReactInstanceManager
     */
    @NonNull
    static ReactInstanceManager getReactInstanceManager(Context context) {
        ReactNativeHost reactNativeHost = getReactNativeHost(context);
        return reactNativeHost.getReactInstanceManager();
    }

    /**
     * 获取ReactContext
     *
     * @param context Context
     * @return ReactContext
     */
    static ReactContext getReactContext(Context context) {
        ReactInstanceManager reactInstanceManager = getReactInstanceManager(context);
        return reactInstanceManager.getCurrentReactContext();
    }

    /**
     * 创建一个ReactView
     *
     * @return ReactRootView
     */
    @NonNull
    static ReactRootView createReactView(@NonNull Context context) {
        return new ReactRootView(context);
    }

    /**
     * 初始化一个ReactView
     *
     * @param context              Context
     * @param reactInstanceManager ReactInstanceManager
     * @param moduleName           Module注册名称
     * @return ReactRootView
     */
    @NonNull
    static ReactRootView initReactView(@NonNull Context context, ReactInstanceManager reactInstanceManager, String moduleName) {
        ReactRootView reactView = createReactView(context);
        reactView.startReactApplication(reactInstanceManager, moduleName);
        return reactView;
    }

    /**
     * 初始化一个ReactView
     *
     * @param context              Context
     * @param reactInstanceManager ReactInstanceManager
     * @param moduleName           Module注册名称
     * @param bundle               传递到组件的Props属性
     * @return ReactRootView
     */
    @NonNull
    static ReactRootView initReactView(@NonNull Context context, ReactInstanceManager reactInstanceManager, String moduleName, Bundle bundle) {
        ReactRootView reactView = createReactView(context);
        // reactView.setAppProperties();
        reactView.startReactApplication(reactInstanceManager, moduleName, bundle);
        return reactView;
    }

    /**
     * 发送设备事件到JavaScript
     *
     * @param reactContext ReactContext
     * @param eventName    事件名称
     * @param params       事件参数
     */
    static void sendDeviceEvent(ReactContext reactContext, String eventName, @Nullable ReadableMap params) {
        // 获取ReactNative系统注册DeviceEventManagerModule模块，发送事件到JS。
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    /**
     * 发送组件事件到JavaScript
     * <p>RCTEventEmitter继承自JavaScriptModule，可以实现 Native 接口到 Javascript 中同名模块的映射。</p>
     *
     * @param reactContext ReactContext
     * @param targetTag    组件ID
     * @param eventName    事件名称
     * @param event        事件参数
     */
    static void sendRCTEvent(ReactContext reactContext, int targetTag, String eventName, WritableMap event) {
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(targetTag, eventName, event);
    }

    /**
     * 发送自定义组件事件到JavaScript
     *
     * @param reactContext ReactContext
     * @param event        自定义事件
     */
    static void sendUIManagerEvent(ReactContext reactContext, Event event) {
        reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(event);
    }

}
