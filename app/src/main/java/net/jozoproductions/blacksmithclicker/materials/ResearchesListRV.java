package net.jozoproductions.blacksmithclicker.materials;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.activities.ResearchActivity;
import net.jozoproductions.blacksmithclicker.rank.Rank;
import net.jozoproductions.blacksmithclicker.research.Research;

import java.util.List;

public class ResearchesListRV extends RecyclerView.Adapter<ResearchesListRV.ViewHolder> {

    private List<Research> researchList;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ResearchesListRV(Context context, List<Research> researchList) {
        this.mInflater = LayoutInflater.from(context);
        this.researchList = researchList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_research_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Research researchItem = researchList.get(position);

        holder.research = researchItem;

        holder.icon.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), researchItem.iconDrawableId));

        holder.nameTV.setText(researchItem.name);
        holder.descTV.setText(researchItem.description);
        holder.UpdateViews();

        //Unlocked? (by rank) Or: Reached max level?
        if (researchItem.minimumRank.ordinal() == 0) {
            holder.researchBtn.setEnabled(true);
            holder.researchBtn.setImageDrawable(ContextCompat.getDrawable(holder.nameTV.getContext(), R.drawable.research_node));
        } else if (researchItem.curLevel >= researchItem.maxLevel && researchItem.maxLevel != -1) {
            holder.researchBtn.setEnabled(false);
            holder.researchBtn.setImageDrawable(ContextCompat.getDrawable(holder.nameTV.getContext(), R.drawable.max));
            holder.priceTV.setText("");
        }
        else if (Rank.values()[researchItem.minimumRank.ordinal()-1].neededExpToAdvance > Player.xp) {
            holder.researchBtn.setEnabled(false);
            holder.researchBtn.setImageDrawable(ContextCompat.getDrawable(holder.nameTV.getContext(), researchItem.minimumRank.iconId));
            holder.priceTV.setText("");
        } else {
            holder.researchBtn.setEnabled(true);
            holder.researchBtn.setImageDrawable(ContextCompat.getDrawable(holder.nameTV.getContext(), R.drawable.research_node));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return researchList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Research research;

        ImageView icon;

        TextView nameTV;
        TextView descTV;
        TextView levelTV;
        TextView priceTV;

        ImageButton researchBtn;

        ConstraintLayout root;

        ViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.research_icon);

            nameTV = itemView.findViewById(R.id.research_name);
            descTV = itemView.findViewById(R.id.research_desc);
            levelTV = itemView.findViewById(R.id.research_cur_level);
            priceTV = itemView.findViewById(R.id.research_price);

            researchBtn = itemView.findViewById(R.id.research_btn);

            researchBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (Player.researchPoints >= research.getPrice()) {
                Player.AddResearchPoints(-research.getPrice());
                research.curLevel++;

                UpdateViews();
                ((ResearchActivity) levelTV.getContext()).UpdateResearchPoints();

                //Check if reached max level
                if (research.maxLevel != -1) {
                    if (research.curLevel >= research.maxLevel) {
                        researchBtn.setEnabled(false);
                        researchBtn.setImageDrawable(ContextCompat.getDrawable(nameTV.getContext(), R.drawable.max));
                        priceTV.setText("");
                    }
                }
            }
        }

        public void UpdateViews() {
            priceTV.setText("" + (int) research.getPrice());

            if (research.maxLevel == -1) { //Is infinite?
                levelTV.setText("" + research.curLevel + " | (" + research.getEffect() + ")");
            } else {
                levelTV.setText("" + research.curLevel + "/" + research.maxLevel + " | (" + research.getEffect() + ")");
            }
        }
    }
}