package net.jozoproductions.blacksmithclicker.materials;

import android.graphics.drawable.Drawable;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;

public enum Material {
    WOOD("Wood", Drawable.createFromPath(MainActivity.DRAWABLE_PREFIX + R.drawable.mat_wood));

    public String name;
    public Drawable icon;

    Material(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }
}
