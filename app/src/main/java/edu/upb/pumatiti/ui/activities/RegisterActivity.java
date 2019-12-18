package edu.upb.pumatiti.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.repository.User;
import edu.upb.pumatiti.viewmodel.LoginViewModel;
import edu.upb.pumatiti.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private Context context;
    private RegisterViewModel viewModel;

    private long count = 0;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        initUI();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = System.currentTimeMillis();
                User user = new User();
                user.setUuid("user-" + count);
                user.setEmail(count + "_temp@pumatiti.com");
                user.setPassword("123456");
                viewModel.register(user);
            }
        });
    }

    private void initUI() {
        registerButton = findViewById(R.id.registerButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.e("Users", new Gson().toJson(users));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.getAll().removeObservers(this);
    }
}
