package com.example.android_hw1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentNumber extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_number, container,false);

        final Bundle bundle = getArguments();

        if (bundle != null) {
            final String value = bundle.getString("value");
            final int color = bundle.getInt("color");
            TextView textView = view.findViewById(R.id.fragmentTwoNumber);
            textView.setText(value);
            textView.setTextColor(color);
        }

        return view;
    }

}
