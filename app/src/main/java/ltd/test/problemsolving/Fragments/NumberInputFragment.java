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

import ltd.test.problemsolving.MainActivity;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class NumberInputFragment extends Fragment {

    TextView textView;
    EditText editText;
    ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number_input, container, false);

        textView = (TextView) view.findViewById(R.id.tvQuestionNumberInput);
        editText = (EditText) view.findViewById(R.id.etInputNumber);
        imageButton = (ImageButton) view.findViewById(R.id.idImgBtnNext);

        //set Text View
        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");
        String question = questionModel.getQuestion();
        textView.setText("Q: "+question);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setEditValue = editText.getText().toString().trim();

                if (setEditValue.isEmpty()){
                    editText.setError("Write something...");
                }else {

                   /* MainActivity mainActivity=(MainActivity)getActivity();
                    mainActivity.returnResult(referToValue);*/

                    //save data to sharedPref
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("problemSolvingQnA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("inputNumber_question", question);
                    editor.putString("inputNumber_ans", setEditValue);
                    editor.commit();
                }

                /*SharedPreferences sharedPreferences = getContext().getSharedPreferences("problemSolvingQnA", Context.MODE_PRIVATE);
                if (sharedPreferences.contains("inputNumber_question") && sharedPreferences.contains("inputNumber_ans")){
                    String questionData = sharedPreferences.getString("inputNumber_question", "Nothing to see...");
                    String answerData = sharedPreferences.getString("inputNumber_ans", "Nothing to see...");
                    Toast.makeText(getContext(), "Ans: "+ questionData+"\n"+answerData, Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        return view;
    }
}