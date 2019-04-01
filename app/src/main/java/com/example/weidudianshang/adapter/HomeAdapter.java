package com.example.weidudianshang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weidudianshang.R;
import com.example.weidudianshang.activity.home.DetailsActivity;
import com.example.weidudianshang.adapter.home.MLssAdapter;
import com.example.weidudianshang.adapter.home.PzshAdapter;
import com.example.weidudianshang.adapter.home.RespAdapter;
import com.example.weidudianshang.bean.home.HomeBannerBean;
import com.example.weidudianshang.bean.home.HomeBean;
import com.example.weidudianshang.fragment.HomeFragment;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_TWO = 0,TYPE_THREE = 1,TYPE_FOUR = 2;
    private Context context;
    private HomeBean homeBean;
    private List<HomeBannerBean.ResultBean> list;

    public HomeAdapter(Context context, List<HomeBannerBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setLiset(HomeBean homeBean) {
        this.homeBean = homeBean;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //轮播图
        /*if (TYPE_ONE == i) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner, viewGroup,false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        }*/
        //热销产品
        if (TYPE_TWO == i) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_hotsell, viewGroup, false);
            return new RxspHoder(view);
        }
        //魔丽时尚

        if (TYPE_THREE == i) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_life, viewGroup, false);
            return new PzshHoder(view);
        }
        //品质生活
        View view = LayoutInflater.from(context).inflate(R.layout.home_style, viewGroup, false);
        return new MlssHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        /*if (getItemViewType(i) == 0) {
            BannerHolder bannerHolder = (BannerHolder) viewHolder;
            *//*AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                    .setUri(list.get(i).getImageUrl())
                    .setTapToRetryEnabled(true)
                    .build();*//*

           // Log.d("--", "onBindViewHolder: "+list.get(i).getImageUrl());
            //bannerHolder.img.setController(build);
        }*/
        //热销商品
         if (getItemViewType(i) == 0) {
            RespAdapter respAdapter = new RespAdapter(context, homeBean.getResult().getRxxp().getCommodityList());
            ((RxspHoder) viewHolder).rexXr.setLayoutManager(new GridLayoutManager(context, 3));
            ((RxspHoder) viewHolder).rexXr.setAdapter(respAdapter);
            respAdapter.setRxspDetailslistener(new RespAdapter.onRxspDetailsLisener() {
                @Override
                public void onRxsp(int id) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("commodityId",id);
                    context.startActivity(intent);
                }
            });
        }
        //魔力时尚
        else if (getItemViewType(i) == 1) {
            PzshAdapter pzshAdapter = new PzshAdapter(context, homeBean.getResult().getMlss().getCommodityList());
            ((PzshHoder) viewHolder).pzahXr.setLayoutManager(new LinearLayoutManager(context));
            ((PzshHoder) viewHolder).pzahXr.setAdapter(pzshAdapter);
            pzshAdapter.setPzshDetailsLisener(new PzshAdapter.onPzshDetailsLisener() {
                @Override
                public void onPzsh(int id) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("commodityId",id);
                    context.startActivity(intent);
                }
            });
        }
        //品质生活
        else {
            MLssAdapter mLssAdapter = new MLssAdapter(context, homeBean.getResult().getPzsh().getCommodityList());
            mLssAdapter.setMlssDetailsLisener(new MLssAdapter.onMlssDetailsLisener() {
                @Override
                public void onMlss(int id) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("commodityId",id);
                    context.startActivity(intent);
                }
            });
            ((MlssHoder) viewHolder).mlssXr.setLayoutManager(new GridLayoutManager(context,2));
            ((MlssHoder) viewHolder).mlssXr.setAdapter(mLssAdapter);

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        /*if (position == 0) {
            return TYPE_ONE;
        } else*/ if (position == 0) {
            return TYPE_TWO;
        } else if (position == 1) {
            return TYPE_THREE;
        } else {
            return TYPE_FOUR;
        }
    }

    //轮播图
    private class BannerHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        //private final SimpleDraweeView img;
        private XBanner xBanner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_item);
            //img = itemView.findViewById(R.id.img_item);
            xBanner = itemView.findViewById(R.id.xbanner);
        }
    }

    //热销产品
    private class RxspHoder extends RecyclerView.ViewHolder {
        private final RecyclerView rexXr;

        public RxspHoder(View view) {
            super(view);
            rexXr = view.findViewById(R.id.rx_xr);
        }
    }

    //品质生活
    private class PzshHoder extends RecyclerView.ViewHolder {
        private final RecyclerView pzahXr;

        public PzshHoder(View view) {
            super(view);
            pzahXr = view.findViewById(R.id.pz_xr);
        }
    }

    //魔力时尚
    private class MlssHoder extends RecyclerView.ViewHolder {
        private final RecyclerView mlssXr;

        public MlssHoder(View view) {
            super(view);
            mlssXr = view.findViewById(R.id.ml_xr);
        }
    }
}
