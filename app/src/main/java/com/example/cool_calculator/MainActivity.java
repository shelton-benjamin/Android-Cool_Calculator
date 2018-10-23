package com.example.cool_calculator;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    CoolButton zeroButton, oneButton, twoButton, threeButton, fourButton,
            fiveButton, sixButton, sevenButton, eightButton, nineButton,
            decimalButton, clearButton, clearEntryButton, plusButton, minusButton,
            multiplyButton, divideButton, equalsButton;


    static TextView display;

    TextView scratch;

    private BigDecimal firstValue;
    private BigDecimal secondValue;
    private char operator;

    static boolean decimalUsed = false;
    private boolean firstValueSet = false;
    static boolean answerDisplayed = false;
    private Snackbar zeroBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        scratch = findViewById(R.id.scratch);
        zeroButton = findViewById(R.id.zeroButton);
        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton = findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);
        sevenButton = findViewById(R.id.sevenButton);
        eightButton = findViewById(R.id.eightButton);
        nineButton = findViewById(R.id.nineButton);
        decimalButton = findViewById(R.id.decimalButton);
        clearButton = findViewById(R.id.clearButton);
        clearEntryButton = findViewById(R.id.clearEntryButton);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);
        equalsButton = findViewById(R.id.equalsButton);

        zeroBar = Snackbar.make(findViewById(R.id.constraintLayout),
                R.string.divideByZero, Snackbar.LENGTH_SHORT);

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeroButton.addDigit();
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneButton.addDigit();
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoButton.addDigit();
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threeButton.addDigit();
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourButton.addDigit();
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiveButton.addDigit();
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sixButton.addDigit();
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sevenButton.addDigit();
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eightButton.addDigit();
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nineButton.addDigit();
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!decimalUsed) {
                    decimalButton.addDigit();
                    decimalUsed = true;
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDisplay();
                firstValue = BigDecimal.valueOf(0);
                secondValue = BigDecimal.valueOf(0);
                operator = ' ';
            }
        });

        clearEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDisplay();
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked('+');
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked('-');
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked('*');
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked('/');
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal answer = calculateAnswer();
                BigDecimal divisor = BigDecimal.valueOf(1);

                answer = answer.stripTrailingZeros();
                display.setText(answer.toPlainString());

                firstValueSet = false;
                answerDisplayed = true;
                clearScratch();
            }
        });

    }


    static public void clearDisplay() {

        display.setText(null);
        decimalUsed = false;
    }

    private void clearScratch() {

        scratch.setText(null);
    }

    private void setScratchText() {

        StringBuilder scratchText = new StringBuilder();
        scratchText.append(scratch.getText());
        scratchText.append(display.getText());
        scratchText.append(operator);
        scratch.setText(scratchText);
    }

    private void setScratchText(char operator) {


    }

    private void operatorClicked(char symbol) {

        if (!firstValueSet)
            try {

                operator = symbol;
                double displayed = Double.parseDouble(display.getText().toString());
                firstValue = BigDecimal.valueOf(displayed);
                firstValueSet = true;
                setScratchText();

            } catch (NumberFormatException ex) {
                // do nothing
            } finally {
                clearDisplay();
            }
        else {

            firstValue = calculateAnswer();
            operator = symbol;
            setScratchText();
            clearDisplay();
        }
    }

    private BigDecimal calculateAnswer() {

        BigDecimal answer = BigDecimal.valueOf(0);

        try {
            double displayed = Double.parseDouble(display.getText().toString());
            secondValue = BigDecimal.valueOf(displayed);

            switch (operator) {

                case '+':
                    answer = firstValue.add(secondValue);
                    break;
                case '-':
                    answer = firstValue.subtract(secondValue);
                    break;
                case '*':
                    answer = firstValue.multiply(secondValue);
                    break;
                case '/':

                    if (secondValue.doubleValue() == 0) {
                        display.setText(R.string.errorDisplay);
                        firstValueSet = false;
                        answerDisplayed = true;
                        zeroBar.show();
                        return answer;
                    } else {
                        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
                        answer = firstValue.divide(secondValue, mc);
                    }
                    break;
            }

            return answer;


        } catch (NumberFormatException ex) {
            return answer;
        }
    }
}