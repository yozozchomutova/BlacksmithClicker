package net.jozoproductions.blacksmithclicker.crates;

import android.widget.ScrollView;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.rank.Rank;
import net.jozoproductions.blacksmithclicker.research.Research;

public enum Crate {
    COMMON_CRATE("Common Crate", R.drawable.crate_common, R.color.rarity_common, Rank.BRONZE,               10f,    5f, 25f, 4f, 0f, 0.0f, 0.0f),
    UNCOMMON_CRATE("Uncommon Crate", R.drawable.crate_uncommon, R.color.rarity_uncommon, Rank.IRON,         50f,    10f, 75f, 30f, 4f, 0.0f, 0.0f),
    RARE_CRATE("Rare Crate", R.drawable.crate_rare, R.color.rarity_rare, Rank.GOLD,                         200,    25f, 100f, 80f, 20f, 2f, 0.3f), // Mythic = 0.3f
    EPIC_CRATE("Epic Crate", R.drawable.crate_epic, R.color.rarity_epic, Rank.PLATINUM,                     1000,   80f, 100f, 93f, 60f, 11f, 0.5f), // Mythic = 0.5f
    LEGENDARY_CRATE("Legendary Crate", R.drawable.crate_legendary, R.color.rarity_legendary, Rank.DIAMOND,  3000,   200f, 100f, 100f, 80f, 65f, 1f), // Mythic = 1f
    MYTHIC_CRATE("Mythic Crate", R.drawable.crate_mythic, R.color.rarity_mythic, Rank.RUBY,                 10500,  1000f, 100f, 100f, 100f, 80f, 20f), // Mythic = 20f
    CHRISTMAS_CRATE("Christmas Crate", R.drawable.crate_christmas, R.color.white, Rank.BRONZE,              100f,   20f, 0f, 0f, 0f, 0f, 0f);

    public String name;
    public int drawableId;
    public int colorId;

    public Rank minimalRequirementRank;

    public float baseCost;
    public float priceIncreaseRate;

    public int openCount = 0;

    public float UNCOMMON_CHANCE;
    public float RARE_CHANCE;
    public float EPIC_CHANCE;
    public float LEGENDARY_CHANCE;
    public float MYTHIC_CHANCE;

    Crate(String name, int drawableId, int colorId, Rank minimalRequirementRank, float baseCost, float priceIncreaseRate, float UNCOMMON_CHANCE, float RARE_CHANCE, float EPIC_CHANCE, float LEGENDARY_CHANCE, float MYTHIC_CHANCE) {
        this.name = name;
        this.drawableId = drawableId;
        this.colorId = colorId;

        this.minimalRequirementRank = minimalRequirementRank;

        this.baseCost = baseCost;
        this.priceIncreaseRate = priceIncreaseRate;

        this.UNCOMMON_CHANCE = UNCOMMON_CHANCE;
        this.RARE_CHANCE = RARE_CHANCE;
        this.EPIC_CHANCE = EPIC_CHANCE;
        this.LEGENDARY_CHANCE = LEGENDARY_CHANCE;
        this.MYTHIC_CHANCE = MYTHIC_CHANCE;
    }

    public float getRealPrice() {
        return (float) ((baseCost + priceIncreaseRate * openCount) / (1f + Research.CRATE_DISCOUNT.getEffect()));
    }
}
