package radinyazilim.com.mhfz.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import radinyazilim.com.mhfz.R;


public class IntroSettingsFragment extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    private static final String ARG_IMAGE_RES_ID = "imageResId";
    private static final String ARG_DESCRIPTION_RES_ID = "descriptionResId";
    private static final String ARG_TITLE_RES_ID = "titleResId";
    private int layoutResId;
    private int imageResId;
    private int descriptionResId;
    private int titleResId;

    public static IntroSettingsFragment newInstance(int layoutResId, int imageResId,int descriptionResId,int titleResId) {
        IntroSettingsFragment fragment = new IntroSettingsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        args.putInt(ARG_DESCRIPTION_RES_ID, descriptionResId);
        args.putInt(ARG_TITLE_RES_ID,titleResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        }
        if (getArguments() != null && getArguments().containsKey(ARG_IMAGE_RES_ID)) {
            imageResId = getArguments().getInt(ARG_IMAGE_RES_ID);
        }
        if (getArguments() != null && getArguments().containsKey(ARG_DESCRIPTION_RES_ID)) {
            descriptionResId = getArguments().getInt(ARG_DESCRIPTION_RES_ID);
        }
        if(getArguments() != null && getArguments().containsKey(ARG_TITLE_RES_ID)){
            titleResId = getArguments().getInt(ARG_TITLE_RES_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_intro_settings, container, false);
        TextView text=view.findViewById(R.id.app_intro_description);
        ImageView image=view.findViewById(R.id.app_intro_image);
        TextView title = view.findViewById(R.id.app_intro_title);

        text.setText(descriptionResId);
        image.setBackgroundResource(imageResId);
        title.setText(titleResId);

        return view;
    }



}
