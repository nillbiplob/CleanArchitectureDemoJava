package com.biplob.cleanarchitecturedemo.application.usecase;

import com.biplob.cleanarchitecturedemo.domain.model.User;
import com.biplob.cleanarchitecturedemo.domain.repository.UserRepository;

import java.util.List;

/*
What is the Application Layer?
Purpose: This layer orchestrates business logic using use cases.

It depends on Domain (entities/repositories), but knows nothing about Data or Presentation.

Each use case is a class (or method) that performs one business action (ex: "GetUsersUseCase").
/*

/*

Explanation:
GetUsersUseCase is independent of UI and frameworks.

It’s easily testable (mock the UserRepository).

It is the only layer that orchestrates use of multiple repositories, if needed.

 */

public class GetUsersUseCase {
    private final UserRepository userRepository;

    // Dependency is injected via constructor (manual DI)
    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Business logic: gets the list of users
    public List<User> execute() throws Exception {
        return userRepository.getUsers();
    }
}