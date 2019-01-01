package com.example.laiixnimvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laiixnimvp.R;
import com.example.laiixnimvp.adapter.Adapter;
import com.example.laiixnimvp.contract.IShowContrace;
import com.example.laiixnimvp.entity.User;
import com.example.laiixnimvp.presenter.ShowPresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements IShowContrace.IShowView {
    @BindView(R.id.title)
    EditText title;
    private Button butt;
    private String page="1";
    private RecyclerView gv;
    private Adapter adapter;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        gv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        showPresenter = new ShowPresenter(this);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = title.getText().toString();
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("keywords",name);
                hashMap.put("page",page);
                showPresenter.show(hashMap);
            }
        });
    }

    private void initView() {
        title = (EditText) findViewById(R.id.title);
        butt = (Button) findViewById(R.id.butt);
        gv = (RecyclerView) findViewById(R.id.gv);

    }

    @Override
    public void onKeywordError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailUre(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        List<User.DataBean> data = user.getData();
        adapter.setList(data);
        gv.setAdapter(adapter);
    }

    @Override
    public void onSuccessMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
