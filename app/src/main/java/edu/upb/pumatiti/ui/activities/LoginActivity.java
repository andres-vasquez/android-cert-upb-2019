package edu.upb.pumatiti.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.viewmodel.LoginViewModel;
import edu.upb.pumatiti.viewmodel.MainViewModel;

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
        Log.e(LOG, "onCreate");
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        this.context = this;
        initUI();
        initEvents();

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
                final String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    if (!email.contains("@")) {
                        emailEditText.setError(getString(R.string.error_invalid_email));
                    }

                    showLoading();
                    LiveData<Base> result = viewModel.login(email, password);
                    result.observe(LoginActivity.this, new Observer<Base>() {
                        @Override
                        public void onChanged(Base base) {
                            dismissLoading();
                            if (base.isSuccess()) {
                                //executeLongAction();
                                Toast.makeText(context,
                                        getString(R.string.welcome, email),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            } else {
                                Toast.makeText(context,
                                        base.getMessage(),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(context,
                            R.string.error_empty,
                            Toast.LENGTH_SHORT)
                            .show();
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


    private void executeLongAction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                longAction();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Action finished", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private void longAction() {
        try {
            Thread.sleep(5000);
            Log.e(LOG, "Long Action finished");
        } catch (Exception ex) {
            Log.e(LOG, "" + ex.getMessage());
        }
    }

    private void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(context);
            loadingDialog.setMessage("Loading");
        }
        loadingDialog.show();
    }

    private void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
