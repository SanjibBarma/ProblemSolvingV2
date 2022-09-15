package ltd.test.problemsolving.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import es.dmoral.toasty.Toasty;
import ltd.test.problemsolving.MainActivity;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class TextInputFragment extends Fragment {

    TextView tvQuestionText;
    ImageButton imgButtonNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_input, container, false);

        tvQuestionText = (TextView) view.findViewById(R.id.tvQuestionText);
        imgButtonNext = (ImageButton) view.findViewById(R.id.imgButtonNext);

        //getData from Activity
        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");

        String question = questionModel.getQuestion();
        tvQuestionText.setText(question);

        imgButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (!questionModel.getOptions().equals("null")){
                    Toasty.warning(getContext(), "Option not Null", Toasty.LENGTH_SHORT).show();
                }else {

                }*/

                int referToValue = Integer.parseInt(questionModel.getReferTo())-1;

                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.returnResult(referToValue);
            }
        });

        return view;
    }
}

