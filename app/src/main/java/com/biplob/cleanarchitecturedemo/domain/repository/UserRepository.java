package com.biplob.cleanarchitecturedemo.domain.repository;

import com.biplob.cleanarchitecturedemo.domain.model.User;

import java.util.List;

// No implementation here! This is the contract between Domain and Data/Application.

public interface UserRepository {
    /**
     * Returns a list of users, or throws an Exception on error.
     * The implementation will decide where/how to get the data (API, cache, etc).
     */
    List<User> getUsers() throws Exception;
}