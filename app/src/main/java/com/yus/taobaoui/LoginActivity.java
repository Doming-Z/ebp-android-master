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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private String account_S,pwd_S;
    private Button login, registered;
    private JSONObject loginobject;
    private String URL = "http://www.zhouzhihui.cn/app/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Log.i("aaaa","1");
        init();
        Log.i("aaaa","2");
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_registered=new Intent(LoginActivity.this, registeredActivity.class);
                startActivity(intent_registered);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account_S=account.getText().toString().trim();
                pwd_S=password.getText().toString().trim();
                postRequest(account_S,pwd_S);
               /* if (account_S.equals(pwd_S)){
                    Log.i("1111111",account_S+pwd_S);
                    Intent intent2=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent2);
                }*/
            }
        });

    }
    public void init(){
        account=(EditText)findViewById(R.id.Lg_phone);
        registered=(Button) findViewById(R.id.Lg_registered);
        login=(Button)findViewById(R.id.Lg_Login);
        password=(EditText)findViewById(R.id.Lg_password);
    }
    private void postRequest(String account_S,String pwd_S){
        loginobject=new JSONObject();
        try {
            loginobject.put("username",account_S);
            loginobject.put("password",pwd_S);
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
              if (ReturnMessage.equals("200")){
                  Intent intent2=new Intent(LoginActivity.this,MainActivity.class);
                  intent2.putExtra("LoginFlag",true);
                  intent2.putExtra("user_name",account_S);
                  startActivity(intent2);
                  finish();

              }else {
                  if(ReturnMessage.equals("201"))
                  {
                      Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                  }
                  if (ReturnMessage.equals("202"))
                  {
                      Toast.makeText(LoginActivity.this,"账户不存在",Toast.LENGTH_SHORT).show();
                  }

              }
          }
      }
    };

}
