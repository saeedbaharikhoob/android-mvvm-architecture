package com.saeedbaharikhoob.testproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityStatePostsBinding;
import com.saeedbaharikhoob.testproject.model.Post;
import com.saeedbaharikhoob.testproject.model.retromodel.SearchRetro;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.view.adapter.SearchAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerStatePostsComponent;
import com.saeedbaharikhoob.testproject.view.di.module.StatePostsModule;
import com.saeedbaharikhoob.testproject.view.interfaces.StatePostsFragmentNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.StatePostsViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


public class StatePostsResultActivity extends AppCompatActivity implements StatePostsFragmentNavigator {

    @Inject
    StatePostsViewModel statePostsViewModel;
    @Inject
    ActivityStatePostsBinding activityStatePostsBinding;
    @Inject
    GridLayoutManager layoutManager;
    @Inject
    SearchAdapter searchAdapter;


    private RecyclerView listStatePosts;
    private int stateId;
    private String txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerStatePostsComponent.builder().statePostsModule(new StatePostsModule(this)).build().inject(this);
        stateId = getIntent().getIntExtra(IntentKeys.STATE_ID, 0);
        listStatePosts = activityStatePostsBinding.listStatePosts;
        searchAdapter.setOnClickListener(onItemClick);
        listStatePosts.setLayoutManager(layoutManager);
        listStatePosts.setAdapter(searchAdapter);
        statePostsViewModel.setNavigator(this);
        if (stateId > 0) {
            statePostsViewModel.getStatePosts(stateId, 0);

        }

    }


    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(G.context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void result(ArrayList<Post> posts) {
        searchAdapter.setItems(posts);
    }

    private SearchAdapter.OnItemClick onItemClick = (post, view) -> {
        Intent intent = G.tools.getIntent();
        intent.putExtra(IntentKeys.NEWS_ID, post.getId());
        intent.setClass(this, NewsActivity.class);
        this.startActivity(intent);
    };

}
