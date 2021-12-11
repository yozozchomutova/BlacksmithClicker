package net.jozoproductions.blacksmithclicker.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.materials.MaterialProgressListRV;

import java.util.ArrayList;
import java.util.Arrays;

public class MaterialInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_materialinfo);

        //Listeners
        findViewById(R.id.close_activity).setOnClickListener(view -> finish());

        //Convert materials to list
        ArrayList<Material> materialList = new ArrayList<>();
        Material[] materialArray = Material.values();

        materialList.addAll(Arrays.asList(materialArray));

        //RecyclerView
        RecyclerView materialProgressListRV = findViewById(R.id.materialProgressList);
        materialProgressListRV.setLayoutManager(new LinearLayoutManager(this));
        materialProgressListRV.setAdapter(new MaterialProgressListRV(this, materialList));
    }
}
