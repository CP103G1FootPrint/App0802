package com.example.molder.app0802;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
                Intent intent = new Intent(MainActivity.this, page3.class);
                startActivity(intent);
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* 呼叫isValid()並將EditText傳入，以檢查輸入是否正確。
                使用&而不使用&&代表第一個欄位即便輸入錯誤（會得到false），
                其他欄位仍舊會執行檢查的動作 */
                boolean isValid = isValid(etHeight) & isValid(etWeight);
                if (!isValid) {
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etHeight.addTextChangedListener(textWatcher);
        etWeight.addTextChangedListener(textWatcher);
    }

    /* 呼叫isValid()將EditText傳入 */
    private boolean isValid(EditText editText) {
        /* 檢查輸入文字的格式是否為0~100的整數 */
        String pattern = "[4][0]{2}|[1-3][0-9][0-9](\\.[0-9]+)?|[1-9][0-9](\\.[0-9]+)?|[1-9](\\.[0-9]+)?";
        String text = editText.getText().toString();
        if (!text.matches(pattern)) {
            /* 如果輸入不正確則呼叫setError()，
               將錯誤訊息顯示在EditText右邊，並回傳false */
            editText.setError("1 ~ 400");
            return false;
        } else {
            return true;
        }
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
