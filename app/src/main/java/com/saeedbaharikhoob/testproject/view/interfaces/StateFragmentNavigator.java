package com.saeedbaharikhoob.testproject.view.interfaces;


import com.saeedbaharikhoob.testproject.model.State;

import java.util.ArrayList;

public interface StateFragmentNavigator {

    void handleError(Throwable throwable);
    void result(ArrayList<State> states);
}
