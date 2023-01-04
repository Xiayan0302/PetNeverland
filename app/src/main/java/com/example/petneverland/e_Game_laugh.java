package com.example.petneverland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class e_Game_laugh extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toast tos;

    //笑話集
    String[] queArr = {"什麼門永遠關不上?","什麼東西沒人愛吃?",
            "什麼瓜不能吃?","什麼布切不斷",
            "什麼鼠最愛乾淨","偷什麼不犯法"};

    String[] ansArr = {"球門", "虧",
            "傻瓜" ,"瀑布",
            "環保署","偷笑"};

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

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
//        tos.setText("答案︰"+ansArr[position]);  // 變更 Toast 物件的文字內容
//        tos.show();    // 立即重新顯示
//
//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.toast_view, (ViewGroup) findViewById(R.id.toast_layout));
//        //透過 inflater跟View方式來取得元件的控制權
//        TextView text = (TextView) layout.findViewById(R.id.text);
//
//        text.setText("I am Happy now!");
//        Toast toast = Toast.makeText(e_Game_laugh.this, "Toast置中顯示", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER,0, -200);
//        toast.setView(layout);
//        toast.show();

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_view, (ViewGroup) findViewById(R.id.toast_layout));
        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        tos.setGravity(Gravity.CENTER,0, 200);
        tos.setView(layout);
        tos.show();    // 立即重新顯示

        //透過 inflater跟View方式來取得元件的控制權
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("答案:"+ansArr[position]);




    }
}