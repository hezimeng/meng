package com.example.weidudianshang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weidudianshang.R;
import com.example.weidudianshang.preseter.BasePresenter;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    public T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        presenter=getPresenter();
        presenter.attachView(presenter);
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract T getPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deatchView();
    }
}
