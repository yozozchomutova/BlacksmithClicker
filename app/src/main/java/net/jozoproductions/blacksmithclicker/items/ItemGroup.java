package net.jozoproductions.blacksmithclicker.items;

import java.util.ArrayList;

public enum ItemGroup {
    STICKS("Sticks", Rarity.COMMON),
    DAGGERS("Daggers", Rarity.COMMON),
    WSWORDS("Wooden swords", Rarity.COMMON),
    ISWORDS("Iron swords", Rarity.COMMON),
    LIGHTSWORDS("Light swords", Rarity.UNCOMMON),
    VESTS("Vests", Rarity.UNCOMMON),
    STAFFS("Staffs", Rarity.UNCOMMON),
    SHURIKENS("Shurikens", Rarity.UNCOMMON),
    CHESTPLATES("Chestplates", Rarity.RARE),
    SHOES("Shoes", Rarity.RARE),
    BETTER_STAFFS("Staffs", Rarity.RARE),
    HELMETS("Helmets", Rarity.RARE),
    BOOTS("Boots", Rarity.EPIC),
    TRIONS("Trions", Rarity.EPIC),
    HEXAGONS("Hexagons", Rarity.EPIC),
    ;

    public String groupName;
    public Rarity rarity;

    public ArrayList<Item> items = new ArrayList<>();

    ItemGroup(String groupName, Rarity rarity) {
        this.groupName = groupName;
        this.rarity = rarity;
    }
}
