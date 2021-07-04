package net.jozoproductions.blacksmithclicker;

import android.widget.TextView;

import net.jozoproductions.blacksmithclicker.items.InventoryItem;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Ingredient;
import net.jozoproductions.blacksmithclicker.materials.Material;

import java.util.ArrayList;

public class Player {

    public static int money = 0;
    public static ArrayList<Ingredient> ingredients = new ArrayList<>();
    public static ArrayList<Item> unlockedItemRecipes = new ArrayList<>();

    //UI
    public static TextView moneyText;

    public static void setMoneyText(TextView moneyText_) {
        moneyText = moneyText_;
        moneyText.setText("" + money);

        unlockedItemRecipes.add(Item.STICK);
        unlockedItemRecipes.add(Item.STAR);
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

    public static void AddMoney(int count) {
        money += count;
        moneyText.setText("" + money);
    }
}
