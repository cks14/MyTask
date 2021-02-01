package com.mytask;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SwapActivity extends AppCompatActivity {

    private RelativeLayout rrLogin;
    private TextView swapTv;
    private LinearLayout inputLayout;
    private EditText firstNumber;
    private EditText secondNumber;
    private LinearLayout llSwapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initView();
    }

    private void initView() {
        rrLogin = (RelativeLayout) findViewById(R.id.rr_login);
        swapTv = (TextView) findViewById(R.id.swap_tv);
        inputLayout = (LinearLayout) findViewById(R.id.input_layout);
        firstNumber = (EditText) findViewById(R.id.first_number);
        secondNumber = (EditText) findViewById(R.id.second_number);
        llSwapBtn = (LinearLayout) findViewById(R.id.ll_swap_btn);

        llSwapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(firstNumber.getText().toString());
                int  b = Integer.parseInt(secondNumber.getText().toString());

                firstNumber.setText(Integer.toString(b));
                secondNumber.setText(Integer.toString(a));
            }
        });

    }
}