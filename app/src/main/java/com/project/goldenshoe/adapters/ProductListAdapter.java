package com.project.goldenshoe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.project.goldenshoe.databinding.ShopRowBinding;
import com.project.goldenshoe.models.Product;

public class ProductListAdapter extends ListAdapter<Product, ProductListAdapter.ProductViewHolder> {

    ProductInterface productInterface;
    public ProductListAdapter(ProductInterface productInterface) {
        super(Product.itemCallback);

        this.productInterface = productInterface;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShopRowBinding shopRowBinding = ShopRowBinding.inflate(layoutInflater,parent,false);
        shopRowBinding.setProductInterface(productInterface); //setting interface within shop_row
        return new ProductViewHolder(shopRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);
        holder.shopRowBinding.setProduct(product);

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ShopRowBinding shopRowBinding;

        public ProductViewHolder(ShopRowBinding binding) {

            super(binding.getRoot());
            this.shopRowBinding = binding;

        }
    }

    /**
     * handling events of adding item to cart or going to the product detail fragment
     */
    public interface ProductInterface{
        void addItem(Product product);
        void onItemClick(Product product);
    }

}
