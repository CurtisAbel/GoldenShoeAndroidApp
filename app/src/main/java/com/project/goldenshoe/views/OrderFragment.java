package com.project.goldenshoe.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.goldenshoe.R;
import com.project.goldenshoe.databinding.FragmentOrderBinding;
import com.project.goldenshoe.viewmodels.ShopViewModel;


public class OrderFragment extends Fragment {

    //creating order variables
    NavController navController;
    FragmentOrderBinding fragmentOrderBinding;
    ShopViewModel shopViewModel;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate/displays the layout for this fragment
        fragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container,false);
        return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);


        //when continueShoppingButton is clicked, navigate the user back to the the products fragment
        fragmentOrderBinding.continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //resetting cart
                shopViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_productsFragment);


            }
        });
    }
}