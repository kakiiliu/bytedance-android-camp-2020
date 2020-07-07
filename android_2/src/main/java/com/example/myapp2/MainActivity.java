package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG";

    private List<Msg> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "MainActivity onCreate");
        initView();
        initMsgs();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MsgAdapter adapter = new MsgAdapter(msgList);

        recyclerView.setAdapter(adapter);

        View page = getWindow().getDecorView();
        Log.i("ViewCount","The view number of this page is "+viewCnt(page));
        Toast.makeText(getApplicationContext(),"The view number of this page is "+viewCnt(page),Toast.LENGTH_SHORT).show();

    }

    private void initMsgs() {
        Msg ai = new Msg("Ai", "2020-07-07", R.drawable.ai_pic);
        msgList.add(ai);
        Msg heye = new Msg("Heye", "2020-07-06", R.drawable.heye_pic);
        msgList.add(heye);
        Msg jingjizhen = new Msg("Jingjizhen","2020-07-06", R.drawable.jingjizhen_pic);
        msgList.add(jingjizhen);
        Msg kid = new Msg("Kid","2020-07-01", R.drawable.kid_pic);
        msgList.add(kid);
        Msg lan = new Msg("Lan", "2020-07-03",R.drawable.lan_pic);
        msgList.add(lan);
        Msg mode = new Msg("Mode", "2020-06-25",R.drawable.mode_pic);
        msgList.add(mode);
        Msg pinci = new Msg("Pingci", "2020-05-30",R.drawable.pinci_pic);
        msgList.add(pinci);
        Msg tou = new Msg("Tou","2020-06-06", R.drawable.tou_pic);
        msgList.add(tou);
        Msg xinyi = new Msg("Xinyi","2020-05-10", R.drawable.xinyi_pic);
        msgList.add(xinyi);
        Msg xiuyi = new Msg("Xiuyi","2020-05-07", R.drawable.xiuyi_pic);
        msgList.add(xiuyi);
        Msg youxizi = new Msg("Youxizi","2020-04-16", R.drawable.youxizi_pic);
        msgList.add(youxizi);
        Msg youzuo = new Msg("Youzuo","2020-01-01", R.drawable.youzuo_pic);
        msgList.add(youzuo);
        Msg yuanzi = new Msg("Yuanzi","2020-01-01", R.drawable.yuanzi_pic);
        msgList.add(yuanzi);
    }

    private void initView() {
        findViewById(R.id.ibtn_1).setOnClickListener(this);
        findViewById(R.id.ibtn_2).setOnClickListener(this);
        findViewById(R.id.ibtn_3).setOnClickListener(this);
        findViewById(R.id.ibtn_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NoneActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity onStart");
        Toast.makeText(getApplicationContext(),"MainActivity onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "MainActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "MainActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "MainActivity onPause");
        Toast.makeText(getApplicationContext(),"MainActivity onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MainActivity onDestroy");
    }

    public int viewCnt(View root) {
        if (null == root) return 0;
        int cnt = 1;
        if (root instanceof ViewGroup) {
            cnt++;
            for (int i =0; i < ((ViewGroup) root).getChildCount(); i++) {
                View v = ((ViewGroup) root).getChildAt(i);
                if (v instanceof ViewGroup) cnt += viewCnt(v);
                else cnt++;
            }
        }
        return cnt;
    }

}