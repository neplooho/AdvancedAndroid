package com.example.eugene.advancedandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.example.eugene.advancedandroid.di.Injector;
import com.example.eugene.advancedandroid.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseController extends Controller {

    @Inject
    Set<ScreenLifecycleTask> screenLifecycleTasks;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private boolean injected = false;
    private Unbinder unbinder;

    public BaseController(){
        super();
    }
    public BaseController(Bundle bundle){
        super(bundle);
    }
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

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        disposables.addAll(subscriptions());
        return view;
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            if (changeType.isEnter) {
                task.onEnterScope(getView());
            } else {
                task.onExitScope();
            }
        }
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        disposables.clear();
        if (unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onDestroy();
        }
    }

    protected void onViewBound(View view){

    }

    protected Disposable[] subscriptions(){
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}
