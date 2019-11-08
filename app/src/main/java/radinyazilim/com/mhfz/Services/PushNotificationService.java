package radinyazilim.com.mhfz.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.ArrayList;
import java.util.List;

import radinyazilim.com.mhfz.Api.ExpertApiClient;
import radinyazilim.com.mhfz.Api.ExpertRestInterface;
import radinyazilim.com.mhfz.models.ExpertModel;
import radinyazilim.com.mhfz.models.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PushNotificationService extends FirebaseInstanceIdService {
    private static final String TAG = "Mhfz-> ";
    List<ExpertModel> ExpertList = new ArrayList<>();
    ExpertModel expertMax;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        //String value = getIntent(LoginActivity.).getStringExtra("Variable name which you sent as an extra");
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        createPost(refreshedToken);





    }
    private void createPost(String token){

        ExpertRestInterface expertRestInterface = ExpertApiClient.getClient().create(ExpertRestInterface.class);
        ExpertModel expert = new ExpertModel("Radin",token);

        Call<ExpertModel> call1= expertRestInterface.createPost(expert);
        call1.enqueue(new Callback<ExpertModel>() {
            @Override
            public void onResponse(Call<ExpertModel> call, Response<ExpertModel> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, String.valueOf(response.code()));

                }
                else{
                    Log.d(TAG, String.valueOf(response.code()));
                }
                ExpertModel postResponse = response.body();

            }

            @Override
            public void onFailure(Call<ExpertModel> call, Throwable t) {

            }
        });
    }

}
