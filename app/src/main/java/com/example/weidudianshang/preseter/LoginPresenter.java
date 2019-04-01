package com.example.weidudianshang.preseter;

import com.example.weidudianshang.bean.LogBean;
import com.example.weidudianshang.model.LoginModel;
import com.example.weidudianshang.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView>{

private final LoginView loginView;
private final LoginModel loginModel;

    public LoginPresenter(LoginView view){
            this.loginView=view;
            loginModel=new LoginModel();
            }

    public void onRelated(String phone,String pwd){
            loginModel.getHttpData(phone,pwd);
            loginModel.setRegListener(new LoginModel.onLoginListener() {
                @Override
                public void onReg(LogBean logBean) {
                    loginView.getLoginData(logBean);
                }
            });

    }
}