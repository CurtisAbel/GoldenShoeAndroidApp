package com.project.goldenshoe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.project.goldenshoe.R;
import com.project.goldenshoe.models.CartItem;
import com.project.goldenshoe.viewmodels.ShopViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //Functionality variables
    private static final String TAG = "HomeActivity";
    NavController navController;
    ShopViewModel shopViewModel;

    //using this to set badge to 0, as cart start off at 0 when app is opened
    private int cartQuantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setting action bar to name. Changes the name to fragment we are currently in
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        //observing cart fragment from Home, returns live data to be observed
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                //quantity is always set to 0 when method is called
                int quantity = 0;
                //getting total number of items in cart and adding it to quantity
                for(CartItem cartitem : cartItems){
                    quantity+= cartitem.getQuantity();


                }
                cartQuantity = quantity;
                invalidateOptionsMenu();
            }
        });


    }

    //activates back button in action bar from fragment
    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    //navigation buttons and functions
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflating cart and help icon on navigation
        getMenuInflater().inflate(R.menu.home_menu, menu);



        MenuItem menuItem = menu.findItem(R.id.cartFragment);
        View actionView = menuItem.getActionView();

        TextView cartBadgeTextView = actionView.findViewById(R.id.cart_badge_text_view);

        //updating badge on cart icon when item is added
        cartBadgeTextView.setText(String.valueOf(cartQuantity));

        //if there are 0 items in the cart, then don't show bade on cart
        cartBadgeTextView.setVisibility(cartQuantity==0 ? View.GONE:View.VISIBLE);


        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onOptionsItemSelected(menuItem);
            }
        });



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