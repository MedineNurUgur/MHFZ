package radinyazilim.com.mhfz.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

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
        idTxt = findViewById(R.id.Id);
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
        timeTxt.setText(time);

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost("Acil Durum");
                Alert("Dikkat","Acil Durum şirketinize bildirildi.");
            }
        });

    }
    private void Alert(String title,String message){

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(EmployeeDetailActivity.this);
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
}
