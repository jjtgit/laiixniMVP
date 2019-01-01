package com.example.laiixnimvp.contract;

import com.example.laiixnimvp.entity.User;
import com.example.laiixnimvp.net.RequestCallback;

import java.util.HashMap;

public interface IShowContrace {
    public  abstract class IShowPresenter{
        public abstract void show(HashMap<String,String>params);
    }
    interface IShowModel{
        void show(HashMap<String,String>params, RequestCallback callback);
    }
    interface IShowView{
        void onKeywordError(String error);
        void onPagerError(String error);
        void onFailUre(String msg);
        void onSuccess(User user);
        void onSuccessMsg(String msg);
    }
}
