package com.yus.taobaoui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yus.taobaoui.util.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView task_title;
    private ImageView task_back;
    private TextView  task_paticular_text;
    private EditText task_price;

    private Button task_draft;
    private Button task_publish;

    private String title;
    private String price;
    private String paticular_text;
    private String user_name;

    private JSONObject loginobject;
    private String URL =/*"http://192.168.31.175:8000/task/"*/"http://www.zhouzhihui.cn/goodsdetail/1/";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskactivity_layout);
        user_name=getIntent().getStringExtra("account");
        initlayout();
        }

        private void initlayout(){
            task_title=(EditText)findViewById(R.id.task_title);
            task_back=(ImageView)findViewById(R.id.task_back);
            task_paticular_text=(EditText)findViewById(R.id.task_paticular_text);
            task_price=(EditText) findViewById(R.id.task_price);
            task_draft=(Button)findViewById(R.id.task_draft);
            task_publish=(Button)findViewById(R.id.task_publish);

            task_back.setOnClickListener(this);
            task_draft.setOnClickListener(this);
            task_publish.setOnClickListener(this);
        }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.task_back:
                Intent toMain=new Intent(TaskActivity.this,MainActivity.class);
                startActivity(toMain);
                break;
            case R.id.task_draft:
                if (user_name!=null){
                    UIUtils.showToast("保存成功");
                }else {
                    Intent toLogin=new Intent(TaskActivity.this,LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                }

                break;
            case R.id.task_publish:
                if (user_name!=null){
                    title=task_title.getText().toString().trim();
                    price=task_price.getText().toString().trim();
                    paticular_text=task_paticular_text.toString().trim();
                    postRequest(price,title,paticular_text,user_name);
                }
                else {
                    Intent toLogin=new Intent(TaskActivity.this,LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                }
                break;
        }
    }

    private void postRequest(String price,String title,String paticular_text,String user_name){
        loginobject=new JSONObject();
        try {
            loginobject.put("price",price);
            loginobject.put("title",title);
            loginobject.put("paticular_text",paticular_text);
            loginobject.put("user_name",user_name);
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
                UIUtils.showToast(ReturnMessage);

            }
        }
    };
}
