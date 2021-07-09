package net.jozoproductions.blacksmithclicker.activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;

public class ResearchActivity extends AppCompatActivity {

    private TextView researchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Research");

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_research);

        researchText = findViewById(R.id.research_text);

        UpdateResearchPoints();
    }

    public void UpdateResearchPoints() {
        researchText.setText("" + Player.researchPoints);
    }
}
