package net.jozoproductions.blacksmithclicker.rank;

import net.jozoproductions.blacksmithclicker.R;

public enum Rank {
    BRONZE(R.drawable.rank_bronze, R.color.rank_bronze, "Bronze", 1f, 1200), //1,200
    IRON(R.drawable.rank_iron, R.color.rank_iron, "Iron", 1f, 7000), //7,000
    GOLD(R.drawable.rank_gold, R.color.rank_gold, "Gold", 1.05f, 40000), //40,000
    PLATINUM(R.drawable.rank_platinum, R.color.rank_platinum, "Platinum", 1.1f, 250000), //250,000
    DIAMOND(R.drawable.rank_diamond, R.color.rank_diamond, "Diamond", 1.15f, 600000), //600,000
    RUBY(R.drawable.rank_ruby, R.color.rank_ruby, "Ruby", 1.2f, 1350000), //1,350,000
    AMETHYST(R.drawable.rank_amethyst, R.color.rank_amethyst, "Amethyst", 1.25f, 3300000), //3,300,000
    AURORA(R.drawable.rank_aurora, R.color.rank_aurora, "Aurora", 1.3f, 8000000), //8,000,000
    BLACKSMITH(R.drawable.rank_blacksmith, R.color.rank_aurora, "Ultimate Blacksmith", 1.35f, 8000000); //8,000,000

    public int iconId;
    public int rankId;
    public String name;
    public float earningMultiplier;
    public int neededExpToAdvance;

    Rank(int iconId, int rankId, String name, float earningMultiplier, int neededExpToAdvance) {
        this.iconId = iconId;
        this.rankId = rankId;
        this.name = name;
        this.earningMultiplier = earningMultiplier;
        this.neededExpToAdvance = neededExpToAdvance;
    }

    public static Rank GetRank(float xp) {
        Rank[] ranks = Rank.values();

        for(int i = ranks.length-1; i >= 0; i--) {
            if (xp >= ranks[i].neededExpToAdvance) {
                return ranks[i];
            }
        }

        return ranks[0]; //First rank
    }

    public static Rank GetNextRank(float xp) {
        Rank[] ranks = Rank.values();

        for(int i = ranks.length-1; i >= 0; i--) {
            if (xp >= ranks[i].neededExpToAdvance) {
                if (i == ranks.length-1)
                    return ranks[i];
                else
                    return ranks[i+1];
            }
        }

        return ranks[0]; //First rank
    }
}
