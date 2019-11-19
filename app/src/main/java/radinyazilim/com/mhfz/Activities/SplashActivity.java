package radinyazilim.com.mhfz.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.sql.Time;
import java.sql.Timestamp;

import radinyazilim.com.mhfz.R;

public class SplashActivity extends AppCompatActivity {
    Thread timerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(5000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            timerThread.start();
        } else {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SplashActivity.this);
            builder.setTitle(getResources().getString(R.string.network_dialog_title));
            builder.setMessage(getResources().getString(R.string.network_dialog_message));
            builder.setCancelable(false);
            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            finish();
        }


    }

}
