package com.example.eugene.advancedandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.example.eugene.advancedandroid.di.Injector;

public abstract class BaseController extends Controller {

    private boolean injected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        /*Controller instances are retained across config changes.
        * As this method can be called more than once we are making sure that we don't
        * waste any time injecting more than once, though technically it wouldn't
        * change functionality. */
        if(!injected){
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }
}
