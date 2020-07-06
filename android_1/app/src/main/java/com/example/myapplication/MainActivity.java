package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private ImageView imageView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.et_picname);
        imageView = (ImageView)findViewById(R.id.img_0);
        btn = (Button)findViewById(R.id.btn_go);
        btn.setOnClickListener(this);

    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                String testName = editText.getText().toString();
                if(testName.equals("zjg")) {
                    imageView.setImageResource(R.mipmap.piczjg);
                } else if(testName.equals("yq")) {
                    imageView.setImageResource(R.mipmap.picyq);
                } else if(testName.equals("zj")) {
                    imageView.setImageResource((R.mipmap.piczj));
                } else {
                    imageView.setImageResource((R.mipmap.pic0));
                }
                break;
            default:
                break;
        }
        Log.i("btn", "button GO clicked");
    }

    public void onCheckboxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        switch (v.getId()) {
            case R.id.cb_zjg:
                if (checked) Log.i("zjg", "zjg checked");
                else Log.i("zjg", "zjg unchecked");
                break;
            case R.id.cb_yq:
                if (checked) Log.i("yq", "yq checked");
                else Log.i("yq", "yq unchecked");
                break;
            case R.id.cb_zj:
                if (checked) Log.i("zj", "zj checked");
                else Log.i("zj", "zj unchecked");
                break;
        }
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
    }
}