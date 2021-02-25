package ch.timonhueppi.m335.bmicalc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import ch.timonhueppi.m335.bmicalc.service.BMIService;

public class ResultActivity extends Activity {
    BMIService bmiService;
    boolean serviceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, BMIService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
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

            setResultTexts();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            serviceBound = false;
        }
    };

    public void setResultTexts() {
        Float BMI = bmiService.calcBMI();
        TextView textViewBMI = findViewById(R.id.textViewBMI);
        textViewBMI.setText(String.valueOf(BMI));
        TextView textViewClassification = findViewById(R.id.textViewClassification);

        textViewClassification.setText(getClassification(BMI));
    }

    public String getClassification(Float BMI){
        if (BMI < 18.5){
            return getString(R.string.Underweight);
        }else if (BMI >= 18.5 && BMI < 24.9){
            return getString(R.string.NormalWeight);
        }else if (BMI >= 25 && BMI < 29.9){
            return getString(R.string.PreObesity);
        }else if (BMI >= 30 && BMI < 34.9){
            return getString(R.string.ObesityClassI);
        }else if (BMI >= 35 && BMI < 39.9){
            return getString(R.string.ObesityClassII);
        }else /*if (BMI >= 40)*/ {
            return getString(R.string.ObesityClassIII);
        }
    }

}