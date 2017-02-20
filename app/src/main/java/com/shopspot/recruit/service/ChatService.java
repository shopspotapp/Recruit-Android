package com.shopspot.recruit.service;

import com.shopspot.recruit.model.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ChatService {
    private static final String BASE_URL = "https://4you.in.th/api/chat/history.json";

    public interface ChatAPI {
        @GET("chat/history.json")
        Call<List<Chat>> getHistory();
    }

    public ChatAPI getAPI() {
        return new Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatAPI.class);
    }
}
