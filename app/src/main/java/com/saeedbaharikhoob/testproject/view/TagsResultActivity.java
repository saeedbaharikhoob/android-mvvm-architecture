package com.saeedbaharikhoob.testproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.saeedbaharikhoob.testproject.databinding.ActivityTagsResultBinding;
import com.saeedbaharikhoob.testproject.model.Post;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.view.adapter.SearchAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerTagsResultComponent;
import com.saeedbaharikhoob.testproject.view.di.module.TagsResultModule;
import com.saeedbaharikhoob.testproject.view.interfaces.TagsResultNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.TagsResultViewModel;
import java.util.ArrayList;
import javax.inject.Inject;


public class TagsResultActivity extends AppCompatActivity implements TagsResultNavigator {

    @Inject
    TagsResultViewModel tagsResultViewModel;
    @Inject
    ActivityTagsResultBinding activityTagsResultBinding;
    @Inject
    GridLayoutManager layoutManager;
    @Inject
    SearchAdapter searchAdapter;


    private RecyclerView listTagsResult;
    private String txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTagsResultComponent.builder().tagsResultModule(new TagsResultModule(this)).build().inject(this);
        txt = getIntent().getStringExtra(IntentKeys.TEXT);

        listTagsResult = activityTagsResultBinding.listTagsResult;
        searchAdapter.setOnClickListener(onItemClick);
        listTagsResult.setLayoutManager(layoutManager);
        listTagsResult.setAdapter(searchAdapter);
        tagsResultViewModel.getHashtagPosts(txt, 0);
        tagsResultViewModel.setNavigator(this);

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
