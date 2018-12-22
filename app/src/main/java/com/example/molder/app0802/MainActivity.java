package com.example.molder.app0802;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight,etWeight;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleViews();
    }

    private void handleViews() {
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,page3.class);
                startActivity(intent);
            }
        });
    }

    public void onSubmitClick(View view){

        SharedPreferences preferences = getSharedPreferences("BMI",MODE_PRIVATE);
        float height , weight;
        height = Float.parseFloat(etHeight.getText().toString());
        weight = Float.parseFloat(etWeight.getText().toString());
//        Bmi bmi = new Bmi(height,weight);
        preferences.edit()
                .putFloat("height", height)
                .putFloat("weight", weight)
                .apply();
        showToast(R.string.textBMISaved);
        Intent intent = new Intent(MainActivity.this,Result.class);
        startActivity(intent);

    }

    private void showToast(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


}
