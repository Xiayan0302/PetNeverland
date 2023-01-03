package com.example.petneverland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainGameActivity extends AppCompatActivity  {

    private ImageView anim_doll;
    private AnimationDrawable ad;
    TextView Feeling;
    int feel = 0;
    boolean feelmax = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

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

////設定點擊動畫
//                anim_doll.setImageResource(R.drawable.doll_touch);
//                // 核心实现代码
//                ad = (AnimationDrawable) anim_doll.getDrawable();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ad.start();
//                    }
//                }, 300);

//設定心情值

                if (feelmax == true) {
                    feel = feel +1;
                    Feeling.setText(String.valueOf(feel));
                }
    //心情值滿時toast提示
                else {
                    //把xml的資源轉成view
                    LayoutInflater inflater = getLayoutInflater();
                    //R.layout.toast_view XML名稱
                    //R.id.toast_layoutt XML裡面Layout ID
                    View layout = inflater.inflate(R.layout.toast_view, (ViewGroup) findViewById(R.id.toast_layout));
                    //透過 inflater跟View方式來取得元件的控制權
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("I am Happy now!");
                    Toast toast = Toast.makeText(MainGameActivity.this, "Toast置中顯示", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0, -200);
                    toast.setView(layout);
                    toast.show();
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
                if (feel >= 10) {
                    feelmax = false;
                }


            }
        });





//        anim_doll.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    ad = (AnimationDrawable) anim_doll.getDrawable();
//                        if (ad.isRunning()){
//                            anim_doll.setImageResource(R.drawable.doll_touch);
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ad.start();
//                                    ad.setOneShot(true);
//                                }
//                            }, 300);
//                        }
////                        else{
////                            anim_doll.setImageResource(R.drawable.doll_normal);
////                            Handler handler = new Handler();
////                            handler.postDelayed(new Runnable() {
////                                @Override
////                                public void run() {
////                                    ad.start();
////                                    ad.setOneShot(true);
////                                }
////                            }, 300);
////
////                        }
//                    }
//                    return true;
//            }
//        });




//導航欄
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.food:
                        startActivity(new Intent(getApplicationContext(),a_Game_food.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.closet:
                        startActivity(new Intent(getApplicationContext(),b_Game_closet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(),d_Game_message.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.laugh:
                        startActivity(new Intent(getApplicationContext(),e_Game_laugh.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        }

}