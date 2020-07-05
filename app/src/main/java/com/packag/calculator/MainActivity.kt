package com.packag.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private enum class OPERATOR {

        PLUS, SUBSTRACT, MULTIPLY, DIVIDE, EQUAL

    }

    //instance variables
    private var currentNumber: String? = null
    private var currentOperator: OPERATOR? = null
    private var stringNumberAtLeft: String? = null
    private var stringNumberAtRight: String? = null
    private var calculationString: String? = null
    private var calculationResult: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber.isEnabled = false
        edtNumber.inputType = InputType.TYPE_NULL

        currentNumber = ""
        calculationString = ""
    }

    fun buttonTapped (view: View) {

        when (view.id){


            R.id.btn0 -> numberIsTapped(0)
            R.id.btn1 -> numberIsTapped(1)
            R.id.btn2 -> numberIsTapped(2)
            R.id.btn3 -> numberIsTapped(3)
            R.id.btn4 -> numberIsTapped(4)
            R.id.btn5 -> numberIsTapped(5)
            R.id.btn6 -> numberIsTapped(6)
            R.id.btn7 -> numberIsTapped(7)
            R.id.btn8 -> numberIsTapped(8)
            R.id.btn9 -> numberIsTapped(9)

            R.id.btnDivide -> {
                operationIsTapped(OPERATOR.DIVIDE)
                calculationString += " / "
            }
            R.id.btnPlus -> {
            operationIsTapped(OPERATOR.PLUS)
                calculationString += " + "
            }
            R.id.btnMinus -> {
                operationIsTapped(OPERATOR.SUBSTRACT)
                calculationString += " - "
            }
            R.id.btnMultiply -> {
                operationIsTapped(OPERATOR.MULTIPLY)
                calculationString += " * "
            }
            R.id.btnEqual -> operationIsTapped(OPERATOR.EQUAL)
            R.id.btnPercent -> implementPercent()
            R.id.btnClear -> clear()
        }

        txtCalculations.setText(calculationString)

    }

    private fun operationIsTapped (tappedOperator: OPERATOR) {

        if (currentOperator != null) {
            if (currentNumber != "") {

                stringNumberAtRight = currentNumber
                currentNumber = ""

                when (currentOperator) {
                    OPERATOR.PLUS -> calculationResult = stringNumberAtLeft!!.toDouble() + stringNumberAtRight!!.toDouble()
                    OPERATOR.SUBSTRACT -> calculationResult = stringNumberAtLeft!!.toDouble() - stringNumberAtRight!!.toDouble()
                    OPERATOR.MULTIPLY -> calculationResult = stringNumberAtLeft!!.toDouble() * stringNumberAtRight!!.toDouble()
                    OPERATOR.DIVIDE -> calculationResult = stringNumberAtLeft!!.toDouble() / stringNumberAtRight!!.toDouble()

                }

                stringNumberAtLeft = calculationResult.toString()
                edtNumber.setText(calculationResult.toString())
                calculationString = stringNumberAtLeft

            }
        }else {

            stringNumberAtLeft = currentNumber
            currentNumber = ""
        }
        currentOperator = tappedOperator

    }

    private fun numberIsTapped(tappedNumber: Int) {

        currentNumber = currentNumber + tappedNumber.toString()
        edtNumber.setText(currentNumber)
        calculationString = currentNumber
        txtCalculations.setText(calculationString)

    }
    private fun implementPercent() {
        val percentValue = edtNumber.text.toString().toDouble() / 100
        currentNumber = percentValue.toString()
        edtNumber.setText(currentNumber)
    }

    private fun clear() {

        stringNumberAtLeft = ""
        stringNumberAtRight = ""
        calculationResult = 0.0
        currentNumber = ""
        currentOperator = null
        edtNumber.setText("0")
        calculationString = ""
        txtCalculations.setText("0")
    }






}
