package com.biplob.cleanarchitecturedemo.presentation.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.biplob.cleanarchitecturedemo.R;
import com.biplob.cleanarchitecturedemo.application.usecase.GetUsersUseCase;
import com.biplob.cleanarchitecturedemo.data.repository.UserRepositoryImpl;
import com.biplob.cleanarchitecturedemo.domain.model.User;
import com.biplob.cleanarchitecturedemo.presentation.userlist.UserListActivity;

import java.util.List;

public class SplashActivity extends Activity {
    private static final int SPLASH_MIN_MS = 3000;
    private boolean dataLoaded = false;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tvError = findViewById(R.id.tvError);

        loadUsers(tvError);

        // Wait SPLASH_MIN_MS or until data is loaded
        new Handler(Looper.getMainLooper()).postDelayed(() -> goNextIfReady(tvError), SPLASH_MIN_MS);
    }

    private void loadUsers(TextView tvError) {
        tvError.setVisibility(View.GONE); // Hide error before loading
        new Thread(() -> {
            try {
                GetUsersUseCase useCase = new GetUsersUseCase(new UserRepositoryImpl(this));
                userList = useCase.execute();
                dataLoaded = true;
            } catch (Exception e) {
                runOnUiThread(() -> {
                    tvError.setText("Failed to load users.\nTap to retry.");
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setOnClickListener(v -> {
                        tvError.setVisibility(View.GONE);
                        loadUsers(tvError); // Retry
                    });
                });
            }
        }).start();
    }

    private void goNextIfReady(TextView tvError) {
        if (dataLoaded && userList != null) {
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Wait and try again only if not currently showing error
            if (tvError.getVisibility() != View.VISIBLE) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> goNextIfReady(tvError), 500);
            }
        }
    }

}
