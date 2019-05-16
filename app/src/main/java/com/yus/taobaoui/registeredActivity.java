package com.yus.taobaoui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class registeredActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText phone;
    private EditText pwd;
    private EditText account;
    private Button registered;
    private JSONObject object;
    private String URL="http://www.zhouzhihui.cn/app/reg/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_activity);
        initView();
    }
    private void initView(){
        phone=(EditText)findViewById(R.id.re_phone);
        pwd=(EditText)findViewById(R.id.re_pwd);
        account=(EditText)findViewById(R.id.re_acc);
        registered=(Button)findViewById(R.id.zhuce);
        registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            String phone_S=phone.getText().toString();
            String pwd_S=pwd.getText().toString();
            String account_S=account.getText().toString();
            OkHttpClient client=new OkHttpClient();
            object=null;
            object=new JSONObject();
            try {
                object.put("phone",phone_S);
                object.put("password",pwd_S);
                object.put("username",account_S);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),object.toString());
            Request request = new Request.Builder()
                        .url(URL)
                        .post(body)
                        .build();
            Call call=client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message msg=handler.obtainMessage();
                    msg.obj=response.body().string();
                    handler.sendMessage(msg);
                }
            });
            }
        }).start();
    }
    Handler handler=new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg){
            String m = (String) msg.obj;
            try{
                m= URLDecoder.decode(m,"utf-8");
                JSONObject jsonObject=new JSONObject(m);
                String x=jsonObject.getString("status");
                Log.i("xxxxx",x);
                Toast.makeText(registeredActivity.this,x,Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }
    });
}
