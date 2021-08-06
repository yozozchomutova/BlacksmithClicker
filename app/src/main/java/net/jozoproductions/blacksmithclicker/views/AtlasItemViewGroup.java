package net.jozoproductions.blacksmithclicker.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.items.ItemGroup;

public class AtlasItemViewGroup extends ConstraintLayout {

    private LinearLayout horizontalList;

    public AtlasItemViewGroup(@NonNull Context context) {
        super(context);
        init();
    }

    public AtlasItemViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AtlasItemViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AtlasItemViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_atlas_item_group, this, true);

        horizontalList = findViewById(R.id.atlas_item_content);
    }

    public void setItemGroup(ItemGroup itemGroup) {
        ((TextView) findViewById(R.id.groupTitle)).setText(itemGroup.groupName);

        for (int i = 0; i < itemGroup.items.size(); i++) {
            AtlasItemView atlasItemView = new AtlasItemView(getContext());
            atlasItemView.setItem(itemGroup.items.get(i));

            horizontalList.addView(atlasItemView);
        }
    }
}
