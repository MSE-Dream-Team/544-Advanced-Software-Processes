package com.example.triangleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateBtn = (Button) findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText num1EditText = (EditText) findViewById(R.id.num1editText);
                EditText num2EditText = (EditText) findViewById(R.id.num2editText);
                EditText num3EditText = (EditText) findViewById(R.id.num3editText);

                TextView resultsText = (TextView) findViewById(R.id.resultsText);

                int num1 = Integer.parseInt(num1EditText.getText().toString());
                int num2 = Integer.parseInt(num2EditText.getText().toString());
                int num3 = Integer.parseInt(num3EditText.getText().toString());

                //need to test cases to see if all 3 sides even make a triangle
                //need to add cases to test all combination of sides

                if (num1 == num2 && num1 == num3){
                  resultsText.setText("Equilateral");
                }
                else if (num1 == num2 || num1 == num3){
                  resultsText.setText("Isosceles");
                }
                else{
                  resultsText.setText("Scalene");
                }
            }
        });
    }
}
