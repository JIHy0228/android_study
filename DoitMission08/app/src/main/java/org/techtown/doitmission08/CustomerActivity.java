package org.techtown.doitmission08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerActivity extends AppCompatActivity {

    int MAIN_RESULT_CODE = 100;
    int MENU_RESULT_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Button Re_Mbutton = findViewById(R.id.Re_Mbutton);
        Re_Mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Customer_Manage","00");
                setResult(MENU_RESULT_CODE, intent);
                finish();
            }
        });

        Button Re_Lbutton = findViewById(R.id.Re_Lbutton);
        Re_Lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Re_Lbutton","01");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                setResult(MAIN_RESULT_CODE, intent);
                finish();
            }
        });
    }
}