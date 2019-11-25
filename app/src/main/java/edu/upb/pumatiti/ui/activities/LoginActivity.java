package edu.upb.pumatiti.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.utils.Constants;
import edu.upb.pumatiti.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getSimpleName();

    private Context context;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button sendButton;

    private LoginViewModel viewModel;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        Log.e(LOG, "onCreate");

        this.context = this;
        initUI();
        initEvents();

        //Only for testing
        emailEditText.setText("user@pumatiti.com");
        passwordEditText.setText("123456");

        Intent intent = getIntent();
        if (intent.hasExtra("message")) {
            String message = intent.getStringExtra("message");
            Toast.makeText(this, // Context, origen
                    message, //Mensaje
                    Toast.LENGTH_SHORT) //Duración
                    .show();
        }
    }

    private void initUI() {
        this.emailEditText = findViewById(R.id.emailEditText);
        this.passwordEditText = findViewById(R.id.passwordEditText);
        this.sendButton = findViewById(R.id.sendButton);
        this.registerButton = findViewById(R.id.registerButton);
    }

    private void initEvents() {
        this.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    login(email, password);
                } else {
                    Toast.makeText(context,
                            R.string.error_empty,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void login(String email, String password) {
        showLoading();
        viewModel.login(email, password).observe(this, new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                dissmissLoading();
                if (base.isSuccess()) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(Constants.INTENT_KEY_USER, new Gson().toJson(base.getData()));
                    startActivity(intent);
                } else {
                    Toast.makeText(context, R.string.error_login_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(LOG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(LOG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(LOG, "onDestroy");
    }

    public void registerClick(View view) {
        Log.e(LOG, "registerClick");
    }

    private void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(context);
            loadingDialog.setMessage(getString(R.string.loading));
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        loadingDialog.show();
    }

    private void dissmissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
