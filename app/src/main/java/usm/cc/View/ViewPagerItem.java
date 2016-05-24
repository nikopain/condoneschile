package usm.cc.View;

import android.view.View;

import usm.cc.Model.Condom;

public class ViewPagerItem {
    Condom condoms;
    public ViewPagerItem(Condom condoms){
        this.condoms = condoms;
    }
    public Condom getCondoms() {
        return condoms;
    }
}

