package com.example.weidudianshang.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.MainActivity;
import com.example.weidudianshang.R;
import com.example.weidudianshang.bean.RegBean;
import com.example.weidudianshang.preseter.RegPresenter;
import com.example.weidudianshang.view.RegView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends BaseActivity<RegPresenter> implements RegView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yz)
    EditText yz;
    @BindView(R.id.hqyz)
    TextView hqyz;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eyes)
    ImageView eyes;
    @BindView(R.id.reg_but)
    Button regBut;
    private RegPresenter regPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected RegPresenter getPresenter() {
        regPresenter = new RegPresenter(this);
        return regPresenter;
    }

    @Override
    public void getRegData(RegBean regdata) {
        if (regdata.getMessage().equals("注册成功")){
            Toast.makeText(RegActivity.this,regdata.getMessage(),Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(RegActivity.this, MainActivity.class);
            finish();
            startActivity(intent1);
        }else {
            Toast.makeText(RegActivity.this,regdata.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.reg_but)
    public void onViewClicked() {

        String pwd = this.pwd.getText().toString();
        String phone = this.phone.getText().toString();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
            if (phone.length() != 11) {
                Toast.makeText(RegActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                return;
            } else if (pwd.length() != 3) {
                Toast.makeText(RegActivity.this, "密码格式不对", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //关联p
                regPresenter.onRelated(phone,pwd);
            }
        } else {
            Toast.makeText(RegActivity.this, "手机号或密码为空", Toast.LENGTH_SHORT).show();
        }
    }
}
