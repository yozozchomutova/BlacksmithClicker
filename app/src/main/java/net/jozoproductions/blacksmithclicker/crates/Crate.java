package net.jozoproductions.blacksmithclicker.crates;

import android.widget.ScrollView;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.rank.Rank;

public enum Crate {
    COMMON_CRATE("Common Crate", R.drawable.crate_common, R.color.rarity_common, Rank.BRONZE, 25f, 4f, 0f, 0.0f, 0.0f),
    UNCOMMON_CRATE("Uncommon Crate", R.drawable.crate_uncommon, R.color.rarity_uncommon, Rank.IRON, 75f, 30f, 4f, 0.0f, 0.0f),
    RARE_CRATE("Rare Crate", R.drawable.crate_rare, R.color.rarity_rare, Rank.GOLD, 100f, 80f, 20f, 2f, 0.0f), // Mythic = 0.3f
    EPIC_CRATE("Epic Crate", R.drawable.crate_epic, R.color.rarity_epic, Rank.PLATINUM, 100f, 93f, 60f, 11f, 0.0f), // Mythic = 0.5f
    LEGENDARY_CRATE("Legendary Crate", R.drawable.crate_legendary, R.color.rarity_legendary, Rank.DIAMOND, 100f, 100f, 80f, 65f, 0f), // Mythic = 1f
    MYTHIC_CRATE("Mythic Crate", R.drawable.crate_mythic, R.color.rarity_mythic, Rank.RUBY, 100f, 100f, 100f, 80f, 0f); // Mythic = 5f

    public String name;
    public int drawableId;
    public int colorId;

    public Rank minimalRequirementRank;

    public float UNCOMMON_CHANCE;
    public float RARE_CHANCE;
    public float EPIC_CHANCE;
    public float LEGENDARY_CHANCE;
    public float MYTHIC_CHANCE;

    Crate(String name, int drawableId, int colorId, Rank minimalRequirementRank, float UNCOMMON_CHANCE, float RARE_CHANCE, float EPIC_CHANCE, float LEGENDARY_CHANCE, float MYTHIC_CHANCE) {
        this.name = name;
        this.drawableId = drawableId;
        this.colorId = colorId;

        this.minimalRequirementRank = minimalRequirementRank;

        this.UNCOMMON_CHANCE = UNCOMMON_CHANCE;
        this.RARE_CHANCE = RARE_CHANCE;
        this.EPIC_CHANCE = EPIC_CHANCE;
        this.LEGENDARY_CHANCE = LEGENDARY_CHANCE;
        this.MYTHIC_CHANCE = MYTHIC_CHANCE;
    }
}
