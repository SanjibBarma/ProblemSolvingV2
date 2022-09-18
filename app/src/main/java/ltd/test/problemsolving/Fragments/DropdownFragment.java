package ltd.test.problemsolving.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ltd.test.problemsolving.MainActivity;
import ltd.test.problemsolving.Models.Option;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class DropdownFragment extends Fragment {

    TextView tvQuestionDropdown;
    Spinner spinner;
    List<Option> optionList;
    List<String> setFirstValue;
    ArrayAdapter adapter;
    private static String first_select = "Select One";
    private String selectedList = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ddropdown, container, false);

        tvQuestionDropdown = (TextView) view.findViewById(R.id.tvQuestionDropdown);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        optionList = new ArrayList<>();
        setFirstValue = new ArrayList<>();
        setFirstValue.add(first_select);

        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");
        //set Text View
        String question = questionModel.getQuestion();
        tvQuestionDropdown.setText("Q: "+question);

        optionList = questionModel.getOptions();

        for (int i = 0; i < optionList.size(); i++){
            if (!setFirstValue.contains(optionList.get(i).getValue())){
                setFirstValue.add(optionList.get(i).getValue());
            }
        }

        try {
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, setFirstValue);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(0, false);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (selectedList.equals("")){
            for (int i = 0; i < setFirstValue.size(); i++){
                spinner.setSelection(i, true);
                break;
            }
        }

        int initialSelectedPosition = spinner.getSelectedItemPosition();
        spinner.setSelection(initialSelectedPosition, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                for (int i = 0; i < adapterView.getCount(); i++){
                    String value = questionModel.getOptions().get(position-1).getReferTo();

                    int value1 = Integer.parseInt(value)-1;
                   //Toast.makeText(getContext(), "Message: "+value1, Toast.LENGTH_SHORT).show();

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.returnResult(value1);

                    String answer = questionModel.getOptions().get(position-1).getValue();
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("problemSolvingQnA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("dropDown_question", question);
                    editor.putString("dropDown_ans", answer);
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }


}