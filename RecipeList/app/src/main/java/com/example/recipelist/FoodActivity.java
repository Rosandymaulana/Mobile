package com.example.recipelist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipelist.model.RecipeObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodActivity extends AppCompatActivity implements AddRecipeDialogFragment.AddRecipeDialogListener {
    private FoodAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.food_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FoodAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mAdapter.removeFoodAt(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddRecipeDialog();
            }
        });

    }

    public void showAddRecipeDialog() {
        DialogFragment dialog = new AddRecipeDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddRecipeDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(AddRecipeDialogFragment dialog, @NonNull String name, String ingred) {
        mAdapter.addFood(new RecipeObject(name, ingred));
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(AddRecipeDialogFragment dialog) {
        dialog.dismiss();
    }

}
