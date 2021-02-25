package ch.timonhueppi.m335.bmicalc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BMIService extends Service {
    // Binder given to clients
    private final IBinder binder = new LocalBinder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public BMIService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BMIService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    private Float weight;
    private Float height;

    public void setWeight(Float weight){
        this.weight = weight;
    }

    public Float getWeight(){
        return this.weight;
    }

    public void setHeight(Float height){
        this.height = height;
    }

    public Float getHeight(){
        return this.height;
    }

    public Float calcBMI(){
        Float BMI = this.weight/(this.height*this.height);
        return BMI;
    }
}