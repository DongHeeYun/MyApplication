package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRes;
    TextView textView;
    String num1, num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 계산기");

        edit1 = (EditText)findViewById(R.id.Edit1);
        edit2 = (EditText)findViewById(R.id.Edit2);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnRes = (Button)findViewById(R.id.btnRes);
        textView = (TextView)findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(edit1.getText().toString().equals("") ||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                   return;
                }
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Double.parseDouble(num1)+Double.parseDouble(num2);
                textView.setText("계산 결과 : "+result.toString());
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit1.getText().toString().equals("") ||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Double.parseDouble(num1)-Double.parseDouble(num2);
                textView.setText("계산 결과 : "+result.toString());
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("") ||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Double.parseDouble(num1)*Double.parseDouble(num2);
                textView.setText("계산 결과 : "+result.toString());
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("") ||edit2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Double.parseDouble(edit2.getText().toString())==0) {
                    Toast.makeText(getApplicationContext(), "0으로는 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Double.parseDouble(num1)/Double.parseDouble(num2);
                textView.setText("계산 결과 : "+result.toString());
            }
        });
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Double.parseDouble(edit2.getText().toString())==0) {
                    Toast.makeText(getApplicationContext(), "0으로는 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Double.parseDouble(num1)%Double.parseDouble(num2);
                textView.setText("계산 결과 : "+result.toString());
            }
        });
    }
}
