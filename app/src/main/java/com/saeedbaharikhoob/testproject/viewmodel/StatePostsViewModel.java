package com.saeedbaharikhoob.testproject.viewmodel;

import com.saeedbaharikhoob.testproject.view.interfaces.StatePostsFragmentNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StatePostsViewModel extends BaseViewModel<StatePostsFragmentNavigator> {


    public void getStatePosts(int stateId, int page) {
        getCompositeDisposable().add(getWebservice().search(stateId,"",page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().result(response.getResult()), throwable -> getNavigator().handleError(throwable)));

    }



}
