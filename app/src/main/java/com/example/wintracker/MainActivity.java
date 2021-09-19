package com.example.wintracker;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int playerAmount = 0;
    int selectedPlayerPos = 0;
    int tableRowHeight = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Complete setup.
        CreateEmptyLeaderboardTable(findViewById(R.id.table));
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

        AddNewPlayerToTable(findViewById(R.id.table));

        // Remove Name and and add 1 to playerAmount.
        EditText PlayerNameBox = findViewById(R.id.PlayerNameBox);
        PlayerNameBox.setText("");
        playerAmount++;
    }




    // Methods

    public void SetAllTableElementsDefaultStyle(TableLayout table)
    {
        // Make all the rows and columns of the table have a white background.
        // This loops through all table rows.
        int counter = 4;
        for (int i = 0; i < table.getChildCount(); i++)
        {
            TableRow row = (TableRow) table.getChildAt(i);
            // This will iterate through the current rows columns. (Thus all rows columns when used with the above loop.)
            for (int j = 0; j < row.getChildCount(); j++)
            {
                // Leave the header row untouched.
                if (counter >= 1)
                {
                    //TextView element = (TextView) row.getChildAt(j);
                    counter--;
                }
                else
                {
                    TextView element = (TextView) row.getChildAt(j);
                    element.setBackground(getResources().getDrawable(R.drawable.player_row_default));
                }
            }
        }
        counter = 4;
    }

    public void CreateEmptyLeaderboardTable(TableLayout table)
    {

        // Add a Table Header Row
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        tableRow.setWeightSum(100);
        tableRow.setClickable(false);

        // Define Pos header.
        TextView pos = new TextView(this);
        pos.setText("Pos");
        pos.setGravity(Gravity.CENTER);
        pos.setBackgroundColor(Color.BLUE);
        pos.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        pos.setTextColor(getResources().getColor(R.color.text));
        pos.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 15));
        pos.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(pos);

        // Define Name header.
        TextView name = new TextView(this);
        name.setText("Name");
        name.setGravity(Gravity.CENTER);
        name.setBackgroundColor(getResources().getColor(R.color.grey_darker));
        name.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        name.setTextColor(getResources().getColor(R.color.text));
        name.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 42));
        name.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(name);

        // Define Wins header
        TextView wins = new TextView(this);
        wins.setText("Wins");
        wins.setGravity(Gravity.CENTER);
        wins.setBackgroundColor(getResources().getColor(R.color.purple));
        wins.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        wins.setTextColor(getResources().getColor(R.color.text));
        wins.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 20));
        wins.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(wins);

        // Define Button header
        TextView plusBtn = new TextView(this);
        plusBtn.setText("+");
        plusBtn.setGravity(Gravity.CENTER);
        plusBtn.setBackgroundColor(getResources().getColor(R.color.gold));
        plusBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        plusBtn.setTextColor(getResources().getColor(R.color.text));
        plusBtn.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 23));
        plusBtn.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(plusBtn);

        // Add row to table.
        table.addView(tableRow);
    }

    public void AddNewPlayerToTable(TableLayout table)
    {
        // Add a new row to the table with the players data.
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        tableRow.setClickable(true);

        // Fill Pos column.
        TextView pos = new TextView(this);
        pos.setText(Integer.toString(playerAmount + 1));
        pos.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 15));
        pos.setGravity(Gravity.CENTER);
        pos.setBackgroundColor(getResources().getColor(R.color.black));
        pos.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        pos.setTextColor(getResources().getColor(R.color.text));
        pos.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(pos);

        // Fill Name column.
        TextView name = new TextView(this);
        EditText nameBox = findViewById(R.id.PlayerNameBox);
        String newPlayerName = nameBox.getText().toString();
        name.setText(newPlayerName);
        name.setGravity(Gravity.CENTER);
        name.setBackgroundColor(getResources().getColor(R.color.black));
        name.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        name.setTextColor(getResources().getColor(R.color.text));
        name.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 42));
        name.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(name);

        // Fill Wins column
        TextView wins = new TextView(this);
        wins.setText("0");
        wins.setGravity(Gravity.CENTER);
        wins.setBackgroundColor(getResources().getColor(R.color.black));
        wins.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        wins.setTextColor(getResources().getColor(R.color.text));
        wins.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 20));
        wins.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(wins);

        // Fill Add Win Button Column
        Button addWinBtn = new Button(this);
        addWinBtn.setText("+");
        addWinBtn.setGravity(Gravity.CENTER);
        addWinBtn.setBackgroundColor(getResources().getColor(R.color.black));
        addWinBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        addWinBtn.setTextColor(getResources().getColor(R.color.text));
        addWinBtn.setLayoutParams(new TableRow.LayoutParams(0, tableRowHeight, 23));
        addWinBtn.setBackground(getResources().getDrawable(R.drawable.player_row_default));
        tableRow.addView(addWinBtn);

        // Add row to table.
        table.addView(tableRow);

        // Set onClick listener for the row with action.
        tableRow.setOnClickListener(view ->
        {
            SetAllTableElementsDefaultStyle(table);

            // Highlighted the selected row & update the selectedPlayerPos.
            selectedPlayerPos = Integer.parseInt(pos.getText().toString());
            pos.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
            name.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
            wins.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
            addWinBtn.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
        });

        // Set onClick listener for the plus button in each row.
        addWinBtn.setOnClickListener(view ->
        {
            SetAllTableElementsDefaultStyle(table);
            // Highlighted the selected row (add the border again to keep it on) & update the selectedPlayerPos.
            selectedPlayerPos = Integer.parseInt(pos.getText().toString());
            pos.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
            name.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
            wins.setBackground(getResources().getDrawable(R.drawable.player_row_selected));
            addWinBtn.setBackground(getResources().getDrawable(R.drawable.player_row_selected));

            int currentWins = Integer.parseInt(wins.getText().toString());
            wins.setText(Integer.toString(currentWins + 1));


            // Sort the table (Descending using Bubble Sort)
            for(int i = 1; i < table.getChildCount(); i++)
            {
                for(int j = 2; j < table.getChildCount() - 1; j++)
                {
                    // Get the row A and row B.
                    TableRow RowA = (TableRow) table.getChildAt(j);
                    TextView RowAWins = (TextView) RowA.getChildAt(2);
                    Log.i("Row A's Wins", RowAWins.getText().toString());

                    TableRow RowB = (TableRow) table.getChildAt(j+1);
                    TextView RowBWins = (TextView) RowB.getChildAt(2);
                    Log.i("Row B's Wins", RowBWins.getText().toString());

                    // Compare row a and b wins
                    if(Integer.parseInt(RowAWins.getText().toString()) < Integer.parseInt(RowBWins.getText().toString()))
                    {
                        // Swap the rows position numbers.
                        TextView CurrentRowAPos = (TextView) RowA.getChildAt(0);
                        int newRowAPos = Integer.parseInt(CurrentRowAPos.getText().toString()) + 1;
                        ((TextView) RowA.getChildAt(0)).setText(String.valueOf(newRowAPos));

                        TextView CurrentRowBPos = (TextView) RowB.getChildAt(0);
                        int newRowBPos = Integer.parseInt(CurrentRowBPos.getText().toString()) - 1;
                        ((TextView) RowB.getChildAt(0)).setText(String.valueOf(newRowBPos));

                        // Swap the rows positions in the table.
                        Log.i("Swap Completed", "Yes");
                        table.removeViewAt(j);
                        table.removeViewAt(j);
                        // Add the new rows.
                        table.addView(RowB,j);
                        table.addView(RowA,(j + 1));
                    }
                }
            }
        });
    }
}


