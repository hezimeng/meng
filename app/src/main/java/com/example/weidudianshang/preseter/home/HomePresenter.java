package com.example.weidudianshang.preseter.home;

import com.example.weidudianshang.bean.home.HomeBean;
import com.example.weidudianshang.model.home.HomeModel;
import com.example.weidudianshang.preseter.BasePresenter;
import com.example.weidudianshang.view.home.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {

    private final HomeView homeView;
    private final HomeModel homeModel;

    public HomePresenter(HomeView view){
        this.homeView=view;
        homeModel=new HomeModel();
    }

    public void onRelated(){
        homeModel.getHttpData();
        homeModel.setHomeListener(new HomeModel.onHomeListener() {
            @Override
            public void onHome(HomeBean homeBean) {
                homeView.getHomeData(homeBean);
            }
        });

    }


}
