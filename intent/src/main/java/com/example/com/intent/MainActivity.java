package com.example.com.intent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    RadioGroup rGroup;
    RadioButton rAdd, rMinus, rMulti, rSub;
    Button btn1;
    EditText edt1, edt2;
    int n=0;//0=add 1=Minus 2=multi 3=sub

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인");

        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        rGroup = (RadioGroup)findViewById(R.id.rGroup);
        rAdd = (RadioButton)findViewById(R.id.rAdd);
        rMinus = (RadioButton)findViewById(R.id.rMinus);
        rMulti = (RadioButton)findViewById(R.id.rMulti);
        rSub = (RadioButton)findViewById(R.id.rSub);
        btn1 = (Button)findViewById(R.id.btn1);

        rAdd.setChecked(true);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (rGroup.getCheckedRadioButtonId()) {
                    case R.id.rAdd:
                        n = 0;
                        break;
                    case R.id.rMinus:
                        n = 1;
                        break;
                    case R.id.rMulti:
                        n = 2;
                        break;
                    case R.id.rSub:
                        n = 3;
                        break;
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("N",n);
                intent.putExtra("Num1", Integer.parseInt(edt1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(edt2.getText().toString()));
                startActivityForResult(intent,0);
            }
        });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            int hap = data.getIntExtra("Hap",0);
            Toast.makeText(getApplicationContext(),"합계 : "+hap, Toast.LENGTH_SHORT).show();
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
