package net.jozoproductions.blacksmithclicker.materials;

import android.util.Log;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;

import java.util.Date;

public enum Material {
    WOOD("Wood", 0, 1.02f, R.drawable.mat_wood, 3, R.drawable.particle_chip),
    STONE("Stone", 2, 1.1f, R.drawable.mat_stone, 4, R.drawable.particle_stone),
    IRON("Iron", 2, 1.2f, R.drawable.mat_iron, 7, R.drawable.particle_spark),
    GOLD("Gold", 2, 1.3f, R.drawable.mat_gold, 10, R.drawable.particle_spark),
    DIAMOND("Diamond", 4, 1.35f, R.drawable.mat_diamond, 13, R.drawable.particle_spark),
    EMERALD("Emerald", 5, 1.4f, R.drawable.mat_emerald, 18, R.drawable.particle_green),
    RUBY("Ruby", 5, 1.45f, R.drawable.mat_ruby, 21, R.drawable.particle_red),

    TOPAZ("Topaz", 6, 1.5f, R.drawable.mat_topaz, 26, R.drawable.particle_yellow),
    SAPPHIRE("Sapphire", 6, 1.6f, R.drawable.mat_sapphire, 32, R.drawable.particle_blue),
    AMETHYST("Amethyst", 7, 1.7f, R.drawable.mat_amethyst, 38, R.drawable.particle_magenta),

    CERUSSITE("Cerussite", 8, 1.75f, R.drawable.mat_cerussite, 40, R.drawable.particle_white),

    RED_ESSENCE("Red Essence", 14, 1.8f, R.drawable.mat_red_essence, 45, R.drawable.particle_red),
    ORANGE_ESSENCE("Orange Essence", 14, 1.85f, R.drawable.mat_orange_essence, 50, R.drawable.particle_orange),
    YELLOW_ESSENCE("Yellow Essence", 14, 1.9f, R.drawable.mat_yellow_essence, 55, R.drawable.particle_yellow),
    GREEN_ESSENCE("Green Essence", 14, 1.95f, R.drawable.mat_green_essence, 60, R.drawable.particle_green),
    BLUE_ESSENCE("Blue Essence", 15, 2f, R.drawable.mat_blue_essence, 65, R.drawable.particle_blue),
    MAGENTA_ESSENCE("Magenta Essence", 15, 2.05f, R.drawable.mat_magenta_essence, 70, R.drawable.particle_magenta),
    WHITE_ESSENCE("White Essence", 15, 2.1f, R.drawable.mat_white_essence, 80, R.drawable.particle_white),
    BARTAZITE("Bartazite", 20, 2.15f, R.drawable.mat_bartazite, 85, R.drawable.particle_crystalite),

    CRYSTALITE("Crysta-Lite", 28, 2.2f, R.drawable.mat_crystalite, 90, R.drawable.particle_crystalite),
    GEOLITE("Geo-Lite", 28, 2.3f, R.drawable.mat_geolite, 100, R.drawable.particle_geolite),
    MONOLITE("Mono-Lite", 30,2.4f, R.drawable.mat_monolite, 110, R.drawable.particle_monolite),
    IRIDIOLITE("Iridio-Lite",  30, 2.5f, R.drawable.mat_iridiolite, 120, R.drawable.particle_iridiolite),

    CATALYST("Catalyst", 38, 2.6f, R.drawable.mat_catalyst, 130, R.drawable.particle_magenta),
    NEONISTE("NEONiste", 38, 2.7f, R.drawable.mat_neoniste, 155, R.drawable.particle_neoniste),
    NYANITE("Nyanite", 40, 2.8f, R.drawable.mat_nyanite, 170, R.drawable.particle_rainbow),
    LUNARTISM("Lunartism", 30, 2.9f, R.drawable.mat_lunartism, 200, R.drawable.particle_lunartism),

    SUGAR("Sugar", 2, 1, R.drawable.mat_sugar, 1, R.drawable.particle_lunartism);
    ;

    public String name;
    public int requiredResearches;

    public float materialValue;
    public float offerMultiplierValue = 1;

    public int drawableId;
    public int sparksDrawableId;

    public int requiredClicksToMine;

    public int curResearches;

    public float count;

    Material(String name, int requiredResearches, float materialValue, int drawableId, int requiredClicksToMine, int sparksDrawableId) {
        this.name = name;
        this.requiredResearches = requiredResearches;
        this.materialValue = materialValue;

        this.drawableId = drawableId;
        this.sparksDrawableId = sparksDrawableId;

        this.requiredClicksToMine = requiredClicksToMine;
    }

    public boolean isResearched() {
        return curResearches >= requiredResearches;
    }

    public static class MaterialMarket {

        public static long nextOfferTime;

        public static Material[] positiveMatOffer;
        public static Material[] negativeMatOffer;

        public static void GenerateNewOffer() {
            Material[] materials = Material.values();

            //Remove old offers (if exists ofc)
            if (positiveMatOffer != null) {
                for (int i = 0; i < positiveMatOffer.length; i++) {
                    positiveMatOffer[i].offerMultiplierValue = 1;
                }

                for (int i = 0; i < negativeMatOffer.length; i++) {
                    negativeMatOffer[i].offerMultiplierValue = 1;
                }
            }

            //Setup new
            nextOfferTime = System.currentTimeMillis() + 1800000 + MainActivity.random.nextInt(3600000); // Random: 30 mins -  90 mins

            positiveMatOffer = new Material[Player.marketSlots];
            negativeMatOffer = new Material[Player.marketSlots];

            //Generate new positive
            for (int i = 0; i < positiveMatOffer.length; i++) {
                Material randomMaterial = materials[MainActivity.random.nextInt(materials.length)];
                float randomMultiplier = 1f + (MainActivity.random.nextFloat() * Player.materialMarketMaxValueDifference);

                positiveMatOffer[i] = randomMaterial;
                randomMaterial.offerMultiplierValue = randomMultiplier;
            }

            //Generate new negative
            for (int i = 0; i < negativeMatOffer.length; i++) {
                Material randomMaterial = materials[MainActivity.random.nextInt(materials.length)];
                float randomMultiplier = 1f - (MainActivity.random.nextFloat() * Player.materialMarketMaxValueDifference);

                negativeMatOffer[i] = randomMaterial;
                randomMaterial.offerMultiplierValue = randomMultiplier;
            }
        }

        public static void CheckForNewOffer() {
            if (nextOfferTime < System.currentTimeMillis()) {
                GenerateNewOffer();
            }
        }
    }
}
