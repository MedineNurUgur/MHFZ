package radinyazilim.com.mhfz.Activities;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

import radinyazilim.com.mhfz.Fragment.IntroSettingsFragment;
import radinyazilim.com.mhfz.R;


public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.intro_purpose,R.string.intro_purpose_title));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.intro_employee_error,R.string.intro_employee_error_title));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.intro_employee,R.string.intro_employee_title));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.intro_external_factors,R.string.intro_external_factors_title));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.intro_information,R.string.intro_information_title));
        addSlide(IntroSettingsFragment.newInstance(R.layout.fragment_intro_settings,R.drawable.logomuz,R.string.intro_email,R.string.intro_email_title));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateFirstTimeStatus();
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        updateFirstTimeStatus();
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        updateFirstTimeStatus();
        finish();
    }

    private void updateFirstTimeStatus() {
        SharedPreferences preferences = getSharedPreferences("isFirstTime", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putBoolean("isFirstTime", false);
        prefEditor.apply();
    }

}
