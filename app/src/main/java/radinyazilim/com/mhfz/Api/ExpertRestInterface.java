package radinyazilim.com.mhfz.Api;

import java.util.List;

import radinyazilim.com.mhfz.models.ExpertModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ExpertRestInterface {
    @POST("api/Expert")
        //@FormUrlEncoded
    Call<ExpertModel> createPost(@Body ExpertModel expert);

    @GET("api/Expert")
    Call<List<ExpertModel>> getExpertModel();
}
