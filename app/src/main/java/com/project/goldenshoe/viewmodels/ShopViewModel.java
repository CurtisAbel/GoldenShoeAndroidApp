package com.project.goldenshoe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.goldenshoe.models.CartItem;
import com.project.goldenshoe.models.Product;
import com.project.goldenshoe.repositories.CartRepo;
import com.project.goldenshoe.repositories.ShopRepo;

import java.util.*;

/**
 * class enables interactions with products in shop
 */
public class ShopViewModel extends ViewModel {

    //initialising repositories for live data
    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();


    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();


    /**
     * method that gets a product and returns it as live data
     */
    public LiveData<List<Product>> getProducts() {

        return shopRepo.getProducts();
    }

    /**
     * When user clicks on recycler view product setProduct is called
     *
     * @param product
     */
    public void setProduct(Product product) {
        mutableProduct.setValue(product);


    }

    /**
     * returns live data
     * e.g. when user clicks on products it will bring up its details that the app currently has
     */
    public LiveData<Product> getProduct() {
        return mutableProduct;
    }

    /**
     * returns live data within cart
     * allows us to observe the cart from any fragment
     *
     * @return
     */
    public LiveData<List<CartItem>> getCart() {

        return cartRepo.getCart();

    }

    /**
     * Return true or false if item has been added to cart
     * adds item to cart
     */
    public boolean addItemToCart(Product product){
        return cartRepo.addItemToCart(product);

    }

    /**
     * method that return the item that need to be removed from cart
     * @param cartItem
     */
    public void removeItemFromCart(CartItem cartItem){
        cartRepo.removeItemFromCart(cartItem);


    }

    /**
     * method that return the quantity value that the item has been change to
     */
    public void changeQuantity(CartItem cartItem, int quantity){

        cartRepo.changeQuantity(cartItem, quantity);


    }

    /**
     * returning live data of the price of product to get total price
     * observed within cart fragment
     */
    public LiveData<Double> getTotalPrice(){


        return cartRepo.getTotalPrice();
    }

    /**
     * resetting cart when place order button has been placed
     */
    public  void resetCart(){
        cartRepo.initCart();


    }


}
