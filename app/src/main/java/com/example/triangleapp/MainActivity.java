package com.example.triangleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.KeyEvent;
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

        // on key listeners...
        userValues.setOnKeyListener(setOnKeyEnter());

    }

    @Override
    public void onClick(View view) {

        // calculate button
        if (view == calculateBtn) {

            calculate();

        }

        //exit button
        if (view == exitBtn) {

            exitDialog();

        }


        // add other buttons here if needed...

    }

    private void exitDialog() {
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
        dialogBuilder.setTitle("Leaving Triangles App!");
        dialogBuilder.setMessage("Bye!");
        AlertDialog byeDialog = dialogBuilder.show();
        byeDialog.show();
    }

     private float[] parseUserInput() throws Exception {

         String userInput = userValues.getText().toString();

         if (userInput.equals("0")) return null; // the string equal to character 0, return null to exit app

         String[] stringArr;

         try {
             // the input string should contain a delimiter of "," or " ", anything else is an invalid delimiter for this app
             stringArr = userInput.contains(",") ? userInput.split(",") : userInput.split(" ");

             if (stringArr.length < 3)
                 throw new Exception("\nNot enough inputs"); // invalid input
             else if (stringArr.length > 3){
                 throw new Exception("\nToo many inputs"); // invalid input
             }

             // begin attempt to convert values to float
             float[] floatArr = new float[3];

             for (int i = 0; i < 3; ++i) {
                 floatArr[i] = Float.parseFloat(stringArr[i]); // throws exception if can not parse
                 if (floatArr[i] < 1.0 || floatArr[i] > 100.0)
                     throw new Exception("\nfloat out of range:\n (" + floatArr[i] + ")");
             }
             return floatArr;

         } catch (Exception e) {
             throw e; // invalid input
         }
     }

    private void calculate() {
        // attempt to parse the input
        long startTime = 0;

        String userInputForOutput = '[' + userValues.getText().toString() + "] = ";

        try {

            // get the start time of the operation
            startTime = System.currentTimeMillis();

            float[] floatArr = parseUserInput();
            if (floatArr == null) {
                // exit the user if he/she really wants to exit the app...
                exitDialog();
                return;
            }


            // Valid input; get results and display to screen
            resultsText.setText(userInputForOutput + new Triangle(floatArr).getTriangleType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // display invalid input
            resultsText.setText(userInputForOutput + e.getMessage() + "\nTry Again");
        }

        // display the total time taken to perform this operation...
        System.out.println("***** Elapsed Time " + ((System.currentTimeMillis() - startTime) / 1000.) + " seconds *****");
        //Log.d("***** Elapsed Time " + ((System.currentTimeMillis() - startTime) / 1000.) + " seconds *****");

    }

    private View.OnKeyListener setOnKeyEnter() {
        return new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                //If the keyevent is KEYCODE_ENTER
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    // simulate the press of the calculate button and call "calcaulate()"
                    calculate();
                    return true;
                }
                return false;
            }
        };
    }
}
