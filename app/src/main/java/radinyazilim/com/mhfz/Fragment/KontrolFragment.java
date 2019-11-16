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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import radinyazilim.com.mhfz.Activities.EmployeeDetailActivity;
import radinyazilim.com.mhfz.Activities.LoginActivity;
import radinyazilim.com.mhfz.Api.ApiClient;
import radinyazilim.com.mhfz.Api.ExpertApiClient;
import radinyazilim.com.mhfz.Api.ExpertRestInterface;
import radinyazilim.com.mhfz.Api.FeedbackApiClient;
import radinyazilim.com.mhfz.Api.FeedbackRestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.ExpertModel;
import radinyazilim.com.mhfz.models.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KontrolFragment extends Fragment {
    ImageButton envanter_ok,file_ok,halat_ok,cevre_ok;
    ImageButton envanter_minus,file_minus,halat_minus,cevre_minus;
    private static final String TAG = "Mhfz-> ";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kontrol,null);
        envanter_ok = view.findViewById(R.id.env_ok);
        envanter_minus = view.findViewById(R.id.env_minus);
        file_ok = view.findViewById(R.id.file_ok);
        file_minus = view.findViewById(R.id.file_minus);
        halat_ok = view.findViewById(R.id.halat_ok);
        halat_minus = view.findViewById(R.id.halat_minus);
        cevre_ok = view.findViewById(R.id.cevre_ok);
        cevre_minus = view.findViewById(R.id.cevre_minus);

        envanter_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Envanterlerin tam olduğuna dair bildiriminiz şirkete iletilmiştir.");
                createPost("İşçi envanterinde eksik yok.");
            }
        });


        envanter_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Envanterlerin tam olmadığına dair bildiriminiz şirkete iletilmiştir");
                createPost("İşçi envanterinde eksik var.");

            }
        });

        file_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Fileler tam ve sağlam olduğuna dair mesajınız şirkete iletilmiştir.");
                createPost("Fileler tam ve sağlam.");
            }
        });



        file_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Fileler tam ve sağlam olmadığına dair bildiriminiz şirkete iletilmiştir.");
                createPost("Fileler tam ve sağlam değil.");
            }
        });
        halat_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Halatlar tam ve sağlam olduğuna dair bildiriminiz şirkete iletilmiştir.");
                createPost("Halatlar tam ve sağlam.");
            }
        });


        halat_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Halatlar tam ve sağlam olmadığına dair bildiriminiz şirkete iletilmiştir.");
                createPost("Halatlar tam ve sağlam değil.");
            }
        });
        cevre_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Çevre koruma tedbirlerinin alındığına dair bildiriminiz şirkete iletilmiştir.");
                createPost("Çevre koruması sağlandı.");
            }
        });


        cevre_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Kayıt","Çevre koruma tedbirlerinin alınmadığına dair bildiriminiz şirkete iletilmiştir.");
                createPost("Çevre koruması sağlanamadı.");
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
                    Toast.makeText(getContext(),"Durum Şirketinize Bildirildi",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),"Bir hata oluştu lütfen tekrar deneyin.",Toast.LENGTH_LONG).show();
                }
                FeedbackModel postResponse = response.body();



            }

            @Override
            public void onFailure(Call<FeedbackModel> call, Throwable t) {

            }
        });

    }

    public void showDialog(String title,String message){
         new MaterialDialog.Builder(getContext())
                 .title(title)
                .content(message)
                .positiveText("TAMAM")
                .positiveColor(getResources().getColor(R.color.colorPrimaryDark))
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        //
                    }
                })
                .build()
                .show();
    }

}