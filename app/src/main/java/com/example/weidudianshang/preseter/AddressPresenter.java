package com.example.weidudianshang.preseter;

import com.example.weidudianshang.bean.AddressBean;
import com.example.weidudianshang.model.AddressModel;
import com.example.weidudianshang.view.AddressView;

public class AddressPresenter extends BasePresenter<AddressView>{
    private AddressView addressView;
    private AddressModel addressModel;

    public AddressPresenter(AddressView view){
        this.addressView=view;
        addressModel=new AddressModel();
    }

    public void onRelated(String realName,String phone,String address,String zipCode){
        addressModel.getHttpData(realName,phone,address,zipCode);
        addressModel.setAddressListener(new AddressModel.onAddressListener() {
            @Override
            public void onAddress(AddressBean addressBean) {
                addressView.getAddressData(addressBean);
            }
        });
    }

}
