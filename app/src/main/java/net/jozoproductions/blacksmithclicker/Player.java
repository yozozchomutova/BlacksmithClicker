package net.jozoproductions.blacksmithclicker;

import android.widget.TextView;

import net.jozoproductions.blacksmithclicker.items.InventoryItem;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.research.Research;

import java.util.ArrayList;

public class Player {

    public static String name;

    public static float money = 0;
    public static float xp = 0;

    public static ArrayList<Ingredient> ingredients = new ArrayList<>();

    //Useless stats
    public static int totalClicks = 0;
    public static int totalForgedItems = 0;
    public static int totalMoneyMade = 0;
    public static int totalCratesOpened = 0;

    //Player and research stuff
    public static float researchPoints = 0;
    public static float researchPointChance = 0; //Chance of getting research point

    //Forging
    public static float forgeEffectiveness = 0f;

    //UI
    public static TextView moneyText;

    //Crate prices
    public static Integer commonCrateOpenCount = 0;
    public static Integer uncommonCrateOpenCount = 0;
    public static Integer rareCrateOpenCount = 0;
    public static Integer epicCrateOpenCount = 0;
    public static Integer legendaryCrateOpenCount = 0;
    public static Integer mythicCrateOpenCount = 0;

    public static final float commonCrateBasePrice = 25;
    public static final float uncommonCrateBasePrice = 70;
    public static final float rareCrateBasePrice = 190;
    public static final float epicCrateBasePrice = 1000;
    public static final float legendaryCrateBasePrice = 10000;
    public static final float mythicCrateBasePrice = 999999999;

    public static void setMoneyText(TextView moneyText_) {
        moneyText = moneyText_;
        moneyText.setText("" + money);
    }

    public static void UnlockItem(Item item) {
        item.owningItem = true;
    }

    public static void AddMaterial(Material material, int count) {
        Process:
        {
            for (int i = 0; i < ingredients.size(); i++)
            {
                if (ingredients.get(i).material == material)
                {
                    ingredients.get(i).count += count;
                    break Process;
                }
            }

            ingredients.add(new Ingredient(material, count));
        }
    }

    public static void AddMoney(float count) {
        AddMoney(count, true);
    }

    public static void AddMoney(float count, boolean writeToStats) {
        if (writeToStats && count > 0)
            totalMoneyMade += count;

        money += count;

        //XP Cannot be lost!
        if (count > 0)
            xp += count;

        moneyText.setText("" + money);
    }

    public static void AddResearchPoints(int count) {
        researchPoints += count;
    }

    //This takes all upgrade nodes and converts it into real bonuses
    public static void CalculateResearchUpgrades() {
        //Reset values
        researchPointChance = 0f;
        forgeEffectiveness = 0f;

        //Counting
        if (Research.BLACKSMITH.researched) {
            researchPointChance += 0.2f;
        }

        if (Research.MORE_RESEARCHPOINTS_CHANCE1.researched) {
            researchPointChance += 0.1f;
        } if (Research.MORE_RESEARCHPOINTS_CHANCE2.researched) {
            researchPointChance += 0.2f;
        } if (Research.MORE_RESEARCHPOINTS_CHANCE3.researched) {
            researchPointChance += 0.2f;
        } if (Research.MORE_RESEARCHPOINTS_CHANCE4.researched) {
            researchPointChance += 0.2f;
        }

        //Forge effectivenes
        if (Research.BLACKSMITH.researched) {
            forgeEffectiveness += 0.5f;
        }

        if (Research.FORGE_EFFECTIVNESS1.researched) {
            forgeEffectiveness += 0.1f;
        } if (Research.FORGE_EFFECTIVNESS2.researched) {
            forgeEffectiveness += 0.1f;
        } if (Research.FORGE_EFFECTIVNESS3.researched) {
            forgeEffectiveness += 0.1f;
        }
    }
}
