package com.example.molder.app0802;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class page3 extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        handleViews();
        loadBmi();
    }

    private void handleViews() {
        textView = findViewById(R.id.tvView);
    }

    private void loadBmi() {
        SharedPreferences preferences =
                getSharedPreferences("BMI", MODE_PRIVATE);
        float height = preferences.getFloat("height", Float.parseFloat("0.0"));
        float weight = preferences.getFloat("weight", Float.parseFloat("0.0"));
        String text = "Height = " + height + "\nWeight = " + weight;
        textView.setText(text);
    }

    public void onBack(View view){
        page3.this.finish();
    }
}
