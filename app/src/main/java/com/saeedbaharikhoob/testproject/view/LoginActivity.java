package com.saeedbaharikhoob.testproject.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityLoginBinding;
import com.saeedbaharikhoob.testproject.model.retromodel.LoginRetro;
import com.saeedbaharikhoob.testproject.utils.Account;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.RxBus;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerLoginComponent;
import com.saeedbaharikhoob.testproject.view.di.module.LoginModule;
import com.saeedbaharikhoob.testproject.view.interfaces.LoginNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.LoginViewModel;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity implements LoginNavigator {

    @Inject
    LoginViewModel loginViewModel;
    @Inject
    ActivityLoginBinding activityLoginBinding;


    private EditText editUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
        loginViewModel.setNavigator(this);
        activityLoginBinding.setItem(loginViewModel);
        editUsername = activityLoginBinding.editUsername;
        editPassword = activityLoginBinding.editPassword;


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unregister(this);
    }


    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(G.context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void login() {
        if (loginViewModel.isUsernameAndPasswordValid(editUsername.getText().toString(), editPassword.getText().toString())) {
            loginViewModel.login(editUsername.getText().toString(), editPassword.getText().toString());
        } else {
            Toast.makeText(this, getString(R.string.invalid_username_password), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void result(LoginRetro loginRetro) {
        if (loginRetro.isStatus())
        {
            Toast.makeText(G.context, loginRetro.getMessage(), Toast.LENGTH_LONG).show();
            Account.getInstant(G.context).storeUserId(loginRetro.getId());
            Account.getInstant(G.context).storeStatusLogin(true);
            RxBus.publish(getString(R.string.RefreshLoginStatus), 0);
            this.finish();
        }
        else
            Toast.makeText(G.context, loginRetro.getMessage(), Toast.LENGTH_LONG).show();
    }


}
