package ch.timonhueppi.m335.bmicalc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.timonhueppi.m335.bmicalc.service.BMIService;

public class MainActivity extends Activity {
    BMIService bmiService;
    boolean serviceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, BMIService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        setButtonHandler();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        serviceBound = false;
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BMIService.LocalBinder binder = (BMIService.LocalBinder) service;
            bmiService = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            serviceBound = false;
        }
    };

    private void setButtonHandler(){
        Button btnCalc = findViewById(R.id.btnCalc);
        EditText editWeightNumber = findViewById((R.id.editWeightNumber));
        EditText editHeightNumber = findViewById((R.id.editHeightNumber));
        btnCalc.setOnClickListener(v -> {
            try {
                if (serviceBound) {
                    bmiService.setHeight(Float.parseFloat(editHeightNumber.getText().toString()));
                    bmiService.setWeight(Float.parseFloat(editWeightNumber.getText().toString()));
                }
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);

            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
}