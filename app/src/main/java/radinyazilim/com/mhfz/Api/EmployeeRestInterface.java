package radinyazilim.com.mhfz.Api;

import java.util.List;

import radinyazilim.com.mhfz.models.EmployeeModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface EmployeeRestInterface {

        @GET("api/Employee")
        Call<List<EmployeeModel>> getEmployeeModel();

        @PUT("api/Employee")
                //@FormUrlEncoded
        Call<EmployeeModel> createPost(@Body EmployeeModel employee);
}
