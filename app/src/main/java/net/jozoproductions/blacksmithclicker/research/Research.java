package net.jozoproductions.blacksmithclicker.research;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.rank.Rank;

public enum Research {
    FORGE_EFFECTIVNES("Forge effectivity", "Increases forge effectivity.", R.drawable.research_forge_effectivnes, 2f, 2f, 0.1f, -1, Rank.BRONZE),
    PICKAXE_EFFECTIVNES("Pickaxe effectivity", "Increases pickaxe effectivity.", R.drawable.research_pickaxe_effectivnes, 2f, 2f, 0.1f, -1, Rank.BRONZE),
    CRITICAL_HIT_CHANCE("Critical hit chance", "Increases chance of getting critical hit when forging item.", R.drawable.research_critical_hit_chance, 2f, 2f, 0.1f, 8, Rank.IRON),
    CRITICAL_HIT_BONUS("Critical hit bonus", "Increases amount of bonus, when you get critical hit.", R.drawable.research_critical_hit_bonus, 3f, 2f, 0.1f, -1, Rank.IRON),
    OFFER_COUNT_ON_MARKET("Offer limit (Market)", "Count of offers in material market.", R.drawable.research_material_offer_limit, 10f, 5f, 1f, 8, Rank.IRON),
    MATERIAL_VALUE_LIMIT("Material value limits", "Materials can have bigger (and also lower) values in market.", R.drawable.research_material_value_limit, 10f, 5f, 0.05f, -1, Rank.GOLD),
    RESEARCH_CHANCE("Research drop chance", "Better chance for getting research point", R.drawable.research_researchpoints, 10f, 10f, 0.4f, 20, Rank.GOLD),
    SHAKE_BONUS_ALLOW("Shake bonus", "Sometimes, you will be able to shake your phone to get forge/pickaxe bonus.", R.drawable.research_shake_bonus_allow, 20, 0f, 1f, 1, Rank.PLATINUM),
    SHAKE_BONUS_EFFICIENCY("Shake bonus efficiency", "Shake bonus speeds up your forging/mining.", R.drawable.research_shake_bonus_efficiency, 15f, 10f, 1f, 7, Rank.PLATINUM),
    MORE_MATERIALS("More materials", "Mining gives you more materials.", R.drawable.research_more_materials, 24f, 11, 1, 2, Rank.DIAMOND),
    FASTER_MATERIAL_RESEARCH("Faster material research", "Your material research will go faster.", R.drawable.research_faster_material_research, 20f, 15, 1, 3, Rank.DIAMOND),
    XP_BOOST("XP Boost", "More xp.", R.drawable.research_xp_boost, 4f, 2f, 0.02f, 40, Rank.RUBY),
    GOLD_MINE("Gold mine", "Amount of money you'll get, while you're offline.", R.drawable.research_gold_mine, 20f, 5f, 0.5f, -1, Rank.RUBY),
    BRIBE_CONTINENT("Bribe continent", "Bribing continent gives you bigger bonus.", R.drawable.research_bribe_continent, 15f, 10f, 0.05f, 10, Rank.AMETHYST),
    BOOST_CONTINENT("Boost continent", "Adds some bonus money to continent you support.", R.drawable.research_boost_continent, 30f, 15f, 0.05f, 10, Rank.AMETHYST),
    CRATE_REFUND("Item refund", "Get some money back, if you get item, you already have.", R.drawable.research_crate_refund, 10f, 5f, 0.05f, 18, Rank.AURORA),
    CRATE_DISCOUNT("Crate discount", "Decreases price of all crates.", R.drawable.research_crate_discount, 20f, 10f, 0.05f, 10, Rank.AURORA),
    WIN_GAME("Win game", "Research you need for winning game.", R.drawable.research_win_game, 5000f, 0, 1, 1, Rank.BLACKSMITH),
    ;

    //Already set values
    public String name;
    public String description;

    public int iconDrawableId;

    public float basePrice;
    public float priceIncreaseRate;

    public float effectIncrease;

    public int maxLevel;

    public Rank minimumRank;

    //Values that are changing during runtime
    public int curLevel = 0;

    Research(String name, String description, int iconDrawableId, float basePrice, float priceIncreaseRate, float effectIncrease, int maxLevel, Rank minimumRank) {
        this.name = name;
        this.description = description;

        this.iconDrawableId = iconDrawableId;

        this.basePrice = basePrice;
        this.priceIncreaseRate = priceIncreaseRate;

        this.effectIncrease = effectIncrease;

        this.maxLevel = maxLevel;

        this.minimumRank = minimumRank;
    }

    public float getPrice() {
        return (float) (basePrice + curLevel * priceIncreaseRate);
    }

    public float getEffect() {
        return effectIncrease * curLevel;
    }
}
