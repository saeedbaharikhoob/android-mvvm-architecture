package com.saeedbaharikhoob.testproject.view.interfaces;


import com.saeedbaharikhoob.testproject.model.retromodel.CommentRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.LogicRespanseRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.NewsRetro;

public interface NewsNavigator {

    void likePost();
    void addComment();
    void handleError(Throwable throwable);
    void newsResult(NewsRetro newsRetro);
    void commentResult(CommentRetro commentRetro);
    void addCommentResult(LogicRespanseRetro logicRespanseRetro);
    void addLikeResult(LogicRespanseRetro logicRespanseRetro);

}
