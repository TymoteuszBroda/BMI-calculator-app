package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();

    }



    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }
    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int age = Integer.parseInt(ageEditText.getText().toString());
                if (age >= 18)
                {
                    displayResult(calculateBmi());
                }else {
                    displayGuidance(calculateBmi());
                }
            }
        });
    }

    private double calculateBmi()
    {
        String feetText = feetEditText.getText().toString();
        String inchesText= inchesEditText.getText().toString();
        String weightText  = weightEditText.getText().toString();

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet*12)+inches;
        double heightInMeters = totalInches*0.0254;

        return weight/(heightInMeters*heightInMeters);
    }
    private void displayResult(double bmi)
    {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextValue = myDecimalFormatter.format(bmi);
        String fullResultString;

        if (bmi<18.5)
        {
            fullResultString = bmiTextValue+" - You are underweight";
        }
        else if (bmi>25)
        {
            fullResultString = bmiTextValue+" - You are overweight";
        }
        else
        {
            fullResultString = bmiTextValue+" - You are a healthy weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi)
    {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextValue = myDecimalFormatter.format(bmi);
        String fullResultString;

        if (maleRadioButton.isChecked())
        {
            fullResultString = bmiTextValue+" - as you are under 18, please consult with your doctor for the healthy range for boys.";
        } else if (femaleRadioButton.isChecked()) {
            fullResultString = bmiTextValue+" - as you are under 18, please consult with your doctor for the healthy range for girls.";
        }else
        {
            fullResultString = bmiTextValue+" - as you are under 18, please consult with your doctor for the healthy range.";
        }
        resultText.setText(fullResultString);
    }


}