package potential_couscous.couscousdrive.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import potential_couscous.couscousdrive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlatoonFragment extends Fragment {
    private IPlatoon mController;

    public PlatoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_platoon, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRippleBackgroundListener(view);
    }

    private void setRippleBackgroundListener(View view) {
        RippleBackground rippleBackground = (RippleBackground) view.findViewById(R.id.content);
        ImageView imageView = (ImageView) view.findViewById(R.id.centerImage);

        if (mController != null) {
            mController.setRippleBackgroundListener(rippleBackground, imageView);
        }
    }

    public void setIController(IPlatoon controller) {
        mController = controller;
    }

}