package com.project.goldenshoe.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.goldenshoe.R;
import com.project.goldenshoe.databinding.FragmentProductsDetailBinding;
import com.project.goldenshoe.viewmodels.ShopViewModel;


public class ProductsDetailFragment extends Fragment {

    //using data binding to display a unique fragment for each product
    FragmentProductsDetailBinding fragmentProductsDetailBinding;
    ShopViewModel shopViewModel;

    public ProductsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductsDetailBinding = FragmentProductsDetailBinding.inflate(inflater, container, false);
        return fragmentProductsDetailBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //displaying details of products on fragment
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductsDetailBinding.setShopViewModel(shopViewModel);
    }
}