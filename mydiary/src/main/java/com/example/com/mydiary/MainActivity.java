package com.example.com.mydiary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    Button btn1;
    EditText editText1;
    DatePicker picker1;
    String fileName, dateStr;
    View dialogView;
    int cYear, cMonth, cDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        btn1 = (Button)findViewById(R.id.btn1);
        editText1 = (EditText)findViewById(R.id.editText1);


        Calendar cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH);
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        fileName = Integer.toString(cYear)+"_"+Integer.toString(cMonth)+"_"+Integer.toString(cDay)+".txt";
        dateStr = Integer.toString(cYear)+"년 "+Integer.toString(cMonth)+"월 "+Integer.toString(cDay)+"일";
        textView1.setText(dateStr);

        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath + "/mydir");

        myDir.mkdir();
        editText1.setText(myDir.toString());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.mkdir();
                editText1.setText(editText1.getText().toString()+"1");
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            int tmpYear=cYear, tmpMonth=cMonth, tmpDay=cDay;
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                picker1 = (DatePicker)dialogView.findViewById(R.id.picker1);
                picker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tmpYear = year;
                        tmpMonth = monthOfYear;
                        tmpDay = dayOfMonth;
                    }
                });
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cYear = tmpYear;
                        cMonth = tmpMonth;
                        cDay = tmpDay;
                        fileName = Integer.toString(cYear)+"_"+Integer.toString(cMonth)+"_"+Integer.toString(cDay)+".txt";
                        dateStr = Integer.toString(cYear)+"년 "+Integer.toString(cMonth)+"월 "+Integer.toString(cDay)+"일";
                        textView1.setText(dateStr);
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 팦업
                    }
                });
                dlg.show();
            }
        });

    }


}
