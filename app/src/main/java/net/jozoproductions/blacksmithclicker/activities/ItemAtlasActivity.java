package net.jozoproductions.blacksmithclicker.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.items.ItemGroup;
import net.jozoproductions.blacksmithclicker.items.Rarity;
import net.jozoproductions.blacksmithclicker.views.AtlasItemViewGroup;

import org.w3c.dom.Text;

public class ItemAtlasActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;

    private ScrollView commonItems;
    private LinearLayout commonItemsList;

    private ScrollView uncommonItems;
    private LinearLayout uncommonItemsList;

    private ScrollView rareItems;
    private LinearLayout rareItemsList;

    private ScrollView epicItems;
    private LinearLayout epicItemsList;

    private ScrollView legendaryItems;
    private LinearLayout legendaryItemsList;

    private ScrollView mythicItems;
    private LinearLayout mythicItemsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_item_atlas);

        //Close activity listener
        findViewById(R.id.close_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Fetch views
        commonItems = findViewById(R.id.commonItems);
        commonItemsList = findViewById(R.id.commonItemsList);

        uncommonItems = findViewById(R.id.uncommonItems);
        uncommonItemsList = findViewById(R.id.uncommonItemsList);

        rareItems = findViewById(R.id.rareItems);
        rareItemsList = findViewById(R.id.rareItemsList);

        epicItems = findViewById(R.id.epicItems);
        epicItemsList = findViewById(R.id.epicItemsList);

        legendaryItems = findViewById(R.id.legendaryItems);
        legendaryItemsList = findViewById(R.id.legendaryItemsList);

        mythicItems = findViewById(R.id.mythicItems);
        mythicItemsList = findViewById(R.id.mythicItemsList);

        GenerateItemAtlas();

        //TabLayout
        tabLayout = findViewById(R.id.itemRarityTab);
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Loading completed
        MainActivity.loadingScreen.setVisibility(View.GONE);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        HideAllScrollViews();

        int tabPosition = tabLayout.getSelectedTabPosition();

        if (tabPosition == 0) {
            commonItems.setVisibility(View.VISIBLE);
        } else if (tabPosition == 1) {
            uncommonItems.setVisibility(View.VISIBLE);
        } else if (tabPosition == 2) {
            rareItems.setVisibility(View.VISIBLE);
        } else if (tabPosition == 3) {
            epicItems.setVisibility(View.VISIBLE);
        } else if (tabPosition == 4) {
            legendaryItems.setVisibility(View.VISIBLE);
        } else if (tabPosition == 5) {
            mythicItems.setVisibility(View.VISIBLE);
        }
    }

    @Override public void onTabUnselected(TabLayout.Tab tab) {}
    @Override public void onTabReselected(TabLayout.Tab tab) {}

    private void HideAllScrollViews() {
        commonItems.setVisibility(View.INVISIBLE);
        uncommonItems.setVisibility(View.INVISIBLE);
        rareItems.setVisibility(View.INVISIBLE);
        epicItems.setVisibility(View.INVISIBLE);
        legendaryItems.setVisibility(View.INVISIBLE);
        mythicItems.setVisibility(View.INVISIBLE);
    }

    private void GenerateItemAtlas() {
        ItemGroup[] itemGroups = ItemGroup.values();

        AddItemGroupToItemList(itemGroups, commonItemsList, Rarity.COMMON);
        AddItemGroupToItemList(itemGroups, uncommonItemsList, Rarity.UNCOMMON);
        AddItemGroupToItemList(itemGroups, rareItemsList, Rarity.RARE);
        AddItemGroupToItemList(itemGroups, epicItemsList, Rarity.EPIC);
        AddItemGroupToItemList(itemGroups, legendaryItemsList, Rarity.LEGENDARY);
        AddItemGroupToItemList(itemGroups, mythicItemsList, Rarity.MYTHIC);
    }

    private void AddItemGroupToItemList(ItemGroup[] itemGroups, LinearLayout itemList, Rarity rarity) {
        for (int i = 0; i < itemGroups.length; i++) {
            if (itemGroups[i].rarity == rarity) {
                //Actual list
                AtlasItemViewGroup atlasItemViewGroup = new AtlasItemViewGroup(this);
                atlasItemViewGroup.setItemGroup(itemGroups[i]);

                itemList.addView(atlasItemViewGroup);
            }
        }
    }
}
