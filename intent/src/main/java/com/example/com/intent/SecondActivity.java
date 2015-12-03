package com.example.com.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by com on 2015-12-03.
 */
public class SecondActivity extends Activity {
    int hap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("세컨드");

        Intent inIntent = getIntent();

        switch (inIntent.getIntExtra("N",0)){
            case 0:
                hap = inIntent.getIntExtra("Num1",0)+inIntent.getIntExtra("Num2",0);
                break;
            case 1:
                hap = inIntent.getIntExtra("Num1",0)-inIntent.getIntExtra("Num2",0);
                break;
            case 2:
                hap = inIntent.getIntExtra("Num1",0)*inIntent.getIntExtra("Num2",0);
                break;
            case 3:
                hap = inIntent.getIntExtra("Num1",0)/inIntent.getIntExtra("Num2",0);
                break;
        }

        Button btnReturn = (Button)findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Hap",hap);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
