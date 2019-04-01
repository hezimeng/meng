package com.example.weidudianshang.preseter.shopping;

import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.model.shopping.QueryModel;
import com.example.weidudianshang.preseter.BasePresenter;
import com.example.weidudianshang.view.shopping.QueryView;

public class QueryPresenter extends BasePresenter<QueryView> {

    private QueryView queryView;
    private QueryModel queryModel;


    public QueryPresenter(QueryView view){
        this.queryView=view;
        queryModel=new QueryModel();
    }

    public void onRelated(){
        queryModel.getHttpData();
        queryModel.setQueryListener(new QueryModel.onQueryListener() {
            @Override
            public void onQuery(QueryBean queryBean) {
                queryView.getQueryData(queryBean);
            }
        });
    }
}
