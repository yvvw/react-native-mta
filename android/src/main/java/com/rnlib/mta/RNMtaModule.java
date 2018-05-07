package com.rnlib.mta;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

import org.json.JSONObject;

import java.util.Properties;

public class RNMtaModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    private Boolean isInitSuccess;
    private static final String Tag = "MTA";

    public RNMtaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNMta";
    }

    @ReactMethod
    public void startWithAppkey(String appKey, String channel, Boolean isDebug, Promise promise) {
        if (!appKey.equals("")) {
            StatConfig.setAppKey(this.reactContext, appKey);
            StatConfig.setInstallChannel(channel);
            StatConfig.setDebugEnable(isDebug);
            try {
                this.isInitSuccess = StatService.startStatService(
                        this.reactContext,
                        null,
                        com.tencent.stat.common.StatConstants.VERSION);

                if (this.isInitSuccess) {
                    Log.d(RNMtaModule.Tag, "MTA init success. appKey: " + appKey);
                } else {
                    Log.w(RNMtaModule.Tag, "MTA init failed.");
                }
            } catch (MtaSDkException e) {
                this.isInitSuccess = false;
                Log.e(RNMtaModule.Tag, "MTA init error.");
            }
        } else {
            Log.d(RNMtaModule.Tag, "There is no appKey for MTA.");
            this.isInitSuccess = false;
        }
        promise.resolve(this.isInitSuccess);
    }

    @ReactMethod
    public void checkInitialResult(Promise promise) {
        promise.resolve(this.isInitSuccess);
    }

    @ReactMethod
    public void trackPageBegin(String page, String appKey, Promise promise) {
        StatService.trackBeginPage(this.reactContext, page);

        promise.resolve(true);
    }

    @ReactMethod
    public void trackPageEnd(String page, String appKey, Boolean isRealTime, Promise promise) {
        StatService.trackEndPage(this.reactContext, page);

        promise.resolve(true);
    }

    @ReactMethod
    public void trackCustomEvent(
            String eventId,
            ReadableMap customerParams,
            String appKey,
            Boolean isRealTime,
            Promise promise) {
        StatService.trackCustomKVEvent(this.reactContext, eventId, rnMapToProperties(customerParams));

        promise.resolve(1);
    }

    @ReactMethod
    public void trackCustomEventBegin(
            String eventId,
            ReadableMap customerParams,
            String appKey,
            Boolean isRealTime,
            Promise promise) {
        int result = StatService.trackCustomBeginKVEvent(
                this.reactContext,
                eventId,
                rnMapToProperties(customerParams));

        promise.resolve(result);
    }

    @ReactMethod
    public void trackCustomEventEnd(
            String eventId,
            ReadableMap customerParams,
            String appKey,
            Boolean isRealTime,
            Promise promise) {
        int result = StatService.trackCustomEndKVEvent(
                this.reactContext,
                eventId,
                rnMapToProperties(customerParams));

        promise.resolve(result);
    }

    @ReactMethod
    public void trackCustomEventDuration(
            String eventId,
            int duration,
            ReadableMap customerParams,
            String appKey,
            Boolean isRealTime,
            Promise promise) {
        int result = StatService.trackCustomKVTimeIntervalEvent(
                this.reactContext,
                duration,
                eventId,
                rnMapToProperties(customerParams));

        promise.resolve(result);
    }

    @ReactMethod
    public void setUserProperty(ReadableMap customerParams, Promise promise) {
        JSONObject customerProperty = new JSONObject(customerParams.toHashMap());
        StatService.reportCustomProperty(this.reactContext, customerProperty);

        promise.resolve(true);
    }

    private static Properties rnMapToProperties(ReadableMap map) {
        Properties properties = new Properties();
        properties.putAll(map.toHashMap());
        return properties;
    }
}
