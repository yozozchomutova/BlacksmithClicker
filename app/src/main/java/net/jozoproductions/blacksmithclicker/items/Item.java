package net.jozoproductions.blacksmithclicker.items;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public enum Item {
    STICK("Stick", ItemGroup.STICKS, 6, 1, R.drawable.item_stick, Material.WOOD),
    STONE_STICK("Stone Stick", ItemGroup.STICKS, 9, 2, R.drawable.item_stone_stick, Material.STONE),
    IRON_STICK("Iron Stick", ItemGroup.STICKS, 12, 3, R.drawable.item_iron_stick, Material.IRON),
    GOLDEN_STICK("Golden Stick", ItemGroup.STICKS, 17, 5, R.drawable.item_golden_stick, Material.GOLD),
    DIAMOND_STICK("Diamond Stick", ItemGroup.STICKS, 20, 8, R.drawable.item_diamond_stick, Material.DIAMOND),
    EMERALD_STICK("Emerald Stick", ItemGroup.STICKS, 22, 12, R.drawable.item_emerald_stick, Material.EMERALD),
    RUBY_STICK("Ruby Stick", ItemGroup.STICKS, 24, 15, R.drawable.item_ruby_stick, Material.RUBY),

    STONE_DAGGER("Stone Dagger", ItemGroup.DAGGERS, 27, 18, R.drawable.item_stone_dagger, Material.STONE),
    IRON_DAGGER("Iron Dagger", ItemGroup.DAGGERS, 29, 21, R.drawable.item_iron_dagger, Material.IRON),
    GOLDEN_DAGGER("Golden Dagger", ItemGroup.DAGGERS, 30, 23, R.drawable.item_golden_dagger, Material.GOLD),
    DIAMOND_DAGGER("Diamond Dagger", ItemGroup.DAGGERS, 31, 24, R.drawable.item_diamond_dagger, Material.DIAMOND),
    EMERALD_DAGGER("Emerald Dagger", ItemGroup.DAGGERS, 31, 25, R.drawable.item_emerald_dagger, Material.EMERALD),
    RUBY_DAGGER("Ruby Dagger", ItemGroup.DAGGERS, 32, 27, R.drawable.item_ruby_dagger, Material.RUBY),

    STONE_WSWORD("Stone Wooden Sword", ItemGroup.WSWORDS, 34, 30, R.drawable.item_stone_wsword, Material.STONE),
    IRON_WSWORD("Iron Wooden Sword", ItemGroup.WSWORDS, 35, 32, R.drawable.item_iron_wsword, Material.IRON),
    GOLDEN_WSWORD("Golden Wooden Sword", ItemGroup.WSWORDS, 36, 33, R.drawable.item_golden_wsword, Material.GOLD),
    DIAMOND_WSWORD("Diamond Wooden Sword", ItemGroup.WSWORDS, 37, 34, R.drawable.item_diamond_wsword, Material.DIAMOND),
    EMERALD_WSWORD("Emerald Wooden Sword", ItemGroup.WSWORDS, 38, 36, R.drawable.item_emerald_wsword, Material.EMERALD),
    RUBY_WSWORD("Ruby Wooden Sword", ItemGroup.WSWORDS, 40, 38, R.drawable.item_ruby_wsword, Material.RUBY),

    IRON_ISWORD("Iron Iron Sword", ItemGroup.ISWORDS, 41, 40, R.drawable.item_iron_isword, Material.IRON),
    GOLDEN_ISWORD("Golden Iron Sword", ItemGroup.ISWORDS, 43, 43, R.drawable.item_golden_isword, Material.GOLD),
    DIAMOND_ISWORD("Diamond Iron Sword", ItemGroup.ISWORDS, 44, 44, R.drawable.item_diamond_isword, Material.DIAMOND),
    EMERALD_ISWORD("Emerald Iron Sword", ItemGroup.ISWORDS, 45, 45, R.drawable.item_emerald_isword, Material.EMERALD),
    RUBY_ISWORD("Ruby Iron Sword", ItemGroup.ISWORDS, 46, 47, R.drawable.item_ruby_isword, Material.RUBY),

    IRON_LIGHTSWORD("Iron Lightsword", ItemGroup.LIGHTSWORDS, 48, 50, R.drawable.item_iron_lightsword, Material.IRON),
    GOLDEN_LIGHTSWORD("Golden Lightsword", ItemGroup.LIGHTSWORDS, 49, 52, R.drawable.item_golden_lightsword, Material.GOLD),
    DIAMOND_LIGHTSWORD("Diamond Lightsword", ItemGroup.LIGHTSWORDS, 50, 53, R.drawable.item_diamond_lightsword,Material.DIAMOND),
    EMERALD_LIGHTSWORD("Emerald Lightsword", ItemGroup.LIGHTSWORDS, 52, 56, R.drawable.item_emerald_lightsword, Material.EMERALD),
    RUBY_LIGHTSWORD("Ruby Lightsword", ItemGroup.LIGHTSWORDS, 54, 59, R.drawable.item_ruby_lightsword, Material.RUBY),

    IRON_VEST("Iron Vest", ItemGroup.VESTS, 54, 60, R.drawable.item_iron_vest, Material.IRON),
    GOLDEN_VEST("Golden Vest", ItemGroup.VESTS, 54, 62, R.drawable.item_golden_vest, Material.GOLD),
    DIAMOND_VEST("Diamond Vest", ItemGroup.VESTS, 55, 63, R.drawable.item_diamond_vest, Material.DIAMOND),
    EMERALD_VEST("Emerald Vest", ItemGroup.VESTS, 57, 65, R.drawable.item_emerald_vest, Material.EMERALD),
    RUBY_VEST("Ruby Vest", ItemGroup.VESTS, 59, 68, R.drawable.item_ruby_vest, Material.RUBY),

    EMERALD_STAFF("Emerald Staff", ItemGroup.STAFFS, 61, 72, R.drawable.item_emerald_staff, Material.EMERALD),
    RUBY_STAFF("Emerald Staff", ItemGroup.STAFFS, 62, 75, R.drawable.item_ruby_staff, Material.RUBY),
    TOPAZ_STAFF("Emerald Staff", ItemGroup.STAFFS, 64, 78, R.drawable.item_topaz_staff, Material.TOPAZ),
    SAPPHIRE_STAFF("Emerald Staff", ItemGroup.STAFFS, 66, 80, R.drawable.item_sapphire_staff, Material.SAPPHIRE),
    AMETHYST_STAFF("Emerald Staff", ItemGroup.STAFFS, 68, 83, R.drawable.item_amethyst_staff, Material.AMETHYST),

    EMERALD_SHURIKEN("Emerald Shuriken", ItemGroup.SHURIKENS, 72, 90, R.drawable.item_emerald_shuriken, Material.EMERALD),
    RUBY_SHURIKEN("Ruby Shuriken", ItemGroup.SHURIKENS, 74, 93, R.drawable.item_ruby_shuriken, Material.RUBY),
    TOPAZ_SHURIKEN("Topaz Shuriken", ItemGroup.SHURIKENS, 76, 97, R.drawable.item_topaz_shuriken, Material.TOPAZ),
    SAPPHIRE_SHURIKEN("Sapphire Shuriken", ItemGroup.SHURIKENS, 79, 101, R.drawable.item_sapphire_shuriken, Material.SAPPHIRE),
    AMETHYST_SHURIKEN("Amethyst Shuriken", ItemGroup.SHURIKENS, 80, 103, R.drawable.item_amethyst_shuriken, Material.AMETHYST),
    CERUSSITE_SHURIKEN("Cerussite Shuriken", ItemGroup.SHURIKENS, 82, 106, R.drawable.item_cerussite_shuriken, Material.CERUSSITE),

    EMERALD_CHESTPLATE("Emerald Chestplate", ItemGroup.CHESTPLATES, 84, 109, R.drawable.item_emerald_chestplate, Material.EMERALD),
    RUBY_CHESTPLATE("Ruby Chestplate", ItemGroup.CHESTPLATES, 85, 114, R.drawable.item_ruby_chestplate, Material.RUBY),
    TOPAZ_CHESTPLATE("Topaz Chestplate", ItemGroup.CHESTPLATES, 87, 119, R.drawable.item_topaz_chestplate, Material.TOPAZ),
    SAPPHIRE_CHESTPLATE("Sapphire Chestplate", ItemGroup.CHESTPLATES, 90, 125, R.drawable.item_sapphire_chestplate, Material.SAPPHIRE),
    AMETHYST_CHESTPLATE("Amethyst Chestplate", ItemGroup.CHESTPLATES, 93, 130, R.drawable.item_amethyst_chestplate, Material.AMETHYST),
    CERUSSITE_CHESTPLATE("Cerussite Chestplate", ItemGroup.CHESTPLATES, 96, 138, R.drawable.item_cerussite_chestplate, Material.CERUSSITE),

    EMERALD_SHOES("Emerald Shoes", ItemGroup.SHOES, 100, 146, R.drawable.item_emerald_shoes, Material.EMERALD),
    RUBY_SHOES("Ruby Shoes", ItemGroup.SHOES, 105, 154, R.drawable.item_ruby_shoes, Material.RUBY),
    TOPAZ_SHOES("Topaz Shoes", ItemGroup.SHOES, 110, 162, R.drawable.item_topaz_shoes, Material.TOPAZ),
    SAPPHIRE_SHOES("Sapphire Shoes", ItemGroup.SHOES, 115, 170, R.drawable.item_sapphire_shoes, Material.SAPPHIRE),
    AMETHYST_SHOES("Amethyst Shoes", ItemGroup.SHOES, 120, 179, R.drawable.item_amethyst_shoes, Material.AMETHYST),
    CERUSSITE_SHOES("Cerussite Shoes", ItemGroup.SHOES, 125, 188, R.drawable.item_cerussite_shoes, Material.CERUSSITE),

    RED_STAFF("Red Staff", ItemGroup.BETTER_STAFFS, 128, 193, R.drawable.item_red_staff, Material.RED_ESSENCE),
    ORANGE_STAFF("Orange Staff", ItemGroup.BETTER_STAFFS, 134, 199, R.drawable.item_orange_staff, Material.ORANGE_ESSENCE),
    YELLOW_STAFF("Yellow Staff", ItemGroup.BETTER_STAFFS, 137, 204, R.drawable.item_yellow_staff, Material.YELLOW_ESSENCE),
    GREEN_STAFF("Green Staff", ItemGroup.BETTER_STAFFS, 140, 212, R.drawable.item_green_staff, Material.GREEN_ESSENCE),
    BLUE_STAFF("Blue Staff", ItemGroup.BETTER_STAFFS, 143, 220, R.drawable.item_blue_staff, Material.BLUE_ESSENCE),
    MAGENTA_STAFF("Magenta Staff", ItemGroup.BETTER_STAFFS, 147, 229, R.drawable.item_magenta_staff, Material.MAGENTA_ESSENCE),
    WHITE_STAFF("White Staff", ItemGroup.BETTER_STAFFS, 151, 238, R.drawable.item_white_staff, Material.WHITE_ESSENCE),

    RED_HELMET("Red Helmet", ItemGroup.HELMETS, 155, 246, R.drawable.item_red_helmet, Material.RED_ESSENCE),
    ORANGE_HELMET("Orange Helmet", ItemGroup.HELMETS, 158, 252, R.drawable.item_orange_helmet, Material.ORANGE_ESSENCE),
    YELLOW_HELMET("Yellow Helmet", ItemGroup.HELMETS, 161, 259, R.drawable.item_yellow_helmet, Material.YELLOW_ESSENCE),
    GREEN_HELMET("Green Helmet", ItemGroup.HELMETS, 163, 265, R.drawable.item_green_helmet, Material.GREEN_ESSENCE),
    BLUE_HELMET("Blue Helmet", ItemGroup.HELMETS, 167, 272, R.drawable.item_blue_helmet, Material.BLUE_ESSENCE),
    MAGENTA_HELMET("Magenta Helmet", ItemGroup.HELMETS, 170, 280, R.drawable.item_magenta_helmet, Material.MAGENTA_ESSENCE),
    WHITE_HELMET("White Helmet", ItemGroup.HELMETS, 173, 285, R.drawable.item_white_helmet, Material.WHITE_ESSENCE),

    RED_BOOTS("Red Boots", ItemGroup.BOOTS, 176, 291, R.drawable.item_red_boots, Material.RED_ESSENCE),
    ORANGE_BOOTS("Orange Boots", ItemGroup.BOOTS, 178, 295, R.drawable.item_orange_boots, Material.ORANGE_ESSENCE),
    YELLOW_BOOTS("Yellow Boots", ItemGroup.BOOTS, 181, 300, R.drawable.item_yellow_boots, Material.YELLOW_ESSENCE),
    GREEN_BOOTS("Green Boots", ItemGroup.BOOTS, 184, 305, R.drawable.item_green_boots, Material.GREEN_ESSENCE),
    BLUE_BOOTS("Blue Boots", ItemGroup.BOOTS, 187, 314, R.drawable.item_blue_boots, Material.BLUE_ESSENCE),
    MAGENTA_BOOTS("Magenta Boots", ItemGroup.BOOTS, 190, 321, R.drawable.item_magenta_boots, Material.MAGENTA_ESSENCE),
    WHITE_BOOTS("White Boots", ItemGroup.BOOTS, 193, 328, R.drawable.item_white_boots, Material.WHITE_ESSENCE),

    RED_TRION("Red Trion", ItemGroup.TRIONS, 198, 335, R.drawable.item_red_trion, Material.RED_ESSENCE),
    ORANGE_TRION("Orange Trion", ItemGroup.TRIONS, 201, 342, R.drawable.item_orange_trion, Material.ORANGE_ESSENCE),
    YELLOW_TRION("Yellow Trion", ItemGroup.TRIONS, 205, 349, R.drawable.item_yellow_trion, Material.YELLOW_ESSENCE),
    GREEN_TRION("Green Trion", ItemGroup.TRIONS, 210, 358, R.drawable.item_green_trion, Material.GREEN_ESSENCE),
    BLUE_TRION("Blue Trion", ItemGroup.TRIONS, 215, 367, R.drawable.item_blue_trion, Material.BLUE_ESSENCE),
    MAGENTA_TRION("Magenta Trion", ItemGroup.TRIONS, 219, 375, R.drawable.item_magenta_trion, Material.MAGENTA_ESSENCE),
    WHITE_TRION("White Trion", ItemGroup.TRIONS, 223, 382, R.drawable.item_white_trion, Material.WHITE_ESSENCE),

    RED_HEXAGON("Red Hexagon", ItemGroup.HEXAGONS, 228, 390, R.drawable.item_red_hexagon, Material.RED_ESSENCE),
    ORANGE_HEXAGON("Orange Hexagon", ItemGroup.HEXAGONS, 233, 399, R.drawable.item_orange_hexagon, Material.ORANGE_ESSENCE),
    YELLOW_HEXAGON("Yellow Hexagon", ItemGroup.HEXAGONS, 238, 409, R.drawable.item_yellow_hexagon, Material.YELLOW_ESSENCE),
    GREEN_HEXAGON("Green Hexagon", ItemGroup.HEXAGONS, 248, 419, R.drawable.item_green_hexagon, Material.GREEN_ESSENCE),
    BLUE_HEXAGON("Blue Hexagon", ItemGroup.HEXAGONS, 258, 430, R.drawable.item_blue_hexagon, Material.BLUE_ESSENCE),
    MAGENTA_HEXAGON("Magenta Hexagon", ItemGroup.HEXAGONS, 269, 438, R.drawable.item_magenta_hexagon, Material.MAGENTA_ESSENCE),
    WHITE_HEXAGON("White Hexagon", ItemGroup.HEXAGONS, 280, 449, R.drawable.item_white_hexagon, Material.WHITE_ESSENCE),

    BLADE_TYPE1("Blade [Type 1]", ItemGroup.BLADES, 290, 458, R.drawable.item_crystalite_blade, Material.CRYSTALITE),
    BLADE_TYPE2("Blade [Type 2]", ItemGroup.BLADES, 300, 468, R.drawable.item_crystalite_blade2, Material.CRYSTALITE),
    BLADE_TYPE3("Blade [Type 3]", ItemGroup.BLADES, 309, 479, R.drawable.item_crystalite_blade3, Material.CRYSTALITE),

    HEAVY_TYPE1("Heavy [Type 1]", ItemGroup.HEAVY, 320, 500, R.drawable.item_geolite_heavy1, Material.GEOLITE),
    HEAVY_TYPE2("Heavy [Type 2]", ItemGroup.HEAVY, 332, 520, R.drawable.item_geolite_heavy2, Material.GEOLITE),
    HEAVY_TYPE3("Heavy [Type 3]", ItemGroup.HEAVY, 348, 545, R.drawable.item_geolite_heavy3, Material.GEOLITE),
    HEAVY_TYPE4("Heavy [Type 4]", ItemGroup.HEAVY, 368, 585, R.drawable.item_geolite_heavy4, Material.GEOLITE),

    MONO_TYPE1("Mono [Type 1]", ItemGroup.MONO, 380, 640, R.drawable.item_monolite_mono1, Material.MONOLITE),
    MONO_TYPE2("Mono [Type 2]", ItemGroup.MONO, 395, 700, R.drawable.item_monolite_mono2, Material.MONOLITE),
    MONO_TYPE3("Mono [Type 3]", ItemGroup.MONO, 418, 770, R.drawable.item_monolite_mono3, Material.MONOLITE),

    TECHNOSCYTHE1("TechnoScythe [Type 1]", ItemGroup.TECHNOSCYTHE, 440, 855, R.drawable.item_iridiolite_technoscythe1, Material.IRIDIOLITE),
    TECHNOSCYTHE2("TechnoScythe [Type 2]", ItemGroup.TECHNOSCYTHE, 465, 940, R.drawable.item_iridiolite_technoscythe2, Material.IRIDIOLITE),
    TECHNOSCYTHE3("TechnoScythe [Type 3]", ItemGroup.TECHNOSCYTHE, 491, 1050, R.drawable.item_iridiolite_technoscythe3, Material.IRIDIOLITE),

    CATALYST1("Catalyst Dagger [Type 1]", ItemGroup.CATALYST, 686, 1310, R.drawable.item_catalyst_dagger1, Material.CATALYST),
    CATALYST2("Catalyst Dagger [Type 2]", ItemGroup.CATALYST, 794, 1586, R.drawable.item_catalyst_dagger2, Material.CATALYST),
    CATALYST3("Catalyst Dagger [Type 3]", ItemGroup.CATALYST, 902, 1875, R.drawable.item_catalyst_dagger3, Material.CATALYST),

    NEONIDE1("Neonide [Type 1]", ItemGroup.NEONIDE, 902, 1875, R.drawable.item_neoniste_neonide1, Material.NEONISTE),
    NEONIDE2("Neonide [Type 2]", ItemGroup.NEONIDE, 1000, 2650, R.drawable.item_neoniste_neonide2, Material.NEONISTE),
    NEONIDE3("Neonide [Type 3]", ItemGroup.NEONIDE, 1150, 3550, R.drawable.item_neoniste_neonide3, Material.NEONISTE),

    LUNARTIC("LUNARTIC", ItemGroup.LUNARTIC, 1350, 5150, R.drawable.item_lunartism_lunartic, Material.LUNARTISM),

    CANDY_CANE1("Candy Cane - Red", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane1, Material.SUGAR),
    CANDY_CANE2("Candy Cane - Blue", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane2, Material.SUGAR),
    CANDY_CANE3("Candy Cane - Green", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane3, Material.SUGAR),
    CANDY_CANE4("Candy Cane - Yellow", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane4, Material.SUGAR),
    CANDY_CANE5("Candy Cane - Pink", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane5, Material.SUGAR),
    CANDY_CANE6("Candy Cane - Purple", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane6, Material.SUGAR),
    CANDY_CANE7("Candy Cane - Orange", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane7, Material.SUGAR),
    CANDY_CANE8("Candy Cane - Gray", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane8, Material.SUGAR),
    CANDY_CANE9("Candy Cane - Aqua", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane9, Material.SUGAR),
    CANDY_CANE10("Candy Cane - Brown", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane10, Material.SUGAR),
    CANDY_CANE11("Candy Cane - White", ItemGroup.CANDY_CANE, 3, 1, R.drawable.item_candy_cane11, Material.SUGAR),
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
