package net.jozoproductions.blacksmithclicker.items;

import net.jozoproductions.blacksmithclicker.R;

import java.util.ArrayList;

public enum Rarity {
    COMMON("Common", R.color.rarity_common),
    UNCOMMON("Uncommon", R.color.rarity_uncommon),
    RARE("Rare", R.color.rarity_rare),
    EPIC("Epic", R.color.rarity_epic),
    LEGENDARY("Legendary", R.color.rarity_legendary),
    MYTHIC("Mythic", R.color.rarity_mythic),
    CHRISTMAS("Christmas", R.color.white);

    public String name;
    public int colorId;

    public ArrayList<Item> items = new ArrayList<>();

    Rarity(String name, int colorId) {
        this.name = name;
        this.colorId = colorId;
    }

    public static Rarity NextRarity(Rarity rarity) {
        Rarity[] rarities = Rarity.values();

        for (int i = 0; i < rarities.length-1; i++) {
            if (rarities[i] == rarity) {
                return rarities[i+1];
            }
        }

        return null;
    }
}
