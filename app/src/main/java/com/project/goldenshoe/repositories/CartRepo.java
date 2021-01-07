package com.project.goldenshoe.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.goldenshoe.models.CartItem;
import com.project.goldenshoe.models.Product;
import com.project.goldenshoe.views.ProductsFragment;

import java.util.*;

import java.text.DecimalFormat;

/**
 * functionality of cart
 * delete, add, calculate total price and change quantity to cart
 */
public class CartRepo {

    //observing mutable cart to get the list of items that have been added to cart
    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

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
     * initialising new empty cart
     */
    public void initCart() {

        //creating a new empty cart
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    /**
     * method to check if item has been added to cart
     */
    public boolean addItemToCart(Product product) {

        //initialising empty cart
        if (mutableCart.getValue() == null) {
            initCart();

        }

        //adding items to cart
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    /**
     * removing item from cart
     */
    public void removeItemFromCart(CartItem cartItem) {
        if (mutableCart.getValue() == null) {
            return;
        }
        //removing item from cart
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();


    }

    /**
     * value of quantity spinner being changed
     * if there is no value just return
     * get current list of items in cart
     *
     * @param cartItem
     * @param quantity
     */
    public void changeQuantity(CartItem cartItem, int quantity) {
        if (mutableCart.getValue() == null) return;


        //get current list of items in cart
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        //creating a updated item and updating it in list
        CartItem updatedItem = new CartItem(cartItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem), updatedItem);

        mutableCart.setValue(cartItemList);
        calculateCartTotal();


    }

    /**
     * calculates total value of cart
     */

    private void calculateCartTotal() {

        if (mutableCart.getValue() == null) return;

        double total = 0.0;
        List<CartItem> cartItemList = mutableCart.getValue();
        //iterating through list to check the amount of product, their price and quantity and add them together
        for (CartItem cartItem : cartItemList) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        //total price of cart
        //observing total price with getTotalPrice method, which notifies when price has been updated
        DecimalFormat decimalFormat = new DecimalFormat("###.##");

        mutableTotalPrice.setValue(Double.valueOf(decimalFormat.format(total)));
    }


    /**
     * getting live data of total price in cart
     * notifies updated price of the cart using live data
     *
     * @return
     */
    public LiveData<Double> getTotalPrice() {
        if (mutableTotalPrice.getValue() == null) {
            mutableTotalPrice.setValue(0.0);

        }
        return mutableTotalPrice;


    }
}
