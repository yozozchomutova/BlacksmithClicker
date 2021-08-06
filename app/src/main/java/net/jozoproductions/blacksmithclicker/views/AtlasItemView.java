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
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.ResourcesCompat;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.SmithingItem;
import net.jozoproductions.blacksmithclicker.items.Item;

public class AtlasItemView extends ConstraintLayout implements View.OnClickListener {

    private Item item;

    private AppCompatTextView itemTitle;
    private ImageView itemRarity;
    private ImageView itemIcon;
    private ImageView itemIngredientMaterial;

    public AtlasItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public AtlasItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AtlasItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AtlasItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_atlas_item, this, true);

        itemTitle = findViewById(R.id.item_name);
        itemRarity = findViewById(R.id.item_rarity);
        itemIcon = findViewById(R.id.item_icon);
        itemIngredientMaterial = findViewById(R.id.item_ingredient_material);
    }

    public void setItem(Item item) {
        this.item = item;

        itemRarity.setColorFilter(getResources().getColor(item.itemGroup.rarity.colorId));
        itemIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), item.iconId, null));

        if (item.owningItem) {
            itemTitle.setText(item.name);
            itemIngredientMaterial.setImageDrawable(ContextCompat.getDrawable(getContext(), item.material.drawableId));
            itemIcon.setOnClickListener(this);
        } else {
            itemTitle.setText("???");
            itemIcon.setColorFilter(Color.argb(255, 0, 0, 0), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public void onClick(View v) {
        SmithingItem.ChangeItem(item);
        ((AppCompatActivity) getContext()).finish();
    }
}
