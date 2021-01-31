package org.techtown.mylocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // locationManager 객체 참조하기
        try{
            //  1 단계 : 위치 관리자 객체 참조하기
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 이전에 확인했던 위치 정보 가져오기
            if(location != null) {
                double latitude = location.getLatitude(); // 위도
                double longitude = location.getLongitude(); // 경도
                String message = "최근 위치 -> \n경도 : " + latitude + "\n위도 :" + longitude;

                textView.setText(message);
            }

            // 내위치 확인
            GPSListener gpsListener = new GPSListener(); // 리스너 객체 생성
            long minTime = 10000; // 최소 10 초마다 갱신
            float minDistance = 0; // 최소 0의 거리만큼 갱신

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            Toast.makeText(getApplicationContext(), "내 위치확인 요청함", Toast.LENGTH_SHORT).show();


        }catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    // 2단계 위치 리스너 구현하기
    class GPSListener implements LocationListener{
        @Override
        public void onLocationChanged(@NonNull Location location) {
            // 위치가 확인되었을때 자동으로 호출되는 onLocationChanged() 메서드
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String message = "내 위치 -> \n 위도 : " + latitude + "\n경도 : " + longitude;

            textView.setText(message);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }

}