package ca.beida.grades_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AboutMenu extends AppCompatActivity {

    private static String TAG = "Grades_Calculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG", "Jun Dang, About, 666-9999");
        setContentView(R.layout.activity_about_menu);

    }
}
