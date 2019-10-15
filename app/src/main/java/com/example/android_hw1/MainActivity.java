package com.example.android_hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentList.EventListener {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentList fragmentList = new FragmentList();

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentList, fragmentList);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onItemClick(String number, int color) {
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.VALUE), number);
        bundle.putInt(getString(R.string.COLOR), color);

        FragmentNumber fragmentNumber = new FragmentNumber();
        fragmentNumber.setArguments(bundle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentList, fragmentNumber);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
