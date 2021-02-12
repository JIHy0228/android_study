package org.techtown.doitmission08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    int MENU_RESULT_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button Customer_button = findViewById(R.id.Customer_button);
        Customer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                startActivityForResult(intent, MENU_RESULT_CODE);
            }
        });

        Button Sales_button = findViewById(R.id.Sales_button);
        Sales_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SalesActivity.class);
                startActivityForResult(intent, MENU_RESULT_CODE);
            }
        });

        Button Product_button = findViewById(R.id.Product_button);
        Product_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                startActivityForResult(intent, MENU_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MENU_RESULT_CODE){
            if(data != null) {
                String name_Customer = data.getStringExtra("Customer_Manage");
                if(name_Customer != null){
                    Toast.makeText(this,"고객 관리 버튼 선택",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}