package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.crates.CrateItem;
import net.jozoproductions.blacksmithclicker.fragments.CrateListFragment;
import net.jozoproductions.blacksmithclicker.fragments.CrateOpenFragment;

public class CrateMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_crate_menu);
    }

    @Override
    public void onBackPressed() {
        //Prevent from accidental closing
        Fragment curFragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment);

        if (curFragment instanceof CrateListFragment) {
            finish();
        } else if (curFragment instanceof CrateOpenFragment) {
            if (!((CrateOpenFragment) curFragment).openingCrateThread.isAlive())
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CrateListFragment()).commit();
        }
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }
}
