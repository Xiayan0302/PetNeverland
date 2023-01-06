package com.example.petneverland;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//設定popupWindows
        myDialog = new Dialog(this);
//設定開啟app時播放音樂
        startService(new Intent(this, MusicPlayer.class));
    }

//    Start 至遊戲主頁面
    public void gotoMainGameActivity(View v) {
        Intent it = new Intent(this, MainGameActivity.class);
        startActivity(it);
    }

//    彈出設定視窗
    public void ShowPopup(View v) {
        ImageButton button;
        Switch switch_music;
        myDialog.setContentView(R.layout.activity_settings);
        button = (ImageButton) myDialog.findViewById(R.id.dismiss);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        //設定控制音樂開關
        switch_music = (Switch) myDialog.findViewById(R.id.switch_music);
        switch_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // The switch button is on
                    startService(new Intent(getApplicationContext(), MusicPlayer.class));
                } else {
                    // The switch button is off
                    stopService(new Intent(getApplicationContext(), MusicPlayer.class));
                }
            }
        });
    }

}

