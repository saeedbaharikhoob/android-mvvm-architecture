package com.saeedbaharikhoob.testproject.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.FragmentSearchBinding;
import com.saeedbaharikhoob.testproject.databinding.FragmentStateBinding;
import com.saeedbaharikhoob.testproject.model.Post;
import com.saeedbaharikhoob.testproject.model.retromodel.SearchRetro;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.view.NewsActivity;
import com.saeedbaharikhoob.testproject.view.adapter.SearchAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerSearchFragmentComponent;
import com.saeedbaharikhoob.testproject.view.di.module.SearchFragmentModule;
import com.saeedbaharikhoob.testproject.view.interfaces.SearchFragmentNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.SearchFragmentViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class SearchFragment extends Fragment implements SearchFragmentNavigator {


    @Inject
    GridLayoutManager layoutManager;
    @Inject
    SearchAdapter searchAdapter;
    @Inject
    SearchFragmentViewModel searchFragmentViewModel;
    @Inject
    FragmentSearchBinding fragmentSearchBinding;


    private EditText editSearch;
    private Activity mActivity;
    private RecyclerView listSearch;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();

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
        DaggerSearchFragmentComponent.builder().searchFragmentModule(new SearchFragmentModule(mActivity, inflater, container)).build().inject(this);

        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        editSearch = fragmentSearchBinding.editSearch;
        listSearch = fragmentSearchBinding.listSearch;
        searchAdapter.setOnClickListener(onItemClick);
        listSearch.setLayoutManager(layoutManager);
        listSearch.setAdapter(searchAdapter);
        searchFragmentViewModel.setNavigator(this);

        RxTextView.textChanges(editSearch).subscribe(text -> {
            if (!editSearch.getText().toString().equals(""))
                searchFragmentViewModel.getData(editSearch.getText().toString(), 0);
            else
                searchAdapter.clearItems();
        });


    }


    private SearchAdapter.OnItemClick onItemClick = (post, view) -> {
        Intent intent = G.tools.getIntent();
        intent.putExtra(IntentKeys.NEWS_ID, post.getId());
        intent.setClass(mActivity, NewsActivity.class);
        mActivity.startActivity(intent);
    };

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(G.context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void result(ArrayList<Post> posts) {
        searchAdapter.setItems(posts);
    }
}
