package com.example.petneverland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class e_Game_laugh extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toast tos;
    Dialog myDialog;

    //笑話集
    String[] queArr = {
            "阿杏摔個四腳朝天，變什麼？",
            "壞事一定要在中午做，為什麼？",
            "皮卡丘左右跳會變成什麼？",
            "川普跌倒了變什麼？",
            "什麼時候二加一會不等於三?",
            "開車時同時踩煞車跟油門會怎麼樣？",
            "一歲的蜜蜂到了一百歲變成甚麼？",
            "上完廁所，要用左手還是右手擦屁股會比較好?",
            "媽媽: 小陳! 不可以用手指月亮。",
            "為什麼模範生容易被綁架？",};

    String[] ansArr = {
            "阿呆",
            "因為…早晚會有報應",
            "皮卡乒乓乒乓" ,
            "三普",
            "算錯的時候",
            "會截圖",
            "高齡蜂",
            "用衛生紙擦比較好",
            "小東: 蛤?",
            "因為他一副好綁樣",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egame_laugh);


//bottom_navigation
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.laugh);

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
                        startActivity(new Intent(getApplicationContext(),MainGameActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(),d_Game_message.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.laugh:
                        return true;
                }
                return false;
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                queArr);

        ListView lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);


        //設定返回主畫面
        ImageButton imageButton1 = findViewById(R.id.game_back);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(e_Game_laugh.this, MainActivity.class);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_laugh, (ViewGroup) findViewById(R.id.toast_laugh));
        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        tos.setGravity(Gravity.CENTER,0, 600);
        tos.setView(layout);
        tos.show();    // 立即重新顯示

        //透過 inflater跟View方式來取得元件的控制權
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(ansArr[position]);




    }
}