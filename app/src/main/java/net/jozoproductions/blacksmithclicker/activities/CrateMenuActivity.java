package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.jozoproductions.blacksmithclicker.R;

public class CrateMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_crate_menu);
    }

    @Override
    public void onBackPressed() {
        //Prevent from closing it
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }
}
