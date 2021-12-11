package net.jozoproductions.blacksmithclicker.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.SmithingItem;
import net.jozoproductions.blacksmithclicker.items.Item;

public class ItemInCrateView extends ConstraintLayout {

    public Item item;

    private ImageView itemIcon;

    private ConstraintLayout root;

    public ItemInCrateView(@NonNull Context context) {
        super(context);
        init();
    }

    public ItemInCrateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemInCrateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ItemInCrateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_item_in_crate, this, true);

        itemIcon = findViewById(R.id.item_icon);
        root = findViewById(R.id.root);
    }

    public void setItem(Item item) {
        this.item = item;

        itemIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), item.iconId));
        root.setBackgroundTintList(getContext().getResources().getColorStateList(item.itemGroup.rarity.colorId));
    }
}
