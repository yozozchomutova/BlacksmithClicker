package net.jozoproductions.blacksmithclicker.activities;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.research.Research;
import net.jozoproductions.blacksmithclicker.research.ResearchNodeView;

public class ResearchActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView researchText;

    //Research panel views
    private ResearchNodeView lastResearchNodeView;

    private ConstraintLayout researchPanel;

    private TextView researchName;
    private TextView researchPrice;
    private TextView researchDescription;

    private ImageButton closePanel;
    private ImageButton researchBuy;

    private ImageView researchIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_research);

        researchText = findViewById(R.id.research_text);

        //Center
        FrameLayout researchMap = findViewById(R.id.research_nodes);
        researchMap.setTranslationX(-4000f + MainActivity.SCREEN_WIDTH/2f);
        researchMap.setTranslationY(-4000f + MainActivity.SCREEN_HEIGHT/2f);
        researchMap.setOnTouchListener(new View.OnTouchListener() {
            private float lastPosX;
            private float lastPosY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    lastPosX = event.getRawX();
                    lastPosY = event.getRawY();
                } else if (action == MotionEvent.ACTION_MOVE) {
                    float newPosX = event.getRawX();
                    float newPosY = event.getRawY();

                    researchMap.setTranslationX(researchMap.getTranslationX() + (newPosX - lastPosX));
                    researchMap.setTranslationY(researchMap.getTranslationY() + (newPosY - lastPosY));

                    lastPosX = newPosX;
                    lastPosY = newPosY;

                    System.out.println("X: " + researchMap.getTranslationX());
                    System.out.println("Y: " + researchMap.getTranslationY());
                }

                return true;
            }
        });

        //Research panel views fetch
        researchPanel = findViewById(R.id.research_panel);

        researchName = findViewById(R.id.research_name);
        researchPrice = findViewById(R.id.research_price);
        researchDescription = findViewById(R.id.research_description);

        closePanel = findViewById(R.id.close_panel);
        researchBuy = findViewById(R.id.research_buy);

        researchIcon = findViewById(R.id.research_icon);

        //Initialize
        findViewById(R.id.close_activity).setOnClickListener(this);
        closePanel.setOnClickListener(this);
        researchBuy.setOnClickListener(this);

        UpdateResearchPoints();
        InitializeResearchNodes();
    }

    public void UpdateResearchPoints() {
        researchText.setText("" + Player.researchPoints);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.close_activity) {
            finish();
        } else if (viewId == R.id.close_panel) {
            researchPanel.startAnimation(AnimationUtils.loadAnimation(this, R.anim.reserach_panel_disappear));
        } else if (viewId == R.id.research_buy) {
            if (lastResearchNodeView.research.researchPointsCost <= Player.researchPoints) {
                Player.researchPoints -= lastResearchNodeView.research.researchPointsCost;
                closePanel.performClick();

                lastResearchNodeView.research.researched = true;

                UpdateResearchPoints();
                InitializeResearchNodes();
                Player.CalculateResearchUpgrades();
            } else {
                Toast.makeText(this, "Not enough research points.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void AppearResearchPanel(ResearchNodeView researchNodeView) {
        this.lastResearchNodeView = researchNodeView;

        Research research = researchNodeView.research;

        researchName.setText(research.name);
        researchDescription.setText(research.description);
        researchPrice.setText("" + research.researchPointsCost);
        researchIcon.setImageDrawable(ContextCompat.getDrawable(this, research.drawableId));
        researchBuy.setVisibility(research.researched || !research.requiredResearch.researched ? View.GONE : View.VISIBLE);

        researchPanel.startAnimation(AnimationUtils.loadAnimation(this, R.anim.reserach_panel_appear));
    }

    private void InitializeResearchNodes() {
        ((ResearchNodeView) findViewById(R.id.blacksmithStartup)).setResearch(Research.BLACKSMITH);

        ((ResearchNodeView) findViewById(R.id.biggerResearchChance1)).setResearch(Research.MORE_RESEARCHPOINTS_CHANCE1);
        ((ResearchNodeView) findViewById(R.id.biggerResearchChance2)).setResearch(Research.MORE_RESEARCHPOINTS_CHANCE2);
        ((ResearchNodeView) findViewById(R.id.biggerResearchChance3)).setResearch(Research.MORE_RESEARCHPOINTS_CHANCE3);
        ((ResearchNodeView) findViewById(R.id.biggerResearchChance4)).setResearch(Research.MORE_RESEARCHPOINTS_CHANCE4);

        ((ResearchNodeView) findViewById(R.id.forgeEffectivity1)).setResearch(Research.FORGE_EFFECTIVNESS1);
        ((ResearchNodeView) findViewById(R.id.forgeEffectivity2)).setResearch(Research.FORGE_EFFECTIVNESS2);
        ((ResearchNodeView) findViewById(R.id.forgeEffectivity3)).setResearch(Research.FORGE_EFFECTIVNESS3);
    }
}
