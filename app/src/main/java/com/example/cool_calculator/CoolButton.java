package com.example.cool_calculator;

import android.content.Context;
import android.util.AttributeSet;

import static com.example.cool_calculator.MainActivity.answerDisplayed;
import static com.example.cool_calculator.MainActivity.clearDisplay;
import static com.example.cool_calculator.MainActivity.display;

public class CoolButton extends android.support.v7.widget.AppCompatButton {

    public CoolButton(Context context) {
        super(context);
    }


    public CoolButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoolButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addDigit() {
        if (answerDisplayed) {
            clearDisplay();
            answerDisplayed = false;
        }
        display.setText(String.format("%s%s", display.getText(), this.getText()));
    }


}
