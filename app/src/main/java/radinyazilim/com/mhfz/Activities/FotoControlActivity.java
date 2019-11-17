package radinyazilim.com.mhfz.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;

import radinyazilim.com.mhfz.Api.ApiClient;
import radinyazilim.com.mhfz.Api.ControlsRestInterface;
import radinyazilim.com.mhfz.Api.FeedbackRestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.ControlModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FotoControlActivity extends AppCompatActivity {
    ImageView image;
    String encodedImage;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_control);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        image = (ImageView) findViewById(R.id.imageView);
        description = findViewById(R.id.editInformation);

        Bundle extras = getIntent().getExtras();
        final byte[] byteArray = extras.getByteArray("image");
        Bitmap bitMap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bitMap);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = description.getText().toString();

                Bitmap image1 = ((BitmapDrawable) image.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image1.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                byte[] byteArray;
                byteArray = byteArrayOutputStream.toByteArray();
                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                ControlModel control = new ControlModel(byteArray,message);
                createPost(control);

                //Toast.makeText(FotoControlActivity.this,"Eksiklik şirketinize iletilmiştir.",Toast.LENGTH_LONG).show();
                finish();

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void createPost(ControlModel control){
        ControlsRestInterface controlsRestInterface = ApiClient.getClient().create(ControlsRestInterface.class);
        Call<ControlModel> call= controlsRestInterface.createPost(control);
        call.enqueue(new Callback<ControlModel>() {
            @Override
            public void onResponse(Call<ControlModel> call, Response<ControlModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(FotoControlActivity.this,"Durum Şirketinize Bildirildi",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(FotoControlActivity.this,"Bir hata oluştu lütfen tekrar deneyin.",Toast.LENGTH_LONG).show();
                }
                ControlModel postResponse = response.body();



            }

            @Override
            public void onFailure(Call<ControlModel> call, Throwable t) {

            }
        });

    }

}
