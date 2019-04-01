package com.example.weidudianshang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weidudianshang.R;
import com.example.weidudianshang.activity.HomeActivity;
import com.example.weidudianshang.activity.home.DetailsActivity;
import com.example.weidudianshang.adapter.HomeAdapter;
import com.example.weidudianshang.adapter.home.MLssAdapter;
import com.example.weidudianshang.bean.home.HomeBannerBean;
import com.example.weidudianshang.bean.home.HomeBean;
import com.example.weidudianshang.preseter.home.HomeBannerPresenter;
import com.example.weidudianshang.preseter.home.HomePresenter;
import com.example.weidudianshang.view.HomeMyView;
import com.example.weidudianshang.view.home.HomeBannerView;
import com.example.weidudianshang.view.home.HomeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView, HomeBannerView {
    @BindView(R.id.myview)
    HomeMyView myview;
    Unbinder unbinder1;
    Unbinder unbinder2;
    private Context context;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.rec)
    RecyclerView rec;
    Unbinder unbinder;
    private HomeActivity homeActivity;
    private HomeAdapter homeAdapter;
    private HomeBannerPresenter homeBannerPresenter;
    private List<String> imgList = new ArrayList<>();
    private List<HomeBannerBean.ResultBean> result;


    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void intoview(View view) {
        unbinder = ButterKnife.bind(this, view);
        //GridLayoutManager linearLayoutManager = new GridLayoutManager(context,2);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview禁止滑动
        //这个暂时没有出现那个滑动问题的 如果两个可以滑动的控件的话 你可以吧rec的滑动取消让scrollview进行滑动
        rec.setNestedScrollingEnabled(false); //这个方法就是取消他的滑动事件
        homeBannerPresenter = new HomeBannerPresenter(this);
        homeBannerPresenter.onRelated();

    }

    @Override
    protected HomePresenter getPresenter() {
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.onRelated();
        return homePresenter;
    }


    @Override
    protected void initdata() {

        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                HomeBannerBean.ResultBean resultBean = (HomeBannerBean.ResultBean) model;
                Glide.with(view.getContext())
                        .load(resultBean.getImageUrl())
                        .into((ImageView) view);
            }
        });

    }

    //设置适配器
    private void setAdapter() {
        //写一个判断这个适配器是否创建
        if (homeAdapter == null) {
            homeAdapter = new HomeAdapter(getActivity(), result);
            rec.setAdapter(homeAdapter);
        } else {
            //已创建过则刷新适配器
            homeAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void getHomeData(HomeBean homeBean) {
        Log.i("homebean", homeBean.toString());

        setAdapter();
        homeAdapter.setLiset(homeBean);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getHomeBannerData(HomeBannerBean homeBannerBean) {

        result = homeBannerBean.getResult();
        xbanner.setData(result, null);

    }

}
