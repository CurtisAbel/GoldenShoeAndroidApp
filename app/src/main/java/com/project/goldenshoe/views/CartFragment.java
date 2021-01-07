package com.project.goldenshoe.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.goldenshoe.R;
import com.project.goldenshoe.adapters.CartListAdapter;
import com.project.goldenshoe.databinding.FragmentCartBinding;
import com.project.goldenshoe.models.CartItem;
import com.project.goldenshoe.viewmodels.ShopViewModel;

import java.util.List;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    //setting up cart variables
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container,false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        //separating the items in cart, responsible for how the items will be displayed in the cart
        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));


        //manipulating items in recycler View of cart fragment e.g. display or remove item
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);

                //if there are no items in the cart, the place order button is disabled
                //only activated when an item is attack, hence size needs to be bigger than 0
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size()>0);
            }
        });

        //setting total price in cart fragment to change to the price of the total price of items in cart
        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                //setting total price in cart fragment change to
                fragmentCartBinding.orderTotalTextView.setText("Total: £ " + aDouble.toString());

            }
        });

        //bringing user to another activity when place order button is clicked
        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
            }
        });

    }

    /**
     * deleting item from cart
     * @param cartItem
     */
    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);


    }

    /**
     * changes the price of an item if the quantity is changed
     * e.g. if an item is worth 1 and we change the quantity to 2, the item will then be worth 2.
     * @param cartItem
     * @param quantity
     */
    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {

shopViewModel.changeQuantity(cartItem,quantity);

    }
}