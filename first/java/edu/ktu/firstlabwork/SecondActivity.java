package edu.ktu.firstlabwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private ListView myList;
    private ListAdapter adapter;
    List<ListItem> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivitydesign);
        myList = (ListView) findViewById(R.id.listView);



        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)){
            items.add(new ListItem("Jack", R.drawable.ic_3d_rotation_black_48dp,"Mathematics, Chemistry"));
            items.add(new ListItem("Jane", R.drawable.ic_announcement_black_48dp,"Physics, Informatics"));
            items.add(new ListItem("Bob", R.drawable.ic_alarm_black_48dp,"Mathematics, Informatics"));
            items.add(new ListItem("Clara", R.drawable.ic_account_box_black_48dp,"Geography, Chemistry"));
            items.add(new ListItem("Sam", R.drawable.ic_accessibility_black_48dp,"Mathematics, Physics"));

        }else{
            items.add(new ListItem("Mathematics", R.drawable.ic_3d_rotation_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, " +
                    "Space and Change."));
            items.add(new ListItem("Physics", R.drawable.ic_announcement_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, " +
                            "Space and Change."));
            items.add(new ListItem("Chemistry", R.drawable.ic_alarm_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, " +
                            "Space and Change."));
            items.add(new ListItem("Informatics", R.drawable.ic_account_box_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, " +
                            "Space and Change."));
            items.add(new ListItem("Geography", R.drawable.ic_accessibility_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, " +
                            "Space and Change."));
        }
        String[] myTitles = intent.getStringArrayExtra("titleArray");
        String[] myDescriptions = intent.getStringArrayExtra("descriptionArray");
        int count = intent.getIntExtra("count", 0);
        if (count > 0){
            for (int i = 0; i < count; i++){
                items.add(new ListItem(myTitles[i], R.drawable.ic_add_a_photo_black_48dp, myDescriptions[i]));
            }
        }
        adapter = new ListAdapter(this, items);
        myList.setAdapter(adapter);
    }
}
