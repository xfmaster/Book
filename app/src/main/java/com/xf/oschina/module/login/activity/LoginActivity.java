package com.xf.oschina.module.login.activity;

import android.arch.lifecycle.LiveData;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseActivity;
import com.xf.oschina.databinding.ActivityLoginBinding;
import com.xf.oschina.module.login.domain.Token;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LiveData<Token> value = entityManager.searchById(Token.class, "8447ff97-9b8c-4224-9cec-63b97d34ba65");
        Token token = value.getValue();
        if (value != null)
            binding.setToken(token);

    }

    @Override
    public void retry() {

    }
}
