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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ltd.test.problemsolving.MainActivity;
import ltd.test.problemsolving.Models.Option;
import ltd.test.problemsolving.Models.QuestionModel;
import ltd.test.problemsolving.R;

public class CheckboxFragment extends Fragment {

    TextView textView;
    ArrayAdapter adapter;
    ListView listView;
    List<Option> optionList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_checkbox, container, false);
        textView = (TextView) view.findViewById(R.id.tvQuestionCheckBox);
        listView = (ListView) view.findViewById(R.id.list_item);

        QuestionModel questionModel = (QuestionModel) getArguments().getSerializable("Question_Data");
        //set Text View
        String question = questionModel.getQuestion();
        textView.setText("Q: "+question);

        optionList = questionModel.getOptions();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, optionList);
        listView.setAdapter(adapter);

        //setOnCheckedChangeListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(getContext(), "Message: "+position, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < adapterView.getCount(); i++){
                    String value = questionModel.getOptions().get(position).getReferTo();
                    //Toast.makeText(getContext(), "Message: "+value, Toast.LENGTH_SHORT).show();

                    int value1 = Integer.parseInt(value)-1;

                    //Toasty.success(getContext(), "Mes: "+ value1, Toasty.LENGTH_SHORT).show();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.returnResult(value1);
                }
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        Toast.makeText(getContext(), "itemId: "+itemId, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }


}