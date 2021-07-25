package net.jozoproductions.blacksmithclicker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.crates.Crate;
import net.jozoproductions.blacksmithclicker.crates.CrateItem;

public class CrateListFragment extends Fragment {

    public CrateListFragment() {
        super(R.layout.fragment_crate_list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crate_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize crates
        Crate[] crates = Crate.values();

        ((CrateItem) view.findViewById(R.id.crate_common)).Inflate(getContext(), crates[0], Player.commonCrateBasePrice, Player.commonCrateOpenCount);
        ((CrateItem) view.findViewById(R.id.crate_uncommon)).Inflate(getContext(), crates[1], Player.uncommonCrateBasePrice, Player.uncommonCrateOpenCount);
        ((CrateItem) view.findViewById(R.id.crate_rare)).Inflate(getContext(), crates[2], Player.rareCrateBasePrice, Player.rareCrateOpenCount);
        ((CrateItem) view.findViewById(R.id.crate_epic)).Inflate(getContext(), crates[3], Player.epicCrateBasePrice, Player.epicCrateOpenCount);
        ((CrateItem) view.findViewById(R.id.crate_legendary)).Inflate(getContext(), crates[4], Player.legendaryCrateBasePrice, Player.legendaryCrateOpenCount);
        ((CrateItem) view.findViewById(R.id.crate_mythic)).Inflate(getContext(), crates[5], Player.mythicCrateBasePrice, Player.mythicCrateOpenCount);
    }
}
