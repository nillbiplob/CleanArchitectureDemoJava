package com.biplob.cleanarchitecturedemo.data.source;

import com.biplob.cleanarchitecturedemo.data.model.UserDto;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

public class UserRemoteDataSource {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    public List<UserDto> fetchUsers() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            throw new Exception("API Error: " + status);
        }

        Reader reader = new InputStreamReader(connection.getInputStream());
        UserDto[] users = new Gson().fromJson(reader, UserDto[].class);
        reader.close();
        connection.disconnect();

        return Arrays.asList(users);
    }
}
