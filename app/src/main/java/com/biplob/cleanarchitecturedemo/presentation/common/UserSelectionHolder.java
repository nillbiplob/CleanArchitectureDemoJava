package com.biplob.cleanarchitecturedemo.presentation.common;

import com.biplob.cleanarchitecturedemo.domain.model.User;

public class UserSelectionHolder {
    private static User selectedUser;

    public static void setUser(User user) {
        selectedUser = user;
    }

    public static User getUser() {
        return selectedUser;
    }

    public static void clear() {
        selectedUser = null;
    }
}
