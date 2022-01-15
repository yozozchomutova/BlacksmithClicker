package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.materials.MaterialProgressListRV;
import net.jozoproductions.blacksmithclicker.views.MaterialOfferView;

import java.util.ArrayList;
import java.util.Arrays;

public class MaterialInfoActivity extends AppCompatActivity {

    private ConstraintLayout researchProgressPanel;
    private ConstraintLayout materialMarketOffers;

    private TextView marketChangeTimer;

    private LinearLayout positiveOffersLL;
    private LinearLayout negativeOffersLL;

    private TabLayout panelSwitch;

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

        panelSwitch = findViewById(R.id.panelSwitch);
        panelSwitch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                researchProgressPanel.setVisibility(View.GONE);
                materialMarketOffers.setVisibility(View.GONE);

                if (panelSwitch.getSelectedTabPosition() == 0) {
                    researchProgressPanel.setVisibility(View.VISIBLE);
                } else if (panelSwitch.getSelectedTabPosition() == 1) {
                    materialMarketOffers.setVisibility(View.VISIBLE);
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        findViewById(R.id.refreshBtn).setOnClickListener(view -> refreshMaterialOffers());

        //Convert materials to list
        ArrayList<Material> materialList = new ArrayList<>();
        Material[] materialArray = Material.values();

        materialList.addAll(Arrays.asList(materialArray));

        //Get views
        researchProgressPanel = findViewById(R.id.researchProgressPanel);
        materialMarketOffers = findViewById(R.id.materialValuesPanel);
        positiveOffersLL = findViewById(R.id.positiveOffers);
        negativeOffersLL = findViewById(R.id.negativeOffers);

        marketChangeTimer = findViewById(R.id.newMarketTimer);

        //RecyclerView
        RecyclerView materialProgressListRV = findViewById(R.id.materialProgressList);
        materialProgressListRV.setLayoutManager(new LinearLayoutManager(this));
        materialProgressListRV.setAdapter(new MaterialProgressListRV(this, materialList));

        refreshMaterialOffers();
    }

    private void refreshMaterialOffers() {
        marketChangeTimer.setText("Next change: " + ((int) ((Material.MaterialMarket.nextOfferTime - System.currentTimeMillis()) / 600f)) / 100f + " minutes.");

        positiveOffersLL.removeAllViews();
        negativeOffersLL.removeAllViews();

        Material[] positiveMatOffers = Material.MaterialMarket.positiveMatOffer;
        Material[] negativeMatOffers = Material.MaterialMarket.negativeMatOffer;

        //Add to layout
        if (positiveMatOffers != null) {
            for (int i = 0; i < positiveMatOffers.length; i++) {
                MaterialOfferView materialOfferView = new MaterialOfferView(this);
                materialOfferView.init(positiveMatOffers[i], true);
                positiveOffersLL.addView(materialOfferView);
            }

            for (int i = 0; i < negativeMatOffers.length; i++) {
                MaterialOfferView materialOfferView = new MaterialOfferView(this);
                materialOfferView.init(negativeMatOffers[i], false);
                negativeOffersLL.addView(materialOfferView);
            }
        } else {
            Toast.makeText(this, "Error with making material offers", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }
}
