package com.saeedbaharikhoob.testproject.viewmodel;

import com.saeedbaharikhoob.testproject.view.interfaces.TagsResultNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class TagsResultViewModel extends BaseViewModel<TagsResultNavigator> {


    public void getHashtagPosts(String txt, int page) {
        getCompositeDisposable().add(getWebservice().getPostByTags(txt,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().result(response.getResult()), throwable -> getNavigator().handleError(throwable)));
    }

}
