package com.mytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mytask.login.LoginActivity;
import com.mytask.product.ListItemActivity;
import com.mytask.util.LocalConstant;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (LocalConstant.getInstance(WelcomeActivity.this).getIsLogin().equalsIgnoreCase("1")) {
            startActivity(new Intent(WelcomeActivity.this, ListItemActivity.class));
            LocalConstant.getInstance(WelcomeActivity.this).setKey("1");
            finishAffinity();
        }
        else {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finishAffinity();
        }
    }
}