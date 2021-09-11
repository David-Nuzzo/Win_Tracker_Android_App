package com.example.wintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int playerAmount = 1;
    int selectedPlayerPos = 0;

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

        // Add a new row to the table with the players data.
        TableLayout table = findViewById(R.id.Table);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.setClickable(true);


        // Fill Pos column.
        TextView pos = new TextView(this);
        pos.setText(Integer.toString(playerAmount));
        pos.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        pos.setGravity(Gravity.CENTER);
        pos.setLayoutParams(new TableRow.LayoutParams(1));
        pos.setBackgroundColor(Color.WHITE);
        tableRow.addView(pos);

        // Fill Name column.
        TextView name = new TextView(this);
        EditText nameBox = (EditText) findViewById(R.id.PlayerNameBox);
        String newPlayerName = (String) nameBox.getText().toString();
        name.setText(newPlayerName);
        name.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        name.setGravity(Gravity.CENTER);
        name.setLayoutParams(new TableRow.LayoutParams(2));
        name.setBackgroundColor(Color.WHITE);
        tableRow.addView(name);

        // Fill Wins column
        TextView wins = new TextView(this);
        wins.setText("0");
        wins.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        wins.setGravity(Gravity.CENTER);
        wins.setLayoutParams(new TableRow.LayoutParams(2));
        wins.setBackgroundColor(Color.WHITE);
        tableRow.addView(wins);

        // Add row to table.
        table.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        // Set onclick listener for the row with action.
        tableRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Make all the rows and columns of the table have a white background.
                // This loops through all table rows.
                for(int i = 0; i < table.getChildCount(); i++)
                {
                    TableRow row = (TableRow) table.getChildAt(i);
                    // This will iterate through the current rows columns. (Thus all rows columns when used with the above loop.)
                    for(int j = 0; j < row.getChildCount(); j++)
                    {
                        TextView btn = (TextView) row.getChildAt(j);
                        btn.setBackgroundColor(Color.WHITE);
                    }
                }

                // Highlighted the selected rows elements.
                // Update the selectedPlayerPos.
                selectedPlayerPos = Integer.parseInt(pos.getText().toString());
                pos.setBackgroundColor(Color.BLUE);
                name.setBackgroundColor(Color.BLUE);
                wins.setBackgroundColor(Color.BLUE);

            }
        });

        // Remove Name and and 1 to playerAmount.
        EditText PlayerNameBox = findViewById(R.id.PlayerNameBox);
        PlayerNameBox.setText("");
        playerAmount ++;
    }
}