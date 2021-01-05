package com.project.goldenshoe.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.goldenshoe.models.CartItem;
import com.project.goldenshoe.models.Product;

import java.util.*;

public class CartRepo {

    //observing mutable cart to get the list of items that have been added to cart
    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    /**
     * get items in cart
     *
     * @return
     */
    public LiveData<List<CartItem>> getCart() {

        if (mutableCart.getValue() == null) {
            initCart();
        }

        return mutableCart;
    }

    /**
     * initialising cart
     */
    public void initCart() {

        mutableCart.setValue(new ArrayList<CartItem>());

    }

    /**
     * method to check if item has been added to cart
     */
    public boolean addItemToCart(Product product) {

        if (mutableCart.getValue() == null) {
            initCart();

        }

        //returns number of items currently in cart
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return true;
    }

}
