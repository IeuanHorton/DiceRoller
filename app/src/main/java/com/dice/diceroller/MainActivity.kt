package com.dice.diceroller


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import android.media.MediaPlayer


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ddlDice.setSelection(5);

        val mp = MediaPlayer.create(this, R.raw.dicerolling);

        var mute = false;

        btnRoll.setOnClickListener{
            //Grabs the string from the drop down list
            val dWhat : String = ddlDice.selectedItem as String;

            //Converts the text to a usesable number for the random number generator
            val diceMax = dWhat.substring(1).toInt() + 1;

            //Plays only if note muted
            if(!mute) {
                //Plays the dice rolling audio file
                mp?.start();
            }

            val roll = Random.nextInt(1, diceMax);

            var mod = 0;

            val rawMod = txtMod.text.toString();

            if(!rawMod.equals("")){
                mod = Integer.parseInt(rawMod);
            }

            //Changes the text to show the roll amount.
            lblAnswer.setText("Rolled a " + roll + "\nModify of: " + mod + "\nTotal: " + (roll + mod));
        }

        btnToggleMute.setOnClickListener{
            mute = !mute;
            if(mute){
                btnToggleMute.setImageResource(R.drawable.soundiconoff);
            }
            else{
                btnToggleMute.setImageResource(R.drawable.soundicon);
            }
        }
    }
}