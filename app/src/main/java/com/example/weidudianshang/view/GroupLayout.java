package com.example.weidudianshang.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.R;
import com.example.weidudianshang.adapter.shopping.QueryAdapter;
import com.example.weidudianshang.bean.shopping.QueryBean;

import java.util.List;

public class GroupLayout extends LinearLayout implements View.OnClickListener{

    private int number = 1;
    private TextView num_goods;
    private QueryAdapter queryAdapter;
    private List<QueryBean.ResultBean> list;
    private int i;


    public GroupLayout(Context context) {
        super(context);
    }

    public GroupLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.group_layout, this);
        //控件
        TextView minus_goods = findViewById(R.id.shopping_item_jian);
        TextView add_goods = findViewById(R.id.shopping_item_jia);
        num_goods = findViewById(R.id.shopping_item_shu);
        num_goods.setText(number + "");

        /**
         * 设置点击事件监听
         */
        minus_goods.setOnClickListener(this);
        add_goods.setOnClickListener(this);


    }

    public GroupLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.shopping_item_jia://加
                number++;
                num_goods.setText(number + "");
                list.get(i).setCount(number);
                numChangeListener.changeNum();
                queryAdapter.notifyItemChanged(i);
                break;
            case R.id.shopping_item_jian://减
                if (number > 1) {
                    number--;

                } else {
                    Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_SHORT).show();
                }
                num_goods.setText(number + "");
                list.get(i).setCount(number);
                numChangeListener.changeNum();
                queryAdapter.notifyItemChanged(i);
                break;
        }


    }

    public void setData(QueryAdapter queryAdapter, List<QueryBean.ResultBean> list, int i) {
        this.list = list;
        this.queryAdapter = queryAdapter;
        this.i = i;
        number = list.get(i).getCount();
        num_goods.setText(this.number + "");
    }


    /**
     * 接口回调
     */
    public interface onNumChangeListener {
        void changeNum();
    }

    public onNumChangeListener numChangeListener;

    public void setNumChangeListener(onNumChangeListener numChangeListener) {
        this.numChangeListener = numChangeListener;
    }
}
