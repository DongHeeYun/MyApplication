package com.example.com.mydiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView1,delDialogTextView;
    Button btn1;
    EditText editText1;
    DatePicker picker1;
    String fileName, dateStr, myDirPath;
    View dialogView, delDialogView;
    int cYear, cMonth, cDay;

    //글씨 크기 정의
    final float SMALL = 10;
    final float NOMAL = 20;
    final float BIG = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        btn1 = (Button)findViewById(R.id.btn1);
        editText1 = (EditText)findViewById(R.id.editText1);

        //현재 날짜를 이용해 오늘날짜 표시
        Calendar cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH);
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        fileName = Integer.toString(cYear)+"_"+Integer.toString(cMonth)+"_"+Integer.toString(cDay)+".txt";
        dateStr = Integer.toString(cYear)+"년 "+Integer.toString(cMonth)+"월 "+Integer.toString(cDay)+"일";
        textView1.setText(dateStr);

        //sdcard의 절대 경로를 저장하고 디렉토리를 생성
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        myDirPath = strSDpath + "/mydir";
        final File myDir = new File(myDirPath);
        myDir.mkdir();

        //일기를 불러오고, 텍스트 사이즈를 NAMAL사이즈로 초기화
        editText1.setText(readDiary());
        editText1.setTextSize(NOMAL);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//저장 버튼을 누르면 수행
                File tmpFile = new File(myDirPath, fileName);//디렉토리 경로와 파일명으로 파일 생성
                FileOutputStream outFs = null;
                try {
                    outFs = new FileOutputStream(tmpFile);//파일아웃풋스트림 생성
                } catch (FileNotFoundException e) {//파일을 만들수 없을경우 안내메세지 출력
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "파일을 저장할 수 없습니다.", 0).show();
                }
                String str = editText1.getText().toString();//일기내용을 변수에 저장
                try {
                    outFs.write(str.getBytes());//해당 변수를 이용해 file에 write
                    outFs.close();
                } catch (IOException e) {//파일 저장에 실패할 경우 안내메세지 출력
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "저장에 실패했습니다.", 0).show();
                }
                Toast.makeText(getApplicationContext(), fileName + "이 저장됨", 0).show();
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            int tmpYear=cYear, tmpMonth=cMonth, tmpDay=cDay;//현재 날짜를 임시 저장
            @Override
            public void onClick(View v) {//텍스트뷰를 터치할경우 수행
                //다이얼로그를 생성
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                picker1 = (DatePicker)dialogView.findViewById(R.id.picker1);
                //데이터 피커의 날짜가 바뀔때 마다 임시저장 날짜를 변경
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
                    public void onClick(DialogInterface dialog, int which) {//확인 버튼을 누를 경우
                        //현재 날짜를 임시저장한 날짜로 변경
                        cYear = tmpYear;
                        cMonth = tmpMonth;
                        cDay = tmpDay;
                        //파일이름과 현재날짜 스트링을 재정의
                        fileName = Integer.toString(cYear)+"_"+Integer.toString(cMonth)+"_"+Integer.toString(cDay)+".txt";
                        dateStr = Integer.toString(cYear)+"년 "+Integer.toString(cMonth)+"월 "+Integer.toString(cDay)+"일";

                        textView1.setText(dateStr);//현재 일기 날짜 표시
                        editText1.setText(readDiary());//현재 날짜의 일기를 읽어옴
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//취소 버튼을 누를 경우
                        //임시저장 날짜를 초기화 하고 안내메세지 출력
                        tmpYear = cYear;
                        tmpMonth = cMonth;
                        tmpDay = cDay;
                        Toast.makeText(getApplicationContext(), "취소 되었습니다.", 0).show();
                    }
                });
                dlg.show();//다이얼로그 출력
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){//메뉴아이템이 선택되면 수행
        switch (item.getItemId()){
            case R.id.itemReload ://다시 읽기 메뉴 선택
                editText1.setText(readDiary());//해당 날짜의 일기를 다시 불러옴
                Toast.makeText(getApplicationContext(),"일기 불러오기 완료.", 0).show();
                return true;
            case R.id.itemDelete ://일기 삭제 메뉴 선택
                deleteDiary();
                return true;
            case R.id.subBig ://글씨 크기 - 크게 메뉴 선택
                editText1.setTextSize(BIG);
                return true;
            case R.id.subNamal ://글씨 크기 - 보통 메뉴 선택
                editText1.setTextSize(NOMAL);
                return true;
            case R.id.subSmall ://글씨 크기 - 작게 메뉴 선택
                editText1.setTextSize(SMALL);
                return true;
            default:
                return true;
        }
    }

    void deleteDiary(){
        delDialogView = (View)View.inflate(MainActivity.this, R.layout.dialog_delete, null);
        delDialogTextView = (TextView)delDialogView.findViewById(R.id.delDialogTextView);
        delDialogTextView.setText(dateStr + "의 일기를 삭제하시겠습니까?");
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("일기 삭제");
        dlg.setView(delDialogView);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File tmpFile = new File(myDirPath, fileName);
                tmpFile.delete();
                editText1.setText("");
                Toast.makeText(getApplicationContext(), "일기 삭제 완료.", 0).show();
            }
        });
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "삭제가 취소되었습니다.", 0).show();
            }
        });
        dlg.show();
    }

    String readDiary(){//디렉토리 경로와 파일명으로 일기를 불러오는 메소드
        String diaryStr = null;//일기 내용 임시 저장용 변수
        File tmpFile = new File(myDirPath,fileName);//디렉토리 경로와 파일명으로 파일 생성
        FileInputStream inFs;
        try{
            inFs = new FileInputStream(tmpFile);//위에서 만든 파일로 FileInputStream생성
            byte[] txt = new byte[2048];
            inFs.read(txt);//파일 read
            inFs.close();
            diaryStr = (new String(txt)).trim();
            Toast.makeText(getApplicationContext(),"일기 불러오기 완료.", 0).show();//안내 메세지
        }catch(IOException e){//일기 불러오기가 실패할 경우
            editText1.setHint("저장된 일기가 없습니다.");//에디트 텍스트의 힌트 세팅
        }
        return diaryStr;//불러온 일기 내용 또는 null값을 리턴
    }


}
