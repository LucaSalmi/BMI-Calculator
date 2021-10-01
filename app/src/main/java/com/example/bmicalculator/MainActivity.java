package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText weight_input;
    EditText height_input;
    TextView yourBmi;
    TextView yourBodyType;
    int ms = 500;
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

                onInput();
                return false;
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onInput();
            }
        });
    }

    /**
     * manages instructions when the button or enter on the keyboard are pressed
     */
    public void onInput(){

        getTextInput();

        if(!isEmpty){
            showToast(1);
            calculateBMI(userWeight, userHeight);
        }

        weight_input.getText().clear();
        height_input.getText().clear();

    }

    /**
     * converts and saves in string variables the input from the UI
     * it also checks if the input fields are empty and returns an error if it's the case
     */
    public void getTextInput(){

        String weightText = weight_input.getText().toString();
        String heightText = height_input.getText().toString();

        if(TextUtils.isEmpty(weightText) && TextUtils.isEmpty(heightText)){
            showToast(2);
            isEmpty = true;
            return;
        }else if(TextUtils.isEmpty(weightText)){
            showToast(3);
            isEmpty = true;
            return;
        }else if(TextUtils.isEmpty(heightText)){
            showToast(4);
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
        DecimalFormat df = new DecimalFormat("#.00");
        String print = df.format(bmi);
        String bmi_flavour_text = getResources().getString(R.string.bmi_flavour_text);
        pause(ms);
        yourBmi.setText(bmi_flavour_text + print);
        if ( bmi >= 30){
            yourBodyType.setText(R.string.obesity);
        }else if ( bmi >= 25){
            yourBodyType.setText(R.string.overweight);
        }else if (bmi >= 18.5){
            yourBodyType.setText(R.string.normal_weight);
        }else{
            yourBodyType.setText(R.string.underweight);
        }
    }

    /**
     * shows the user a toast message after pressing enter or the button.
     * displays a toast and error message if EnterText is empty
     * @param id used to identify what to show
     *           id 1 is after pressing button and entering values
     *           id 2 is if both EnterText are empty
     *           id 3 is if weight EnterText is empty
     *           id 4 is if height EnterText is empty
     */
    public void showToast(int id){

        switch (id){

            case 1:
                Toast.makeText(MainActivity.this,R.string.floating_message,Toast.LENGTH_SHORT).show();
                break;
            case 2:
                weight_input.setError("Error");
                height_input.setError("Error");
                Toast.makeText(MainActivity.this,R.string.both_fields_empty,Toast.LENGTH_SHORT).show();
                break;
            case 3:
                weight_input.setError("Error");
                Toast.makeText(MainActivity.this,R.string.weight_field_empty,Toast.LENGTH_SHORT).show();
                break;
            case 4:
                height_input.setError("Error");
                Toast.makeText(MainActivity.this,R.string.height_field_empty,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * pauses the program for a set amount of milliseconds ( it slows down showing the output to make it seem more "professional") ;)
     * @param ms defined at the start as int ms = 500
     */
    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }


}