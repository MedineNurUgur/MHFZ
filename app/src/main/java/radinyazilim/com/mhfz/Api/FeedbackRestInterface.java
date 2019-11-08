package radinyazilim.com.mhfz.Api;

import java.util.List;

import radinyazilim.com.mhfz.models.ExpertModel;
import radinyazilim.com.mhfz.models.FeedbackModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FeedbackRestInterface {
    @POST("api/Feedback")
        //@FormUrlEncoded
    Call<FeedbackModel> createPost(@Body FeedbackModel feedback);

    @GET("api/Feedback")
    Call<List<FeedbackModel>> getFeedbackModel();
}
