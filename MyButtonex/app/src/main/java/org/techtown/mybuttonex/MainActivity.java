package org.techtown.mybuttonex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Button_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count %3 == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("카운트").setMessage("1");

                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();
                }
                else if (count %3 == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("카운트").setMessage("2");

                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("카운트").setMessage("3");

                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();
                }
                count ++;
            }
        });
    }
}