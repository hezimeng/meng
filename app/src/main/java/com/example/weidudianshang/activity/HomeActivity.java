package com.example.weidudianshang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.weidudianshang.R;
import com.example.weidudianshang.fragment.Circle_Fragment;
import com.example.weidudianshang.fragment.HomeFragment;
import com.example.weidudianshang.fragment.MyFragment;
import com.example.weidudianshang.fragment.OrderFragment;
import com.example.weidudianshang.fragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends FragmentActivity {

    @BindView(R.id.header_frag)
    FrameLayout headerFrag;
    @BindView(R.id.header_radio_home)
    RadioButton headerRadioHome;
    @BindView(R.id.header_radio_circle)
    RadioButton headerRadioCircle;
    @BindView(R.id.header_radio_shopping)
    RadioButton headerRadioShopping;
    @BindView(R.id.header_radio_list)
    RadioButton headerRadioList;
    @BindView(R.id.header_radio_main)
    RadioButton headerRadioMain;
    @BindView(R.id.header_radiogroup)
    RadioGroup headerRadiogroup;
    @BindView(R.id.header_bottom_ly)
    LinearLayout headerBottomLy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setaddfragment(new HomeFragment());
    }



    @OnClick({R.id.header_radio_home, R.id.header_radio_circle, R.id.header_radio_shopping, R.id.header_radio_list, R.id.header_radio_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_radio_home:
                setaddfragment(new HomeFragment());
                break;
            case R.id.header_radio_circle:
                setaddfragment(new Circle_Fragment());
                break;
            case R.id.header_radio_shopping:
                setaddfragment(new ShoppingFragment());
                break;
            case R.id.header_radio_list:
                setaddfragment(new OrderFragment());
                break;
            case R.id.header_radio_main:
                setaddfragment(new MyFragment());
                break;
        }
    }

    private void setaddfragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.header_frag,fragment).commit();
    }


}
