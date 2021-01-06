package org.techtown.doitmission07;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int requestCode = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == requestCode) {
            if (data != null) {
               String name_Customer = data.getStringExtra("Customer_Manage");
                if(name_Customer != null){
                    Toast.makeText(this,"고객 관리 버튼 선택",Toast.LENGTH_SHORT).show();
                }
                String name_Sales = data.getStringExtra("Sales_Manage");
                if(name_Sales != null){
                    Toast.makeText(this,"매출 관리 버튼 선택",Toast.LENGTH_SHORT).show();
                }
                String name_Product = data.getStringExtra("Product_Manage");
                if(name_Product != null){
                    Toast.makeText(this,"상품 관리 버튼 선택",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}