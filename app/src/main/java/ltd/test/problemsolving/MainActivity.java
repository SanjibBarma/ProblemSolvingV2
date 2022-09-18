package ltd.test.problemsolving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.dmoral.toasty.Toasty;
import ltd.test.problemsolving.ApiUtils.APIClient;
import ltd.test.problemsolving.Fragments.CheckboxFragment;
import ltd.test.problemsolving.Fragments.DropdownFragment;
import ltd.test.problemsolving.Fragments.MultipleChoiceFragment;
import ltd.test.problemsolving.Fragments.NumberInputFragment;
import ltd.test.problemsolving.Fragments.TextInputFragment;
import ltd.test.problemsolving.Interface.ApiInterface;
import ltd.test.problemsolving.Interface.DataTransferInterface;
import ltd.test.problemsolving.Models.QuestionModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DataTransferInterface {

    TextView textView;
    ApiInterface apiInterface;
    List<QuestionModel> questionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = (TextView) findViewById(R.id.textView);
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<List<QuestionModel>> call = apiInterface.getQuestionData();
        call.enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                if (response.isSuccessful()){
                    //Toast.makeText(MainActivity.this, "Success... ", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", response.body()+"");
                    questionModelList = response.body();
                    /*String loadId = questionModelList.get(0).getId();
                    String type = questionModelList.get(5).getType();*/

                    loadSingleQuestion(questionModelList.get(0).getId());

                }else{
                    Toast.makeText(MainActivity.this, "Wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadSingleQuestion(String id) {
        for (int i = 0; i < questionModelList.size(); i++){
            if (id == questionModelList.get(i).getId()){
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                loadNextQuestion(questionModelList.get(i));
            }
        }
    }

    private void loadNextQuestion(QuestionModel questionModel) {
        //Toast.makeText(this, "Data: "+ questionModel, Toast.LENGTH_SHORT).show();
        Log.d("Question_Data", questionModel+"");

        if (questionModel.getType().equals("multipleChoice")){
            //Log.d("Question_Data", questionModel.getOptions().get(0).getValue()+"");
            //Send Data from activity to fragment
            Fragment fragment = new MultipleChoiceFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putSerializable("Question_Data", questionModel);
            fragment.setArguments(bundle);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.frameLayoutId, fragment);
            fragmentTransaction.commit();
        }

        if (questionModel.getType().equals("textInput")){
            //Log.d("Question_Data", questionModel.getOptions().get(0).getValue()+"");
            //Send Data from activity to fragment
            Fragment fragment = new TextInputFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putSerializable("Question_Data", questionModel);
            fragment.setArguments(bundle);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.frameLayoutId, fragment);
            fragmentTransaction.commit();
        }

        if (questionModel.getType().equals("dropdown")){
            //Log.d("Question_Data", questionModel.getOptions().get(0).getValue()+"");
            //Send Data from activity to fragment
            Fragment fragment = new DropdownFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putSerializable("Question_Data", questionModel);
            fragment.setArguments(bundle);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.frameLayoutId, fragment);
            fragmentTransaction.commit();
        }

        if (questionModel.getType().equals("checkbox")){
            //Log.d("Question_Data", questionModel.getOptions().get(0).getValue()+"");
            //Send Data from activity to fragment
            Fragment fragment = new CheckboxFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putSerializable("Question_Data", questionModel);
            fragment.setArguments(bundle);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.frameLayoutId, fragment);
            fragmentTransaction.commit();
        }

        if (questionModel.getType().equals("numberInput")){
            //Log.d("Question_Data", questionModel.getOptions().get(0).getValue()+"");
            //Send Data from activity to fragment
            Fragment fragment = new NumberInputFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putSerializable("Question_Data", questionModel);
            fragment.setArguments(bundle);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.frameLayoutId, fragment);
            fragmentTransaction.commit();
        }


        //camera fragment will be here
        if (questionModel.getType().equals("camera")){
            //Log.d("Question_Data", questionModel.getOptions().get(0).getValue()+"");
            //Send Data from activity to fragment
            Intent Intent3=new   Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            startActivity(Intent3);
        }

    }

    @Override
    public void returnResult(int id) {
        loadSingleQuestion(questionModelList.get(id).getId());
        //Toast.makeText(this, ""+questionModelList.get(id).getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        refresh();
        //Toast.makeText(this, "Reload App", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        clearAllSharedPreferencesData();
    }

    private void clearAllSharedPreferencesData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("problemSolvingQnA", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();

        Toast.makeText(this, "Clear Data", Toast.LENGTH_SHORT).show();
    }

    public void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}