package com.saeedbaharikhoob.testproject.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.databinding.ObservableBoolean;

import com.saeedbaharikhoob.testproject.network.Webservice;
import com.saeedbaharikhoob.testproject.utils.G;

import io.reactivex.disposables.CompositeDisposable;
import java.lang.ref.WeakReference;

/**
 * Created by amitshekhar on 07/07/17.
 */

public abstract class BaseViewModel<N> extends ViewModel {


    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator.get();
    }
    public Webservice getWebservice() {
        return  G.tools.getRetrofit().create(Webservice.class);
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }


}