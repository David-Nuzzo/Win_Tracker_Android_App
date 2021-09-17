package com.example.wintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
        pos.setText(Integer.toString(playerAmount + 1));
        //pos.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        pos.setGravity(Gravity.CENTER);
        pos.setLayoutParams(new TableRow.LayoutParams(1));
        pos.setBackgroundColor(Color.WHITE);
        pos.setHeight(100);
        pos.setTextSize(14);
        tableRow.addView(pos);

        // Fill Name column.
        TextView name = new TextView(this);
        EditText nameBox = findViewById(R.id.PlayerNameBox);
        String newPlayerName = nameBox.getText().toString();
        name.setText(newPlayerName);
        name.setGravity(Gravity.CENTER);
        name.setLayoutParams(new TableRow.LayoutParams(2));
        name.setBackgroundColor(Color.WHITE);
        name.setHeight(100);
        name.setTextSize(14);
        tableRow.addView(name);

        // Fill Wins column
        TextView wins = new TextView(this);
        wins.setText("0");
        wins.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        wins.setGravity(Gravity.CENTER);
        wins.setLayoutParams(new TableRow.LayoutParams(3));
        wins.setBackgroundColor(Color.WHITE);
        wins.setHeight(100);
        wins.setTextSize(14);
        tableRow.addView(wins);

        // Fill Add Win Button Column (Make Invisible)
        Button addWinBtn = new Button(this);
        addWinBtn.setText("+");
        addWinBtn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        addWinBtn.setGravity(Gravity.CENTER);
        addWinBtn.setLayoutParams(new TableRow.LayoutParams(4));
        addWinBtn.setBackgroundColor(Color.WHITE);
        addWinBtn.setHeight(100);
        addWinBtn.setTextSize(16);
        tableRow.addView(addWinBtn);

        // Add row to table.
        table.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        // Set onClick listener for the row with action.
        tableRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AllTableElementsWhiteBg(table);

                // Highlighted the selected row blue & update the selectedPlayerPos.
                selectedPlayerPos = Integer.parseInt(pos.getText().toString());
                pos.setBackgroundResource(R.color.light_green);
                name.setBackgroundResource(R.color.light_green);
                wins.setBackgroundResource(R.color.light_green);
                addWinBtn.setBackgroundResource(R.color.light_green);
            }
        });

        // Set onClick listener for the plus button in each row.
        addWinBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AllTableElementsWhiteBg(table);
                // Highlighted the selected row blue & update the selectedPlayerPos.
                selectedPlayerPos = Integer.parseInt(pos.getText().toString());
                pos.setBackgroundResource(R.color.light_green);
                name.setBackgroundResource(R.color.light_green);
                wins.setBackgroundResource(R.color.light_green);
                addWinBtn.setBackgroundResource(R.color.light_green);

                int currentWins = Integer.parseInt(wins.getText().toString());
                wins.setText(Integer.toString(currentWins + 1));


                // Sort the table (Descending using Bubble Sort)
                /* This for loop loops through each row of the table. (Loop starts on 1 to miss out the header row)
                for(int i = 1; i < table.getChildCount() - 1; i++)
                {
                    // This for loop allows the selected row to be compared against all other rows of the table.
                    for(int j = i+1; j < table.getChildCount(); j++)
                    {
                        // Get the selected row.
                        TableRow iRow = (TableRow) table.getChildAt(i);
                        // Read the values of the wins from iRow.
                        TextView txtViewiRowWins = (TextView) iRow.getChildAt(2);
                        TextView txtViewiRowName = (TextView) iRow.getChildAt(1);
                        Log.i("I-Row's Name", txtViewiRowName.getText().toString());
                        Log.i("I-Row's Wins", txtViewiRowWins.getText().toString());

                        // Get the row below the selected row.
                        TableRow jRow = (TableRow) table.getChildAt(j);
                        TextView jRowName = (TextView) jRow.getChildAt(1);
                        TextView jRowWins = (TextView) jRow.getChildAt(2);
                        Log.i("J-Row's Name", jRowName.getText().toString());
                        Log.i("J-Row's Wins", jRowWins.getText().toString());

                        // Compare wins in both rows.
                        if(Integer.parseInt(txtViewiRowWins.getText().toString()) < Integer.parseInt(jRowWins.getText().toString()))
                        {
                            // Update pos number of iRow, add 1 to it.
                            Log.i("jRow has more wins", "I will swap the rows around.");
                            TextView iRowPos = (TextView) iRow.getChildAt(0);
                            int newiRowPosNum = Integer.parseInt(iRowPos.getText().toString()) + 1;
                            ((TextView) iRow.getChildAt(0)).setText(String.valueOf(newiRowPosNum));

                            // Update pos number of jRow, subtract 1 from it.
                            TextView jRowPos = (TextView) jRow.getChildAt(0);
                            int newjRowPosNum = Integer.parseInt(jRowPos.getText().toString()) - 1;
                            ((TextView) jRow.getChildAt(0)).setText(String.valueOf(newjRowPosNum));

                            // Remove the current rows.
                            table.removeViewAt(i);
                            table.removeViewAt((i));

                            // Add the new rows.
                            table.addView(jRow,i);
                            table.addView(iRow, (i + 1));
                        }
                    }
                }
                */

                // New bubble sort attempts
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    for(int j = 1; j < table.getChildCount() - 1; j++)
                    {
                        // Get the row A and row B.
                        TableRow RowA = (TableRow) table.getChildAt(j);
                        TextView RowAWins = (TextView) RowA.getChildAt(2);
                        //Log.i("Row A's Wins", RowAWins.getText().toString());

                        TableRow RowB = (TableRow) table.getChildAt(j+1);
                        TextView RowBWins = (TextView) RowB.getChildAt(2);
                        //Log.i("Row B's Wins", RowBWins.getText().toString());

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
                            // Remove the current rows.
                            table.removeViewAt(j);
                            table.removeViewAt(j);

                            // Add the new rows.
                            table.addView(RowB,j);
                            table.addView(RowA,(j + 1));
                        }
                    }
                }

            }
        });

        // Remove Name and and 1 to playerAmount.
        EditText PlayerNameBox = findViewById(R.id.PlayerNameBox);
        PlayerNameBox.setText("");
        playerAmount++;
    }

    // Methods

    public void AllTableElementsWhiteBg(TableLayout table)
    {
        // Make all the rows and columns of the table have a white background.
        // This loops through all table rows.
        int counter = 4;
        for (int i = 0; i < table.getChildCount(); i++) {
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
                    element.setBackgroundColor(Color.WHITE);
                }
            }
        }
        counter = 4;
    }

}


