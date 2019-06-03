package ca.beida.grades_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GradesResult extends AppCompatActivity {

    private static String TAG = "Grades_Report";
    int androidGrade = 0;
    int javaGrade = 0;
    int kotlinGrade = 0;
    int xmlGrade = 0;
    int average = 0;
    int minValue = 0;
    int maxValue = 0;
    int buttonIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_result);
        Log.i("TAG", "Jun Dang, GradesResult, 666-9999");
        getValueFromIntent();
        displayText();
    }

    private void getValueFromIntent() {
        androidGrade = getIntent().getIntExtra("adroidGrade", 0);
        javaGrade = getIntent().getIntExtra("javaGrade", 0);
        kotlinGrade = getIntent().getIntExtra("kotlinGrade", 0);
        xmlGrade = getIntent().getIntExtra("xmlGrade", 0);
        average = getIntent().getIntExtra("average", 0);
        minValue = getIntent().getIntExtra("minValue", 0);
        maxValue = getIntent().getIntExtra("maxValue", 0);
        buttonIndex = getIntent().getIntExtra("buttonIndex", 0);
    }

    private void displayText() {
        TextView androidGradeText = (TextView) findViewById(R.id.android_grade);
        TextView javaGradeText = (TextView) findViewById(R.id.java_grade);
        TextView kotlinGradeText = (TextView) findViewById(R.id.kotlin_grade);
        TextView xmlGradeText = (TextView) findViewById(R.id.xml_grade);
        TextView gradeText = (TextView) findViewById(R.id.grade);
        androidGradeText.setText(String.valueOf(androidGrade));
        javaGradeText.setText(String.valueOf(javaGrade));
        kotlinGradeText.setText(String.valueOf(kotlinGrade));
        xmlGradeText.setText(String.valueOf(xmlGrade));
        switch (buttonIndex) {
            case 0:
                gradeText.setText("Minium Score is: " + String.valueOf(minValue));
                break;
            case 1:
                gradeText.setText("Average Score is: " + String.valueOf(average));
                break;
            case 2:
                gradeText.setText("Maximum Score is: " + String.valueOf(maxValue));
                break;
            default:
                break;
        }

    }
}
