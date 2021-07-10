package net.jozoproductions.blacksmithclicker.crates;

import android.widget.ScrollView;

import net.jozoproductions.blacksmithclicker.R;

public enum Crate {
    COMMON_CRATE("Common Crate", R.drawable.crate_common, R.color.rarity_common, 15f, 0f, 0f, 0.0f, 0.0f),
    UNCOMMON_CRATE("Uncommon Crate", R.drawable.crate_uncommon, R.color.rarity_uncommon, 55f, 10f, 1f, 0.0f, 0.0f),
    RARE_CRATE("Rare Crate", R.drawable.crate_rare, R.color.rarity_rare, 80f, 50f, 8f, 1f, 0.3f),
    EPIC_CRATE("Epic Crate", R.drawable.crate_epic, R.color.rarity_epic, 98f, 70f, 45f, 1f, 0.5f),
    LEGENDARY_CRATE("Legendary Crate", R.drawable.crate_legendary, R.color.rarity_legendary, 100f, 85f, 65.0f, 40.0f, 1f),
    MYTHIC_CRATE("Mythic Crate", R.drawable.crate_mythic, R.color.rarity_mythic, 100f, 100f, 85f, 70.0f, 5f);

    public String name;
    public int drawableId;
    public int colorId;

    public float UNCOMMON_CHANCE;
    public float RARE_CHANCE;
    public float EPIC_CHANCE;
    public float LEGENDARY_CHANCE;
    public float MYTHIC_CHANCE;

    Crate(String name, int drawableId, int colorId, float UNCOMMON_CHANCE, float RARE_CHANCE, float EPIC_CHANCE, float LEGENDARY_CHANCE, float MYTHIC_CHANCE) {
        this.name = name;
        this.drawableId = drawableId;
        this.colorId = colorId;

        this.UNCOMMON_CHANCE = UNCOMMON_CHANCE;
        this.RARE_CHANCE = RARE_CHANCE;
        this.EPIC_CHANCE = EPIC_CHANCE;
        this.LEGENDARY_CHANCE = LEGENDARY_CHANCE;
        this.MYTHIC_CHANCE = MYTHIC_CHANCE;
    }
}
