package net.jozoproductions.blacksmithclicker.research;

import net.jozoproductions.blacksmithclicker.R;

public enum Research {
    BLACKSMITH("Blacksmith starter pack", "First needed items, to help you with start.", R.drawable.anvil, 0, null), //First initial research, that is always researched.

    MORE_RESEARCHPOINTS_CHANCE1("Bigger research chance 1", "Increases chance of getting a research point. (+0.1%)", R.drawable.research_researchpoints, 2, BLACKSMITH),
    MORE_RESEARCHPOINTS_CHANCE2("Bigger research chance 2", "Increases chance of getting a research point. (+0.2%)", R.drawable.research_researchpoints, 5, MORE_RESEARCHPOINTS_CHANCE1),
    MORE_RESEARCHPOINTS_CHANCE3("Bigger research chance 3", "Increases chance of getting a research point. (+0.2%)", R.drawable.research_researchpoints, 13, MORE_RESEARCHPOINTS_CHANCE2),
    MORE_RESEARCHPOINTS_CHANCE4("Bigger research chance 4", "Increases chance of getting a research point. (+0.2%)", R.drawable.research_researchpoints, 25, MORE_RESEARCHPOINTS_CHANCE3),

    FORGE_EFFECTIVNESS1("Forge effectiveness 1", "Increases forge effectiveness. (+10%)", R.drawable.research_forge_effectivnes, 2, BLACKSMITH),
    FORGE_EFFECTIVNESS2("Forge effectiveness 2", "Increases forge effectiveness. (+10%)", R.drawable.research_forge_effectivnes, 5, FORGE_EFFECTIVNESS1),
    FORGE_EFFECTIVNESS3("Forge effectiveness 3", "Increases forge effectiveness. (+10%)", R.drawable.research_forge_effectivnes, 10, FORGE_EFFECTIVNESS2),

    CRITICAL_HIT_CHANCE1("Critical hit chance 1", "Increases chance of getting critical hit when forging item. (+5%)", R.drawable.research_critical_hit_chance, 2, BLACKSMITH),
    CRITICAL_HIT_CHANCE2("Critical hit chance 2", "Increases chance of getting critical hit when forging item. (+5%)", R.drawable.research_critical_hit_chance, 4, CRITICAL_HIT_CHANCE1),
    CRITICAL_HIT_CHANCE3("Critical hit chance 3", "Increases chance of getting critical hit when forging item. (+5%)", R.drawable.research_critical_hit_chance, 7, CRITICAL_HIT_CHANCE2),

    CRITICAL_HIT_BONUS1("Critical hit bonus 1", "Increases amount of bonus, when you get critical hit. (+5%)", R.drawable.research_critical_hit_bonus, 3, CRITICAL_HIT_CHANCE1),
    CRITICAL_HIT_BONUS2("Critical hit bonus 2", "Increases amount of bonus, when you get critical hit. (+5%)", R.drawable.research_critical_hit_bonus, 5, CRITICAL_HIT_BONUS1),
    ;

    public String name;
    public String description;

    public int drawableId;
    public int researchPointsCost;
    public Research requiredResearch;

    public boolean researched = false;

    Research(String name, String description, int drawableId, int researchPointsCost, Research requiredResearch) {
        this.name = name;
        this.description = description;
        this.drawableId = drawableId;
        this.researchPointsCost = researchPointsCost;
        this.requiredResearch = requiredResearch;

        if (requiredResearch == null)
            this.requiredResearch = this;
    }
}
