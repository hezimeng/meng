package com.example.weidudianshang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.activity.BaseActivity;
import com.example.weidudianshang.activity.HomeActivity;
import com.example.weidudianshang.activity.RegActivity;
import com.example.weidudianshang.bean.LogBean;
import com.example.weidudianshang.preseter.LoginPresenter;
import com.example.weidudianshang.utils.SharedPreferencesUtils;
import com.example.weidudianshang.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eyes)
    ImageView eyes;
    @BindView(R.id.check_jz)
    CheckBox checkJz;
    @BindView(R.id.resign)
    TextView resign;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter loginPresenter;
    //private SharedPreferences sp;
    //private SharedPreferences.Editor edit;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        //吧这个sp封装一下 然后直接可以在哪个地方用 不需要每次都去传
        //sp = getSharedPreferences("deng", Context.MODE_PRIVATE);

        if (SharedPreferencesUtils.getBoolean("remober",false)) {
            String phone1 = SharedPreferencesUtils.getString("phone", "");
            String pwd1 = SharedPreferencesUtils.getString("pwd", "");
            phone.setText(phone1);
            pwd.setText(pwd1);
            checkJz.setChecked(true);
        }

    }

    @Override
    protected LoginPresenter getPresenter() {
        loginPresenter = new LoginPresenter(this);
        return loginPresenter;
    }

    @OnClick({R.id.check_jz, R.id.resign, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_jz:

                break;
            case R.id.resign:
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.login:
                String pwd = this.pwd.getText().toString();
                String phone = this.phone.getText().toString();
                loginPresenter.onRelated(phone,pwd);
                //edit = sp.edit();

                //保存状态值
                SharedPreferencesUtils.saveBoolean("remober",checkJz.isChecked());
                SharedPreferencesUtils.saveString("phone",phone);
                SharedPreferencesUtils.saveString("pwd",pwd);

                break;
        }
    }

    @Override
    public void getLoginData(LogBean logBean) {
        if (logBean.getMessage().equals("登录成功")){
            String headPic = logBean.getResult().getHeadPic();
            String nickName = logBean.getResult().getNickName();
            String phone = logBean.getResult().getPhone();
            String sessionId = logBean.getResult().getSessionId();
            int sex = logBean.getResult().getSex();
            int userId = logBean.getResult().getUserId();

            //保存状态值
            SharedPreferencesUtils.saveString("headPic",headPic);
            SharedPreferencesUtils.saveString("nickName",nickName);
            SharedPreferencesUtils.saveString("sessionId",sessionId);
            SharedPreferencesUtils.saveString("sex",sex + "");
            SharedPreferencesUtils.saveString("userId",userId + "");
            SharedPreferencesUtils.saveString("phone",phone);

            Toast.makeText(MainActivity.this,logBean.getMessage(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }else{
            Toast.makeText(MainActivity.this,logBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
