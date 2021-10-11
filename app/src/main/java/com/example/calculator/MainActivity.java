package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CalcClass calcClass = new CalcClass();

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMult;
    private Button btnDivi;
    private Button btnDec;
    private Button btnNegpos;
    private Button btnRemdig;
    private Button btnClear;
    private Button btnEquals;
    private TextView displayTV;
    private TextView historyTV;

    private String numString = "";
    private String currentAnsStr = "";
    private boolean lastWasOp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMult = findViewById(R.id.btnMult);
        btnDivi = findViewById(R.id.btnDivi);
        btnDec = findViewById(R.id.btnDec);
        btnNegpos = findViewById(R.id.btnNegpos);
        btnRemdig = findViewById(R.id.btnRemdig);
        btnClear = findViewById(R.id.btnClear);
        btnEquals = findViewById(R.id.btnEquals);
        displayTV = findViewById(R.id.displayTV);
        historyTV = findViewById(R.id.historyTV);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDigit("0");
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useDecimal();
            }
        });

        btnNegpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleNegative();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearNums();
            }
        });

        btnRemdig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDigit();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOpAndCalculate("+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOpAndCalculate("-");
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOpAndCalculate("*");
            }
        });

        btnDivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOpAndCalculate("/");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useEquals();
            }
        });
    }

    private void updateStringNumber(String num){
        //Add number to string of numbers
        numString += num;
    }

    private void chooseDigit(String num){
        //If there is an answer in memory & an operator has been set, a new digit entry should send the current answer i.e. current left number & operator to the history display
        //This means that the last operation was a rolling calculation:
        if (numString.equals("") && !currentAnsStr.equals("") && calcClass.operatorIsSet()){
            updateHistoryWAnswerAndOp(calcClass.getOperator());
        }
        //If there is an answer in memory but no right num has been set, a new digit entry implies the beginning of a new calculation, so clear memory
        //This means the last operation was an equals:
        else if (numString.equals("") && !currentAnsStr.equals("") && !calcClass.rightNumIsSet()){
            clearNums();
        }
        updateStringNumber(num);
        updateDisplayWStringNum();
        lastWasOp = false;
    }

    private void checkOpAndCalculate(String op){
        //If the last entry was a different operator, change operator in memory (if same operator, do nothing)
        if (lastWasOp && !(calcClass.getOperator().equals(op))){
            updateHistory(calcClass.getLeftNum() + " " + op);
            calcClass.setOperator(op);
            lastWasOp = true;
        }
        //If last entry was not an operator, proceed with operator logic
        else if (!lastWasOp) {
            //Check if the left digit has been set yet; if not, set it to current number string
            if (!calcClass.leftNumIsSet()) {
                calcClass.setLeftNum(numString);
                updateHistory(numString + " " + op);
            }
            //If left number has been set but no operator is in memory, the last operation was an equals; just need operator & to change displays
            else if (calcClass.leftNumIsSet() && !calcClass.operatorIsSet()) {
                updateDisplayWStringNum();
                updateHistoryWAnswerAndOp(op);
            }
            //If left number has been set and operator has been set, set right number to current string
            else if (calcClass.leftNumIsSet()) {
                calcClass.setRightNum(numString);
                updateHistory(numString + " " + op);
            }
            //If both the left and right operator have been set, perform calculation using operator already in memory
            if (calcClass.leftNumIsSet() && calcClass.rightNumIsSet()) {
                getAnswer();
            }
            //Finally, always set the operator and reset the number string
            setOperatorAndReset(op);
            lastWasOp = true;
        }
    }

    private void getAnswer(){
        //Change history to display current calculation
        String histMsg = calcClass.getLeftNum() + " " + calcClass.getOperator() + " " + calcClass.getRightNum();
        historyTV.setText(histMsg);
        //Calculate & display answer
        currentAnsStr = calcClass.calculate();
        updateDisplayWAnswer();
        if (currentAnsStr.equals("NaN")){
            clearNums();
            historyTV.setText("NaN");
        }
        else {
            //Set the answer as the new left number
            calcClass.setLeftNum(currentAnsStr);
            calcClass.setRightNum(null);
        }
    }

    private void useEquals(){
        String currentNumString = displayTV.getText().toString();
        //Check if there is any input on screen; if not, do nothing
        if (!currentNumString.equals("")){
            //Check if any input has been entered into numString; if not, do nothing;
            if (!numString.equals("")){
                //Check if both the left number and the operator have been set
                if (calcClass.leftNumIsSet() && calcClass.operatorIsSet()) {
                    //Set right number as current number string
                    calcClass.setRightNum(numString);
                    //Calculate answer
                    getAnswer();
                    //Reset values
                    numString = "";
                    calcClass.setOperator(null);
                    lastWasOp = false;
                }
            }
        }
    }

    private void clearNums(){
        calcClass.setRightNum(null);
        calcClass.setLeftNum(null);
        numString = "";
        currentAnsStr = "";
        updateDisplayWStringNum();
        updateHistory("");
        lastWasOp = false;
    }

    private void useDecimal(){
        String currentNumString = displayTV.getText().toString();
        if (currentNumString.isEmpty() || numString.isEmpty()){
            chooseDigit("0.");
        }
        else {
            if (!currentNumString.contains(".")){
                chooseDigit(".");
            }
        }
    }

    private void toggleNegative(){
        //Get string currently on screen
        String currentNumString = displayTV.getText().toString();
        //Check if the string is empty; if so, do nothing
        if (!currentNumString.isEmpty()) {
            //Then, check if the string is a 0; if so, do nothing
            if (!currentNumString.equals("0")) {
                //First, check if what is on display is actually the result of a calculation; if so, apply neg/pos to answer
                if (!currentAnsStr.isEmpty() && !calcClass.operatorIsSet() && !calcClass.rightNumIsSet()) {
                    if (!currentNumString.contains("-")) {
                        currentAnsStr = makeNegative(currentNumString);
                    } else {
                        currentAnsStr = makePositive(currentNumString);
                    }
                    //Because the current answer is also the current left number, update that in memory
                    calcClass.setLeftNum(currentAnsStr);
                    updateDisplayWAnswer();
                }
                //In all other cases, apply neg/pos to number string
                else {
                    if (!currentNumString.contains("-")) {
                        numString = makeNegative(currentNumString);

                    } else {
                        numString = makePositive(currentNumString);
                    }
                    updateDisplayWStringNum();
                }
            }
        }
    }

    private String makeNegative(String currentNumString){
        String negNum;
        String neg = "-";
        negNum = neg + currentNumString;
        return negNum;
    }

    private String makePositive(String currentNumString){
        String posNum;
        posNum = currentNumString.substring(1);
        return posNum;
    }

    private void removeDigit(){
        String currentNumString = displayTV.getText().toString();
        if (!currentNumString.isEmpty()) {
            String shortString;
            shortString = currentNumString.substring(0, numString.length() - 1);
            if (!currentAnsStr.isEmpty() && !calcClass.operatorIsSet() && !calcClass.rightNumIsSet()) {
                currentAnsStr = shortString;
                updateDisplayWAnswer();
            }
            else {
                numString = shortString;
                updateDisplayWStringNum();
            }
        }
    }

    private void updateDisplayWStringNum(){
        displayTV.setText(numString);
    }

    private void updateDisplayWAnswer(){
        displayTV.setText(currentAnsStr);
    }

    private void updateHistory(String oldString){
        historyTV.setText(oldString);
    }

    private void updateHistoryWAnswerAndOp(String op){
        String histAns = currentAnsStr + " " + op;
        historyTV.setText(histAns);
    }

    private void setOperatorAndReset(String op){
        calcClass.setOperator(op);
        numString = "";
    }
}