package net.jozoproductions.blacksmithclicker.items;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public Bitmap itemTileBcg;

    Rarity(String name, int colorId) {
        this.name = name;
        this.colorId = colorId;
    }

    public static void init(Resources resources) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;

        Rarity.COMMON.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_common, opts);
        Rarity.UNCOMMON.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_uncommon, opts);
        Rarity.RARE.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_rare, opts);
        Rarity.EPIC.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_epic, opts);
        Rarity.LEGENDARY.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_legendary, opts);
        Rarity.MYTHIC.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_mythic, opts);
        Rarity.CHRISTMAS.itemTileBcg = BitmapFactory.decodeResource(resources, R.drawable.item_tile_common, opts);
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
