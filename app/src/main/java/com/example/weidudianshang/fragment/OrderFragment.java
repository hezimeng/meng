package com.example.weidudianshang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.weidudianshang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends Fragment {
    @BindView(R.id.order_mean)
    ImageView orderMean;
    @BindView(R.id.order_pay)
    ImageView orderPay;
    @BindView(R.id.order_receiveshop)
    ImageView orderReceiveshop;
    @BindView(R.id.order_say)
    ImageView orderSay;
    @BindView(R.id.order_finash)
    ImageView orderFinash;
    @BindView(R.id.sel_order_recyc)
    RecyclerView selOrderRecyc;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LinearLayout.inflate(getContext(), R.layout.order_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
