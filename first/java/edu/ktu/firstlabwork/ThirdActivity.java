package edu.ktu.firstlabwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextClock;
import android.widget.TextView;


public class ThirdActivity extends AppCompatActivity {
    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdactivitydesign);

        title = (TextView) findViewById(R.id.thirdtitle);
        description = (TextView) findViewById(R.id.thirddescription);

        title.setText("My image name");
        description.setText("My image description.");

    }
}
