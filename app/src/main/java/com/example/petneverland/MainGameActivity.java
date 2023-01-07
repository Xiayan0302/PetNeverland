package com.example.petneverland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainGameActivity extends AppCompatActivity {

    private ImageView anim_doll;
    private AnimationDrawable ad;
    TextView Feeling;
    int feel = 0;
    boolean feelmax = true;
    ImageView State_feel;

    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        State_feel = (ImageView) findViewById(R.id.state_feel);
        Feeling = (TextView) findViewById(R.id.Feeling);
        Feeling.setText(String.valueOf(feel));


//動畫設定
        anim_doll = (ImageView) findViewById(R.id.anim_doll);
        // 核心实现代码
        ad = (AnimationDrawable) anim_doll.getDrawable();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ad.start();
            }
        }, 300);

//設置點擊監聽器
        anim_doll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//設定心情值
                if (feelmax == true) {
                    feel = feel + 1;
                    Feeling.setText(String.valueOf(feel));
                }
                //心情值滿時toast提示
                else {
                    //把xml的資源轉成view
                    LayoutInflater inflater = getLayoutInflater();
                    //R.layout.toast_view XML名稱
                    //R.id.toast_layout XML裡面Layout ID
                    View layout = inflater.inflate(R.layout.toast_view, (ViewGroup) findViewById(R.id.toast_layout));
                    Toast toast = Toast.makeText(MainGameActivity.this, "Toast置中顯示", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 200);
                    toast.setView(layout);
                    toast.show();
                    //透過 inflater跟View方式來取得元件的控制權
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("I am Happy now!");
                    //心情值滿時更改動畫
                    anim_doll.setImageResource(R.drawable.doll_touch);
                    // 核心实现代码
                    ad = (AnimationDrawable) anim_doll.getDrawable();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ad.start();
                        }
                    }, 300);

                }
                if (feel >= 3) {
                    State_feel.setImageResource(R.drawable.state_feel2);
                }
                if (feel >= 6) {
                    State_feel.setImageResource(R.drawable.state_feel3);
                }
                if (feel >= 8) {
                    State_feel.setImageResource(R.drawable.state_feel4);
                }
                if (feel >= 10) {
                    feelmax = false;
                    Feeling.setText("MAX");
                    State_feel.setImageResource(R.drawable.state_feel5);
                }
            }
        });


//導航欄
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.food:
                        startActivity(new Intent(getApplicationContext(), a_Game_food.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.closet:
                        startActivity(new Intent(getApplicationContext(), b_Game_closet.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(), d_Game_message.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.laugh:
                        startActivity(new Intent(getApplicationContext(), e_Game_laugh.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

//設定返回主畫面
        ImageButton imageButton1 = findViewById(R.id.game_back);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainGameActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

//設定popupWindows
        myDialog = new Dialog(this);

        ImageButton imageButton = findViewById(R.id.game_set);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

    }//OnCreate
}
