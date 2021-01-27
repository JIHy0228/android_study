package org.techtown.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRecord();
            }
        });
    }

    public void createDatabase(String databaseName) {
       println("creadeDatabase 호출됨");

       try {
           database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
           println("데이터베이스 생성됨 : " + databaseName);
       }catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void createTable(String tableName){
        println("createTable 호출됨");
       try {
           if (database == null) {
               println("데이터베이스를 먼저 생성하세요");
               return;
           }

           String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
           database.execSQL(sql);
           println("테이블 생성됨 : " + tableName);
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    public void insertRecord() {
        println("insertRecord 호출됨");
            try {
                if (database == null) {
                    println("데이터베이스를 먼저 생성하세요.");
                    return;
                }

                String tableName = editText2.getText().toString();
                if (tableName == null) {
                    println("테이블 이름을 입력하세요.");
                    return;
                }

                String sql = "insert into " + tableName + "(name, age, mobile) values ('john', 20, '010-0000-0000')";
                database.execSQL(sql);
                println("레코드 추가함");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}