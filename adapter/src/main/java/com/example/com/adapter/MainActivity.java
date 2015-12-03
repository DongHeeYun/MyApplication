package com.example.com.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("포스터");

        System.out.print("-------------------------------------------------------------------");
        final GridView gv = (GridView)findViewById(R.id.gridView1);
        myGridAdapter gAdapter = new myGridAdapter(this);
        gv.setAdapter(gAdapter);
    }

    public class myGridAdapter extends BaseAdapter{
        Context context;

        Integer[] posterID = {R.drawable.pos1,R.drawable.pos2,R.drawable.pos3,R.drawable.pos4,R.drawable.pos5,R.drawable.pos6,R.drawable.pos7,R.drawable.pos8,R.drawable.pos9,R.drawable.pos10,
                R.drawable.pos1,R.drawable.pos2,R.drawable.pos3,R.drawable.pos4,R.drawable.pos5,R.drawable.pos6,R.drawable.pos7,R.drawable.pos8,R.drawable.pos9,R.drawable.pos10,
                R.drawable.pos1,R.drawable.pos2,R.drawable.pos3,R.drawable.pos4,R.drawable.pos5,R.drawable.pos6,R.drawable.pos7,R.drawable.pos8,R.drawable.pos9,R.drawable.pos10,
                R.drawable.pos1,R.drawable.pos2,R.drawable.pos3,R.drawable.pos4,R.drawable.pos5,R.drawable.pos6,R.drawable.pos7,R.drawable.pos8,R.drawable.pos9,R.drawable.pos10};
        String[] posterName = {"내부자들","하트 오브 더 씨", "열정 같은 소리 하고 있네","검은 사제들","극적인 하룻밤","사우스 포","시카리오","도리화가","파워 레인저","헝거게임",
                "내부자들","하트 오브 더 씨", "열정 같은 소리 하고 있네","검은 사제들","극적인 하룻밤","사우스 포","시카리오","도리화가","파워 레인저","헝거게임",
                "내부자들","하트 오브 더 씨", "열정 같은 소리 하고 있네","검은 사제들","극적인 하룻밤","사우스 포","시카리오","도리화가","파워 레인저","헝거게임",
                "내부자들","하트 오브 더 씨", "열정 같은 소리 하고 있네","검은 사제들","극적인 하룻밤","사우스 포","시카리오","도리화가","파워 레인저","헝거게임"};

        public myGridAdapter(Context c){
            context = c;
        }

        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.dialog,null);
            convertView.setLayoutParams(new LinearLayout.LayoutParams(100,250));

            ImageView imageView = (ImageView)convertView.findViewById(R.id.ivPos);
            imageView.setImageResource(posterID[position]);

            TextView textView = (TextView)convertView.findViewById(R.id.ivName);
            textView.setText(posterName[position]);

            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView)dialogView.findViewById(R.id.ivPos);
                    ivPoster.setImageResource(posterID[pos]);
                    TextView ivName = (TextView)dialogView.findViewById(R.id.ivName);
                    ivName.setText(posterName[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.icon);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return convertView;
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
