package com.example.app27.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app27.R;
import com.example.app27.databinding.FragmentCartBinding;
import com.example.app27.databinding.FragmentHomeBinding;
import com.example.app27.ui.home.HomeViewModel;

public class CartFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return FragmentCartBinding.inflate(inflater, container, false).getRoot();
    }

}