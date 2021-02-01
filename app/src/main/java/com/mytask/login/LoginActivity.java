package com.mytask.login;

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

import androidx.appcompat.app.AppCompatActivity;

import com.mytask.product.ListItemActivity;
import com.mytask.R;
import com.mytask.SwapActivity;
import com.mytask.register.RegisterActivity;
import com.mytask.util.AsteriskPasswordTransformationMethod;
import com.mytask.util.Configuration;
import com.mytask.util.DialogLoader;
import com.mytask.util.LocalConstant;
import com.mytask.util.UtilSnackbar;
import com.mytask.webservices.HttpCallback;
import com.mytask.webservices.RestClient;
import com.mytask.webservices.model.Login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout rrLog;
    private RelativeLayout rrLogin;
    private TextView loginTv;
    private LinearLayout inputLayout;
    private EditText emailLogin;
    private EditText emailPassword;
    private ImageView imgShowPassword;
    private LinearLayout llLoginSub;
    private LinearLayout llSignSub;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DialogLoader dialogLoader;
    private LinearLayout ll_swap;

    private TextView tv_pass_error;
    private TextView tv_user_error;
    private TextView tv_pass_error1;
    private TextView tv_user_error1;
    String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imgShowPassword = (ImageView) findViewById(R.id.img_show_password);


        imgShowPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        emailPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        emailPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }

                return true;
            }
        });
        initView();
    }

    private void initView() {

        tv_pass_error = (TextView) findViewById(R.id.tv_pass_error);
        tv_user_error = (TextView) findViewById(R.id.tv_user_error);
        tv_pass_error1 = (TextView) findViewById(R.id.tv_pass_error1);
        tv_user_error1 = (TextView) findViewById(R.id.tv_user_error1);

        rrLog = (LinearLayout) findViewById(R.id.rr_log);
        rrLogin = (RelativeLayout) findViewById(R.id.rr_login);
        loginTv = (TextView) findViewById(R.id.login_tv);
        inputLayout = (LinearLayout) findViewById(R.id.input_layout);
        emailLogin = (EditText) findViewById(R.id.email_login);
        emailPassword = (EditText) findViewById(R.id.email_password);
        llLoginSub = (LinearLayout) findViewById(R.id.ll_login_sub);
        llSignSub = (LinearLayout) findViewById(R.id.ll_sign_sub);
        dialogLoader = new DialogLoader(LoginActivity.this);

        emailLogin.addTextChangedListener(new GenericTextWatcher(emailLogin));
        emailPassword.addTextChangedListener(new GenericTextWatcher(emailPassword));

        ll_swap = (LinearLayout) findViewById(R.id.ll_swap);
        ll_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SwapActivity.class);
                startActivity(i);
            }
        });

        emailPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        llSignSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        llLoginSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginValid()) {

                    if (Configuration.isInternetConnection(LoginActivity.this)) {

                        processloginup();
                    }

                    else
                    {
                        UtilSnackbar.showSnakbarTypeInternet(findViewById(R.id.rr_log), LoginActivity.this);

                    }

                }


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

            tv_user_error.setVisibility(View.GONE);
            tv_pass_error.setVisibility(View.GONE);
            tv_pass_error1.setVisibility(View.GONE);
            tv_user_error1.setVisibility(View.GONE);


        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Aut

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_user_error.setVisibility(View.VISIBLE);
            tv_pass_error.setVisibility(View.VISIBLE);
            tv_pass_error1.setVisibility(View.VISIBLE);
            tv_user_error1.setVisibility(View.VISIBLE);

        }
    }


    private void processloginup() {

        dialogLoader.showProgressDialog();

        RestClient.getInst().Login(emailLogin.getText().toString(),emailPassword.getText().toString()).enqueue(new HttpCallback<Login>() {
            @Override
            public void onSuccess(Call<Login> call, Response<Login> response) {
                dialogLoader.hideProgressDialog();
                if (response.isSuccessful()) {

                    if (response.body().getMessage().equalsIgnoreCase("phaukat E-shop send OTP to your register mobile number verify to continue login.")) {

                        LocalConstant.getInstance(LoginActivity.this).setIsLogin("1");
                        LocalConstant.getInstance(LoginActivity.this).setUserEmail(response.body().getLogin_data().getFld_user_email());
                        LocalConstant.getInstance(LoginActivity.this).setUserMobile(response.body().getLogin_data().getFld_user_phone());
                        LocalConstant.getInstance(LoginActivity.this).setUserID(response.body().getLogin_data().getFld_user_id());
                        startActivity(new Intent(LoginActivity.this, ListItemActivity.class));
                        finishAffinity();
                    }
                    else {
                        UtilSnackbar.showSnackbardoesnotexist(findViewById(R.id.rr_log), LoginActivity.this);

                    }

                }
                else {

                }
            }

            @Override
            public void onError(Call<Login> call, Throwable t) {
                dialogLoader.hideProgressDialog();
                UtilSnackbar.showSnakbarTypeError(findViewById(R.id.rr_log), LoginActivity.this);

            }
        });

    }



    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkValidation(String s)
    {
        String input = emailLogin.getText().toString();

        if(input.contains("@"))
        {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
        }
        else
        {
            return android.util.Patterns.PHONE.matcher(input).matches();
        }
    }

    private boolean isLoginValid() {

        if (TextUtils.isEmpty(emailLogin.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_user_error.setVisibility(View.VISIBLE);
                    tv_user_error.requestFocus();
                    emailLogin.requestFocus();
                }
            });
            return false;
        }

        else if (!checkValidation(emailLogin.getText().toString())) {
            runOnUiThread(new Runnable() {
                @SuppressLint("WrongConstant")
                @Override
                public void run() {
                    tv_user_error1.setVisibility(View.VISIBLE);
                    tv_user_error1.requestFocus();
                    emailLogin.requestFocus();
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(emailPassword.getText().toString())) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_pass_error.setVisibility(View.VISIBLE);
                    emailPassword.requestFocus();
                }
            });
            return false;
        }

        return true;
    }

}