package net.jozoproductions.blacksmithclicker.materials;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;

public enum Material {
    WOOD("Wood", R.drawable.mat_wood),
    STONE("Stone", R.drawable.mat_stone),
    IRON("Iron", R.drawable.mat_iron),
    GOLD("Gold", R.drawable.mat_gold),
    DIAMOND("Diamond", R.drawable.mat_diamond),
    EMERALD("Emerald", R.drawable.mat_emerald),
    RUBY("Ruby", R.drawable.mat_ruby),

    TOPAZ("Topaz", R.drawable.mat_topaz),
    SAPPHIRE("Sapphire", R.drawable.mat_sapphire),
    AMETHYST("Amethyst", R.drawable.mat_amethyst),

    CERUSSITE("Cerussite", R.drawable.mat_cerussite),

    RED_ESSENCE("Red Essence", R.drawable.mat_red_essence),
    ORANGE_ESSENCE("Orange Essence", R.drawable.mat_orange_essence),
    YELLOW_ESSENCE("Yellow Essence", R.drawable.mat_yellow_essence),
    GREEN_ESSENCE("Green Essence", R.drawable.mat_green_essence),
    BLUE_ESSENCE("Blue Essence", R.drawable.mat_blue_essence),
    MAGENTA_ESSENCE("Magenta Essence", R.drawable.mat_magenta_essence),
    WHITE_ESSENCE("White Essence", R.drawable.mat_white_essence),
    ;

    public String name;
    public int drawableId;

    Material(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }
}
