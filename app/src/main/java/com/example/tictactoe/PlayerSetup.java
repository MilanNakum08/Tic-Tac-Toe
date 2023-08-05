package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerSetup extends AppCompatActivity {
    private EditText player1;
    private EditText player2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);
        player1=findViewById(R.id.play1);
        player2=findViewById(R.id.play2);
    }
    public void openMain(View view){
        String player1name=player1.getText().toString();
        String player2name=player2.getText().toString();
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("PLAYER_NAMES",new String[] {player1name,player2name});
        startActivity(intent);
//        finish();
    }
}
