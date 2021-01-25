package org.techtown.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    BackgroundTask task;
    ProgressBar progressBar;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
        }

        @Override
        protected void onPreExecute() { // 스레드로 실행하기 전 상태
            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected void onPostExecute(Integer integer) { // 스레드로 실행한 후 상태
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) { /* 스레드로 실행하면서 중간중간 UI를 업데이트를
                                                               하고싶을때 사용*/
            progressBar.setProgress(values[0].intValue());
        }

        // 위 3가지 스레드로 동작하다가 또는 그 전 후 UI를 업데이트 하고 싶을 때 사용
        @Override
        protected Integer doInBackground(Integer... integers) { // 스레드로 실행된 상태 ,,,, 스레드로 동작
            while (isCancelled() == false) {
                value += 1;
                if (value >= 100) {
                    break;
                }
                publishProgress(value); //publish에서 onProgressUpdate를 호출함
                try {
                    Thread.sleep(100);
                } catch (Exception E) {
                }
            }
            return value;
        }
    }
}