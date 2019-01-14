package edu.ktu.firstlabwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    private Button thirdButton;
    private Button secondActivityButton;

    private Button addButton;
    private EditText addTitleText;
    private EditText addDescriptionText;
    private TextView addInfo;
    private int count = 0;
    private String[] titleArray = new String[100];
    private String[] descriptionArray = new String[100];
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanveState){
        super.onCreate(savedInstanveState);
        setContentView(R.layout.firstactivitydesign);

        thirdButton = (Button) findViewById(R.id.thirdActivityButton);
        thirdButton.setOnClickListener(myThirdButtonClick);

        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);

        addButton = (Button) findViewById(R.id.addButton);
        addTitleText = (EditText) findViewById(R.id.addTitleText);
        addDescriptionText = (EditText) findViewById(R.id.addDescriptionText);
        addInfo = (TextView) findViewById(R.id.addInfo);
        addButton.setOnClickListener(addButtonClick);

        secondActivityButton.setOnClickListener(startSecondActivity);
        secondActivityButton.setOnLongClickListener(startSecondActivityLong);
    }

    View.OnClickListener myThirdButtonClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(context, ThirdActivity.class);
            context.startActivity(intent);
        }
    };

    public void runSecondActivity(boolean b){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("titleArray", titleArray);
        intent.putExtra("descriptionArray", descriptionArray);
        intent.putExtra("count", count);
        intent.putExtra("flag",b);
        context.startActivity(intent);
    }

    View.OnClickListener startSecondActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            runSecondActivity(true);
        }
    };

    View.OnLongClickListener startSecondActivityLong = new View.OnLongClickListener(){
      @Override
      public boolean onLongClick(View v){
          runSecondActivity(false);
          return true;
      }
    };

    View.OnClickListener addButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = addTitleText.getText().toString();
            String description = addDescriptionText.getText().toString();

            titleArray[count] = title;
            descriptionArray[count] = description;
            count++;
            addInfo.setText(count + " Object is added successfully");
        }
    };
}
