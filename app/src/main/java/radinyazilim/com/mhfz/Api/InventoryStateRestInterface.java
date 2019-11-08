package radinyazilim.com.mhfz.Api;

import java.util.List;

import radinyazilim.com.mhfz.models.FeedbackModel;
import radinyazilim.com.mhfz.models.InventoryStateModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface InventoryStateRestInterface {
    @PUT("api/Inventory_State")
        //@FormUrlEncoded
    Call<InventoryStateModel> createPost(@Body InventoryStateModel inventoryState);

    @GET("api/Inventory_State")
    Call<List<InventoryStateModel>> getInventoryStateModel();
}
