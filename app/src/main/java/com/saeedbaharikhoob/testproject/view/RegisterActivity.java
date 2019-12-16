package com.saeedbaharikhoob.testproject.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityNewsBinding;
import com.saeedbaharikhoob.testproject.databinding.ActivityRegisterBinding;
import com.saeedbaharikhoob.testproject.databinding.ActivityRegisterBindingImpl;
import com.saeedbaharikhoob.testproject.model.retromodel.RegisterRetro;
import com.saeedbaharikhoob.testproject.utils.Account;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.RxBus;
import com.saeedbaharikhoob.testproject.utils.SliderViews;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerRegisterComponent;
import com.saeedbaharikhoob.testproject.view.di.module.NewsModule;
import com.saeedbaharikhoob.testproject.view.di.module.RegisterModule;
import com.saeedbaharikhoob.testproject.view.interfaces.RegisterNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.NewsViewModel;
import com.saeedbaharikhoob.testproject.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class RegisterActivity extends AppCompatActivity implements RegisterNavigator {

    @Inject
    RegisterViewModel registerViewModel;
    @Inject
    ActivityRegisterBinding activityNewsBinding;


    private EditText editNickName;
    private EditText editUsername;
    private EditText editPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRegisterComponent.builder().registerModule(new RegisterModule(this)).build().inject(this);

        editNickName = activityNewsBinding.editNickName;
        editUsername = activityNewsBinding.editUsername;
        editPassword = activityNewsBinding.editPassword;
        registerViewModel.setNavigator(this);
        activityNewsBinding.setItem(registerViewModel);



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unregister(this);
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void register() {
        if (registerViewModel.isUsernameAndPasswordAndNickNameValid(editUsername.getText().toString(), editPassword.getText().toString(),editNickName.getText().toString())) {
            registerViewModel.register(editNickName.getText().toString(),editUsername.getText().toString(), editPassword.getText().toString());
        } else {
            Toast.makeText(this, getString(R.string.invalid_username_password_nickname), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void result(RegisterRetro registerRetro) {
        if (registerRetro.isStatus())
            autoLogin(registerRetro);
        else
            Toast.makeText(G.context, registerRetro.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void autoLogin(RegisterRetro registerRetro) {
        Toast.makeText(G.context, registerRetro.getMessage(), Toast.LENGTH_LONG).show();
        Account.getInstant(G.context).storeUserId(registerRetro.getId());
        Account.getInstant(G.context).storeStatusLogin(true);
        RxBus.publish("RefreshLoginStatus",0);
        this.finish();
    }
}
