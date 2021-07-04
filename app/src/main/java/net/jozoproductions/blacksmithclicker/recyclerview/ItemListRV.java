package net.jozoproductions.blacksmithclicker.recyclerview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.SmithingItem;
import net.jozoproductions.blacksmithclicker.items.Item;

import java.util.List;

public class ItemListRV extends RecyclerView.Adapter<ItemListRV.ViewHolder> {

    public List<Item> itemList;
    public Context context;

    public ItemListRV(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemListRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(ItemListRV.ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.icon.setImageDrawable(context.getResources().getDrawable(item.iconId));
        holder.name.setText(item.name);
        holder.selectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmithingItem.ChangeItem(item);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView icon;
        public ImageButton selectItem;

        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            icon = (ImageView) view.findViewById(R.id.icon);
            selectItem = (ImageButton) view.findViewById(R.id.select_item);
        }
    }
}
