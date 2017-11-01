package ca.bcit.comp2052.a00643427.multiplechoicequiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static ca.bcit.comp2052.a00643427.multiplechoicequiz.MainActivity.answers;

/**
 * Created by steward on 2017-10-22.
 */

public class QuestionFragment extends Fragment {

    //public int fragmentNumber = getArguments().getInt("position");
    public QuestionFragment() {
    }

    private android.view.View.OnClickListener radioListener =
            new android.view.View.OnClickListener() {
                public void  onClick(View v) {
                    RadioButton rb = (RadioButton) v;
                    final String Right_Answers[] = getResources().getStringArray(R.array.rightAnswers);
                    int num =getArguments().getInt("position");
                    // Implement your logic here
                    answers[num] = rb.getText();
                    CharSequence answer;
                    if(Right_Answers[num].equals(rb.getText().toString())){
                        //android.app.Activity.setTitle(" Answer is right! ");
                        answer = "Right";

                    }else {
                        answer = "Wrong";
                        //rb.setTextColor(66666);
                    }
                    getActivity().setTitle(" Answer is "+answer+"! ");
                    Toast.makeText(getActivity(),
                            "Question " + (num+1)  + " answer: " + rb.getText()+"  is  "+ answer,
                            Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.question, container, false);
        final int questionNo = getArguments().getInt("position");
        final String questions[] = getResources().getStringArray(R.array.questions);
        final String questionOptions[] = getResources().getStringArray(R.array.options);
        final int optionNumber = Integer.parseInt(getResources().getString(R.string.optionSize));;
        int optionNo = questionNo * optionNumber;


        //RatingBar ratingBar = (RatingBar) fragmentView.findViewById(R.id.ratingBar);
        //build question text
        TextView textView = (TextView) fragmentView.findViewById(R.id.textView);
        textView.setText(questions[questionNo]);

        //radioListener
        RadioButton radioButton1 = (RadioButton) fragmentView.findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton) fragmentView.findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton) fragmentView.findViewById(R.id.radioButton3);
        RadioButton radioButton4 = (RadioButton) fragmentView.findViewById(R.id.radioButton4);
        RadioButton radioButton5 = (RadioButton) fragmentView.findViewById(R.id.radioButton5);

        radioButton1.setText(questionOptions[optionNo]);
        radioButton2.setText(questionOptions[optionNo+1]);
        radioButton3.setText(questionOptions[optionNo+2]);
        radioButton4.setText(questionOptions[optionNo+3]);
        radioButton5.setText(questionOptions[optionNo+4]);

        radioButton1.setOnClickListener(radioListener);
        radioButton2.setOnClickListener(radioListener);
        radioButton3.setOnClickListener(radioListener);
        radioButton4.setOnClickListener(radioListener);
        radioButton5.setOnClickListener(radioListener);

        return fragmentView;
    }
}
