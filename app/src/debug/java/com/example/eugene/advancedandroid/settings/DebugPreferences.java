package com.example.eugene.advancedandroid.settings;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DebugPreferences {

    private static final String MOCK_RESPONSE_KEY = "mock_response";
    private final SharedPreferences sharedPreferences;

    @Inject
    DebugPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("debug_settings", Context.MODE_PRIVATE);
    }

    public boolean useMockResponsesEnabled() {
        return sharedPreferences.getBoolean(MOCK_RESPONSE_KEY, false);
    }

    public void setUseMockResposes(boolean useMockResposes) {
        sharedPreferences.edit().putBoolean(MOCK_RESPONSE_KEY, useMockResposes).apply();
    }
}
