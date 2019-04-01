package com.example.weidudianshang.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weidudianshang.R;
import com.example.weidudianshang.bean.home.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RespAdapter extends RecyclerView.Adapter<RespAdapter.RespHolder> {
    private Context context;
    private List<HomeBean.ResultBean.RxxpBean.CommodityListBean> list;
    public RespAdapter(Context context, List<HomeBean.ResultBean.RxxpBean.CommodityListBean> list){
        this.context=context;
        this.list=list;
    }

    //加载布局
    @NonNull
    @Override
    public RespHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_style_tiem, parent, false);
        RespHolder respHolder = new RespHolder(view);
        return respHolder;
    }

    //设置属性
    @Override
    public void onBindViewHolder(@NonNull RespHolder holder, int position) {
        final HomeBean.ResultBean.RxxpBean.CommodityListBean commodityListBean=list.get(position);
        holder.name.setText(commodityListBean.getCommodityName());
        holder.price.setText(commodityListBean.getPrice()+"");
        holder.img.setImageURI(commodityListBean.getMasterPic());
        /**
         * 点击获取id
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rxspDetailsLisener!=null){
                    rxspDetailsLisener.onRxsp(commodityListBean.getCommodityId());
                }
            }
        });
    }

    //长度
    @Override
    public int getItemCount() {
        return list.size();
    }

    //viewholder
    public class RespHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final SimpleDraweeView img;
        private final TextView price;

        public RespHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.style_title);
            img=itemView.findViewById(R.id.style_img);
            price=itemView.findViewById(R.id.style_price);
        }
    }


    /**
     * 接口回调
     */
    public interface onRxspDetailsLisener{
        void onRxsp(int id);
    }

   onRxspDetailsLisener rxspDetailsLisener;

    public void setRxspDetailslistener(onRxspDetailsLisener rxspDetailsLisener){
        this.rxspDetailsLisener=rxspDetailsLisener;
    }
}
