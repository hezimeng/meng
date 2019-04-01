package com.example.weidudianshang.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.R;
import com.example.weidudianshang.activity.OrderActivity;
import com.example.weidudianshang.adapter.shopping.QueryAdapter;
import com.example.weidudianshang.bean.shopping.QueryBean;
import com.example.weidudianshang.bean.shopping.SynchBean;
import com.example.weidudianshang.preseter.shopping.QueryPresenter;
import com.example.weidudianshang.preseter.shopping.SynchPresenter;
import com.example.weidudianshang.utils.SharedPreferencesUtils;
import com.example.weidudianshang.view.shopping.QueryView;
import com.example.weidudianshang.view.shopping.SynchView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShoppingFragment extends BaseFragment<QueryPresenter> implements QueryView, QueryAdapter.onCallbackListener, SynchView {

    @BindView(R.id.shopp_rec)
    RecyclerView shoppRec;
    @BindView(R.id.shopp_che)
    CheckBox shoppChe;
    @BindView(R.id.shopp_price)
    TextView shoppPrice;
    @BindView(R.id.shopp_btn)
    Button shoppBtn;
    Unbinder unbinder;
    private QueryPresenter presenter;
    private String userId;
    private String sessionId;
    private QueryAdapter adapter;
    private List<QueryBean.ResultBean> result;
    private SynchPresenter synchPresenter;



    @Override
    protected int getLayout() {
        return R.layout.shopping_fragment;
    }

    @Override
    protected void intoview(View view) {
        unbinder = ButterKnife.bind(this, view);
//        SharedPreferences deng = getActivity().getSharedPreferences("deng", Context.MODE_PRIVATE);
////        sessionId = deng.getString("sessionId", "");
////        userId = deng.getString("userId", "");


        sessionId = SharedPreferencesUtils.getString("sessionId", "");
        userId = SharedPreferencesUtils.getString("userId", "");
        shoppRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        shoppRec.addItemDecoration(new DividerItemDecoration(getActivity(),OrientationHelper.VERTICAL));

    }

    @Override
    protected QueryPresenter getPresenter() {
         presenter = new QueryPresenter(this);
         //同步购物车数据
        synchPresenter = new SynchPresenter(this);
        return presenter;
    }

    @Override
    protected void initdata() {
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
            //关联p
            presenter.onRelated();
        }
        /**
         * 全选点击事件
         */
        shoppChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAll(shoppChe.isChecked());
            }
        });
        /**
         * 创建订单
         */
        shoppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Gson gson = new Gson();
                //将集合数据转换为json串
                String mJsonData = gson.toJson(result);

                //循环集合判断选中状态
                for (int i = 0; i < result.size(); i++) {
                    //如果为true,获取选中的商品信息和总价 跳转
                    if(result.get(i).isChecked()){
                        Intent intent = new Intent(getContext(), OrderActivity.class);
                        //商品信息
                        intent.putExtra("orderInfo",mJsonData);
                        //总价
                        intent.putExtra("totalPrice",shoppPrice+"");
                        Toast.makeText(getContext(),mJsonData+shoppPrice+"￥",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
//                    else if(result.get(i).isChecked()==false){
//                        //如果没有选中的数据就吐司
//                        Toast.makeText(getContext(),"请选择商品后再去结算",Toast.LENGTH_SHORT).show();
//                    }
                }


            }
        });



    }

    private void setAdapter(){
        //如果适配器经常进行刷新的话最好是这样判断一下，避免多次创建适配器对象
        if(adapter == null) {
            adapter = new QueryAdapter(getActivity(), result);
            shoppRec.setAdapter(adapter);

            adapter.setCallbackListener(this);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getQueryData(QueryBean queryBean) {
        if (queryBean != null) {
            result = queryBean.getResult();
            setAdapter();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 全选方法
     *
     * @param checked
     */
    private void checkAll(boolean checked) {

        for (int i = 0; i < result.size(); i++) {
            result.get(i).setChecked(checked);
        }

        setPrices();
    }

    private void setPrices(){

        double priceAll = 0.0;
        int num = 0;

        //循环集合判断选中状态
        for (int i = 0; i < result.size(); i++) {
            //如果为true获取价格和数量
            if(result.get(i).isChecked()){
                priceAll += result.get(i).getPrice() * result.get(i).getCount();
                num += result.get(i).getCount();
            }
        }

        shoppPrice.setText("合计：" + priceAll);
        shoppBtn.setText("去结算(" + num + ")");

        //刷新适配器 也可以直接写
        setAdapter();//这个地方
        //adapter.notifyDataSetChanged();

    }

    /**
     * 适配器内 复选框监听回调
     * @param position
     * @param isChecked
     */
    @Override
    public void onChecked(int position, boolean isChecked) {
        //修改状态值
        result.get(position).setChecked(isChecked);
        //调用计算价钱和数量方法
        setPrices();
    }

    @Override
    public void onDelete(int position) {
        //拿到这个下标就可以进行删除了
        result.remove(position);
        //在吧本地内数据删除之后再调用这个同步接口
        Gson gson = new Gson();
        //将集合数据转换为json串
        String mJsonData = gson.toJson(result);
        //可以调用接口了
        Log.d("mJsonData", "onViewClicked: " + mJsonData);
        synchPresenter.onRelated(mJsonData);

        //之后再计算一下价钱就好了
        setPrices();
    }

    @Override
    public void getSynchData(SynchBean synchBean) {

    }
}
