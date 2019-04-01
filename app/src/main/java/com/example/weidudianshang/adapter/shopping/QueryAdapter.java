package com.example.weidudianshang.adapter.shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weidudianshang.R;
import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.view.GroupLayout;

import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {
    private Context context;
    private List<QueryBean.ResultBean> list;

    public QueryAdapter(Context context, List<QueryBean.ResultBean> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_tiem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        QueryBean.ResultBean resultBean = list.get(i);
        int price = resultBean.getPrice();

        viewHolder.name.setText(resultBean.getCommodityName());
        viewHolder.price.setText("￥" + price);
        Glide.with(context).load(resultBean.getPic()).into(viewHolder.image);
        //根据我记录的状态，改变勾选
        viewHolder.che.setChecked(list.get(i).isChecked());

        //商品复选框点击
        viewHolder.che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (callbackListener != null) {
                    callbackListener.onChecked(i,viewHolder.che.isChecked());
                }
            }
        });

        //加减器方法
        viewHolder.group_item.setData(this, list, i);

        //商品数量改变  这个等下做
        viewHolder.group_item.setNumChangeListener(new GroupLayout.onNumChangeListener() {
            @Override
            public void changeNum() {
                if (callbackListener != null) {
                    //callbackListener.onChecked(i, viewHolder.che.isChecked());
                }
            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackListener!=null){
                    callbackListener.onDelete(i);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name, price;
        private final CheckBox che;
        private final Button delete;
        private final GroupLayout group_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shopping_item_image);
            name = itemView.findViewById(R.id.shopping_item_name);
            price = itemView.findViewById(R.id.shopping_item_price);

            che = itemView.findViewById(R.id.shopping_item_che);
            group_item = itemView.findViewById(R.id.shopping_group_item);
            delete = itemView.findViewById(R.id.shopping_item_delete);

        }
    }

    /**
     * 接口回调
     */
    public interface onCallbackListener {
        //item 选中接口回调
        void onChecked(int position, boolean isChecked);

        //删除回调
        void onDelete(int position);
    }

    public onCallbackListener callbackListener;

    public void setCallbackListener(onCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }


}
