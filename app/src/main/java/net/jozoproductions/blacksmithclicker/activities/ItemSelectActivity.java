package net.jozoproductions.blacksmithclicker.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.recyclerview.ItemListRV;

public class ItemSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Select item");

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_item_select);

        //Initialize RecyclerView list
        RecyclerView itemList = findViewById(R.id.item_list);
        itemList.setLayoutManager(new LinearLayoutManager(this));

        ItemListRV itemListRV = new ItemListRV(this, Player.unlockedItemRecipes);
        itemList.setAdapter(itemListRV);
    }

}
