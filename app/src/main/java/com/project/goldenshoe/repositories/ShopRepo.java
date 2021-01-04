package com.project.goldenshoe.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.goldenshoe.models.Product;

import java.util.*;

public class ShopRepo {


    //Creating list of products of mutable live data
    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {

            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }

        return mutableProductList;
    }

    //getting list of products
    private void loadProducts() {


        List<Product> productList = new ArrayList<>();

        //adding list of products that golden shoe currently sell
        productList.add(new Product(UUID.randomUUID().toString(), "Men Black Hard Soles", 50.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/black_hard_sole_shoe.jpg?alt=media&token=5d7a567b-f28f-4d94-adb5-d28def576874"));


        productList.add(new Product(UUID.randomUUID().toString(), "Men black trainers", 30.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/black_men_trainer.jpg?alt=media&token=af52343c-be61-4609-ba2d-92a7b300cb8e"));

        productList.add(new Product(UUID.randomUUID().toString(), "Black Leather Heels", 34.99, false,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/black_smart_women_shoe.jpg?alt=media&token=8bf5e2c1-5deb-489a-a828-017207902bff"));

        productList.add(new Product(UUID.randomUUID().toString(), "Men Boat shoes", 12.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/men_boat_shoe.jpg?alt=media&token=4a086c03-61f4-4cfa-b5ca-30c3092ab1d7"));

        productList.add(new Product(UUID.randomUUID().toString(), "Men Chelsea Boots", 21.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/men_chelsea_boot.jpg?alt=media&token=02131664-81da-4635-bdf8-cc8c7c15d7dc"));

        productList.add(new Product(UUID.randomUUID().toString(), "Men Hiking Sandals ", 15.99, false,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/men_hiking_scandal.jpg?alt=media&token=0bd251ee-dc8a-445c-86b4-53ca4402af4e"));

        productList.add(new Product(UUID.randomUUID().toString(), "Men Stylish Trainers  ", 48.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/men_stylish_trainer.jpg?alt=media&token=b4be4fea-1185-4802-96c0-777f9a924b8c"));

        productList.add(new Product(UUID.randomUUID().toString(), "Navy Trainers  ", 25.99, false,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/trainer_shoe.jpg?alt=media&token=61d45244-b630-4488-8d9d-40a4c757c54d"));

        productList.add(new Product(UUID.randomUUID().toString(), "Women White Trainers  ", 32.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/white_women_shoe.jpg?alt=media&token=e8512776-dacf-44aa-81bd-2cdabfdbc432"));

        productList.add(new Product(UUID.randomUUID().toString(), "Women High Heels  ", 51.99, true,
                "https://firebasestorage.googleapis.com/v0/b/golden-shoe-android-app-dd197.appspot.com/o/women_high_heel_shoe.jpg?alt=media&token=088680c0-c237-45d9-a7d9-32d6c81a6139"));


        mutableProductList.setValue(productList);


    }

}
