package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Results extends AppCompatActivity {
    ListView resultsList;
    ResultsRegister[] allResults = new ResultsRegister[30];
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsList = findViewById(R.id.resultsList);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_results, allResults);

        resultsList.setAdapter(adapter);


    }
}

class ResultsRegister extends Results{

    private int bmi;
    private String name;
    private String dateTime;


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    ResultsRegister(int bmi, String name){
        this.bmi = bmi;
        this.dateTime = dateFormat.format(new Date());
        this.name = name;
        allResults[super.position] = this;
    }

}
