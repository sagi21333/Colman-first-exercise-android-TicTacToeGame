package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;

        int tappedImage = Integer.parseInt(img.getTag().toString());

        //Todo: add a button of asking the player if he want to play a new game - "play again"
        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[tappedImage] == 2) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            // Check which img to show his picture, o or x
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;

                ImageView imgStatus= (ImageView) findViewById(R.id.imageStatus);
                imgStatus.setImageResource(R.drawable.oplay);

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ImageView imgStatus= (ImageView) findViewById(R.id.imageStatus);
                imgStatus.setImageResource(R.drawable.xplay);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                String winnerStr;

                //Todo: add mark the line of winning
                gameActive = false;
                ImageView imgStatus= (ImageView) findViewById(R.id.imageStatus);

                if (gameState[winPosition[0]] == 0) {
                    imgStatus.setImageResource(R.drawable.xwin);
                } else {
                    imgStatus.setImageResource(R.drawable.owin);
                }
            }
        }

        if (counter == 9 && flag == 0) {
            ImageView imgStatus= (ImageView) findViewById(R.id.imageStatus);
            imgStatus.setImageResource(R.drawable.nowin);

        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        ImageView imgStatus= (ImageView) findViewById(R.id.imageStatus);
        imgStatus.setImageResource(R.drawable.xplay);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}