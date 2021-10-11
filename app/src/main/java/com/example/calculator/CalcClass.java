package com.example.calculator;

import android.widget.Switch;

public class CalcClass {
    private String leftNum;
    private String rightNum;
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(String leftNum) {
        this.leftNum = leftNum;
    }

    public String getRightNum() {
        return rightNum;
    }

    public void setRightNum(String rightNum) {
        this.rightNum = rightNum;
    }

    public boolean leftNumIsSet() {
        if (getLeftNum() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean rightNumIsSet() {
        if (getRightNum() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean operatorIsSet(){
        if(getOperator() == null){
            return false;
        }
        else {
            return true;
        }
    }

    public String calculate() {
        double leftInt = Double.parseDouble(getLeftNum());
        double rightInt = Double.parseDouble(getRightNum());
        String operator = getOperator();

        double dAnswer;
        String answer = "";

        switch (operator) {
            case "+":
                dAnswer = leftInt + rightInt;
                break;
            case "-":
                dAnswer = leftInt - rightInt;
                break;
            case "*":
                dAnswer = leftInt * rightInt;
                break;
            case  "/":
                dAnswer = leftInt / rightInt;
                break;
            default:
                dAnswer = 0.0;
        }
        if (operator.equals("/") && (rightInt == 0)){
            answer = "NaN";
        }

        else if (dAnswer % 1 == 0){
            answer = String.format("%.0f", dAnswer);
        }
        else{
            answer = Double.toString(dAnswer);
        }
        return answer;
    }



}