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
                // This for loop loops through each row of the table. (Loop starts on 1 to miss out the header row and ends 1 early as the last row is already compared)
                for(int row = 1; row < table.getChildCount() - 1; row++)
                {
                    // This for loop, loops through each column (of each row with application of the previous for loop.)
                    // This for loop has been restricted to loop through only the pos, name and wins columns of each row.
                    for(int rowColumn = 0; rowColumn < tableRow.getChildCount() - 1; rowColumn++)
                    {
                        // Get i and i+1 and assign them to temp tablerows.
                        TableRow iRow     = (TableRow) table.getChildAt(row);
                        TableRow iAdd1Row = (TableRow) table.getChildAt(row+1);

                        // Read the values of the wins from iRow and iAdd1Row.
                        TextView txtView_I_Wins = (TextView) iRow.getChildAt(2);
                        //Log.i("iRows Content Wins", txtView_I_Wins.getText().toString());

                        TextView txtView_I_Add1Wins = (TextView) iAdd1Row.getChildAt(2);
                        //Log.i("iAdd1Rows Content Wins", txtView_I_Add1Wins.getText().toString());

                        if(Integer.parseInt(txtView_I_Wins.getText().toString()) < Integer.parseInt(txtView_I_Add1Wins.getText().toString()))
                        {
                            // Get the current pos number of iRow and add 1 to it.
                            Log.i("iAdd1Rows has more wins", "I will swap the rows around.");
                            TextView tempPos = (TextView) iRow.getChildAt(0);
                            int newPosNumber = Integer.parseInt(tempPos.getText().toString()) + 1;
                            tempPos.setText(String.valueOf(newPosNumber));
                            ((TextView) iRow.getChildAt(0)).setText(tempPos.getText().toString());

                            // Get the current pos number of iAdd1Row and subtract 1 from it.
                            Log.i("iAdd1Rows has more wins", "I will swap the rows around.");
                            TextView tempPos2 = (TextView) iAdd1Row.getChildAt(0);
                            int newPosNumber2 = Integer.parseInt(tempPos2.getText().toString()) - 1;
                            tempPos2.setText(String.valueOf(newPosNumber2));
                            ((TextView) iAdd1Row.getChildAt(0)).setText(tempPos2.getText().toString());

                            // Remove the current rows.
                            table.removeViewAt(row);
                            table.removeViewAt((row));

                            // Add the new rows.
                            table.addView(iAdd1Row,row);
                            table.addView(iRow, (row +1));

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


