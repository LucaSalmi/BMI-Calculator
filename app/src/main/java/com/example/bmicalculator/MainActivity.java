package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText weight_input;
    EditText height_input;
    TextView yourBmi;
    int userWeight;
    double userHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate = findViewById(R.id.calculate_button);
        yourBmi = findViewById(R.id.result_text);
        weight_input = findViewById(R.id.edit_text_weight);
        height_input = findViewById(R.id.edit_text_height);

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getTextInput();
                calculateBMI(userWeight, userHeight);
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
        if(TextUtils.isEmpty(weightText) || TextUtils.isEmpty(heightText)){
            weight_input.setError("Field cannot be empty");
            height_input.setError("Field cannot be empty");
            return;
        }

        convertToInt(weightText,heightText);
    }

    /**
     * the method converts my två strings in Int and saves them in two variables declared at the start of the program
     * @param a String weightText
     * @param b String heightText
     */
    public void convertToInt(String a, String b){
        userWeight = Integer.parseInt(a);
        userHeight = Double.parseDouble(b);
    }

    /**
     * calculates the bmi value from weight and height
     * @param weight
     * @param height
     */
    public void calculateBMI(int weight, double height){
        double bmi = weight/Math.pow(height,2);
        printResult(bmi);
    }

    /**
     * converts result in string and shows it to user
     * @param bmi
     */
    public void printResult(double bmi){
        DecimalFormat df = new DecimalFormat("#,00");
        String print = df.format(bmi);
        yourBmi.setText(print);
    }

}