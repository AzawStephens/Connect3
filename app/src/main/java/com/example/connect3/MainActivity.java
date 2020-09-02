package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2}; //2 represents each empty square on the grid
    int[][] PossibleWinningPositions ={{0,1,2},{3,4,5},{6,7,8}, {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    //These are the positions that are in gameState array. Look at the grid for reference.
    boolean gameActive = true;
    public void clicked(View view)
    {
        ImageView counter = (ImageView) view;
        TextView winnerText = findViewById(R.id.winnerTextView);
        int Pucktag = Integer.parseInt(counter.getTag().toString());

        if(gameState[Pucktag] == 2 && gameActive)
        {
            counter.setTranslationY(-1500f);
            gameState[Pucktag] = activePlayer;//This will replace the 2 in gamestate at the position of the tag with either a zero or a one.
            if(activePlayer == 0)
            {
                counter.setImageResource(R.drawable.redclear);
                activePlayer = 1;
            }else
            {
                counter.setImageResource(R.drawable.yellowclear);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500f).rotation(3000f).setDuration(1000);
            for(int[] PossibleWinningPosition : PossibleWinningPositions)//This will loop through each set in the possibleWinningPositions array with a iterator array
            {
                String message = "";
                if (gameState[PossibleWinningPosition[0]] == gameState[PossibleWinningPosition[1]] && gameState[PossibleWinningPosition[1]]== gameState[PossibleWinningPosition[2]]&& gameState[PossibleWinningPosition[0]]!=2 )
                {//It is searching gamestate at each position from the PossibleWinningPositions Array. For instance, one combination from Possible... array
                    // is 0,4,8 (elements 0,1,and 2 in the PossibleWinninngPositon iteration array)so this loop will search gamestate at THOSE positions [0,4,8] and if it finds the same number (either 0 or 1) in all three positions, then we'll know that a winner was found.
                    if(activePlayer == 0)
                    {
                        message = "YELLOW WINS!";
                    }else
                    {
                        message = "RED WINS";
                    }
                    Button resetButton = findViewById(R.id.button);
                    winnerText.setText(message);
                    winnerText.setVisibility(View.VISIBLE);
                    resetButton.setVisibility(View.VISIBLE);
                    gameActive = false;
                }
            }
        }
    }
    public void reset(View Rbutton)
    {
       Button resetButton = findViewById(R.id.button);
       TextView winnerText = findViewById(R.id.winnerTextView);
       winnerText.setVisibility(View.INVISIBLE);
       resetButton.setVisibility(View.INVISIBLE);
       GridLayout gridLay = findViewById(R.id.gridLayout);
     for(int i=0; i<gridLay.getChildCount(); i++)
     {
         ImageView counters = (ImageView) gridLay.getChildAt(i);
         counters.setImageDrawable(null);
     }
       for(int num = 0; num < gameState.length; num++)
        {
            gameState[num]=2;
        }
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}