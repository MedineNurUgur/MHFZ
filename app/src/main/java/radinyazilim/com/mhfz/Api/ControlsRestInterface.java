package radinyazilim.com.mhfz.Api;

import java.util.List;

import radinyazilim.com.mhfz.models.ControlModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ControlsRestInterface {
    @POST("api/Feedback")
        //@FormUrlEncoded
    Call<ControlModel> createPost(@Body ControlModel control);

    @GET("api/Feedback")
    Call<List<ControlModel>> getFeedbackModel();
}
