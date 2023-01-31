package com.android.monopolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Players> playersArray = new ArrayList<>();

    public static Players currentPlayer;
    public Players moneyReceiver;

    private Button startButton;
    private Button playingCube;

    private EditText redScore;
    private EditText greenScore;
    private EditText blueScore;
    private EditText yellowScore;

    private ImageButton redHome;
    private ImageButton greenHome;
    private ImageButton blueHome;
    private ImageButton yellowHome;

    private ImageButton redEdit;
    private ImageButton greenEdit;
    private ImageButton blueEdit;
    private ImageButton yellowEdit;

    private ImageButton redPay;
    private ImageButton greenPay;
    private ImageButton bluePay;
    private ImageButton yellowPay;

    private TextView editText;
    private EditText calculator;
    private Button calculator0;
    private Button calculator00;
    private Button calculator000;
    private Button calculator1;
    private Button calculator2;
    private Button calculator3;
    private Button calculator4;
    private Button calculator5;
    private Button calculator6;
    private Button calculator7;
    private Button calculator8;
    private Button calculator9;
    private Button calculatorPlus;
    private Button calculatorMinus;
    private Button calculatorClear;
    private Button calculateMultiply;
    private Button calculatorEquals;

    private int number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().setFragmentResultListener("selectPlayers", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                startNewGame(bundle.getBooleanArray("checkedPlayersArray"));
            }
        });

        getSupportFragmentManager().setFragmentResultListener("selectReceiver", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                moneyReceiver = Players.valueOf(bundle.getString("moneyReceiver"));
                editText.setText(String.format("%s >> %s",currentPlayer.toString(), moneyReceiver.toString()));
            }
        });

        startButton = findViewById(R.id.startButton);
        playingCube = findViewById(R.id.playingCube);

        redScore    = findViewById(R.id.redScore);
        greenScore  = findViewById(R.id.greenScore);
        blueScore   = findViewById(R.id.blueScore);
        yellowScore = findViewById(R.id.yellowScore);

        redHome     = findViewById(R.id.redHome);
        greenHome   = findViewById(R.id.greenHome);
        blueHome    = findViewById(R.id.blueHome);
        yellowHome  = findViewById(R.id.yellowHome);

        redEdit     = findViewById(R.id.redEdit);
        greenEdit   = findViewById(R.id.greenEdit);
        blueEdit    = findViewById(R.id.blueEdit);
        yellowEdit  = findViewById(R.id.yellowEdit);

        redPay     = findViewById(R.id.redPay);
        greenPay   = findViewById(R.id.greenPay);
        bluePay    = findViewById(R.id.bluePay);
        yellowPay  = findViewById(R.id.yellowPay);

        editText        = findViewById(R.id.editText);
        calculator      = findViewById(R.id.calculator);
        calculator0     = findViewById(R.id.calculator0);
        calculator00    = findViewById(R.id.calculator00);
        calculator000   = findViewById(R.id.calculator000);
        calculator1     = findViewById(R.id.calculator1);
        calculator2     = findViewById(R.id.calculator2);
        calculator3     = findViewById(R.id.calculator3);
        calculator4     = findViewById(R.id.calculator4);
        calculator5     = findViewById(R.id.calculator5);
        calculator6     = findViewById(R.id.calculator6);
        calculator7     = findViewById(R.id.calculator7);
        calculator8     = findViewById(R.id.calculator8);
        calculator9     = findViewById(R.id.calculator9);
        calculatorPlus  = findViewById(R.id.calculatorPlus);
        calculatorMinus = findViewById(R.id.calculatorMinus);
        calculatorClear = findViewById(R.id.calculatorClear);
        calculateMultiply = findViewById(R.id.calculateMultiply);
        calculatorEquals = findViewById(R.id.calculatorEquals);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        openGame();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartYesNoDialog myDialogFragment = new StartYesNoDialog();
                myDialogFragment.show(getSupportFragmentManager(), "myDialog");
            }
        });

        playingCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cube = (int) (1 + Math.random()*6);
                playingCube.setText(String.valueOf(cube));
            }
        });

        redHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScore(redScore, 500, null);
            }
        });

        greenHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScore(greenScore, 500, null);
            }
        });

        blueHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScore(blueScore, 500, null);
            }
        });

        yellowHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScore(yellowScore, 500, null);
            }
        });

        redEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.RED;
                editText.setText(String.format("Edit %s", currentPlayer));
            }
        });

        greenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.GREEN;
                editText.setText(String.format("Edit %s", currentPlayer));
            }
        });

        blueEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.BLUE;
                editText.setText(String.format("Edit %s", currentPlayer));
            }
        });

        yellowEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.YELLOW;
                editText.setText(String.format("Edit %s", currentPlayer));
            }
        });

        redPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.RED;
                selectReceiver();
            }
        });

        greenPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.GREEN;
                selectReceiver();
            }
        });

        bluePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.BLUE;
                selectReceiver();
            }
        });

        yellowPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = Players.YELLOW;
                selectReceiver();
            }
        });

        calculator0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("0");
            }
        });

        calculator00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("00");
            }
        });

        calculator000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("000");
            }
        });

        calculator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("1");
            }
        });

        calculator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("2");
            }
        });

        calculator3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("3");
            }
        });

        calculator4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("4");
            }
        });

        calculator5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("5");
            }
        });

        calculator6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("6");
            }
        });

        calculator7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("7");
            }
        });

        calculator8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("8");
            }
        });

        calculator9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("9");
            }
        });

        calculatorClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalculator("C");
            }
        });

        calculatorPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curSum;
                try {
                    curSum = Integer.valueOf(calculator.getText().toString());
                } catch (Exception e) {
                    curSum = 0;
                }

                EditText receiverScore = null;
                if (curSum != 0) {
                    if(currentPlayer == Players.RED) {
                        changeScore(redScore, curSum, getReceiverScoreField());
                    } else if(currentPlayer == Players.GREEN) {
                        changeScore(greenScore, curSum, getReceiverScoreField());
                    } else if(currentPlayer == Players.BLUE) {
                        changeScore(blueScore, curSum, getReceiverScoreField());
                    } else if(currentPlayer == Players.YELLOW) {
                        changeScore(yellowScore, curSum, getReceiverScoreField());
                    }
                }
            }
        });

        calculatorMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curSum;
                try {
                    curSum = Integer.valueOf(calculator.getText().toString());
                } catch (Exception e) {
                    curSum = 0;
                }

                if (curSum != 0) {
                    if(currentPlayer == Players.RED) {
                        changeScore(redScore, -curSum, getReceiverScoreField());
                    } else if(currentPlayer == Players.GREEN) {
                        changeScore(greenScore, -curSum, getReceiverScoreField());
                    } else if(currentPlayer == Players.BLUE) {
                        changeScore(blueScore, -curSum, getReceiverScoreField());
                    } else if(currentPlayer == Players.YELLOW) {
                        changeScore(yellowScore, -curSum, getReceiverScoreField());
                    }
                }
            }
        });

        calculateMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curSum;
                try {
                    curSum = Integer.valueOf(calculator.getText().toString());
                } catch (Exception e) {
                    curSum = 0;
                }
                number1 = curSum;
                calculator.setText("0");
            }
        });

        calculatorEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curSum;
                try {
                    curSum = Integer.valueOf(calculator.getText().toString());
                } catch (Exception e) {
                    curSum = 0;
                }
                curSum = number1 * curSum;
                calculator.setText(String.valueOf(curSum));
            }
        });
    }

    private void openGame() {
        currentPlayer = null;
        moneyReceiver = null;

        number1 = 0;

        redScore.setText("0");
        redHome.setEnabled(false);
        redEdit.setEnabled(false);
        redPay.setEnabled(false);

        greenScore.setText("0");
        greenHome.setEnabled(false);
        greenEdit.setEnabled(false);
        greenPay.setEnabled(false);

        blueScore.setText("0");
        blueHome.setEnabled(false);
        blueEdit.setEnabled(false);
        bluePay.setEnabled(false);

        yellowScore.setText("0");
        yellowHome.setEnabled(false);
        yellowEdit.setEnabled(false);
        yellowPay.setEnabled(false);
    }

    private void startNewGame(boolean[] checkedPlayersArray) {
        currentPlayer = null;
        moneyReceiver = null;

        playersArray.clear();

        if(checkedPlayersArray[0]) {
            playersArray.add(Players.RED);
            redScore.setText("15000");
            redHome.setEnabled(true);
            redEdit.setEnabled(true);
            redPay.setEnabled(true);
        }

        if(checkedPlayersArray[1]) {
            playersArray.add(Players.GREEN);
            greenScore.setText("15000");
            greenHome.setEnabled(true);
            greenEdit.setEnabled(true);
            greenPay.setEnabled(true);
        }

        if(checkedPlayersArray[2]) {
            playersArray.add(Players.BLUE);
            blueScore.setText("15000");
            blueHome.setEnabled(true);
            blueEdit.setEnabled(true);
            bluePay.setEnabled(true);
        }

        if(checkedPlayersArray[3]) {
            playersArray.add(Players.YELLOW);
            yellowScore.setText("15000");
            yellowHome.setEnabled(true);
            yellowEdit.setEnabled(true);
            yellowPay.setEnabled(true);
        }
    }

    private void changeScore(EditText score, int sum, EditText receiverScore) {
        int curSum;
        try {
            curSum = Integer.valueOf(score.getText().toString());
        } catch (Exception e) {
            curSum = 0;
        }

        int curReceiverSum = 0;
        try {
            if(receiverScore != null) {
                curReceiverSum = Integer.valueOf(receiverScore.getText().toString());
            }
        } catch (Exception e) {
            curReceiverSum = 0;
        }

        if(curReceiverSum == 0) {
            curSum += sum;
        } else {
            if(sum < 0) sum = -sum;
            if(sum > curSum) {
                curReceiverSum += curSum;
            } else {
                curReceiverSum += sum;
            }
            curSum -= sum;
        }

        if(curReceiverSum > 0) {
            receiverScore.setText(String.valueOf(curReceiverSum));
        }

        if(curSum > 0) {
            score.setText(String.valueOf(curSum));
        } else {
            score.setText(String.valueOf(0));

            if(currentPlayer == Players.RED) {
                redHome.setEnabled(false);
                redEdit.setEnabled(false);
                redPay.setEnabled(false);
            } else if(currentPlayer == Players.GREEN) {
                greenHome.setEnabled(false);
                greenEdit.setEnabled(false);
                greenPay.setEnabled(false);
            } else if(currentPlayer == Players.BLUE) {
                blueHome.setEnabled(false);
                blueEdit.setEnabled(false);
                bluePay.setEnabled(false);
            } else if(currentPlayer == Players.YELLOW) {
                yellowHome.setEnabled(false);
                yellowEdit.setEnabled(false);
                yellowPay.setEnabled(false);
            }

            for (int i = 0; i < playersArray.size(); i++) {
                if(playersArray.get(i) == currentPlayer) {
                    playersArray.remove(i);
                    break;
                }
            }

            BankruptMessageDialog myDialogFragment = new BankruptMessageDialog();
            myDialogFragment.show(getSupportFragmentManager(), "myDialog");
        }

        currentPlayer = null;
        moneyReceiver = null;
        editText.setText("EDIT");
        calculator.setText("0");
    }

    private void setCalculator(String addNumber) {
        String curSum = calculator.getText().toString();
        if (curSum.equals("")) {
            curSum = "0";
        }
        String newSum;
        if (addNumber.equals("C")) {
            newSum = "0";
        } else if (curSum.equals("0") && addNumber.equals("0")) {
            newSum = "0";
        } else if (curSum.equals("0") && addNumber.equals("00")) {
            newSum = "0";
        } else if (curSum.equals("0") && addNumber.equals("000")) {
            newSum = "0";
        } else if (curSum.equals("0")) {
            newSum = addNumber;
        } else {
            newSum = curSum + addNumber;
        }
        calculator.setText(newSum);
    }

    private EditText getReceiverScoreField() {
        if(moneyReceiver == Players.RED) return redScore;
        else if(moneyReceiver == Players.GREEN) return greenScore;
        else if(moneyReceiver == Players.BLUE) return blueScore;
        else if(moneyReceiver == Players.YELLOW) return yellowScore;

        return null;
    }

    private void selectReceiver() {
        SelectReceiverDialog myDialogFragment = new SelectReceiverDialog();
        myDialogFragment.show(getSupportFragmentManager(), "myDialog");
    }
}