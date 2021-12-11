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

    //Critical hit stuff
    public static float criticalHitChance = 0f;
    public static float criticalHitBonus = 0f;

    //

    //UI
    public static TextView moneyText;

    public static void setMoneyText(TextView moneyText_) {
        moneyText = moneyText_;
        moneyText.setText("" + money);
    }

    public static void UnlockItem(Item item) {
        item.owningItem = true;
    }

    public static boolean HasItem(Item item) {
        return item.owningItem;
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

        moneyText.setText("" + (int) money);
    }

    public static void AddResearchPoints(int count) {
        researchPoints += count;
    }

    //This takes all upgrade nodes and converts it into real bonuses
    public static void CalculateResearchUpgrades() {
        //Reset values
        researchPointChance = 0f;
        forgeEffectiveness = 0f;
        criticalHitChance = 0f;
        criticalHitBonus = 0f;

        researchPointChance = 1f;
        forgeEffectiveness = 1f;
    }
}
