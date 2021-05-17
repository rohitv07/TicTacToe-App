package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() , View.OnClickListener{

    lateinit var boards:Array<Array<Button>>
    lateinit var button1:Button
    lateinit var button2:Button
    lateinit var button3:Button
    lateinit var button4:Button
    lateinit var button5:Button
    lateinit var button6:Button
    lateinit var button7:Button
    lateinit var button8:Button
    lateinit var button9:Button
    lateinit var resetBtn:Button
    lateinit var display_txt:TextView

    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3){ IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 =  findViewById(R.id.button1)
        button2 =  findViewById(R.id.button2)
        button3 =  findViewById(R.id.button3)
        button4 =  findViewById(R.id.button4)
        button5 =  findViewById(R.id.button5)
        button6 =  findViewById(R.id.button6)
        button7 =  findViewById(R.id.button7)
        button8 =  findViewById(R.id.button8)
        button9 =  findViewById(R.id.button9)
        display_txt = findViewById(R.id.Display_txt)
        resetBtn = findViewById(R.id.ResetBtn)

        boards = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i in boards) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

            initializeBoardStatus()

            resetBtn.setOnClickListener{
                PLAYER = true
                TURN_COUNT = 0
                initializeBoardStatus()
            }

        }


    private fun initializeBoardStatus() {
        for(i:Int in 0..2){
            for(j:Int in 0..2){
                boardStatus[i][j] = -1
            }
        }

        for (i:Array<Button> in boards){
            for(button:Button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.button1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)

            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)

            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)

            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)

            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)

            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)

            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)

            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)

            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if(PLAYER){
            updateDisplay("Player X turn")
        }else{
            updateDisplay("Player O turn")
        }

        if(TURN_COUNT == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

   private fun checkWinner(){
        //Horizontal Rows
       for(i:Int in 0..2){
           if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
               if(boardStatus[i][0] == 1){
                   updateDisplay("Player X is Winner")
                   break
               }else if(boardStatus[i][0] == 0){
                   updateDisplay("Player O is Winner")
                   break
               }
           }
       }

       //Vertical Columns
       for(i:Int in 0..2){
           if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
               if(boardStatus[0][i] == 1){
                   updateDisplay("Player X is Winner")
                   break
               }else if(boardStatus[0][i] == 0){
                   updateDisplay("Player O is Winner")
                   break
               }
           }
       }

       //First Diagonal
       if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
           if(boardStatus[0][0] == 1){
               updateDisplay("Player X is Winner")
           }else if(boardStatus[0][0] == 0){
               updateDisplay("Player O is Winner")
           }
       }

       //Second Diagonal
       if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
           if(boardStatus[0][2] == 1){
               updateDisplay("Player X is Winner")
           }else if(boardStatus[0][2] == 0){
               updateDisplay("Player O is Winner")
           }
       }
    }

    private fun updateDisplay(text: String) {
           display_txt.text = text
        if (text.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton() {
        for (i:Array<Button> in boards) {
            for (button:Button in i) {
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0

        boards[row][col].apply {
            isEnabled = true
            setText(text)
        }

        boardStatus[row][col] = value
    }
}