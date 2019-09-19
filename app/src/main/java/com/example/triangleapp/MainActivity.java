package com.example.triangleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userValues;
    private TextView resultsText;
    private Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        userValues = (EditText) findViewById(R.id.userValues);
        resultsText = (TextView) findViewById(R.id.resultsText);

        // on click listeners...
        calculateBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        // calculate button
        if (view == calculateBtn) {

            // attempt to parse the input
            float[] floatArr = parseUserInput();
            if (floatArr == null) {
                // disp error
                resultsText.setText("Invalid input.  Try Again");
                return;
            }
            // valid input, perform calculation
            resultsText.setText("Good Input");

        }
        // add other buttons here if needed...
    }

    public float[] parseUserInput() {

        String userInput = userValues.getText().toString();
        String[] stringArr;

        // the input string should contain a delimiter of "," or " ", anything else is an invalid delimiter for this app
        stringArr = userInput.contains(",") ? userInput.split(",") : userInput.split(" ");

        if (stringArr.length != 3) return null; // invalid input

        // begin attempt to convert values to float
        float[] floatArr = new float[3];

        for(int i = 0; i < 3; ++i) {
            try {
                floatArr[i] = Float.parseFloat(stringArr[i]); // throws exception if can not parse
                if (floatArr[i] < 1.0 || floatArr[i] > 100.0)
                    throw new Exception("float out of range (" + floatArr[i] + ")");
            }catch (Exception e) {
                System.out.println(e);
                return null; // invalid input
            }
        }
        return floatArr;
    }

}
