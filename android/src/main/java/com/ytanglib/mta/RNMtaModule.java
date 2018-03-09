package com.ytanglib.mta;

import android.util.Log;

import java.util.Map;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;

import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;
import com.tencent.stat.MtaSDkException;

public class RNMtaModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private static final String Tag = "MTA";
    private Boolean isRunSucc = null;

    public RNMtaModule(ReactApplicationContext reactContext, Map config) {
        super(reactContext);
        this.reactContext = reactContext;

        String appKey = (String) config.get("appKey");
        if (!appKey.equals("")) {
            StatConfig.setDebugEnable((Boolean) config.get("isDebug"));
            StatConfig.setAppKey(this.reactContext, (String) config.get("appKey"));
            StatConfig.setInstallChannel((String) config.get("channel"));
            this.init();
        } else {
            Log.d(RNMtaModule.Tag, "There is no appKey for MTA.");
            this.isRunSucc = false;
        }
    }

    private void init() {
        try {
            boolean mtaInitRes = StatService.startStatService(this.reactContext, null, com.tencent.stat.common.StatConstants.VERSION);
            if (mtaInitRes) {
                this.isRunSucc = true;
                Log.d(RNMtaModule.Tag, "MTA init success. appKey: " + StatConfig.getAppKey(this.reactContext));
            } else {
                this.isRunSucc = false;
                Log.w(RNMtaModule.Tag, "MTA init failed.");
            }
        } catch (MtaSDkException e) {
            this.isRunSucc = false;
            Log.e(RNMtaModule.Tag, "MTA init error.");
        }
    }

    @Override
    public String getName() {
        return "RNMta";
    }

    @ReactMethod
    public void checkInitialResult(Promise promise) {
        promise.resolve(getOperateResult(this.isRunSucc));
    }

    private String getOperateResult(boolean isOperateSucc) {
        return isOperateSucc ? "true" : "false";
    }
}