package com.example.geoquiz;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private Button nextButton;
    private Button prevButton;
    private TextView questionTextView;
    private int correct = 0;

    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {

            new Question(R.string.a, true),
            new Question(R.string.b, false),
            new Question(R.string.c, true),
            new Question(R.string.d, true),
            new Question(R.string.e, true),
            new Question(R.string.f, false),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        questionTextView
                = findViewById(R.id.answer_text_view);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:

                if (currentQuestionIndex < 7) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;

                    if (currentQuestionIndex == 6) {
                        questionTextView.setText(getString(R.string.correct, correct));
                        nextButton.setVisibility(View.INVISIBLE);
                        prevButton.setVisibility(View.INVISIBLE);
                        trueButton.setVisibility(View.INVISIBLE);
                        falseButton.setVisibility(View.INVISIBLE);

                        if (correct > 3)
                            questionTextView.setText(
                                    "CORRECTNESS IS " + correct
                                            + " "
                                            + "OUT OF 6");
                        else
                            questionTextView.setText("0");
                    }
                    else { updateQuestion(); }
                }

                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = (currentQuestionIndex - 1)
                            % questionBank.length;
                    updateQuestion();
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());


    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();

        int toastMessageId;


        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else { toastMessageId = R.string.wrong_answer; }


        Toast
                .makeText(MainActivity.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}