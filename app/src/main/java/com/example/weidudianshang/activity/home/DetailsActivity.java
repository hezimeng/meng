package com.example.weidudianshang.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weidudianshang.R;
import com.example.weidudianshang.activity.BaseActivity;
import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.bean.shopping.SynchBean;
import com.example.weidudianshang.bean.home.DetailsBean;
import com.example.weidudianshang.preseter.shopping.QueryPresenter;
import com.example.weidudianshang.preseter.shopping.SynchPresenter;
import com.example.weidudianshang.preseter.home.DetailsPresenter;
import com.example.weidudianshang.view.shopping.QueryView;
import com.example.weidudianshang.view.shopping.SynchView;
import com.example.weidudianshang.view.home.DetailsView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView, QueryView, SynchView {

    @BindView(R.id.return_btu)
    TextView returnBtu;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.price_details)
    TextView priceDetails;
    @BindView(R.id.num_details)
    TextView numDetails;
    @BindView(R.id.details_xp)
    TextView detailsXp;
    @BindView(R.id.details_zl)
    TextView detailsZl;
    @BindView(R.id.spxq)
    TextView spxq;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.l)
    RelativeLayout l;
    @BindView(R.id.goodsContent_product_addcargoodsImg)
    ImageView goodsContentProductAddcargoodsImg;
    @BindView(R.id.goodsContent_product_addbuygoodsImg)
    ImageView goodsContentProductAddbuygoodsImg;
    private DetailsPresenter detailsPresenter;
    private int commodityId;
    private QueryPresenter queryPresenter;
    private List<QueryBean.ResultBean> result;
    private List<Integer> mDataId = new ArrayList<>();
    private SynchPresenter synchPresenter;
    private DetailsBean.ResultBean mResultBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initData() {
        detailsPresenter.onRelated(commodityId);

        queryPresenter.onRelated();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        commodityId = intent.getIntExtra("commodityId", commodityId);
        returnBtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected DetailsPresenter getPresenter() {
        //商品详情P层
        detailsPresenter = new DetailsPresenter(this);
        //获取购物车列表数据
        queryPresenter = new QueryPresenter(this);
        //同步购物车接口
        synchPresenter = new SynchPresenter(this);

        return detailsPresenter;
    }

    @Override
    public void getDetailsData(DetailsBean.ResultBean resultBean) {

        mResultBean = resultBean;

        final List<String> list = new ArrayList<>();

        String picture = resultBean.getPicture();
        String[] split = picture.split(",");


        for (int i = 0; i < split.length; i++) {
            //不是从第一个图片取吗？
            list.add(split[i]);
        }
        banner.setData(list, null);
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailsActivity.this).load(list.get(position)).into((ImageView) view);

            }
        });


        /**
         * 其它赋值
         */
        priceDetails.setText("￥" + resultBean.getPrice());
        numDetails.setText("已售" + resultBean.getStock() + "件");
        detailsXp.setText(resultBean.getCommodityName());
        detailsZl.setText("重量" + resultBean.getCommentNum());

        WebSettings settings = webview.getSettings();
        //webwiew可以执行 脚本
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setBuiltInZoomControls(true);

        String s2 = "<script type=\"text/javascript\">" +

                "var imgs=document.getElementsByTagName('img');" +
                "for(var i = 0; i<imgs.length; i++){" +
                "imgs[i].style.width='100%';" +
                "imgs[i].style.height='auto';" +
                "}" +
                "</script>";

        String details = resultBean.getDetails();
        webview.loadDataWithBaseURL(null, details + s2 + "<html><body>", "text/html", "utf-8", null);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.goodsContent_product_addcargoodsImg, R.id.goodsContent_product_addbuygoodsImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //添加入购物车按钮
            case R.id.goodsContent_product_addcargoodsImg:

                //你的这个接口它属于是更新和添加。 更新的话就是你需要自己做这个判断了，
                // 就是判断result 购物车内所有数据是否存在有当前查看的这个物品

                //result.contains()
                /******************* 一下判断会出现问题所以不能使用 ***********************/
                /*for (int i = 0; i < result.size(); i++) {
                    //判断购物车内存在数据的id和当前商品详情id比较
                    if (result.get(i).getCommodityId() == commodityId) {
                        //如果相等的话 就是更新他里面的数据
                        //首先修改的话肯定有数量
                        int count = result.get(i).getCount();//先获取他原有的数量
                        count++; //执行加一数量操作
                        result.get(i).setCount(count);

                    } else {
                        //否侧购物车内就没有这个数据 执行添加

                    }
                }*/


                /**********  使用这个  **********/
                //判断这个id是在这个集合中存在

                if (mDataId.contains(commodityId)) {
                    //如果存在更新
                    for (int i = 0; i < result.size(); i++) {
                        if (commodityId == result.get(i).getCommodityId()) {
                            int count = result.get(i).getCount();//先获取他原有的数量
                            count++; //执行加一数量操作
                            result.get(i).setCount(count);
                        }
                    }

                } else {
                    int commodityId = mResultBean.getCommodityId();
                    QueryBean.ResultBean resultBean = new QueryBean.ResultBean();
                    resultBean.setCommodityId(commodityId);
                    resultBean.setCount(1);
                    //否侧添加
                    result.add(resultBean);
                }

                Gson gson = new Gson();
                //将集合数据转换为json串
                String mJsonData = gson.toJson(result);
                //可以调用接口了
                Log.d("mJsonData", "onViewClicked: " + mJsonData);
                synchPresenter.onRelated(mJsonData);
                break;
            case R.id.goodsContent_product_addbuygoodsImg:
                break;
        }
    }

    @Override
    public void getQueryData(QueryBean queryBean) {
        if (queryBean != null) {
            result = queryBean.getResult();

            for (QueryBean.ResultBean resultBean : result) {
                mDataId.add(resultBean.getCommodityId());
            }
        }
    }

    @Override
    public void getSynchData(SynchBean synchBean) {
        Toast.makeText(getApplicationContext(), synchBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
