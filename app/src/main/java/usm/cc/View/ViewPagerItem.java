package usm.cc.View;

import android.view.View;

import usm.cc.Model.Condom;

public class ViewPagerItem {
    Condom condoms;
    String text;
    int color;

    public ViewPagerItem(String text, int color) {
        this.text = text;
        this.color = color;
    }
    public ViewPagerItem(Condom condoms){
        this.condoms = condoms;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Condom getCondoms() {
        return condoms;
    }

    public void setCondoms(Condom condoms) {
        this.condoms = condoms;
    }

}

