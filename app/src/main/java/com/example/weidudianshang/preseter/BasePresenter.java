package com.example.weidudianshang.preseter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {

    private Reference<T> tReference;

    public void attachView(T t){
        tReference= new WeakReference<>(t);
    }
    public void deatchView(){
        if (tReference!=null){
            tReference.clear();
            tReference=null;
        }
    }


}
