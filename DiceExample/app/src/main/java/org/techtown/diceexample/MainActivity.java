package org.techtown.diceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView roll_result;
    private Button button_roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        button_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int result = random.nextInt(6) + 1;

                roll_result.setText(String.valueOf(result));
            }
        });
    }

    public void init(){
        roll_result = (TextView) findViewById(R.id.roll_result);
        button_roll = (Button) findViewById(R.id.button_roll);
    }
}