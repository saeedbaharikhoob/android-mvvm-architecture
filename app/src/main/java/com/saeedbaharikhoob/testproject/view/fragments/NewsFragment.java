package com.saeedbaharikhoob.testproject.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.FragmentNewsBinding;
import com.saeedbaharikhoob.testproject.model.Post;
import com.saeedbaharikhoob.testproject.model.retromodel.PostRetro;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.view.NewsActivity;
import com.saeedbaharikhoob.testproject.view.adapter.NewsAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerNewsFragmentComponent;
import com.saeedbaharikhoob.testproject.view.di.module.NewsFragmentModule;
import com.saeedbaharikhoob.testproject.view.interfaces.NewsFragmentNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.NewsFragmentViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class NewsFragment extends Fragment implements NewsFragmentNavigator {


    @Inject
    GridLayoutManager layoutManager;
    @Inject
    NewsAdapter newsAdapter;
    @Inject
    NewsFragmentViewModel newsFragmentViewModel;
    @Inject
    FragmentNewsBinding fragmentNewsBinding;

    private Activity mActivity;
    private RecyclerView listPost;


    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();

        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DaggerNewsFragmentComponent.builder().newsFragmentModule(new NewsFragmentModule(mActivity,inflater,container)).build().inject(this);

        return fragmentNewsBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        listPost = fragmentNewsBinding.listPost;
        newsAdapter.setOnClickListener(onItemClick);
        listPost.setLayoutManager(layoutManager);
        listPost.setAdapter(newsAdapter);
        newsFragmentViewModel.setNavigator(this);
        newsFragmentViewModel.getData();


    }


    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(G.context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void result(ArrayList<Post> posts) {
        newsAdapter.setItems(posts);

    }
    private NewsAdapter.OnItemClick onItemClick = (post, view) -> {
        Intent intent = G.tools.getIntent();
        intent.putExtra(IntentKeys.NEWS_ID, post.getId());
        intent.setClass(mActivity, NewsActivity.class);
        mActivity.startActivity(intent);


    };

}
