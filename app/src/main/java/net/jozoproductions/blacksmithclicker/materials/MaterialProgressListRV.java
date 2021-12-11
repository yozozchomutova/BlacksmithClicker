package net.jozoproductions.blacksmithclicker.materials;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import net.jozoproductions.blacksmithclicker.R;

import java.util.List;

public class MaterialProgressListRV extends RecyclerView.Adapter<MaterialProgressListRV.ViewHolder> {

    private List<Material> materialList;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public MaterialProgressListRV(Context context, List<Material> materialList) {
        this.mInflater = LayoutInflater.from(context);
        this.materialList = materialList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_material_reasearch_progress, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Material material = materialList.get(position);

        holder.icon.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), material.drawableId));
        holder.name.setText(material.name);
        holder.progressBar.setMax(material.requiredResearches);
        holder.progressBar.setProgress(material.curResearches);

        //Change background color according to research progress
        if (material.isResearched())
            holder.root.setBackgroundColor(Color.argb(22, 0, 255, 0));
        else
            holder.root.setBackgroundColor(Color.argb(22, 255, 0, 0));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return materialList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView name;
        ProgressBar progressBar;
        ConstraintLayout root;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            icon = itemView.findViewById(R.id.materialIcon);
            progressBar = itemView.findViewById(R.id.smith_progress2);
            root = itemView.findViewById(R.id.root);
        }
    }
}