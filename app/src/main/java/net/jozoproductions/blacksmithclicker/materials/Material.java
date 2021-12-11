package net.jozoproductions.blacksmithclicker.materials;

import net.jozoproductions.blacksmithclicker.R;

public enum Material {
    WOOD("Wood", 0, R.drawable.mat_wood),
    STONE("Stone", 2, R.drawable.mat_stone),
    IRON("Iron", 5, R.drawable.mat_iron),
    GOLD("Gold", 8, R.drawable.mat_gold),
    DIAMOND("Diamond", 12, R.drawable.mat_diamond),
    EMERALD("Emerald", 16, R.drawable.mat_emerald),
    RUBY("Ruby", 22, R.drawable.mat_ruby),

    TOPAZ("Topaz", 30, R.drawable.mat_topaz),
    SAPPHIRE("Sapphire", 35, R.drawable.mat_sapphire),
    AMETHYST("Amethyst", 40, R.drawable.mat_amethyst),

    CERUSSITE("Cerussite", 45, R.drawable.mat_cerussite),

    RED_ESSENCE("Red Essence", 50, R.drawable.mat_red_essence),
    ORANGE_ESSENCE("Orange Essence", 55, R.drawable.mat_orange_essence),
    YELLOW_ESSENCE("Yellow Essence", 60, R.drawable.mat_yellow_essence),
    GREEN_ESSENCE("Green Essence", 65, R.drawable.mat_green_essence),
    BLUE_ESSENCE("Blue Essence", 70, R.drawable.mat_blue_essence),
    MAGENTA_ESSENCE("Magenta Essence", 75, R.drawable.mat_magenta_essence),
    WHITE_ESSENCE("White Essence", 80, R.drawable.mat_white_essence),

    CRYSTALITE("Crysta-Lite", 90, R.drawable.mat_crystalite),
    GEOLITE("Geo-Lite", 100, R.drawable.mat_geolite),
    MONOLITE("Mono-Lite", 115, R.drawable.mat_monolite),
    IRIDIOLITE("Iridio-Lite",  130, R.drawable.mat_iridiolite),

    CATALYST("Catalyst", 155, R.drawable.mat_catalyst),
    NEONISTE("NEONiste", 170, R.drawable.mat_neoniste),
    LUNARTISM("Lunartism", 1, R.drawable.mat_lunartism),

    SUGAR("Sugar", 0, R.drawable.mat_sugar);
    ;

    public String name;
    public int requiredResearches;
    public int drawableId;

    public int curResearches;

    Material(String name, int requiredResearches, int drawableId) {
        this.name = name;
        this.requiredResearches = requiredResearches;

        this.drawableId = drawableId;
    }

    public boolean isResearched() {
        return curResearches >= requiredResearches;
    }
}
