package com.example.laiixnimvp.net;

import com.example.laiixnimvp.entity.User;

public interface RequestCallback {
    void onFailUre(String msg);
    void onSuccess(User user);
    void onSuccessMsg(String msg);
}
