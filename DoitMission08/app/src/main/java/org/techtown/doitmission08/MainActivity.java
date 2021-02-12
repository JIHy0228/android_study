package org.techtown.doitmission08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText login_id;
    EditText login_pw;
    int MAIN_RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = login_id.getText().toString();
                String userpw = login_pw.getText().toString();

                if (userid.length() != 0 && userpw.length() != 0) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.putExtra("userid", userid);
                    intent.putExtra("userpw", userpw);
                    startActivityForResult(intent, MAIN_RESULT_CODE);
                } else {
                    Toast.makeText(getApplicationContext(), "아이디 or 비밀번호 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MAIN_RESULT_CODE){
            if(data != null){
                String name_Customer = data.getStringExtra("Re_Lbutton");
                if(name_Customer != null){
                    Toast.makeText(this,"고객 관리 - 로그인 ",Toast.LENGTH_SHORT).show();
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