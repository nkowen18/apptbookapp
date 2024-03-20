package com.example.apptbookapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.example.apptbookapp.databinding.ActivityScrollingBinding;

import java.io.PrintWriter;
import java.io.StringWriter;

public class prettyActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView outputtext = (TextView) findViewById(R.id.prettyprintertext);
        Intent intent = getIntent();
        String output = intent.getStringExtra("apptbook");
        outputtext.setText(output);

    }
}