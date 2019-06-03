package ca.beida.grades_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "Grades_Calculator";
    private List<SchoolItem> schoolList;

    int androidGrade = 0;
    int javaGrade = 0;
    int kotlinGrade = 0;
    int xmlGrade = 0;
    int buttonIndex = 0;
    ArrayList<Integer> numList;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Jun Dang, 666-888-999");
        setContentView(R.layout.activity_main);
        fillSchoolList();
        View avgBtn = findViewById(R.id.calculateBtn);
        avgBtn.setOnClickListener(this);
        View minBtn = findViewById(R.id.minBtn);
        minBtn.setOnClickListener(this);
        View maxBtn = findViewById(R.id.maxBtn);
        maxBtn.setOnClickListener(this);
        AutoCompleteTextView editText = findViewById(R.id.actv);
        AutoCompleteSchoolAdapter adapter = new AutoCompleteSchoolAdapter(this, schoolList);
        editText.setAdapter(adapter);
        numList = new ArrayList<>();
        i = new Intent(this, GradesResult.class);

    }
// Autocomplete adapter array;
    private void fillSchoolList() {
        schoolList = new ArrayList<>();
        schoolList.add(new SchoolItem("Seneca Valley College", R.drawable.senecavalley));
        schoolList.add(new SchoolItem("Harrington High", R.drawable.harriton));
        schoolList.add(new SchoolItem("Hummingbird Academy", R.drawable.humingbird));
        schoolList.add(new SchoolItem("Georgian Bay College", R.drawable.georgian));
        schoolList.add(new SchoolItem("Saracuse Academy", R.drawable.syracuse));
        schoolList.add(new SchoolItem("St-Vincent College", R.drawable.st_vincent_college));
        schoolList.add(new SchoolItem("Seneca College", R.drawable.seneca));
        schoolList.add(new SchoolItem("Seneca York", R.drawable.senecayork));
        schoolList.add(new SchoolItem("Seneca North", R.drawable.senecanorth));
    }
// for data processing;
    private void dataBrain() {
        EditText androidText = (EditText) findViewById(R.id.android_text);
        String androidGradeString = androidText.getText().toString();
        Log.i("androidGradeString: ", androidGradeString);
        EditText javaText = findViewById(R.id.java_text);
        String javaGradeString = javaText.getText().toString();
        EditText kotlinText = findViewById(R.id.kotlin_text);
        String kotlinGradeString = kotlinText.getText().toString();
        EditText xmlText = findViewById(R.id.xml_text);
        String xmlGradeString = xmlText.getText().toString();
        androidGrade = (int) Math.round(Double.parseDouble(androidGradeString));
        javaGrade = (int) Math.round(Double.parseDouble(javaGradeString));
        kotlinGrade = (int) Math.round(Double.parseDouble(kotlinGradeString));
        xmlGrade = (int) Math.round(Double.parseDouble(xmlGradeString));

        numList.add(androidGrade);
        numList.add(javaGrade);
        numList.add(kotlinGrade);
        numList.add(xmlGrade);


        i.putExtra("adroidGrade", androidGrade);
        i.putExtra("javaGrade", javaGrade);
        i.putExtra("kotlinGrade", kotlinGrade);
        i.putExtra("xmlGrade", xmlGrade);
    }
//calcuate the average grade and pass the data within the same Intent object;
    private void calculateAverageAndPassData() {
        int average = Math.round((androidGrade + javaGrade + kotlinGrade + xmlGrade)/4);
        buttonIndex = 1;
        i.putExtra("average", average);
        i.putExtra("buttonIndex", buttonIndex);
        startActivity(i);
    }
//obtain the minium grade and pass the data within the same Intent object;
    private void getMinGradeAndPassData() {
        int minValue = findMiniumFromArrayList(numList);
        buttonIndex = 0;
        i.putExtra("minValue", minValue);
        i.putExtra("buttonIndex", buttonIndex);
        startActivity(i);
    }
//obtain the maxium grade and pass the data within the same Intent object;
    private void getMaxGradeAndPassData() {
        int maxValue = findMaximumFromArrayList(numList);
        buttonIndex = 2;
        i.putExtra("maxValue", maxValue);
        i.putExtra("buttonIndex", buttonIndex);
        startActivity(i);
    }
//Button onClick listener;
   @Override
   public void onClick(View v) {
       dataBrain();

       switch (v.getId()) {
           case R.id.calculateBtn:
               calculateAverageAndPassData();
               break;
           case R.id.minBtn:
               getMinGradeAndPassData();
               break;
           case R.id.maxBtn:
               getMaxGradeAndPassData();
               break;
           default:
               break;

       }


   }
   //helper method to find the minimum number from an arraylist;
   private int findMiniumFromArrayList(ArrayList<Integer> numberList) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i) < minValue) {
                minValue = numberList.get(i);
            }
        }
        return minValue;
   }
    //helper method to find the maximum number from an arraylist;
    private int findMaximumFromArrayList(ArrayList<Integer> numberList) {
        int maxValue = -Integer.MAX_VALUE;
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i) > maxValue) {
                maxValue = numberList.get(i);
            }
        }
        return maxValue;
    }
  // menu method;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent aboutIntent = new Intent(this, AboutMenu.class);
            startActivity(aboutIntent);
        } else {
            dataBrain();
            switch (id) {
                case R.id.avg:
                    dataBrain();
                    calculateAverageAndPassData();
                    break;
                case R.id.min:
                    dataBrain();
                    getMinGradeAndPassData();
                    break;
                case R.id.max:
                    dataBrain();
                    getMaxGradeAndPassData();
                    break;
                default:
                    break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
