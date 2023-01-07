package com.example.petneverland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
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

public class a_Game_food extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox applepie;
    private CheckBox bread;
    private CheckBox burger;
    private CheckBox cake;
    private CheckBox egg;
    private CheckBox ginger;

    private Button eat;

    private ImageView anim_doll;
    private AnimationDrawable ad;

    TextView Eating;
    ImageView Statefood;
    int eating = 0;
    boolean food_max = true;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agame_food);

        //設定飽食度
        Statefood = (ImageView) findViewById(R.id.state_food);
        Eating = (TextView) findViewById(R.id.eatTXV);
        Eating.setText(String.valueOf(eating));

        //設定食物
        applepie=findViewById(R.id.food_applepie);
        bread=findViewById(R.id.food_bread);
        burger=findViewById(R.id.food_burger);
        cake=findViewById(R.id.food_cake);
        egg=findViewById(R.id.food_egg);
        ginger=findViewById(R.id.food_ginger);

        applepie.setOnCheckedChangeListener(this);
        bread.setOnCheckedChangeListener(this);
        burger.setOnCheckedChangeListener(this);
        cake.setOnCheckedChangeListener(this);
        egg.setOnCheckedChangeListener(this);
        ginger.setOnCheckedChangeListener(this);

//bottom_navigation
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.food);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.food:
                        return true;
                    case R.id.closet:
                        startActivity(new Intent(getApplicationContext(),b_Game_closet.class));
                        overridePendingTransition(0,0);
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
                Intent it = new Intent(a_Game_food.this, MainActivity.class);
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


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
        switch (compoundButton.getId()){

            case R.id.food_applepie:
                if (applepie.isChecked()){
                    bread.setChecked(false);
                    burger.setChecked(false);
                    cake.setChecked(false);
                    egg.setChecked(false);
                    ginger.setChecked(false);

                    eat=findViewById(R.id.eat);

                    eat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(food_max == true) {
                                //加飽食度
                                eating = eating + 1;
                                Eating.setText(String.valueOf(eating));

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_applepie);
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
                            else{
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_end);
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

                            if (eating >= 3) {
                                Statefood.setImageResource(R.drawable.state_food2);
                            }

                            if (eating >= 5) {
                                food_max = false;
                                Eating.setText("MAX");
                                Statefood.setImageResource(R.drawable.state_food3);
                            }

                        }
                    });

                }
                break;

            case R.id.food_bread:
                if (bread.isChecked()){
                    applepie.setChecked(false);
                    burger.setChecked(false);
                    cake.setChecked(false);
                    egg.setChecked(false);
                    ginger.setChecked(false);

                    eat=findViewById(R.id.eat);

                    eat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(food_max == true) {
                                //加飽食度
                                eating = eating + 1;
                                Eating.setText(String.valueOf(eating));

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_bread);
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
                            else{
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_end);
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
                            if (eating >= 3) {
                                Statefood.setImageResource(R.drawable.state_food2);
                            }

                            if (eating >= 5) {
                                food_max = false;
                                Eating.setText("MAX");
                                Statefood.setImageResource(R.drawable.state_food3);
                            }
                        }
                    });

                }
                break;

            case R.id.food_burger:
                if (burger.isChecked()){
                    applepie.setChecked(false);
                    bread.setChecked(false);
                    cake.setChecked(false);
                    egg.setChecked(false);
                    ginger.setChecked(false);

                    eat=findViewById(R.id.eat);

                    eat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(food_max == true) {
                                //加飽食度
                                eating = eating + 1;
                                Eating.setText(String.valueOf(eating));

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_burger);
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
                            else{
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_end);
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
                            if (eating >= 3) {
                                Statefood.setImageResource(R.drawable.state_food2);
                            }
                            if (eating >= 5) {
                                food_max = false;
                                Eating.setText("MAX");
                                Statefood.setImageResource(R.drawable.state_food3);
                            }
                        }
                    });

                }
                break;

            case R.id.food_cake:
                if (cake.isChecked()){
                    applepie.setChecked(false);
                    bread.setChecked(false);
                    burger.setChecked(false);
                    egg.setChecked(false);
                    ginger.setChecked(false);

                    eat=findViewById(R.id.eat);

                    eat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(food_max == true) {
                                //加飽食度
                                eating = eating + 1;
                                Eating.setText(String.valueOf(eating));

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_cake);
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
                            else{
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_end);
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
                            if (eating >= 3) {
                                Statefood.setImageResource(R.drawable.state_food2);
                            }

                            if (eating >= 5) {
                                food_max = false;
                                Eating.setText("MAX");
                                Statefood.setImageResource(R.drawable.state_food3);
                            }
                        }
                    });

                }
                break;

            case R.id.food_egg:
                if (egg.isChecked()){
                    applepie.setChecked(false);
                    bread.setChecked(false);
                    burger.setChecked(false);
                    cake.setChecked(false);
                    ginger.setChecked(false);

                    eat=findViewById(R.id.eat);

                    eat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(food_max == true) {
                                //加飽食度
                                eating = eating + 1;
                                Eating.setText(String.valueOf(eating));

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_egg);
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
                            else{
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_end);
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
                            if (eating >= 3) {
                                Statefood.setImageResource(R.drawable.state_food2);
                            }

                            if (eating >= 5) {
                                food_max = false;
                                Eating.setText("MAX");
                                Statefood.setImageResource(R.drawable.state_food3);
                            }
                        }
                    });

                }
                break;

            case R.id.food_ginger:
                if (ginger.isChecked()){
                    applepie.setChecked(false);
                    bread.setChecked(false);
                    burger.setChecked(false);
                    cake.setChecked(false);
                    egg.setChecked(false);

                    eat=findViewById(R.id.eat);

                    eat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(food_max == true) {
                                //加飽食度
                                eating = eating + 1;
                                Eating.setText(String.valueOf(eating));

                                //設置popup
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_ginger);
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
                            else{
                                ImageButton button;
                                myDialog.setContentView(R.layout.activity_eat_end);
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
                            if (eating >= 3) {
                                Statefood.setImageResource(R.drawable.state_food2);
                            }

                            if (eating >= 5) {
                                food_max = false;
                                Eating.setText("MAX");
                                Statefood.setImageResource(R.drawable.state_food3);
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
