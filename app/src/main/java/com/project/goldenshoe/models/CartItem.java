package com.project.goldenshoe.models;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class CartItem {
    //creating variables of items that should be in cart
    private Product product;
    private int quantity;
    private int size;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return getQuantity() == cartItem.getQuantity() &&
                getSize() == cartItem.getSize() &&
                getProduct().equals(cartItem.getProduct());
    }



    /**
     * retrieving the value within quantity drop down menu
     * helps with checking the amount of one item a user would like to purchase
     */
    @BindingAdapter("android:setVal")
    public static void getSelectedSpinnerValue(Spinner spinner, int quantity){
        spinner.setSelection(quantity-1, true);


    }

    @BindingAdapter("android:setShoeSize")
    public static void getSelectedShoeSizeValue(Spinner spinner, int size){

        spinner.setSelection(size-1, true);
    }


    public static DiffUtil.ItemCallback<CartItem> itemCallback = new DiffUtil.ItemCallback<CartItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            //if the quantity is the same, the recycler row will not be updated
            //or else if the quantity is different, the recycler row will be updated
            return oldItem.getQuantity() == newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return oldItem.equals(newItem);
        }
    };


}
