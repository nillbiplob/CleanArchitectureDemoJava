package com.biplob.cleanarchitecturedemo.presentation.userlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.biplob.cleanarchitecturedemo.application.usecase.GetUsersUseCase;
import com.biplob.cleanarchitecturedemo.data.repository.UserRepositoryImpl;
import com.biplob.cleanarchitecturedemo.domain.model.User;
import com.biplob.cleanarchitecturedemo.presentation.common.UserAdapter;
import com.biplob.cleanarchitecturedemo.R;
import com.biplob.cleanarchitecturedemo.presentation.common.UserSelectionHolder;
import com.biplob.cleanarchitecturedemo.presentation.userdetail.UserDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private List<User> userList = new ArrayList<>();
    private List<User> displayedList = new ArrayList<>();
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new UserAdapter(displayedList, this::openUserDetail);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button refreshBtn = findViewById(R.id.btnRefresh);
        refreshBtn.setOnClickListener(v -> loadUsers());

        EditText searchBox = findViewById(R.id.etSearch);
        searchBox.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void afterTextChanged(android.text.Editable s) {
                filterUsers(s.toString());
            }
            // (other overrides can be empty)
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void onTextChanged(CharSequence s, int st, int b, int c) {}
        });

        // Set clear icon logic here
        findViewById(R.id.icClear).setOnClickListener(v -> {
            searchBox.setText("");
        });

        loadUsers();
    }

    private void loadUsers() {
        new Thread(() -> {
            try {
                GetUsersUseCase useCase = new GetUsersUseCase(new UserRepositoryImpl(this));
                userList = useCase.execute();
                runOnUiThread(() -> {
                    displayedList.clear();
                    displayedList.addAll(userList);
                    adapter.notifyDataSetChanged();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    // Show error message
                });
            }
        }).start();
    }

    private void filterUsers(String query) {
        displayedList.clear();
        for (User u : userList) {
            if (u.getName().toLowerCase().contains(query.toLowerCase()) ||
                    u.getUsername().toLowerCase().contains(query.toLowerCase()) ||
                    u.getEmail().toLowerCase().contains(query.toLowerCase())) {
                displayedList.add(u);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void openUserDetail(User user) {
        // Open detail activity with user data

        UserSelectionHolder.setUser(user);
        Intent intent = new Intent(this, UserDetailActivity.class);
        // Optionally pass user data via singleton, ViewModel, or (not recommended) Intent extra
        startActivity(intent);

    }
}
