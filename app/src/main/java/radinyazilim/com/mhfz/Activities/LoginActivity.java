package radinyazilim.com.mhfz.Activities;
// İş Güvenliği Uzmanları için login ekranı
// Bu ekranda veri olarak bir id ve parola alınacaktır.
// Buradan alınan veriler veritabanında karşılaştırıldığında sonuçlar eşleşirse
// kullanıcı giriş yapacak, eğer yanlışsa 'alert dialog' metni ile karşılaşacak.

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import radinyazilim.com.mhfz.Api.ApiClient;
import radinyazilim.com.mhfz.Api.RestInterface;
import radinyazilim.com.mhfz.R;
import radinyazilim.com.mhfz.models.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // Ekranda bulunan itemların tanıtılması

    EditText id_number;
    EditText password;
    Button girisButton;
    CheckBox checkBox;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String ID;
    String pass;
    RestInterface restInterface;
    List<LoginModel> LoginList = new ArrayList<>();
    LoginModel expert;
    private static final String TAG = "Mhfz-> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);




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


        id_number = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        girisButton = findViewById(R.id.cirLoginButton);
        checkBox = findViewById(R.id.checkBox);

        //  SharedPreferences ile checkbox'ı aktif etme

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        if(preferences.getBoolean("login",false)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        if (preferences.getBoolean("isChecked", false)) {
            id_number.setText(preferences.getString("userID", ""));
            password.setText(preferences.getString("userPass", ""));
            checkBox.setChecked(true);

        }

        // Giriş butonuna tıklandığında

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id_number.getText().toString().equals("") && !password.getText().toString().equals("")) {

                    for (int i = 0; i < LoginList.size(); i++) {
                        if (id_number.getText().toString().equals(LoginList.get(i).id)) {
                            expert = LoginList.get(i);
                            break;
                        }
                    }
                    if (expert != null) {
                        if (password.getText().toString().equals(expert.password)) {
                            if (checkBox.isChecked()) {
                                setRemind(id_number.getText().toString(), password.getText().toString());
                            }
                            else{
                                editor.putBoolean("isChecked", true);
                                editor.commit();
                            }
                            editor.putBoolean("login",true);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Alert("Hatalı Giriş", "Parola yanlış! Tekrar Deneyin");
                        }
                    }
                    else{
                        Alert("Hatalı Giriş","Kullanıcı Id yanlış! Lütfen tekrar deneyiniz.");
                    }


                }
                else {
                    Alert("Hatalı Giriş","Alanlar boş bırakılamaz");

                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (controlFirstTime()){
            startActivity(new Intent(LoginActivity.this, IntroActivity.class));
        }
    }

    private void setRemind(String id, String pass) {
        editor.putString("userID", id);
        editor.putString("userPass", pass);
        editor.putBoolean("isChecked", true);
        editor.commit();
    }
    private void Alert(String title,String message){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(LoginActivity.this);
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

    private boolean controlFirstTime(){
        SharedPreferences preferences = getSharedPreferences("isFirstTime", MODE_PRIVATE);
        return preferences.getBoolean("isFirstTime", true);
    }
}