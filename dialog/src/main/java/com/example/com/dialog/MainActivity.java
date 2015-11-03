package com.example.com.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText tvName, tvEmail;
    Button tvBtn;
    EditText dlgName, dlgEmail;
    TextView toastText;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (EditText)findViewById(R.id.edt1);
        tvEmail = (EditText)findViewById(R.id.edt2);
        tvBtn = (Button)findViewById(R.id.btn);

        tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.star);
                dlg.setView(dialogView);
                dlgName = (EditText)dialogView.findViewById(R.id.edt1);
                dlgName.setText(tvName.getText().toString());
                dlgEmail = (EditText)dialogView.findViewById(R.id.edt2);
                dlgEmail.setText(tvEmail.getText().toString());
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvName.setText(dlgName.getText().toString());
                        tvEmail.setText(dlgEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View)View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView)toastView.findViewById(R.id.toastText);
                        toastText.setText("취소했습니다.");
                        Display dis = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int x = (int)(Math.random()*dis.getWidth());
                        int y = (int)(Math.random()*dis.getHeight());
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, x, y);
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });

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
