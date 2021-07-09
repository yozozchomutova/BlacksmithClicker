package net.jozoproductions.blacksmithclicker.items;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public enum Item {
    STICK("Stick", Rarity.COMMON, 3, 1, R.drawable.item_stick, new Ingredient[]{
                    new Ingredient(Material.WOOD, 3)
    }),
    STONE_STICK("Stone Stick", Rarity.COMMON, 3, 2, R.drawable.item_stone_stick, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    IRON_STICK("Iron Stick", Rarity.COMMON, 6, 4, R.drawable.item_iron_stick, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    GOLDEN_STICK("Golden Stick", Rarity.COMMON, 3, 2, R.drawable.item_golden_stick, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    DIAMOND_STICK("Diamond Stick", Rarity.COMMON, 3, 2, R.drawable.item_diamond_stick, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    EMERALD_STICK("Emerald Stick", Rarity.COMMON, 3, 2, R.drawable.item_emerald_stick, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    RUBY_STICK("Ruby Stick", Rarity.COMMON, 3, 2, R.drawable.item_ruby_stick, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    STONE_DAGGER("Stone Dagger", Rarity.COMMON, 3, 2, R.drawable.item_stone_dagger, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    IRON_DAGGER("Iron Dagger", Rarity.COMMON, 3, 2, R.drawable.item_iron_dagger, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    GOLDEN_DAGGER("Golden Dagger", Rarity.COMMON, 3, 2, R.drawable.item_golden_dagger, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    DIAMOND_DAGGER("Diamond Dagger", Rarity.COMMON, 3, 2, R.drawable.item_diamond_dagger, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    EMERALD_DAGGER("Emerald Dagger", Rarity.COMMON, 3, 2, R.drawable.item_emerald_dagger, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    RUBY_DAGGER("Ruby Dagger", Rarity.COMMON, 3, 2, R.drawable.item_ruby_dagger, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    STONE_WSWORD("Stone Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_stone_wsword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    IRON_WSWORD("Iron Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_iron_wsword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    GOLDEN_WSWORD("Golden Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_golden_wsword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    DIAMOND_WSWORD("Diamond Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_diamond_wsword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    EMERALD_WSWORD("Emerald Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_emerald_wsword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    RUBY_WSWORD("Ruby Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_ruby_wsword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    IRON_ISWORD("Iron Iron Sword", Rarity.COMMON, 3, 2, R.drawable.item_iron_isword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    GOLDEN_ISWORD("Golden Iron Sword", Rarity.COMMON, 3, 2, R.drawable.item_golden_isword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    DIAMOND_ISWORD("Diamond Iron Sword", Rarity.COMMON, 3, 2, R.drawable.item_diamond_isword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    EMERALD_ISWORD("Emerald Iron Sword", Rarity.COMMON, 3, 2, R.drawable.item_emerald_isword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    }),
    RUBY_ISWORD("Ruby Iron Sword", Rarity.COMMON, 3, 2, R.drawable.item_ruby_isword, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    })
    ;

    public String name;
    public Rarity rarity;
    public int requiredClicks;
    public int iconId;
    public int sellPrice;
    public Ingredient[] ingredients;

    Item(String name, Rarity rarity, int requiredClicks, int sellPrice, int iconId, Ingredient[] ingredients) {
        this.name = name;
        this.rarity = rarity;
        this.requiredClicks = requiredClicks;
        this.iconId = iconId;
        this.sellPrice = sellPrice;
        this.ingredients = ingredients;
    }

    public static void InitializeRarityItemsArrayLists() {
        Item[] items = Item.values();
        Rarity[] rarities = Rarity.values();

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < rarities.length; j++) {
                if (items[i].rarity == rarities[j]) {
                    rarities[j].items.add(items[i]);
                    break;
                }
            }
        }
    }
}
