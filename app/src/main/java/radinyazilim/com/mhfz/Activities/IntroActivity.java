package radinyazilim.com.mhfz.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

import radinyazilim.com.mhfz.Fragment.IntroSettingsFragment;
import radinyazilim.com.mhfz.R;


public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.app_name));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.app_name));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.app_name));
    }
}
