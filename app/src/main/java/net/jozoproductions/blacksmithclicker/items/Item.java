package net.jozoproductions.blacksmithclicker.items;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

public enum Item {
    STICK("Stick", 10, 3, R.drawable.item_stick, new Ingredient[]{
                    new Ingredient(Material.WOOD, 3)
    }),
    STAR("Star", 3, 20, R.drawable.particle_star, new Ingredient[]{
            new Ingredient(Material.WOOD, 3)
    })
    ;

    public String name;
    public int requiredClicks;
    public int iconId;
    public int sellPrice;
    public Ingredient[] ingredients;

    Item(String name, int requiredClicks, int sellPrice, int iconId, Ingredient[] ingredients) {
        this.name = name;
        this.requiredClicks = requiredClicks;
        this.iconId = iconId;
        this.sellPrice = sellPrice;
        this.ingredients = ingredients;
    }
}
