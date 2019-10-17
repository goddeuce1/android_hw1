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
import java.util.List;

public class FragmentList extends Fragment {

    private EventListener eventListener;
    private List<Integer> numbers;

    public interface EventListener {
        void onItemClick(String number, int color);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eventListener = (EventListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        eventListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("array", (ArrayList<Integer>) numbers);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            numbers = savedInstanceState.getIntegerArrayList("array");
        } else {
            numbers = new ArrayList<>();

            for (int i = 0; i < getResources().getInteger(R.integer.MAX_VALUES); ++i) {
                numbers.add(i + 1);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_list, container, false);

        final int orientation = getResources().getConfiguration().orientation;
        RecyclerView recyclerView = view.findViewById(R.id.list);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.VERTICAL_COLUMNS)));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.HORIZONTAL_COLUMNS)));
        }

        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(numbers);
        recyclerView.setAdapter(recyclerAdapter);

        final Button btn = view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers.add(numbers.size() + 1);
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView singleValue;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            singleValue = itemView.findViewById(R.id.value);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(@NonNull View view) {
                    final TextView number = view.findViewById(R.id.value);
                    final String value = number.getText().toString();
                    final int color = number.getCurrentTextColor();
                    eventListener.onItemClick(value, color);
                }
            });
        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private final List<Integer> values;

        private RecyclerAdapter(@NonNull List<Integer> values) {
            this.values = values;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new RecyclerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.singleValue.setText(String.valueOf(values.get(position)));
            final int number = Integer.parseInt(holder.singleValue.getText().toString());

            if (number % 2 == 0) {
                holder.singleValue.setTextColor(Color.RED);
            } else {
                holder.singleValue.setTextColor(Color.BLUE);
            }

        }

        @Override
        public int getItemCount() {
            return values.size();
        }
    }
}
