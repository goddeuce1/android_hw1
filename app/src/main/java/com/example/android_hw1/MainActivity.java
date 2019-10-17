package com.example.android_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentList.EventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            final FragmentList fragmentList = new FragmentList();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentList, fragmentList)
                    .commit();
        }
    }

    @Override
    public void onItemClick(String number, int color) {
        final Bundle bundle = new Bundle();
        bundle.putString("value", number);
        bundle.putInt("color", color);

        final FragmentNumber fragmentNumber = new FragmentNumber();
        fragmentNumber.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentList, fragmentNumber)
                .addToBackStack(null)
                .commit();
    }
}
