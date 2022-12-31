package com.example.petneverland;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDialog = new Dialog(this);
    }

//    彈出設定視窗
    public void ShowPopup(View v) {
        Button button;
        myDialog.setContentView(R.layout.activity_settings);
        button = (Button) myDialog.findViewById(R.id.dismiss);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

//    Start 至遊戲主頁面
    public void gotoMainGameActivity(View v) {
        Intent it = new Intent(this, MainGameActivity.class);
        startActivity(it);
    }


}