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

import com.yus.taobaoui.util.UIUtils;
import com.yus.taobaoui.view.FlyBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.yus.taobaoui.util.UIUtils.getContext;

public class goods_newsActivity extends AppCompatActivity implements View.OnClickListener{
    private List<Integer> watermelonPics;
    private List<Integer> mangoPics;
    private List<Integer> grapePics;
    private List<Integer> pineapplePics;
    private ImageView news_back;
    private ImageView news_firstImg;
    private ImageView news_comment_more;
    private ImageView news_SecondImg;

    private TextView news_title;
    private TextView news_price;
    private TextView news_freight;
    private TextView news_comment_number;
    private TextView news_producing_area;
    private TextView news_time;
    private TextView news_inventory;
    private TextView news_comment;
    private TextView news_username;
    private TextView news_buying_point;

    private Button news_Look_comment;
    private Button news_shopping_cart;
    private Button news_buy;


    private String user_name;
    private int fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_news);
        user_name=getIntent().getStringExtra("user_name");
        initmodule();
        initView();
    }
    private void initmodule(){
        news_back=(ImageView)findViewById(R.id.news_back);
        news_firstImg=(ImageView)findViewById(R.id.news_firstImg);
        news_comment_more=(ImageView)findViewById(R.id.news_comment_more);
        news_SecondImg=(ImageView)findViewById(R.id.news_SecondImg);

        news_title=(TextView)findViewById(R.id.news_title);
        news_price=(TextView)findViewById(R.id.news_price);
        news_freight=(TextView)findViewById(R.id.news_freight);
        news_comment_number=(TextView)findViewById(R.id.news_comment_number);
        news_producing_area=(TextView)findViewById(R.id.news_producing_area);
        news_time=(TextView)findViewById(R.id.news_time);
        news_inventory=(TextView)findViewById(R.id.news_inventory);
        news_comment=(TextView)findViewById(R.id.news_comment);
        news_username=(TextView)findViewById(R.id.news_username);
        news_buying_point=(TextView)findViewById(R.id.news_buying_point);


        news_Look_comment=(Button)findViewById(R.id.news_Look_comment);
        news_shopping_cart=(Button)findViewById(R.id.news_shopping_cart);
        news_buy=(Button)findViewById(R.id.news_buy);

        news_Look_comment.setOnClickListener(this);
        news_buy.setOnClickListener(this);
        news_shopping_cart.setOnClickListener(this);
        news_back.setOnClickListener(this);


    }
    private void setmangoNews(){
        news_firstImg.setImageResource(R.drawable.mango2);
        news_title.setText("越南进口芒果，当季芒果带箱10斤新鲜水果 玉芒香芒果批发包邮");
        news_price.setText("39.8");
        news_freight.setText("20.00");
        news_comment_number.setText("商品评价(2040)");
        news_producing_area.setText("产地：越南");
        news_time.setText("日期：5月、6月、7月");
        news_inventory.setText("总库存：5000kg");
        news_comment.setText("差评，到家芒果都被撞烂了，还不给换，垃圾，差评，***");
        news_username.setText("我爱学习");
        news_SecondImg.setImageResource(R.drawable.mango3);
        news_buying_point.setText("卖点：越南特产/日期新鲜");

    }
    private void setgrapeNews(){
        news_firstImg.setImageResource(R.drawable.grape2);
        news_SecondImg.setImageResource(R.drawable.grape3);
        news_title.setText("当季葡萄，刚采摘的新鲜葡萄带箱10斤批发包邮");
        news_price.setText("60.0");
        news_freight.setText("0.00");
        news_comment_number.setText("商品评价(10000)");
        news_producing_area.setText("产地：广东");
        news_time.setText("日期：6月份新鲜采摘");
        news_inventory.setText("总库存：10000kg");
        news_comment.setText("好吃，很甜，包装保护得很到位");
        news_username.setText("蔡某人");
        news_buying_point.setText("卖点：广东特产/日期新鲜");

    }
    private void setpineappleNews(){
        news_firstImg.setImageResource(R.drawable.pineapple2);
        news_SecondImg.setImageResource(R.drawable.pineapple3);
        news_title.setText("大量批发海南菠萝，包邮");
        news_price.setText("30.0");
        news_freight.setText("0.00");
        news_comment_number.setText("商品评价(253)");
        news_producing_area.setText("产地：海南");
        news_time.setText("日期：5月成熟");
        news_inventory.setText("总库存：60000kg");
        news_comment.setText("水果很不错，很大，很甜，一点也不涩，很好吃");
        news_username.setText("鸡你太美");
        news_buying_point.setText("卖点：海南特产/日期新鲜");

    }
    private void initView(){
        fruit= getIntent().getIntExtra("水果",1);
        switch (fruit){
            case 1:
                init_watermelon();
                break;
            case 2:
                init_mango();
                break;
            case 3:
                init_grape();
                break;
            case 4:
                init_pineapple();
                break;


        }

    }
    private void init_watermelon(){
        watermelon_BigPics();
        initBannerTop(watermelonPics);

    }
    private void init_grape(){
        grape_BigPics();
        initBannerTop(grapePics);
        setgrapeNews();
    }
    private void init_mango(){
        mango_BigPics();
        initBannerTop(mangoPics);
        setmangoNews();
    }
    private void init_pineapple(){
        pineapple_BigPics();
        initBannerTop(pineapplePics);
        setpineappleNews();
    }
    private void watermelon_BigPics() {
        watermelonPics = new ArrayList<>();
        watermelonPics.add(R.drawable.watermelon);
        watermelonPics.add(R.drawable.watermelon1);
        watermelonPics.add(R.drawable.watermelon2);
        watermelonPics.add(R.drawable.watermelon3);
    }
    private void mango_BigPics() {
        mangoPics = new ArrayList<>();
        mangoPics.add(R.drawable.mango);
        mangoPics.add(R.drawable.mango1);
        mangoPics.add(R.drawable.mango2);
        mangoPics.add(R.drawable.mango3);
        mangoPics.add(R.drawable.mango4);
    }
    private void grape_BigPics() {
        grapePics = new ArrayList<>();
        grapePics.add(R.drawable.grape);
        grapePics.add(R.drawable.grape1);
        grapePics.add(R.drawable.grape2);
        grapePics.add(R.drawable.grape3);
        grapePics.add(R.drawable.grape4);
    }
    private void pineapple_BigPics() {
        pineapplePics = new ArrayList<>();
        pineapplePics.add(R.drawable.pineapple);
        pineapplePics.add(R.drawable.pineapple1);
        pineapplePics.add(R.drawable.pineapple2);
        pineapplePics.add(R.drawable.pineapple3);
        pineapplePics.add(R.drawable.pineapple4);
    }

    private void initBannerTop(List<Integer> fruit_list) {
        FlyBanner bannerTop= (FlyBanner)findViewById(R.id.news_bannerTop);
        bannerTop.setImages(fruit_list);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtils.showToast("position--->"+position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_back:
                Intent back=new Intent(goods_newsActivity.this,MainActivity.class);
                startActivity(back);
                finish();
                break;
            case R.id.news_comment_more:
                UIUtils.showToast("没有更多");
                break;
            case R.id.news_Look_comment:
                UIUtils.showToast("没有更多");
                break;
            case R.id.news_shopping_cart:
                if (user_name!=null){
                    UIUtils.showToast("添加成功");
                }else {
                    Intent toLogin=new Intent(goods_newsActivity.this,LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                }
                break;
            case R.id.news_buy:
                if (user_name!=null){
                   Intent toBuy=new Intent(goods_newsActivity.this,BuyActivity.class);
                   toBuy.putExtra("user_name",user_name);
                   toBuy.putExtra("水果",fruit);
                   startActivity(toBuy);
                }else {
                    Intent toLogin=new Intent(goods_newsActivity.this,LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                }

        }
    }

}
