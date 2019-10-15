package com.example.android_hw1;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentList extends Fragment {

    private EventListener eventListener;
    private ArrayList<Integer> mNumbers;

    public interface EventListener {
        void onItemClick(String number, int color);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        eventListener = (EventListener)context;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(getString(R.string.ARRAY_KEY), mNumbers);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mNumbers = savedInstanceState.getIntegerArrayList(getString(R.string.ARRAY_KEY));
        } else {
            mNumbers = new ArrayList<>();

            for (int i = 0; i < getResources().getInteger(R.integer.MAX_VALUES); ++i) {
                mNumbers.add(i + 1);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_list, container, false);

        int orientation = getResources().getConfiguration().orientation;
        RecyclerView recyclerView = view.findViewById(R.id.list);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.VERT_COLUMNS)));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.HORIZ_COLUMNS)));
        }

        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mNumbers);
        recyclerView.setAdapter(recyclerAdapter);

        Button btn = view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumbers.add(mNumbers.size() + 1);
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mSingleValue;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mSingleValue = itemView.findViewById(R.id.value);
        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private ArrayList<Integer> mValues;

        private RecyclerAdapter(ArrayList<Integer> values) {
            mValues = values;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new RecyclerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.mSingleValue.setText(String.valueOf(mValues.get(position)));
            int number = Integer.parseInt(holder.mSingleValue.getText().toString());

            if (number % 2 == 0) {
                holder.mSingleValue.setTextColor(Color.RED);
            } else {
                holder.mSingleValue.setTextColor(Color.BLUE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView number = view.findViewById(R.id.value);
                    String value = number.getText().toString();
                    int color = number.getCurrentTextColor();
                    eventListener.onItemClick(value, color);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
