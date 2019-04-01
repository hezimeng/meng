package com.example.weidudianshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weidudianshang.R;
import com.example.weidudianshang.adapter.AddressAdapter;
import com.example.weidudianshang.adapter.shopping.QueryAdapter;
import com.example.weidudianshang.bean.AddressBean;
import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.fragment.BaseFragment;
import com.example.weidudianshang.preseter.AddressPresenter;
import com.example.weidudianshang.view.AddressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity<AddressPresenter> implements AddressView {

    @BindView(R.id.mShopAddres)
    RecyclerView mShopAddres;
    @BindView(R.id.mShopItem)
    RecyclerView mShopItem;
    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.mGitShopButton)
    Button mGitShopButton;
    private String realName="贺梓萌",phone="18238371398",address="河南省濮阳市 华龙区 站前路 慧竹丽景",zipCode="457000";
    private AddressPresenter addressPresenter;
    private List<QueryBean.ResultBean> result;
    private QueryAdapter adapter;
    private int totalPrice;

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }


    @Override
    protected void initData() {
        addressPresenter.onRelated(realName,phone,address,zipCode);
        mShopItem.setAdapter(adapter);
        setAdapter();

    }

    @Override
    protected AddressPresenter getPresenter() {
        addressPresenter = new AddressPresenter(this);
        return addressPresenter;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String orderInfo = intent.getStringExtra("orderInfo");
        int totalPrice = intent.getIntExtra("totalPrice", this.totalPrice);
    }

    /****如果适配器经常进行刷新的话最好是这样判断一下，避免多次创建适配器对象****/
    /**
     * 信息的适配器
     */
    private void setAdapter(){
        if(adapter == null) {
            adapter = new QueryAdapter(OrderActivity.this, result);

        } else {
            adapter.notifyDataSetChanged();
        }
    }
    /**
     * 地址的适配器
     */
    private void setAddAdapter(){

    }




    @Override
    public void getAddressData(AddressBean addressBean) {

    }

}
