package ltd.test.problemsolving.Interface;

import java.util.List;

import ltd.test.problemsolving.Models.Option;
import ltd.test.problemsolving.Models.QuestionModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/getSurvey")
    Call<List<QuestionModel>> getQuestionData();
}
