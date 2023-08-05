package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0==X
    //1==O
    //2==NULL
    boolean gameActive =true;
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6 }};
    int cC=0;
    String s1,s2;
    public void PlayTap(View view){
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        cC+=1;
        if(gameState[tappedImage]==2 && gameActive){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.xsymbol);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText(s2+"'s Turn");
            }
            else{
                img.setImageResource(R.drawable.osymol);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText(s1+"'s Turn");
            }
        }
        img.animate().translationYBy(1000f).setDuration(300);
        int ff=0;
        for(int[] winPosition: winPositions ){

            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[1]] !=2){
                String winStr;
                gameActive=false;
                if(gameState[winPosition[0]]==0){
                    winStr=s1+" has Won !!";
                    showAlertDialog(s1,view);
                }
                else{
                    winStr=s2+" has Won !!";
                    showAlertDialog(s2,view);
                }
                TextView status=findViewById(R.id.status);
                status.setText(winStr);
                ff=1;
            }
        if(cC>=9 && ff==0){
            TextView status=findViewById(R.id.status);
            status.setText("Draw!");
            showAlertDialogDraw(view);
            gameReset(view);
            gameActive=false;
        }

        }
    }
    private void showAlertDialog(String winnerName,View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Winner")
                .setMessage("Congratulations, " + winnerName + " wins!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset(view);
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showAlertDialogDraw(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tie")
                .setMessage("Better Luck Next Time! ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset(view);
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        cC=0;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status=findViewById(R.id.status);
        status.setText(s1+"'s Turn");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            String[] stringArray = bundle.getStringArray("PLAYER_NAMES");

            TextView TextView1 = findViewById(R.id.textView5);
            TextView TextView2 = findViewById(R.id.textView6);
            if (stringArray != null && stringArray.length >= 2) {
                TextView1.setText(stringArray[0]);
                TextView2.setText(stringArray[1]);
                s1=stringArray[0];
                s2=stringArray[1];
            }
        }
        if(s1.length()==0){
            s1="Alice";
            TextView TextView1 = findViewById(R.id.textView5);
            TextView1.setText(s1);
        }
        if(s2.length()==0){
            s2="Bob";
            TextView TextView2 = findViewById(R.id.textView6);
            TextView2.setText(s2);
        }
            TextView status=findViewById(R.id.status);
            status.setText(s1+"'s Turn");
    }
}