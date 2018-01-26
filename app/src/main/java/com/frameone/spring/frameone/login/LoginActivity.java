package com.frameone.spring.frameone.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.frameone.spring.frameone.R;
import com.frameone.spring.frameone.base.BaseActivity;
import com.frameone.spring.frameone.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;

    @OnClick(R.id.btn_login)
    public void click() {
        loginPresenter.login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public String getPhone() {
        return edtPhone.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return edtPass.getText().toString();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
