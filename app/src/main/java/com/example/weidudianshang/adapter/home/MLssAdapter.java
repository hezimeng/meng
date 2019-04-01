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

public class MLssAdapter extends RecyclerView.Adapter<MLssAdapter.MlssHolder>{

    private Context context;
    private List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public MLssAdapter(Context context, List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MlssHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.home_hotsell_tiem,viewGroup, false);
        MlssHolder mlssHolder = new MlssHolder(view);
        return mlssHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MlssHolder mlssHolder, int i) {
        final HomeBean.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);
        mlssHolder.nameTV.setText(commodityListBeanX.getCommodityName());
        mlssHolder.priceTV.setText("¥："+commodityListBeanX.getPrice()+"");
//        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
//                .setUri(commodityListBean.getMasterPic())
//                .setTapToRetryEnabled(true)
//                .build();
        mlssHolder.imgTV.setImageURI(commodityListBeanX.getMasterPic());
/**
 * 点击获取id
 */
        mlssHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mlssDetailsLisener!=null){
                    mlssDetailsLisener.onMlss(commodityListBeanX.getCommodityId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MlssHolder extends RecyclerView.ViewHolder{

        private final TextView nameTV,priceTV;
        private final SimpleDraweeView imgTV;

        public MlssHolder(View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.mlss_title);
            priceTV=itemView.findViewById(R.id.mlss_price);
            imgTV=itemView.findViewById(R.id.mlss_img);
        }
    }

    /**
     * 接口回调
     */
    public interface onMlssDetailsLisener{
        void onMlss(int id);
    }

    onMlssDetailsLisener mlssDetailsLisener;

    public void setMlssDetailsLisener(onMlssDetailsLisener mlssDetailsLisener){
        this.mlssDetailsLisener=mlssDetailsLisener;
    }
}
