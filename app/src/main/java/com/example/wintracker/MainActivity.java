package com.example.wintracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void New_Player_Button_OnClick(View view)
    {
        findViewById(R.id.PlayerNameBox).setVisibility(View.VISIBLE);
        findViewById(R.id.AddPlayerButton).setVisibility(View.VISIBLE);
    }

    public void Add_Player_Button_OnClick(View view)
    {
        // Hide Elements
        findViewById(R.id.PlayerNameBox).setVisibility(View.INVISIBLE);
        findViewById(R.id.AddPlayerButton).setVisibility(View.INVISIBLE);

        // Remove Name
        EditText PlayerNameBox = findViewById(R.id.PlayerNameBox);
        PlayerNameBox.setText("");
    }
}