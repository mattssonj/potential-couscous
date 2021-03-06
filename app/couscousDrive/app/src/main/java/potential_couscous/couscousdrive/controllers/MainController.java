package potential_couscous.couscousdrive.controllers;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ToggleGroup;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.FragmentFactory;

/**
 * Main controller responsible for toggle buttons, changing fragments.
 */
public class MainController extends AbstractController {
    private IFragmentChanger mFragmentReplacer;

    private JoystickController mJoystickController;
    private ACCController mACCController;
    private PlatoonController mPlatoonController;

    public MainController() {
    }

    public void setToggleButtonListener(final ToggleGroup toggleGroup) {
        toggleGroup.setOnCheckedChangeListener(new ToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ToggleGroup group, @IdRes int[] checkedId) {
                CarCom carCom = CarCom.getCarCom();

                switch (toggleGroup.getCheckedId()) {
                    case (R.id.manual_button):
                        replaceFragment(FragmentFactory.createJoystickFragment(mJoystickController));
                        if (isCarCom(carCom)) {
                            carCom.sendData(carCom.MANUAL_KEY);
                        }
                        break;
                    case (R.id.acc_button):
                        replaceFragment(FragmentFactory.createACCFragment(mACCController));

                        if (isCarCom(carCom)) {
                            carCom.sendData(carCom.ACC_KEY);
                        }
                        break;
                    case (R.id.platoon_button):
                        replaceFragment(FragmentFactory.createPlatoonFragment(mPlatoonController));

                        if (isCarCom(carCom)) {
                            carCom.sendData(carCom.PLATOON_KEY);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * This method tells MainActivity to change fragment
     *
     * @param fragment is the fragment you want to display.
     */
    private void replaceFragment(Fragment fragment) {
        if (mFragmentReplacer != null) {
            mFragmentReplacer.replaceFragment(fragment);
        }
    }

    public void setFragmentReplacer(IFragmentChanger fragmentReplacer) {
        mFragmentReplacer = fragmentReplacer;
    }

    public void setJoystickController(JoystickController joystickController) {
        mJoystickController = joystickController;
    }

    public void setACCController(ACCController ACCController) {
        mACCController = ACCController;
    }

    public void setPlatoonController(PlatoonController platoonController) {
        mPlatoonController = platoonController;
    }
}