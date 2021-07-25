package net.jozoproductions.blacksmithclicker;

import android.widget.TextView;

import net.jozoproductions.blacksmithclicker.items.InventoryItem;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public class Player {

    public static String name;

    public static float money = 0;
    public static float researchPoints = 0;
    public static float xp = 0;

    public static float researchPointChance = 0.2f; //Chance of getting research point

    public static ArrayList<Ingredient> ingredients = new ArrayList<>();
    public static ArrayList<Item> unlockedItemRecipes = new ArrayList<>();

    //UI
    public static TextView moneyText;

    //Crate prices
    public static Integer commonCrateOpenCount = new Integer(0);
    public static Integer uncommonCrateOpenCount = 0;
    public static Integer rareCrateOpenCount = 0;
    public static Integer epicCrateOpenCount = 0;
    public static Integer legendaryCrateOpenCount = 0;
    public static Integer mythicCrateOpenCount = 0;

    public static final float commonCrateBasePrice = 50;
    public static final float uncommonCrateBasePrice = 100;
    public static final float rareCrateBasePrice = 999999999;
    public static final float epicCrateBasePrice = 999999999;
    public static final float legendaryCrateBasePrice = 999999999;
    public static final float mythicCrateBasePrice = 999999999;

    public static void setMoneyText(TextView moneyText_) {
        moneyText = moneyText_;
        moneyText.setText("" + money);
    }

    public static void AddItemRecipe(Item item) {
        unlockedItemRecipes.add(item);
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
        money += count;

        //XP Cannot be lost!
        if (count > 0)
            xp += count;

        moneyText.setText("" + money);
    }

    public static void AddResearchPoints(int count) {
        researchPoints += count;
    }
}
