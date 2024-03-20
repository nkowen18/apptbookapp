package com.example.apptbookapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.apptbookapp.databinding.ActivityHelpBinding;

public class help_activity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void goback(View view) {
        finishActivity(1);
    }
}