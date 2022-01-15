package net.jozoproductions.blacksmithclicker.items;

import java.util.ArrayList;

public enum ItemGroup {
    STICKS("Sticks", Rarity.COMMON, 1),
    DAGGERS("Daggers", Rarity.COMMON, 1),
    WSWORDS("Wooden swords", Rarity.COMMON, 2),
    ISWORDS("Iron swords", Rarity.COMMON, 2),
    LIGHTSWORDS("Light swords", Rarity.UNCOMMON, 4),
    VESTS("Vests", Rarity.UNCOMMON, 4),
    STAFFS("Staffs", Rarity.UNCOMMON, 6),
    SHURIKENS("Shurikens", Rarity.UNCOMMON, 6),
    CHESTPLATES("Chestplates", Rarity.RARE, 8),
    SHOES("Shoes", Rarity.RARE, 9),
    BETTER_STAFFS("Staffs", Rarity.RARE, 10),
    HELMETS("Helmets", Rarity.RARE, 11),
    BOOTS("Boots", Rarity.EPIC, 13),
    TRIONS("Trions", Rarity.EPIC, 14),
    HEXAGONS("Hexagons", Rarity.EPIC, 15),
    MACES("Maces", Rarity.EPIC, 16),
    BLADES("Blades", Rarity.LEGENDARY, 18),
    HEAVY("Heavies", Rarity.LEGENDARY, 20),
    MONO("Monos", Rarity.LEGENDARY, 22),
    TECHNOSCYTHE("TechnoScythes", Rarity.LEGENDARY, 24),
    CATALYST("Catalysts", Rarity.MYTHIC, 25),
    NEONIDE("Neonides", Rarity.MYTHIC, 28),
    PONIES("Ponies", Rarity.MYTHIC, 31),
    LUNARTIC("Lunartics", Rarity.MYTHIC, 34),
    CANDY_CANE("Candy canes", Rarity.CHRISTMAS, 2),
    ;

    public String groupName;
    public Rarity rarity;
    public int materialConsume;

    public ArrayList<Item> items = new ArrayList<>();

    ItemGroup(String groupName, Rarity rarity, int materialConsume) {
        this.groupName = groupName;
        this.rarity = rarity;
        this.materialConsume = materialConsume;
    }
}
