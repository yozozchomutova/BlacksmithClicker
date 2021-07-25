package net.jozoproductions.blacksmithclicker.items;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public enum Item {
    STICK("Stick", Rarity.COMMON, 6, 1, R.drawable.item_stick, Material.WOOD),
    STONE_STICK("Stone Stick", Rarity.COMMON, 10, 2, R.drawable.item_stone_stick, Material.STONE),
    IRON_STICK("Iron Stick", Rarity.COMMON, 10, 3, R.drawable.item_iron_stick, Material.IRON),
    GOLDEN_STICK("Golden Stick", Rarity.COMMON, 20, 3, R.drawable.item_golden_stick, Material.GOLD),
    DIAMOND_STICK("Diamond Stick", Rarity.COMMON, 25, 4, R.drawable.item_diamond_stick, Material.DIAMOND),
    EMERALD_STICK("Emerald Stick", Rarity.COMMON, 30, 5, R.drawable.item_emerald_stick, Material.EMERALD),
    RUBY_STICK("Ruby Stick", Rarity.COMMON, 30, 6, R.drawable.item_ruby_stick, Material.RUBY),

    STONE_DAGGER("Stone Dagger", Rarity.COMMON, 34, 6, R.drawable.item_stone_dagger, Material.STONE),
    IRON_DAGGER("Iron Dagger", Rarity.COMMON, 27, 4, R.drawable.item_iron_dagger, Material.IRON),
    GOLDEN_DAGGER("Golden Dagger", Rarity.COMMON, 25, 4, R.drawable.item_golden_dagger, Material.GOLD),
    DIAMOND_DAGGER("Diamond Dagger", Rarity.COMMON, 25, 5, R.drawable.item_diamond_dagger, Material.DIAMOND),
    EMERALD_DAGGER("Emerald Dagger", Rarity.COMMON, 30, 7, R.drawable.item_emerald_dagger, Material.EMERALD),
    RUBY_DAGGER("Ruby Dagger", Rarity.COMMON, 30, 8, R.drawable.item_ruby_dagger, Material.RUBY),

    STONE_WSWORD("Stone Wooden Sword", Rarity.COMMON, 40, 15, R.drawable.item_stone_wsword, Material.STONE),
    IRON_WSWORD("Iron Wooden Sword", Rarity.COMMON, 39, 15, R.drawable.item_iron_wsword, Material.IRON),
    GOLDEN_WSWORD("Golden Wooden Sword", Rarity.COMMON, 39, 16, R.drawable.item_golden_wsword, Material.GOLD),
    DIAMOND_WSWORD("Diamond Wooden Sword", Rarity.COMMON, 38, 17, R.drawable.item_diamond_wsword, Material.DIAMOND),
    EMERALD_WSWORD("Emerald Wooden Sword", Rarity.COMMON, 33, 15, R.drawable.item_emerald_wsword, Material.EMERALD),
    RUBY_WSWORD("Ruby Wooden Sword", Rarity.COMMON, 3, 2, R.drawable.item_ruby_wsword, Material.RUBY),

    IRON_ISWORD("Iron Iron Sword", Rarity.COMMON, 32, 12, R.drawable.item_iron_isword, Material.IRON),
    GOLDEN_ISWORD("Golden Iron Sword", Rarity.COMMON, 30, 12, R.drawable.item_golden_isword, Material.GOLD),
    DIAMOND_ISWORD("Diamond Iron Sword", Rarity.COMMON, 31, 11, R.drawable.item_diamond_isword, Material.DIAMOND),
    EMERALD_ISWORD("Emerald Iron Sword", Rarity.COMMON, 31, 14, R.drawable.item_emerald_isword, Material.EMERALD),
    RUBY_ISWORD("Ruby Iron Sword", Rarity.COMMON, 35, 18, R.drawable.item_ruby_isword, Material.RUBY),

    IRON_LIGHTSWORD("Iron Lightsword", Rarity.UNCOMMON, 25, 10, R.drawable.item_iron_lightsword, Material.IRON),
    GOLDEN_LIGHTSWORD("Golden Lightsword", Rarity.UNCOMMON, 23, 11, R.drawable.item_golden_lightsword, Material.GOLD),
    DIAMOND_LIGHTSWORD("Diamond Lightsword", Rarity.UNCOMMON, 23, 13, R.drawable.item_diamond_lightsword,Material.DIAMOND),
    EMERALD_LIGHTSWORD("Emerald Lightsword", Rarity.UNCOMMON, 21, 15, R.drawable.item_emerald_lightsword, Material.EMERALD),
    RUBY_LIGHTSWORD("Ruby Lightsword", Rarity.UNCOMMON, 20, 15, R.drawable.item_ruby_lightsword, Material.RUBY),

    IRON_VEST("Iron Vest", Rarity.UNCOMMON, 36, 17, R.drawable.item_iron_vest, Material.IRON),
    GOLDEN_VEST("Golden Vest", Rarity.UNCOMMON, 34, 17, R.drawable.item_golden_vest, Material.GOLD),
    DIAMOND_VEST("Diamond Vest", Rarity.UNCOMMON, 33, 17, R.drawable.item_diamond_vest, Material.DIAMOND),
    EMERALD_VEST("Emerald Vest", Rarity.UNCOMMON, 32, 17, R.drawable.item_emerald_vest, Material.EMERALD),
    RUBY_VEST("Ruby Vest", Rarity.UNCOMMON, 30, 17, R.drawable.item_ruby_vest, Material.RUBY),

    EMERALD_STAFF("Emerald Staff", Rarity.UNCOMMON, 36, 19, R.drawable.item_emerald_staff, Material.EMERALD),
    RUBY_STAFF("Emerald Staff", Rarity.UNCOMMON, 34, 19, R.drawable.item_ruby_staff, Material.RUBY),
    TOPAZ_STAFF("Emerald Staff", Rarity.UNCOMMON, 33, 18, R.drawable.item_topaz_staff, Material.TOPAZ),
    SAPPHIRE_STAFF("Emerald Staff", Rarity.UNCOMMON, 32, 18, R.drawable.item_sapphire_staff, Material.SAPPHIRE),
    AMETHYST_STAFF("Emerald Staff", Rarity.UNCOMMON, 30, 18, R.drawable.item_amethyst_staff, Material.AMETHYST),

    EMERALD_SHURIKEN("Emerald Shuriken", Rarity.UNCOMMON, 30, 20, R.drawable.item_emerald_shuriken, Material.EMERALD),
    RUBY_SHURIKEN("Ruby Shuriken", Rarity.UNCOMMON, 30, 20, R.drawable.item_ruby_shuriken, Material.RUBY),
    TOPAZ_SHURIKEN("Topaz Shuriken", Rarity.UNCOMMON, 50, 32, R.drawable.item_topaz_shuriken, Material.TOPAZ),
    SAPPHIRE_SHURIKEN("Sapphire Shuriken", Rarity.UNCOMMON, 50, 32, R.drawable.item_sapphire_shuriken, Material.SAPPHIRE),
    AMETHYST_SHURIKEN("Amethyst Shuriken", Rarity.UNCOMMON, 60, 30, R.drawable.item_amethyst_shuriken, Material.AMETHYST),
    CERUSSITE_SHURIKEN("Cerussite Shuriken", Rarity.UNCOMMON, 40, 30, R.drawable.item_cerussite_shuriken, Material.CERUSSITE),

    EMERALD_CHESTPLATE("Emerald Chestplate", Rarity.RARE, 60, 46, R.drawable.item_emerald_chestplate, Material.EMERALD),
    RUBY_CHESTPLATE("Ruby Chestplate", Rarity.RARE, 65, 48, R.drawable.item_ruby_chestplate, Material.RUBY),
    TOPAZ_CHESTPLATE("Topaz Chestplate", Rarity.RARE, 65, 50, R.drawable.item_topaz_chestplate, Material.TOPAZ),
    SAPPHIRE_CHESTPLATE("Sapphire Chestplate", Rarity.RARE, 65, 52, R.drawable.item_sapphire_chestplate, Material.SAPPHIRE),
    AMETHYST_CHESTPLATE("Amethyst Chestplate", Rarity.RARE, 70, 58, R.drawable.item_amethyst_chestplate, Material.AMETHYST),
    CERUSSITE_CHESTPLATE("Cerussite Chestplate", Rarity.RARE, 70, 60, R.drawable.item_cerussite_chestplate, Material.CERUSSITE),

    RED_STAFF("Red Staff", Rarity.RARE, 70, 60, R.drawable.item_red_staff, Material.RED_ESSENCE),
    ORANGE_STAFF("Orange Staff", Rarity.RARE, 70, 65, R.drawable.item_orange_staff, Material.ORANGE_ESSENCE),
    YELLOW_STAFF("Yellow Staff", Rarity.RARE, 80, 55, R.drawable.item_yellow_staff, Material.YELLOW_ESSENCE),
    GREEN_STAFF("Green Staff", Rarity.RARE, 80, 60, R.drawable.item_green_staff, Material.GREEN_ESSENCE),
    BLUE_STAFF("Blue Staff", Rarity.RARE, 80, 75, R.drawable.item_blue_staff, Material.BLUE_ESSENCE),
    MAGENTA_STAFF("Magenta Staff", Rarity.RARE, 90, 80, R.drawable.item_magenta_staff, Material.MAGENTA_ESSENCE),
    WHITE_STAFF("White Staff", Rarity.RARE, 100, 95, R.drawable.item_white_staff, Material.WHITE_ESSENCE),
    ;

    public String name;
    public Rarity rarity;
    public int requiredClicks;
    public int iconId;
    public int sellPrice;
    public Material material;

    Item(String name, Rarity rarity, int requiredClicks, int sellPrice, int iconId, Material material) {
        this.name = name;
        this.rarity = rarity;
        this.requiredClicks = requiredClicks;
        this.iconId = iconId;
        this.sellPrice = sellPrice;
        this.material = material;
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
