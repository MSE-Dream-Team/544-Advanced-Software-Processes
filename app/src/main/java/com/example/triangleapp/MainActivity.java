package com.example.triangleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.DialogInterface;
import android.app.AlertDialog;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userValues;
    private TextView resultsText;
    private Button calculateBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        userValues = (EditText) findViewById(R.id.userValues);
        resultsText = (TextView) findViewById(R.id.resultsText);
        exitBtn = (Button) findViewById(R.id.exitButton);

        // on click listeners...
        calculateBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);

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
            // Valid input; get results and display to screen
            resultsText.setText(new Triangle(floatArr).getTriangleType());
        }

        //exit button
        if (view == exitBtn) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure you want to exit?");
            builder.setPositiveButton("Exit now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    byeDialog();
                    exitApp(2000);

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }


        // add other buttons here if needed...

    }

    private void exitApp(int delay) {
        Handler handler=new Handler();
        Runnable r=new Runnable() {
            public void run() {
                // this action will occur after the specified delay time
                finish();
            }
        };
        handler.postDelayed(r, delay);
    }

    private void byeDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setTitle("Bye!");
        dialogBuilder.setMessage("Bye!");
        AlertDialog byeDialog = dialogBuilder.show();
        byeDialog.show();
    }

     private float[] parseUserInput() {

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
