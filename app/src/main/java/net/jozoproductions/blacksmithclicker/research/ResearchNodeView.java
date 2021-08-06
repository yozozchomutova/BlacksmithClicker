package net.jozoproductions.blacksmithclicker.research;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.activities.ResearchActivity;

public class ResearchNodeView extends ConstraintLayout implements View.OnClickListener {

    public Research research;

    private static Drawable researchNodeBcgAvailable;
    private static Drawable researchNodeBcgUnavailable;
    private static Drawable researchNodeBcgResearched;

    private ImageView nodeBackground;
    private ImageView contentIcon;

    public ResearchNodeView(@NonNull Context context) {
        super(context);
        init();
    }

    public ResearchNodeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResearchNodeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ResearchNodeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_research_node, this, true);

        researchNodeBcgAvailable = ContextCompat.getDrawable(getContext(), R.drawable.research_node_background);
        researchNodeBcgUnavailable = ContextCompat.getDrawable(getContext(), R.drawable.research_node_disabled_background);
        researchNodeBcgResearched = ContextCompat.getDrawable(getContext(), R.drawable.research_node_researched_background);

        nodeBackground = findViewById(R.id.node_background);
        contentIcon = findViewById(R.id.research_content);
    }

    public void setResearch(Research research) {
        this.research = research;

        contentIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), research.drawableId));
        contentIcon.setOnClickListener(this);

        System.out.println("Research: " + research.name + research.researched);
        if (research.researched) {
            nodeBackground.setImageDrawable(researchNodeBcgResearched);
        } else if (research.requiredResearch.researched) {
            nodeBackground.setImageDrawable(researchNodeBcgAvailable);
        } else {
            nodeBackground.setImageDrawable(researchNodeBcgUnavailable);
        }
    }

    @Override
    public void onClick(View v) {
        ((ResearchActivity) getContext()).AppearResearchPanel(this);
    }
}
