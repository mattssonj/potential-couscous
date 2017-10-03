package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

import potential_couscous.couscousdrive.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;

public class ACCController {

    public ACCController(Button ACCButton) {
        setACCListener(ACCButton);
    }

    public void setACCListener (Button ACCButton) {
        ACCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarCom carCom = MainActivity.getCarCom();

                if (carCom != null && carCom.isConnected()) {
                    carCom.sendData(carCom.MANUAL_KEY, null);
                }
            }
        });
    }
}