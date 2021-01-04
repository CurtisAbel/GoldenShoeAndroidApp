package com.project.goldenshoe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.project.goldenshoe.R;

public class HomeActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setting nav to name. Changes the name to fragment we are currently in
        navController = Navigation.findNavController(this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    //activates back button in action bar from fragment
    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    //displays cart in actionBar of app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    /**
     * if a item is clicked, the app will go to that fragment e.g.
     * if the cart icon is clicked, the app will display the cart fragment
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       return NavigationUI.onNavDestinationSelected(item, navController) ||
        super.onOptionsItemSelected(item);
    }
}