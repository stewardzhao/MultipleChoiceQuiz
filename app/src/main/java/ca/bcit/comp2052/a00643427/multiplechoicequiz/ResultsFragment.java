package ca.bcit.comp2052.a00643427.multiplechoicequiz;

/**
 * Created by steward on 2017-10-21.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static ca.bcit.comp2052.a00643427.multiplechoicequiz.MainActivity.answers;

public class ResultsFragment extends Fragment {


    public ResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.results, container, false);
        //TextView textView = (TextView) fragmentView.findViewById(R.id.textView1);

        //textView.setText(questions[fragmentNumber]);
        getActivity().setTitle(getString(R.string.app_name));
        Button button1 = (Button) fragmentView.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String Right_Answers[] = getResources().getStringArray(R.array.rightAnswers);
                TextView textView1 = (TextView) fragmentView.findViewById(R.id.textView1);
                textView1.setText(null);
                Integer arraySize = answers.length;
                for(int l=0; l<arraySize; l++){
                    int num = l + 1;
                    textView1.append("Q"+num+" answer:  "+answers[l]+ " ");
                    if(Right_Answers[l].equals(answers[l])){
                        //textView1.append("Q"+num+" answer:  "+answers[l]);
                        textView1.append(Html.fromHtml("<font color='GREEN'>  Right</font>"));
                        textView1.append("\n");
                    }else {
                        //textView1.append("Q"+num+" answer:  "+answers[l]);
                        textView1.append(Html.fromHtml(getString(R.string.wrong)));
                        textView1.append("\n");
                        //textView1.setTextColor(Color.RED);
                    }
                    //textView1.append("Q"+num+" answer:  "+answers[l]+"\n");
                    textView1.append(" "+"\n");
                }
            }
        });
        button1.callOnClick();
        button1.setVisibility(fragmentView.GONE);
        return fragmentView;
    }

}
