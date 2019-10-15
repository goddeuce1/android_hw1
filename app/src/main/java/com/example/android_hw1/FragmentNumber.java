package com.example.android_hw1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentNumber extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_number, container,false);

        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            String value = savedInstanceState.getString(getString(R.string.VALUE));
            int color = savedInstanceState.getInt(getString(R.string.COLOR));
            TextView textView = (TextView)view.findViewById(R.id.fragmentTwoNumber);
            textView.setText(value);
            textView.setTextColor(color);
        }

        return view;
    }

}
