package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive= true;
    int playerActive = 0; //0=X,1=O
    int [] gameState = {2,2,2,2,2,2,2,2,2};  //2=null
    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}}   ;


    public void tap(View view){
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());

        if(!gameActive){
            gameReset(view);
        }

        if(gameState[tappedImg]==2){
            gameState[tappedImg] = playerActive;
            img.setTranslationY(-1000f);
            if(playerActive==0){
                img.setImageResource(R.drawable.x);
                playerActive=1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            }
            else {
                img.setImageResource(R.drawable.o);
                playerActive=0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for (int [] winPosition: winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=2){
                String winnerStr;
                gameActive=false;
                if(gameState[winPosition[0]]==0){
                    winnerStr="X has won";
                }
                else{
                    winnerStr="0 has won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }

    public void gameReset(View view){
        gameActive=true;
        playerActive=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.image1)).setImageResource(0);
        ((ImageView)findViewById(R.id.image2)).setImageResource(0);
        ((ImageView)findViewById(R.id.image3)).setImageResource(0);
        ((ImageView)findViewById(R.id.image4)).setImageResource(0);
        ((ImageView)findViewById(R.id.image5)).setImageResource(0);
        ((ImageView)findViewById(R.id.image6)).setImageResource(0);
        ((ImageView)findViewById(R.id.image7)).setImageResource(0);
        ((ImageView)findViewById(R.id.image8)).setImageResource(0);
        ((ImageView)findViewById(R.id.image9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}