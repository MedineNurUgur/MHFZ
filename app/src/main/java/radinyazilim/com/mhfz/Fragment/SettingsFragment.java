package radinyazilim.com.mhfz.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import radinyazilim.com.mhfz.Activities.LoginActivity;
import radinyazilim.com.mhfz.Api.ApiClient;
import radinyazilim.com.mhfz.Api.FeedbackApiClient;
import radinyazilim.com.mhfz.Api.FeedbackRestInterface;
import radinyazilim.com.mhfz.Api.RestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.ExpertModel;
import radinyazilim.com.mhfz.models.FeedbackModel;
import radinyazilim.com.mhfz.models.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingsFragment extends Fragment {
    EditText Id,EskiParola,YeniParola,Feedback;
    RestInterface restInterface;
    List<LoginModel> LoginList = new ArrayList<>();
    LoginModel expert;
    private static final String TAG = "Mhfz-> ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,null);

        Button btnFeedback = view.findViewById(R.id.profile_btn_feedback);
        Button btnPassword = view.findViewById(R.id.profile_btn_password);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFeedBackDialog();
            }
        });
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPasswordDialog();
            }
        });
        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<List<LoginModel>> call = restInterface.getLoginModel();
        call.enqueue(new Callback<List<LoginModel>>() {
            @Override
            public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
                LoginList = response.body();
            }

            @Override
            public void onFailure(Call<List<LoginModel>> call, Throwable t) {

            }
        });

        return view;

    }

    private void createPost(String feedbackMesage){
        FeedbackRestInterface feedbackRestInterface = FeedbackApiClient.getClient().create(FeedbackRestInterface.class);


        FeedbackModel feedback = new FeedbackModel(feedbackMesage);
        Call<FeedbackModel> call= feedbackRestInterface.createPost(feedback);
        call.enqueue(new Callback<FeedbackModel>() {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, String.valueOf(response.code()));
                }
                FeedbackModel postResponse = response.body();

            }

            @Override
            public void onFailure(Call<FeedbackModel> call, Throwable t) {

            }
        });

    }

    public void showPasswordDialog(){
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.password_change_dialog, null);
        Id = dialogView.findViewById(R.id.Id);
        EskiParola = dialogView.findViewById(R.id.EskiParola);
        YeniParola = dialogView.findViewById(R.id.YeniParola);
        materialDialog.customView(dialogView, false);
        materialDialog.cancelable(true);
        materialDialog.positiveText("Parola değiştir");
        materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                if(LoginList!=null){
                    for(int i = 0;i<LoginList.size();i++){
                        if(Id.getText().toString().equals(LoginList.get(i).id)){
                            expert = LoginList.get(i);
                            break;
                        }
                    }
                }
                else {
                    Log.d(TAG,"İşci bulunamadı");
                }
                if(expert!=null) {
                    if (expert.password.equals(EskiParola.getText().toString())) {
                        expert.password = YeniParola.getText().toString();
                        Call<LoginModel> call1 = restInterface.createPost(expert);
                        call1.enqueue(new Callback<LoginModel>() {
                            @Override
                            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                if (!response.isSuccessful()) {
                                    Log.d(TAG, String.valueOf(response.code()));
                                    Alert("Hata","Sistemde bir arıza oluştu. Lütfen tekrar deneyiniz");
                                }
                                else{
                                    Alert("Bilgi","Parolanız başarıyla güncellendi.");
                                }

                            }

                            @Override
                            public void onFailure(Call<LoginModel> call, Throwable t) {

                            }
                        });
                    }
                    else{
                        Alert("Hatalı İşlem","Eski şifrenizi yanlış girdiniz.Lütfen tekrar deneyiniz");
                    }
                }
                else{
                    Log.d(TAG,"İşci bulunamadı");
                    Alert("Hatalı işlem","Kullanıcı numaranızı yanlış girdiniz.Lütfen tekrar giriniz.");
                }

                dialog.dismiss();

            }
        });
        materialDialog.show();
    }

    public void showFeedBackDialog(){
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.feedback_dialog, null);
        Feedback = dialogView.findViewById(R.id.Feedback);
        materialDialog.customView(dialogView, false);
        materialDialog.cancelable(true);
        materialDialog.positiveText("Gönder");
        materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                String feedback = Feedback.getText().toString();
                createPost(feedback);
                Alert("Kayıt","Geri bildiriminiz başarıyla tarafımıza gönderilmiştir.");
                dialog.dismiss();

            }
        });
        materialDialog.show();
    }
    private void Alert(String title,String message){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
