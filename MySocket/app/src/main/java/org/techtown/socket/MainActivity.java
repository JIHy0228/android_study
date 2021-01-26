package org.techtown.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText input1;
    TextView output1;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = input1.getText().toString(); // final을 사용하여 상수처럼 접근 가능하게 함

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);        // send함수가 Rinnable객체의 코드가 되므로
                    }
                }).start();
            }
        });
        Button startServerButton = findViewById(R.id.startServerButton);
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        startServer();
                    }
                }).start();
            }
        });
    }

    public void startServer(){
        int port = 5001;
        try{
            ServerSocket server = new ServerSocket(port);
            while(true){
                Socket socket = server.accept(); // 서버가 대기중에 클라이언트가 연결될 때 accept가 실행됨
                InetAddress clientHost = socket.getLocalAddress();
                int clientPort = socket.getPort();
                println("클라이언트 연결됨 :" + clientHost + ", " + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                String input = (String) inputStream.readObject();
                println("데이터 받음 : " + input);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(input + "from server.");
                outputStream.flush();
                println("데이터 보냄");

                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void println(final String data) {
         handler.post(new Runnable() {
            @Override
            public void run() {
                output1.append(data + "\n");
            }
        });

    }
    public void send(String data) {
        int port = 5001;
        try {
            Socket socket = new Socket("localhost", port);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String input = (String) inputStream.readObject();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}