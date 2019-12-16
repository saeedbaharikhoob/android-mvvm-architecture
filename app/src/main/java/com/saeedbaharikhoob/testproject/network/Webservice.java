package com.saeedbaharikhoob.testproject.network;


import com.saeedbaharikhoob.testproject.model.retromodel.CommentRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.LogicRespanseRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.LoginRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.NewsRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.PostRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.RegisterRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.SearchRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.StateRetro;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Webservice {


  @POST("api/v1/getNews")
  Single<PostRetro> getNews();

  @POST("api/v1/getStates")
  Single<StateRetro> getStates();

  @FormUrlEncoded
  @POST("api/v1/search")
  Single<SearchRetro> search(@Field("stateId") int stateId, @Field("text") String text, @Field("page") int page);
  @FormUrlEncoded
  @POST("api/v1/getPostByTags")
  Single<SearchRetro> getPostByTags( @Field("text") String text, @Field("page") int page);

  @FormUrlEncoded
  @POST("api/v1/getNewsInfo")
  Single<NewsRetro> getNewsInfo(@Field("newsId") int newsId, @Field("userId") int userId);

  @FormUrlEncoded
  @POST("api/v1/getComments")
  Single<CommentRetro> getComments(@Field("newsId") int newsId);

  @FormUrlEncoded
  @POST("api/v1/newComment")
  Single<LogicRespanseRetro> newComment(@Field("newsId") int newsId, @Field("userId") int userId, @Field("comment") String comment);

  @FormUrlEncoded
  @POST("api/v1/register")
  Single<RegisterRetro> register(@Field("nickname") String nickname, @Field("username") String username, @Field("password") String password);

  @FormUrlEncoded
  @POST("api/v1/login")
  Single<LoginRetro> login(@Field("username") String username, @Field("password") String password);

  @FormUrlEncoded
  @POST("api/v1/likePost")
  Single<LogicRespanseRetro> likePost(@Field("newsId") int newsId, @Field("userId") int userId);

}