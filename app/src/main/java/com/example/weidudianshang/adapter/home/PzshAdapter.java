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

public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.PzshHolder> {
    private List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> list;
    private Context context;
    public PzshAdapter(Context context, List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> list ){
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public PzshHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_life_tiem, parent, false);
        PzshHolder pzshHolder = new PzshHolder(view);
        return pzshHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PzshHolder holder, int position) {
        final HomeBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = list.get(position);
        holder.nameVH.setText(commodityListBeanXX.getCommodityName());
        holder.priceVh.setText(commodityListBeanXX.getPrice()+"");
        holder.img.setImageURI(commodityListBeanXX.getMasterPic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pzshDetailsLisener!=null){
                    pzshDetailsLisener.onPzsh(commodityListBeanXX.getCommodityId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PzshHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView nameVH,priceVh;

        public PzshHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.life_img);
            nameVH=itemView.findViewById(R.id.life_title);
            priceVh=itemView.findViewById(R.id.life_price);
        }
    }

    /**
     * 接口回调
     */
    public interface onPzshDetailsLisener{
        void onPzsh(int id);
    }

   onPzshDetailsLisener pzshDetailsLisener;

    public void setPzshDetailsLisener(onPzshDetailsLisener pzshDetailsLisener){
        this.pzshDetailsLisener=pzshDetailsLisener;
    }
}
