package com.example.molder.app0802;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        handleViews();
        loadBmi();
    }

    private void handleViews() {
        textView = findViewById(R.id.tvView);
    }

    private void loadBmi() {
        SharedPreferences preferences =
                getSharedPreferences("BMI", MODE_PRIVATE);
        float hieght = preferences.getFloat("height", Float.parseFloat("0.0"));
        float weight = preferences.getFloat("weight", Float.parseFloat("0.0"));

        double bmi;
        try {
            bmi = weight / (Math.pow(hieght * 0.01, 2));
        } catch (Exception e) {
            showToast(R.string.textError);
            return;
        }
        String text = String.format("BMI : %.1f \n",bmi);
        if (bmi < 18.5) {
            text += "Underweight";
        }
        if (18.5 <= bmi && bmi < 25) {
            text += "Normal weight";
        }
        if (25 <= bmi && bmi < 30) {
            text += "Overweight";
        }
        if (bmi >= 30) {
            text += "Obese";
        }
        textView.setText(text);
    }

    private void showToast(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    public void onBack(View view){
        Result.this.finish();
    }
}
