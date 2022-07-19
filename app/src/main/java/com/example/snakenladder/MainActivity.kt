package com.example.snakenladder

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

var snake = intArrayOf(0)
var ladder = intArrayOf(0)

var currentPos = 1
var droll: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        genSnakeandLadder()
        board()

//        for(ele in snake){
//            println(ele)
//        }  // ye debug karne ke liye

        var rollButton: Button = findViewById(R.id.dice_roll)
        var butt:Button = findViewById(R.id.ij1)
        butt.setBackgroundColor(-256)
        var resetButton :Button = findViewById(R.id.reset)
        resetButton.setOnClickListener {
            currentPos = 1
            genSnakeandLadder()
            board()
            butt.setBackgroundColor(-256)
            var text = findViewById<TextView>(R.id.testText)
            text.text = currentPos.toString()
        }


        rollButton.setOnClickListener {
            droll = (1..6).random()
            rollDice()
            runGame()
        }




    }

    private fun genSnake() {
        var snakelist: MutableList<Int> = mutableListOf<Int>()
        var n = 0
        while(n <6) {
            var temp = (1..35).random()
            if(snakelist.contains(temp)){
                continue
            }
            else {
                snakelist.add(temp)
                n++
            }
        }
        snake = snakelist.toIntArray()
        snake.sort()
    }
    private fun genSnakeandLadder() {
        genSnake()
        var ladderlist: MutableList<Int> = mutableListOf<Int>()
        var n = 0
        while(n <6) {
            var temp = (1..36).random()
            if(ladderlist.contains(temp) || snake.contains(temp)){
                continue
            }
            else {
                ladderlist.add(temp)
                n++
            }
        }
        ladder = ladderlist.toIntArray()
        ladder.sort()
    }


    private fun runGame(){

                board()
                if(currentPos + droll <= 36){
                    currentPos += droll
                }


                var text = findViewById<TextView>(R.id.testText)

                if(snake.contains(currentPos) && snake.indexOf(currentPos) != 0){

                    currentPos = snake[snake.indexOf(currentPos) - 1 ]
                    print(currentPos) // used for debugging
                }
                if(ladder.contains(currentPos) && ladder.indexOf(currentPos) != ladder.size - 1){

                    currentPos = ladder[ladder.indexOf(currentPos) + 1 ]
                    print(currentPos)
                }

                text.text = currentPos.toString()

                if(currentPos == 36){
                    text.text = "YOU WON"

                }

                val res: Resources = resources
                var id: Int = res.getIdentifier("ij$currentPos", "id", packageName)
                val button: Button = findViewById(id) // current position ko dhoondh rahe hai
                button.setBackgroundColor(-256) // changing to yellow

    }

    private fun board () {
        var i=1
        while (i <=36) {
            val res: Resources = resources
            val id: Int = res.getIdentifier("ij$i", "id", packageName)


            val button: Button = findViewById(id)

            button.setBackgroundColor(-3355444)

            if(snake.contains(i)){
                button.setBackgroundColor(-65536)
            }
            if(ladder.contains(i)) {
                button.setBackgroundColor(-16711936)
            }
            i++
        }

    }






    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {


        val diceRoll = droll

        /*
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()
        // This was for converting the returned value to a string as TextView requires a text, no?
        */

        //The above two lines of code was for when we displayed the number that we rolled
        // For example if the diceRoll returned 5 it would show 5 only.



        // Finding the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)


        val dicePic = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the corresponding dice image
        diceImage.setImageResource(dicePic)


        diceImage.contentDescription = diceRoll.toString()
    }

}

