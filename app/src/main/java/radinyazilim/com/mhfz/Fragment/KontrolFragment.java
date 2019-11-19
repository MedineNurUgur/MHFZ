package radinyazilim.com.mhfz.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import radinyazilim.com.mhfz.Activities.EmployeeDetailActivity;
import radinyazilim.com.mhfz.Activities.FotoControlActivity;
import radinyazilim.com.mhfz.Activities.LoginActivity;
import radinyazilim.com.mhfz.Api.ApiClient;
import radinyazilim.com.mhfz.Api.ControlsRestInterface;
import radinyazilim.com.mhfz.Api.ExpertApiClient;
import radinyazilim.com.mhfz.Api.ExpertRestInterface;
import radinyazilim.com.mhfz.Api.FeedbackApiClient;
import radinyazilim.com.mhfz.Api.FeedbackRestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.ControlModel;
import radinyazilim.com.mhfz.models.ExpertModel;
import radinyazilim.com.mhfz.models.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KontrolFragment extends Fragment {
    private static final int RESULT_OK = -1;
    ImageButton envanter_ok,file_ok,halat_ok,cevre_ok;
    ImageButton envanter_minus,file_minus,halat_minus,cevre_minus;
    ImageButton foto;
    private static final int IMAGE_ACTION_CODE = 102;

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
        foto = view.findViewById(R.id.foto);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhotoIntent, IMAGE_ACTION_CODE);
            }
        });

        envanter_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_envanter_alert_positive));
                createPost("İşçi envanterinde eksik yok.");
            }
        });


        envanter_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_envanter_alert_negative));
                createPost("İşçi envanterinde eksik var.");

            }
        });

        file_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_file_alert_positive));
                createPost("Fileler tam ve sağlam.");
            }
        });



        file_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_file_alert_negative));
                createPost("Fileler tam ve sağlam değil.");
            }
        });
        halat_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_halat_alert_positive));
                createPost("Halatlar tam ve sağlam.");
            }
        });


        halat_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_halat_alert_negative));
                createPost("Halatlar tam ve sağlam değil.");
            }
        });
        cevre_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_around_alert_positive));
                createPost("Çevre koruması sağlandı.");
            }
        });


        cevre_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getResources().getString(R.string.feedback_alert_title),getResources().getString(R.string.control_around_alert_negative));
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
                .positiveText(getResources().getString(R.string.alert_button))
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;

        if(requestCode == IMAGE_ACTION_CODE){
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = new Intent(getContext(), FotoControlActivity.class);
            intent.putExtra("image",byteArray);
            startActivity(intent);
        }


    }


}