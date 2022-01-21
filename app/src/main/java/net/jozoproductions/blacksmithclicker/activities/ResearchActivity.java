package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.ResearchesListRV;
import net.jozoproductions.blacksmithclicker.research.Research;

import java.util.ArrayList;
import java.util.Arrays;

public class ResearchActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView researchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_research);

        researchText = findViewById(R.id.research_text);

        //Convert research items to list
        ArrayList<Research> researchList = new ArrayList<>();
        Research[] researchArray = Research.values();

        researchList.addAll(Arrays.asList(researchArray));

        //Recycler view
        ResearchesListRV rvAdapter = new ResearchesListRV(this, researchList);

        RecyclerView researchItemsRV = findViewById(R.id.research_list);
        researchItemsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        researchItemsRV.setAdapter(rvAdapter);

        //Initialize
        findViewById(R.id.close_activity).setOnClickListener(this);

        UpdateResearchPoints();
    }

    public void UpdateResearchPoints() {
        researchText.setText("" + Player.researchPoints);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.close_activity) {
            Player.CalculateResearchUpgrades();
            finish();
        }
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }
}