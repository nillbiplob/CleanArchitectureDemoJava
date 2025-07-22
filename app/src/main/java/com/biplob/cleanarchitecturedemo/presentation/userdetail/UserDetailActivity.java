package com.biplob.cleanarchitecturedemo.presentation.userdetail;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.biplob.cleanarchitecturedemo.domain.model.User;
import com.biplob.cleanarchitecturedemo.R;
import com.biplob.cleanarchitecturedemo.presentation.common.UserSelectionHolder;

public class UserDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // Receive user from intent/other means
        User user = UserSelectionHolder.getUser();

        if (user == null) {
            finish(); // Defensive: Go back if no user is set
            return;
        }

        TextView tvName = findViewById(R.id.tvDetailsName);
        TextView tvUsername = findViewById(R.id.tvDetailsUsername);
        TextView tvEmail = findViewById(R.id.tvDetailsEmail);

        tvName.setText(user.getName());
        tvUsername.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
        // Add more fields as needed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserSelectionHolder.clear();
    }

    public void closeActivity(View view) {
        finish();
    }
}
