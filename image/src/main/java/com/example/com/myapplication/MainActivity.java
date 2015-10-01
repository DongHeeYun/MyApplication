package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;
    Switch switch1;
    RadioGroup radioGroup1;
    RadioButton radioJelly, radioKitkat, radioLollipop;
    Button btn1, btn2;
    ImageView imgAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진 보기");

        text1 = (TextView)findViewById(R.id.Text1);
        switch1 = (Switch)findViewById(R.id.Switch1);

        text2 = (TextView)findViewById(R.id.Text2);
        radioGroup1 = (RadioGroup)findViewById(R.id.RadioGroup1);
        radioJelly = (RadioButton)findViewById(R.id.RadioJelly);
        radioKitkat = (RadioButton)findViewById(R.id.RadioKitkat);
        radioLollipop = (RadioButton)findViewById(R.id.RadioLollipop);

        btn1 = (Button)findViewById(R.id.Btn1);
        btn2 = (Button)findViewById(R.id.Btn2);
        imgAndroid = (ImageView)findViewById(R.id.ImgAndroid);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch1.isChecked()==true) {
                    text2.setVisibility(View.VISIBLE);
                    radioGroup1.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.VISIBLE);
                    imgAndroid.setVisibility(View.VISIBLE);
                }
                else{
                    text2.setVisibility(View.INVISIBLE);
                    radioGroup1.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                    imgAndroid.setVisibility(View.INVISIBLE);
                }
            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.RadioJelly:
                        imgAndroid.setImageResource(R.drawable.jelly);
                        break;
                    case R.id.RadioKitkat:
                        imgAndroid.setImageResource(R.drawable.kitkat);
                        break;
                    case R.id.RadioLollipop:
                        imgAndroid.setImageResource(R.drawable.lollipop);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"선택해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch1.setChecked(false);
                text2.setVisibility(View.INVISIBLE);
                radioGroup1.setVisibility(View.INVISIBLE);
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                imgAndroid.setVisibility(View.INVISIBLE);
            }
        });
    }

}

