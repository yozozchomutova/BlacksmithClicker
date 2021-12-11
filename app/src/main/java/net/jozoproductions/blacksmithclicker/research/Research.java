package net.jozoproductions.blacksmithclicker.research;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.rank.Rank;

public enum Research {
    FORGE_EFFECTIVNES("Forge effectivity", "Increases forge effectivity.", R.drawable.research_forge_effectivnes, 2f, 2f, 10f, Rank.BRONZE),
    PICKAXE_EFFECTIVNES("Pickaxe effectivity", "Increases pickaxe effectivity.", R.drawable.research_pickaxe_effectivnes, 2f, 2f, 10f, Rank.IRON),
    CRITICAL_HIT_CHANCE("Critical hit chance", "Increases chance of getting critical hit when forging item.", R.drawable.research_critical_hit_chance, 2f, 2f, 10f, Rank.BRONZE),
    CRITICAL_HIT_BONUS("Critical hit bonus", "Increases amount of bonus, when you get critical hit.", R.drawable.research_critical_hit_bonus, 3f, 2f, 10f, Rank.BRONZE),
    MATERIAL_VALUE_LIMIT("Material value limits", "Materials can have bigger values.", R.drawable.research_material_value_limit, 20f, 2f, 10f, Rank.BRONZE),
    ;

    //Already set values
    public String name;
    public String description;

    public int iconDrawableId;

    public float basePrice;
    public float priceIncreaseRate;

    public float effectIncrease;

    public Rank minimumRank;

    //Values that are changing during runtime
    public int curLevel = 0;

    Research(String name, String description, int iconDrawableId, float basePrice, float priceIncreaseRate, float effectIncrease, Rank minimumRank) {
        this.name = name;
        this.description = description;

        this.iconDrawableId = iconDrawableId;

        this.basePrice = basePrice;
        this.priceIncreaseRate = priceIncreaseRate;

        this.effectIncrease = effectIncrease;

        this.minimumRank = minimumRank;
    }

    public float getPrice() {
        return (float) (basePrice * Math.pow(priceIncreaseRate, curLevel));
    }

    public float getEffect() {
        return effectIncrease * curLevel;
    }
}
