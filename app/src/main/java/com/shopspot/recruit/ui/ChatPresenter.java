package com.shopspot.recruit.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shopspot.recruit.model.Chat;
import com.shopspot.recruit.service.ChatService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View mChatView;
    private ChatService       mChatService;

    public ChatPresenter(@NonNull ChatContract.View chatView,
                         @NonNull ChatService chatService) {
        this.mChatView = chatView;
        this.mChatService = chatService;
    }

    @Override
    public void start() {
        getChatHistory();
    }

    private void getChatHistory() {
        mChatView.showLoading();
        mChatService
            .getAPI()
            .getHistory()
            .enqueue(new Callback<List<Chat>>() {
                @Override
                public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                    // Response success
                    mChatView.hideLoading();
                    List<Chat> chatHistoryList = response.body();
                    if (chatHistoryList.size() > 0) {
                        String firstMessage = chatHistoryList.get(0).getMessage();
                        Log.i("Response", "First firstMessage is \"" + firstMessage + "\"");
                    }
                    else {
                        Log.i("Response", "Chat history is empty.");
                    }
                }

                @Override
                public void onFailure(Call<List<Chat>> call, Throwable t) {
                    // Response error
                    mChatView.hideLoading();
                    Log.i("Failure", t.getMessage());
                }
            });
    }
}
