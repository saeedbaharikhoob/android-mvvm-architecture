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
import com.saeedbaharikhoob.testproject.databinding.FragmentStateBinding;
import com.saeedbaharikhoob.testproject.model.State;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.view.StatePostsResultActivity;
import com.saeedbaharikhoob.testproject.view.adapter.StateAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerStateFragmentComponent;
import com.saeedbaharikhoob.testproject.view.di.module.StateFragmentModule;
import com.saeedbaharikhoob.testproject.view.interfaces.StateFragmentNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.StateFragmentViewModel;
import java.util.ArrayList;
import javax.inject.Inject;



public class StateFragment extends Fragment implements StateFragmentNavigator {


    private Activity mActivity;
    private RecyclerView listState;
    @Inject
    GridLayoutManager layoutManager;
    @Inject
    StateAdapter stateAdapter;
    @Inject
    StateFragmentViewModel stateFragmentViewModel;
    @Inject
    FragmentStateBinding fragmentStateBinding;

    public static StateFragment newInstance() {
        StateFragment fragment = new StateFragment();


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
        DaggerStateFragmentComponent.builder().stateFragmentModule(new StateFragmentModule(mActivity, inflater, container)).build().inject(this);

        return fragmentStateBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        listState = fragmentStateBinding.listState;
        stateAdapter.setOnClickListener(onItemClick);
        listState.setLayoutManager(layoutManager);
        listState.setAdapter(stateAdapter);
        stateFragmentViewModel.setNavigator(this);
        stateFragmentViewModel.getData();


    }


    private StateAdapter.OnItemClick onItemClick = (state, view) -> {
        Intent intent = G.tools.getIntent();
        intent.putExtra(IntentKeys.STATE_ID, state.getId());
        intent.setClass(mActivity, StatePostsResultActivity.class);
        mActivity.startActivity(intent);
    };


    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(G.context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void result(ArrayList<State> states) {
        stateAdapter.setItems(states);
    }
}
