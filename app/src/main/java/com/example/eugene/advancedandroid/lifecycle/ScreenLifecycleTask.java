package com.example.eugene.advancedandroid.lifecycle;

import android.view.View;

import com.example.eugene.advancedandroid.di.ActivityScope;

public abstract class ScreenLifecycleTask {

    /**
     * Callback received when a Screen becomes the visible screen.
     */
    public void onEnterScope(View view) {

    }

    /**
     * Callback received when a Screen is either popped of moved to the back stack.
     */
    public void onExitScope() {

    }

    /**
     * Callback received when a Screen is destroyed and will not be coming back (except as a new instance). Thos
     * should be used to clear any {@link ActivityScope} connections (Disposables, etc).
     */
    public void onDestroy() {

    }
}
