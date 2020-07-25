package com.example.todo_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class AddItemActivity extends AppCompatActivity {

    SharedPreferences dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        dataSource = getSharedPreferences("todo items", Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                EditText itemName = findViewById(R.id.itemspace);
                String item_entered = itemName.getText().toString();
                if (item_entered.equals("")) {
                    Toast.makeText(this, "No item name given", Toast.LENGTH_SHORT).show();
                    return true;
                }

                Set<String> items = dataSource.getStringSet("items", new HashSet<String>());
                assert items != null;

                items.add(item_entered);

                dataSource.edit().clear().putStringSet("items", items).apply();

                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
