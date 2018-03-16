package com.ytanglib.mta;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

public class RNMtaModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private static final String Tag = "MTA";
    private Boolean isRunSucc = null;

    public RNMtaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.isRunSucc = false;
    }

    @Override
    public String getName() {
        return "RNMta";
    }

    @ReactMethod
    public void startWithAppkey(String appKey, String isDebug, String channel, Promise promise) {
        if (!appKey.equals("")) {
            StatConfig.setDebugEnable(isDebug.equals("true"));
            StatConfig.setAppKey(this.reactContext, appKey);
            StatConfig.setInstallChannel(channel);
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
        } else {
            Log.d(RNMtaModule.Tag, "There is no appKey for MTA.");
            this.isRunSucc = false;
        }
        promise.resolve(getOperateResult(this.isRunSucc));
    }

    @ReactMethod
    public void checkInitialResult(Promise promise) {
        promise.resolve(getOperateResult(this.isRunSucc));
    }

    private String getOperateResult(boolean isOperateSucc) {
        return isOperateSucc ? "true" : "false";
    }
}
