package com.biplob.cleanarchitecturedemo.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.biplob.cleanarchitecturedemo.data.model.UserDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class UserLocalDataSource {
    private static final String PREF_NAME = "user_cache";
    private static final String KEY_USER_LIST = "user_list";

    private SharedPreferences prefs;
    private Gson gson = new Gson();

    public UserLocalDataSource(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUsers(List<UserDto> users) {
        String json = gson.toJson(users);
        prefs.edit().putString(KEY_USER_LIST, json).apply();
    }

    public List<UserDto> getCachedUsers() {
        String json = prefs.getString(KEY_USER_LIST, null);
        if (json == null) return Collections.emptyList();
        Type listType = new TypeToken<List<UserDto>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
