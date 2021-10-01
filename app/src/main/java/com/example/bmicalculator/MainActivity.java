package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText weight_input;
    EditText height_input;
    TextView yourBmi;
    TextView yourBodyType;
    //final values for calculation
    int userWeight;
    double userHeight;
    // isEmpty is used in getTextInput()
    boolean isEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate = findViewById(R.id.calculate_button);
        yourBmi = findViewById(R.id.result_text);
        yourBodyType = findViewById(R.id.body_type_text);
        weight_input = findViewById(R.id.edit_text_weight);
        height_input = findViewById(R.id.edit_text_height);

        weight_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                Toast.makeText(MainActivity.this,R.string.floating_message,Toast.LENGTH_SHORT).show();
                getTextInput();

                if(!isEmpty){
                    calculateBMI(userWeight, userHeight);
                }

                weight_input.getText().clear();
                height_input.getText().clear();
                return false;
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,R.string.floating_message,Toast.LENGTH_SHORT).show();
                getTextInput();

                if(!isEmpty){
                    calculateBMI(userWeight, userHeight);
                }

                weight_input.getText().clear();
                height_input.getText().clear();
            }
        });
    }

    /**
     * converts and saves in string variables the input from the UI
     * it also checks if the input fields are empty and returns an error if it's the case
     */
    public void getTextInput(){

        String weightText = weight_input.getText().toString();
        String heightText = height_input.getText().toString();

        if(TextUtils.isEmpty(weightText) && TextUtils.isEmpty(heightText)){
            weight_input.setError("Field cannot be empty");
            height_input.setError("Field cannot be empty");
            isEmpty = true;
            return;
        }else if(TextUtils.isEmpty(weightText)){
            weight_input.setError("Field cannot be empty");
            isEmpty = true;
            return;
        }else if(TextUtils.isEmpty(heightText)){
            height_input.setError("Field cannot be empty");
            isEmpty = true;
            return;
        }

        convertToInt(weightText,heightText);
    }

    /**
     * the method converts my two strings in Int and saves them in two variables declared at the start of the program
     * @param a String weightText
     * @param b String heightText
     */
    public void convertToInt(String a, String b){
        userWeight = Integer.parseInt(a);
        userHeight = Double.parseDouble(b);
        if (userHeight >= 100){
            userHeight /= 100;
        }else if (userHeight >= 10){
            userHeight /= 10;
        }
    }

    /**
     * calculates the bmi value from weight and height
     * @param weight int value from user
     * @param height double value from user
     */
    public void calculateBMI(int weight, double height){
        double bmi = weight/Math.pow(height,2);
        printResult(bmi);
    }

    /**
     * converts result from calculateBMI() in string and shows it to user, then it checks value of bmi and modifies textView to show body type
     * @param bmi result of calculateBMI()
     */
    public void printResult(double bmi){
        DecimalFormat df = new DecimalFormat("#,00");
        String print = df.format(bmi);
        String bmi_flavour_text = getResources().getString(R.string.bmi_flavour_text);
        yourBmi.setText(bmi_flavour_text + print);
        if ( bmi >= 30){
            yourBodyType.setText(R.string.Obesity);
        }else if ( bmi >= 25){
            yourBodyType.setText(R.string.overweight);
        }else if (bmi >= 18.5){
            yourBodyType.setText(R.string.normal_weight);
        }else{
            yourBodyType.setText(R.string.underweight);
        }
    }
}