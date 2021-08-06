package net.jozoproductions.blacksmithclicker.items;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public enum Item {
    STICK("Stick", ItemGroup.STICKS, 6, 1, R.drawable.item_stick, Material.WOOD),
    STONE_STICK("Stone Stick", ItemGroup.STICKS, 10, 2, R.drawable.item_stone_stick, Material.STONE),
    IRON_STICK("Iron Stick", ItemGroup.STICKS, 10, 3, R.drawable.item_iron_stick, Material.IRON),
    GOLDEN_STICK("Golden Stick", ItemGroup.STICKS, 20, 3, R.drawable.item_golden_stick, Material.GOLD),
    DIAMOND_STICK("Diamond Stick", ItemGroup.STICKS, 25, 4, R.drawable.item_diamond_stick, Material.DIAMOND),
    EMERALD_STICK("Emerald Stick", ItemGroup.STICKS, 30, 5, R.drawable.item_emerald_stick, Material.EMERALD),
    RUBY_STICK("Ruby Stick", ItemGroup.STICKS, 30, 6, R.drawable.item_ruby_stick, Material.RUBY),

    STONE_DAGGER("Stone Dagger", ItemGroup.DAGGERS, 34, 6, R.drawable.item_stone_dagger, Material.STONE),
    IRON_DAGGER("Iron Dagger", ItemGroup.DAGGERS, 27, 4, R.drawable.item_iron_dagger, Material.IRON),
    GOLDEN_DAGGER("Golden Dagger", ItemGroup.DAGGERS, 25, 4, R.drawable.item_golden_dagger, Material.GOLD),
    DIAMOND_DAGGER("Diamond Dagger", ItemGroup.DAGGERS, 25, 5, R.drawable.item_diamond_dagger, Material.DIAMOND),
    EMERALD_DAGGER("Emerald Dagger", ItemGroup.DAGGERS, 30, 7, R.drawable.item_emerald_dagger, Material.EMERALD),
    RUBY_DAGGER("Ruby Dagger", ItemGroup.DAGGERS, 30, 8, R.drawable.item_ruby_dagger, Material.RUBY),

    STONE_WSWORD("Stone Wooden Sword", ItemGroup.WSWORDS, 40, 15, R.drawable.item_stone_wsword, Material.STONE),
    IRON_WSWORD("Iron Wooden Sword", ItemGroup.WSWORDS, 39, 15, R.drawable.item_iron_wsword, Material.IRON),
    GOLDEN_WSWORD("Golden Wooden Sword", ItemGroup.WSWORDS, 39, 16, R.drawable.item_golden_wsword, Material.GOLD),
    DIAMOND_WSWORD("Diamond Wooden Sword", ItemGroup.WSWORDS, 38, 17, R.drawable.item_diamond_wsword, Material.DIAMOND),
    EMERALD_WSWORD("Emerald Wooden Sword", ItemGroup.WSWORDS, 33, 15, R.drawable.item_emerald_wsword, Material.EMERALD),
    RUBY_WSWORD("Ruby Wooden Sword", ItemGroup.WSWORDS, 3, 2, R.drawable.item_ruby_wsword, Material.RUBY),

    IRON_ISWORD("Iron Iron Sword", ItemGroup.ISWORDS, 32, 12, R.drawable.item_iron_isword, Material.IRON),
    GOLDEN_ISWORD("Golden Iron Sword", ItemGroup.ISWORDS, 30, 12, R.drawable.item_golden_isword, Material.GOLD),
    DIAMOND_ISWORD("Diamond Iron Sword", ItemGroup.ISWORDS, 31, 11, R.drawable.item_diamond_isword, Material.DIAMOND),
    EMERALD_ISWORD("Emerald Iron Sword", ItemGroup.ISWORDS, 31, 14, R.drawable.item_emerald_isword, Material.EMERALD),
    RUBY_ISWORD("Ruby Iron Sword", ItemGroup.ISWORDS, 35, 18, R.drawable.item_ruby_isword, Material.RUBY),

    IRON_LIGHTSWORD("Iron Lightsword", ItemGroup.LIGHTSWORDS, 25, 10, R.drawable.item_iron_lightsword, Material.IRON),
    GOLDEN_LIGHTSWORD("Golden Lightsword", ItemGroup.LIGHTSWORDS, 23, 11, R.drawable.item_golden_lightsword, Material.GOLD),
    DIAMOND_LIGHTSWORD("Diamond Lightsword", ItemGroup.LIGHTSWORDS, 23, 13, R.drawable.item_diamond_lightsword,Material.DIAMOND),
    EMERALD_LIGHTSWORD("Emerald Lightsword", ItemGroup.LIGHTSWORDS, 21, 15, R.drawable.item_emerald_lightsword, Material.EMERALD),
    RUBY_LIGHTSWORD("Ruby Lightsword", ItemGroup.LIGHTSWORDS, 20, 15, R.drawable.item_ruby_lightsword, Material.RUBY),

    IRON_VEST("Iron Vest", ItemGroup.VESTS, 36, 17, R.drawable.item_iron_vest, Material.IRON),
    GOLDEN_VEST("Golden Vest", ItemGroup.VESTS, 34, 17, R.drawable.item_golden_vest, Material.GOLD),
    DIAMOND_VEST("Diamond Vest", ItemGroup.VESTS, 33, 17, R.drawable.item_diamond_vest, Material.DIAMOND),
    EMERALD_VEST("Emerald Vest", ItemGroup.VESTS, 32, 17, R.drawable.item_emerald_vest, Material.EMERALD),
    RUBY_VEST("Ruby Vest", ItemGroup.VESTS, 30, 17, R.drawable.item_ruby_vest, Material.RUBY),

    EMERALD_STAFF("Emerald Staff", ItemGroup.STAFFS, 36, 19, R.drawable.item_emerald_staff, Material.EMERALD),
    RUBY_STAFF("Emerald Staff", ItemGroup.STAFFS, 34, 19, R.drawable.item_ruby_staff, Material.RUBY),
    TOPAZ_STAFF("Emerald Staff", ItemGroup.STAFFS, 33, 18, R.drawable.item_topaz_staff, Material.TOPAZ),
    SAPPHIRE_STAFF("Emerald Staff", ItemGroup.STAFFS, 32, 18, R.drawable.item_sapphire_staff, Material.SAPPHIRE),
    AMETHYST_STAFF("Emerald Staff", ItemGroup.STAFFS, 30, 18, R.drawable.item_amethyst_staff, Material.AMETHYST),

    EMERALD_SHURIKEN("Emerald Shuriken", ItemGroup.SHURIKENS, 30, 20, R.drawable.item_emerald_shuriken, Material.EMERALD),
    RUBY_SHURIKEN("Ruby Shuriken", ItemGroup.SHURIKENS, 30, 20, R.drawable.item_ruby_shuriken, Material.RUBY),
    TOPAZ_SHURIKEN("Topaz Shuriken", ItemGroup.SHURIKENS, 50, 32, R.drawable.item_topaz_shuriken, Material.TOPAZ),
    SAPPHIRE_SHURIKEN("Sapphire Shuriken", ItemGroup.SHURIKENS, 50, 32, R.drawable.item_sapphire_shuriken, Material.SAPPHIRE),
    AMETHYST_SHURIKEN("Amethyst Shuriken", ItemGroup.SHURIKENS, 60, 30, R.drawable.item_amethyst_shuriken, Material.AMETHYST),
    CERUSSITE_SHURIKEN("Cerussite Shuriken", ItemGroup.SHURIKENS, 40, 30, R.drawable.item_cerussite_shuriken, Material.CERUSSITE),

    EMERALD_CHESTPLATE("Emerald Chestplate", ItemGroup.CHESTPLATES, 60, 46, R.drawable.item_emerald_chestplate, Material.EMERALD),
    RUBY_CHESTPLATE("Ruby Chestplate", ItemGroup.CHESTPLATES, 65, 48, R.drawable.item_ruby_chestplate, Material.RUBY),
    TOPAZ_CHESTPLATE("Topaz Chestplate", ItemGroup.CHESTPLATES, 65, 50, R.drawable.item_topaz_chestplate, Material.TOPAZ),
    SAPPHIRE_CHESTPLATE("Sapphire Chestplate", ItemGroup.CHESTPLATES, 65, 52, R.drawable.item_sapphire_chestplate, Material.SAPPHIRE),
    AMETHYST_CHESTPLATE("Amethyst Chestplate", ItemGroup.CHESTPLATES, 70, 58, R.drawable.item_amethyst_chestplate, Material.AMETHYST),
    CERUSSITE_CHESTPLATE("Cerussite Chestplate", ItemGroup.CHESTPLATES, 70, 60, R.drawable.item_cerussite_chestplate, Material.CERUSSITE),

    RED_STAFF("Red Staff", ItemGroup.BETTER_STAFFS, 70, 60, R.drawable.item_red_staff, Material.RED_ESSENCE),
    ORANGE_STAFF("Orange Staff", ItemGroup.BETTER_STAFFS, 70, 65, R.drawable.item_orange_staff, Material.ORANGE_ESSENCE),
    YELLOW_STAFF("Yellow Staff", ItemGroup.BETTER_STAFFS, 80, 55, R.drawable.item_yellow_staff, Material.YELLOW_ESSENCE),
    GREEN_STAFF("Green Staff", ItemGroup.BETTER_STAFFS, 80, 60, R.drawable.item_green_staff, Material.GREEN_ESSENCE),
    BLUE_STAFF("Blue Staff", ItemGroup.BETTER_STAFFS, 80, 75, R.drawable.item_blue_staff, Material.BLUE_ESSENCE),
    MAGENTA_STAFF("Magenta Staff", ItemGroup.BETTER_STAFFS, 90, 80, R.drawable.item_magenta_staff, Material.MAGENTA_ESSENCE),
    WHITE_STAFF("White Staff", ItemGroup.BETTER_STAFFS, 100, 95, R.drawable.item_white_staff, Material.WHITE_ESSENCE),
    ;

    public String name;
    public ItemGroup itemGroup;
    public int requiredClicks;
    public int iconId;
    public int sellPrice;
    public Material material;

    public boolean owningItem = false;

    Item(String name, ItemGroup itemGroup, int requiredClicks, int sellPrice, int iconId, Material material) {
        this.name = name;
        this.itemGroup = itemGroup;
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
                if (items[i].itemGroup.rarity == rarities[j]) {
                    rarities[j].items.add(items[i]);
                    break;
                }
            }
        }
    }

    public static void InitializeItemGroupItemsArrayLists() {
        Item[] items = Item.values();
        ItemGroup[] itemGroups = ItemGroup.values();

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < itemGroups.length; j++) {
                if (items[i].itemGroup == itemGroups[j]) {
                    itemGroups[j].items.add(items[i]);
                    break;
                }
            }
        }
    }
}
