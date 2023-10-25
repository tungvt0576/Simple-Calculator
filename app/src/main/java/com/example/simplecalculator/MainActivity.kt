package com.example.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var inputOutput: TextView
    private var firstOperand: Double = 0.0
    private var operator: String? = null
    private var secondOperand: Double = 0.0
    private var isResultDisplayed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputOutput = findViewById(R.id.input_output)

        val numberButtons = arrayOf(
            findViewById<Button>(R.id.button_0),
            findViewById(R.id.button_1),
            findViewById(R.id.button_2),
            findViewById(R.id.button_3),
            findViewById(R.id.button_4),
            findViewById(R.id.button_5),
            findViewById(R.id.button_6),
            findViewById(R.id.button_7),
            findViewById(R.id.button_8),
            findViewById(R.id.button_9),
            findViewById(R.id.button_dot)
        )

        val operatorButtons = arrayOf(
            findViewById<Button>(R.id.plus_button),
            findViewById(R.id.minus_button),
            findViewById(R.id.multiply_button),
            findViewById(R.id.divide_button)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberClick(button) }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener { onOperatorClick(button) }
        }

        val clearEntryButton = findViewById<Button>(R.id.clear_entry)
        val clearButton = findViewById<Button>(R.id.clear)
        val backspaceButton = findViewById<Button>(R.id.back_space)
        val equalButton = findViewById<Button>(R.id.equal_button)

        clearEntryButton.setOnClickListener { onClearEntryClick() }
        clearButton.setOnClickListener { onClearClick() }
        backspaceButton.setOnClickListener { onBackspaceClick() }
        equalButton.setOnClickListener { onEqualClick() }
    }

    private fun onNumberClick(button: View) {
        if (isResultDisplayed) {
            inputOutput.text = ""
            isResultDisplayed = false
        }

        if(inputOutput.text == "0") inputOutput.text = ""
        val number = (button as Button).text.toString()
        val currentText = inputOutput.text.toString()
        inputOutput.text = currentText + number
    }

    private fun onOperatorClick(button: View) {
        if (isResultDisplayed) {
            isResultDisplayed = false
        }

        operator = (button as Button).text.toString()
        firstOperand = inputOutput.text.toString().toDouble()
        inputOutput.text = ""
    }

    private fun onClearEntryClick() {
        inputOutput.text = "0"
    }

    private fun onClearClick() {
        inputOutput.text = "0"
        firstOperand = 0.0
        operator = null
        secondOperand = 0.0
        isResultDisplayed = false
    }

    private fun onBackspaceClick() {
        val currentText = inputOutput.text.toString()
        if (currentText.length > 1) {
            inputOutput.text = currentText.substring(0, currentText.length - 1)
        } else {
            inputOutput.text = "0"
        }
    }

    private fun onEqualClick() {
        if (operator != null) {
            val currentText = inputOutput.text.toString()
            secondOperand = currentText.toDouble()
            val result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> {
                    if (secondOperand != 0.0) {
                        firstOperand / secondOperand
                    } else {
                        inputOutput.text = "Error"
                        return
                    }
                }
                else -> {
                    inputOutput.text = "Error"
                    return
                }
            }
            inputOutput.text = result.toString()
            firstOperand = result
            operator = null
            isResultDisplayed = true
        }
    }
}