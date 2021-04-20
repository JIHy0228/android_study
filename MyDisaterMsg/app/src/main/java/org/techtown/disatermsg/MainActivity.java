
package org.techtown.disatermsg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> adspin1, adspin2;

    private ArrayList<Row> rowData;
    private DisaterAdapter disaterAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public Row row;

    Button morebtn;
    int pageNo = 1;


    static RequestQueue requestQueue;



    String key = "pPaSpIZ%2BXFweoQb0rmHH5gguuqHRO00DHw7CgOuW9wZ2c5HDm%2BwqWpv%2B29V9NIHAcggmnJz3ztzM8206Hkkw7A%3D%3D";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        rowData = new ArrayList<>();

        disaterAdapter = new DisaterAdapter(rowData);
        recyclerView.setAdapter(disaterAdapter);

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());

            sendRequest(pageNo);

        }

        morebtn = findViewById(R.id.morebtn);
        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNo++;
                sendRequest(pageNo);
            }
        });
    }
    public void sendRequest(int pageNo) {

        String url = "http://apis.data.go.kr/1741000/DisasterMsg3/getDisasterMsg1List?serviceKey="+key+"&numOfRows=1000&pageNo="+pageNo;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Button button = findViewById(R.id.button);

                        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
                        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);

                        adspin1 = ArrayAdapter.createFromResource(MainActivity.this, R.array.sido, android.R.layout.simple_spinner_dropdown_item);
                        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spin1.setAdapter(adspin1);
                        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                if (adspin1.getItem(i).equals("전체")) {
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.all, android.R.layout.simple_spinner_dropdown_item);

                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    All(response);
                                                }
                                            });
                                        }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("서울")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.seoul, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) seoulAll(response);
                                                    else if(str.equals("강남구")) seoul_gangnam(response);
                                                    else if(str.equals("강동구")) seoul_gangdong(response);
                                                    else if(str.equals("강북구")) seoul_gangbuk(response);
                                                    else if(str.equals("강서구"))seoul_gangseo(response);
                                                    else if(str.equals("관악구")) seoul_gwanak(response);
                                                    else if(str.equals("광진구")) seoul_gwangin(response);
                                                    else if(str.equals("구로구")) seoul_guro(response);
                                                    else if(str.equals("금천구")) seoul_geumcheon(response);
                                                    else if(str.equals("노원구")) seoul_nowon(response);
                                                    else if(str.equals("도봉구")) seoul_dobong(response);
                                                    else if(str.equals("동대문구")) seoul_dongdaemun(response);
                                                    else if(str.equals("동작구")) seoul_dongjak(response);
                                                    else if(str.equals("마포구")) seoul_mapo(response);
                                                    else if(str.equals("서대문구")) seoul_seodaemun(response);
                                                    else if(str.equals("서초구")) seoul_seocho(response);
                                                    else if(str.equals("성동구")) seoul_seongdong(response);
                                                    else if(str.equals("성북구")) seoul_seongbuk(response);
                                                    else if(str.equals("송파구")) seoul_songpa(response);
                                                    else if(str.equals("양천구")) seoul_yangcheon(response);
                                                    else if(str.equals("영등포구")) seoul_yeongdeungpo(response);
                                                    else if(str.equals("용산구")) seoul_yongsan(response);
                                                    else if(str.equals("은평구")) seoul_eunpyeong(response);
                                                    else if(str.equals("종로구")) seoul_jongro(response);
                                                    else if(str.equals("중구")) seoul_junggu(response);
                                                    else if(str.equals("중랑구")) seoul_jungrang(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("부산")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.busan, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) busanAll(response);
                                                    else if(str.equals("강서구")) busan_gangseo(response);
                                                    else if(str.equals("금정구")) busan_geumjeong(response);
                                                    else if(str.equals("기장군")) busan_gijang(response);
                                                    else if(str.equals("남구")) busan_namgu(response);
                                                    else if(str.equals("동구")) busan_donggu(response);
                                                    else if(str.equals("동래구")) busan_dongrae(response);
                                                    else if(str.equals("부산진구")) busan_busanjin(response);
                                                    else if(str.equals("북구")) busan_bukgu(response);
                                                    else if(str.equals("사상구")) busan_sasang(response);
                                                    else if(str.equals("사하구")) busan_saha(response);
                                                    else if(str.equals("서구")) busan_seogu(response);
                                                    else if(str.equals("수영구")) busan_suyeong(response);
                                                    else if(str.equals("연제구")) busan_yeonjae(response);
                                                    else if(str.equals("영도구")) busan_yeongdo(response);
                                                    else if(str.equals("중구")) busan_junggu(response);
                                                    else if(str.equals("해운대구")) busan_haeeundae(response);

                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("대구")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.daegu, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) daeguAll(response);
                                                    else if(str.equals("남구")) daegu_namgu(response);
                                                    else if(str.equals("달서구")) daegu_dalseo(response);
                                                    else if(str.equals("달성군")) daegu_dalseong(response);
                                                    else if(str.equals("동구")) daegu_donggu(response);
                                                    else if(str.equals("북구")) daegu_bukgu(response);
                                                    else if(str.equals("서구")) daegu_seogu(response);
                                                    else if(str.equals("수성구")) daegu_susung(response);
                                                    else if(str.equals("중구")) daegu_junggu(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("인천")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.incheon, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) incheonAll(response);
                                                    else if(str.equals("강화군")) incheon_ganghwa(response);
                                                    else if(str.equals("계양구")) incheon_gaeyang(response);
                                                    else if(str.equals("미추홀구")) incheon_michuhol(response);
                                                    else if(str.equals("남동구")) incheon_namdong(response);
                                                    else if(str.equals("동구"))incheon_donggu(response);
                                                    else if(str.equals("부평구")) incheon_bupyeong(response);
                                                    else if(str.equals("서구")) incheon_seogu(response);
                                                    else if(str.equals("연수구")) incheon_yeonsu(response);
                                                    else if(str.equals("옹진군")) incheon_ongjin(response);
                                                    else if(str.equals("중구")) incheon_junggu(response);
                                                }
                                            });

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("광주")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.gwangju, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) gwangjuAll(response);
                                                    else if(str.equals("광산구")) gwangju_gwangsan(response);
                                                    else if(str.equals("남구")) gwangju_namgu(response);
                                                    else if(str.equals("동구")) gwangju_donggu(response);
                                                    else if(str.equals("북구")) gwangju_bukgu(response);
                                                    else if(str.equals("서구")) gwangju_seogu(response);

                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("대전")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.daejeon, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) daejeonAll(response);
                                                    else if(str.equals("대덕구")) daejeon_daedeok(response);
                                                    else if(str.equals("동구")) daejeon_donggu(response);
                                                    else if(str.equals("서구")) daejeon_seogu(response);
                                                    else if(str.equals("유성구")) daejeon_yuseong(response);
                                                    else if(str.equals("중구")) daejeon_junggu(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("울산")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.ulsan, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) ulsannAll(response);
                                                    else if(str.equals("남구")) ulsan_namgu(response);
                                                    else if(str.equals("동구"))  ulsan_donggu(response);
                                                    else if(str.equals("북구"))  ulsan_bukgu(response);
                                                    else if(str.equals("울주군"))  ulsan_ulju(response);
                                                    else if(str.equals("중구"))  ulsan_junggu(response);

                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("세종")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sejong, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    sejongAll(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("경기")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) gyeonggiAll(response);
                                                    else if(str.equals("가평군")) gyeonggi_gapyeong(response);
                                                    else if(str.equals("고양시")) gyeonggi_goyang(response);
                                                    else if(str.equals("과천시")) gyeonggi_gwacheon(response);
                                                    else if(str.equals("광명시")) gyeonggi_gwangmyeong(response);
                                                    else if(str.equals("광주시")) gyeonggi_gwangju(response);
                                                    else if(str.equals("구리시")) gyeonggi_guri(response);
                                                    else if(str.equals("군포시"))gyeonggi_gunpo(response);
                                                    else if(str.equals("김포시"))gyeonggi_gimpo(response);
                                                    else if(str.equals("남양주시"))gyeonggi_namyangju(response);
                                                    else if(str.equals("동두천시"))gyeonggi_dongducheon(response);
                                                    else if(str.equals("부천시"))gyeonggi_bucheon(response);
                                                    else if(str.equals("성남시"))gyeonggi_seongnam(response);
                                                    else if(str.equals("수원시"))gyeonggi_suwon(response);
                                                    else if(str.equals("시흥시"))gyeonggi_siheung(response);
                                                    else if(str.equals("안산시"))gyeonggi_ansan(response);
                                                    else if(str.equals("안성시"))gyeonggi_anseong(response);
                                                    else if(str.equals("안양시"))gyeonggi_anyang(response);
                                                    else if(str.equals("양주시"))gyeonggi_yangju(response);
                                                    else if(str.equals("양평군"))gyeonggi_yangpyeong(response);
                                                    else if(str.equals("여주시"))gyeonggi_yeoju(response);
                                                    else if(str.equals("연천군"))gyeonggi_yeoncheon(response);
                                                    else if(str.equals("오산시"))gyeonggi_osan(response);
                                                    else if(str.equals("용인시"))gyeonggi_yongin(response);
                                                    else if(str.equals("의왕시"))gyeonggi_uiwang(response);
                                                    else if(str.equals("의정부시"))gyeonggi_uijeongbu(response);
                                                    else if(str.equals("이천시"))gyeonggi_icheon(response);
                                                    else if(str.equals("파주시"))gyeonggi_paju(response);
                                                    else if(str.equals("평택시"))gyeonggi_pyeongtaek(response);
                                                    else if(str.equals("포천시"))gyeonggi_pocheon(response);
                                                    else if(str.equals("하남시"))gyeonggi_hanam(response);
                                                    else if(str.equals("화성시"))gyeonggi_hwaseong(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("강원")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.gangwon, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) gangwonAll(response);
                                                    else if(str.equals("강릉시")) gangwon_gangneung(response);
                                                    else if(str.equals("고성군")) gangwon_gosung(response);
                                                    else if(str.equals("동해시")) gangwon_donghae(response);
                                                    else if(str.equals("삼척시")) gangwon_samcheok(response);
                                                    else if(str.equals("속초시")) gangwon_sokcho(response);
                                                    else if(str.equals("양구군")) gangwon_yanggu(response);
                                                    else if(str.equals("양양군")) gangwon_yangyang(response);
                                                    else if(str.equals("영월군")) gangwon_yeongwol(response);
                                                    else if(str.equals("원주시")) gangwon_wonju(response);
                                                    else if(str.equals("인제군")) gangwon_inje(response);
                                                    else if(str.equals("정선군")) gangwon_jeongsun(response);
                                                    else if(str.equals("철원군")) gangwon_cheorwon(response);
                                                    else if(str.equals("춘천시")) gangwon_chuncheon(response);
                                                    else if(str.equals("태백시")) gangwon_taebaek(response);
                                                    else if(str.equals("평창군")) gangwon_pyeongchang(response);
                                                    else if(str.equals("홍천군")) gangwon_hongcheon(response);
                                                    else if(str.equals("화천군")) gangwon_hwacheon(response);
                                                    else if(str.equals("횡성군")) gangwon_hoengseong(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("충북")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.chungbuk, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) chungbukAll(response);
                                                    else if(str.equals("괴산군")) chungbuk_goesan(response);
                                                    else if(str.equals("단양군")) chungbuk_danyang(response);
                                                    else if(str.equals("보은군")) chungbuk_boeun(response);
                                                    else if(str.equals("영동군")) chungbuk_yeongdong(response);
                                                    else if(str.equals("옥천군")) chungbuk_okcheon(response);
                                                    else if(str.equals("음성군")) chungbuk_eumseong(response);
                                                    else if(str.equals("제천시")) chungbuk_jaecheon(response);
                                                    else if(str.equals("증평군")) chungbuk_jeungpyeong(response);
                                                    else if(str.equals("진천시")) chungbuk_jincheon(response);
                                                    else if(str.equals("청주시")) chungbuk_cheongju(response);
                                                    else if(str.equals("충주시")) chungbuk_chungju(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("충남")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.chungnam, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) chungnamAll(response);
                                                    else if(str.equals("계룡시")) chungnam_gyeryong(response);
                                                    else if(str.equals("공주시")) chungnam_gongju(response);
                                                    else if(str.equals("금산군")) chungnam_geumsan(response);
                                                    else if(str.equals("논산시")) chungnam_nonsan(response);
                                                    else if(str.equals("당진시")) chungnam_dangjin(response);
                                                    else if(str.equals("보령시")) chungnam_boryeong(response);
                                                    else if(str.equals("부여군")) chungnam_buyeo(response);
                                                    else if(str.equals("서산시")) chungnam_seosan(response);
                                                    else if(str.equals("서천군")) chungnam_seocheon(response);
                                                    else if(str.equals("아산시")) chungnam_asan(response);
                                                    else if(str.equals("예산군")) chungnam_yaesan(response);
                                                    else if(str.equals("천안시")) chungnam_cheonan(response);
                                                    else if(str.equals("청양군")) chungnam_chungyang(response);
                                                    else if(str.equals("태안군")) chungnam_taean(response);
                                                    else if(str.equals("홍성군")) chungnam_hongseong(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("전북")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.jeonbuk, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) jeonbukAll(response);
                                                    else if(str.equals("고창군")) jeonbuk_gochang(response);
                                                    else if(str.equals("군산시")) jeonbuk_gunsan(response);
                                                    else if(str.equals("김제시")) jeonbuk_gimjae(response);
                                                    else if(str.equals("남원시")) jeonbuk_namwon(response);
                                                    else if(str.equals("무주군")) jeonbuk_muju(response);
                                                    else if(str.equals("부안군")) jeonbuk_buan(response);
                                                    else if(str.equals("순창군")) jeonbuk_sunchang(response);
                                                    else if(str.equals("완주군")) jeonbuk_wanju(response);
                                                    else if(str.equals("익산시")) jeonbuk_iksan(response);
                                                    else if(str.equals("임실군")) jeonbuk_imsil(response);
                                                    else if(str.equals("장수군")) jeonbuk_jangsu(response);
                                                    else if(str.equals("전주시")) jeonbuk_jeonju(response);
                                                    else if(str.equals("정읍시")) jeonbuk_jeongeup(response);
                                                    else if(str.equals("진안군")) jeonbuk_jinan(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("전남")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.jeonnam, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) jeonnamAll(response);
                                                    else if(str.equals("강진군")) jeonnam_gangjin(response);
                                                    else if(str.equals("고흥군")) jeonnam_goheung(response);
                                                    else if(str.equals("곡성군")) jeonnam_gokseung(response);
                                                    else if(str.equals("광양시")) jeonnam_gwangyang(response);
                                                    else if(str.equals("구례군")) jeonnam_gurae(response);
                                                    else if(str.equals("나주시")) jeonnam_naju(response);
                                                    else if(str.equals("담양군")) jeonnam_damyang(response);
                                                    else if(str.equals("목포시")) jeonnam_mokpo(response);
                                                    else if(str.equals("무안군")) jeonnam_muan(response);
                                                    else if(str.equals("보성군")) jeonnam_boseung(response);
                                                    else if(str.equals("순천시")) jeonnam_suncheon(response);
                                                    else if(str.equals("신안군")) jeonnam_sinan(response);
                                                    else if(str.equals("여수시")) jeonnam_yeosu(response);
                                                    else if(str.equals("영광군")) jeonnam_yeonggwang(response);
                                                    else if(str.equals("영암군")) jeonnam_yeongam(response);
                                                    else if(str.equals("완도군")) jeonnam_wando(response);
                                                    else if(str.equals("장성군")) jeonnam_jangseung(response);
                                                    else if(str.equals("장흥군")) jeonnam_jangheung(response);
                                                    else if(str.equals("진도군")) jeonnam_jindo(response);
                                                    else if(str.equals("함평군")) jeonnam_hampyeong(response);
                                                    else if(str.equals("해남군")) jeonnam_haenam(response);
                                                    else if(str.equals("화순군")) jeonnam_hwasun(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                else if(adspin1.getItem(i).equals("경북")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.gyeongbuk, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) gyeongbukAll(response);
                                                    else if(str.equals("경산시")) gyeongbuk_gyeongsan(response);
                                                    else if(str.equals("경주시")) gyeongbuk_gyeongju(response);
                                                    else if(str.equals("고령군")) gyeongbuk_goryeong(response);
                                                    else if(str.equals("구미시")) gyeongbuk_gumi(response);
                                                    else if(str.equals("군위군")) gyeongbuk_gunwi(response);
                                                    else if(str.equals("김천시")) gyeongbuk_gimcheon(response);
                                                    else if(str.equals("문경시")) gyeongbuk_mungyeong(response);
                                                    else if(str.equals("봉화군")) gyeongbuk_bonghwa(response);
                                                    else if(str.equals("상주시")) gyeongbuk_sangju(response);
                                                    else if(str.equals("성주군")) gyeongbuk_seungju(response);
                                                    else if(str.equals("안동시")) gyeongbuk_andong(response);
                                                    else if(str.equals("영덕군")) gyeongbuk_yeongdeok(response);
                                                    else if(str.equals("영양군")) gyeongbuk_yeongyang(response);
                                                    else if(str.equals("영주시")) gyeongbuk_yeongju(response);
                                                    else if(str.equals("영천시")) gyeongbuk_yeongcheon(response);
                                                    else if(str.equals("예천군")) gyeongbuk_yaecheon(response);
                                                    else if(str.equals("울릉군")) gyeongbuk_ulleung(response);
                                                    else if(str.equals("울진군")) gyeongbuk_uljin(response);
                                                    else if(str.equals("의성군")) gyeongbuk_jusoen(response);
                                                    else if(str.equals("청도군")) gyeongbuk_chungdo(response);
                                                    else if(str.equals("청송군")) gyeongbuk_chungsong(response);
                                                    else if(str.equals("칠곡군")) gyeongbuk_chilgok(response);
                                                    else if(str.equals("포항시")) gyeongbuk_pohang(response);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("경남")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.gyeonnam, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) gyeongnamAll(response);
                                                    else if(str.equals("거제시")) gyeongnam_geoje(response);
                                                    else if(str.equals("거창군")) gyeongnam_geochang(response);
                                                    else if(str.equals("고성군")) gyeongnam_goseong(response);
                                                    else if(str.equals("김해시")) gyeongnam_gimhae(response);
                                                    else if(str.equals("남해군")) gyeongnam_namhae(response);
                                                    else if(str.equals("밀양시")) gyeongnam_milyang(response);
                                                    else if(str.equals("사천시")) gyeongnam_sacheon(response);
                                                    else if(str.equals("산청군")) gyeongnam_sancheong(response);
                                                    else if(str.equals("양산시")) gyeongnam_yangsan(response);
                                                    else if(str.equals("의령군")) gyeongnam_uiryeong(response);
                                                    else if(str.equals("진주시")) gyeongnam_jinju(response);
                                                    else if(str.equals("창녕군")) gyeongnam_changnyeong(response);
                                                    else if(str.equals("창원시")) gyeongnam_changwon(response);
                                                    else if(str.equals("통영시")) gyeongnam_tongyeong(response);
                                                    else if(str.equals("하동군")) gyeongnam_hadong(response);
                                                    else if(str.equals("함안군")) gyeongnam_haman(response);
                                                    else if(str.equals("함양군")) gyeongnam_hamyang(response);
                                                    else if(str.equals("합천군")) gyeongnam_habcheon(response);
                                                }
                                            });
                                        }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                else if(adspin1.getItem(i).equals("제주")){
                                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.jeju, android.R.layout.simple_spinner_dropdown_item);
                                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spin2.setAdapter(adspin2);
                                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                            disaterAdapter.Clear();
                                            button.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String str = adspin2.getItem(i).toString();
                                                    if(str.equals("전체")) jejuAll(response);
                                                    else if(str.equals("서귀포시")) jeju_seogwipo(response);
                                                    else if(str.equals("제주시")) jeju_jeju(response);
                                                }
                                            });
                                        }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }
    public void All(String response) {

        ArrayList arrayList = new ArrayList<>();

        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            disaterAdapter.addItem(row);
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*서울*/
    public void seoulAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_gangnam(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("137")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_gangdong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("138")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_gangbuk(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("139")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_gangseo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("140")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_gwanak(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("141")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_gwangin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("142")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_guro(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("143")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_geumcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("144")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_nowon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("145")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_dobong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("146")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_dongdaemun(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("147")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_dongjak(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("148")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_mapo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("149")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_seodaemun(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("150")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_seocho(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("151")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_seongdong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("152")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_seongbuk(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("153")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_songpa(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("154")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_yangcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("148")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_yeongdeungpo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("156")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_yongsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("157")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_eunpyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("158")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_jongro(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("159")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_junggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("160")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void seoul_jungrang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("136") || row.location_id.equals("161")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*부산*/
    public void busanAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_gangseo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("120")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_geumjeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("121")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_gijang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("122")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_namgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("123")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_donggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("124")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_dongrae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("125")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_busanjin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("126")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_bukgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("127")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_sasang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("128")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_saha(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("129")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_seogu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("130")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_suyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("131")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_yeonjae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("132")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_yeongdo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("133")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_junggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("134")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void busan_haeeundae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("119") || row.location_id.equals("135")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*대구*/
    public void daeguAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_namgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("105")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_dalseo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("106")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_dalseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("107")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_donggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("108")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_bukgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("109")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_seogu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("110")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_susung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("111")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daegu_junggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("104") || row.location_id.equals("112")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*인천*/
    public void incheonAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_ganghwa(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("169")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_gaeyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("170")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_michuhol(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("171")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_namdong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("172")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_donggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("173")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_bupyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("174")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_seogu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("175")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_yeonsu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("176")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_ongjin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("177")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void incheon_junggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("168") || row.location_id.equals("178")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*광주*/
    public void gwangjuAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("98")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gwangju_gwangsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("98") || row.location_id.equals("99")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gwangju_namgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("98") || row.location_id.equals("100")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gwangju_donggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("98") || row.location_id.equals("101")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gwangju_bukgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("98") || row.location_id.equals("102")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gwangju_seogu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("98") || row.location_id.equals("103")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*대전*/
    public void daejeonAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("113")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daejeon_daedeok(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("113") || row.location_id.equals("114")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daejeon_donggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("113") || row.location_id.equals("115")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daejeon_seogu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("113") || row.location_id.equals("116")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daejeon_yuseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("113") || row.location_id.equals("117")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void daejeon_junggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("113") || row.location_id.equals("118")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*울산*/
    public void ulsannAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("162")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void ulsan_namgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("162") || row.location_id.equals("163")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void ulsan_donggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("162") || row.location_id.equals("164")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void ulsan_bukgu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("162") || row.location_id.equals("165")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void ulsan_ulju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("162") || row.location_id.equals("166")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void ulsan_junggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("162") || row.location_id.equals("167")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*세종*/
    public void sejongAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("6474")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*경기*/
    public void gyeonggiAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_gapyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("22")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_goyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("23")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_gwacheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("24")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_gwangmyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("25")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_gwangju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("26")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_guri(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("27")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_gunpo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("28")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_gimpo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("29")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_namyangju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("30")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_dongducheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("31")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_bucheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("32")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_seongnam(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("33")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_suwon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("34")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_siheung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("35")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_ansan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("36")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_anseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("37")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_anyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("38")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_yangju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("39")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_yangpyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("40")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_yeoju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("41")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_yeoncheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("42") || row.location_id.equals("6487")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_osan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("43")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_yongin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("44")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_uiwang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("45")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_uijeongbu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("46")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_icheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("47")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_paju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("48") || row.location_id.equals("6487")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_pyeongtaek(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("49")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_pocheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("50")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_hanam(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("51")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeonggi_hwaseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("21") || row.location_id.equals("52")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*강원*/
    public void gangwonAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_gangneung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("3")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_gosung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("4")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_donghae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("5")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_samcheok(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("6")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_sokcho(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("7")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_yanggu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("8")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_yangyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("9")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_yeongwol(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("10")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_wonju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("11")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_inje(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("12")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_jeongsun(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("13")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_cheorwon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("14")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_chuncheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("15")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_taebaek(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("16")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_pyeongchang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("17")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_hongcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("18")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_hwacheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("19")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gangwon_hoengseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("2") || row.location_id.equals("20")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*충북*/
    public void chungbukAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_goesan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("239")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_danyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("240")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_boeun(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("241")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_yeongdong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("242")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_okcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("243")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_eumseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("244")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_jaecheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("245")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_jeungpyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("6406")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_jincheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("246")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_cheongju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("248")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungbuk_chungju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("238") || row.location_id.equals("249")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*충남*/
    public void chungnamAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_gyeryong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("250")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_gongju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("223")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_geumsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("224")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_nonsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("225")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_dangjin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("226")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_boryeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("227")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_buyeo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("228")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_seosan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("229")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_seocheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("230")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_asan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("231")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_yaesan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("233")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_cheonan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("234")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_chungyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("235")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_taean(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("236")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void chungnam_hongseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("222") || row.location_id.equals("237")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*전북*/
    public void jeonbukAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_gochang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("203")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_gunsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("204")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_gimjae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("205")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_namwon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("206")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_muju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("207")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_buan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("208")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_sunchang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("209")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_wanju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("210")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_iksan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("211")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_imsil(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("212")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_jangsu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("213")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_jeonju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("214")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_jeongeup(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("215")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonbuk_jinan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("202") || row.location_id.equals("216")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*전남*/
    public void jeonnamAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_gangjin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("180")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_goheung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("181")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_gokseung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("182")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_gwangyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("183")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_gurae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("184")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_naju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("185")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_damyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("186")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_mokpo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("187")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_muan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("188")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_boseung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("189")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_suncheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("190")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_sinan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("191")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_yeosu(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("192")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_yeonggwang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("193")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_yeongam(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("194")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_wando(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("195")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_jangseung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("196")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_jangheung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("197")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_jindo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("198")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_hampyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("199")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_haenam(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("200")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeonnam_hwasun(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("179") || row.location_id.equals("201")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*경북*/
    public void gyeongbukAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_gyeongsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("75")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_gyeongju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("76")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_goryeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("77")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_gumi(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("78")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_gunwi(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("79")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_gimcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("80")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_mungyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("81")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_bonghwa(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("82")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_sangju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("83")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_seungju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("84")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_andong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("85")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_yeongdeok(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("86")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_yeongyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("87")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_yeongju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("88")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_yeongcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("89")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_yaecheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("90")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_ulleung(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("91")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_uljin(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("92")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_jusoen(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("93")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_chungdo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("94")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_chungsong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("95")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_chilgok(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("96")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongbuk_pohang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("74") || row.location_id.equals("97")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*경남*/
    public void gyeongnamAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_geoje(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("54")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_geochang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("55")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_goseong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("56")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_gimhae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("57")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_namhae(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("58")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_milyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("60")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_sacheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("61")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_sancheong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("62")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_yangsan(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("63")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_uiryeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("64")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_jinju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("65")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_changnyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("67")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_changwon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("68")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_tongyeong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("69")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_hadong(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("70")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_haman(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("71")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_hamyang(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("72")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void gyeongnam_habcheon(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("53") || row.location_id.equals("73")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    /*제주*/
    public void jejuAll(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("217")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeju_seogwipo(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("217") || row.location_id.equals("220")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
    public void jeju_jeju(String response) {
        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(xmlToJson.toJson().toString(), Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);

            if(row.location_id.equals("217") || row.location_id.equals("221")){
                disaterAdapter.addItem(row);
            }
        }
        disaterAdapter.notifyDataSetChanged();
    }
}