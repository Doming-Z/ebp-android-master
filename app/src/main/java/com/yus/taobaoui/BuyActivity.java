package com.yus.taobaoui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BuyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView buy_image;

    private TextView buy_title;
    private EditText buy_name;

    private EditText buy_number;
    private EditText buy_phone;
    private EditText buy_address;

    private JSONObject loginobject;
    private String URL ="http://www.zhouzhihui.cn/goodsdetail/1/";

    private Button buy_bnt;

    private String phone;
    private String address;
    private String number;
    private String user_name;
    private String name;

    private int fruit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_activity);
        user_name=getIntent().getStringExtra("user_name");
        fruit=getIntent().getIntExtra("水果",1);
        initmodlu();
    }
    private void initmodlu(){
        buy_image=(ImageView)findViewById(R.id.buy_image);

        buy_title=(TextView)findViewById(R.id.buy_title);
        buy_name=(EditText)findViewById(R.id.buy_name);

        buy_number=(EditText)findViewById(R.id.buy_number);
        buy_phone=(EditText)findViewById(R.id.buy_phone);
        buy_address=(EditText)findViewById(R.id.buy_address);

        buy_bnt=(Button)findViewById(R.id.buy_bnt);

        buy_bnt.setOnClickListener(this);
        switch (fruit){
            case 1:
                buy_image.setImageResource(R.drawable.watermelon);
                buy_title.setText("又大又甜的西瓜，包邮包邮包邮");
                break;
            case 2:
                buy_image.setImageResource(R.drawable.mango);
                buy_title.setText("越南进口芒果，新鲜水果 玉芒香芒果批发包邮");
                break;
            case 3:
                buy_image.setImageResource(R.drawable.grape);
                buy_title.setText("当季葡萄，刚采摘的新鲜葡萄带箱10斤批发包邮");
                break;
            case 4:
                buy_image.setImageResource(R.drawable.pineapple);
                buy_title.setText("大量批发海南菠萝，包邮");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_bnt:
                phone=buy_phone.getText().toString().trim();
                address=buy_address.getText().toString().trim();
                number=buy_number.getText().toString().trim();
                name=buy_name.getText().toString().trim();
                postRequest(number,fruit,name,user_name,address,phone);
                break;
        }
    }
    private void postRequest(String number,int fruit,String name,String user_name,String address,String phone){
        loginobject=new JSONObject();
        try {
            loginobject.put("fruit",fruit);
            loginobject.put("user_name",user_name);
            loginobject.put("fruit_number",number);
            loginobject.put("address",address);
            loginobject.put("phone",phone);
            loginobject.put("real_name",name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),loginobject.toString());
        final Request request=new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response=null;
                try {
                    response=client.newCall(request).execute();
                    if (response.isSuccessful()){
                        messageHandler.obtainMessage(1,response.body().string()).sendToTarget();
                    }else {
                        throw new IOException("Unexpected code:"+response);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    final OkHttpClient client=new OkHttpClient();
    private Handler messageHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what==1){
                String ReturnMessage=(String)msg.obj;
                Toast.makeText(BuyActivity.this,ReturnMessage,Toast.LENGTH_SHORT).show();
            }
        }
    };
}
