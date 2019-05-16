package com.yus.taobaoui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button my_remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        my_remove=(Button)findViewById(R.id.my_remove);
        my_remove.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_remove:
                Intent toLogin=new Intent(MyActivity.this,LoginActivity.class);
                startActivity(toLogin);
        }
    }
}
