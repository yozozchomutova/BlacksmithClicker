package net.jozoproductions.blacksmithclicker;

import android.widget.TextView;

import com.google.firebase.Timestamp;
import com.google.firestore.v1.DocumentTransform;

import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.rank.Rank;
import net.jozoproductions.blacksmithclicker.research.Research;

public class Player {

    public static String name;

    public static float money = 0;
    public static float xp = 0;

    //Investing to server
    public static String continentName;
    public static long nextTimeToInvest = 0;
    public static float serverBonus = 1f;

    //Useless stats
    public static int totalClicks = 0;
    public static int totalForgedItems = 0;
    public static int totalForgedMaterials = 0;
    public static int totalMoneyMade = 0;
    public static int totalCratesOpened = 0;

    //Player and research stuff
    public static float researchPoints = 0;
    public static float researchPointChance = 0; //Chance of getting research point

    //Forging
    public static float itemForgeEffectiveness = 0f;
    public static float materialForgeEffectiveness = 0f;

    //Critical hit stuff
    public static float criticalHitChance = 0f;
    public static float criticalHitBonus = 0f;

    //Materials
    public static int marketSlots = 0; //For 1 side
    public static float materialMarketMaxValueDifference = 0;

    //Shake bonus
    public static boolean shakeBonusUnlocked;
    public static float shakeBonusEfficiency;

    public static boolean shakeBonusReady = false;
    public static int shakeBonusCountDown = 18000; //18,000 = 3 minutes

    public static boolean shakeBonusActivated = false;
    public static float curShakeBonusEffect = 1f;

    //UI
    public static TextView moneyText;

    public static void setMoneyText(TextView moneyText_) {
        moneyText = moneyText_;
        moneyText.setText("" + money);
    }

    public static void UnlockItem(Item item) {
        item.owningItem = true;
    }

    public static void AddMoney(float count) {
        AddMoney(count, true);
    }

    public static void AddMaterial(Material material, float count) {
        if (count > 0)
            totalForgedMaterials += count;

        material.count += count;
        AddXP(count);
    }

    public static void AddMoney(float count, boolean writeToStats) {
        if (writeToStats && count > 0)
            totalMoneyMade += count;

        money += count;
        AddXP(count);

        moneyText.setText("" + (int) money);
    }

    public static void AddResearchPoints(float count) {
        researchPoints += count;

        AddXP(1);
    }

    public static void AddXP(float count) {
        //XP Cannot be lost!
        if (count > 0)
            xp += count * (1f + Research.XP_BOOST.getEffect());
    }

    //This takes all upgrade nodes and converts it into real bonuses
    public static void CalculateResearchUpgrades() {
        researchPointChance = 0.4f + Research.RESEARCH_CHANCE.getEffect();

        itemForgeEffectiveness = 1f + Research.FORGE_EFFECTIVNES.getEffect();

        materialForgeEffectiveness = 1f + Research.PICKAXE_EFFECTIVNES.getEffect();

        criticalHitChance = Research.CRITICAL_HIT_CHANCE.getEffect();
        criticalHitBonus = 1.2f + Research.CRITICAL_HIT_BONUS.getEffect();

        shakeBonusUnlocked = Research.SHAKE_BONUS_ALLOW.curLevel == 1;
        shakeBonusEfficiency = 1.1f + Research.SHAKE_BONUS_EFFICIENCY.getEffect();

        marketSlots = 2 + (int) Research.OFFER_COUNT_ON_MARKET.getEffect();
        materialMarketMaxValueDifference = 0.1f + Research.MATERIAL_VALUE_LIMIT.getEffect();
    }

    public static Boolean readyToAdvanceBlacksmithRank() {
        for (Item item : Item.values()) {
            if (!item.owningItem)
                return false;
        }

        for (Material material : Material.values()) {
            if (!material.isResearched())
                return false;
        }

        for (Research research : Research.values()) {
            if (research == Research.WIN_GAME) {
                continue;
            }

            if (research.maxLevel != -1)
            {
                if (research.curLevel != research.maxLevel) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Boolean checkForFinishedGame() {
        if (!readyToAdvanceBlacksmithRank()) {
            return false;
        }

        if (Player.xp < Rank.BLACKSMITH.neededExpToAdvance) {
            return false;
        }

        if (Research.WIN_GAME.curLevel == 0) {
            return false;
        }

        return true;
    }

    public static int totalUnlockedItems() {
        int unlockedItems = 0;

        for (Item item : Item.values()) {
            if (item.owningItem)
                unlockedItems++;
        }

        return unlockedItems;
    }

    public static int totalUnlockedMaterials() {
        int unlockedMaterials = 0;

        for (Material mat : Material.values()) {
            if (mat.isResearched())
                unlockedMaterials++;
        }

        return unlockedMaterials;
    }

    public static int totalResearchedResearches() {
        int researchedResearches = 0;

        for (Research research : Research.values()) {
            if (research.maxLevel != -1)
            {
                if (research.curLevel == research.maxLevel) {
                    researchedResearches++;
                }
            }
        }

        return researchedResearches;
    }

    public static int totalMaxResearches() {
        int maxResearches = 0;

        for (Research research : Research.values()) {
            if (research.maxLevel != -1)
            {
                maxResearches++;
            }
        }

        return maxResearches;
    }
}
