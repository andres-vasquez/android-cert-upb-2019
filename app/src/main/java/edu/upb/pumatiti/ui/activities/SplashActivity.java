package edu.upb.pumatiti.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import edu.upb.pumatiti.R;


public class SplashActivity extends AppCompatActivity {

    private static final String LOG = SplashActivity.class.getSimpleName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG, "onCreate");
        this.context = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "onDestroy");
    }

    /**
     * Para el click function tiene que cumplir:
     * public --> Pública
     * View view --> Recibir como parámetro view
     *
     * @param view --> Desde dónde se ejecuta
     */
    public void imageClick(View view) {
        Log.e(LOG, "imageClick");
        //Origen: SplashActivity, this, this.context
        //Destino: LoginActivity
        //Mensaje: Hello World

        Intent intent = new Intent(this.context, LoginActivity.class);
        intent.putExtra("message", "Hello world");
        startActivity(intent);
    }
}
