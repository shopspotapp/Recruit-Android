package com.shopspot.recruit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shopspot.recruit.R;
import com.shopspot.recruit.service.ChatService;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private ChatPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatService chatService = new ChatService();
        mPresenter = new ChatPresenter(this, chatService);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
