package com.example.weidudianshang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.weidudianshang.preseter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private View view;
    private T presenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(),container,false);
        //bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //bind.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=getPresenter();
        presenter.attachView(presenter);
        intoview(view);
        initdata();
    }


    //布局
    protected abstract int getLayout();
    //控件
    protected abstract void intoview(View view);
    //P层
    protected abstract T getPresenter();
    //数据
    protected abstract void initdata();

}
