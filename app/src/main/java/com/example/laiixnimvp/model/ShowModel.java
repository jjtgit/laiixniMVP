package com.example.laiixnimvp.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.laiixnimvp.api.UserApi;
import com.example.laiixnimvp.contract.IShowContrace;
import com.example.laiixnimvp.entity.User;
import com.example.laiixnimvp.net.OkHttpCallback;
import com.example.laiixnimvp.net.OkHttpUtile;
import com.example.laiixnimvp.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class ShowModel implements IShowContrace.IShowModel {
    Handler handler=new Handler();
    @Override
    public void show(HashMap<String, String> params, final RequestCallback callback) {
        OkHttpUtile.getmInstrance().doPost(UserApi.User_SHOW, params, new OkHttpCallback() {
            @Override
            public void failUre(String msg) {
                if (callback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                           callback.onFailUre("网络异常");
                        }
                    });
                }
            }

            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)){
                    requestCall(result,callback);
                }
            }
        });
    }
    private void requestCall(String result,final RequestCallback callback) {
       final User user = new Gson().fromJson(result, User.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(user);
            }
        });
    }
}
