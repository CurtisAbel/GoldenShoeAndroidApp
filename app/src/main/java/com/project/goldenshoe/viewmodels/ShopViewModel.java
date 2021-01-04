package com.project.goldenshoe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.goldenshoe.models.Product;
import com.project.goldenshoe.repositories.ShopRepo;

import java.util.*;

/**
 *class enables interactions with products
 */
public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

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
     * returns live data e.g. when user clicks on products it will bring up its details that the app currently has
     */
    public LiveData<Product> getProduct(){
        return mutableProduct;
    }

}
