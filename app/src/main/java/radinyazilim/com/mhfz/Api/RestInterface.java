package radinyazilim.com.mhfz.Api;

import java.util.List;

import radinyazilim.com.mhfz.models.EmployeeModel;
import radinyazilim.com.mhfz.models.ExpertModel;
import radinyazilim.com.mhfz.models.LoginModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RestInterface {
    @GET("api/ExpertLogin")
    Call<List<LoginModel>> getLoginModel();
    @PUT("api/ExpertLogin")
        //@FormUrlEncoded
    Call<LoginModel> createPost(@Body LoginModel expert);

}
