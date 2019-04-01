package com.example.weidudianshang.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.R;

public class HomeMyView extends LinearLayout {


    private  EditText edit;
    private TextView text;

    public HomeMyView(Context context) {
        super(context);
    }

    public HomeMyView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.home_myview,this);
        edit=findViewById(R.id.edit_search);
        text=findViewById(R.id.text_search);
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(context,"请输入你要搜素的内容",Toast.LENGTH_SHORT).show();
                }else {
                    searchData.onRearchData(s);
                }
            }
        });
    }

    public HomeMyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface getSearchData{
        void onRearchData(String s);
    }
    getSearchData searchData;

    public void setSearchData(getSearchData searchData) {
        this.searchData = searchData;
    }
}
