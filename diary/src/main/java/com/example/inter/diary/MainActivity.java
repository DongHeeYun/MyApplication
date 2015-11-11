package com.example.inter.diary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText edtDiary;
    Button btn1;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker)findViewById(R.id.dataPicker1);
        edtDiary = (EditText)findViewById(R.id.editDiary);
        btn1 = (Button)findViewById(R.id.btn1);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);
        fileName = Integer.toString(cYear)+"_"+Integer.toString(cMonth)+"_"+Integer.toString(cDay)+".txt";

        String str = readDiary(fileName);
        edtDiary.setText(str);


        dp.init(cYear,cMonth,cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year)+"_"+Integer.toString(monthOfYear)+"_"+Integer.toString(dayOfMonth)+".txt";
                String str = readDiary(fileName);
                edtDiary.setText(str);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(),fileName+"이 저장됨",0).show();
                }catch (IOException e){}
            }
        });
    }
    String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btn1.setText("수정하기");
        }catch(IOException e){
            edtDiary.setHint("일기 없음");
            btn1.setText("새로 저장");
        }
        return diaryStr;
    }

}
