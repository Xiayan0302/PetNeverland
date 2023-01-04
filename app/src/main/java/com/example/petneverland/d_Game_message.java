package com.example.petneverland;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class d_Game_message extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static final int REQUEST_CODE_EDIT = 1;

    String[] aMemo ={
            "1.按一下可以編輯備忘",
            "2.長按可以清除備忘","3.","4.","5.","6."};
    ListView lv;
    ArrayAdapter<String> aa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgame_message);

        lv = (ListView)findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, aMemo);

        lv.setAdapter(aa);    //設定 listView1 的內容

        //設定 listView1 被按一下的監聽器
        lv.setOnItemClickListener(this);
        //設定 listView1 被長按的監聽器
        lv.setOnItemLongClickListener(this);


//bottom_navigation
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.message);

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

    @Override
    public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
        Intent it = new Intent(this, d_Game_message_edit.class);
        it.putExtra("requestCode",pos);      //附加編號
        it.putExtra("memo", aMemo[pos]); //附加備忘項目的內容
        activityResultLaunch.launch(it);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> a, View v, int pos, long id) {
        aMemo[pos] = (pos+1) + "."; //將內容清除 (只剩編號)
        aa.notifyDataSetChanged();  //通知 Adapter 要更新陣列內容
        return true;     			//傳回 true 表示此事件已處理
    }
    

//    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == RESULT_OK) {
//                        Intent data = result.getData();
//                        int index = result.getResultCode();
//                        aMemo[index] = data.getStringExtra("memo"); // 使用傳回的資料更新陣列內容
//                        aa.notifyDataSetChanged(); // 通知 Adapter 陣列內容有更新
//                    }
//                }
//            });

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        int requestCode = data.getIntExtra("requestCode", 0);
                        aMemo[requestCode] = data.getStringExtra("memo"); // 使用傳回的資料更新陣列內容
                        aa.notifyDataSetChanged(); // 通知 Adapter 陣列內容有更新
                    }
                }
            });

//    ActivityResultLauncher<String> activityResultLaunch = registerForActivityResult(new ActivityResultContracts.GetContent(),
//            new ActivityResultCallback<Uri>() {
//                @Override
//                public void onActivityResult(Uri uri) {
//                    // Handle the returned Uri
//                }
//            });

//
//        protected void onActivityResult(int requestCode, int resultCode, Intent it) {
//        if(resultCode == RESULT_OK) {
//            aMemo[requestCode] = it.getStringExtra("memo"); // 使用傳回的資料更新陣列內容
//            aa.notifyDataSetChanged(); // 通知 Adapter 陣列內容有更新
//        }
//    }

}