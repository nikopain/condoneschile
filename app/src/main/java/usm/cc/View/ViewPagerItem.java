package usm.cc.View;

import android.view.View;

import usm.cc.Model.Condom;

public class ViewPagerItem {
    String text;
    int color;


    public ViewPagerItem(String text, int color) {
        this.text = text;
        this.color = color;
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
}

