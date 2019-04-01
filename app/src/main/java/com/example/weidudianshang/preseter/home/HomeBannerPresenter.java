package com.example.weidudianshang.preseter.home;

import com.example.weidudianshang.bean.home.HomeBannerBean;
import com.example.weidudianshang.model.home.HomeBannerModel;
import com.example.weidudianshang.preseter.BasePresenter;
import com.example.weidudianshang.view.home.HomeBannerView;

public class HomeBannerPresenter extends BasePresenter<HomeBannerView> {

    private final HomeBannerView homeBannerView;
    private final HomeBannerModel homeBannerModel;

    public HomeBannerPresenter(HomeBannerView view){
        this.homeBannerView=view;
        homeBannerModel=new HomeBannerModel();
    }

    public void onRelated(){
        homeBannerModel.getHttpData();
        homeBannerModel.setHomeBannerListener(new HomeBannerModel.onHomeBannerListener() {
            @Override
            public void onHomeBanner(HomeBannerBean homeBannerBean) {
                homeBannerView.getHomeBannerData(homeBannerBean);
            }
        });

    }


}