

package com.example.vadim.caps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.*;
import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class CapsActivity extends AppCompatActivity
{
    private Game game;
    private String question;
    private Map <String, String> map = new TreeMap<String,String>();
    private String answer;

    CountryDB db = new CountryDB();
    List<String> capitals = db.getCapitals();
    int capitalsSize = capitals.size();

    private int score;
    private int qNum = 1;
    private  int index = 0;
    private int randIndex;
    private String randIndexString;
    private String model;
    private String key;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);
        game = new Game();

        model = game.qa();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            index++;
        }
        ask();
    }
    private void ask(){
        qNum++;

        ((TextView) findViewById(R.id.question)).setText(this.question);
        ((TextView) findViewById(R.id.qNum)).setText(String.format("%.0d", qNum));
    }
    public void onDone(View v)
    {
        question = model;
        ((TextView) findViewById(R.id.question)).setText(question);
        String inputtedAnswer= ((EditText)findViewById(R.id.answer)).getText().toString();
        String correctAnswer = this.answer;
        String s =((TextView)findViewById(R.id.question)).getText().toString()+"\n"+
                "Your Answer: "+ inputtedAnswer.toUpperCase()+"\n"+"Correct Answer: " + correctAnswer+ "\n" + "\n";
        s = s + "\n\n";
        ((TextView) findViewById(R.id.log)).setText(s);
        if (this.qNum==10)
        {
            ((TextView) findViewById(R.id.qNum)).setText("Game Over!");
            finish();
        }
        else if (this.key.equalsIgnoreCase(this.value))
        {

            score++;
            ((TextView) findViewById(R.id.score)).setText("Score= "+ String.format("%.0d", score));


            ((TextView) findViewById(R.id.qNum)).setText("Q# " + String.format("%.0d", qNum));
            ask();
        }
        else {
            ((TextView) findViewById(R.id.qNum)).setText("Game Over!");
            finish();
        }
    }

}
