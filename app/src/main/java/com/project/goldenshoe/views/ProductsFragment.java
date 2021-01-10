package com.project.goldenshoe.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.project.goldenshoe.R;
import com.project.goldenshoe.adapters.ProductListAdapter;
import com.project.goldenshoe.databinding.FragmentProductsBinding;
import com.project.goldenshoe.models.Product;
import com.project.goldenshoe.viewmodels.ShopViewModel;

import java.util.List;

public class ProductsFragment extends Fragment implements ProductListAdapter.ProductInterface {


    //creating class variables
    private static final String TAG = "ProductsFragment";
    FragmentProductsBinding fragmentProductsBinding;
    private ProductListAdapter productListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductsBinding = fragmentProductsBinding.inflate(inflater, container, false);
        return fragmentProductsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        productListAdapter = new ProductListAdapter(this);
        fragmentProductsBinding.productsRecyclerView.setAdapter(productListAdapter);

        //Create a grid view, each product is placed within its own space
        fragmentProductsBinding.productsRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext()
                , DividerItemDecoration.VERTICAL));

        fragmentProductsBinding.productsRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext()
                , DividerItemDecoration.HORIZONTAL));


        //displays list of products on fragment
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productListAdapter.submitList(products);
            }
        });

        //navigating over to product details fragment when product is clicked
        navController = Navigation.findNavController(view);
    }

    /**
     * add product to cart when add to cart is clicked
     *
     * @param product
     */
    @Override
    public void addItem(Product product) {

        boolean isAdded = shopViewModel.addItemToCart(product);
        if(isAdded){
            Snackbar.make(requireView(), product.getName() + " added to cart.",
                    Snackbar.LENGTH_LONG).setAction("Checkout", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_productsFragment_to_cartFragment);
                }
            })
                    .show();
        }else{
            Snackbar.make(requireView(),   " Max amount has been added to cart.",
                    Snackbar.LENGTH_LONG).show();
        }


    }


    /**
     * brings user to the  products details when clicked
     *
     * @param product
     */
    @Override
    public void onItemClick(Product product) {
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_productsFragment_to_productsDetailFragment); //navigating over to the product's details
    }
}