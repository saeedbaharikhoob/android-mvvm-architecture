package com.saeedbaharikhoob.testproject.viewmodel;


import com.saeedbaharikhoob.testproject.view.interfaces.StateFragmentNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class StateFragmentViewModel extends BaseViewModel<StateFragmentNavigator> {


    public void getData() {

        getCompositeDisposable().add(getWebservice().getStates().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> getNavigator().result(response.getStates()), throwable -> getNavigator().handleError(throwable)));

    }

}
