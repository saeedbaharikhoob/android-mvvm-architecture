package com.saeedbaharikhoob.testproject.viewmodel;

import com.saeedbaharikhoob.testproject.view.interfaces.NewsFragmentNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class NewsFragmentViewModel extends BaseViewModel<NewsFragmentNavigator> {

    public void getData() {

        getCompositeDisposable().add(getWebservice().getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().result(response.getNewPost()), throwable -> getNavigator().handleError(throwable)));



    }


}
