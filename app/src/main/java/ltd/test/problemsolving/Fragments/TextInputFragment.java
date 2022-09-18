package ltd.test.problemsolving.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import ltd.test.problemsolving.MainActivity;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class TextInputFragment extends Fragment {

    TextView tvQuestionText;
    ImageButton imgButtonNext;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_input, container, false);

        tvQuestionText = (TextView) view.findViewById(R.id.tvQuestionText);
        imgButtonNext = (ImageButton) view.findViewById(R.id.imgButtonNext);
        editText = (EditText) view.findViewById(R.id.etInputText);

        //getData from Activity
        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");

        String question = questionModel.getQuestion();
        tvQuestionText.setText(question);

        imgButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String setEditValue = editText.getText().toString().trim();

                if (setEditValue.isEmpty()){
                    editText.setError("Write something...");
                }else {
                    int referToValue = Integer.parseInt((String) questionModel.getReferTo())-1;
                    MainActivity mainActivity=(MainActivity)getActivity();
                    mainActivity.returnResult(referToValue);

                    //save data to sharedPref
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("problemSolvingQnA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("inputText_question", question);
                    editor.putString("inputText_ans", setEditValue);
                    editor.commit();
                }
            }
        });

        return view;
    }
}

