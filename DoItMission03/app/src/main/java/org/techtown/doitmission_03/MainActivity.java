package org.techtown.doitmission_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }
    public void OnClick(View v) {
        switch (v.getId()){
            case R.id.button:
                imageView.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "UP 버튼이 눌렸어요", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                imageView2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                Toast.makeText(this,"DOWN 버튼이 눌렸어요",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}