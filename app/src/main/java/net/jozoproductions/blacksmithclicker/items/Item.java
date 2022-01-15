package net.jozoproductions.blacksmithclicker.items;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public enum Item {
    STICK("Stick", ItemGroup.STICKS, 6, 1, 1, R.drawable.item_stick, Material.WOOD),
    STONE_STICK("Stone Stick", ItemGroup.STICKS, 9, 2, 2, R.drawable.item_stone_stick, Material.STONE),
    IRON_STICK("Iron Stick", ItemGroup.STICKS, 12, 3, 2, R.drawable.item_iron_stick, Material.IRON),
    GOLDEN_STICK("Golden Stick", ItemGroup.STICKS, 17, 5, 2, R.drawable.item_golden_stick, Material.GOLD),
    DIAMOND_STICK("Diamond Stick", ItemGroup.STICKS, 20, 8, 3, R.drawable.item_diamond_stick, Material.DIAMOND),
    EMERALD_STICK("Emerald Stick", ItemGroup.STICKS, 22, 12, 3, R.drawable.item_emerald_stick, Material.EMERALD),
    RUBY_STICK("Ruby Stick", ItemGroup.STICKS, 24, 15, 3, R.drawable.item_ruby_stick, Material.RUBY),

    STONE_DAGGER("Stone Dagger", ItemGroup.DAGGERS, 27, 18, 2, R.drawable.item_stone_dagger, Material.STONE),
    IRON_DAGGER("Iron Dagger", ItemGroup.DAGGERS, 29, 21, 2, R.drawable.item_iron_dagger, Material.IRON),
    GOLDEN_DAGGER("Golden Dagger", ItemGroup.DAGGERS, 30, 23, 3, R.drawable.item_golden_dagger, Material.GOLD),
    DIAMOND_DAGGER("Diamond Dagger", ItemGroup.DAGGERS, 31, 24, 3, R.drawable.item_diamond_dagger, Material.DIAMOND),
    EMERALD_DAGGER("Emerald Dagger", ItemGroup.DAGGERS, 31, 25, 3, R.drawable.item_emerald_dagger, Material.EMERALD),
    RUBY_DAGGER("Ruby Dagger", ItemGroup.DAGGERS, 32, 27, 4, R.drawable.item_ruby_dagger, Material.RUBY),

    STONE_WSWORD("Stone Wooden Sword", ItemGroup.WSWORDS, 34, 30, 3, R.drawable.item_stone_wsword, Material.STONE),
    IRON_WSWORD("Iron Wooden Sword", ItemGroup.WSWORDS, 35, 32, 3, R.drawable.item_iron_wsword, Material.IRON),
    GOLDEN_WSWORD("Golden Wooden Sword", ItemGroup.WSWORDS, 36, 33, 3, R.drawable.item_golden_wsword, Material.GOLD),
    DIAMOND_WSWORD("Diamond Wooden Sword", ItemGroup.WSWORDS, 37, 34, 3, R.drawable.item_diamond_wsword, Material.DIAMOND),
    EMERALD_WSWORD("Emerald Wooden Sword", ItemGroup.WSWORDS, 38, 36, 4, R.drawable.item_emerald_wsword, Material.EMERALD),
    RUBY_WSWORD("Ruby Wooden Sword", ItemGroup.WSWORDS, 40, 38, 4, R.drawable.item_ruby_wsword, Material.RUBY),

    IRON_ISWORD("Iron Iron Sword", ItemGroup.ISWORDS, 41, 40, 5, R.drawable.item_iron_isword, Material.IRON),
    GOLDEN_ISWORD("Golden Iron Sword", ItemGroup.ISWORDS, 43, 43, 5, R.drawable.item_golden_isword, Material.GOLD),
    DIAMOND_ISWORD("Diamond Iron Sword", ItemGroup.ISWORDS, 44, 44, 5, R.drawable.item_diamond_isword, Material.DIAMOND),
    EMERALD_ISWORD("Emerald Iron Sword", ItemGroup.ISWORDS, 45, 45, 5, R.drawable.item_emerald_isword, Material.EMERALD),
    RUBY_ISWORD("Ruby Iron Sword", ItemGroup.ISWORDS, 46, 47, 5, R.drawable.item_ruby_isword, Material.RUBY),

    IRON_LIGHTSWORD("Iron Lightsword", ItemGroup.LIGHTSWORDS, 48, 50, 5, R.drawable.item_iron_lightsword, Material.IRON),
    GOLDEN_LIGHTSWORD("Golden Lightsword", ItemGroup.LIGHTSWORDS, 49, 52, 5, R.drawable.item_golden_lightsword, Material.GOLD),
    DIAMOND_LIGHTSWORD("Diamond Lightsword", ItemGroup.LIGHTSWORDS, 50, 53, 5, R.drawable.item_diamond_lightsword,Material.DIAMOND),
    EMERALD_LIGHTSWORD("Emerald Lightsword", ItemGroup.LIGHTSWORDS, 52, 56, 5, R.drawable.item_emerald_lightsword, Material.EMERALD),
    RUBY_LIGHTSWORD("Ruby Lightsword", ItemGroup.LIGHTSWORDS, 54, 59, 6, R.drawable.item_ruby_lightsword, Material.RUBY),

    IRON_VEST("Iron Vest", ItemGroup.VESTS, 54, 60, 6, R.drawable.item_iron_vest, Material.IRON),
    GOLDEN_VEST("Golden Vest", ItemGroup.VESTS, 54, 62, 6, R.drawable.item_golden_vest, Material.GOLD),
    DIAMOND_VEST("Diamond Vest", ItemGroup.VESTS, 55, 63, 6, R.drawable.item_diamond_vest, Material.DIAMOND),
    EMERALD_VEST("Emerald Vest", ItemGroup.VESTS, 57, 65, 6, R.drawable.item_emerald_vest, Material.EMERALD),
    RUBY_VEST("Ruby Vest", ItemGroup.VESTS, 59, 68, 6, R.drawable.item_ruby_vest, Material.RUBY),

    EMERALD_STAFF("Emerald Staff", ItemGroup.STAFFS, 61, 72, 6, R.drawable.item_emerald_staff, Material.EMERALD),
    RUBY_STAFF("Ruby Staff", ItemGroup.STAFFS, 62, 75, 6, R.drawable.item_ruby_staff, Material.RUBY),
    TOPAZ_STAFF("Topaz Staff", ItemGroup.STAFFS, 64, 78, 6, R.drawable.item_topaz_staff, Material.TOPAZ),
    SAPPHIRE_STAFF("Sapphire Staff", ItemGroup.STAFFS, 66, 80, 6, R.drawable.item_sapphire_staff, Material.SAPPHIRE),
    AMETHYST_STAFF("Amethyst Staff", ItemGroup.STAFFS, 68, 83, 6, R.drawable.item_amethyst_staff, Material.AMETHYST),

    EMERALD_SHURIKEN("Emerald Shuriken", ItemGroup.SHURIKENS, 72, 90, 6, R.drawable.item_emerald_shuriken, Material.EMERALD),
    RUBY_SHURIKEN("Ruby Shuriken", ItemGroup.SHURIKENS, 74, 93, 6, R.drawable.item_ruby_shuriken, Material.RUBY),
    TOPAZ_SHURIKEN("Topaz Shuriken", ItemGroup.SHURIKENS, 76, 97, 6, R.drawable.item_topaz_shuriken, Material.TOPAZ),
    SAPPHIRE_SHURIKEN("Sapphire Shuriken", ItemGroup.SHURIKENS, 79, 101, 6, R.drawable.item_sapphire_shuriken, Material.SAPPHIRE),
    AMETHYST_SHURIKEN("Amethyst Shuriken", ItemGroup.SHURIKENS, 80, 103, 6, R.drawable.item_amethyst_shuriken, Material.AMETHYST),
    CERUSSITE_SHURIKEN("Cerussite Shuriken", ItemGroup.SHURIKENS, 82, 106, 7, R.drawable.item_cerussite_shuriken, Material.CERUSSITE),

    EMERALD_CHESTPLATE("Emerald Chestplate", ItemGroup.CHESTPLATES, 84, 109, 8, R.drawable.item_emerald_chestplate, Material.EMERALD),
    RUBY_CHESTPLATE("Ruby Chestplate", ItemGroup.CHESTPLATES, 85, 114, 8, R.drawable.item_ruby_chestplate, Material.RUBY),
    TOPAZ_CHESTPLATE("Topaz Chestplate", ItemGroup.CHESTPLATES, 87, 119, 8, R.drawable.item_topaz_chestplate, Material.TOPAZ),
    SAPPHIRE_CHESTPLATE("Sapphire Chestplate", ItemGroup.CHESTPLATES, 90, 125, 8, R.drawable.item_sapphire_chestplate, Material.SAPPHIRE),
    AMETHYST_CHESTPLATE("Amethyst Chestplate", ItemGroup.CHESTPLATES, 93, 130, 8, R.drawable.item_amethyst_chestplate, Material.AMETHYST),
    CERUSSITE_CHESTPLATE("Cerussite Chestplate", ItemGroup.CHESTPLATES, 96, 138, 8, R.drawable.item_cerussite_chestplate, Material.CERUSSITE),

    EMERALD_SHOES("Emerald Shoes", ItemGroup.SHOES, 100, 146, 8, R.drawable.item_emerald_shoes, Material.EMERALD),
    RUBY_SHOES("Ruby Shoes", ItemGroup.SHOES, 105, 154, 8, R.drawable.item_ruby_shoes, Material.RUBY),
    TOPAZ_SHOES("Topaz Shoes", ItemGroup.SHOES, 110, 162, 8, R.drawable.item_topaz_shoes, Material.TOPAZ),
    SAPPHIRE_SHOES("Sapphire Shoes", ItemGroup.SHOES, 115, 170, 8, R.drawable.item_sapphire_shoes, Material.SAPPHIRE),
    AMETHYST_SHOES("Amethyst Shoes", ItemGroup.SHOES, 120, 179, 9, R.drawable.item_amethyst_shoes, Material.AMETHYST),
    CERUSSITE_SHOES("Cerussite Shoes", ItemGroup.SHOES, 125, 188, 9, R.drawable.item_cerussite_shoes, Material.CERUSSITE),

    RED_STAFF("Red Staff", ItemGroup.BETTER_STAFFS, 128, 193, 10, R.drawable.item_red_staff, Material.RED_ESSENCE),
    ORANGE_STAFF("Orange Staff", ItemGroup.BETTER_STAFFS, 134, 199, 10, R.drawable.item_orange_staff, Material.ORANGE_ESSENCE),
    YELLOW_STAFF("Yellow Staff", ItemGroup.BETTER_STAFFS, 137, 204, 10, R.drawable.item_yellow_staff, Material.YELLOW_ESSENCE),
    GREEN_STAFF("Green Staff", ItemGroup.BETTER_STAFFS, 140, 212, 10, R.drawable.item_green_staff, Material.GREEN_ESSENCE),
    BLUE_STAFF("Blue Staff", ItemGroup.BETTER_STAFFS, 143, 220, 10, R.drawable.item_blue_staff, Material.BLUE_ESSENCE),
    MAGENTA_STAFF("Magenta Staff", ItemGroup.BETTER_STAFFS, 147, 229, 11, R.drawable.item_magenta_staff, Material.MAGENTA_ESSENCE),
    WHITE_STAFF("White Staff", ItemGroup.BETTER_STAFFS, 151, 238, 11, R.drawable.item_white_staff, Material.WHITE_ESSENCE),

    RED_HELMET("Red Helmet", ItemGroup.HELMETS, 155, 246, 11, R.drawable.item_red_helmet, Material.RED_ESSENCE),
    ORANGE_HELMET("Orange Helmet", ItemGroup.HELMETS, 158, 252, 11, R.drawable.item_orange_helmet, Material.ORANGE_ESSENCE),
    YELLOW_HELMET("Yellow Helmet", ItemGroup.HELMETS, 161, 259, 11, R.drawable.item_yellow_helmet, Material.YELLOW_ESSENCE),
    GREEN_HELMET("Green Helmet", ItemGroup.HELMETS, 163, 265, 12, R.drawable.item_green_helmet, Material.GREEN_ESSENCE),
    BLUE_HELMET("Blue Helmet", ItemGroup.HELMETS, 167, 272, 12, R.drawable.item_blue_helmet, Material.BLUE_ESSENCE),
    MAGENTA_HELMET("Magenta Helmet", ItemGroup.HELMETS, 170, 280, 12, R.drawable.item_magenta_helmet, Material.MAGENTA_ESSENCE),
    WHITE_HELMET("White Helmet", ItemGroup.HELMETS, 173, 285, 12, R.drawable.item_white_helmet, Material.WHITE_ESSENCE),

    RED_BOOTS("Red Boots", ItemGroup.BOOTS, 176, 291, 12, R.drawable.item_red_boots, Material.RED_ESSENCE),
    ORANGE_BOOTS("Orange Boots", ItemGroup.BOOTS, 178, 295, 12, R.drawable.item_orange_boots, Material.ORANGE_ESSENCE),
    YELLOW_BOOTS("Yellow Boots", ItemGroup.BOOTS, 181, 300, 12, R.drawable.item_yellow_boots, Material.YELLOW_ESSENCE),
    GREEN_BOOTS("Green Boots", ItemGroup.BOOTS, 184, 305, 12, R.drawable.item_green_boots, Material.GREEN_ESSENCE),
    BLUE_BOOTS("Blue Boots", ItemGroup.BOOTS, 187, 314, 12, R.drawable.item_blue_boots, Material.BLUE_ESSENCE),
    MAGENTA_BOOTS("Magenta Boots", ItemGroup.BOOTS, 190, 321, 13, R.drawable.item_magenta_boots, Material.MAGENTA_ESSENCE),
    WHITE_BOOTS("White Boots", ItemGroup.BOOTS, 193, 328, 13, R.drawable.item_white_boots, Material.WHITE_ESSENCE),

    RED_TRION("Red Trion", ItemGroup.TRIONS, 198, 335, 13, R.drawable.item_red_trion, Material.RED_ESSENCE),
    ORANGE_TRION("Orange Trion", ItemGroup.TRIONS, 201, 342, 13, R.drawable.item_orange_trion, Material.ORANGE_ESSENCE),
    YELLOW_TRION("Yellow Trion", ItemGroup.TRIONS, 205, 349, 13, R.drawable.item_yellow_trion, Material.YELLOW_ESSENCE),
    GREEN_TRION("Green Trion", ItemGroup.TRIONS, 210, 358, 13, R.drawable.item_green_trion, Material.GREEN_ESSENCE),
    BLUE_TRION("Blue Trion", ItemGroup.TRIONS, 215, 367, 13, R.drawable.item_blue_trion, Material.BLUE_ESSENCE),
    MAGENTA_TRION("Magenta Trion", ItemGroup.TRIONS, 219, 375, 13, R.drawable.item_magenta_trion, Material.MAGENTA_ESSENCE),
    WHITE_TRION("White Trion", ItemGroup.TRIONS, 223, 382, 13, R.drawable.item_white_trion, Material.WHITE_ESSENCE),

    RED_HEXAGON("Red Hexagon", ItemGroup.HEXAGONS, 228, 390, 13, R.drawable.item_red_hexagon, Material.RED_ESSENCE),
    ORANGE_HEXAGON("Orange Hexagon", ItemGroup.HEXAGONS, 233, 399, 13, R.drawable.item_orange_hexagon, Material.ORANGE_ESSENCE),
    YELLOW_HEXAGON("Yellow Hexagon", ItemGroup.HEXAGONS, 238, 409, 13, R.drawable.item_yellow_hexagon, Material.YELLOW_ESSENCE),
    GREEN_HEXAGON("Green Hexagon", ItemGroup.HEXAGONS, 248, 419, 13, R.drawable.item_green_hexagon, Material.GREEN_ESSENCE),
    BLUE_HEXAGON("Blue Hexagon", ItemGroup.HEXAGONS, 258, 430, 14, R.drawable.item_blue_hexagon, Material.BLUE_ESSENCE),
    MAGENTA_HEXAGON("Magenta Hexagon", ItemGroup.HEXAGONS, 269, 438, 14, R.drawable.item_magenta_hexagon, Material.MAGENTA_ESSENCE),
    WHITE_HEXAGON("White Hexagon", ItemGroup.HEXAGONS, 280, 449, 14, R.drawable.item_white_hexagon, Material.WHITE_ESSENCE),

    MACE1("Mace [Type 1]", ItemGroup.MACES, 299, 450, 14, R.drawable.item_mace1, Material.BARTAZITE),
    MACE2("Mace [Type 2]", ItemGroup.MACES, 289, 450, 14, R.drawable.item_mace2, Material.BARTAZITE),
    MACE3("Mace [Type 3]", ItemGroup.MACES, 270, 450, 14, R.drawable.item_mace3, Material.BARTAZITE),

    BLADE_TYPE1("Blade [Type 1]", ItemGroup.BLADES, 300, 455, 15, R.drawable.item_crystalite_blade, Material.CRYSTALITE),
    BLADE_TYPE2("Blade [Type 2]", ItemGroup.BLADES, 306, 470, 15, R.drawable.item_crystalite_blade2, Material.CRYSTALITE),
    BLADE_TYPE3("Blade [Type 3]", ItemGroup.BLADES, 312, 480, 15, R.drawable.item_crystalite_blade3, Material.CRYSTALITE),

    HEAVY_TYPE1("Heavy [Type 1]", ItemGroup.HEAVY, 320, 500, 15, R.drawable.item_geolite_heavy1, Material.GEOLITE),
    HEAVY_TYPE2("Heavy [Type 2]", ItemGroup.HEAVY, 332, 520, 15, R.drawable.item_geolite_heavy2, Material.GEOLITE),
    HEAVY_TYPE3("Heavy [Type 3]", ItemGroup.HEAVY, 348, 545, 16, R.drawable.item_geolite_heavy3, Material.GEOLITE),
    HEAVY_TYPE4("Heavy [Type 4]", ItemGroup.HEAVY, 368, 585, 16, R.drawable.item_geolite_heavy4, Material.GEOLITE),

    MONO_TYPE1("Mono [Type 1]", ItemGroup.MONO, 380, 640, 16, R.drawable.item_monolite_mono1, Material.MONOLITE),
    MONO_TYPE2("Mono [Type 2]", ItemGroup.MONO, 395, 700, 16, R.drawable.item_monolite_mono2, Material.MONOLITE),
    MONO_TYPE3("Mono [Type 3]", ItemGroup.MONO, 418, 770, 16, R.drawable.item_monolite_mono3, Material.MONOLITE),

    TECHNOSCYTHE1("TechnoScythe [Type 1]", ItemGroup.TECHNOSCYTHE, 440, 855, 16, R.drawable.item_iridiolite_technoscythe1, Material.IRIDIOLITE),
    TECHNOSCYTHE2("TechnoScythe [Type 2]", ItemGroup.TECHNOSCYTHE, 465, 940, 16, R.drawable.item_iridiolite_technoscythe2, Material.IRIDIOLITE),
    TECHNOSCYTHE3("TechnoScythe [Type 3]", ItemGroup.TECHNOSCYTHE, 491, 1050, 17, R.drawable.item_iridiolite_technoscythe3, Material.IRIDIOLITE),

    CATALYST1("Catalyst Dagger [Type 1]", ItemGroup.CATALYST, 686, 1310, 17, R.drawable.item_catalyst_dagger1, Material.CATALYST),
    CATALYST2("Catalyst Dagger [Type 2]", ItemGroup.CATALYST, 794, 1586, 17, R.drawable.item_catalyst_dagger2, Material.CATALYST),
    CATALYST3("Catalyst Dagger [Type 3]", ItemGroup.CATALYST, 902, 1875, 17, R.drawable.item_catalyst_dagger3, Material.CATALYST),

    NEONIDE1("Neonide [Type 1]", ItemGroup.NEONIDE, 902, 1875, 18, R.drawable.item_neoniste_neonide1, Material.NEONISTE),
    NEONIDE2("Neonide [Type 2]", ItemGroup.NEONIDE, 1000, 2650, 18, R.drawable.item_neoniste_neonide2, Material.NEONISTE),
    NEONIDE3("Neonide [Type 3]", ItemGroup.NEONIDE, 1050, 3550, 18, R.drawable.item_neoniste_neonide3, Material.NEONISTE),

    PONY1("Pony [Type 1]", ItemGroup.PONIES, 1100, 3850, 19, R.drawable.item_pony1, Material.NYANITE),
    PONY2("Pony [Type 2]", ItemGroup.PONIES, 1200, 4200, 19, R.drawable.item_pony2, Material.NYANITE),
    PONY3("Pony [Type 3]", ItemGroup.PONIES, 1250, 4455, 19, R.drawable.item_pony3, Material.NYANITE),
    PONY4("Pony [Type 4]", ItemGroup.PONIES, 1300, 4700, 19, R.drawable.item_pony4, Material.NYANITE),

    LUNARTIC("LUNARTIC", ItemGroup.LUNARTIC, 1720, 7100, 20, R.drawable.item_lunartism_lunartic, Material.LUNARTISM),

    CANDY_CANE1("Candy Cane - Red", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane1, Material.SUGAR),
    CANDY_CANE2("Candy Cane - Blue", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane2, Material.SUGAR),
    CANDY_CANE3("Candy Cane - Green", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane3, Material.SUGAR),
    CANDY_CANE4("Candy Cane - Yellow", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane4, Material.SUGAR),
    CANDY_CANE5("Candy Cane - Pink", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane5, Material.SUGAR),
    CANDY_CANE6("Candy Cane - Purple", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane6, Material.SUGAR),
    CANDY_CANE7("Candy Cane - Orange", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane7, Material.SUGAR),
    CANDY_CANE8("Candy Cane - Gray", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane8, Material.SUGAR),
    CANDY_CANE9("Candy Cane - Aqua", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane9, Material.SUGAR),
    CANDY_CANE10("Candy Cane - Brown", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane10, Material.SUGAR),
    CANDY_CANE11("Candy Cane - White", ItemGroup.CANDY_CANE, 3, 1, 2, R.drawable.item_candy_cane11, Material.SUGAR),
    ;

    public String name;
    public ItemGroup itemGroup;
    public int requiredClicks;
    public int iconId;
    public float sellPrice;
    public float materialCost;

    public Material material;

    public boolean owningItem = false;

    public Bitmap itemShadow;

    Item(String name, ItemGroup itemGroup, int requiredClicks, int sellPrice, int materialCost, int iconId, Material material) {
        this.name = name;
        this.itemGroup = itemGroup;
        this.requiredClicks = requiredClicks;
        this.iconId = iconId;
        this.sellPrice = sellPrice;
        this.materialCost = materialCost;
        this.material = material;
    }

    public static void Init(Context context) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;

        Item[] items = Item.values();

        //Generate item shadow
        for (int i = 0; i < items.length; i++) {
            Bitmap itemShadow = BitmapFactory.decodeResource(context.getResources(), items[i].iconId, opts);
            Bitmap newItemShadow = Bitmap.createScaledBitmap(itemShadow, 160, 160, false);

            items[i].itemShadow = newItemShadow;
        }

        InitializeRarityItemsArrayLists(items);
    }

    public static void InitializeRarityItemsArrayLists(Item[] items) {
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
