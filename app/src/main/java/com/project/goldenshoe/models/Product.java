package com.project.goldenshoe.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class Product {
    //creating product properties
    private String id;
    private String name;
    private double price;
    private boolean isInStock;
    private String imageUrl;
    private String description;

    public Product(String id, String name, double price, boolean isInStock, String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isInStock = isInStock;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isInStock=" + isInStock +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                isInStock() == product.isInStock() &&
                getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getImageUrl().equals(product.getImageUrl()) &&
                getDescription().equals(product.getDescription());
    }
    

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    /**
     * Method that loads image from URL
     *
     * adapts to the image that is called, then displays that image
     */
    @BindingAdapter("android:productImage")
    public static void loadImage(ImageView imageView, String imageUrl){

        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);

    }
}
