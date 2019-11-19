package radinyazilim.com.mhfz.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import java.io.ByteArrayOutputStream;
import radinyazilim.com.mhfz.Activities.FotoControlActivity;
import radinyazilim.com.mhfz.Api.FeedbackApiClient;
import radinyazilim.com.mhfz.Api.FeedbackRestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KontrolFragment extends Fragment {
    private static final int RESULT_OK = -1;
    ImageButton envanter_ok,file_ok,halat_ok,cevre_ok;
    FloatingActionButton foto;
    private Boolean controlArr[];
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

        file_ok = view.findViewById(R.id.file_ok);

        halat_ok = view.findViewById(R.id.halat_ok);
        cevre_ok = view.findViewById(R.id.cevre_ok);
        foto = view.findViewById(R.id.foto);

        controlArr=new Boolean[]{true,true,true,true};
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
                if (controlArr[0]){
                    controlArr[0]=false;
                    envanter_ok.setBackgroundResource(R.drawable.delete);
                }
                else {
                    envanter_ok.setBackgroundResource(R.drawable.checkmark);
                    controlArr[0]=true;
                }



                createPost("İşçi envanterinde eksik yok.");
            }
        });



        file_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlArr[1]){
                    controlArr[1]=false;
                    file_ok.setBackgroundResource(R.drawable.delete);
                }
                else {
                    file_ok.setBackgroundResource(R.drawable.checkmark);
                    controlArr[1]=true;
                }
                createPost("Fileler tam ve sağlam.");
            }
        });



        halat_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlArr[2]){
                    controlArr[2]=false;
                    halat_ok.setBackgroundResource(R.drawable.delete);
                }
                else {
                    halat_ok.setBackgroundResource(R.drawable.checkmark);
                    controlArr[2]=true;
                }

                createPost("Halatlar tam ve sağlam.");
            }
        });


        cevre_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlArr[3]){
                    controlArr[3]=false;
                    cevre_ok.setBackgroundResource(R.drawable.delete);
                }
                else {
                    cevre_ok.setBackgroundResource(R.drawable.checkmark);
                    controlArr[3]=true;
                }

                createPost("Çevre koruması sağlandı.");
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