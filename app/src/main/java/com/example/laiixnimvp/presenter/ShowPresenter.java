package com.example.laiixnimvp.presenter;

import com.example.laiixnimvp.contract.IShowContrace;
import com.example.laiixnimvp.entity.User;
import com.example.laiixnimvp.model.ShowModel;
import com.example.laiixnimvp.net.RequestCallback;

import java.util.HashMap;

public class ShowPresenter extends IShowContrace.IShowPresenter {
    private ShowModel showModel;
    private IShowContrace.IShowView iShowView;
    public ShowPresenter(IShowContrace.IShowView iShowView){
        this.showModel=new ShowModel();
        this.iShowView=iShowView;
    }
    @Override
    public void show(HashMap<String, String> params) {
        if (showModel!=null){
            showModel.show(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iShowView!=null){
                        iShowView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccess(User user) {
                    if (iShowView!=null){
                        iShowView.onSuccess(user);
                    }
                }

                @Override
                public void onSuccessMsg(String msg) {
                    if (iShowView!=null){
                        iShowView.onSuccessMsg(msg);
                    }
                }
            });
        }
    }
    private void destroy(){
        if (iShowView!=null){
            iShowView=null;
        }
    }
}
