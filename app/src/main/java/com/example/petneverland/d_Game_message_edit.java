package com.example.petneverland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class d_Game_message_edit extends AppCompatActivity {

    TextView txv;
    EditText edt;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgame_message_edit);

        Intent it = getIntent();               //取得傳入的 Intent 物件
        String s = it.getStringExtra("memo");  //讀出名為 "備忘" 的 String 資料
        position = it.getIntExtra("requestCode",position);

        txv = (TextView)findViewById(R.id.textView);
        txv.setText(s.substring(0, 2));                 //在畫面左上角顯示編號
        edt = (EditText)findViewById(R.id.editText);
        if(s.length() > 3)
            edt.setText(s.substring(2)); //將傳來的備忘資料去除前3個字, 然後填入編輯元件中


    }

    public void onCancel(View v) {  //按取消鈕時
        setResult(RESULT_CANCELED);
        finish();    //結束活動
    }

    public void onSave(View v) {    //按儲存鈕時
        Intent it2 = new Intent();
        it2.putExtra("memo", txv.getText() + " " + edt.getText()); // 附加項目編號與修改後的內容
        it2.putExtra("requestCode", position);
        setResult(RESULT_OK, it2); // 傳回代表成功的結果碼, 以及修改的資料
        finish();    //結束活動
    }
    }
