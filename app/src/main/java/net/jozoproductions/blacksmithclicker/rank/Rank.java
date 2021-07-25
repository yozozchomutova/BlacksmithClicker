package net.jozoproductions.blacksmithclicker.rank;

import net.jozoproductions.blacksmithclicker.R;

public enum Rank {
    BRONZE(R.drawable.rank_bronze, R.color.rank_bronze, "Bronze", 2000), //2,000
    IRON(R.drawable.rank_iron, R.color.rank_iron, "Iron", 10000), //10,000
    GOLD(R.drawable.rank_gold, R.color.rank_gold, "Gold", 50000), //50,000
    PLATINUM(R.drawable.rank_platinum, R.color.rank_platinum, "Platinum", 250000), //250,000
    DIAMOND(R.drawable.rank_diamond, R.color.rank_diamond, "Diamond", 1100000), //1,100,000
    RUBY(R.drawable.rank_ruby, R.color.rank_ruby, "Ruby", 5000000), //5,000,000
    AMETHYST(R.drawable.rank_amethyst, R.color.rank_amethyst, "Amethyst", 50000000), //50,000,000
    AURORA(R.drawable.rank_aurora, R.color.rank_aurora, "Aurora", 1000000000); //1,000,000,000

    public int iconId;
    public int rankId;
    public String name;
    public int neededExpToAdvance;

    Rank(int iconId, int rankId, String name, int neededExpToAdvance) {
        this.iconId = iconId;
        this.rankId = rankId;
        this.name = name;
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
