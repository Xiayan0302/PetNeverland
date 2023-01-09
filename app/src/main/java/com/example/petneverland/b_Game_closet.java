package com.example.petneverland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class b_Game_closet extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox gentle_black;
    private CheckBox gentle_gray;
    private CheckBox santa_red;
    private CheckBox santa_green;
    private CheckBox tie_red;
    private CheckBox tie_green;

    private ImageButton wear;
    boolean closet_max = true;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgame_closet);

        //設定食物
        gentle_black=findViewById(R.id.closet_gentle_black);
        gentle_gray=findViewById(R.id.closet_gentle_gray);
        santa_red=findViewById(R.id.closet_santa_red);
        santa_green=findViewById(R.id.closet_santa_green);
        tie_red=findViewById(R.id.closet_tie_red);
        tie_green=findViewById(R.id.closet_tie_green);

        gentle_black.setOnCheckedChangeListener(this);
        gentle_gray.setOnCheckedChangeListener(this);
        santa_red.setOnCheckedChangeListener(this);
        santa_green.setOnCheckedChangeListener(this);
        tie_red.setOnCheckedChangeListener(this);
        tie_green.setOnCheckedChangeListener(this);



//bottom_navigation
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.closet);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //背景
                switch(item.getItemId())
                {
                    case R.id.food:
                        startActivity(new Intent(getApplicationContext(),a_Game_food.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.closet:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainGameActivity.class));
                        overridePendingTransition(0,0);
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

        //設定返回主畫面
        ImageButton imageButton1 = findViewById(R.id.game_back);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(b_Game_closet.this, MainActivity.class);
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

    } //OnCreate

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()){

            case R.id.closet_gentle_black:
                if (gentle_black.isChecked()){
                    gentle_gray.setChecked(false);
                    santa_red.setChecked(false);
                    santa_green.setChecked(false);
                    tie_red.setChecked(false);
                    tie_green.setChecked(false);

                    wear=findViewById(R.id.wear);

                    wear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(closet_max == true) {
                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_wear_gentle_black);
                                button = (ImageButton) myDialog.findViewById(R.id.dismiss);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();
                            }
                        }
                    });

                }
                break;

            case R.id.closet_gentle_gray:
                if (gentle_gray.isChecked()){
                    gentle_black.setChecked(false);
                    santa_red.setChecked(false);
                    santa_green.setChecked(false);
                    tie_red.setChecked(false);
                    tie_green.setChecked(false);

                    wear=findViewById(R.id.wear);

                    wear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(closet_max == true) {
                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_wear_gentle_gray);
                                button = (ImageButton) myDialog.findViewById(R.id.dismiss);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();
                            }
                        }
                    });

                }
                break;

            case R.id.closet_santa_red:
                if (santa_red.isChecked()){
                    gentle_black.setChecked(false);
                    gentle_gray.setChecked(false);
                    santa_green.setChecked(false);
                    tie_red.setChecked(false);
                    tie_green.setChecked(false);

                    wear=findViewById(R.id.wear);

                    wear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(closet_max == true) {
                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_wear_santa_red);
                                button = (ImageButton) myDialog.findViewById(R.id.dismiss);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();
                            }
                        }
                    });

                }
                break;

            case R.id.closet_santa_green:
                if (santa_green.isChecked()){
                    gentle_black.setChecked(false);
                    gentle_gray.setChecked(false);
                    santa_red.setChecked(false);
                    tie_red.setChecked(false);
                    tie_green.setChecked(false);

                    wear=findViewById(R.id.wear);

                    wear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(closet_max == true) {
                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_wear_santa_green);
                                button = (ImageButton) myDialog.findViewById(R.id.dismiss);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();
                            }
                        }
                    });

                }
                break;

            case R.id.closet_tie_red:
                if (tie_red.isChecked()){
                    gentle_black.setChecked(false);
                    gentle_gray.setChecked(false);
                    santa_red.setChecked(false);
                    santa_green.setChecked(false);
                    tie_green.setChecked(false);

                    wear=findViewById(R.id.wear);

                    wear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(closet_max == true) {
                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_wear_tie_red);
                                button = (ImageButton) myDialog.findViewById(R.id.dismiss);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();
                            }
                        }
                    });

                }
                break;

            case R.id.closet_tie_green:
                if (tie_green.isChecked()){
                    gentle_black.setChecked(false);
                    gentle_gray.setChecked(false);
                    santa_red.setChecked(false);
                    santa_green.setChecked(false);
                    tie_red.setChecked(false);

                    wear=findViewById(R.id.wear);

                    wear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(closet_max == true) {

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_wear_tie_green);
                                button = (ImageButton) myDialog.findViewById(R.id.dismiss);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();

                            }
                        }
                    });

                }
                break;

            default:
                break;
        }

    }
}