package ltd.test.problemsolving.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class DropdownFragment extends Fragment {

    TextView textView;
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dropdown, container, false);

        textView = (TextView) view.findViewById(R.id.tvQuestionDd);
        spinner = (Spinner) view.findViewById(R.id.spinner1);

        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");

        String question = questionModel.getQuestion();
        textView.setText("Q: "+question);
//
//        for (int i = 0; i < questionModel.getOptions().size(); i++){
//            String listItem = questionModel.getOptions().get(i).getValue();
//            //arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Integer.parseInt(listItem));
//            spinner.setAdapter(arrayAdapter);
//        }

        return view;
    }
}