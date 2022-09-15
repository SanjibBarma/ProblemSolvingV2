package ltd.test.problemsolving.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ltd.test.problemsolving.Models.Option;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class DropdownFragment extends Fragment {

    TextView tvQuestionDropdown;
    Spinner spinner;
    List<Option> optionList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ddropdown, container, false);

        tvQuestionDropdown = (TextView) view.findViewById(R.id.tvQuestionDropdown);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");
        //set Text View
        String question = questionModel.getQuestion();
        tvQuestionDropdown.setText("Q: "+question);


        optionList = questionModel.getOptions();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, optionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select");
//        spinner.setOnItemSelectedListener(this);



        return view;
    }

    /*@Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}