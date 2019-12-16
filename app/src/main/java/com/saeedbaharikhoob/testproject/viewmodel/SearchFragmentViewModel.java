package com.saeedbaharikhoob.testproject.viewmodel;

import com.saeedbaharikhoob.testproject.view.interfaces.SearchFragmentNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SearchFragmentViewModel extends BaseViewModel<SearchFragmentNavigator> {



    public void getData(String text,int page)
    {

        getCompositeDisposable().add(getWebservice().search(0,text,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().result(response.getResult()), throwable -> getNavigator().handleError(throwable)));

    }

}
