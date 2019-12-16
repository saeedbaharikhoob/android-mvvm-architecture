package com.saeedbaharikhoob.testproject.viewmodel;

import com.saeedbaharikhoob.testproject.model.retromodel.NewsRetro;
import com.saeedbaharikhoob.testproject.utils.Account;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.view.interfaces.NewsNavigator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewsViewModel extends BaseViewModel<NewsNavigator> {

    private NewsRetro newsRetro;

    public void getNews(int newsId) {
        getCompositeDisposable().add(getWebservice().getNewsInfo(newsId, Account.getInstant(G.context).getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                    newsRetro = response;
                    getNavigator().newsResult(response);
                }, throwable -> getNavigator().handleError(throwable)));

    }

    public void getComments(int newsId) {

        getCompositeDisposable().add(getWebservice().getComments(newsId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().commentResult(response), throwable -> getNavigator().handleError(throwable)));
    }

    public void newComment(int newsId, String comment) {
        getCompositeDisposable().add(getWebservice().newComment(newsId, Account.getInstant(G.context).getUserId(), comment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().addCommentResult(response), throwable -> getNavigator().handleError(throwable)));
    }

    public void getLikeStatus(int newsId) {

        getCompositeDisposable().add(getWebservice().likePost(newsId, Account.getInstant(G.context).getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> getNavigator().addLikeResult(response), throwable -> getNavigator().handleError(throwable)));
    }
    public NewsRetro getNewsRetro()
    {
        return newsRetro;
    }
    public void onServerLikeClick() {
        getNavigator().likePost();
    }
    public void onServerAddCommentClick() {
        getNavigator().addComment();
    }
}
