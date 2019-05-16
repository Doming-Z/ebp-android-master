package com.yus.taobaoui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.util.UIUtils;
import com.yus.taobaoui.view.ButtomBtn;
import com.yus.taobaoui.view.DividerGridItemDecoration;
import com.yus.taobaoui.view.FlyBanner;
import com.yus.taobaoui.view.GridMenu;
import com.yus.taobaoui.view.MyImageView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static com.yus.taobaoui.util.UIUtils.getContext;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, RvAdapter.OnItemClickListener {
    /**普通商品的（normalHolder）的标题集合*/
    private List<String> normalGoodsTitls=new ArrayList<>();
    private String[] gridMenuTitles=new String[]{"推荐","大批量","小批量"};
    @BindView(R.id.buttomBtnScan)
    ButtomBtn buttomBtnScan;
    @BindView(R.id.bomBtnMsg)
    ButtomBtn bomBtnMsg;
    @BindView(R.id.bomBtnHome)
    ButtomBtn bomBtnHome;
    @BindView(R.id.bomBtnTiny)
    ButtomBtn bomBtnTiny;
    @BindView(R.id.bomBtnAsk)
    ButtomBtn bomBtnAsk;
    @BindView(R.id.bomBtnShopCar)
    ButtomBtn bomBtnShopCar;
    @BindView(R.id.bomBtnMy)
    ButtomBtn bomBtnMy;
    @BindView(R.id.swp)
    SwipeRefreshLayout swp;
    @BindView(R.id.rv)
    RecyclerView rv;
    private Handler mHandler;
    private List<Integer> bigPics;
    public String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNormalGoodsTitls();
        user_name=getIntent().getStringExtra("user_name");
        initBigPics();
        mHandler = new Handler();
        initTopBtn();
        initBomBtn();
        initRv();
    }

    private void initNormalGoodsTitls() {
        //6个
//        normalGoodsTitls.add("质男说");
    }


    private void initBigPics() {
        bigPics = new ArrayList<>();
        bigPics.add(R.drawable.refruit1);
        bigPics.add(R.drawable.refruit2);
        bigPics.add(R.drawable.refruit3);
        bigPics.add(R.drawable.refruit4);
        bigPics.add(R.drawable.refruit5 );
    }
    private void initTopBtn() {
        buttomBtnScan.setIvAndTv(R.drawable.scan, "扫一扫");
        buttomBtnScan.setTvColor(Color.WHITE);
        bomBtnMsg.setIvAndTv(R.drawable.comment, "消息");
        bomBtnMsg.setTvColor(Color.WHITE);
    }
    private void initBomBtn() {
        bomBtnHome.setIvAndTv(R.drawable.shouye,"首页");
        bomBtnHome.setTvColor(Color.parseColor("#d81e06"));

        bomBtnTiny.setIvAndTv(R.drawable.fenlei,"分类");
        bomBtnTiny.setTvColor(getResources().getColor(R.color.font33));

        bomBtnAsk.setIvAndTv(R.drawable.renwu,"任务");
        bomBtnAsk.setTvColor(getResources().getColor(R.color.font33));

        bomBtnShopCar.setIvAndTv(R.drawable.huoyun,"货运");
        bomBtnShopCar.setTvColor(getResources().getColor(R.color.font33));

        bomBtnMy.setIvAndTv(R.drawable.my,"我的果燃");
        bomBtnMy.setTvColor(getResources().getColor(R.color.font33));
    }
    private void initRv() {
        swp.setOnRefreshListener(this);
        RvAdapter rvAdapter = new RvAdapter(normalGoodsTitls);
        rvAdapter.setmOnItemClickLitener(this);

        initBannerTop(rvAdapter);
        initGridMenu(rvAdapter);
        initHotGoodsShow(rvAdapter);
        initSnapUp(rvAdapter);
        initHotGoods(rvAdapter);
        initHotMarket(rvAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion == 0) {
                    return 2;
                }else if(postion==1){
                    return 2;
                }else if (postion==2){
                    return 2;
                }else if (postion==3){
                    return 2;
                }else if (postion==4){
                    return 2;
                }else if (postion==5){
                    return 2;
                }else if (postion==6){
                    return 2;
                }
                else {
                    return 1;
                }
            }
        });
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(rvAdapter);
        rv.addItemDecoration(new DividerGridItemDecoration(this));

    }
    private void initBannerTop(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(getContext(), R.layout.banner_top, null);
        FlyBanner bannerTop= (FlyBanner) bannerBigView.findViewById(R.id.bannerTop);
        rvAdapter.addHeadView0(bannerBigView);
        bannerTop.setImages(bigPics);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtils.showToast("position--->"+position);
            }
        });
    }
    /**初始化3个子菜单*/
    private void initGridMenu(RvAdapter rvAdapter) {
        View gridMenu = View.inflate(getContext(), R.layout.grid_menu_10, null);

        List<GridMenu> gridMenus=new ArrayList<>();
        GridMenu gridCat= (GridMenu) gridMenu.findViewById(R.id.gridCat);
        GridMenu gridJHS= (GridMenu) gridMenu.findViewById(R.id.gridJHS);
        GridMenu gridTMgj= (GridMenu) gridMenu.findViewById(R.id.gridTMgj);

        gridMenus.add(gridCat);
        gridMenus.add(gridJHS);
        gridMenus.add(gridTMgj);

        initGridMenuAttr(gridMenus);
        initOnCLick(gridMenus);
        rvAdapter.addHeaderView1(gridMenu);

    }
    private void initHotGoodsShow(RvAdapter rvAdapter){
        View HotGoodsShowView=View.inflate(getContext(),R.layout.hotgoods_show,null);
        HotGoodsShowView.findViewById(R.id.hg_qtg).setOnClickListener(this);
        HotGoodsShowView.findViewById(R.id.hg_yhh).setOnClickListener(this);
        HotGoodsShowView.findViewById(R.id.hg_agj).setOnClickListener(this);
        HotGoodsShowView.findViewById(R.id.hg_bmqd).setOnClickListener(this);


        rvAdapter.addHotGoodsShowView2(HotGoodsShowView);
    }
    //**初始化抢购*//*
    private void initSnapUp(RvAdapter rvAdapter) {
        View snapUpView = View.inflate(getContext(), R.layout.snapup_layout, null);
        snapUpView.findViewById(R.id.ll_qtg).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_yhh).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_agj).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_bmqd).setOnClickListener(this);
        rvAdapter.addHeaderView3(snapUpView);
    }
    private void initHotGoods(RvAdapter rvAdapter){
        View hotgoodstView= View.inflate(getContext(),R.layout.hot_goods,null);
        hotgoodstView.findViewById(R.id.hg_hotMarket).setOnClickListener(this);
        rvAdapter.addHotGoodsView4(hotgoodstView);
    }
    private void initHotMarket(RvAdapter rvAdapter) {
        View hotMarketView = View.inflate(getContext(), R.layout.hot_market, null);
        hotMarketView.findViewById(R.id.rl_hotMarket).setOnClickListener(this);
        rvAdapter.addHeaderView5(hotMarketView);
    }
    /**设置3个子菜单的图片和文字*/
    private void initGridMenuAttr(List<GridMenu> gridMenus) {
        for (int i = 0; i < gridMenus.size(); i++) {
            GridMenu gridMenu = gridMenus.get(i);
            gridMenu.setAttr(gridMenuTitles[i]);
        }
    }
    private void initOnCLick(List<GridMenu> gridMenus) {
        for (GridMenu gridMenu : gridMenus) {
            gridMenu.setOnClickListener(this);
        }
    }
    @OnClick(R.id.buttomBtnScan)
    public void clickScan(View v) {
        UIUtils.showToast("扫一扫");
    }

    @OnClick(R.id.bomBtnMsg)
    public void clickMsg(View v) {
        UIUtils.showToast("消息");
    }

    @OnClick(R.id.ll_search)
    public void clickSearch(View v) {
        UIUtils.showToast("搜索");
    }

    @OnClick(R.id.iv_photo)
    public void clickPhoto(View v) {
        UIUtils.showToast("拍照");
    }


    @OnClick(R.id.bomBtnHome)
    public void bomBtnHome(View v) {
        UIUtils.showToast("首页");
    }
    @OnClick(R.id.bomBtnTiny)
    public void bomBtnTiny(View v) {
        UIUtils.showToast("分类");
    }
    @OnClick(R.id.bomBtnAsk)
    public void bomBtnAsk(View v) {
        Intent toTask=new Intent(UIUtils.getContext(),TaskActivity.class);
        toTask.putExtra("account",user_name);
        startActivity(toTask);
    }
    @OnClick(R.id.bomBtnShopCar)
    public void bomBtnShopCar(View v) {
        UIUtils.showToast("货运");
    }
    @OnClick(R.id.bomBtnMy)
    public void bomBtnMy(View v) {
        Intent toMy=new Intent(UIUtils.getContext(),MyActivity.class);
        startActivity(toMy);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swp.setRefreshing(false);
            }
        }, 1500);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gridCat://推荐
                UIUtils.showToast(gridMenuTitles[0]);
                 break;
            case R.id.gridJHS://大批量
                UIUtils.showToast(gridMenuTitles[1]);
                break;
            case R.id.gridTMgj://小批量
                UIUtils.showToast(gridMenuTitles[2]);
                break;
            case R.id.hg_qtg:
                Intent watermelon=new Intent(MainActivity.this,goods_newsActivity.class);
                watermelon.putExtra("水果",1);//西瓜
                watermelon.putExtra("user_name",user_name);
                Log.i("Main",user_name+"");
                startActivity(watermelon);
                break;
            case R.id.hg_yhh:
                Intent mango=new Intent(MainActivity.this,goods_newsActivity.class);
                mango.putExtra("水果",2);//芒果
                mango.putExtra("user_name",getIntent().getStringExtra("user_name"));
                startActivity(mango);
                break;
            case R.id.hg_agj:
                Intent grape=new Intent(MainActivity.this,goods_newsActivity.class);
                grape.putExtra("水果",3);//草莓
                grape.putExtra("user_name",user_name);
                startActivity(grape);
                break;
            case R.id.hg_bmqd:
                Intent pineapple=new Intent(MainActivity.this,goods_newsActivity.class);
                pineapple.putExtra("水果",4);//香蕉
                pineapple.putExtra("user_name",getIntent().getStringExtra("user_name"));
                startActivity(pineapple);
                break;
            case R.id.ll_qtg:
                UIUtils.showToast("苹果");
                break;
            case R.id.ll_yhh:
                UIUtils.showToast("货车");
                break;
            case R.id.ll_agj:
                UIUtils.showToast("货车");
                break;
            case R.id.ll_bmqd:
                UIUtils.showToast("苹果");
                break;
        }
    }

    @Override
    public void onItemClick(View v, int postion) {
        UIUtils.showToast("item click postion "+postion);
    }

    @Override
    public void onItemLongClick(View v, int postion) {
        UIUtils.showToast("item long click postion "+postion);

    }
}
