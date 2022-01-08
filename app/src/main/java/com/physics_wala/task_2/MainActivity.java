package com.physics_wala.task_2;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    Button delete,add;
    EditText item;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        delete = findViewById(R.id.deleteItems);
        add=findViewById(R.id.addToList);
        item=findViewById(R.id.editText);
        data.add("item1");
        data.add("item2");
        data.add("item3");
        data.add("item4");
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, data);
        lv.setAdapter(adapter);

        delete.setOnClickListener(v -> {

            SparseBooleanArray checkedItemPositions = lv.getCheckedItemPositions();
            int itemCount = lv.getCount();

            for(int i=itemCount-1; i >= 0; i--){
                if(checkedItemPositions.get(i)){
                    adapter.remove(data.get(i));
                }
            }
            checkedItemPositions.clear();
            adapter.notifyDataSetChanged();
        });
        add.setOnClickListener(view -> {
            String itemString=item.getText().toString();
            if(itemString.equals("")){
                Toast.makeText(MainActivity.this, "Please type anything to add to list.", Toast.LENGTH_SHORT).show();
                return;
            }
            data.add(itemString);
            adapter.notifyDataSetChanged();
        });
    }
}