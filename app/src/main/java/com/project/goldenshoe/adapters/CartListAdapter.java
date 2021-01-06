package com.project.goldenshoe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.project.goldenshoe.databinding.CartRowBinding;
import com.project.goldenshoe.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartVH> {

private CartInterface cartInterface;

    public CartListAdapter(CartInterface cartInterface) {

        super(CartItem.itemCallback);
        this.cartInterface=cartInterface;
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater, parent, false);
        return new CartVH(cartRowBinding);
    }



    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();


    }

    /**
     * using data binding to use live data on what is called
     * e.g.if an item is added to cart, this item will be displayed on cart recycler view
     * or delete a live item when the delete button is clicked
     */

    class CartVH extends RecyclerView.ViewHolder{

        CartRowBinding cartRowBinding;

        public CartVH(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding=cartRowBinding;

            cartRowBinding.deleteProductButton.setOnClickListener(v -> cartInterface.deleteItem(getItem(getAdapterPosition())));
        }
    }
    public interface CartInterface{
        void deleteItem(CartItem cartItem);
    }

}
