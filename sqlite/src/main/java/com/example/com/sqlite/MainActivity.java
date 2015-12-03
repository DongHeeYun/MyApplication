package com.example.com.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    myDBHelper myHelper;
    EditText edtName, edtNum, edtNameResult, edtNumResult;
    Button btnInit, btnIn, btnMo, btnDe, btnRe;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그룹");

        edtName = (EditText)findViewById(R.id.edtName);
        edtNum = (EditText)findViewById(R.id.edtNum);
        edtNameResult = (EditText)findViewById(R.id.edtNameResult);
        edtNumResult = (EditText)findViewById(R.id.edtNumResult);
        btnInit = (Button)findViewById(R.id.btnInit);
        btnIn = (Button)findViewById(R.id.btnIn);
        btnMo = (Button)findViewById(R.id.btnMo);
        btnDe = (Button)findViewById(R.id.btnDe);
        btnRe = (Button)findViewById(R.id.btnRe);

        myHelper = new myDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('"+edtName.getText().toString()+"' , "+edtNum.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨",0).show();
                btnRe.callOnClick();
            }
        });

        btnMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber = "+edtNum.getText().toString()+" WHERE gName = '"+edtName.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정됨",0).show();
                btnRe.callOnClick();
            }
        });

        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '"+edtName.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제됨",0).show();
                btnRe.callOnClick();
            }
        });

        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;",null);

                String strNames = "그룹 이름"+"\r\n"+"--------"+"\r\n";
                String strNum = "인원"+"\r\n"+"--------"+"\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0)+"\r\n";
                    strNum += cursor.getString(1)+"\r\n";
                }

                edtNameResult.setText(strNames);
                edtNumResult.setText(strNum);

                cursor.close();
                sqlDB.close();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context,"groupDB",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
