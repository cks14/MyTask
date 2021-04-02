package com.mytask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mytask.util.DialogLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rubikstudio.library.PielView;
import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

public class WheelPlayActivity extends AppCompatActivity {
    List<LuckyItem> data = new ArrayList<>();
    TextView txt_no,txt_yes;
    Button play;
    DialogLoader dialogLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_play);
        play = findViewById(R.id.play);

        final LuckyWheelView luckyWheelView = (LuckyWheelView) findViewById(R.id.luckyWheel);
        LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.topText = "500 Watts";
        luckyItem1.color = 0xFF2068DA;
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.topText = "10";
        luckyItem2.color = 0xFF9730FE;
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.topText = "20";
        luckyItem3.color = 0xFFFE0E70;
        data.add(luckyItem3);
        //////////////////
        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.topText = "30";
        luckyItem4.color = 0xFF42AAF6;
        data.add(luckyItem4);
        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.topText = "35";
        luckyItem5.color = 0xFFFFB300;
        data.add(luckyItem5);
        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.topText = "50";
        luckyItem6.color = 0xFF00897B;
        data.add(luckyItem6);

        luckyWheelView.setData(data);
        luckyWheelView.setRound(5);

        int index = getRandomIndex();
        luckyWheelView.startLuckyWheelWithTargetIndex(index);
        luckyWheelView.setLuckyWheelCenterImage(getResources().getDrawable(R.drawable.spin_design));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                //  Toast.makeText(getActivity(), data.get(index).topText, Toast.LENGTH_SHORT).show();

                if (data.get(0).topText == "0") {
                    OpenDilog("0","Better Luck next time !!");
                } else {
                    //OpenDilog(data.get(index).topText,"Congrats,You earn "+data.get(index).topText+" KNP in your wallet. Try again in an hour to earn more.");
                }
            }
        });
    }

        public  void OpenDilog(String points,String title){

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

            alertBuilder.setMessage(title);
            alertBuilder.setCancelable(false);

            alertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });


            AlertDialog alert = alertBuilder.create();
            alert.show();
    }


    private int getRandomIndex() {
        Random rand = new Random();
//        return rand.nextInt(1);
        return 1;

       // return rand.nextInt(data.size() - 1) + 0;
    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
    }

}