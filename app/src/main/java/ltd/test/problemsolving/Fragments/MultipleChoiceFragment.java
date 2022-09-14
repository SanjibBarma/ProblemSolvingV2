package ltd.test.problemsolving.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;
import ltd.test.problemsolving.MainActivity;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;


public class MultipleChoiceFragment extends Fragment {

    TextView tvQuestion;
    RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        //getData from Activity
        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");

        //set Text View
        String question = questionModel.getQuestion();
        tvQuestion.setText("Q: "+question);

        for (int i = 0; i < questionModel.getOptions().size(); i++){
            RadioButton radioButton = new RadioButton(getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                radioButton.setId(View.generateViewId());
                radioButton.setText(questionModel.getOptions().get(i).getValue());
                radioGroup.addView(radioButton);
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    String id1 = String.valueOf(id -1);
                    Toasty.success(getContext(), "Mes: "+ questionModel.getOptions().get(Integer.parseInt(id1)).getReferTo(), Toasty.LENGTH_SHORT).show();
                    String referToId = questionModel.getOptions().get(Integer.parseInt(String.valueOf(id1))).getReferTo();

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.returnResult(referToId);

                }
            }
        });

        Toasty.success(getContext(), "data: "+question, Toasty.LENGTH_SHORT).show();

        return view;
    }
}