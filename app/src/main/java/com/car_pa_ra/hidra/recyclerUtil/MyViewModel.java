package com.car_pa_ra.hidra.recyclerUtil;

import com.car_pa_ra.hidra.model.Grupos;
import androidx.lifecycle.ViewModel;

public
class MyViewModel extends ViewModel {

    public Grupos g;

    public Grupos getG() {
        return g;
    }

    public void setG(Grupos g) {
        this.g = g;
    }
}
