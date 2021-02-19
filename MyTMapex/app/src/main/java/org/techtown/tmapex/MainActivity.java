package org.techtown.tmapex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout TMap = (LinearLayout)findViewById(R.id.map_view);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( "l7xxaea19db92e874f8686c483c6f2fefa2c" );
        TMap.addView( tMapView );

        ArrayList<TMapPoint> TMapPoint = new ArrayList<TMapPoint>();
        TMapPoint.add( new TMapPoint(37.570841, 126.985302) ); // SKT타워
        TMapPoint.add( new TMapPoint(37.551135, 126.988205) ); // N서울타워
        TMapPoint.add( new TMapPoint(37.579600, 126.976998) ); // 경복궁

        TMapPolyLine tMapPolyLine = new TMapPolyLine();
        tMapPolyLine.setLineColor(Color.BLUE);
        tMapPolyLine.setLineWidth(2);
        for( int i=0; i<TMapPoint.size(); i++ ) {
            tMapPolyLine.addLinePoint( TMapPoint.get(i) );
        }
        tMapView.addTMapPolyLine("Line1", tMapPolyLine);

    }
}


