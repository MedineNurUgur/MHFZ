package radinyazilim.com.mhfz.Activities;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import radinyazilim.com.mhfz.Api.FeedbackApiClient;
import radinyazilim.com.mhfz.Api.FeedbackRestInterface;
import radinyazilim.com.mhfz.Api.InventoryStateApiClient;
import radinyazilim.com.mhfz.Api.InventoryStateRestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.EmployeeModel;
import radinyazilim.com.mhfz.models.FeedbackModel;
import radinyazilim.com.mhfz.models.InventoryStateModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetailActivity extends AppCompatActivity {
    private static final String TAG = "Mhfz-> ";

    TextView nameTxt,idTxt,puanTxt,timeTxt;
    Button positive;
    String name,id;
    String puan;
    String time;
    Toolbar mToolbar;
    InventoryStateModel inventoryState;
    InventoryStateRestInterface restInterface;
    Integer pos = 1;
    Integer neg = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        nameTxt = findViewById(R.id.txt_name);
        idTxt = findViewById(R.id.txt_id);
        puanTxt = findViewById(R.id.txt_puan);
        timeTxt = findViewById(R.id.txt_time);
        positive = findViewById(R.id.btn_feedback);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("Name");
        id = extras.getString("Id");
        puan = extras.getString("Puan");
        time = extras.getString("Time");

        nameTxt.setText(name);
        idTxt.setText(id);
        puanTxt.setText(puan);
        timeTxt.setText(time+" "+getResources().getString(R.string.minute));

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(getResources().getString(R.string.emergency_dialog_title),getResources().getString(R.string.emergency_dialog));
            }
        });

    }
    public void showDialog(String title,String message){
        new MaterialDialog.Builder(this)
                .title(title)
                .content(message)
                .positiveText(getResources().getString(R.string.emergency_dialog_positive_button))
                .negativeText(getResources().getString(R.string.emergency_dialog_negative_button))
                .positiveColor(getResources().getColor(R.color.colorPrimaryDark))
                .negativeColor(getResources().getColor(R.color.kirmizi))
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        createPost("Acil Durum");
                        dialog.dismiss();

                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }
    private void createPost(String feedbackMesage){
        FeedbackRestInterface feedbackRestInterface = FeedbackApiClient.getClient().create(FeedbackRestInterface.class);

        FeedbackModel feedback = new FeedbackModel(feedbackMesage);
        Call<FeedbackModel> call= feedbackRestInterface.createPost(feedback);
        call.enqueue(new Callback<FeedbackModel>() {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                showSuccessMessage();
                if(!response.isSuccessful()){
                    showSuccessMessage();
                }
                else {
                    showSuccessMessage();
                   // Toast.makeText(EmployeeDetailActivity.this,"Bir hata oluştu lütfen tekrar deneyin.",Toast.LENGTH_LONG).show();
                }
                FeedbackModel postResponse = response.body();
            }

            @Override
            public void onFailure(Call<FeedbackModel> call, Throwable t) {

            }
        });

    }
    private void showSuccessMessage() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_view,
                (ViewGroup) findViewById(R.id.toast_container));
        layout.setPadding(50, 0, 50, 0);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 75);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
