package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer;
    boolean gameActive;
    int[] gameState = new int[9];
    int counter;

    static int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    
    private void startGame() {
        gameActive = true;
        activePlayer = 0; // x goes first
        counter = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
    }
    private void endGame(int position) {
        gameActive = false;
        Button playAgain= (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.VISIBLE);

        ImageView winLine = (ImageView) findViewById(R.id.winLine);
        winLine.setRotation(0);

        if (position == 0) {
            winLine.setImageResource(R.drawable.mark3);
            winLine.setRotation(90);
        } else if(position == 1) {
            winLine.setImageResource(R.drawable.mark4);
            winLine.setRotation(90);
        } else if(position == 2) {
            winLine.setImageResource(R.drawable.mark5);
            winLine.setRotation(90);
        } else if(position == 3) {
            winLine.setImageResource(R.drawable.mark3);
        } else if(position == 4) {
            winLine.setImageResource(R.drawable.mark4);
        } else if(position == 5) {
            winLine.setImageResource(R.drawable.mark5);
        } else if(position == 6) {
            winLine.setImageResource(R.drawable.mark1);
        } else if(position == 7) {
            winLine.setImageResource(R.drawable.mark2);
        }
    }

    private int checkWin() {
        for (int i = 0; i < 8; i++) {
            if (gameState[winPositions[i][0]] == gameState[winPositions[i][1]] &&
                    gameState[winPositions[i][1]] == gameState[winPositions[i][2]] &&
                    gameState[winPositions[i][0]] != 2) {

                //Todo: add mark the line of winning
                endGame(i);

                ImageView imgStatus = (ImageView) findViewById(R.id.imageStatus);

                if (gameState[winPositions[i][0]] == 0) {
                    imgStatus.setImageResource(R.drawable.xwin);
                    return 0;
                } else {
                    imgStatus.setImageResource(R.drawable.owin);
                    return 1;
                }
            }
        }
        return 2;
    }
    
    public void playerTap(View view) {
        ImageView img = (ImageView) view;

        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            return;
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
        if (counter > 2 && counter < 9) {
            checkWin();
        } else if (counter == 9 && checkWin() == 2) {
            ImageView imgStatus= (ImageView) findViewById(R.id.imageStatus);
            imgStatus.setImageResource(R.drawable.nowin);

            endGame(9);
        }
    }

    public void gameReset(View view) {
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

        Button playAgain= (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);

        ImageView winLine= (ImageView) findViewById(R.id.winLine);
        winLine.setImageResource(R.drawable.empty);

        startGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();
    }
}