package com.saeedbaharikhoob.testproject.view.interfaces;


import com.saeedbaharikhoob.testproject.model.Post;

import java.util.ArrayList;

public interface SearchFragmentNavigator {

    void handleError(Throwable throwable);
    void result(ArrayList<Post> posts);
}
