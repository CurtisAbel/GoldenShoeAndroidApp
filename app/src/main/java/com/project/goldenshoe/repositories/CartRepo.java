package com.project.goldenshoe.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.goldenshoe.models.CartItem;
import com.project.goldenshoe.models.Product;
import com.project.goldenshoe.views.ProductsFragment;

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
        for (CartItem cartItem : cartItemList) {
            //checking to see if the product being added is identical with other products that have been added to the cart
            if (cartItem.getProduct().getId().equals(product.getId())) {
                if (cartItem.getQuantity() == 5) {
                    //no more identical items can be added, as 5 in the max same items we can add
                    return false;
                }

                //if max quantity has not been met, then increase quantity of similar item by 1
                //return true, as item has successfully been added to the cart
                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);

                return true;

            }

        }

        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return true;
    }

}
