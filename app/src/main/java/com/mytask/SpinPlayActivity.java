package com.mytask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;

public class SpinPlayActivity extends AppCompatActivity {

    private LuckyWheel lw;
    List<WheelItem> wheelItems ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_play);

        lw = findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);
        lw.setTarget(1);


        lw.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Toast.makeText(SpinPlayActivity.this, "Target Reached", Toast.LENGTH_LONG).show();
            }
        });

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lw.rotateWheelTo(1);
            }
        });
    }
    }

