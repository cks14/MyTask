package com.mytask.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mytask.R;
import com.mytask.login.LoginActivity;
import com.mytask.util.Configuration;
import com.mytask.util.DeviceData;
import com.mytask.util.DialogLoader;
import com.mytask.util.LocalConstant;
import com.mytask.util.UtilSnackbar;
import com.mytask.webservices.HttpCallback;
import com.mytask.webservices.RestClient;
import com.mytask.webservices.model.Signup;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout rrLogin;
    private TextView loginTv;
    private LinearLayout inputLayout;
    private EditText name;
    private EditText emailId;
    private EditText phoneNumber;
    private EditText pass;
    private EditText confirmPass;
    private LinearLayout llLoginSub;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DialogLoader dialogLoader;
    private ImageView imgShowPassword;
    private TextView tvFirstError;
    private TextView tvEmailError;
    private TextView tvEmailError1;
    private TextView tvPhoneError;
    private TextView tvPhoneError1;
    private TextView tvPassError;
    private TextView tvPassError1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imgShowPassword = (ImageView) findViewById(R.id.img_show_password);

        imgShowPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        pass.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }

                return true;
            }
        });
        initView();
    }

    private void initView() {

        rrLogin = (RelativeLayout) findViewById(R.id.rr_login);
        loginTv = (TextView) findViewById(R.id.login_tv);
        inputLayout = (LinearLayout) findViewById(R.id.input_layout);
        name = (EditText) findViewById(R.id.name);
        emailId = (EditText) findViewById(R.id.email_id);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        pass = (EditText) findViewById(R.id.pass);
        confirmPass = (EditText) findViewById(R.id.confirm_pass);
        llLoginSub = (LinearLayout) findViewById(R.id.ll_login_sub);
        dialogLoader = new DialogLoader(RegisterActivity.this);
        name.addTextChangedListener(new GenericTextWatcher(name));
        phoneNumber.addTextChangedListener(new GenericTextWatcher(phoneNumber));
        emailId.addTextChangedListener(new GenericTextWatcher(emailId));
        pass.addTextChangedListener(new GenericTextWatcher(pass));
        confirmPass.addTextChangedListener(new GenericTextWatcher(confirmPass));



        llLoginSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginValid()) {
                    if (Configuration.isInternetConnection(RegisterActivity.this)) {

                        processsignup();


                    } else {
                        UtilSnackbar.showSnakbarTypeInternet(findViewById(R.id.sign_rr), RegisterActivity.this);

                    }

                }
            }
        });

        tvFirstError = (TextView) findViewById(R.id.tv_first_error);
        tvEmailError = (TextView) findViewById(R.id.tv_email_error);
        tvEmailError1 = (TextView) findViewById(R.id.tv_email_error1);
        tvPhoneError = (TextView) findViewById(R.id.tv_phone_error);
        tvPhoneError1 = (TextView) findViewById(R.id.tv_phone_error1);
        tvPassError = (TextView) findViewById(R.id.tv_pass_error);
        tvPassError1 = (TextView) findViewById(R.id.tv_pass_error1);
    }

    private void processsignup() {
        dialogLoader.showProgressDialog();

        RestClient.getInst().Signup(DeviceData.GetDeviceId(RegisterActivity.this), name.getText().toString(), emailId.getText().toString(),
                phoneNumber.getText().toString(), pass.getText().toString()).enqueue(new HttpCallback<Signup>() {
            @Override
            public void onSuccess(Call<Signup> call, Response<Signup> response) {
                dialogLoader.hideProgressDialog();

                if (response.isSuccessful()) {
                    if (response.body().getMessage().equalsIgnoreCase("account created successfully send OTP your register mobile or email verify account to continue shopping.")) {

                        LocalConstant.getInstance(RegisterActivity.this).setUserID(response.body().getSignup_data().getFld_user_id());
                        LocalConstant.getInstance(RegisterActivity.this).setUserEmail(response.body().getSignup_data().getFld_user_email());
                        LocalConstant.getInstance(RegisterActivity.this).setUserMobile(response.body().getSignup_data().getFld_user_phone());
                        LocalConstant.getInstance(RegisterActivity.this).setUserName(response.body().getSignup_data().getFld_user_name());
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finishAffinity();
                    }

                    else {
                        UtilSnackbar.showSnackbarEnterOtp(findViewById(R.id.sign_rr), RegisterActivity.this);

                    }

                }
                else {

                }

            }

            @Override
            public void onError(Call<Signup> call, Throwable t) {
                dialogLoader.hideProgressDialog();
                UtilSnackbar.showSnakbarTypeError(findViewById(R.id.sign_rr), RegisterActivity.this);

            }
        });


    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {

            tvFirstError.setVisibility(View.GONE);
            tvPhoneError.setVisibility(View.GONE);
            tvEmailError.setVisibility(View.GONE);
            tvPhoneError1.setVisibility(View.GONE);
            tvEmailError1.setVisibility(View.GONE);
            tvPassError.setVisibility(View.GONE);
            tvPassError1.setVisibility(View.GONE);

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Aut

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tvFirstError.setVisibility(View.VISIBLE);
            tvPhoneError.setVisibility(View.VISIBLE);
            tvEmailError.setVisibility(View.VISIBLE);
            tvEmailError1.setVisibility(View.VISIBLE);
            tvPhoneError1.setVisibility(View.VISIBLE);
            tvPassError.setVisibility(View.VISIBLE);
            tvPassError1.setVisibility(View.VISIBLE);

        }

    }

    private boolean isLoginValid() {

        String passw = pass.getText().toString();
        String cpass = confirmPass.getText().toString();
        boolean temp = true;

        if (TextUtils.isEmpty(name.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFirstError.setVisibility(View.VISIBLE);
                    tvFirstError.requestFocus();
                }
            });
            return false;
        }

        else if (TextUtils.isEmpty(emailId.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvEmailError.setVisibility(View.VISIBLE);
                    tvEmailError.requestFocus();
                }
            });
            return false;
        } else if (!emailId.getText().toString().trim().matches(emailPattern)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvEmailError1.setVisibility(View.VISIBLE);
                    tvEmailError1.requestFocus();
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvPhoneError.setVisibility(View.VISIBLE);
                    tvPhoneError.requestFocus();
                }
            });
            return false;
        } else if ((phoneNumber.getText ().toString ().length()<10)) {
            runOnUiThread(new Runnable() {
                @SuppressLint("WrongConstant")
                @Override
                public void run() {
                    tvPhoneError1.setVisibility(View.VISIBLE);
                    tvPhoneError1.requestFocus();
                }
            });
            return false;
        }

        else if (TextUtils.isEmpty(pass.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvPassError.setVisibility(View.VISIBLE);
                    tvPassError.requestFocus();
                }
            });
            return false;
        } else if (TextUtils.isEmpty(confirmPass.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvPassError1.setVisibility(View.VISIBLE);
                    tvPassError1.requestFocus();
                }
            });
            return false;
        } else if (!passw.equals(cpass)) {

            UtilSnackbar.showPasswordMissmatch(findViewById(R.id.sign_rr), RegisterActivity.this);
            temp = false;
        }

//
        return temp;
    }

}