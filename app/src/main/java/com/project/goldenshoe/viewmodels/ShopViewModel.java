package com.project.goldenshoe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.goldenshoe.models.Product;
import com.project.goldenshoe.repositories.ShopRepo;

import java.util.*;

/*

 */
public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

    /**
     * method that gets a product and returns it as live data
     */
    public LiveData<List<Product>> getProducts() {

        return shopRepo.getProducts();
    }


}
