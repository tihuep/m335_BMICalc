package ch.timonhueppi.m335.bmicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonHandler();
    }

    private void setButtonHandler(){
        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        });
    }
}