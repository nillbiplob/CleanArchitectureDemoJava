package com.biplob.cleanarchitecturedemo.data.repository;

import android.content.Context;

import com.biplob.cleanarchitecturedemo.data.model.UserDto;
import com.biplob.cleanarchitecturedemo.data.source.UserRemoteDataSource;
import com.biplob.cleanarchitecturedemo.data.source.UserLocalDataSource;
import com.biplob.cleanarchitecturedemo.domain.model.User;
import com.biplob.cleanarchitecturedemo.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private UserRemoteDataSource remoteDataSource;
    private UserLocalDataSource localDataSource;

    public UserRepositoryImpl(Context context) {
        this.remoteDataSource = new UserRemoteDataSource();
        this.localDataSource = new UserLocalDataSource(context);
    }

    @Override
    public List<User> getUsers() throws Exception {
        try {
            List<UserDto> dtos = remoteDataSource.fetchUsers();
            localDataSource.saveUsers(dtos); // Cache latest
            return mapDtosToUsers(dtos);
        } catch (Exception e) {
            // Fallback to cache on failure
            List<UserDto> cached = localDataSource.getCachedUsers();
            if (cached.isEmpty()) throw new Exception("No data available", e);
            return mapDtosToUsers(cached);
        }
    }

    private List<User> mapDtosToUsers(List<UserDto> dtos) {
        List<User> users = new ArrayList<>();
        for (UserDto dto : dtos) {
            users.add(new User(dto.getId(), dto.getName(), dto.getUsername(), dto.getEmail()));
        }
        return users;
    }
}
